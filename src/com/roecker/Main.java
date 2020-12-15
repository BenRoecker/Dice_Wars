package com.roecker;

public class Main {

    public static void main(String[] args) {
	    Joueur J1 = new Joueur(1);
	    System.out.println(J1.getId());
	    Carte map = new Carte(5);
	    System.out.println(map.toString());
    }
}
