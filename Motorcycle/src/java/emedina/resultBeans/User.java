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
public class User implements Serializable {
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private int lastLogin;
    private String password;
    private ContactInfo contactInfo;
    private String addressHTMLFormat;
    private int id;
    
    public User(){
        
        userName = "";
        firstName = "";
        lastName = "";
        email = "";
        lastLogin = 0;
        password = "";
        contactInfo = null;
        this.addressHTMLFormat = "";
        id = -1;
    }
    
    public User(String userName, String firstName, String lastName, 
            String email , Cart cart, int lastLogin, String password, ContactInfo contactInfo){
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.lastLogin = lastLogin;
        this.password = password;
        this.contactInfo = contactInfo;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the lastLogin
     */
    public int getLastLogin() {
        return lastLogin;
    }

    /**
     * @param lastLogin the lastLogin to set
     */
    public void setLastLogin(int lastLogin) {
        this.lastLogin = lastLogin;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }

    /**
     * @return the contactInfo
     */
    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    /**
     * @param contactInfo the contactInfo to set
     */
    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }
    
    public String getAddressHTMLFormat (){
        String rv = "";
        if (contactInfo != null)
        {
            rv = contactInfo.getState() + "<br>" + contactInfo.getCity() + 
                    ", " + contactInfo.getState()+ " " + 
                    contactInfo.getZipCode()+ "<br>";
        }
        return rv;
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
}
