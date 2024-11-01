/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author LENOVO
 */
public class OnlinePaymentDAO {

    private static final String INSERT = "INSERT INTO onlinePayments (userPaymentId, orderId, amount, description)\n"
            + "VALUES (?,?,?,?)";

    private static final String UPDATE_STATUS = "UPDATE onlinePayments SET status = ? WHERE userPaymentId = ?";
    private static final String GET_ALL = "SELECT userPaymentId,orderId,amount,description,status,createdAt,updatedAt FROM onlinePayments";
    private static final String SEARCH = "SELECT userPaymentId,orderId,amount,description,status,createdAt,updatedAt FROM onlinePayments WHERE orderId = ?";

    public boolean createPayment(OnlinePaymentDTO payment) throws ClassNotFoundException, SQLException {
        boolean checkInsert = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet generatedKeys = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT);
                ptm.setString(1, payment.getPaymentId());
                ptm.setInt(2, payment.getOrderId());
                ptm.setFloat(3, payment.getAmount());
                ptm.setString(4, payment.getDescription());
                checkInsert = ptm.executeUpdate() > 0;
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
        return checkInsert;
    }

    public boolean updatePayStatus(String appTransId, String status) throws ClassNotFoundException, SQLException {
        boolean checkUpdate = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_STATUS);
                ptm.setString(2, appTransId);
                ptm.setString(1, status);
                checkUpdate = ptm.executeUpdate() > 0;
            }
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return checkUpdate;
    }

    public List<OnlinePaymentDTO> searchPaymentByOrder(int orderId) throws ClassNotFoundException, SQLException {
        List<OnlinePaymentDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH);
                ptm.setInt(1, orderId);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String payId = rs.getString("userPaymentId");
                    int orderID = rs.getInt("orderId");
                    float amount = rs.getFloat("amount");
                    String description = rs.getString("description");
                    String status = rs.getString("status");
                    Timestamp createdAtTimestamp = rs.getTimestamp("createdAt");
                    Timestamp updatedAtTimestamp = rs.getTimestamp("updatedAt");

                    LocalDateTime createdAt = createdAtTimestamp != null ? createdAtTimestamp.toLocalDateTime() : null;
                    LocalDateTime updatedAt = updatedAtTimestamp != null ? updatedAtTimestamp.toLocalDateTime() : null;

                    OnlinePaymentDTO pay = new OnlinePaymentDTO(payId, orderID, description, status, amount, createdAt, updatedAt);
                    list.add(pay);
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
        return list;
    }

    public List<OnlinePaymentDTO> getAll() throws ClassNotFoundException, SQLException {
        List<OnlinePaymentDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ALL);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String payId = rs.getString("userPaymentId");
                    int orderId = rs.getInt("orderId");
                    float amount = rs.getFloat("amount");
                    String description = rs.getString("description");
                    String status = rs.getString("status");
                    Timestamp createdAtTimestamp = rs.getTimestamp("createdAt");
                    Timestamp updatedAtTimestamp = rs.getTimestamp("updatedAt");

                    LocalDateTime createdAt = createdAtTimestamp != null ? createdAtTimestamp.toLocalDateTime() : null;
                    LocalDateTime updatedAt = updatedAtTimestamp != null ? updatedAtTimestamp.toLocalDateTime() : null;

                    OnlinePaymentDTO pay = new OnlinePaymentDTO(payId, orderId, description, status, amount, createdAt, updatedAt);
                    list.add(pay);
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
        return list;
    }

}
