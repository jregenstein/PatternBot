/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aisimplified;

import java.util.LinkedList;
import java.util.Scanner;

/**
 *a simplified version of AITest. It makes a tree that stores patterns, and if 
 * a pattern contains a pattern already in the tree, it replaces that pattern
 * with a reference to that pattern in the tree.
 * 
 * things to change: the problem is the pattern constructor is creating symbol 
 * objects, which the hasChild() method doesn't see as being the same as the
 * ones used in the tree, cause they are different but identical objects. Recode
 * so that only the TreeNavigator creates things (use the makeThing method instead
 * of the symbol constructor in the pattern from string constructor) and change the
 * TreeNavigator makeThing method so that it checks to see if the thing already
 * exists.
 * 
 * 
 * next up, find a way for sibling nodes to both link to the same child (and figure
 * how to deal with this in the parent field). Possibly go back to the idea of replacing
 * pattern elements with numbers
 * 
 * @author jacobregenstein
 */
public class AISimplified {
    public static boolean explicit = false;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String str = "";
        Scanner s = new Scanner(System.in);
        Thing p;
        while(!str.equals("exit")){
            System.out.println("Please give me an input \n");
            str = s.nextLine();
            if(str.equals("print")){
                System.out.println(ThingNode.root.toString());
            }
            p = ThingNode.makeThing(str);
            System.out.println("the thing you typed in was : " + p.toString());
            if(explicit) System.out.println("current data stored is " + ThingNode.root.toString());
        }
        System.out.println(ThingNode.root.toString());
    }
}
