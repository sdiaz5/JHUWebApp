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
public class Product implements Serializable {
    
    
    private int id;
    private String name;
    private String desc;
    private Double price;
    private String size;
    
    public Product(){
        id = -1;
        name = "";
        desc = "";
        price = 0.0;
        size = "";
    }

    public Product(Type type, int id, String name, String desc, 
            Double price, String size){
        this.type = type;
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.size = size;
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return desc;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String desc) {
        this.desc = desc;
    }

    /**
     * @return the price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * @return the size
     */
    public String getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * @return the type
     */
    public Type getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(Type type) {
        this.type = type;
    }
    
    public enum Type{
        UNKNOWN, MOTORCYCLE, GLOVE, JACKET, HELMET
    }
    private Type type;
    
    
}
