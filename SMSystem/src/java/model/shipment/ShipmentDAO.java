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
}
