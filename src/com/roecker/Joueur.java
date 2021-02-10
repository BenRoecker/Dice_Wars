package com.roecker;
import java.util.ArrayList;
import java.util.Random;

public class Joueur {
    private final int id; // ajout private
    private ArrayList<Territoire> Territoires;

    public Joueur(int id) {
        this.id = id;
        this.Territoires = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public ArrayList<Territoire> getListeTerritoire() {
        return Territoires;
    }

    public void addTerritoire(Territoire add){
        Territoires.add(add);
    }

    public Territoire popTerritoire(Territoire pop ){
        Territoires.remove(pop);
        return pop;
    }

    public int attackTerritoire(Territoire attack) throws AttackWithOneDiceException {
        int diceThrows = 0;
        if(attack.getForce() == 1){
            throw new AttackWithOneDiceException();// force = 1
        }
        diceThrows = attack.diceThrower();
        return diceThrows;
    }

    public int defendTerritoire(Territoire defend){
        int diceThrows = 0;
        diceThrows = defend.diceThrower();
        return diceThrows;
    }

    public void reinforcement(){
        int nbTerritoire = getListeTerritoire().size();
        for(int i = 0; i < nbTerritoire; i++){
            ArrayList<Territoire> possRenfort = new ArrayList<>();
            for(Territoire territoire : getListeTerritoire()){
                if(territoire.getForce() < 8){
                    possRenfort.add(territoire);
                }
            }
            if(possRenfort.size() != 0){
                Random rand = new Random();
                int randIndex = rand.nextInt(possRenfort.size());
                possRenfort.get(randIndex).addForce(1);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder rendu = new StringBuilder("Joueur n°"+this.getId()+", territoires : \n");
        for(Territoire i : Territoires){
            rendu.append(i.toString());
        }
        return String.valueOf(rendu);
    }
}
