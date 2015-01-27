package modele;

import utils.Message;
import java.util.ArrayList;
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

    //position actuel
    private Position position;

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

    //position terminal
    private int idAgent;

    // list messages envoyés
    //Vector vect;
    private ArrayList<Agent> listAgent;

    Agent(int i, int j, Environnement env, Position posFinal) {
        super();
        this.posFinal = posFinal;
    }

    public void perception() {
        //env.getvoisin

        // se deplace fait tentative de recuperer 
        //OU
        //se deplace fait tentative de poser
    }

    void print() {
        System.out.print("A|");
    }

    public void seDeplacer() {

        // probabilité 1/4
        int lower = 1;
        int higher = 5;
        int random = (int) (Math.random() * (higher - lower)) + lower;
        System.out.println("Random 2 " + random);
        //Je dis a l'environnement que je bouge
        env.move(this, random);

    }

    public void run() {
        //Tant que le puzzle nest pas reconstitue {
        //Regarder si le but est atteint pour notre case //fin?

        while (enVie) {

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
                    posListe[1] = new Position(position.x, position.y  + 1);
                } else {
                    posListe[1] = new Position(position.x, position.y -1);
                }
            }
            
            for(int i=0;i<2;i++)
            {
                if(posListe[i] != null )//&& this.env.testPos(posListe[i]))
                {
                    move(posListe[i]);
                }
            }
        }

        // On consulte ses messages et les traiter ( soit on part dans tout les cas, soit on reflechie)
        Message msgRecu = this.env.getBmsg().consulte(this);
        listeMessagesReceive.add(msgRecu);
        if (msgRecu.action == "MOVE" && msgRecu.position.isEquals(this.position)) {
            // MoveRandom permet à l'agent de se déplacer afin de ne pas géner un autre agent, sans pour autant connaitre 
            //moveRandom();
        }
    }
    
    public void move(Position pos)
    {
        Case cTmp;
        cTmp = env.env[pos.x][pos.y];
        env.env[pos.x][pos.y] = this;
        cTmp.pos.x = this.position.x ;
        cTmp.pos.y = this.position.y ;
        this.position.x = pos.x;
        this.position.y = pos.y;
        
    }

}

