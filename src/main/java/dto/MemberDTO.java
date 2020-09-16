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
    private String studentId;
    private String name;
    private String[] favoriteShows;

    // Empty constructor
    public MemberDTO() {
    }

    // Constructor with a Member object
    public MemberDTO(Member member) {
        this.studentId = member.getStudentId();
        this.name = member.getName();
        this.favoriteShows = member.getFavoriteShows();
    }

    // Getters and setters
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
    

}
