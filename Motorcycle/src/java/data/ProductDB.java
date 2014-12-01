/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;
import java.sql.*;
import java.util.ArrayList;
import emedina.resultBeans.*;


/**
 *
 * @author mark
 */
public class ProductDB {
    
    public static int insertMotorcycle(Motorcycle motorcycle) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query = "INSERT INTO Motorcycle (name, description, price, condition, brand, type, quantity, productNumber) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, motorcycle.getName());
            ps.setString(2, motorcycle.getDescription());
            ps.setDouble(3, motorcycle.getPrice());
            ps.setString(4, motorcycle.getCondition());
            ps.setString(5, motorcycle.getBrand());
            ps.setString(6, motorcycle.getType());
            ps.setInt(7, motorcycle.getQuantity());
            ps.setString(8, motorcycle.getProductNumber());
            return ps.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static int updateMotorcyclePrice(Motorcycle motorcycle) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query = "UPDATE Motorcycle SET "
                + "price = ?, "
                + "WHERE name = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setDouble(1, motorcycle.getPrice());
            ps.setString(2, motorcycle.getName());
            
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static int updateMotorcycleQuantity(Motorcycle motorcycle) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query = "UPDATE Motorcycle SET "
                + "quantity = ?, "
                + "WHERE name = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, motorcycle.getQuantity());
            ps.setString(2, motorcycle.getName());
            
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static int deleteMotorcycle(Motorcycle motorcycle) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query = "DELETE FROM Motorcycle "
                + "WHERE name = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, motorcycle.getName());
            
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static Motorcycle selectMotorcycle(int id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM Motorcycle "
                + "WHERE id = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            Motorcycle motorcycle = null;
            if(rs.next()) {
                motorcycle = new Motorcycle();
                motorcycle.setName(rs.getString("name"));
                motorcycle.setDescription(rs.getString("description"));
                motorcycle.setPrice(rs.getDouble("price"));
                motorcycle.setCondition(rs.getString("condition"));
                motorcycle.setBrand(rs.getString("brand"));
                motorcycle.setType(rs.getString("type"));
                motorcycle.setQuantity(rs.getInt("quantity"));
                motorcycle.setProductNumber(rs.getString("productNumber"));
            }
            return motorcycle;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public ArrayList<Integer> selectMotorcycleIds(){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT id From Motorcycle ORDER BY id";
        
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            ArrayList<Integer> motorcycleIds = new ArrayList<>();
            while (rs.next()) {
                motorcycleIds.add(rs.getInt("id"));
            }
            return motorcycleIds;
        } catch (SQLException e) {
                System.out.println(e);
                return null;
        } finally {
                DBUtil.closeResultSet(rs);
                DBUtil.closePreparedStatement(ps);
                pool.freeConnection(connection);
        }
    }
    
    //Product insert, update, delete, select
    
    public static int insertProduct(Product product) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        Product.Type productType = product.getType();
        String query = null;
        switch(productType){
            case JACKET:
                query = "INSERT INTO Jacket (name, description, price, size, quantity, productNumber) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
                break;
            case HELMET:
                query = "INSERT INTO Helmet (name, description, price, size, quantity, productNumber) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
                break;
            case GLOVE:
                query = "INSERT INTO Gloves (name, description, price, size, quantity, productNumber) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
                break;
        }
       
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getPrice());
            ps.setString(4, product.getSize());
            ps.setInt(5, product.getQuantity());
            ps.setString(6, product.getProductNumber());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement (ps);
            pool.freeConnection(connection);
        }
    }
    
    public static int updateProductPrice(Product product) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        Product.Type productType = product.getType();
        String query = null;
        switch(productType){
            case JACKET:
                query = "UPDATE Jacket SET "
                        + "price = ? "
                        + "WHERE name = ?";
                break;
            case HELMET:
                query = "UPDATE Helmet SET "
                        + "price = ? "
                        + "WHERE name = ?";
                break;
            case GLOVE:
                query = "UPDATE Gloves SET "
                        + "price = ? "
                        + "WHERE name = ?";
                break;
        }
                       
        try {
            ps = connection.prepareStatement(query);
            ps.setDouble(1,product.getPrice());
            ps.setString(2, product.getName());
            
            return ps.executeUpdate();
        } catch(SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
        public static int updateProductQuantity(Product product) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        Product.Type productType = product.getType();
        String query = null;
        switch(productType){
            case JACKET:
                query = "UPDATE Jacket SET "
                        + "quantity = ? "
                        + "WHERE name = ?";
                break;
            case HELMET:
                query = "UPDATE Helmet SET "
                        + "quantity = ? "
                        + "WHERE name = ?";
                break;
            case GLOVE:
                query = "UPDATE Gloves SET "
                        + "quantity = ? "
                        + "WHERE name = ?";
                break;
        }
                       
        try {
            ps = connection.prepareStatement(query);
            ps.setDouble(1,product.getQuantity());
            ps.setString(2, product.getName());
            
            return ps.executeUpdate();
        } catch(SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static int deleteProduct (Product product) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        Product.Type productType = product.getType();
        String query = null;
        switch(productType){
            case JACKET:
                query = "DELETE FROM Jacket "
                    + "WHERE name = ?";
                break;
            case HELMET:
                query = "DELETE FROM Helmet "
                    + "WHERE name = ?";
                break;
            case GLOVE:
                query = "DELETE FROM Gloves "
                    + "WHERE name = ?";
                break;
        }
                        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, product.getName());
            
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static Product selectProduct(Product.Type type, int id){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = null;
        switch(type){
            case JACKET:
                query = "SELECT * FROM Jacket "
                        + "WHERE id = ?";
                break;
            case HELMET:
                query = "SELECT * FROM Helmet "
                        + "WHERE id = ?";
                break;
            case GLOVE:
                query = "SELECT * FROM Gloves "
                        + "WHERE id = ?";
                break;
        }
        
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            Product product = null;
            if (rs.next()) {
                product = new Product();
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                product.setSize(rs.getString("size"));
                product.setQuantity(rs.getInt("quantity"));
                product.setProductNumber(rs.getString("productNumber"));
            }
            return product;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
        public ArrayList<Integer> selectProductIds(Product.Type type){
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            
            String query = null;
            switch(type) {
                case JACKET:
                    query = "SELECT id FROM Jacket ORDER BY id";
                    break;
                case HELMET:
                    query = "SELECT id FROM Helmet ORDER BY id";
                    break;
                case GLOVE:
                    query = "SELECT id FROM Gloves ORDER BY id";
                    break;
            }
            
            try {
                ps = connection.prepareStatement(query);
                rs = ps.executeQuery();
                ArrayList<Integer> productIds = new ArrayList<>();
                while (rs.next()) {
                    productIds.add(rs.getInt("id"));
                }
                return productIds;
            } catch (SQLException e) {
                System.out.println(e);
                return null;
            } finally {
                DBUtil.closeResultSet(rs);
                DBUtil.closePreparedStatement(ps);
                pool.freeConnection(connection);
            }
        }
        
    }
    
