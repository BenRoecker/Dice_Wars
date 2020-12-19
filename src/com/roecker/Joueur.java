package com.roecker;
import java.util.ArrayList;


public class Joueur {
    final int id;
    ArrayList<Territoire> ListeTerritoire;
    public Joueur(int id) {
        this.id = id;
    }
    public int getId(){
        return this.id;
    }

    public void addTerritoire(Territoire add){
        ListeTerritoire.add(add);
    }

    @Override
    public String toString() {
        return "Joueur n°"+this.getId();
    }
}
