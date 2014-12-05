/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package emedina.resultBeans;

import java.io.Serializable;
import java.util.*;
/**
 *
 * @author larva
 */
public class Cart implements Serializable {
    private int id;
    private List<CartItem> cartItems;
    
    public Cart (){
        id = -1;
        cartItems = new ArrayList<>();
    }

    public Cart(int id, List<CartItem> cartItems){
        this.id = id;
        this.cartItems = cartItems;
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
    public List<CartItem> getCartItems() {
        return cartItems;
    }

    /**
     * @param cartItem the cartItem to set
     */
    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
    
    public int getCount() {
        return cartItems.size();
    }
    
    public void addCartItem(CartItem item) {
        String productNumber = item.getProduct().getProductNumber();
        int quantity = item.getQuantity();
        for (CartItem cartItem : cartItems) {
            if (cartItem.getProduct().getProductNumber().equals(productNumber)) {
                cartItem.setQuantity(quantity);
                return;
            }
        }
        cartItems.add(item);
    }
    
    public void removeCartItem(CartItem item) {
        String productNumber = item.getProduct().getProductNumber();
        for (int i = 0; i < cartItems.size(); i++) {
            CartItem cartItem = cartItems.get(i);
            if (cartItem.getProduct().getProductNumber().equals(productNumber)) {
                cartItems.remove(i);
                return;
            }
        }
    }
    
}
