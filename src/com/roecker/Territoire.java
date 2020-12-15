package com.roecker;
import java.util.ArrayList;
import java.util.UUID;

public class Territoire {
    final int id;
    String idJoueur;
    int force;
    ArrayList<String> idVoisins;

    public Territoire(int id) {
        this.id = id;
    }
}
