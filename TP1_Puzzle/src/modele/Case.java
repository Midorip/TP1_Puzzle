/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author Epulapp
 */
public class Case {
// case vide

    Environnement env;
    //position courante
    private int i, j;
   
    
    public Case() {

    }

    Case(int i, int j, Environnement env) {
        this.env = env;
        this.i = i;
        this.j = j;
    }

   

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

}
