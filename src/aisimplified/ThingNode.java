/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aisimplified;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author jacobregenstein
 */
public class ThingNode {
    private static HashMap<Character,Symbol> symbols = new HashMap<>();//stores all the symbols so we don't have duplicates
    public static ThingNode root = new ThingNode(null);
    private Thing pattern;//should only have a value if isPattern==true, contains the values of all the parents
    private Thing value;
    private HashMap<Thing,ThingNode> children = new HashMap<>();
    private ThingNode parent;
    
    //depending on the length of the string, calls either the pattern or symbol
    //constructor
    public static Thing makeThing(String s){
        assert s.length()!=0;
        if(s.length()==1){
            return makeThing(s.charAt(0));
        }else{
            return new Pattern(s);
        }
    }
    
    //really only makes symbols, but named the same so makeThing can take chars or strings
    public static Thing makeThing(char c){
        if(symbols.containsKey(c)){
            return symbols.get(c);
        }else{
            Symbol s = new Symbol(c);
            symbols.put(c, s);
            root.addChild(new ThingNode(s));
            return s;
        }
    }
    
    public String toString(){
        String s = "";
        if(value!=null) s += value.toString();
        if(pattern!=null) s += "pattern: " + pattern.toString();
        s += "(";
        for(ThingNode n : children.values()){
            s += n.toString();
        }
        s += ")";
        return s;
    }
    
    //adds a pattern to the tree (unless the pattern is already contained in the tree)
    public static void addPattern(Pattern p){
        root.addPattern(p, p.getPattern());
    }
    
    private void addPattern(Pattern p, List<Thing> things){
        if(things.isEmpty()){
            this.pattern = p;
        }else{
            if(!this.hasChild(things.get(0))){//adds it as a child if it isn't one
                this.addChild(new ThingNode(things.get(0)));
            }
            this.getChild(things.get(0)).addPattern(p, things.subList(1, things.size()));
        }
    }
    
    public static void addSymbol(Symbol s){
        root.addChild(new ThingNode(s));
    }
    
    public void setPattern(Pattern p){
        pattern = p;
    }
    
    public boolean isPattern(){
        return pattern!=null;
    }
    
    public Thing getPattern(){
        assert isPattern();
        return pattern;
    }
    
    //constructs a thingnode given a thing and a boolean that tells you if the path to the node represents a pattern
    public ThingNode(Thing t){
        value = t;
    }
    
    public void addChild(ThingNode node){
        children.put(node.getValue(), node);
        node.setParent(this);
    }
    
    public void setParent(ThingNode node){
        parent = node;
    }
    
    public Thing getValue(){
        return value;
    }
    
    public ThingNode getChild(Thing t){
        return children.get(t);
    }
    
    public Set<Thing> getChildren(){
        return children.keySet();
    }
    
    public boolean hasChild(Thing t){
        return children.containsKey(t);
    }
}
