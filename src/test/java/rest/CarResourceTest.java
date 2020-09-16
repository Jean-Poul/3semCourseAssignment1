/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import entities.Car;
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
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

/**
 *
 * @author jplm
 */
public class CarResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private static Car m1, m2, m3, m4, m5, m6, m7, m8, m9, m10;

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

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        m1 = new Car(1990, "Ford", "Mustang", 25000);
        m2 = new Car(1995, "Kia", "Rio", 3500);
        m3 = new Car(2000, "Renault", "Megan RS", 30000);
        m4 = new Car(2005, "Seat", "Leon", 7000);
        m5 = new Car(2015, "Audi", "A3", 8500);
        m6 = new Car(2020, "Audi", "A7", 10500);
        m7 = new Car(2020, "Ford", "Focus", 5500);
        m8 = new Car(2020, "Dacia", "Duster", 20000);
        m9 = new Car(2020, "Ranault", "Talisman", 3000);
        m10 = new Car(2020, "Seat", "Ibitza", 7500);
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Car.deleteAllRows").executeUpdate();
            em.persist(m1);
            em.persist(m2);
            em.persist(m3);
            em.persist(m4);
            em.persist(m5);
            em.persist(m6);
            em.persist(m7);
            em.persist(m8);
            em.persist(m9);
            em.persist(m10);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    public void testServerIsUp() {
        System.out.println("Testing is server UP");
        given().when().get("/car").then().statusCode(200);
    }

    // Test for car count
    @Test
    public void testCount() throws Exception {
        given()
                .contentType("application/json")
                .get("/car/count").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("count", equalTo(10));
    }

    // Test for all cars
    @Test
    public void testGetAll() {
        given()
                .contentType("application/json")
                .get("/car/all")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("model", hasItems("A7",
                        "Rio",
                        "Focus",
                        "Mustang",
                        "Talisman",
                        "A3",
                        "Ibitza",
                        "Duster",
                        "Megan RS",
                        "Leon"));
    }

    // Test to see if studentId matches with cph-as509
    @Test
    public void testFindByMaker() {
        given().
                get("/car/" + m3.getMaker())
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("maker", hasItems("Renault"));
    }

    // Testing to see if a model does not contain a specific model
    @Test
    public void testFindByModelNotFound() {
        given().
                get("/car/Mustang")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("model", not(hasItem("Stinger")));
    }
}
