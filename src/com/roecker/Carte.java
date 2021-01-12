package com.roecker;

import java.util.ArrayList;

public class Carte {

    private Territoire[][] map;
    private int nbJoueurs;

    public Carte(int playerNum){
        this.map = new Territoire[playerNum][4];
        this.nbJoueurs = playerNum;
        int id = 0;
        for(int i = 0; i < playerNum; i ++){
            for(int j = 0; j < 4; j ++){
                Territoire test = new Territoire(id);
                if(test.getId()-4 >= 0){
                    test.addNeighbour(id-4);
                }
                if(test.getId() % 4 != 0){
                    test.addNeighbour(id-1);
                }
                if(test.getId() % 4 != 3){
                    test.addNeighbour(id+1);
                }
                if(test.getId()+4 < 4*playerNum){
                    test.addNeighbour(id+4);
                }
                id += 1;
                this.map[i][j] = test;
            }
        }
    }
    public Carte(ArrayList<Territoire> territoires, int playerNum) throws TerritoryCanceledException {
        this.map = new Territoire[playerNum][4];
        int id = 0;
        for(int i = 0; i < playerNum; i ++) {
            for (int j = 0; j < 4; j++) {
                boolean present = false;
                for(Territoire territoire: territoires){
                    if(territoire.getId() == id){
                        this.map[i][j] = territoire;
                        present = true;
                    }
                }
                if(!present){
                    throw new TerritoryCanceledException();
                }
                id += 1;
            }
        }
    }

    public Territoire[][] getMap() {
        return map;
    }

    public Territoire getTerritoire(int id){
        Territoire Terri;
        for (Territoire[] ligne : this.map) {
            for (Territoire territoire : ligne) {
                if(id == territoire.getId()){
                    return territoire;
                }
            }
        }
        return null; // n'arrive jamais
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder("\n");
        for (Territoire[] territoires : this.map) {
            for (Territoire territoire : territoires) {
                text.append(territoire.toString());
            }
            text.append("\n");
        }
        return text.toString();
    }
}
