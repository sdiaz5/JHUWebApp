/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;
import java.sql.*;
import java.util.ArrayList;
import emedina.resultBeans.Product;


/**
 *
 * @author mark
 */
public class ProductDB {
    
    public static int insertMotorcycle(Motorcycle motorcycle) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query = "INSERT INTO Motorcycle (name, description, rentalPrice, condition, miles, brand, type) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, motorcycle.getName());
            ps.setString(2, motorcycle.getDescription());
            ps.setDouble(3, motorcycle.getRentalPrice());
            ps.setString(4, motorcycle.getCondition());
            ps.setInt(5, motorcycle.getMiles());
            ps.setString(6, motorcycle.getBrand());
            ps.setString(7, motorcycle.getType());
            return ps.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static int updateMotorcycle(Motorcycle motorcycle) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query = "UPDATE Motorcycle SET "
                + "rentalPrice = ?, "
                + "condition = ?, "
                + "miles = ? "
                + "WHERE name = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setDouble(1, motorcycle.getRentalPrice());
            ps.setString(2, motorcycle.getCondition());
            ps.setInt(3, motorcycle.getMiles());
            
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
                motorcycle.setRentalPrice(rs.getDouble("rentalPrice"));
                motorcycle.setCondition(rs.getString("condition"));
                motorcycle.setMiles(rs.getInt("miles"));
                motorcycle.setBrand(rs.getString("brand"));
                motorcycle.setType(rs.getString("type"));
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
    
    public static int setMotorcycleOrderId (Motorcycle motorcycle, int Order_id){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query = "UPDATE Motorcycle SET "
                + "Order_id = ? "
                + "WHERE name = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, Order_id);
            ps.setString(2, motorcycle.getName());
            
            return ps.executeUpdate();
        } catch (SQLException e){
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static int insertRented(int motorcycleId, Date dateBorrowed, Date dateReturned) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query = "INSERT INTO RentedDate (dateBorrowed, dateReturned, motorcycle_id) "
                + "VALUES (?, ?, ?)";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setDate(1, dateBorrowed);
            ps.setDate(2, dateReturned);
            ps.setInt(3, motorcycleId);
            return ps.executeUpdate();
        } catch(SQLException e){
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static int updateRented(int motorcycleId, Date dateBorrowed, Date dateReturned){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query = "UPDATE RentedDate SET "
                + "dateBorrowed = ?, "
                + "dateReturned = ? "
                + "WHERE motorcycle_id = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setDate(1, dateBorrowed);
            ps.setDate(2, dateReturned);
            ps.setInt(3, motorcycleId);
            
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    //FIX THIS PART NEEDS SOME TYPE OF JOIN!!!!!!!
    public static int deleteRented(int motorcycleId, int orderId){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query = "DELETE FROM RentedDate "
                + "WHERE"
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
                query = "INSERT INTO Jacket (name, description, rentalPrice, size) "
                    + "VALUES (?, ?, ?, ?)";
                break;
            case HELMET:
                query = "INSERT INTO Helmet (name, description, rentalPrice, size) "
                    + "VALUES (?, ?, ?, ?)";
                break;
            case GLOVE:
                query = "INSERT INTO Gloves (name, description, rentalPrice, size) "
                    + "VALUES (?, ?, ?, ?)";
                break;
        }
       
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getPrice());
            ps.setString(4, product.getSize());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement (ps);
            pool.freeConnection(connection);
        }
    }
    
    public static int updateProduct(Product product) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        Product.Type productType = product.getType();
        String query = null;
        switch(productType){
            case JACKET:
                query = "UPDATE Jacket SET "
                        + "rentalPrice = ? "
                        + "WHERE name = ?";
                break;
            case HELMET:
                query = "UPDATE Helmet SET "
                        + "rentalPrice = ? "
                        + "WHERE name = ?";
                break;
            case GLOVE:
                query = "UPDATE Gloves SET "
                        + "rentalPrice = ? "
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
                product.setPrice(rs.getDouble("rentalPrice"));
                product.setSize(rs.getString("size"));
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
        
        public static int setProductOrderId(Product product, int Order_id){
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            PreparedStatement ps = null;
            
            Product.Type productType = product.getType();
            String query = null;
            switch(productType){
                case JACKET:
                    query = "UPDATE Jacket SET "
                        + "Order_id = ? "
                        + "WHERE name = ?";
                    break;
                case HELMET:
                    query = "UPDATE Helmet SET "
                        + "Order_id = ? "
                        + "WHERE name = ?";
                    break;
                case GLOVE:
                    query = "UPDATE Gloves SET "
                        + "Order_id = ? "
                        + "WHERE name = ?";
                    break;
            }
            
            try {
                ps = connection.prepareStatement(query);
                ps.setInt(1, Order_id);
                ps.setString(2, product.getName());
                
                return ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e);
                return 0;
            } finally {
                DBUtil.closePreparedStatement(ps);
                pool.freeConnection(connection);
            }
        }
    }
    
