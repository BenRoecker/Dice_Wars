package com.roecker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Partie {
    private final int NBjoueurs;

    //Inisialisation
    Partie(int joueurs){
        this.NBjoueurs = joueurs;
        Carte map  = new Carte(this.NBjoueurs);
        ArrayList<Territoire> territoires = new ArrayList<>();
        Territoire[][] tableau = map.getMap();
        for (Territoire[] value : tableau) {
            territoires.addAll(Arrays.asList(value));
        }
        Random rand = new Random();
        ArrayList<Joueur> challenger = new ArrayList<>();
        for(int i = 0;i < NBjoueurs; i ++){
            Joueur J = new Joueur(i);
            for( int j = 0; j < 4; j ++){
                int randomIndex = rand.nextInt(territoires.size());
                Territoire randomTerritoire = territoires.get(randomIndex);
                territoires.remove(randomIndex);
                J.addTerritoire(randomTerritoire);
            }
            challenger.add(J);
        }
        System.out.println(map);
        System.out.println(challenger);

    }
}
