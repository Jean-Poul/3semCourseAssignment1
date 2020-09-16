package facades;

import dto.JokeDTO;
import entities.Joke;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

/**
 *
 * @author jplm
 */
//Uncomment the line below, to temporarily disable this test
//@Disabled
public class JokeFacadeTest {

    private static EntityManagerFactory emf;
    private static JokeFacade facade;
    private static Joke m1, m2, m3;

    public JokeFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = JokeFacade.getFacadeExample(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
     public void setUp() {
        EntityManager em = emf.createEntityManager();
        m1 = new Joke("When Chuck Norris gets fast food, his order is ready before he walks in.", "https://api.chucknorris.io/", "Chuck Norris");
        m2 = new Joke("Chuck Norris doesnt have to shave, his beard shaves itself.", "https://api.chucknorris.io/", "Chuck Norris");
        m3 = new Joke("Chuck Norris can whistle in sign language.", "https://api.chucknorris.io/", "Chuck Norris");
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Joke.deleteAllRows").executeUpdate();
            em.persist(m1);
            em.persist(m2);
            em.persist(m3);
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
        assertEquals(3, facade.getCount(), "Expects tree rows in the database");
    }
    
    // Testing to see if all jokes has been insertet to the database
    @Test
    public void testGetAllJokes() {
        List<JokeDTO> joke = facade.getAllJokes();
        assertThat(joke, hasSize(3));
    }

    //  Testing to see if a List with JokeDTO has a joke with Id 2
    @Test
    public void testGetJokeById() {
        List<JokeDTO> joke = facade.getJokeId(m2.getId());
        assertEquals("2", joke.get(0).getId());
    }
    
    // Testing to see if joke id 3 contains the right joke
    @Test
    public void getJokeByJoke() {
        List<JokeDTO> joke = facade.getJokeId(m3.getId());
        assertEquals("Chuck Norris can whistle in sign language.", joke.get(0).getJoke());
    }
}
