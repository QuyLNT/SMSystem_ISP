/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.discount;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author LENOVO
 */
public class DiscountDAO {

    private static final String GET_ALL = "SELECT discountId,discountCode,detail,discountAmount,startDay,endDay,usageLimit,usage,discountStatus\n"
            + "                                          FROM discountCodes";
    private static final String UPDATE_STATUS = "UPDATE discountCodes\n"
            + "SET discountStatus=?\n"
            + "WHERE discountId=?";
    public static final String ADD_DISCOUNT = "INSERT INTO discountCodes(discountCode, detail, discountAmount, startDay, endDay, usageLimit,usage,discountStatus) VALUES (?,?,?,?,?,?,?,?)";
    public static final String REMOVE_DISCOUNT = "DELETE FROM discountCodes WHERE discountId =?";
    private static final String GET_DISCOUNT = "SELECT discountId,discountCode,detail,discountAmount,startDay,endDay,usageLimit,usage,discountStatus\n"
            + "                                          FROM discountCodes WHERE discountCode LIKE ? ";

    public List<DiscountDTO> getALlDiscount() throws SQLException, ClassNotFoundException {
        List<DiscountDTO> discountList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ALL);
                rs = ptm.executeQuery();
                while (rs.next()) {

                    DiscountDTO discount = new DiscountDTO(rs.getInt("discountId"),
                            rs.getString("discountCode"),
                            rs.getString("detail"),
                            rs.getFloat("discountAmount"),
                            rs.getDate("startDay"),
                            rs.getDate("endDay"),
                            rs.getInt("usageLimit"),
                            rs.getInt("usage"),
                            rs.getString("discountStatus"));

                    discountList.add(discount);
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
        return discountList;
    }

    public boolean updateStatus(int discountId, String status) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean result = false;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_STATUS);
                ptm.setString(1, status);
                ptm.setInt(2, discountId);
                result = ptm.executeUpdate() > 0;
            }
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return result;

    }

    public DiscountDTO ApplyCode(String discountCode) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DiscountDTO discount = null;

        try {
            con = DBUtils.getConnection();
            String sql = "SELECT discountId, discountCode, detail, discountAmount, startDay, endDay, usageLimit, usage, discountStatus "
                    + "FROM discountCodes "
                    + "WHERE discountCode = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, discountCode);
            rs = ps.executeQuery();

            if (rs.next()) {
                discount = new DiscountDTO(
                        rs.getInt("discountId"),
                        rs.getString("discountCode"),
                        rs.getString("detail"),
                        rs.getFloat("discountAmount"),
                        rs.getDate("startDay"),
                        rs.getDate("endDay"),
                        rs.getInt("usageLimit"),
                        rs.getInt("usage"),
                        rs.getString("discountStatus")
                );
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return discount;
    }

    public void updateUsage(int dicountId) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBUtils.getConnection();
            String sql = "UPDATE discountCodes "
                    + "SET usage = usage + 1 "
                    + "WHERE discountId = ? AND usageLimit > usage";
            ps = con.prepareStatement(sql);
            ps.setInt(1, dicountId);
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public DiscountDTO addDiscount(DiscountDTO discount) throws ClassNotFoundException, SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet generatedKeys = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ADD_DISCOUNT, PreparedStatement.RETURN_GENERATED_KEYS);
                ptm.setString(1, discount.getDiscountCode());
                ptm.setString(2, discount.getDetail());
                ptm.setFloat(3, discount.getDiscountAmount());
                ptm.setDate(4, (Date) discount.getStartDay());
                ptm.setDate(5, (Date) discount.getEndDay());
                ptm.setInt(6, discount.getUsageLimit());
                ptm.setInt(7, discount.getUsed());
                ptm.setString(8, discount.getStatus());

                int rowsInserted = ptm.executeUpdate();

                if (rowsInserted > 0) {
                    generatedKeys = ptm.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int discountId = generatedKeys.getInt(1);
                        discount.setDiscountId(discountId);
                        check = true;
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
        return discount;
    }

    public boolean deleteDiscount(int discountId) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean result = false;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(REMOVE_DISCOUNT);
                ptm.setInt(1, discountId);
                result = ptm.executeUpdate() > 0;

            }
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }

    public DiscountDTO getDiscountByCode(String discountCode) throws ClassNotFoundException, SQLException {
        DiscountDTO discount = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_DISCOUNT);
                ptm.setString(1, discountCode);
                rs = ptm.executeQuery();
                if (rs.next()) {

                    discount = new DiscountDTO(rs.getInt("discountId"),
                            rs.getString("discountCode"),
                            rs.getString("detail"),
                            rs.getFloat("discountAmount"),
                            rs.getDate("startDay"),
                            rs.getDate("endDay"),
                            rs.getInt("usageLimit"),
                            rs.getInt("usage"),
                            rs.getString("discountStatus"));

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
        return discount;
    }

}
