/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.BoiteMessage;
import utils.Position;

/**
 *
 * @author Epulapp
 */
public class Environnement {

    public Case[][] env;
    public static int sizex = 0, sizey = 0;
    private int NB_AGENTS_MAX;

    private int nbIterationAgent;

    private int nbAgents = 0;

    private ArrayList<Thread> threadListe;

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
            Thread t = new Thread(agent);
            t.start();
            threadListe.add(t);

        }

    }

    public Environnement(int i0, int i1, int nbAgentMax, int nbIterations) {

        env = new Case[i0][i1];
        this.sizex = i0;
        this.sizey = i1;
        NB_AGENTS_MAX = nbAgentMax;
        bmsg = new BoiteMessage();
        nbIterationAgent = nbIterations;
        threadListe = new ArrayList<Thread>();

        // Initialisation list agent
        listAgentsEnv = new ArrayList<Agent>();
        ArrayList<Position> listePosBut = new ArrayList<Position>();
        for (int i = 0; i < sizex; i++) {

            for (int j = 0; j < sizey; j++) {

                // probabilité 1/4
                int lower = 1;
                int higher = 5;
                int random = (int) (Math.random() * (higher - lower)) + lower;

                if (random == 4 && nbAgents < NB_AGENTS_MAX) {

                    int limite = sizex;
                    int random1 = 0;
                    int random2 = 0;

                    // Les agents ont un but totalement random
                    
                    Position posTmp = null;

                    // Test si la position voulu est déjà une pos voulu par un autre agent
                    boolean test = true;
                    boolean found = false;
                    while (test) {
                        found = false;
                        random1 = (int) (Math.random() * (limite));
                        random2 = (int) (Math.random() * (limite));
                        posTmp = new Position(random1, random2);
                        Iterator<Position> it = listePosBut.iterator();
                        while (it.hasNext()) {
                            
                            Position pos = it.next();
                            if (pos.isEquals(posTmp)) {
                                found = true;
                                System.out.println("Trouvé ! ");
                                break;
                            }
                        }
                        if(!found)
                            test=false;
                    }
                    listePosBut.add(posTmp);
                   // System.out.println(listePosBut.size());

                    env[i][j] = new Agent(i, j, this, new Position(random1, random2), nbAgents, nbIterationAgent);
                    System.out.println(random1 + "|" + random2);
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

    public synchronized void printEnv() {
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
    public synchronized boolean testPosLibre(Position pos) {
        Iterator<Agent> it = listAgentsEnv.iterator();

        while (it.hasNext()) {
            Agent agent = it.next();
          //  System.out.println("id Agent: " + agent.getIdAgent() + "  " + agent.position.toString());
         try {
               if (pos.isEquals(agent.getPosition())) {
                return false;
            }
         }catch (Exception e){
             System.out.println("Bug, pos: "+ pos + "agent pos :" + agent.getPosition());
             e.printStackTrace();
         }
          
        }
        return true;
    }
    
     // Test si position libre
    public synchronized boolean testAllAgentHappy() {
        Iterator<Agent> it = listAgentsEnv.iterator();

        while (it.hasNext()) {
            Agent agent = it.next();
         
           if(!agent.isHappy()) return false;
              
            
        }
        return true;
    }

    public boolean finishThread() {
        Iterator<Thread> it = threadListe.iterator();
        //System.out.println("On est en attente... ");

        while (it.hasNext()) {
            Thread t = it.next();
            //System.out.println(t.getId());
            try {
                while (t.isAlive()) {
                    sleep(500);

                }

            } catch (InterruptedException ex) {
                Logger.getLogger(Environnement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }

    public void printStat() {
        float resultat = 0;
        float tmpRes = 0;
        Iterator<Agent> it = listAgentsEnv.iterator();
        while (it.hasNext()) {
            Agent agent = it.next();
            if (agent.isHappy()) {
                resultat++;
            }
            System.out.println(agent.getIdAgent() + " is happy or not ? : " + agent.isHappy());
        }
        tmpRes = resultat / nbAgents * 100;
        System.out.println("Resultat OK : " + tmpRes + "% ");
    }
}
