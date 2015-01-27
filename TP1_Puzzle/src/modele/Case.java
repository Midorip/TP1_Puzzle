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
    public Position pos;

    public Case() {

    }

    Case(int i, int j, Environnement env) {
        pos = new Position(i, j);
        this.env = env;

    }

    void print() {
       System.out.print("0|");
    }

}
