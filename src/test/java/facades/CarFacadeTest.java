/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.CarDTO;
import dto.MemberDTO;
import entities.Car;
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
import utils.EMF_Creator;

/**
 *
 * @author jplm
 */
public class CarFacadeTest {

    private static EntityManagerFactory emf;
    private static CarFacade facade;
    private static Car m1, m2, m3, m4, m5, m6, m7, m8, m9, m10;

    public CarFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = CarFacade.getFacadeExample(emf);
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

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    // TODO: Delete or change this method 
    @Test
    public void testCarsCount() {
        assertEquals(10, facade.getCount(), "Expects 10 rows in the database");
    }

    // Testing to see if all cars has been insertet to the database
    @Test
    public void testGetAllMembers() {
        List<CarDTO> car = facade.getAllCars();
        assertThat(car, hasSize(10));
    }

    //  Testing to see if a List with CarDTO has a car with maker Ford
    @Test
    public void testGetCarsByMaker() {
        List<CarDTO> car = facade.getCarsByMaker(m1.getMaker());
        assertEquals("Ford", car.get(1).getMaker());
    }

//    //  Testing to see if a List with CarDTO has a car with maker Ford
//    @Test
//    public void testGetCarsByYear() {
//        List<CarDTO> car = facade.getCarsByYear(m5.getYear());
//        assertEquals(2015, car.get(1).getYear());
//    }

    // Testing to see if the car model array contains Rio
    @Test
    public void testCarModelByName() {
        List<CarDTO> car = facade.getCarsByMaker(m2.getMaker());
        assertEquals("Rio", car.get(0).getModel());
    }
}
