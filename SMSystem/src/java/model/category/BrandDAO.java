/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.category;

import java.sql.Connection;
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
public class BrandDAO {

    private static final String GET_BRAND_BY_ID = "SELECT brandId,brandName\n"
            + "FROM brands\n"
            + "WHERE brandId = ?";
    private static final String GET_ALL = "SELECT brandId,brandName\n"
            + "FROM brands";

    private static final String GET_BRAND_BY_NAME = "SELECT brandId,brandName\n"
            + "FROM brands\n"
            + "WHERE brandName LIKE ?";
    private static final String GET_NUMBER_OF_PRODUCT = "SELECT COUNT(productId) AS numberOfProduct\n"
            + "FROM products\n"
            + "WHERE brandId=?\n"
            + "GROUP BY brandId";
    private static final String INSERT_BRAND = "INSERT INTO brands(brandName)\n"
            + "VALUES (?)";

    public List<BrandDTO> getAllBrand() throws ClassNotFoundException, SQLException {
        List<BrandDTO> listBrand = new ArrayList();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ALL);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    BrandDTO brand = new BrandDTO(rs.getInt("brandId"), rs.getString("brandName"));
                    listBrand.add(brand);
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
        return listBrand;
    }

    public BrandDTO getBrandById(int brandId) throws SQLException, ClassNotFoundException {
        BrandDTO brand = new BrandDTO();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_BRAND_BY_ID);
                ptm.setInt(1, brandId);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    brand = new BrandDTO(brandId, rs.getString("brandName"));
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
        return brand;
    }

    public List<BrandDTO> getBrandByName(String searchTerm) throws ClassNotFoundException, SQLException {
        List<BrandDTO> brandList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_BRAND_BY_NAME);
                ptm.setString(1, "%" + searchTerm + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    BrandDTO brand = new BrandDTO(
                            rs.getInt("brandId"),
                            rs.getString("brandName"));
                    brandList.add(brand);
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
        return brandList;
    }

    public int getProductCountByBrand(int brandId) throws ClassNotFoundException, SQLException {
        int numberOfProduct = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_NUMBER_OF_PRODUCT);
                ptm.setInt(1, brandId);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    numberOfProduct = rs.getInt("numberOfProduct");
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
        return numberOfProduct;
    }

    public BrandDTO insertNewBrand(String brandName) throws SQLException, ClassNotFoundException {
        BrandDTO brand = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT_BRAND, PreparedStatement.RETURN_GENERATED_KEYS);
                ptm.setString(1, brandName);
                int rowsInserted = ptm.executeUpdate();
                if (rowsInserted > 0) {
                    rs = ptm.getGeneratedKeys();
                    if (rs.next()) {
                        int brandId = rs.getInt(1);
                        brand = new BrandDTO(brandId,
                                brandName);
                    }

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
        return brand;
    }

    private static final String DISABLE_FOREIGN_KEY = "ALTER TABLE products NOCHECK CONSTRAINT ALL;";
    private static final String DELETE_BRAND = "DELETE FROM brands WHERE brandId = ?";
    private static final String ENABLE_FOREIGN_KEY = "ALTER TABLE products WITH CHECK CHECK CONSTRAINT ALL;";

    public boolean deleteBrand(int brandId) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean result = false;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                // Disable foreign key checks
                ptm = conn.prepareStatement(DISABLE_FOREIGN_KEY);
                ptm.executeUpdate();
                ptm.close(); // Close the first statement

                // Delete brand
                ptm = conn.prepareStatement(DELETE_BRAND);
                ptm.setInt(1, brandId);
                ptm.executeUpdate();
                ptm.close(); // Close the second statement

                // Enable foreign key checks
                ptm = conn.prepareStatement(ENABLE_FOREIGN_KEY);
                ptm.executeUpdate();

                result = true; // If all operations are successful
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
