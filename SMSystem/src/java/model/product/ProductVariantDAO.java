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
import model.cart.CartDAO;
import utils.DBUtils;
import model.cart.CartItems;

/**
 *
 * @author LENOVO
 */
public class ProductVariantDAO {

    private static final String GET_ALL = "SELECT variantId,productId,size,stock FROM productVariants";
    private static final String GET_STOCK_BY_PRODUCT = "SELECT productId, SUM(stock) AS sumOfProduct\n"
            + "FROM productVariants\n"
            + "GROUP BY productId";
    private static final String GET_AVALABLE_SIZE_BY_PRODUCT = "SELECT size FROM productVariants WHERE productId = ? AND stock>0";
    private static final String GET_ALL_SIZE_BY_PRODUCT = "SELECT size FROM productVariants WHERE productId = ?";
    private static final String VARIANTS_BY_PRODUCTID = "SELECT variantId, productId, size, stock FROM productVariants WHERE productId = ?";
    private static final String GET_AVALABLE_SIZE = "SELECT stock FROM ProductSize WHERE productId = ? AND size = ?";
    private static final String UPDATE_STOCK = "UPDATE productVariants\n"
            + "SET stock = stock - ?\n"
            + "WHERE productId = ? AND size = ?";
    private static final String CHECK_VALIDATE = "SELECT *\n"
            + "FROM productVariants\n"
            + "WHERE productId = ? AND size = ? AND stock >= ?";
    private static final String GET_STATUS = "SELECT status FROM productVariants WHERE productId = ? AND size = ?";
    private static final String CREATE_SIZE = "INSERT INTO productVariants(productId,size,stock) VALUES (?,?,?)";
    private static final String CHECK_EXISTED_SIZE = "SELECT variantId,productId,size,stock FROM productVariants WHERE productId = ? AND size = ?";
    private static final String UPDATE_STOCK_QUANTITY = "UPDATE productVariants SET stock = ? WHERE variantId = ?";

    public List<ProductVariantDTO> getAllVariant() throws SQLException, ClassNotFoundException {
        ProductVariantDTO variant;
        List<ProductVariantDTO> allListProduct = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ALL);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    variant = new ProductVariantDTO(rs.getInt("variantId"),
                            rs.getInt("productid"),
                            rs.getFloat("size"),
                            rs.getInt("stock"));
                    allListProduct.add(variant);
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
        return allListProduct;
    }

    public List<ProductDTO> getStockByProduct() throws SQLException, ClassNotFoundException {
        ProductDTO product;
        List<ProductDTO> stockByProduct = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_STOCK_BY_PRODUCT);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    product = new ProductDTO(rs.getInt("productId"),
                            rs.getInt("sumOfProduct"));
                    stockByProduct.add(product);
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
        return stockByProduct;
    }

    public List<Float> getAvailableSize(int productId) throws SQLException, ClassNotFoundException {
        List<Float> sizeList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_AVALABLE_SIZE_BY_PRODUCT);
                ptm.setInt(1, productId);

                rs = ptm.executeQuery();
                while (rs.next()) {
                    Float size = rs.getFloat("size");
                    sizeList.add(size);
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
        return sizeList;
    }

    public List<Float> getAllSize(int productId) throws SQLException, ClassNotFoundException {
        List<Float> sizeList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ALL_SIZE_BY_PRODUCT);
                ptm.setInt(1, productId);
                rs = ptm.executeQuery();

                while (rs.next()) {
                    Float size = rs.getFloat("size");
                    sizeList.add(size);
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
        return sizeList;
    }

    public List<ProductVariantDTO> getVariantByProduct(int productId) throws ClassNotFoundException, SQLException {
        List<ProductVariantDTO> variants = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(VARIANTS_BY_PRODUCTID);
                ptm.setInt(1, productId);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    ProductVariantDTO variant = new ProductVariantDTO(
                            rs.getInt("variantId"),
                            rs.getInt("productId"),
                            rs.getFloat("size"),
                            rs.getInt("stock")
                    );
                    variants.add(variant);
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
        return variants;
    }

    public void updateStock(int pId, float size, int quantity) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_STOCK);
                ptm.setInt(1, quantity);
                ptm.setInt(2, pId);
                ptm.setFloat(3, size);
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
    
    public ProductVariantDTO createNewSize(ProductVariantDTO variant) throws Exception {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CREATE_SIZE, PreparedStatement.RETURN_GENERATED_KEYS);
                ptm.setInt(1, variant.getProductId());
                ptm.setFloat(2, variant.getSize());
                ptm.setInt(3, variant.getStock());
                int rowsInserted = ptm.executeUpdate();

                if (rowsInserted > 0) {
                    rs = ptm.getGeneratedKeys();
                    if (rs.next()) {
                        int variantId = rs.getInt(1);
                        variant.setVariantId(variantId);
                        check = true;
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
        return variant;
    }

    public boolean isSizeExists(int productId, float size) throws SQLException, ClassNotFoundException {
        boolean exists = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_EXISTED_SIZE);
                ptm.setInt(1, productId);
                ptm.setFloat(2, size);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    exists = rs.getInt(1) > 0; // Nếu có ít nhất 1 bản ghi thì exists sẽ true
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
        return exists;
    }

   
    
    public boolean updateStock(List<ProductVariantDTO> variants) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_STOCK_QUANTITY);
                for (ProductVariantDTO variant : variants) {
                    ptm.setInt(1, variant.getStock());
                    ptm.setInt(2, variant.getVariantId());
                    ptm.addBatch();
                }
                int[] updateCounts = ptm.executeBatch();
                check = Arrays.stream(updateCounts).allMatch(count -> count > 0);
            }
        } finally {
            if (ptm != null) {
                ptm.close(); // Đóng PreparedStatement
            }
            if (conn != null) {
                conn.close(); // Đóng kết nối
            }
        }
        return check; // Trả về kết quả cập nhật
    }

    public boolean checkValidateStock(int productId, float size, int newQuantity) throws ClassNotFoundException, SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_VALIDATE);
                ptm.setInt(1, productId);
                ptm.setInt(3, newQuantity);
                ptm.setFloat(2, size);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
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

    public boolean getStatus(CartItems item) throws ClassNotFoundException, SQLException  {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_STATUS);
                ptm.setInt(1, item.getProduct().getProductId());
                ptm.setFloat(2, item.getSize());
                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = rs.getBoolean("status");
                    if(!check){
                        CartDAO cartDao = new CartDAO();
                        cartDao.updateSelectedItem(item.getCartItemId(), false);
                    }
                }
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

}
