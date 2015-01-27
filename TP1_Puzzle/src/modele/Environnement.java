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

    public Case[][] env;
    public static int sizex = 0, sizey = 0;
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

    private void startAgents() {
        Iterator<Agent> it = listAgentsEnv.iterator();
        while (it.hasNext()) {
            Agent agent = it.next();
            agent.run();
        }

    }

    public Environnement(int i0, int i1) {

        env = new Case[i0][i1];
        this.sizex = i0;
        this.sizey = i1;
        bmsg = new BoiteMessage();

        // Initialisation list agent
        listAgentsEnv = new ArrayList<Agent>();
        for (int i = 0; i < sizex; i++) {

            for (int j = 0; j < sizey; j++) {

                // probabilité 1/4
                int lower = 1;
                int higher = 5;
                int random = (int) (Math.random() * (higher - lower)) + lower;

                if (random == 4 && nbAgents < NB_AGENTS) {

                    switch (nbAgents) {
                        case 0:
                            env[i][j] = new Agent(i, j, this, new Position(1, 1),nbAgents);
                            break;
                        case 1:
                            env[i][j] = new Agent(i, j, this, new Position(2, 2),nbAgents);
                            break;
                        case 2:
                            env[i][j] = new Agent(i, j, this, new Position(3, 3),nbAgents);
                            break;
                        case 3:
                            env[i][j] = new Agent(i, j, this, new Position(4, 4),nbAgents);
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
        this.printEnv();
        this.startAgents();

    }

    public void printEnv() {
        for (int i = 0; i < env.length; i++) {
            for (int j = 0; j < env[i].length; j++) {
                env[i][j].print();
            }
            System.out.println("");
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
        return true;

    }
}
