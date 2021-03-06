package facades;

import dto.MemberDTO;
import entities.Member;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;

/**
 *
 * @author jplm
 */
public class MemberFacade {

    private static MemberFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private MemberFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MemberFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MemberFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Get number of records in Member table from database
    public long getCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long count = (long) em.createQuery("SELECT COUNT(m) FROM Member m").getSingleResult();
            return count;
        } finally {
            em.close();
        }

    }

    // Get all groupmembers
    public List<MemberDTO> getAllMembers() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m", Member.class);
        List<Member> members = query.getResultList();
        List<MemberDTO> memberDTOs = new ArrayList();
        members.forEach((Member member) -> {
            memberDTOs.add(new MemberDTO(member));
        });
        return memberDTOs;
    }

    // Get groupmember by studentId
    public List<MemberDTO> getStudentId(String studentId) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m WHERE m.studentId LIKE :studentId", Member.class);
        query.setParameter("studentId", "%" + studentId + "%");
        List<Member> members = query.getResultList();
        List<MemberDTO> memberDTOs = new ArrayList();
        members.forEach((Member member) -> {
            memberDTOs.add(new MemberDTO(member));
        });
        return memberDTOs;
    }

    // Inserting to database
    public static void main(String[] args) {
        //Create emf pointing to the dev-database
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE from Member").executeUpdate();
            em.persist(new Member(30, "cph-ml616", "Mick Larsen", new String[]{"The Wire", "Silicon Valley", "Supernatural"}, "Java"));
            em.persist(new Member(27, "cph-as509", "Alexander Pihl", new String[]{"Power", "Ray Donovan", "Ozark"}, "Java"));
            em.persist(new Member(35, "cph-jl360", "Jean-Poul Leth-Møller", new String[]{"Big Bang Theory", "Star trek", "Game of Thrones"}, "Java"));
            em.persist(new Member(50, "cph-pk171", "Per Kringelbach", new String[]{"Stranger Things", "The expanse", "Mash"}, "Java"));
            em.persist(new Member(30, "cph-mr462", "Morten Rasmussen", new String[]{"Stripper Kongens Piger", "Bang bros", "Fake Taxi"}, "Java"));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}
