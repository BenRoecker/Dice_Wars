package com.roecker;

public class AttackWithOneDiceException extends Exception{
    public AttackWithOneDiceException(){
    }
    public AttackWithOneDiceException(String message)
    {
        super(message);
    }
}
