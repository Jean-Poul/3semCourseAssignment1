package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "Member.deleteAllRows", query = "DELETE from Member")
public class Member implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Private variables
    private Long id;
    private int age;
    private String firstName;
    private String lastName;
    private String educaton;

    // Empty constructor
    public Member() {
    }

    // TODO, delete this class, or rename to an Entity class that makes sense for what you are about to do
    // Delete EVERYTHING below if you decide to use this class, it's dummy data used for the initial demo
//    private String dummyStr1;
//    private String dummyStr2;
//
//    public Member(String dummyStr1, String dummyStr2) {
//        this.dummyStr1 = dummyStr1;
//        this.dummyStr2 = dummyStr2;
//    }
//
//    public String getDummyStr1() {
//        return dummyStr1;
//    }
//
//    public void setDummyStr1(String dummyStr1) {
//        this.dummyStr1 = dummyStr1;
//    }
//
//    public String getDummyStr2() {
//        return dummyStr2;
//    }
//
//    public void setDummyStr2(String dummyStr2) {
//        this.dummyStr2 = dummyStr2;
//    }
    // All variables constructor
    public Member(int age, String firstName, String lastName, String educaton) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.educaton = educaton;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEducaton() {
        return educaton;
    }

    public void setEducaton(String educaton) {
        this.educaton = educaton;
    }

}
