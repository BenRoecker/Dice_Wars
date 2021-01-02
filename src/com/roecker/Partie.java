package com.roecker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Partie {
    private final int NBjoueurs;
    private Carte map;
    private ArrayList<Joueur> joueurs;

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
        this.map = map;
        this.joueurs = challenger;
        System.out.println(challenger);
        Joueur joueur = this.joueurs.get(0);
        try{
            combat(joueur);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println(challenger);
        renfort(this.joueurs);
        System.out.println(challenger);
    }

    public int[] demandeattack(){
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

    public Joueur findjoueurs(int id){
        for(Joueur joueur: joueurs){
            for(Territoire territoire : joueur.ListeTerritoire){
                if(id == territoire.id){
                    return joueur;
                }
            }
        }
        return null;
    }

    public boolean victoire(Joueur now){
        return now.getListeTerritoire().size() == NBjoueurs * 4;
    }
    
    public boolean defaite(Joueur now){
        return now.getListeTerritoire().size() == 0;
    }

    public void combat(Joueur attaque) throws YouAttackYourselfException, NotNeighbourException {
        //initialisation combat
        int[] rendu = demandeattack();
        int Idattaque = rendu[0];
        int Iddefense = rendu[1];
        //ajout try et catch pour les exceptions
            int valattaque = 0;
            try{
                valattaque = attaque.attaquerTerritoire(Idattaque);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
            Joueur defense = findjoueurs(Iddefense);
            if(defense == attaque){
                throw new YouAttackYourselfException("Tu ne peux pas attaquer un territoire que tu possède."); // le joueur qui attaque est le joueur qui défend
            }
            int valdefense = 0;
            try{
                valdefense = defense.defendreTerritoire(Iddefense);
            }catch(Exception e){
                System.out.println("tu peux pas te défendre");
            }

            Territoire quiattaque = map.getTerritoire(Idattaque);
            Territoire quidefend = map.getTerritoire(Iddefense);
            boolean presence = false;
            for(int voisin : quiattaque.idVoisins){
                if (voisin == quidefend.id) {
                    presence = true;// le territoire attaqué n'est pas un voisin
                    break;
                }
            }
            if(!presence){
                throw new NotNeighbourException("le territoire que tu attaques n'est pas voisins");
            }
            if(valattaque-valdefense > 0){
                attaque.addTerritoire(defense.popTerritoire(Iddefense));
                quidefend.setForce(quiattaque.getForce()-1);
            }
            quiattaque.setForce(1);
    }

    public void renfort(ArrayList<Joueur> joueurs){
        for(Joueur joueur : joueurs){
            int nbTerritoire = joueur.ListeTerritoire.size();
            for(int i = 0; i < nbTerritoire; i++){
                ArrayList<Territoire> possRenfort = new ArrayList<>();
                for(Territoire territoire : joueur.ListeTerritoire){
                    if(territoire.force < 8){
                        possRenfort.add(territoire);
                    }
                }
                Random rand = new Random();
                int randIndex = rand.nextInt(possRenfort.size());
                possRenfort.get(randIndex).addForce(1);
            }
        }
    }
}
