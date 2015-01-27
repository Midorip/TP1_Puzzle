/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import utils.Position;

/**
 *
 * @author Epulapp
 */
public class Case {
// case vide

    Environnement env;
    //position courante
    private Position pos;
   
    
    public Case() {

    }

    Case(int i, int j, Environnement env) {
        this.env = env;
        this.pos.x = i;
        this.pos.y = j;
    }


}
