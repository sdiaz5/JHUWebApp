/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import data.UserDB;
import emedina.resultBeans.User;
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
