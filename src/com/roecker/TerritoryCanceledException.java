package com.roecker;

public class TerritoryCanceledException extends Exception{
    public TerritoryCanceledException(){
        super("Le fichier n'estpas complet ");
    }
}
