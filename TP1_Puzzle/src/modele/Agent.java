package modele;

import utils.Message;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.BoiteMessage;
import utils.Position;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Epulapp
 * 
 * Rapport : mettre en valeur ce qui fonctionne + capture d'écran
 */
public class Agent extends Case implements Runnable {

    private boolean enVie = true;
    private boolean happy = false;
    //position terminal
    private Position posFinal;

    //Position terminal
    private int idAgent;

    private int nbIterationAgent;

    //Utilité agent
    private int utilIndiv = 0;

    // Liste des messages reçus
    private ArrayList<Message> listeMessagesReceive;

    // Liste des messages envoyés
    private ArrayList<Message> messageSend;

    public int nbCoupsAgent = 0;

    public int getIdAgent() {
        return idAgent;
    }

    public void setIdAgent(int idAgent) {
        this.idAgent = idAgent;
    }

    // list messages envoyés
    //Vector vect;
    private ArrayList<Agent> listAgent;

    Agent(int i, int j, Environnement env, Position posFinal, int idAgent, int nbIteration) {
        super(i, j, env);
        this.posFinal = posFinal;
        this.idAgent = idAgent;
        this.nbIterationAgent = nbIteration;
        listeMessagesReceive = new ArrayList<Message>();
    }

    void print() {
        if (this.isHappy()) {
            System.out.print("Y" + +this.idAgent + "|");
        } else {
            System.out.print("N" + this.idAgent + "|");
        }
    }

    public boolean isHappy() {
        if (this.position.isEquals(posFinal)) {
            return true;
        } else {
            return false;
        }

    }

    public synchronized void run() {
        //Tant que le puzzle nest pas reconstitue {
        //Regarder si le but est atteint pour notre case //fin?
        int nb = nbIterationAgent;

        while (!env.testAllAgentHappy()) {

            while (!this.isHappy()) {
                
                utilIndiv = 0;

                System.out.println("agent" + this.getIdAgent() + " is " + this.isHappy());
                System.out.println("===================================================");
                env.printEnv();
                System.out.println("===================================================");

                Position[] posListe = new Position[2];
                //Regarder si le but est atteint pour notre case //fin?

                if (position.isEquals(posFinal)) {
                    happy = true;
                } else {
                    happy = false;
                }
                // Recherche de la solution finale
                Position posTemp = this.position.getDiff(posFinal);
                if (posTemp.x == 0) {
                    posListe[0] = null;
                } else {
                    if (posTemp.x > 0) {
                        posListe[0] = new Position(position.x + 1, position.y);
                    } else {
                        posListe[0] = new Position(position.x - 1, position.y);
                    }
                }
                if (posTemp.y == 0) {
                    posListe[1] = null;
                } else {
                    if (posTemp.y > 0) {
                        posListe[1] = new Position(position.x, position.y + 1);
                    } else {
                        posListe[1] = new Position(position.x, position.y - 1);
                    }
                }

                boolean haveMove = false;
                for (int i = 0; i < 2; i++) {

                    if (posListe[i] != null) {
                        if (this.env.testPosLibre(posListe[i]) && haveMove != true) {
                            move(posListe[i]);

                            haveMove = true;

                        }
                    }

                }

                if (position.isEquals(posFinal)) {
                    happy = true;
                } else {
                    happy = false;
                }

                System.out.println("id agent" + this.getIdAgent() + "Position courante : " + position.toString() + " position final " + posFinal.toString());
                if (!haveMove && !this.isHappy()) {

                    System.out.println("Move mother fuckaaa + " + this.idAgent + " n'a pas bougé" + " \n Position final ;" + this.posFinal.toString() + " \nPosition actuel : " + this.position);
                    System.out.println(" On affiche les positions bloquées :");
                    boolean pos0NN = false;
                    boolean pos1NN = false;

                    for (int i = 0; i < 2; i++) {
                        if (posListe[i] != null) {
                            System.out.println(" posListe[i] x : " + posListe[i].x + "posListe[i] y :" + posListe[i].y);
                            System.out.println(" Doit etre  :" + env.testPosLibre(posListe[i]));
                            if (i == 0) {
                                pos0NN = true;
                            }
                            if (i == 1) {
                                pos1NN = true;
                            }

                        }
                    }

                    if (pos0NN) {
                        System.out.println("Envois d'un msg... à x: " + posListe[0].x + " y: " + posListe[0].y);
                        try {

                            Agent dest = (Agent) env.env[posListe[0].x][posListe[0].y];

                            Message msg = new Message(this, dest, "MOVE", posListe[0]);
                            env.getBmsg().envois(dest.idAgent, msg);

                        } catch (Exception e) {

                            if (env.env[posListe[0].x][posListe[0].y] instanceof Agent) {
                                System.out.println(posListe[0].x + ";" + posListe[0].y + " Agent");
                            } else {
                                System.out.println(posListe[0].x + ";" + posListe[0].y + " Non Agent");
                            }
                            // e.printStackTrace();
                        }

                    } else if (pos1NN) {

                        try {
                            System.out.println("Envois d'un msg... à x: " + posListe[1].x + " y: " + posListe[1].y);

                            Agent dest = (Agent) env.env[posListe[1].x][posListe[1].y];
                            Message msg = new Message(this, dest, "MOVE", posListe[1]);
                            env.getBmsg().envois(dest.idAgent, msg);

                        } catch (Exception e) {

                            if (env.env[posListe[1].x][posListe[1].y] instanceof Agent) {
                                System.out.println(posListe[1].x + ";" + posListe[1].y + " Agent");
                            } else {
                                System.out.println(posListe[1].x + ";" + posListe[1].y + " Non Agent");
                            }
                            //  e.printStackTrace();
                        }
                    }

                }

                //Si ils s'entrebloquent ils doivent tout de meme consulter leur msg:
                consultAndMove();
            }

            if (nbCoupsAgent != 0) utilIndiv = env.getNbCoupAgentsTotaux() / nbCoupsAgent;
            
            while (this.isHappy() && !env.testAllAgentHappy()) {// Sinon il meurt et ne recoit jamais de message 

                consultAndMove();

            }
            
        }

        utilIndiv += 100;
        
        System.out.println(" Agent " + this.getIdAgent() + " est mort avec utilité : " + utilIndiv + " avec un nb coup de : " + this.nbCoupsAgent + " nbcoup tot ; "+ env.getNbCoupAgentsTotaux());
    }

    public void consultAndMove() {

        // On consulte ses messages et les traiter ( soit on part dans tout les cas, soit on reflechie)
        Message msgRecu = this.env.getBmsg().consulte(this);
        if (msgRecu != null) {
            System.out.println(this.getIdAgent() + " a recu un Message pour bouger;;;");
            listeMessagesReceive.add(msgRecu);
            if (msgRecu.action == "MOVE" && msgRecu.position.isEquals(this.position)) {
                System.out.println("Message reçu pour bouger, OK je bouge");
                System.out.println("Position courante : " + position.toString());
                int x = this.position.x;
                int y = this.position.y;
                Position pHaut = null, pBas = null, pGauche = null, pDroite = null;

                if ((x - 1) > 0) {
                    pHaut = new Position(x - 1, y);
                }
                if ((y + 1) < env.getSizey()) {
                    pDroite = new Position(x, y + 1);
                }
                if ((y - 1) > 0) {
                    pGauche = new Position(x, y - 1);
                }
                if ((x + 1) < env.getSizex()) {
                    pBas = new Position(x + 1, y);
                }

                if (pHaut != null && env.testPosLibre(pHaut)) {
                    move(pHaut);
                } else if (pBas != null && env.testPosLibre(pBas)) {
                    move(pBas);
                } else if (pGauche != null && env.testPosLibre(pGauche)) {
                    move(pGauche);
                } else if (pDroite != null && env.testPosLibre(pDroite)) {
                    move(pDroite);
                }

                if (position.isEquals(posFinal)) {
                    happy = true;
                } else {
                    happy = false;
                }
                System.out.println("Apres move Position courante : " + position.toString() + " h " + this.happy);
                // maximiser la somme de lutilité de tout le monde
                // chaque agent voit tout le monde
                //
            }
        }
    }

    public void move(Position posVoulu) {
        Case dest;
        //posVoulu.x = (posVoulu.x + env.getSizex()) % env.getSizex();
        //posVoulu.y = (posVoulu.y + env.getSizey()) % env.getSizey();

        posVoulu.x = (posVoulu.x) % env.getSizex();
        posVoulu.y = (posVoulu.y) % env.getSizey();

        dest = env.env[posVoulu.x][posVoulu.y];

        env.env[posVoulu.x][posVoulu.y] = this;

        dest.position.x = this.position.x;
        dest.position.y = this.position.y;

        this.position.x = posVoulu.x;
        this.position.y = posVoulu.y;

        env.env[dest.position.x][dest.position.y] = dest;

        nbCoupsAgent++;
        this.env.incrementCt();

    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

}
