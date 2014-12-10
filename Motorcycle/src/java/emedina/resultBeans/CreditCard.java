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
public class CreditCard implements Serializable {
    
    private int userId;
    private String creditCardNumber;
    private String cardType;
    private String expMonth;
    private String expYear;
    
    public CreditCard(){
        userId = 0;
        creditCardNumber="";
        cardType = "";
        expMonth = "";
        expYear = "";
        
    }
    
    public void setUserId(int userId){
        this.userId = userId;
    }
    
    public int getUserId(){
        return userId;
    }
    
    public void setCreditCardNumber(String creditCardNumber){
        this.creditCardNumber = creditCardNumber;
    }
    
    public String getCreditCardNumber(){
        return creditCardNumber;
    }
    
    public void setExpMonth(String expMonth){
        this.expMonth = expMonth;
    }
    
    public String getExpMonth(){
        return expMonth;
    }
    
    public void setExpYear(String expYear){
        this.expYear = expYear;
    }
    
    public String getExpYear(){
        return expYear;
    }
    
    public void setCardType(String cardType){
        this.cardType = cardType;
    }
    
    public String getCardType(){
        return cardType;
    }

    
}
