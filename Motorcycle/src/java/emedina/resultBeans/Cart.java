/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package emedina.resultBeans;

import java.io.Serializable;
/**
 *
 * @author larva
 */
public class Cart implements Serializable {
    private int id;
    private CartItem cartItem;
    
    public Cart (){
        id = -1;
        cartItem = null;
    }

    public Cart(int id, CartItem cartItem){
        this.id = id;
        this.cartItem = cartItem;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the cartItem
     */
    public CartItem getCartItem() {
        return cartItem;
    }

    /**
     * @param cartItem the cartItem to set
     */
    public void setCartItem(CartItem cartItem) {
        this.cartItem = cartItem;
    }
    
}
