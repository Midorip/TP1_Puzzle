/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

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

    public boolean isEquals(Position posCompare) {
        if (this.x == posCompare.x && this.y == posCompare.y) {
            return true;
        } else {
            return false;
        }
    }

}
