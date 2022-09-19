package com.example.bacfragment;

public class Drinks {
    private double alcPercent;
    private int size;

    public Drinks(){
        //Default constructor
    }
    public Drinks(double alcPercent, int size){
        this.alcPercent = alcPercent;
        this.size = size;
    }

    //Getters
    public double getAlcPercent(){
        return this.alcPercent;
    }
    public int getSize(){
        return this.size;
    }
    //Setters
    public void setAlcPercent(double percent){
        this.alcPercent = percent;
    }
    public void setSize(int newSize){
        this.size = newSize;
    }
}
