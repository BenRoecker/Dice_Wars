package com.roecker;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Veuillez saisir le nombre de joueurs :");
        int ent = 0;
        boolean type = false;
        do{
            Scanner saisieJoueurs = new Scanner(System.in);
            System.out.println("Un nombre compris entre 1 et 10");
            try{
                ent = saisieJoueurs.nextInt();
            }catch(InputMismatchException e){
                type = true;
            }
        }while(ent <= 1 || ent >= 11 || type);
	    Partie jeu = new Partie(ent);
    }
}
