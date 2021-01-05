package com.roecker;

public class Carte {
    Territoire[][] map;
    int nbJoueurs;

    public Carte(int nombreJoueur){
        this.map = new Territoire[nombreJoueur][4];
        this.nbJoueurs = nombreJoueur;
        int id = 0;
        for(int i = 0; i < nombreJoueur; i ++){
            for(int j = 0; j < 4; j ++){
                Territoire test = new Territoire(id);
                if(test.id-4 >= 0){
                    test.addVoisin(id-4);
                }
                if(test.id % 4 != 0){
                    test.addVoisin(id-1);
                }
                if(test.id % 4 != 3){
                    test.addVoisin(id+1);
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

    public Territoire getTerritoire(int id){
        Territoire Terri;
        for (Territoire[] ligne : this.map) {
            for (Territoire territoire : ligne) {
                if(id == territoire.id){
                    return territoire;
                }
            }
        }
        return null;
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
