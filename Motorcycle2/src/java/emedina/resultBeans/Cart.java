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
    private Product products[];

    public Cart(int id, Product products[]){
        this.id = id;
        this.products = products;
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
}
