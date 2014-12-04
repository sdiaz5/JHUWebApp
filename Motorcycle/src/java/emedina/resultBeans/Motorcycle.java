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
public class Motorcycle extends Product {
    
    private int id;
    private String name;
    private String description;
    private Double price;
    private String condition;
    private String brand;
    private String bikeType;
    private int quantity;
    private String productNumber;
    
    public Motorcycle(){
        id = 0;
        name = "";
        description = "";
        price = 0.0;
        condition = "";
        bikeType = "";
        quantity = 0;
        productNumber = "";
        
    }
    
    public Motorcycle(int id, String name, String description, double price, 
            String condition, String bikeType, int quantity, String productNumber){
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.condition = condition;
        this.bikeType = bikeType;
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
    
    public Double getPrice(){
        return price;
    }
    
    public void setPrice(Double price){
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
    
    public String getBikeType(){
        return bikeType;
    }
    
    public void setBikeType(String bikeType){
        this.bikeType = bikeType;
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
