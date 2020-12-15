package com.roecker;
import java.util.ArrayList;
import java.util.UUID;

public class Joueur {
    final int id;
    ArrayList<Territoire> ListeTerritoire;


    public Joueur(int id) {
        this.id = id;
    }

    public String getId(){
        return "L'ID du joueur est"+this.id;
    }
}
