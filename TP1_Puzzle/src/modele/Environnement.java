/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import utils.BoiteMessage;

/**
 *
 * @author Epulapp
 */
public class Environnement {

    private Case[][] env;
    private int sizex = 0, sizey = 0;
    private final int NB_AGENTS = 20;
    private final int NB_OBJETS = 200;
    private int nbAgents = 0;
    private int nbObjets = 0;

    public BoiteMessage getBmsg() {
        return bmsg;
    }
    
    private BoiteMessage bmsg ;
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
                System.out.println("Random" + random);
                if (random == 4 && nbAgents < NB_AGENTS) {
                    env[i][j] = new Agent(i, j, this);
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
     //   int j = c.indexJ();

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

    @Override
    public String toString() {
        String environnement = "";
        for (int i = 0; i < env.length; i++) {
            for (int j = 0; j < env[i].length; j++) {
                environnement += env[i][j];
            }
            System.out.println("");
        }
        return environnement;

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
    

}
