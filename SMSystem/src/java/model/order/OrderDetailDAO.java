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
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.product.ProductDTO;
import model.product.ProductVariantDTO;
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

    public static final String WARRANTY
            = "SELECT im.imagePath, p.name, o.orderId, s.estimatedArrival, p.warrantPeriod\n"
            + "            FROM orderDetails od\n"
            + "            JOIN products p ON p.productId = od.productId\n"
            + "			JOIN productImages im ON p.productId = im.productId\n"
            + "            JOIN orders o ON o.orderId = od.orderId\n"
            + "            JOIN shipments s ON s.orderId = o.orderId\n"
            + "            WHERE o.customerId = (SELECT u.userId FROM users u WHERE u.phoneNumber = ?)\n"
            + "			AND im.isAvatar = 1;";

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
                check = ptm.executeUpdate() > 0;
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

    public List<Map<String, Object>> checkWarrant(String phoneNumber) throws ClassNotFoundException, SQLException {
        List<Map<String, Object>> result = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(WARRANTY);
                ptm.setString(1, phoneNumber);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    Map<String, Object> record = new HashMap<>();
                    record.put("productImage", rs.getString("imagePath"));
                    record.put("name", rs.getString("name"));
                    record.put("orderId", rs.getInt("orderId"));
                    Timestamp time = rs.getTimestamp("estimatedArrival");
                    LocalDateTime estimatedArrival = time != null ? time.toLocalDateTime() : null;
                    record.put("estimatedArrival", estimatedArrival);
                    record.put("warrantPeriod", rs.getInt("warrantPeriod"));
                    result.add(record);
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
        return result;
    }

}
