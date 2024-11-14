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
import javax.naming.NamingException;
import utils.DBUtils;

/**
 *
 * @author LENOVO
 */
public class UserDAO {

    private static final String CHECK_USERNAME_EXISTS = "SELECT COUNT(userId) FROM users WHERE userName = ?";
    private static final String CHECK_PHONE_EXISTS = "SELECT COUNT(userId) FROM users WHERE phoneNumber = ?";
    private static final String CHECK_EMAIL_EXISTS = "SELECT COUNT(userId) FROM users WHERE email = ?";

    private static final String INSERT_USER = " INSERT INTO users (userName, fullName, password, phoneNumber, sex, email, roleId) VALUES(?, ?, ?, ?, ?, ?, ?)";

    private static final String LOGIN = "SELECT userName,fullName,userId,phoneNumber,sex,email,isActive,roleId,createdAt\n"
            + "FROM users\n"
            + "WHERE (userName =? OR email=?) AND password = ? AND isActive = 1";
    private static final String GET_ALL_USER = "SELECT userId,userName, fullName,phoneNumber,sex,email,isActive, roleId,createdAt FROM users WHERE userName LIKE ? ";
    private static final String UPDATE = "UPDATE users SET  password=?,fullName= ?, phoneNumber=?, sex=?, email=? where userName=?";
    private static final String GET_TOTAL_ACCOUNT = "SELECT COUNT(userId) AS numberOfAccount\n"
            + "FROM users";
    private static final String GET_NUMBER_OF_ACCOUNT = "SELECT COUNT(userId) AS numberOfAccount\n"
            + "FROM users\n"
            + "WHERE roleId LIKE ?";
    private static final String SET_ROLE_ID = "UPDATE users\n"
            + "SET roleId = ?\n"
            + "WHERE userId = ?";
    private static final String DELETE = "DELETE users WHERE userId = ?";
    private static final String GET_USER = "SELECT userId,userName, fullName,phoneNumber,sex,email,isActive, roleId,createdAt FROM users WHERE userId = ? ";
    private static final String GET_USER_NAME = "SELECT userName FROM users WHERE userId = ? ";
    private static final String GET_EMAIL = "SELECT email FROM users WHERE email = ?";
    private static final String UPDATE_PASSWORD = "UPDATE users SET password = ? WHERE email = ?";
    private static final String GET_SHIPPER = "SELECT userId, fullName, userName, phoneNumber, email, roleId FROM users WHERE roleId = 'SP'";
    private static final String IS_PHONE_EXISTS = "SELECT * FROM users WHERE phoneNumber = ?";

    private static final String SET_STATUS = "UPDATE users\n"
            + "SET isActive = ?\n"
            + "WHERE userId = ?";

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
                int userId = rs.getInt("userId");
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

    public boolean userAfterUpdate(UserDTO user, String newPassword) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String passwordToUpdate = (newPassword != null && !newPassword.isEmpty()) ? newPassword : user.getPassword();
                ptm = conn.prepareStatement(UPDATE);
                ptm.setString(1, passwordToUpdate);
                ptm.setString(2, user.getFullName());
                ptm.setString(3, user.getPhoneNumber());
                ptm.setString(4, user.getSex());
                ptm.setString(5, user.getEmail());
                ptm.setString(6, user.getUserName());
                check = ptm.executeUpdate() > 0;
            }
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }

        }
        return check;
    }

    public int getTotalAccount() throws SQLException, ClassNotFoundException {
        int result = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_TOTAL_ACCOUNT);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    result = rs.getInt("numberOfAccount");
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

    public int getNumberOf(String roleId) throws SQLException, ClassNotFoundException {
        int result = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_NUMBER_OF_ACCOUNT);
                ptm.setString(1, roleId);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    result = rs.getInt("numberOfAccount");
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

    public boolean setRoleId(int userId, String roleId) throws ClassNotFoundException, SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SET_ROLE_ID);
                ptm.setString(1, roleId);
                ptm.setInt(2, userId);
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

    public boolean isUserNameExists(String userName) throws ClassNotFoundException, SQLException {
        boolean checkExits = true;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(CHECK_USERNAME_EXISTS);
            ptm.setString(1, userName);
            if (ptm.executeUpdate() > 0) {
                checkExits = false;
            }

        } finally {
            if (ptm != null) {
                conn.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return checkExits;
    }

    public boolean createUser(UserDTO user) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            //userName,fullName,password,phoneNumber,sex,email,isActive,roleId,createdAt)
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT_USER);
                ptm.setString(1, user.getUserName());
                ptm.setString(2, user.getFullName());
                ptm.setString(3, user.getPassword());
                ptm.setString(4, user.getPhoneNumber());
                ptm.setString(5, user.getSex());
                ptm.setString(6, user.getEmail());
                ptm.setString(7, user.getRoleId());
                check = ptm.executeUpdate() > 0;
            }
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean delete(int userId) throws ClassNotFoundException, SQLException {
        boolean checkDelete = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE);
                ptm.setInt(1, userId);
                checkDelete = ptm.executeUpdate() > 0;
            }
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }

        }
        return checkDelete;
    }

    public boolean isPhoneExists(String phoneNumber) throws ClassNotFoundException, SQLException {
        boolean checkExits = true;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(CHECK_PHONE_EXISTS);
            ptm.setString(1, phoneNumber);
            if (ptm.executeUpdate() > 0) {
                checkExits = false;
            }

        } finally {
            if (ptm != null) {
                conn.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return checkExits;
    }

    public boolean isEmailExists(String email) throws ClassNotFoundException, SQLException {
        boolean checkExits = true;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(CHECK_EMAIL_EXISTS);
            ptm.setString(1, email);
            if (ptm.executeUpdate() > 0) {
                checkExits = false;
            }

        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return checkExits;
    }

    public UserDTO getUserById(int userId) throws ClassNotFoundException, SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_USER);
                ptm.setInt(1, userId);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String fullName = rs.getString("fullName");
                    String userName = rs.getString("userName");
                    String password = "***";
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
                conn.close();
            }
        }

        return user;
    }

    public UserDTO getUserByName(int userId) throws ClassNotFoundException, SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_USER_NAME);
                ptm.setInt(1, userId);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String userName = rs.getString("userName"); // Lấy tên người dùng từ ResultSet
                    user = new UserDTO(userId, userName); // Khởi tạo UserDTO với userId và userName
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
        return user;
    }

    public boolean isEmailRegistered(String email) throws SQLException, ClassNotFoundException {
        boolean isRegistered = false;

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ptm = conn != null ? conn.prepareStatement(GET_EMAIL) : null) {
            if (ptm != null) {
                ptm.setString(1, email);
                try (ResultSet rs = ptm.executeQuery()) {
                    if (rs.next()) {
                        isRegistered = true;
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
        return isRegistered;
    }

    public boolean updatePasswordByEmail(String email, String newPassword) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean isUpdated = false;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_PASSWORD);
                ptm.setString(1, newPassword);
                ptm.setString(2, email);
                isUpdated = ptm.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
            return isUpdated;
        }
    }

    public String getUserNameByEmail(String email) throws SQLException, ClassNotFoundException {
        String fullName = null;
        String query = "SELECT fullName FROM users WHERE email = ?";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ptm = conn.prepareStatement(query)) {
            ptm.setString(1, email);
            ResultSet rs = ptm.executeQuery();
            if (rs.next()) {
                fullName = rs.getString("fullName");
            }
        }
        return fullName;
    }

    public List<UserDTO> getAllShippers() throws ClassNotFoundException, SQLException {
        List<UserDTO> shippers = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_SHIPPER);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    UserDTO shipper = new UserDTO();
                    shipper.setUserId(rs.getInt("userId"));
                    shipper.setFullName(rs.getString("fullName"));
                    shipper.setUserName(rs.getString("userName"));
                    shipper.setPhoneNumber(rs.getString("phoneNumber"));
                    shipper.setEmail(rs.getString("email"));
                    shipper.setRoleId(rs.getString("roleId"));
                    shippers.add(shipper);
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
        return shippers;
    }

    public boolean checkPhoneExists(String phoneNumber) throws ClassNotFoundException, SQLException {
        boolean checkExits = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(IS_PHONE_EXISTS);
                ptm.setString(1, phoneNumber);
                rs = ptm.executeQuery();
                {
                    if (rs.next()) {
                        checkExits = true;
                    }
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                conn.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return checkExits;
    }

    public boolean changePassword(String userName, String newPassword) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean changePassword = false;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "UPDATE users SET password = ? WHERE userName = ?";
                ptm = conn.prepareStatement(sql);
                ptm.setString(1, newPassword);
                ptm.setString(2, userName);

                int rowsUpdated = ptm.executeUpdate();
                if (rowsUpdated > 0) {
                    changePassword = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return changePassword;
    }

    public boolean setStatus(int userId, boolean status) throws ClassNotFoundException, SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SET_STATUS);
                ptm.setBoolean(1, status);
                ptm.setInt(2, userId);
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
}
