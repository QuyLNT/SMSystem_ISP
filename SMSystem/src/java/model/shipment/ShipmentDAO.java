/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.shipment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author dell
 */
public class ShipmentDAO {

    private static final String GET_ALL_SHIPMENT
            = "SELECT s.shipmentId, s.orderId, s.shipperId, s.shippingMethodId, sm.methodName, s.shippedDate, s.estimatedArrival, s.shipmentStatus "
            + "FROM shipments s "
            + "JOIN shippingMethods sm ON s.shippingMethodId = sm.shippingMethodId";

    private static final String GET_SHIPMENTS_BY_SHIPPER
            = "SELECT s.shipmentId, s.orderId, s.shipperId, s.shippingMethodId, sm.methodName, s.shippedDate, s.estimatedArrival, s.shipmentStatus "
            + "FROM shipments s "
            + "JOIN shippingMethods sm ON s.shippingMethodId = sm.shippingMethodId "
            + "WHERE s.shipperId = ?";

    private static final String UPDATE_ESTIMATED_ARRIVAL = "UPDATE shipments SET estimatedArrival = ? WHERE shipmentId = ?";
    private static final String UPDATE_SHIPMENT_STATUS = "UPDATE shipments SET shipmentStatus = ? WHERE shipmentId = ?";
    private static final String GET_SHIP_BY_ORDERID
            = "SELECT s.shipmentId, s.orderId, s.shipperId, s.shippingMethodId, sm.methodName, s.shippedDate, s.estimatedArrival, s.shipmentStatus "
            + "FROM shipments s "
            + "JOIN shippingMethods sm ON s.shippingMethodId = sm.shippingMethodId "
            + "WHERE s.orderId = ?";

    private static final String GET_TODAY_SHIP_BY_SHIPPER
            = "SELECT s.shipmentId, s.orderId, s.shipperId, s.shippingMethodId, sm.methodName, s.shippedDate, s.estimatedArrival, s.shipmentStatus\n"
            + "            FROM shipments s\n"
            + "            JOIN shippingMethods sm ON s.shippingMethodId = sm.shippingMethodId \n"
            + "            WHERE CONVERT(DATE, shippedDate) = CONVERT(DATE, GETDATE())\n"
            + "            AND s.shipperId = ?";

    public List<ShipmentDTO> getAllShipments() throws SQLException, ClassNotFoundException {
        List<ShipmentDTO> shipments = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ALL_SHIPMENT);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    ShipmentDTO ship = new ShipmentDTO();
                    ship.setShipmentId(rs.getInt("shipmentId"));
                    ship.setOrderId(rs.getInt("orderId"));
                    ship.setShipperId(rs.getInt("shipperId"));
                    ship.setShippingMethodId(rs.getInt("shippingMethodId"));
                    ship.setMethodName(rs.getString("methodName"));
                    ship.setShippedDate(rs.getDate("shippedDate"));
                    ship.setShipmentStatus(rs.getString("shipmentStatus"));
                    ship.setEstimatedArrival(rs.getDate("estimatedArrival"));
                    shipments.add(ship);
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
        return shipments;
    }

    public List<ShipmentDTO> getShipmentsByShipper(int shipperId) throws SQLException, ClassNotFoundException {
        List<ShipmentDTO> shipments = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_SHIPMENTS_BY_SHIPPER);

                ptm.setInt(1, shipperId);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    ShipmentDTO ship = new ShipmentDTO();
                    ship.setShipmentId(rs.getInt("shipmentId"));
                    ship.setOrderId(rs.getInt("orderId"));
                    ship.setShipperId(rs.getInt("shipperId"));
                    ship.setShippingMethodId(rs.getInt("shippingMethodId"));
                    ship.setMethodName(rs.getString("methodName"));
                    ship.setShippedDate(rs.getDate("shippedDate"));
                    ship.setShipmentStatus(rs.getString("shipmentStatus"));
                    ship.setEstimatedArrival(rs.getDate("estimatedArrival"));
                    shipments.add(ship);
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
        return shipments;
    }

    public void updateEstimatedArrival(int shipmentId, String newEstimatedArrival) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_ESTIMATED_ARRIVAL);
                ptm.setString(1, newEstimatedArrival);
                ptm.setInt(2, shipmentId);
                ptm.executeUpdate();
            }
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void updateShipmentStatus(int shipmentId, String newStatus) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_SHIPMENT_STATUS);
                ptm.setString(1, newStatus);
                ptm.setInt(2, shipmentId);
                ptm.executeUpdate();
            }
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public ShipmentDTO getShipByOrderId(int orderId) throws ClassNotFoundException, SQLException {
        ShipmentDTO ship = new ShipmentDTO();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_SHIP_BY_ORDERID);
                ptm.setInt(1, orderId);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    ship.setShipmentId(rs.getInt("shipmentId"));
                    ship.setOrderId(rs.getInt("orderId"));
                    ship.setShipperId(rs.getInt("shipperId"));
                    ship.setShippingMethodId(rs.getInt("shippingMethodId"));
                    ship.setMethodName(rs.getString("methodName"));
                    ship.setShippedDate(rs.getDate("shippedDate"));
                    ship.setShipmentStatus(rs.getString("shipmentStatus"));
                    ship.setEstimatedArrival(rs.getDate("estimatedArrival"));
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
        return ship;
    }

    public List<ShipmentDTO> getToDayShip(int userId) throws ClassNotFoundException, SQLException {
        List<ShipmentDTO> shipments = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_TODAY_SHIP_BY_SHIPPER);

                ptm.setInt(1, userId);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    ShipmentDTO ship = new ShipmentDTO();
                    ship.setShipmentId(rs.getInt("shipmentId"));
                    ship.setOrderId(rs.getInt("orderId"));
                    ship.setShipperId(rs.getInt("shipperId"));
                    ship.setShippingMethodId(rs.getInt("shippingMethodId"));
                    ship.setMethodName(rs.getString("methodName"));
                    ship.setShippedDate(rs.getDate("shippedDate"));
                    ship.setShipmentStatus(rs.getString("shipmentStatus"));
                    ship.setEstimatedArrival(rs.getDate("estimatedArrival"));
                    shipments.add(ship);
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
        return shipments;
    }

    public List<ShipmentDTO> filter(int userid, String dateFilter) throws ClassNotFoundException, SQLException {
        List<ShipmentDTO> shipments = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        StringBuilder query = new StringBuilder("SELECT s.shipmentId, s.orderId, s.shipperId, s.shippingMethodId, sm.methodName, s.shippedDate, s.estimatedArrival, s.shipmentStatus\n"
                + "            FROM shipments s\n"
                + "            JOIN shippingMethods sm ON s.shippingMethodId = sm.shippingMethodId \n"
                + "            WHERE s.shipperId =? ");

        if (dateFilter != null && !dateFilter.isEmpty()) {
            switch (dateFilter) {
                case "Today":
                    query.append("AND s.shippedDate >= CAST(GETDATE() AS DATE) ");
                    break;
                case "This Week":
                    query.append("AND s.shippedDate >= DATEADD(DAY, -(DATEPART(WEEKDAY, GETDATE()) - 1), CAST(GETDATE() AS DATE)) ");
                    break;
                case "This Month":
                    query.append("AND s.shippedDate >= DATEADD(DAY, -(DAY(GETDATE()) - 1), CAST(GETDATE() AS DATE)) ");
                    break;
                default:
                    break;
            }
        }
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(query.toString());
                ptm.setInt(1, userid);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    ShipmentDTO ship = new ShipmentDTO();
                    ship.setShipmentId(rs.getInt("shipmentId"));
                    ship.setOrderId(rs.getInt("orderId"));
                    ship.setShipperId(rs.getInt("shipperId"));
                    ship.setShippingMethodId(rs.getInt("shippingMethodId"));
                    ship.setMethodName(rs.getString("methodName"));
                    ship.setShippedDate(rs.getDate("shippedDate"));
                    ship.setShipmentStatus(rs.getString("shipmentStatus"));
                    ship.setEstimatedArrival(rs.getDate("estimatedArrival"));
                    shipments.add(ship);
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
        return shipments;
    }

    public Date getDay(int shipmentId) throws SQLException, ClassNotFoundException {
        Date date = new Date();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement("SELECT estimatedArrival FROM shipments WHERE shipmentId = ?");

                ptm.setInt(1, shipmentId);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    
                    date = rs.getDate("estimatedArrival");
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
        return date;
    }
}
