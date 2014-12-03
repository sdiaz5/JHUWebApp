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
 * @author mark
 */
public class Motorcycle implements Serializable {
    
    private int id;
    private String name;
    private String description;
    private double price;
    private String condition;
    private String brand;
    private String type;
    private int quantity;
    private String productNumber;
    
    public Motorcycle(){
        id = 0;
        name = "";
        description = "";
        price = 0.0;
        condition = "";
        type = "";
        quantity = 0;
        productNumber = "";
        
    }
    
    public Motorcycle(int id, String name, String description, double price, 
            String condition, String type, int quantity, String productNumber){
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.condition = condition;
        this.type = type;
        this.quantity = quantity;
        this.productNumber = productNumber;
    }
    
    public int getId(){
        return id;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getDescription(){
        return description;
    }
    
    public void setDescription(String description){
        this.description = description;
    }
    
    public double getPrice(){
        return price;
    }
    
    public void setPrice(double price){
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
    public String getCondition(){
        return condition;
    }
    
    public void setCondition(String condition){
        this.condition = condition;
    }
    
    public String getBrand(){
        return brand;
    }
    
    public void setBrand(String brand){
        this.brand = brand;
    }
    
    public String getType(){
        return type;
    }
    
    public void setType(String type){
        this.type = type;
    }
    
    public int getQuantity(){
        return quantity;
    }
    
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    
    public String getProductNumber(){
        return productNumber;
    }
    
    public void setProductNumber(String productNumber){
        this.productNumber = productNumber;
    }
}
