/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.JokeDTO;
import entities.Joke;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;

/**
 *
 * @author jplm
 */
public class JokeFacade {
    private static JokeFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private JokeFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static JokeFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new JokeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    // Get number of records in Joke table from database
    public long getCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long count = (long)em.createQuery("SELECT COUNT(j) FROM Joke j").getSingleResult();
            return count;
        }finally{  
            em.close();
        }
        
    }
    
    // Get all jokes
    public List<JokeDTO> getAllJokes() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Joke> query = em.createQuery("SELECT j FROM Joke j", Joke.class);
        List<Joke> jokes = query.getResultList();
        List<JokeDTO> jokeDTOs = new ArrayList();
        jokes.forEach((Joke joke) -> {
            jokeDTOs.add(new JokeDTO(joke));
        });
        return jokeDTOs;
    }
    
     // Get joke by id
    public JokeDTO getJokeById(long id) {
        EntityManager em = emf.createEntityManager();
        Joke j = em.find(Joke.class, id);
        return new JokeDTO(j);
    }
    
    
     // Inserting to database
    public static void main(String[] args) {
        //Create emf pointing to the dev-database
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE from Joke").executeUpdate();
            em.persist(new Joke("Every one of Chuck Norris' restaurants specialize in serving piping-hot knuckle sandwiches to anyone who dares to complain.","https://api.chucknorris.io/","Chuck Norris"));
            em.persist(new Joke("When Chuck Norris gets fast food, his order is ready before he walks in.","https://api.chucknorris.io/","Chuck Norris"));
            em.persist(new Joke("There is no Ninja Turtle cereal because eating ninjas for breakfast is a copyright of Chuck Norris.","https://api.chucknorris.io/","Chuck Norris"));
            em.persist(new Joke("Chuck Norris doesnt have to shave, his beard shaves itself.","https://api.chucknorris.io/","Chuck Norris"));
            em.persist(new Joke("Chuck Norris can whistle in sign language.","https://api.chucknorris.io/","Chuck Norris"));
            em.persist(new Joke("Chuck Norris won the 1994 Grammy for best new song. His song went like this: \"The wheels on the bus go fuck yourself.","https://api.chucknorris.io/","Chuck Norris"));
            em.persist(new Joke("Chuck Norris created the alphabet just so he could spell the words \"kicked your ass.","https://api.chucknorris.io/","Chuck Norris"));
            em.persist(new Joke("Lightning is like a Chuck Norris roundhouse kick in the face. In a flash it's gone. Both lightning & your face.","https://api.chucknorris.io/","Chuck Norris"));
            em.persist(new Joke("Chuck Norris created tetris when he bent several people spines and stacked them on top of each other.","https://api.chucknorris.io/","Chuck Norris"));
            em.persist(new Joke("Chuck Norris is the only person in the world that can actually email a roundhouse kick.","https://api.chucknorris.io/","Chuck Norris"));        
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
}
