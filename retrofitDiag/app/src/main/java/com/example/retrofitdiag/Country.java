package com.example.retrofitdiag;

public class Country implements Comparable<Country> {
    private String name;
    private String population;

    public Country(String name, String population) {
        this.name = name;
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public int compareTo (Country country) {
        return Integer.parseInt(this.getPopulation()) - Integer.parseInt(country.getPopulation());
    }
}
