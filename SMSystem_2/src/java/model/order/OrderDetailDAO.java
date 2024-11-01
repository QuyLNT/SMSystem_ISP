/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.order;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.product.ProductDTO;
import utils.DBUtils;

/**
 *
 * @author LENOVO
 */
public class OrderDetailDAO {
   public static final String ORDER_DETAIL
            = "SELECT od.orderDetailId, od.productId, od.quantity, "
            + "p.price AS unitPrice, p.sale AS salePrice, p.name, "
            + "pi.imagePath AS avatarPath "
            + "FROM orderDetails od "
            + "JOIN products p ON od.productId = p.productId "
            + "LEFT JOIN productImages pi ON p.productId = pi.productId AND pi.isAvatar = 1 "
            + "WHERE od.orderId = ?";

    public List<OrderDetailDTO> getOrderDetailListByOrderID(int orderId) throws ClassNotFoundException, SQLException {
        List<OrderDetailDTO> orderDetailsList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ORDER_DETAIL);
                ptm.setInt(1, orderId);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int orderDetailId = rs.getInt("orderDetailId");
                    int quantity = rs.getInt("quantity");
                    float unitPrice = rs.getFloat("unitPrice");
                    
                    ProductDTO product = new ProductDTO();
                    product.setProductId(rs.getInt("productId"));
                    product.setName(rs.getString("name"));
                    product.setSale(rs.getFloat("salePrice"));
                    product.setAvatarPath(rs.getString("avatarPath"));
                    product.setPrice(unitPrice);
                    OrderDetailDTO orderDetail = new OrderDetailDTO(orderDetailId, product, orderId, quantity, unitPrice);
                    orderDetailsList.add(orderDetail);
                }
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return orderDetailsList;
    }
}
