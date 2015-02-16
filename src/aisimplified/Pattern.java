/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aisimplified;

import java.util.LinkedList;

/**
 *A pattern is an ordering of symbols and other patterns. 
 * @author jacobregenstein
 */
public class Pattern extends Thing{
    private LinkedList<Thing> pattern;
    final private boolean explicit = true;//just for debugging purposes, turns on print statements
    
    //returns the number of items contained in the pattern
    public int getLength(){
        return pattern.size();
    }
    
    //Constructs a pattern from a string
    public Pattern(String s){
        assert(s.length() > 1);
        pattern = new LinkedList<>();
        //First it turns the string into a list of symbol objects, so patterns
        //can be recognized.
        for(int i = 0; i<s.length(); i++){
            pattern.add(ThingNode.makeThing(s.charAt(i)));
        }
        //then it reads and rereads the list replacing patterns it finds
        //with references to the pattern (for instance, the pattern
        //[a,s,d,f,d,o,g,] would be replaced with 
        //[a,s,d,f,(reference to "dog" pattern)]. This is done in the optimize
        //method. The print statements are because I spent the longest time
        //on a bug hunt trying to figure out why the code wasn't recognizing 
        //patterns.
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
    
    //looks for known patterns in the list and replaces the explicit pattern 
    //with a reference to that pattern object
    private void shortenList(){
        //the list it will return
        LinkedList<Thing> improvedList = new LinkedList<>();
        //makes a tree navigator to look through the tree of patterns
        TreeNavigator nav = new TreeNavigator();
        //the loop that looks for patterns (print statements from the 
        //aformentioned bug hunt)
        while(!pattern.isEmpty()){
            if(explicit) System.out.println("the element at the beginning of the pattern list is " + pattern.getFirst());
            //If the navigator can make a pattern with the next Thing in the 
            //list, then we want to tell the navigator to add it to the potential
            //pattern it's looking at.
            if(nav.hasChild(pattern.getFirst())){
                if(explicit) System.out.println("shortenlist started checking for a pattern");
                nav.goToChild(pattern.remove());
                //if it is looking at a pattern, we put a reference to that pattern
                //in place of all the elements in the pattern, add that reference
                //to the improved pattern list, and keep going (this does create
                //the problem of only noticing the first of overlapping problems,
                //this is something that will have to be seen to later)
                if(nav.hasPattern()){
                    improvedList.add(nav.getPattern());
                    System.out.println("holy shit people there's a pattern it is: " + nav.getPattern().toString());
                    nav.reset();
                }
            //If the navigator can't make a pattern with what it has and the
            //next Thing we're looking at, then we either:
            }else{
                //run this, because the navigator is empty and was simply
                //given an item that no patterns start with, so we move the
                //thing to the improved list, because we know we won't be
                //missing any patterns by putting it there
                if(nav.isEmpty()){
                    improvedList.add(pattern.remove());
                    if(explicit) System.out.println("resetting from an empty pattern");
                //run this, there was the start to a pattern but it didn't
                //pan out, so put the first element in the potential pattern
                //in the list of things that aren't in patterns and put the rest
                //back in the list to be checked
                }else{
                    improvedList.add(nav.removeThing());
                    pattern.addAll(0, nav.getThings());
                }
            }
        }
        pattern = improvedList;
    }
    
    //runs the pattern recognizing shortenList method until it no longer shortens
    //the list (meaning it has recognized all the patterns it can)
    private void optimizePattern(){
        int l;
        do{
            l = pattern.size();
            this.shortenList();
        }while(l>pattern.size());
    }
    
    
}
