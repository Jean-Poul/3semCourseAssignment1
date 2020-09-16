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
    private String studentId;
    private String name;
    private String[] favoriteShows;
    private String favoriteCodingLanguage;

    // Empty constructor
    public Member() {
    }

    // All variables constructor
    public Member(int age, String studentId, String name, String[] favoriteShows, String favoriteCodingLanguage) {
        this.age = age;
        this.studentId = studentId;
        this.name = name;
        this.favoriteShows = favoriteShows;
        this.favoriteCodingLanguage = favoriteCodingLanguage;
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

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getFavoriteShows() {
        return favoriteShows;
    }

    public void setFavoriteShows(String[] favoriteShows) {
        this.favoriteShows = favoriteShows;
    }

    public String getFavoriteCodingLanguage() {
        return favoriteCodingLanguage;
    }

    public void setFavoriteCodingLanguage(String favoriteCodingLanguage) {
        this.favoriteCodingLanguage = favoriteCodingLanguage;
    }

}
