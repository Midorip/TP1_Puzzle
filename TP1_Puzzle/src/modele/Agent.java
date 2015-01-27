package modele;

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

    private int i, j;
    //position terminal
    private int iF, jF;

    // list messages envoyés
    //Vector vect;
    private Agent[] listPiece;

    Agent(int i, int j, Environnement env) {
        this.env = env;
        this.i = i;
        this.j = j;
    }

    public void perception() {
        //env.getvoisin

        // se deplace fait tentative de recuperer 
        //OU
        //se deplace fait tentative de poser
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
        // On consulte ses messages et les traiter ( soit on part dans tout les cas, soit on reflechie)
        //

    }

}
