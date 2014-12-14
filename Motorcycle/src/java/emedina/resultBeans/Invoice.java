/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emedina.resultBeans;

import java.util.*;
import java.text.*;
import java.io.Serializable;
import java.sql.Timestamp;
/**
 *
 * @author mark
 */
public class Invoice implements Serializable {
    private User user;
    private List<CartItem> cartItems;
    private Timestamp dateCreated;
    private Date dateOrdered;
    private int confirmationNumber;

    
    public Invoice(){
        
        user = null;
        cartItems = null;
        dateCreated = null;
        dateOrdered = null;
        confirmationNumber = 0;

       
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
    
    public List<CartItem> getCartItems() {
        return cartItems;
    }
    
    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }
    
    public Timestamp getDateCreated() {
        return dateCreated;
    }
    
    public void setDateOrdered(Date dateOrdered){
        this.dateOrdered = dateOrdered;
    }
    
    public Date getDateOrdered() {
        return dateOrdered;
    }
    
    public void setConfirmationNumber(int confirmationNumber){
        this.confirmationNumber = confirmationNumber;
    }
    
    public int getConfirmationNumber() {
        return confirmationNumber;
    }
    
    public double getInvoiceTotal() {
        double invoiceTotal = 0.0;
        for (CartItem item : cartItems) {
            invoiceTotal += item.getTotal();
        }
        return invoiceTotal;
    }
    
    public String getInvoiceTotalCurrencyFormat() {
        double total = this.getInvoiceTotal();
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        String formattedTotal = currency.format(total);
        return formattedTotal;
    }
}
