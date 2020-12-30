package com.roecker;
import java.util.ArrayList;
import java.util.Random;

public class Territoire {
    final int id;
    int force;
    ArrayList<Integer> idVoisins;

    public Territoire(int id) {
        this.id = id;
        this.idVoisins = new ArrayList<>();
    }

    public int lancerDes(){
        int des = 5;
        int lancer = 0;
        Random rand = new Random();
        for (int i = 0; i < force; i++) {
            lancer = lancer + rand.nextInt(des) + 1;
        }
        return lancer;
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
