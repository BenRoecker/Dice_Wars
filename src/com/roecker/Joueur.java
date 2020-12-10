package com.roecker;
import java.util.ArrayList;
import java.util.UUID;

public class Joueur {
    final String id;
    ArrayList<Territoire> ListeTerritoire;


    public Joueur() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId(){
        return "L'ID du joueur est"+this.id;
    }
}
