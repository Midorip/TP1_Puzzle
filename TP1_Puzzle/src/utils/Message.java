/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import modele.Agent;

/**
 *
 * @author Epulapp
 */
public class Message {
    
    public Agent expediteur;
    public Agent destinataire;
    // Act langage : request (pas sur qu'il fasse
    // Action : move 
    // + poistion ou plusieurs que lon quitte
    public String act_langage;
    
    // Action = Move
    public String action;
    public Position position;
    
    public Message(Agent exp, Agent dest, String action, Position pos)
    {
        this.expediteur = exp;
        this.destinataire = dest;
        this.action = action;
        this.position = pos;
    }
    
}
