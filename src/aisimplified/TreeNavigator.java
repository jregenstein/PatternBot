/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aisimplified;

import java.util.LinkedList;

/**
 *looks through the thingnode tree
 * @author jacobregenstein
 */
public class TreeNavigator {
    ThingNode currentNode;
    LinkedList<Thing> currentpattern = new LinkedList<>();//a list containing that pattern that is currently being looked at
    private boolean explicit = false;//just for debugging purposes, turns on print statements
    
    public TreeNavigator(){
        currentNode = ThingNode.root;
    }
    
    public void goToChild(Thing t){
         ThingNode child = currentNode.getChild(t);
         assert child!=null;
         currentpattern.add(t);
         if(explicit) System.out.println("going to " + t.toString());
         if(currentNode.isPattern()){
             if(explicit) System.out.print("oh my god guys I found a pattern and it is " + currentNode.getValue().toString());
         }
         currentNode = child;
    }
    
    public Thing removeThing(){
        return currentpattern.remove();
    }
    
    public LinkedList<Thing> getThings(){
        return currentpattern;
    }
    
    public boolean hasChild(Thing t){
        if(explicit) System.out.println("checking if " + t.toString() + " is contained in " + currentNode.getChildren().toString());
        if(explicit) System.out.println("answer: " + currentNode.hasChild(t));
        return currentNode.hasChild(t);
    }
    
    
    public boolean hasPattern(){
        return currentNode.isPattern();
    }
    
    public Thing getPattern(){
        return currentNode.getPattern();
    }
    
    public void reset(){
        currentNode = ThingNode.root;
        if(explicit) System.out.println("resetting: " + currentpattern.toString());
        currentpattern.clear();
    }
    
    public boolean isEmpty(){
        return currentpattern.isEmpty();
    }
}
