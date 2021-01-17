package com.roecker;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        System.out.println("Voulez vous créer la partie à l'aide d'un fichier CSV ?");
        Scanner saisiePartie = new Scanner(System.in);
        Partie jeu;
        String rendu;
        do{
            System.out.println("Y / N ?");
            rendu  = saisiePartie.next();
        }while(! (rendu.equals("Y") || rendu.equals("N")));
        if(rendu.equals("N")){
            System.out.println("\u001B[38m" + "Veuillez saisir le nombre de joueurs :");
            int ent = 0;
            Scanner saisieJoueurs = new Scanner(System.in);
            do{
                System.out.println("Un nombre compris entre 1 et 10");
                while(!saisieJoueurs.hasNextInt()){
                    System.out.println("ce n'est pas un nombre");
                    saisieJoueurs.next();
                }
                ent = saisieJoueurs.nextInt();
            }while(ent <= 1 || ent >= 11);
            jeu = new Partie(ent);
        }else{
            try{
                Path path = Paths.get("Classeur1.csv");
                jeu = new Partie(String.valueOf(path));
            } catch (TerritoryCanceledException e) {
                System.out.println(e.getMessage() + "tu ne peux pas utiliser ce classeur");
                jeu = new Partie(2);
            }
        }
        boolean endOfGame = false;
        boolean test = true;
        do{
            Random rand = new Random();
            int randomId = rand.nextInt(jeu.getPlayersNum());
            Joueur now = jeu.getPlayers().get(randomId);
            System.out.println("C'est au tour du joueur n°"+now.getId());
            do{
                try{
                    test = jeu.battle(now);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
                if(!test){
                    now.reinforcement();
                }
                System.out.println(jeu);
            }while(test);
            System.out.println("veut tu sauvegarder ta partie ?");
            Scanner saisieBackup = new Scanner(System.in);
            String backupApproval = "";
            do{
                System.out.println("Y / N ?");
                backupApproval = saisieBackup.next();
            }while(! (backupApproval.equals("Y") || backupApproval.equals("N")));
            if(backupApproval.equals("Y")){
                System.out.println("Sauvegarde en cours...");
                jeu.backup("Classeur1.csv");
                System.out.println("Sauvegarde effectuée");
            }
            if(jeu.victory(now)){
                System.out.println("Le joueur n°"+now.getId()+" a gagné !");
                endOfGame = true;
            }
            for(Joueur testLooser : jeu.getPlayers()){
                if(jeu.defeated(testLooser)){
                    System.out.println("Le joueur n°"+testLooser.getId()+" a perdu !");
                    jeu.removePlayer(testLooser);
                }
            }
        }while(!endOfGame);
    }
}
