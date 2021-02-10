package com.roecker;

public class AttackWithOneDiceException extends Exception{
    public AttackWithOneDiceException(){
        super("\u001B[31m"+"Tu ne peux pas attaquer avec un territoire qui contient un seul dés."+"\u001B[0m");
    }
}
