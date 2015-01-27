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
    private ArrayList<Message> messagesReceive;

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
            //Regarder si le but est atteint pour notre case //fin?
            if (position.isEquals(posFinal)) {
                  happy = true;
            }
            

            // On consulte ses messages et les traiter ( soit on part dans tout les cas, soit on reflechie)
            Message msgRecu = this.env.getBmsg().consulte(this);
            messagesReceive.add(msgRecu);
            if (msgRecu.action == "MOVE" && msgRecu.position.isEquals(this.position)) {
            // MoveRandom permet à l'agent de se déplacer afin de ne pas géner un autre agent, sans pour autant connaitre 
                //moveRandom();
            }
        }

    }

}
