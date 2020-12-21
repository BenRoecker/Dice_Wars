package com.roecker;
import java.util.ArrayList;

public class Territoire {
    final int id;
    int force;
    ArrayList<Integer> idVoisins;

    public Territoire(int id) {
        this.id = id;
        this.idVoisins = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "id : " + this.id + ", voisin :" + this.idVoisins + ", Force :"+force;
    }

    public void addVoisin(int id){
        idVoisins.add(id);
    }

    public void setForce(int force) {
        this.force = force;
    }

    public int getForce(){
        return this.force;
    }

    public void addForce(int de){
        this.force += de;
    }
}
