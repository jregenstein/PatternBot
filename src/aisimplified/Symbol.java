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
    private boolean explicit = false;//just for debugging purposes, turns on print statements
    
    public Symbol(char v){
        this.value = v;
    }
    
    public Symbol(String s){
        this(s.charAt(0));
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
