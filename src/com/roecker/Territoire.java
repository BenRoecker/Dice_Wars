package com.roecker;
import java.util.ArrayList;
import java.util.UUID;

public class Territoire {
    final String id;
    String idJoueur;
    int force;
    ArrayList<String> idVoisins;

    public Territoire() {
        this.id = UUID.randomUUID().toString();
    }
}
