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
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;

/**
 *
 * @author jplm
 */
public class CarFacade {

    private static CarFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private CarFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static CarFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CarFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Get number of records in Car table from database
    public long getCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long count = (long) em.createQuery("SELECT COUNT(c) FROM Car c").getSingleResult();
            return count;
        } finally {
            em.close();
        }
    }

    // Get all cars
    public List<CarDTO> getAllCars() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Car> query = em.createQuery("SELECT m FROM Car m", Car.class);
        List<Car> cars = query.getResultList();
        List<CarDTO> carDTOs = new ArrayList();
        cars.forEach((Car car) -> {
            carDTOs.add(new CarDTO(car));
        });
        return carDTOs;
    }

    // Get cars by maker
    public List<CarDTO> getCarsByMaker(String maker) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Car> query = em.createQuery("SELECT m FROM Car m WHERE m.maker LIKE :maker", Car.class);
        query.setParameter("maker", "%" + maker + "%");
        List<Car> cars = query.getResultList();
        List<CarDTO> carDTOs = new ArrayList();
        cars.forEach((Car car) -> {
            carDTOs.add(new CarDTO(car));
        });
        return carDTOs;
    }

    // Get cars by year
    public List<CarDTO> getCarsByYear(int year) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT m FROM Car m WHERE m.year = :year");
        query.setParameter("year", year);
        List<Car> cars = query.getResultList();
        List<CarDTO> carDTOs = new ArrayList();
        cars.forEach((Car car) -> {
            carDTOs.add(new CarDTO(car));
        });
        return carDTOs;
    }

    // Inserting to database
    public static void main(String[] args) {
        //Create emf pointing to the dev-database
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE from Car").executeUpdate();
            em.persist(new Car(1990, "Ford", "Mustang", 25000));
            em.persist(new Car(1995, "Kia", "Rio", 3500));
            em.persist(new Car(2000, "Renault", "Megan RS", 30000));
            em.persist(new Car(2005, "Seat", "Leon", 7000));
            em.persist(new Car(2015, "Audi", "A3", 8500));
            em.persist(new Car(2020, "Audi", "A7", 10500));
            em.persist(new Car(2020, "Ford", "Focus", 5500));
            em.persist(new Car(2020, "Dacia", "Duster", 20000));
            em.persist(new Car(2020, "Renault", "Talisman", 3000));
            em.persist(new Car(2020, "Seat", "Ibitza", 7500));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
