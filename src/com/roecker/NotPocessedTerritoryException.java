package com.roecker;

public class NotPocessedTerritoryException extends Exception{
    public NotPocessedTerritoryException(){
        super("\u001B[31m"+"Tu ne peux pas attaquer avec un territoire que tu ne possèdes pas"+"\u001B[0m");
    }
}
