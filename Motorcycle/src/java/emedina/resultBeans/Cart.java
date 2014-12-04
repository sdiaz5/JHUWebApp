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
    private User user;
    private Product products[];
    private Motorcycle motorcycles[];
    
    public Cart (){
        this.products = new Product[0];
        this.motorcycles = new Motorcycle[0];
    }

    public Cart(int id, Product products[], Motorcycle motorcycles[]){
        this.products = products;
        this.motorcycles = motorcycles;
    }

    /**
     * @return the products
     */
    public Product[] getProducts() {
        return products;
    }

    /**
     * @param products the products to set
     */
    public void setProducts(Product[] products) {
        this.products = products;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }
}
