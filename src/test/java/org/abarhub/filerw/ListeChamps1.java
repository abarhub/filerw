/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.abarhub.filerw;

import org.abarhub.filerw.Field;

/**
 *
 * @author abarhub
 */
public enum ListeChamps1 implements Field {
    Nom(0,20),Prenom(20,20),DateNaissance(40,8);
    
    private ListeChamps1(int position,int length)
    {
        this.position=position;
        this.length=length;
    }

    //@Override
    public int getLength() {
        return length;
    }

    //@Override
    public int getPosition() {
        return position;
    }
        
    private int position;
    private int length;
}
