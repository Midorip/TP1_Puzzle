/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import modele.Environnement;

/**
 *
 * @author Epulapp
 */
public class Position {

    public int x;
    public int y;

    public Position(int i, int j) {
        this.x = i;
        this.y = j;
    }
    
    public Position()
    {
        this.x = 0;
        this.y = 0;
    }

    public boolean isEquals(Position posCompare) {
        if (this.x == posCompare.x && this.y == posCompare.y) {
            return true;
        } else {
            return false;
        }
    }

    public Position getDiff(Position posFinal) {
        Position tmp = new Position();
        
        tmp.x = (posFinal.x - this.x);
        tmp.y = (posFinal.y - this.y);
        return tmp;
    }

}
