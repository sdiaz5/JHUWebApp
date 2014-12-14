/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import data.*;
import emedina.resultBeans.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import utility.Utility;

/**
 *
 * @author mark
 */
public class OrderController extends HttpServlet {
    private static final String defaultURL = "/cart/cart.jsp";
    
    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String url = "";
        if (requestURI.endsWith("/addItem")) {
            url = addItem(request, response);
        } else if (requestURI.endsWith("/updateItem")) {
            url = updateItem(request, response);
        } else if (requestURI.endsWith("/removeItem")) {
            url = removeItem(request, response);
        } else if (requestURI.endsWith("/thanks")) {
            url = completeOrder(request, response);
        }
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String url = defaultURL;
        if (requestURI.endsWith("/cart")) {
            showCart(request, response);
        } else if (requestURI.endsWith("/pastOrders")){
            url = pastOrders(request, response);
        } else {
            url = showPastOrder(request, response);
        }
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }
    
    private String showCart(HttpServletRequest request,
            HttpServletResponse response) {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null || cart.getCount() == 0) {
            String message = "You cart is empty please add an item to you cart to check out.";
            request.setAttribute("message", message);
        } else {
            request.getSession().setAttribute("cart", cart);
        }
        return defaultURL;
    }
    
    private String addItem(HttpServletRequest request,
            HttpServletResponse response) {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null)
            cart = new Cart();
        
        String productNumber = request.getParameter("productNumber");
        Product product = ProductDB.selectProduct(productNumber);
        if (product != null) {
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cart.addCartItem(cartItem);
        }
        session.setAttribute("cart", cart);
        return defaultURL;
    }
    
    private String updateItem(HttpServletRequest request,
            HttpServletResponse response) {
        String quantityString = request.getParameter("quantity");
        String productNumber = request.getParameter("productNumber");
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        int quantity;
        String message = "";
        
        try {
            quantity = Integer.parseInt(quantityString);
            if (quantity < 0)
                quantity = 1;
        } catch (NumberFormatException ex) {
            quantity = 1;
        }
        Product product = ProductDB.selectProduct(productNumber);
        if (product != null && cart != null  && product.getQuantity() >= quantity) {
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            if (quantity > 0)
                cart.addCartItem(cartItem);
            else
                cart.removeCartItem(cartItem);
        } else {
            message = "Cannot update cart.  We do not have enough inventory. Current number of " + product.getName() +
                    " in stock is: " + product.getQuantity();
            
        }
        request.setAttribute("message", message);
        return defaultURL;
    }
    
    private String removeItem(HttpServletRequest request,
            HttpServletResponse response) {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        String productNumber = request.getParameter("productNumber");
        if(Character.isDigit(productNumber.charAt(productNumber.length()-1))){
            Motorcycle motorcycle = (Motorcycle) ProductDB.selectProduct(productNumber);
            if (motorcycle != null && cart != null) {
            CartItem cartItem = new CartItem();
            cartItem.setProduct(motorcycle);
            cart.removeCartItem(cartItem);
        }
        } else {
            Product product = ProductDB.selectProduct(productNumber);
            if (product != null && cart != null) {
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cart.removeCartItem(cartItem);
        }
        }
        
        if(cart.getCount() == 0){
            request.setAttribute("message", "You cart is empty please add an item to you cart to check out.");
        }
        
        
        return defaultURL;
    }
    
    private String completeOrder(HttpServletRequest request,
            HttpServletResponse response) {
       
        HttpSession session = request.getSession();
        //create invoice with user id
        User user = (User) session.getAttribute("user");
        Cart cart = (Cart) session.getAttribute("cart");
        int confirmationNumber = InvoiceDB.selectMaxConfirmationNumber();
        
        Invoice invoice = new Invoice();
        
        invoice.setUser(user);
        invoice.setCartItems(cart.getCartItems());
        invoice.setConfirmationNumber(confirmationNumber);
        
        //Get Credit Card Info
        CreditCard card = new CreditCard();
        
        card.setUserId(user.getId());
        card.setCardType(request.getParameter("creditCardType"));
        card.setExpMonth(request.getParameter("creditCardExpirationMonth"));
        card.setExpYear(request.getParameter("creditCardExpirationYear"));
        
        String ccNum = request.getParameter("creditCardNumber");
        
        String creditCardNumber = ccNum.replaceAll("[^0-9]","");
        card.setCreditCardNumber(creditCardNumber);
        
        //Write CreditCard info to the DB
        CreditCardDB.insert(card);
        
        

        
        //update all the link tables with orderid, productid, and quantity
        //update product quanitities
        InvoiceDB.insert(invoice);
        
        
        //set cart to null
        session.setAttribute("cart", null);
        return "/cart/thanks.jsp"; 
    }
    
    private String pastOrders(HttpServletRequest request,
            HttpServletResponse response){
        
        HttpSession session = request.getSession();
        
        User user = (User) session.getAttribute("user");
        
        
        ArrayList<Integer> confirmationNumbers = InvoiceDB.selectConfirmationNumbers(user.getId());
        
        request.setAttribute("confirmationNumbers", confirmationNumbers);
        
        return "/cart/past_orders.jsp";
    }
    
    private String showPastOrder(HttpServletRequest request,
            HttpServletResponse response){
        
        HttpSession session = request.getSession();
        
        String confirmationNumber = request.getPathInfo();
        
        if (confirmationNumber != null){
            
            confirmationNumber = confirmationNumber.substring(1); 
            
            request.setAttribute("confirmationNumber", confirmationNumber);
        }
        
        int confNum = Integer.parseInt(confirmationNumber);
        
        String date = InvoiceDB.selectOrderDate(confNum);
        request.setAttribute("invoiceDate", date);
        
        ArrayList<Product> invoiceProducts = InvoiceDB.selectProducts(confNum);
        request.setAttribute("invoiceProducts", invoiceProducts);
        
        Cart pastInvoice = new Cart();
        
        ArrayList<CartItem> invoiceItems = new ArrayList<>();
        
        for(Product p : invoiceProducts){
            
            CartItem invoiceItem = new CartItem();
            invoiceItem.setQuantity(p.getQuantity());
            invoiceItem.setProduct(p);
            
            invoiceItems.add(invoiceItem);
        }
        
        pastInvoice.setCartItems(invoiceItems);
        
        request.setAttribute("pastInvoice", pastInvoice);
        
        double total = 0.0;
        for(Product p : invoiceProducts){
            total += p.getQuantity()*p.getPrice();
        }
        
        
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        String formattedTotal = currency.format(total);
        
        request.setAttribute("Total", formattedTotal);
        
        return "/cart/orderHistory.jsp";
        
    }

    
}
