package com.roecker;

import java.util.ArrayList;
import java.util.Random;

public class Carte {
    Territoire[][] map;

    public Carte(int nombreJoueur){
        this.map = new Territoire[nombreJoueur][4];
        Random rand = new Random();
        int id = 0;
        for(int i = 0; i < nombreJoueur; i ++){
            for(int j = 0; j < 4; j ++){
                Territoire test = new Territoire(id);
                if(test.id-4 >= 0){
                    test.addVoisin(id-4);
                }
                if(test.id+4 < 4*nombreJoueur){
                    test.addVoisin(id+4);
                }
                id += 1;
                map[i][j] = test;
            }
        }
    }

    public Territoire[][] getMap() {
        return map;
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        for(int i = 0; i < 4; i ++){
            for(int j = 0; j < map[i].length; j++){
                text.append(map[i][j].toString());
            }
        }
        return text.toString();
    }
}
