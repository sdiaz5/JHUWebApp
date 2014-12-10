/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;


import emedina.resultBeans.*;
import java.sql.*;
/**
 *
 * @author mark
 */
public class CreditCardDB {
    
    public static int insert(CreditCard card){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "INSERT INTO JHUAppDB.CreditCard (userId, creditCardNumber, cardType, expMonth, expYear) "
                + "VALUES (?, ?, ?, ?, ?)";
        
        try{
            ps = connection.prepareStatement(query);
            ps.setInt(1, card.getUserId());
            ps.setString(2, card.getCreditCardNumber());
            ps.setString(3, card.getCardType());
            ps.setString(4, card.getExpMonth());
            ps.setString(5, card.getExpYear());
            return ps.executeUpdate();
            
        }catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement (ps);
            pool.freeConnection(connection);
        }
    }
}
