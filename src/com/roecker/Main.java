package com.roecker;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner saisieJoueurs = new Scanner(System.in);
        System.out.println("Veuillez saisir le nombre de joueurs :");
        int ent;
        do{
            System.out.println("Un nombre compris entre 1 et 10");
            ent = saisieJoueurs.nextInt();
        }while(ent <= 1 || ent >= 11);
	    Partie jeu = new Partie(ent);
    }
}
