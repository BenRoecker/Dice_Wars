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
        System.out.println(map);
        System.out.println(challenger);
        Scanner rep = new Scanner(System.in);
        System.out.println("Tattaque : ");
        int Tattaque = rep.nextInt();
        System.out.println("Tdefense : ");
        int Tdefense = rep.nextInt();
        int atq = challenger.get(0).attaquerTerritoire(Tattaque);
        int def = challenger.get(1).attaquerTerritoire(Tdefense);
        System.out.println(def - atq);
    }

    public int[] attackJoueur(){
        Scanner saisieIDs = new Scanner(System.in);
        System.out.println("Veuillez saisir le teritoire qui attaque suivi du territoire à attaquer :");
        int[] rendu = new int[2];
        do{
            System.out.println("Un nombre compris entre 1 et "+NBjoueurs*4);
            rendu[0] = saisieIDs.nextInt();
            rendu[1] = saisieIDs.nextInt();
        }while(rendu[0] < 0 || rendu[0] >= NBjoueurs*4 || rendu[1] < 0 || rendu[1] >= NBjoueurs*4);
        return rendu;
    }

    public boolean victoire(Joueur now){
        return now.getListeTerritoire().size() == NBjoueurs * 4;
    }
    
    public boolean defaite(Joueur now){
        return now.getListeTerritoire().size() == 0;
    }


}
