package com.roecker;

import java.util.ArrayList;

public class Partie {
    private final int NBjoueurs;
    Partie(int joueurs){
        this.NBjoueurs = joueurs;
        Carte map  = new Carte(this.NBjoueurs);
        ArrayList<Joueur> challenger = new ArrayList<>();
        for(int i = 0;i < NBjoueurs; i ++){
            Joueur J = new Joueur(i);
            challenger.add(J);
        }
        System.out.println(map);
        System.out.println(challenger);
    }
}
