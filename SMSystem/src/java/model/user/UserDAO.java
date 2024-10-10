/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.user;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.naming.NamingException;
import utils.DBUtils;

/**
 *
 * @author LENOVO
 */
public class UserDAO {

    private static final String LOGIN = "SELECT userName,fullName,userId,phoneNumber,sex,email,isActive,roleId,createdAt\n"
            + "FROM users\n"
            + "WHERE (userName =? OR email=?) AND password = ?";
    private static final String GET_ALL_USER = "SELECT userId,userName, fullName,phoneNumber,sex,email,isActive, roleId,createdAt FROM users WHERE userName=? ";
    private static final String UPDATE = "UPDATE users SET  password=?,fullName= ?, phoneNumber=?, sex=?, email=? where userName=?";

    public UserDTO checkLogin(String userIndentify, String password) throws SQLException, ClassNotFoundException, NamingException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(LOGIN);
                ptm.setString(1, userIndentify);
                ptm.setString(2, userIndentify);
                ptm.setString(3, password);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int userId = rs.getInt("userId");
                    String userName = rs.getString("userName");
                    String fullName = rs.getString("fullName");
                    String phoneNumber = rs.getString("phoneNumber");
                    String sex = rs.getString("sex");
                    String email = rs.getString("email");
                    boolean isActive = rs.getBoolean("isActive");
                    String roleId = rs.getString("roleId");
                    Date createdAt = rs.getDate("createdAt");

                    user = new UserDTO(userId, fullName, userName, password, phoneNumber, sex, email, isActive, roleId, createdAt);
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
                rs.close();
            }
        }

        return user;
    }

    public List<UserDTO> getAllUser(String searchUser) throws ClassNotFoundException, SQLException {
        List<UserDTO> userList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ALL_USER);
                ptm.setString(1, "%" + searchUser + "%");
                rs = ptm.executeQuery();
            }
            while (rs.next()) {
                int userId = rs.getInt("userID");
                String fullName = rs.getString("fullName");
                String userName = rs.getString("userName");
                String userPass = "***";
                String phoneNumber = rs.getString("phoneNumber");
                String Sex = rs.getString("sex");
                String email = rs.getString("email");
                boolean isActive = rs.getBoolean("isActive");
                String roleId = rs.getString("roleId");
                java.sql.Date createdAt = rs.getDate("createdAt");
                UserDTO user = new UserDTO(userId, fullName, userName, userPass, phoneNumber, Sex, email, isActive, roleId, createdAt);
                userList.add(user);
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
        return userList;
    }

    public boolean userAfterUpdate(UserDTO user) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE);
                ptm.setString(1, user.getPassword());
                ptm.setString(2, user.getFullName());
                ptm.setString(3, user.getPhoneNumber());
                ptm.setString(4, user.getSex());
                ptm.setString(5, user.getEmail());
                ptm.setString(6, user.getUserName());
                check = ptm.executeUpdate() > 0 ? true : false;
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
            return check;
        }

    }
}
