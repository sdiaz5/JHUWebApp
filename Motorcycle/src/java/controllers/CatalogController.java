/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import data.*;
import emedina.resultBeans.*;
/**
 *
 * @author mark
 */
public class CatalogController extends HttpServlet {
    
    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        String requestURI = request.getRequestURI();
        String url = showProduct(request, response);

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }
    
    private String showProduct(HttpServletRequest request,
            HttpServletResponse response) {
        String productNumber = request.getPathInfo();
        if(productNumber != null){

            if(Character.isDigit(productNumber.charAt(productNumber.length()-1))){
                productNumber = productNumber.substring(1, 7);
                Motorcycle motorcycle = (Motorcycle) ProductDB.selectProduct(productNumber);
                HttpSession session = request.getSession();
                session.setAttribute("product", motorcycle);
            } else {
                productNumber = productNumber.substring(1);
                Product product = ProductDB.selectProduct(productNumber);
                HttpSession session = request.getSession();
                session.setAttribute("product", product);
            }
            
            
        }
        return "/catalog/product.jsp";
    }
}
