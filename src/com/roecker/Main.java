package com.roecker;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
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
	    Partie jeu = new Partie(ent);
	    boolean fin = false;
	    do{
            for(Joueur joueur : jeu.getJoueurs()){
                System.out.println("\u001B[38m" + "C'est au tours du joueur n°"+joueur.id);
                boolean test = false;
                do{
                    if(jeu.victoire(joueur)){
                        fin = true;
                        System.out.println("\u001B[38m" + "Le joueur "+joueur.getId()+" a gagné !");
                        break;
                    }
                    if(jeu.defaite(joueur)){
                        jeu.suppJoueur(joueur);
                        break;
                    }
                    try{
                        test = jeu.combat(joueur);
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                        test = true;
                    }
                    if(jeu.victoire(joueur)){
                        fin = true;
                        System.out.println("\u001B[38m" + "Le joueur "+joueur.getId()+" a gagné !");
                        break;
                    }else {
                        System.out.println(jeu);
                    }
                }while(test);
                if(fin){
                    break;
                }
            }
            jeu.renfort(jeu.getJoueurs());
            System.out.println(jeu);
        }while(!fin);
    }
}
