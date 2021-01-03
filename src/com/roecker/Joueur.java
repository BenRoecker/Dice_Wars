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

    public int attaquerTerritoire(int Tattaque) throws AttackWithOneDiceException, NotPocessedTerritoryException {
        int lancer = 0;
        for (Territoire territoire : ListeTerritoire) {
            if (Tattaque == territoire.id){
                if(territoire.getForce() == 1){
                    throw new AttackWithOneDiceException("Tu ne peux pas attaquer avec ce territoire il n'a qu'un dé");// force = 1
                }
                lancer = territoire.lancerDes();
                System.out.println(lancer);
                return lancer;
            }
        }
        throw new NotPocessedTerritoryException("Tu ne peux pas attaquer avec un territoire que tu ne possèdes pas");// non propriétaire
    }

    public int defendreTerritoire(int Tdefense) throws NotPocessedTerritoryException{
        int lancer = 0;
        for (Territoire territoire : ListeTerritoire) {
            if (Tdefense == territoire.id){
                lancer = territoire.lancerDes();
                System.out.println(lancer);
                return lancer;
            }
        }
        throw new NotPocessedTerritoryException("Tu ne peux pas défendre avec un territoire que tu ne possèdes pas");
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
