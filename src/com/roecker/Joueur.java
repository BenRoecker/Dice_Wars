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
        for (Territoire territoire : ListeTerritoire) {
            if (Tattaque == territoire.id){
                if(territoire.force == 1){
                    //exception
                }else{
                    lancer = territoire.lancerDes();
                    System.out.println(lancer);
                    return lancer;
                }
            }
        }
        return lancer;
    }

    public boolean verifTerritoire(int Tattaque, int Tdefense){
        for (Territoire territoire : ListeTerritoire) {
            if(Tattaque == territoire.id) {
                for (int voisin : territoire.idVoisins) {
                    if(Tdefense == voisin) {
                        for(Territoire territoirevoisin : ListeTerritoire){
                            if(territoirevoisin.id == Tdefense){
                                return false;
                                //exceptions
                            }
                        }
                        return true;
                    }
                }
                return false;
                // exceptions
            }
        }
        return false;
        // exceptions
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
