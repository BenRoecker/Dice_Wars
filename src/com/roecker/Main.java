package com.roecker;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner saisieJoueurs = new Scanner(System.in);
        System.out.println("Veuillez saisir le nombre de joueurs :");
        int ent = saisieJoueurs.nextInt();
	    Partie jeu = new Partie(ent);
    }
}
