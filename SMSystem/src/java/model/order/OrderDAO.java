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
public class OrderDAO {

    private static final String INSERT_ORDER = "INSERT INTO orders (customerId, street, district, city, discountId, paymentMethodId, shippingMethodId) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?)";

    public int createOrder(int userId, String street, String district, String city, Integer discount, int payMethod, int shipMethod) throws SQLException, ClassNotFoundException {
        int orderId = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet generatedKeys = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT_ORDER, PreparedStatement.RETURN_GENERATED_KEYS);
                ptm.setInt(1, userId);
                ptm.setString(2, street);
                ptm.setString(3, district);
                ptm.setString(4, city);
                if(discount==null){
                    ptm.setNull(5, java.sql.Types.INTEGER);
                }else{
                ptm.setInt(5, discount);
                }
                ptm.setInt(6, payMethod);
                ptm.setInt(7, shipMethod);

                int rowsInserted = ptm.executeUpdate();

                if (rowsInserted > 0) {
                    generatedKeys = ptm.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        orderId = generatedKeys.getInt(1);
                    }
                }
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
        return orderId;
    }

}
