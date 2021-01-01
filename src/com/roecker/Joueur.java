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

    public Territoire popTerritoire(int idpop){
        for(Territoire territoire : ListeTerritoire){
            if(territoire.getId() == idpop){
                ListeTerritoire.remove(territoire);
                return territoire;
            }
        }
       return null;
    }

    public int attaquerTerritoire(int Tattaque) throws Exception {
        int lancer = 0;
        for (Territoire territoire : ListeTerritoire) {
            if (Tattaque == territoire.id){
                if(territoire.getForce() == 1){
                    throw new Exception();// force = 1
                }
                lancer = territoire.lancerDes();
                System.out.println(lancer);
                return lancer;
            }
        }
        throw new Exception();// non propriétaire
    }

    public int defendreTerritoire(int Tdefense) throws Exception{
        int lancer = 0;
        for (Territoire territoire : ListeTerritoire) {
            if (Tdefense == territoire.id){
                lancer = territoire.lancerDes();
                System.out.println(lancer);
                return lancer;
            }
        }
        throw new Exception();// non propriétaire
    }

    public boolean verifTerritoire(int Tattaque, int Tdefense){
        for (Territoire territoire : ListeTerritoire) {
            if(Tattaque == territoire.id) {
                if(territoire.force != 1){
                    for (int voisin : territoire.idVoisins) {
                        if(Tdefense == voisin) {
                            for(Territoire territoirevoisin : ListeTerritoire){
                                if(territoirevoisin.id == Tdefense){
                                    return false;
                                    //exceptions le territoire de defense appartient au joueur qui attaque Done
                                }
                            }
                            return true;
                        }
                    }
                    return false;
                    // exceptions le territoire de defense n'est pas un voisin Done
                }else{
                    return false;
                    // exceptions force 1 Done
                }

            }
        }
        return false;
        // exceptions le territoire d'attaque n'appartient pas au joueurs Done
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
