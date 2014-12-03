/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package emedina.resultBeans;

import java.io.Serializable;
import java.text.NumberFormat;

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
    private int quantity;
    private String productNumber;
    
    public Product(){
        id = -1;
        name = "";
        desc = "";
        price = 0.0;
        size = "";
        quantity = 0;
        productNumber = "";
    }

    public Product(Type type, int id, String name, String desc, 
            Double price, String size, int quantity, String productNumber){
        this.type = type;
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.size = size;
        this.quantity = quantity;
        this.productNumber = productNumber;
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
    
    public String getPriceCurrencyFormat() {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(price);
    }
    
    public String getImageURL() {
        String imageURL = "/JHUWebApp/images/" + productNumber + ".jpg";
        return imageURL;
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
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public int getQuantity(){
        return quantity;
    }
    
    public void setProductNumber(String productNumber){
        this.productNumber = productNumber;
    }
    
    public String getProductNumber(){
        return productNumber;
    }
    
    
    public enum Type{
        UNKNOWN, MOTORCYCLE, GLOVE, JACKET, HELMET
    }
    private Type type;
    
    
}
