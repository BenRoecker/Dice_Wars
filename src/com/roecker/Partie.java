package com.roecker;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.io.BufferedReader;

public class Partie {

    private int playersNum;
    private Carte map;
    private ArrayList<Joueur> players;

    Partie(int joueurs){
        this.playersNum = joueurs;
        Carte map  = new Carte(this.playersNum);
        ArrayList<Territoire> territoires = new ArrayList<>();
        Territoire[][] newmap = map.getMap();
        for (Territoire[] value : newmap) {
            territoires.addAll(Arrays.asList(value));
        }
        Random rand = new Random();
        ArrayList<Joueur> challenger = new ArrayList<>();
        for(int i = 0;i < playersNum; i ++){
            int force = 0;
            Joueur J = new Joueur(i);
            for( int j = 0; j < 4; j ++){
                // ajouts des territoires
                int randomIndex = rand.nextInt(territoires.size());
                Territoire randomTerritoire = territoires.get(randomIndex);
                territoires.remove(randomIndex);
                J.addTerritoire(randomTerritoire);
                // force de chaques territoires
                int moyenne = 4;
                if(force == 0 & j != 3){
                    int randomForce = rand.nextInt(4)-2;
                    randomTerritoire.setForce(3+randomForce);
                    force = force - randomForce;
                }else{
                    randomTerritoire.setForce(3+force);
                    force = 0;
                }
            }
            challenger.add(J);
        }
        this.map = map;
        this.players = challenger;
        System.out.println(challenger);
    }

    Partie(String CsvFileName) throws TerritoryCanceledException {
        BufferedReader row = null;
        String splitString = ";";
        ArrayList<Territoire> maps = new ArrayList<>();
        ArrayList<Joueur> challengers = new ArrayList<>();
        try{
            row = new BufferedReader(new FileReader(CsvFileName));
            String line;
            while ((line = row.readLine()) != null){
                String[] territoire = line.split(splitString);
                Territoire test = new Territoire(Integer.parseInt(territoire[0]));
                String[] neighbors = territoire[2].split(",");
                for(String adding : neighbors){
                    test.addNeighbour(Integer.parseInt(adding));
                }
                test.setForce(Integer.parseInt(territoire[1]));
                Joueur player = findjoueurs(Integer.parseInt(territoire[3]), challengers);
                if(player == null){
                    player = new Joueur(Integer.parseInt(territoire[3]));
                    player.addTerritoire(test);
                    challengers.add(player);
                }else{
                    player.addTerritoire(test);
                }
                maps.add(test);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (row != null){
                try{
                    row.close();
                    System.out.println(challengers);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        this.playersNum = challengers.size();
        try{
            this.map = new Carte(maps, this.playersNum);
        }catch(TerritoryCanceledException e){
            System.out.println(e.getMessage());
        }
        this.players = challengers;

    }

    public int[] demandeattack(){
        Scanner saisieIDs = new Scanner(System.in);
        System.out.println("\u001B[0m" + "Veuillez saisir le teritoire qui attaque suivi du territoire à attaquer : ('fin' pour arreter)");
        int[] rendu = new int[2];
        while(!saisieIDs.hasNext("fin")){
            Scanner Linesec = new Scanner(saisieIDs.nextLine());
            if(Linesec.hasNextInt()){
                int result1 = Linesec.nextInt();
                if(Linesec.hasNextInt() && result1 >= 0 && result1 < 4*playersNum){
                    int result2 = Linesec.nextInt();
                    if(result2 >= 0 && result2 < 4 * playersNum){
                        rendu[0] = result1;
                        rendu[1] = result2;
                        return rendu;
                    }else{
                        System.out.println("Ces territoires n'existe pas.");
                    }
                }
                else{
                    System.out.println("Le deuxième n'est pas un nombre !");
                }
            }else{
                System.out.println("Le premier n'est pas un nombre !");
            }
        }
        return null;
    }

    public Object findjoueurs(Territoire search){
        for(Joueur joueur: players){
            for(Territoire territoire : joueur.getListeTerritoire()){
                if(search.getId() == territoire.getId()){
                    return joueur;
                }
            }
        }
        return null;
    }

    public boolean victory(Joueur now){
        return now.getListeTerritoire().size() == playersNum * 4;
    }

    public boolean defeated(Joueur now){
        return now.getListeTerritoire().size() == 0;
    }

    public ArrayList<Joueur> getPlayers() {
        return players;
    }

    public int getPlayersNum() {
        return playersNum;
    }

    public boolean battle(Joueur attaque) throws Exception {
        int[] rendu = demandeattack();
        if(rendu != null){
            int Idattaque = rendu[0];
            int Iddefense = rendu[1];
            Territoire attack = map.getTerritoire(Idattaque);
            Territoire defend = map.getTerritoire(Iddefense);
            if(!attack.getIdNeighbours().contains(defend.getId())){
                throw new NotNeighbourException();
            }
            Joueur attacker = (Joueur) findjoueurs(attack);
            Joueur defender = (Joueur) findjoueurs(defend);
            if(attacker != attaque){
                throw new NotPocessedTerritoryException();
            }
            if(attacker == defender){
               throw new YouAttackYourselfException();
            }
            int valattack;
            try{
                valattack = attack.diceThrower();
            }catch(Exception e){
                throw new AttackWithOneDiceException();
            }
            int valdefend = defend.diceThrower();
            if(valattack-valdefend > 0){
                attacker.addTerritoire(defender.popTerritoire(defend));
                defend.setForce(attack.getForce()-1);
            }
            attack.setForce(1);
            return true;
        }else{
            return false;
        }
    }

    public void removePlayer(Joueur now){
        if(players.get(0) == now){
            ArrayList<Joueur> newlist = new ArrayList<>();
            for(int i = 1; i < players.size();i ++){
                newlist.add(players.get(i));
            }
            players = newlist;
        }else{
            players.remove(now);
        }
    }

    public Joueur findjoueurs(Integer search, ArrayList<Joueur> players) {
        for (Joueur joueur : players) {
            if (joueur.getId() == search) {
                return joueur;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder rendu = new StringBuilder("Les joueurs sont :");
        rendu.append("\n");

        int i = 0;
        for(Joueur joueur : this.players){
            rendu.append(joueur.toString());
            i ++;
            rendu.append("\n");
        }
        return rendu.toString();
    }
}
