/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author LENOVO
 */
public class ProductImageDAO {

    private static final String GET_PRODUCT_IMAGE = "SELECT imageId,imagePath,isAvatar\n"
            + "FROM productImages\n"
            + "WHERE productId = ?";
 private static final String GET_DETAIL_IMAGE = "SELECT imageId,imagePath,isAvatar\n"
            + "FROM productImages\n"
            + "WHERE productId = ? AND isAvatar = 0";
    private static final String UPDATE_AVATAR = "UPDATE productImages "
            + "SET imagePath=? "
            + "WHERE productId=? AND isAvatar =1";

    private static final String INSERT_IMAGE = "INSERT INTO productImages(productId,imagePath,isAvatar) VALUES (?, ?, ?)";
    private static final String UPDATE_DETAIL_IMAGE = "UPDATE productImages SET imagePath = ? WHERE imageId = ? AND isAvatar = 0";

    public List<ProductImageDTO> getImageByProduct(int productId) throws ClassNotFoundException, SQLException {
        List<ProductImageDTO> listImage = new ArrayList();
        ProductDTO product = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_PRODUCT_IMAGE);
                ptm.setInt(1, productId);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int imageId = rs.getInt("imageId");
                    String path = rs.getString("imagePath");
                    boolean isAvatar = rs.getBoolean("isAvatar");
                    ProductImageDTO image = new ProductImageDTO(imageId, productId, path, isAvatar);
                    listImage.add(image);
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

        return listImage;
    }
    
    public List<ProductImageDTO> getImageDetail(int productId) throws ClassNotFoundException, SQLException {
        List<ProductImageDTO> listImage = new ArrayList();
        ProductDTO product = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_DETAIL_IMAGE);
                ptm.setInt(1, productId);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int imageId = rs.getInt("imageId");
                    String path = rs.getString("imagePath");
                    boolean isAvatar = rs.getBoolean("isAvatar");
                    ProductImageDTO image = new ProductImageDTO(imageId, productId, path, isAvatar);
                    listImage.add(image);
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

        return listImage;
    }

    public boolean updateAvatar(ProductImageDTO avatar) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean result = false;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_AVATAR);
                ptm.setString(1, avatar.getImagePath());
                ptm.setInt(2, avatar.getProductId());
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

    public boolean addProductImage(int productId, String imageUrl, boolean isAvatar) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean result = false;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT_IMAGE);
                ptm.setInt(1, productId);
                ptm.setString(2, imageUrl);
                ptm.setBoolean(3, isAvatar);
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

    public boolean updateDetailImages(List<ProductImageDTO> productImage) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean result = false;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_DETAIL_IMAGE);
                for (ProductImageDTO image : productImage) {
                    ptm.setString(1, image.getImagePath());
                    ptm.setInt(2, image.getImageId());
                    ptm.addBatch();
                }
                int[] updateCounts = ptm.executeBatch();
                result = Arrays.stream(updateCounts).allMatch(count -> count >= 0);
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

    public boolean isAvatar(int imageId) throws SQLException, ClassNotFoundException {
    boolean isAvatar = false;
    Connection conn = null;
    PreparedStatement ptm = null;
    ResultSet rs = null;
    try {
        conn = DBUtils.getConnection();
        if (conn != null) {
            String query = "SELECT isAvatar FROM productImages WHERE imageId = ?";
            ptm = conn.prepareStatement(query);
            ptm.setInt(1, imageId);
            rs = ptm.executeQuery();
            if (rs.next()) {
                isAvatar = rs.getBoolean("isAvatar");
            }
        }
    } finally {
        if (rs != null) rs.close();
        if (ptm != null) ptm.close();
        if (conn != null) conn.close();
    }
    return isAvatar;
}
    
    
    
    private static final String DELETE_PRODUCT_IMAGES = "DELETE FROM productImages WHERE productId = ?";

    public boolean deleteProductImages(int productId) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean result = false;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_PRODUCT_IMAGES);
                ptm.setInt(1, productId);
                result = ptm.executeUpdate() > 0 ? true : false;
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
