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
            case MOTORCYCLE:
                query = "INSERT INTO Motorcycle (name, description, price, condition, brand, type, quantity, productNumber) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                break;  
        }
       
        try {
            if(product.getType() == Product.Type.MOTORCYCLE)
            {
                ps.setString(1, ((Motorcycle) product).getName());
                ps.setString(2, ((Motorcycle) product).getDescription());
                ps.setDouble(3, ((Motorcycle) product).getPrice());
                ps.setString(4, ((Motorcycle) product).getCondition());
                ps.setString(5, ((Motorcycle) product).getBrand());
                ps.setString(6, ((Motorcycle) product).getBikeType());
                ps.setInt(7, ((Motorcycle) product).getQuantity());
                ps.setString(8, ((Motorcycle) product).getProductNumber());
            }
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
            case MOTORCYCLE:
                query = "UPDATE Motorcycle SET "
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
            case MOTORCYCLE:
                query = "UPDATE Motorcycle SET "
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
            case MOTORCYCLE:
                query = "DELETE FROM Motorcycle "
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
            case MOTORCYCLE:
                query = "SELECT * FROM Motorcycle "
                        + "WHERE id = ?";
                break;
        }
        
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            Product product = null;
            if (type == Product.Type.MOTORCYCLE)
            {
                if(rs.next())
                {
                    Motorcycle motorcycle = new Motorcycle();
                    motorcycle.setName(rs.getString("name"));
                    motorcycle.setDescription(rs.getString("description"));
                    motorcycle.setPrice(rs.getDouble("price"));
                    motorcycle.setCondition(rs.getString("condition"));
                    motorcycle.setBrand(rs.getString("brand"));
                    motorcycle.setBikeType(rs.getString("type"));
                    motorcycle.setType(type);
                    motorcycle.setQuantity(rs.getInt("quantity"));
                    motorcycle.setProductNumber(rs.getString("productNumber"));
                    product = motorcycle;
                }
            }
            else
            {
                if (rs.next()) {
                    product = new Product();
                    product.setName(rs.getString("name"));
                    product.setDescription(rs.getString("description"));
                    product.setPrice(rs.getDouble("price"));
                    product.setSize(rs.getString("size"));
                    product.setQuantity(rs.getInt("quantity"));
                    product.setProductNumber(rs.getString("productNumber"));
                    product.setType(type);
                }
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
    
    public static Product selectProduct(String productNumber){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = null;
        String accessory = productNumber.substring(productNumber.length()-1);
        Product.Type type;
        switch(accessory){
            case "J":
                query = "SELECT * FROM Jacket "
                        + "WHERE productNumber = ?";
                type = Product.Type.JACKET;
                break;
            case "H":
                query = "SELECT * FROM Helmet "
                        + "WHERE productNumber = ?";
                type = Product.Type.HELMET;
                break;
            case "G":
                query = "SELECT * FROM Gloves "
                        + "WHERE productNumber = ?";
                type = Product.Type.GLOVE;
                break;
            default:
                query = "SELECT * FROM Motorcycle "
                        + "WHERE productNumber = ?";
                type = Product.Type.MOTORCYCLE;
                break;
        }
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1,productNumber);
            rs = ps.executeQuery();
            Product product = null;
            if (type == Product.Type.MOTORCYCLE)
            {
                if(rs.next()) {
                    Motorcycle motorcycle = new Motorcycle();
                    motorcycle.setName(rs.getString("name"));
                    motorcycle.setDescription(rs.getString("description"));
                    motorcycle.setPrice(rs.getDouble("price"));
                    motorcycle.setCondition(rs.getString("condition"));
                    motorcycle.setBrand(rs.getString("brand"));
                    motorcycle.setBikeType(rs.getString("type"));
                    motorcycle.setType(type);
                    motorcycle.setQuantity(rs.getInt("quantity"));
                    motorcycle.setProductNumber(rs.getString("productNumber"));
                    product = motorcycle;
                }
            }
            else
            {
                if (rs.next()) {
                    product = new Product();
                    product.setId(rs.getInt("id"));
                    product.setName(rs.getString("name"));
                    product.setDescription(rs.getString("description"));
                    product.setPrice(rs.getDouble("price"));
                    product.setSize(rs.getString("size"));
                    product.setQuantity(rs.getInt("quantity"));
                    product.setProductNumber(rs.getString("productNumber"));
                    product.setType(type);
                }
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
                case MOTORCYCLE:
                    query = "SELECT id FROM Motorcycle ORDER BY id";
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
    
