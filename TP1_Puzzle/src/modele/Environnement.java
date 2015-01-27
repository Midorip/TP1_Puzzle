/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.ArrayList;
import java.util.Iterator;
import utils.BoiteMessage;
import utils.Position;

/**
 *
 * @author Epulapp
 */
public class Environnement {

    private Case[][] env;
    private int sizex = 0, sizey = 0;
    private final int NB_AGENTS = 4;

    private int nbAgents = 0;

    // Liste des agents présents dans l'environnement
    public static ArrayList<Agent> listAgentsEnv;

    public BoiteMessage getBmsg() {
        return bmsg;
    }

    private BoiteMessage bmsg;
    // armoire contenant la liste des agents et une liste de message
    // on pose un message dans la liste de y pour y

    public Environnement(int i0, int i1) {

        env = new Case[i0][i1];
        this.sizex = i0;
        this.sizey = i1;
        bmsg = new BoiteMessage();

        for (int i = 0; i < sizex; i++) {

            for (int j = 0; j < sizey; j++) {

                // probabilité 1/4
                int lower = 1;
                int higher = 5;
                int random = (int) (Math.random() * (higher - lower)) + lower;

                if (random == 4 && nbAgents < NB_AGENTS) {

                    switch (nbAgents) {
                        case 0:
                            env[i][j] = new Agent(i, j, this, new Position(4, 4));
                            break;
                        case 1:
                            env[i][j] = new Agent(i, j, this, new Position(3, 3));
                            break;
                        case 2:
                            env[i][j] = new Agent(i, j, this, new Position(2, 2));
                            break;
                        case 3:
                            env[i][j] = new Agent(i, j, this, new Position(1, 1));
                            break;

                    }

                    // On ajoute l'agent à la liste d'agent
                    listAgentsEnv.add((Agent) env[i][j]);

                    nbAgents++;

                } else {
                    env[i][j] = new Case(i, j, this);

                }

            }

        }

    }

    /*
     * Retourne tableau contenant les voisins
     */
    public Case[] getVoisines(Case c) {
        Case[] cVoisines = new Case[8];
        // On récupère la position de la case dont on souhaite avoir les cases voisines
        // int i = c.indexI();
        // int j = c.indexJ();

        /*
         00 01 02
         10 11 12
         20 21 22
         */
        // haut i-1 j
        // bas  i+1 j
        // droite i j+1
        // gauche i j-1
        return null;
    }

    public void printEnv() {
        for (int i = 0; i < env.length; i++) {
            for (int j = 0; j < env[i].length; j++) {
                env[i][j].print();
            }
            System.out.println("");
        }
    }

    void move(Case caseE, int random) {
        /*
         if (random == 1) {
         //bas
         caseE.setI(caseE.getI());
         caseE.setJ(caseE.getJ());
         } else if (random == 2) {
         //haut
         caseE.setI(caseE.getI());
         caseE.setJ(caseE.getJ());
         } else if (random == 3) {
         //gauche
         caseE.setI(caseE.getI());
         caseE.setJ(caseE.getJ());
         } else if (random == 4) {
         //droite
         caseE.setI(caseE.getI());
         caseE.setJ(caseE.getJ());
         }*/
    }

    /*
     * Retourne l'index de la case si elle existe sinon retourne null
     */
    public Integer calcIndexI(int i) {

        if (i < 0 || i >= this.sizex) {
            //  System.out.println("null");
            return null;
        } else {
            return i;
        }

    }

    public Integer calcIndexJ(int j) {
        if (j < 0 || j >= this.sizey) {
            //  System.out.println("null");
            return null;
        } else {
            return j;
        }

    }

    public int getSizex() {
        return sizex;
    }

    public void setSizex(int sizex) {
        this.sizex = sizex;
    }

    public int getSizey() {
        return sizey;
    }

    public void setSizey(int sizey) {
        this.sizey = sizey;
    }

    // Test si position libre
    public boolean testPosLibre(Position pos) {
        Iterator<Agent> it = listAgentsEnv.iterator();
        while (it.hasNext()) {
            Agent agent = it.next();
            if (agent.getPosition().equals(pos)) {
                return false;
            }
        }
        return false;

    }
}
