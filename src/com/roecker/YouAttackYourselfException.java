package com.roecker;

public class YouAttackYourselfException extends Exception{
    public YouAttackYourselfException(){
        super("\u001B[31m"+"Tu ne peux pas attaquer un territoire que tu possèdes."+"\u001B[0m");
    }
}
