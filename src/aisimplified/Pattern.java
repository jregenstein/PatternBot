/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aisimplified;

import java.util.LinkedList;

/**
 *
 * @author jacobregenstein
 */
public class Pattern extends Thing{
    private LinkedList<Thing> pattern;
    private boolean explicit = false;//just for debugging purposes, turns on print statements
    
    public int getLength(){
        return pattern.size();
    }
    
    public Pattern(String s){
        assert(s.length() > 1);
        pattern = new LinkedList<>();
        for(int i = 0; i<s.length(); i++){
            pattern.add(ThingNode.makeThing(s.charAt(i)));
        }
        if(explicit) System.out.println("the pattern before optimizing was: " + pattern);
        this.optimizePattern();
        if(explicit) System.out.println("the pattern after optimizing is: " + pattern);
        ThingNode.addPattern(this);
    }
    
    public String toString(){
        return pattern.toString();
    }
    
    public Pattern(LinkedList<Thing> p){
        pattern = p;
        this.optimizePattern();
        ThingNode.addPattern(this);
    }
    
    
    public LinkedList<Thing> getPattern(){
        return pattern;
    }
    
    public boolean equals(Object o){
        if(o instanceof Pattern){
            Pattern s = (Pattern) o;
            return s.getPattern()==pattern;
        }else{
            return false;
        }
    }
    
    public boolean isPattern(){
        return true;
    }
    
    public boolean isSymbol(){
        return false;
    }
    
    private void shortenList(){//looks for known patterns in the list and replaces the explicit pattern with a reference
        LinkedList<Thing> improvedList = new LinkedList<>();
        TreeNavigator nav = new TreeNavigator();
        while(!pattern.isEmpty()){
            if(explicit) System.out.println("the element at the beginning of the pattern list is " + pattern.getFirst());
            if(nav.hasChild(pattern.getFirst())){
                if(explicit) System.out.println("shortenlist started checking for a pattern");
                nav.goToChild(pattern.remove());
                if(nav.hasPattern()){
                    improvedList.add(nav.getPattern());
                    System.out.println("holy shit people there's a pattern it is: " + nav.getPattern().toString());
                    nav.reset();
                }
            }else{
                if(nav.isEmpty()){
                    improvedList.add(pattern.remove());
                }else{
                    improvedList.add(nav.removeThing());
                    pattern.addAll(0, nav.getThings());
                }
            }
        }
        pattern = improvedList;
    }
    
    private void optimizePattern(){
        int l;
        do{
            l = pattern.size();
            this.shortenList();
        }while(l>pattern.size());
    }
    
    
}
