/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;
import java.util.HashMap;
import modele.Agent;

/**
 *
 * @author Epulapp
 */
public class  BoiteMessage {
    
    private static HashMap<Integer,Message> liste;
    
    public BoiteMessage()
    {
        liste = new HashMap<Integer,Message>();
    }
    
    public synchronized Message consulte(Agent a)
    {
        if(liste.containsKey(a.getIdAgent()))
        {
            Message msg = liste.get(a.getIdAgent());
            liste.remove(a.getIdAgent());
            return msg;  
        }
        else
        {
            return null;
        }
    }
    
    public synchronized void envois( int id_dest, Message msg)
    {
        liste.put(id_dest,msg);
    }
    
    
    
    
}
