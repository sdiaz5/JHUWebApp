/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.*;
import java.util.*;

import emedina.resultBeans.*;

/**
 *
 * @author mark
 */
public class InvoiceDB {
    
    public static int insert(Invoice invoice) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "INSERT INTO JHUAppDB.Order (userID, dateOrdered, confirmationNumber) "
                + "VALUES (?, current_date, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, invoice.getUser().getId());
            ps.setInt(2, invoice.getConfirmationNumber());
            ps.executeUpdate();
            
            //get the id of the order that was just created
            String identityQuery = "SELECT @@IDENTITY AS IDENTITY";
            Statement identityStatement = connection.createStatement();
            ResultSet identityResultSet = identityStatement.executeQuery(identityQuery);
            identityResultSet.next();
            int Order_id = identityResultSet.getInt("IDENTITY");
            identityResultSet.close();
            identityStatement.close();
            
            //update the link tables with the productIds from the order
            //update the product quantities based on how many products were purchased
            int quantity;
            List<CartItem> cartItems = invoice.getCartItems();
            for(CartItem item : cartItems) {
                LinkDB.insertLink(Order_id, item.getProduct().getId(), item.getQuantity(), item.getProduct().getType());
                quantity = item.getProduct().getQuantity()-item.getQuantity();
                ProductDB.updateProductQuantity(item.getProduct(), quantity);
            }
            return 0;
        } catch(SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
    }
    
    public static int selectMaxConfirmationNumber() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT max(confirmationNumber) as number FROM JHUAppDb.Order";
        
        try {
            ps = connection.prepareStatement(query);
            rs=ps.executeQuery();
            
            int newConfirmationNumber = 0;
            int ConfNumber = 0;
            if(rs.next()){
                ConfNumber = rs.getInt("number");
                newConfirmationNumber=ConfNumber + 1;
            }
            //int confirmationNumber = Integer.parseInt(rs.getString("cNumber"));
            //newConfirmationNumber = String.valueOf((confirmationNumber + 1));
            
            return newConfirmationNumber;
        }catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static ArrayList<Integer> selectConfirmationNumbers(int userId){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT confirmationNumber FROM JHUAppDb.Order where userId = ?";
        
        try{
            ps = connection.prepareStatement(query);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            
            ArrayList<Integer> confirmationNumbers = new ArrayList<>();
            while(rs.next()){
                confirmationNumbers.add(rs.getInt("confirmationNumber"));
            }
            return confirmationNumbers;
        }catch (SQLException e) {
                System.out.println(e);
                return null;
        } finally {
                DBUtil.closeResultSet(rs);
                DBUtil.closePreparedStatement(ps);
                pool.freeConnection(connection);
        }
        
    }
    
    public static int selectInvoiceId(int confirmationNumber){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT id FROM JHUAppDb.Order where confirmationNumber = ?";
        
        try{
            ps = connection.prepareStatement(query);
            ps.setInt(1, confirmationNumber);
            rs = ps.executeQuery();
            
            int orderId = 0;
            if(rs.next()){
                orderId = rs.getInt("id");
            }
            
            return orderId;
        }catch (SQLException e) {
                System.out.println(e);
                return 0;
        } finally {
                DBUtil.closeResultSet(rs);
                DBUtil.closePreparedStatement(ps);
                pool.freeConnection(connection);
        }
        
    }
    
    public static ArrayList<Product> selectProducts(int confirmationNumber){
        
        int orderId = selectInvoiceId(confirmationNumber);
        
        ArrayList<Product> invoiceProducts = new ArrayList<>();
        
        ArrayList<Product> gloves = ProductDB.selectOrderGloves(orderId);
        ArrayList<Product> helmets = ProductDB.selectOrderHelmets(orderId);
        ArrayList<Product> jackets = ProductDB.selectOrderJackets(orderId);
        ArrayList<Product> motorcycles = ProductDB.selectOrderMotorcycles(orderId);
        
        if(motorcycles.size() != 0){
            for(Product motorcycle : motorcycles){
                invoiceProducts.add(motorcycle);
            }
        }
        
        if(helmets.size() != 0){
            for(Product helmet : helmets){
                invoiceProducts.add(helmet);
            }
        }
        
        if(jackets.size() != 0){
            for(Product jacket : jackets){
                invoiceProducts.add(jacket);
            }
        }
        
        if(gloves.size() != 0){
            for(Product glove : gloves){
                invoiceProducts.add(glove);
            }
        }
        
        return invoiceProducts;        
        
    }
    
    public static String selectOrderDate(int confirmationNumber){
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT dateOrdered from JHUAppDb.Order where confirmationNumber = ?";
        
        try{
            ps = connection.prepareStatement(query);
            ps.setInt(1, confirmationNumber);
            rs = ps.executeQuery();
            
            java.sql.Date date = null;
            if(rs.next()){
                 date = rs.getDate("dateOrdered");
                
            }
            return date.toString();
        }catch (SQLException e) {
                System.out.println(e);
                return "";
        } finally {
                DBUtil.closeResultSet(rs);
                DBUtil.closePreparedStatement(ps);
                pool.freeConnection(connection);
        }
    }
    
}
