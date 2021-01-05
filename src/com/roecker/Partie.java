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
        this.map = map;
        this.joueurs = challenger;
        System.out.println(challenger);
    }

    public int[] demandeattack(){
        Scanner saisieIDs = new Scanner(System.in);
        System.out.println("\u001B[0m" + "Veuillez saisir le teritoire qui attaque suivi du territoire à attaquer : ('fin' pour arreter)");
        int[] rendu = new int[2];

        while(!saisieIDs.hasNext("fin")){
            Scanner Linesec = new Scanner(saisieIDs.nextLine());
            if(Linesec.hasNextInt()){
                int result1 = Linesec.nextInt();
                if(Linesec.hasNextInt() && result1 >= 0 && result1 < 4*NBjoueurs){
                    int result2 = Linesec.nextInt();
                    if(result2 >= 0 && result2 < 4 * NBjoueurs){
                        rendu[0] = result1;
                        rendu[1] = result2;
                        return rendu;
                    }else{
                        System.out.println("Ces territoires n'existe pas.");
                    }
                }
                else{
                    System.out.println("Le deuxième n'est pas un nombre !");
                }
            }else{
                System.out.println("Le premier n'est pas un nombre !");
            }
        }
        return null;
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

    public boolean combat(Joueur attaque) throws YouAttackYourselfException, NotNeighbourException {
        //initialisation combat
        int[] rendu = demandeattack();
        if(rendu != null){
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
            return true;
        }else{
            return  false;
        }
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
                if(possRenfort.size() != 0){
                    Random rand = new Random();
                    int randIndex = rand.nextInt(possRenfort.size());
                    possRenfort.get(randIndex).addForce(1);
                }
            }
        }
    }

    public Carte getMap(){
        return this.map;
    }

    public ArrayList<Joueur> getJoueurs(){
        return this.joueurs;
    }

    @Override
    public String toString() {
        StringBuilder rendu = new StringBuilder("Les joueurs sont :");
        rendu.append("\n");

        int i = 0;
        for(Joueur joueur : this.joueurs){
            rendu.append(joueur.toString());
            i ++;
            rendu.append("\n");
        }
        return rendu.toString();
    }

    public void suppJoueur(Joueur now){
        if(joueurs.get(0) == now){
            ArrayList<Joueur> newlist = new ArrayList<>();
            for(int i = 1; i < joueurs.size();i ++){
                newlist.add(joueurs.get(i));
            }
            joueurs = newlist;
        }else{
            joueurs.remove(now);
        }
    }
}
