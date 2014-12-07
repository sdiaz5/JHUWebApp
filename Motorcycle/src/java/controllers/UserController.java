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
}    
