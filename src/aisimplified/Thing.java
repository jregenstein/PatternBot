/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aisimplified;

import java.util.HashSet;

/**
 *Thing is usually a very general word, but in this program, Thing has a very 
 * specific definition: a thing is a symbol or a pattern. This is because 
 * patterns need to be able to contain both symbols and objects. I realize that
 * at this point I could just use object class to the same effect, but I'm adding
 * this in cause I figure at some point I'll want to have methods that both
 * symbol and patterns use, like having a static list holding references to the
 * patterns they're used it.
 * @author jacobregenstein
 */
public abstract class Thing {
    
    
    public abstract boolean isSymbol();
    public abstract boolean isPattern();
}
