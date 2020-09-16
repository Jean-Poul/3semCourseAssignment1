package facades;

import dto.MemberDTO;
import utils.EMF_Creator;
import entities.Member;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import static org.hamcrest.MatcherAssert.assertThat;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.hasSize;
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
    private static Member m1, m2, m3, m4, m5;

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

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    // Testing to see if count matches the database records
    @Test
    public void testMemberCount() {
        assertEquals(5, facade.getCount(), "Expects five rows in the database");
    }

    // Testing to see if all members has been insertet to the database
    @Test
    public void testGetAllMembers() {
        List<MemberDTO> member = facade.getAllMembers();
        assertThat(member, hasSize(5));
    }

    //  Testing to see if a List with MemberDTO has a member with studentId cph-jl360
    @Test
    public void testGetMemberById() {
        List<MemberDTO> member = facade.getStudentId(m3.getStudentId());
        assertEquals("cph-jl360", member.get(0).getStudentId());
    }

    // Testing to see if the FavoriteShows array contains Ozark
    @Test
    public void testMemberHasFavoriteShow() {
        List<MemberDTO> member = facade.getStudentId(m2.getStudentId());
        assertThat(member.get(0).getFavoriteShows(), Matchers.hasItemInArray("Ozark"));
    }

    // Testing to see if member contains name Per Kringelbach
    @Test
    public void getMemberByName() {
        List<MemberDTO> member = facade.getStudentId(m4.getStudentId());
        assertEquals("Per Kringelbach", member.get(0).getName());
    }
}