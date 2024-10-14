/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import utils.DBUtils;

/**
 *
 * @author LENOVO
 */
public class CartDAO {

    private static final String DELETE_ITEM = "DELETE FROM cart_items WHERE cartItemId = ?";

    public boolean deleteCartItem(int cartItemId) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean result = false;
        
        try {
            conn = DBUtils.getConnection();
            if(conn != null){
                ptm = conn.prepareStatement(DELETE_ITEM);
                ptm.setInt(1, cartItemId);
                result = ptm.executeUpdate()>0;
                
            }
        }finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        return result;
    }

}
}
