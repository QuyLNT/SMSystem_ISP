/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DBUtils;

/**
 *
 * @author LENOVO
 */
public class OrderDetailDAO {

    String INSERT_ORDER_DETAIL = "INSERT INTO orderDetails (orderId, productId, quantity, size) VALUES (?, ?, ?, ?)";

    public boolean insertOrderDetail(int pId, int insertedOrderId, int quantity, float size) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet generatedKeys = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT_ORDER_DETAIL);
                ptm.setInt(1, insertedOrderId);
                ptm.setInt(2, pId);
                ptm.setInt(3, quantity);
                ptm.setFloat(4, size);
                check = ptm.executeUpdate()>0;
            }
        } finally {
            if (generatedKeys != null) {
                generatedKeys.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

}
