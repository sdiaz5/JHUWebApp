/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import emedina.resultBeans.Product;
import java.sql.*;

/**
 *
 * @author mark
 */
public class LinkDB {
    
    public static int insertLink(int Order_id, int Product_id, int quantity, Product.Type type) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query = null;
        switch(type){
            case JACKET:
                query = "INSERT INTO Order_has_Jacket (Order_id, Jacket_id, quantity) "
                + "VALUES (?, ?, ?)";
                break;
            case HELMET:
                query = "INSERT INTO Order_has_Helmet (Order_id, Helmet_id, quantity) "
                + "VALUES (?, ?, ?)";
                break;
            case GLOVE:
                query = "INSERT INTO Order_has_Gloves (Order_id, Gloves_id, quantity) "
                + "VALUES (?, ?, ?)";
                break;
            case MOTORCYCLE:
                query = "INSERT INTO Order_has_Motorcycle (Order_id, Motorcycle_id, quantity) "
                + "VALUES (?, ?, ?)";
                break;
        }
        
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, Order_id);
            ps.setInt(2, Product_id);
            ps.setInt(3, quantity);
            ps.executeUpdate();
            
            return ps.executeUpdate();
        }catch(SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }        
        
    }
    
    public static int insertJacektLink(int Order_id, int Jacket_id, int quantity) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query = "INSERT INTO Order_has_Jacket (Order_id, Jacket_id, quantity) "
                + "VALUES (?, ?, ?)";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, Order_id);
            ps.setInt(2, Jacket_id);
            ps.setInt(3, quantity);
            ps.executeUpdate();
            
            return ps.executeUpdate();
        }catch(SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }        
        
    }
    
    
    public static int insertHelmetLink(int Order_id, int Helmet_id, int quantity) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query = "INSERT INTO Order_has_Helmet (Order_id, Helmet_id, quantity) "
                + "VALUES (?, ?, ?)";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, Order_id);
            ps.setInt(2, Helmet_id);
            ps.setInt(3, quantity);
            ps.executeUpdate();
            
            return ps.executeUpdate();
        }catch(SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
        
    }
    
    public static int insertGlovesLink(int Order_id, int Gloves_id, int quantity) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query = "INSERT INTO Order_has_Gloves (Order_id, Gloves_id, quantity) "
                + "VALUES (?, ?, ?)";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, Order_id);
            ps.setInt(2, Gloves_id);
            ps.setInt(3, quantity);
            ps.executeUpdate();
            
            return ps.executeUpdate();
        }catch(SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
        
    }
    
    public static int insertMotorcycleLink(int Order_id, int Motorcycle_id, int quantity) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query = "INSERT INTO Order_has_Motorcycle (Order_id, Motorcycle_id, quantity) "
                + "VALUES (?, ?, ?)";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, Order_id);
            ps.setInt(2, Motorcycle_id);
            ps.setInt(3, quantity);
            ps.executeUpdate();
            
            return ps.executeUpdate();
        }catch(SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
        
    }
}
