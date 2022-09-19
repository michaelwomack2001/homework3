package com.example.bacfragment;

public class Profile {
    private double weight;
    private String gender;

    public Profile(){
        //Default constructor
    }
    public Profile(Double weight, String gender){
        this.gender = gender;
        this.weight = weight;
    }

    //Getters
    public double getWeight(){
        return this.weight;
    }
    public String getGender(){
        return this.gender;
    }
    //Setters
    public void setWeight(double newWeight){
        this.weight = newWeight;
    }
    public void setGender(String newGender){
        this.gender = newGender;
    }
}
