package com.example.hackathon.random.model;

/**
 * Created by hackathon on 1/9/16.
 */
public class Person {
    private String name;
    private String strength;

    public Person() {
    }

    public Person(String name, String strength) {
        this.name = name;
        this.strength = strength;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", strength='" + strength + '\'' +
                '}';
    }
}
