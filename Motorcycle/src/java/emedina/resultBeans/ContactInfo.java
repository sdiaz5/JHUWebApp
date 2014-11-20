/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emedina.resultBeans;

import java.io.Serializable;
/**
 *
 * @author mark
 */
public class ContactInfo implements Serializable {
    
    private int id;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private int userId;
    
    public ContactInfo(){
        id = 0;
        street = "";
        city = "";
        state = "";
        zipCode = "";
        userId = 0;
    }
    
    public ContactInfo(String street, String city, String state, String zipCode,
            int userId){
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.userId = userId;
    }
    
    public String getStreet(){
        return street;
    }
    
    public void setStreet(String street){
        this.street = street;
    }
    
    public String getCity(){
        return city;
    }
    
    public void setCity(String city){
        this.city = city;
    }
    
    public String getState(){
        return state;
    }
    
    public void setState(String state){
        this.state = state;
    }
    
    public String getZipCode(){
        return zipCode;
    }
    
    public void setZipCode(String zipCode){
        this.zipCode = zipCode;
    }
    
    public int getUserId(){
        return userId;
    }
    
    public void setUserId(int userId){
        this.userId = userId;
    }
    
}
