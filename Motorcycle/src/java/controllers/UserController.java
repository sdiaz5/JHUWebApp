/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import data.UserDB;
import emedina.resultBeans.ContactInfo;
import emedina.resultBeans.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author mark
 */

public class UserController extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        String requestURI = request.getRequestURI();
        String url = "";
        if (requestURI.endsWith("/login")) {
            url = loginUser(request, response);
        }else if(requestURI.endsWith("/logout")) {
            url = logoutUser(request, response);
        }else if (requestURI.endsWith("/register")){
            url = registerUser(request, response);
        }else if (requestURI.endsWith("/setShipping")){
            url = setShipping(request, response);
        }else if (requestURI.endsWith("/setUser")){
            url = setUser(request, response);
        }else if (requestURI.endsWith("/changePass")){
            url = setPassword(request, response);
        }
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }
    
    private String registerUser(HttpServletRequest request,
            HttpServletResponse response)
    {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUserName(userName);
        user.setPassword(password);
        user.setEmail(email);

        String url;
        String message;
        //check that email address doesn't already exist
        if (UserDB.emailExists(email)) {
            message = "This email address already exists. <br>"
                    + "Please enter another email address.";
            request.setAttribute("message", message);
            url = "/newuser.jsp";
        } else if (UserDB.selectUser(userName) != null) {
            message = "This username already exists. <br>"
                    + "Please enter another username.";
            request.setAttribute("message", message);
            url = "/newuser.jsp";
        } else {
            UserDB.insertUser(user);
            request.getSession().setAttribute("user", user);
            url = "/index.jsp";
        }
        
        return url;        
    
    }
    
    private String logoutUser(HttpServletRequest request,
            HttpServletResponse response)
    {
        request.getSession().setAttribute("user", null);
        return "/login.jsp";
    }

    private String setShipping(HttpServletRequest request,
            HttpServletResponse response)
    {
        User user = (User) request.getSession().getAttribute("user");
        String rv = "/cart/invoice.jsp";
        if (user != null)
        {
            String street = request.getParameter("street");
            String city = request.getParameter("city");
            String state = request.getParameter("state");
            String zip = request.getParameter("zip");

            if(street != null && city != null && state != null && 
                zip != null)
            {
                ContactInfo contact = user.getContactInfo();
                if(contact == null){
                    contact = new ContactInfo();
                    contact.setUserId(user.getId());
                }
                
                contact.setState(state);
                contact.setStreet(street);
                contact.setCity(city);
                contact.setZipCode(zip);
                user.setContactInfo(contact);

                UserDB.updateContact(contact, user.getId());
            }else{
                rv = "/user/setShipping";
            }
        }
        return rv;
    }
    
    private String loginUser(HttpServletRequest request,
            HttpServletResponse response) {

        if(request.getAttribute("user") != null){
            return "/index.jsp";
        }
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        String message = null;
        String url = "/login.jsp";        
        //check that email address doesn't already exist
        if (UserDB.checkUserPassword(username, password)) {
            User user = UserDB.selectUser(username);
            if(user != null){
                request.getSession().setAttribute("user", user);
                url = "/index.jsp";
            }
        } else {
            message = "Username and password combination are incorrect.";
            request.setAttribute("message", message);
        }
        
        return url;
    }
    
    private String setUser(HttpServletRequest request,
        HttpServletResponse response) {
        String rv = "/account.jsp";
        String account_message = "Successfully updated user information";
        User user = (User) request.getSession().getAttribute("user");
        if (user != null)
        {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String street = request.getParameter("street");
            String city = request.getParameter("city");
            String state = request.getParameter("state");
            String zip = request.getParameter("zip");

            if(street != null && city != null && state != null && 
                zip != null)
            {
                user.setFirstName(firstName);
                user.setLastName(lastName);
                
                if(!UserDB.checkUserEmail(user.getUserName(), email.trim())){
                    if(UserDB.emailExists(email.trim()))
                    {
                        account_message = "Another user already uses this email.";
                    }
                    else
                    {
                        user.setEmail(email.trim());
                    }
                }
                
                UserDB.updateUser(user);
                
                ContactInfo contact = user.getContactInfo();
                if(contact == null){
                    contact = new ContactInfo();
                    contact.setUserId(user.getId());
                }
                
                contact.setState(state);
                contact.setStreet(street);
                contact.setCity(city);
                contact.setZipCode(zip);
                user.setContactInfo(contact);

                UserDB.updateContact(contact, user.getId());
                request.setAttribute("account_message", account_message);
            }
        }
        else
        {//No User
            rv = "/login.jsp";
        }
        return rv;
    }    

    private String setPassword(HttpServletRequest request,
        HttpServletResponse response) {  
        String pass_message = "Successfully Updated Password";
        String rv = "/account.jsp";
        User user = (User) request.getSession().getAttribute("user");
        if (user != null)
        {
            String pass = request.getParameter("pass");
            String nPass = request.getParameter("npass");
            String rPass = request.getParameter("rpass");

            if(pass != null && nPass != null && rPass != null)
            {
                if(UserDB.checkUserPassword(user.getUserName(), pass))
                {
                    if(nPass.trim().equals(rPass.trim()))
                    {
                        user.setPassword(nPass);
                        UserDB.updateUser(user);
                    }
                    else
                    {
                        pass_message = "Your new password must match the re-type.";
                    }
                }
                else
                {
                    pass_message = "Your old password was not valid.";
                }
            }
            else
            {
                pass_message = "Invalid parameters";
            }
        }
        else
        {   //No User
            pass_message = "Action could not be performed, no user was found, please login.";
            rv = "/login.jsp";
        }
        
        request.setAttribute("pass_message", pass_message);

        return rv;
    }
}