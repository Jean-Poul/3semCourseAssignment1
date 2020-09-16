/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Member;

/**
 *
 * @author jplm
 */
public class MemberDTO {

    // Private variables
    private Long id;
    private int age;
    private String firstName;
    private String lastName;
    private String educaton;

    // Empty constructor
    public MemberDTO() {
    }

    // Constructor with a Member object
    public MemberDTO(Member member) {
        this.id = member.getId();
        this.age = member.getAge();
        this.firstName = member.getFirstName();
        this.lastName = member.getLastName();
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
