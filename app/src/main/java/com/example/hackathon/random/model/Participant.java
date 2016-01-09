package com.example.hackathon.random.model;

/**
 * Created by hackathon on 1/9/16.
 */
public class Participant {
    private String name;
    private String seed;

    public Participant() {
    }

    public Participant(String name, String seed) {
        this.name = name;
        this.seed = seed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "name='" + name + '\'' +
                ", seed='" + seed + '\'' +
                '}';
    }
}
