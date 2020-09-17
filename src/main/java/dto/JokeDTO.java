/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Joke;

/**
 *
 * @author jplm
 */
public class JokeDTO {

    // Private variables
    private Long id;
    private String joke;

    // Empty constructor
    public JokeDTO() {
    }

    // Constructor with a Joke object
    public JokeDTO(Joke joke) {
        this.id = joke.getId();
        this.joke = joke.getJoke();
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

}
