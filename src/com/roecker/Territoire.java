package com.roecker;

import java.util.ArrayList;
import java.util.Random;

public class Territoire {

    private final int id;
    private int force;
    private ArrayList<Integer> idNeighbours;

    public Territoire(int id) {
        this.id = id;
        this.idNeighbours = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public int getForce() {
        return force;
    }

    public ArrayList<Integer> getIdNeighbours() {
        return idNeighbours;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public void addNeighbour(int id){
        idNeighbours.add(id);
    }

    public void addForce(int de){
        this.force += de;
    }

    public int diceThrower(){
        int dice = 5;
        int throwDice = 0;
        Random rand = new Random();
        for (int i = 0; i < force; i++) {
            throwDice = throwDice + rand.nextInt(dice) + 1;
        }
        return throwDice;
    }

    @Override
    public String toString() {
        return " id : " + this.id + ", voisin :" + this.idNeighbours + ", Force : "+force +".\n";
    }
}
