package com.roecker;
import java.util.ArrayList;

public class Territoire {
    final int id;
    int idJoueur;
    int force;
    ArrayList<Integer> idVoisins;

    public Territoire(int id) {
        this.id = id;
        this.idVoisins = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "id : " + this.id + ", voisin :" + this.idVoisins;
    }

    public void addVoisin(int id){
        idVoisins.add(id);
    }
}
