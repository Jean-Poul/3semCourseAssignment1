package rest;

import entities.Member;
import utils.EMF_Creator;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.parsing.Parser;
import java.net.URI;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
//Uncomment the line below, to temporarily disable this test
//@Disabled

public class MemberResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private static Member m1, m2, m3, m4, m5;

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        //System.in.read();
        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    // Setup the DataBase (used by the test-server and this test) in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the EntityClass used below to use YOUR OWN (renamed) Entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        m1 = new Member(30, "cph-ml616", "Mick Larsen", new String[]{"The Wire", "Silicon Valley", "Supernatural"}, "Java");
        m2 = new Member(27, "cph-as509", "Alexander Pihl", new String[]{"Power", "Ray Donovan", "Ozark"}, "Java");
        m3 = new Member(35, "cph-jl360", "Jean-Poul Leth-Møller", new String[]{"Big Bang Theory", "Star trek", "Game of Thrones"}, "Java");
        m4 = new Member(50, "cph-pk171", "Per Kringelbach", new String[]{"Stranger Things", "The expanse", "Mash"}, "Java");
        m5 = new Member(30, "cph-mr462", "Morten Rasmussen", new String[]{"Stripper kongens piger", "Bang bros", "Fake Taxi"}, "Java");
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Member.deleteAllRows").executeUpdate();
            em.persist(m1);
            em.persist(m2);
            em.persist(m3);
            em.persist(m4);
            em.persist(m5);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    public void testServerIsUp() {
        System.out.println("Testing is server UP");
        given().
                when()
                .get("/groupmembers")
                .then()
                .statusCode(200);
    }

    //This test assumes the database contains two rows
//    @Test
//    public void testDummyMsg() throws Exception {
//        given()
//                .contentType("application/json")
//                .get("/member/").then()
//                .assertThat()
//                .statusCode(HttpStatus.OK_200.getStatusCode())
//                .body("msg", equalTo("Hello World"));
//    }

    // Test for groupmember count
    @Test
    public void testCount() throws Exception {
        given()
                .contentType("application/json")
                .get("/groupmembers/count").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("count", equalTo(5));
    }

    // Test for all groupmembers
    @Test
    public void testGetAll() {

        given()
                .contentType("application/json")
                .get("/groupmembers/all")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("name", hasItems("Mick Larsen",
                        "Alexander Pihl",
                        "Jean-Poul Leth-Møller",
                        "Per Kringelbach",
                        "Morten Rasmussen"));
    }
}
