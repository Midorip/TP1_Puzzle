/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1_puzzle;

import static java.lang.Thread.sleep;
import modele.Environnement;
import utils.BoiteMessage;

/**
 *
 * @author Epulapp
 */
public class TP1_Puzzle {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        int nbIterations = 200;
        int nbAgents = 20;
        int size = 50;
        Environnement env = new Environnement(size,size,nbAgents,nbIterations);
        System.out.println("");
        if(env.finish())
        {
           env.printEnv(); 
        }

    }
    
}
