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
 */
public class Agent extends Case implements Runnable {

    private boolean enVie = true;
    private boolean happy = false;
    //position terminal
    private Position posFinal;
    //Position terminal
    private int idAgent;

    private int nbIterationAgent;

    // Liste des messages reçus
    private ArrayList<Message> listeMessagesReceive;

    // Liste des messages envoyés
    private ArrayList<Message> messageSend;

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

    public void perception() {
        //env.getvoisin

        // se deplace fait tentative de recuperer 
        //OU
        //se deplace fait tentative de poser
    }

    void print() {
        if (this.isHappy()) {
            System.out.print("Y"+ + this.idAgent + "|");
        } else {
            System.out.print("N" + this.idAgent + "|");
        }
    }

    public void seDeplacer() {

        // probabilité 1/4
        int lower = 1;
        int higher = 5;
        int random = (int) (Math.random() * (higher - lower)) + lower;

        //Je dis a l'environnement que je bouge
        // env.move(this, random);
    }

    public boolean isHappy() {
        if (this.position.isEquals(posFinal)) {
            return true;
        } else {
            return false;
        }

    }

    public void run() {
        //Tant que le puzzle nest pas reconstitue {
        //Regarder si le but est atteint pour notre case //fin?
        int nb = nbIterationAgent;
        while (nb > 0) {
            if (!this.isHappy()) {
               
                System.out.println("===================================================");
                env.printEnv();
                System.out.println("===================================================");
                
                
                Position[] posListe = new Position[2];
                //Regarder si le but est atteint pour notre case //fin?
                if (position.isEquals(posFinal)) {
                    happy = true;
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
                    if (posListe[i] != null && this.env.testPosLibre(posListe[i])) {
                        move(posListe[i]);
                        haveMove = true;
                        break;
                    }
                }
                if (!haveMove) {
                    System.out.println("Move mother fuckaaa");
                    Agent dest = (Agent) env.env[posListe[0].x][posListe[0].y];
                    Message msg = new Message(this, dest, "Move", posListe[0]);
                    env.getBmsg().envois(dest.idAgent, msg);
                    System.out.println("Envois d'un msg...");
                }
            } else {
                // On consulte ses messages et les traiter ( soit on part dans tout les cas, soit on reflechie)
                Message msgRecu = this.env.getBmsg().consulte(this);
                if (msgRecu != null) {
                    System.out.println("Message reçu pour bouger, Flemme");
                    listeMessagesReceive.add(msgRecu);
                    if (msgRecu.action == "MOVE" && msgRecu.position.isEquals(this.position)) {
                        System.out.println("Message reçu pour bouger, Flemme");
                    }
                }
            }
            nb--;
           

        }
    }

    public void move(Position posVoulu) {
        Case dest, cTmp2;
        //posVoulu.x = (posVoulu.x + env.getSizex()) % env.getSizex();
        //posVoulu.y = (posVoulu.y + env.getSizey()) % env.getSizey();
        
        posVoulu.x = (posVoulu.x ) % env.getSizex();
        posVoulu.y = (posVoulu.y ) % env.getSizey();
        
        dest = env.env[posVoulu.x][posVoulu.y];
        env.env[posVoulu.x][posVoulu.y] = this;
        dest.position.x = this.position.x;
        dest.position.y = this.position.y;
          
        this.position.x = posVoulu.x;
        this.position.y = posVoulu.y;
        
        env.env[dest.position.x][dest.position.y] = dest;
        

    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

}
