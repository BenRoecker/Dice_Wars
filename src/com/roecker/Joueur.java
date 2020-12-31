package com.roecker;
import java.util.ArrayList;


public class Joueur {
    final int id;
    ArrayList<Territoire> ListeTerritoire;
    public Joueur(int id) {
        this.id = id;
        this.ListeTerritoire = new ArrayList<>();
    }
    public int getId(){
        return this.id;
    }
    public ArrayList<Territoire> getListeTerritoire(){
        return this.ListeTerritoire;
    }
    public void addTerritoire(Territoire add){
        ListeTerritoire.add(add);
    }

    public int attaquerTerritoire(int Tattaque){
        int lancer = 0;
        for (int i = 0; i < ListeTerritoire.toArray().length; i++) {
            if (Tattaque == ListeTerritoire.get(i).id){
                lancer = ListeTerritoire.get(i).lancerDes();
                System.out.println(lancer);
                return lancer;
            }
        }
        return lancer;
    }

    public boolean verifTerritoire(int Tattaque, int Tdefense){
        for (int i = 0; i < ListeTerritoire.toArray().length; i++) {
            if(Tattaque == ListeTerritoire.get(i).id) {
                for (int j = 0; j < ListeTerritoire.get(i).idVoisins.toArray().length; j++) {
                    if(Tdefense == ListeTerritoire.get(i).idVoisins.get(j)) {
                        for (int k = 0; k < ListeTerritoire.toArray().length; k++) {
                            if (Tdefense == ListeTerritoire.get(k).id) {
                                return false;
                            }
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder rendu = new StringBuilder("Joueur n°"+this.getId()+", territoire : ");
        for(Territoire i : ListeTerritoire){
            rendu.append(i.toString());
        }
        return String.valueOf(rendu);
    }
}
