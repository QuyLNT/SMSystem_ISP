/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DBUtils;

/**
 *
 * @author LENOVO
 */
public class CartDAO {

    private static final String DELETE_ITEM = "DELETE FROM cart_items WHERE cartItemId = ?";
    private static final String INSERT_CART = "INSERT INTO carts (customerId) VALUES (?)";
    private static final String INSERT_CART_ITEM = "INSERT INTO cart_items (cartId, productId, quantity) VALUES (?, ?, ?)";
    private static final String GET_CART_ID = "SELECT cartId FROM carts WHERE customerId = ?";

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

    public int createCart(int customerId) throws SQLException, ClassNotFoundException {
        int cartId = -1;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                // Kiểm tra nếu giỏ hàng của người dùng đã tồn tại chưa
                ptm = conn.prepareStatement(GET_CART_ID);
                ptm.setInt(1, customerId);
                rs = ptm.executeQuery();

                if (rs.next()) {
                    // Nếu giỏ hàng đã tồn tại, lấy cartId hiện tại
                    cartId = rs.getInt("cartId");
                } else {
                    // Nếu chưa tồn tại, tạo giỏ hàng mới
                    ptm = conn.prepareStatement(INSERT_CART, PreparedStatement.RETURN_GENERATED_KEYS);
                    ptm.setInt(1, customerId);
                    ptm.executeUpdate();
                    rs = ptm.getGeneratedKeys();
                    if (rs.next()) {
                        cartId = rs.getInt(1); // Lấy cartId tự sinh
                    }
                }
            }
        } finally {
            if(rs!=null) rs.close();
            if(ptm!=null) ptm.close();
            if(conn!=null) conn.close();
        }
        return cartId;
    }


    public void addCartItem(int cartId, int productId, int quantity, Float size) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT_CART_ITEM);
                ptm.setInt(1, cartId);
                ptm.setInt(2, productId);
                ptm.setInt(3, quantity);
                ptm.setDouble(4, size);
                ptm.executeUpdate();
            }
        } finally {
            if(ptm!=null) ptm.close();
            if(conn!=null) conn.close();
        }
    }
}
