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
    
}
