/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aisimplified;

import java.util.HashMap;

/**
 *
 * @author jacobregenstein
 */
public class Symbol extends Thing{
    private char value;
    private boolean explicit = true;//just for debugging purposes, turns on print statements
    
    //neither constructor should be used by anything other than the ThingNode's
    //makeThing method, otherwise the code runs into problems when looking for
    //node's children containing a given Symbol, because the .contains() method
    //only checks for an identical reference, not an identical object
    public Symbol(char v){
        this.value = v;
    }
    
    public Symbol(String s){
        this(s.charAt(0));
        //if this is not the case most likely some bit of code other than the
        //ThingNode has called the Symbol constructor
        assert(s.length()==1);
    }
    
    public String toString(){
        return "'" + value + "'";
    }
    
    public char getValue(){
        return value;
    }
    
    public boolean equals(Object o){
        if(o instanceof Symbol){
            Symbol s = (Symbol) o;
            return s.getValue()==value;
        }else{
            return false;
        }
    }
    
    public boolean isPattern(){
        return false;
    }
    
    public boolean isSymbol(){
        return true;
    }
}
