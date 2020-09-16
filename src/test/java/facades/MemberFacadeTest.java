package facades;

import utils.EMF_Creator;
import entities.Member;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class MemberFacadeTest {

    private static EntityManagerFactory emf;
    private static MemberFacade facade;

    public MemberFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = MemberFacade.getFacadeExample(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Member.deleteAllRows").executeUpdate();
            em.persist(new Member(30, "cph-ml616", "Mick Larsen", new String[]{"The Wire", "Silicon Valley", "Supernatural"}, "Java"));
            em.persist(new Member(27, "cph-as509", "Alexander Pihl", new String[]{"Power", "Ray Donovan", "Ozark"}, "Java"));
            em.persist(new Member(35, "cph-jl360", "Jean-Poul Leth-Møller", new String[]{"Big Bang Theory", "Star trek", "Game of Thrones"}, "Java"));
            em.persist(new Member(50, "cph-pk171", "Per Kringelbach", new String[]{"Stranger Things", "The expanse", "Mash"}, "Java"));
            em.persist(new Member(30, "cph-mr462", "Morten Rasmussen", new String[]{"Stripper kongens piger", "Bang bros", "Fake Taxi"}, "Java"));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    // TODO: Delete or change this method 
    @Test
    public void testAFacadeMethod() {
        assertEquals(5, facade.getCount(), "Expects two rows in the database");
    }

}
