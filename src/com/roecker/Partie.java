package com.roecker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

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
            int force = 0;
            Joueur J = new Joueur(i);
            for( int j = 0; j < 4; j ++){
                // ajouts des territoires
                int randomIndex = rand.nextInt(territoires.size());
                Territoire randomTerritoire = territoires.get(randomIndex);
                territoires.remove(randomIndex);
                J.addTerritoire(randomTerritoire);
                // force de chaques territoires
                int moyenne = 4;
                if(force == 0 & j != 3){
                    int randomForce = rand.nextInt(4)-2;
                    randomTerritoire.setForce(3+randomForce);
                    force = force - randomForce;
                }else{
                    randomTerritoire.setForce(3+force);
                    force = 0;
                }
            }
            challenger.add(J);
        }
        int lol = attackJoueur();
        System.out.println(lol);
        System.out.println(map);
        System.out.println(challenger);
    }

    public int attackJoueur(){
        Scanner saisieIDs = new Scanner(System.in);
        System.out.println("Veuillez saisir le teritoire qui attaque suivi du territoire à attaquer :");
        int ent, ent1;
        do{
            System.out.println("Un nombre compris entre 1 et "+NBjoueurs*4);
            ent = saisieIDs.nextInt();
            ent1 = saisieIDs.nextInt();
        }while(ent < 0 || ent >= NBjoueurs*4 || ent1 < 0 || ent1 >= NBjoueurs);
        return ent1;
    }
}
