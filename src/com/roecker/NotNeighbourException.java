package com.roecker;

public class NotNeighbourException extends Exception{
    public NotNeighbourException(){
        super("\u001B[31m"+"Les deux territoires données ne sont pas voisins."+"\u001B[0m");
    }
}
