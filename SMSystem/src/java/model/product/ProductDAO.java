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
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author LENOVO
 */
public class ProductDAO {
    private static final String GET_PRODUCT =    "SELECT productId,brandId,userObjectId,detail,hot,"
            + "                                      name,color,price,sale,warrantPeriod,productStatus \n" +
                                                    "FROM products\n" +
                                                    "WHERE productId=?";
    
    private static final String UPDATE_PRODUCT= "UPDATE products\n" +
                                                "SET brandId=?, userObjectId=?, detail=?, name=?, price=?, color=?, sale=?, warrantPeriod =?\n" +
                                                "WHERE ProductID=?";
    
    private static final String GET_ALL_PRODUCT= "SELECT productId,brandId,userObjectId,detail,hot,"
            + "                                      name,color,price,sale,warrantPeriod,productStatus \n" +
                                                    "FROM products";
 
    private static final String ADD_PRODUCT = "INSERT INTO Product (brandId, userOjectId, detail, hot, name, color, price, sale, warrantyPeriod, productStatus) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_HOT = "UPDATE products"
                                            + "SET hot=?"
                                            + "WHERE productId=?";
    private static final String UPDATE_PRODUCT_STATUS = "UPDATE products"
                                                        + "SET productStatus=?"
                                                        + "WHERE productId=?";
    
        private static final String SEARCH_PRODUCT_BY_NAME = "SELECT productId,brandId,userObjectId,detail,hot,"
            + "                                      name,color,price,sale,warrantPeriod,productStatus \n" +
                                                    "FROM products\n" +
                                                    "WHERE name LIKE ?";
    
    public List<ProductDTO> getAllProduct() throws ClassNotFoundException, SQLException{
        List<ProductDTO> listProduct = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try{
            conn = DBUtils.getConnection();
            if(conn!=null){
                ptm = conn.prepareStatement(GET_ALL_PRODUCT);
                rs = ptm.executeQuery();

                while (rs.next()) {
                    int productId = rs.getInt("productId");
                    int brandId = rs.getInt("brandId");
                    int userObjectId = rs.getInt("userObjectId");
                    String detail = rs.getString("detail");
                    boolean hot = rs.getBoolean("hot");
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    String color = rs.getString("color");
                    float sale = rs.getFloat("sale");
                    int warrantPeriod = rs.getInt("warrantPeriod");
                    boolean productStatus = rs.getBoolean("productStatus");
                    
                    ProductDTO product = new ProductDTO(productId, brandId, userObjectId, detail, hot, name, color, price, sale, warrantPeriod, productStatus);
                    listProduct.add(product);
                    
                }
                
            }
        }finally{
            if(rs!=null) rs.close();
            if(ptm!=null) ptm.close();
            if(conn!=null) conn.close();
        }
        
        return listProduct;
    }
    
    public ProductDTO getProductById(int productId) throws ClassNotFoundException, SQLException {
        ProductDTO product = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try{
            conn = DBUtils.getConnection();
            if(conn!=null){
                ptm = conn.prepareStatement(GET_PRODUCT);
                ptm.setInt(1, productId);
                rs = ptm.executeQuery();
                if(rs.next()){
                    int brandId = rs.getInt("brandId");
                    int userObjectId = rs.getInt("userObjectId");
                    String detail = rs.getString("detail");
                    boolean hot = rs.getBoolean("hot");
                    String name = rs.getString("name");
                    String color = rs.getString("color");
                    float price = rs.getFloat("price");
                    float sale = rs.getFloat("sale");
                    int warrantPeriod = rs.getInt("warrantPeriod");
                    boolean productStatus = rs.getBoolean("productStatus");

                    product = new ProductDTO(productId, brandId, userObjectId, detail, hot, name, color, price , sale, warrantPeriod, productStatus);
                }
            }
        }finally{
            if(rs!=null) rs.close();
            if(ptm!=null) ptm.close();
            if(conn!=null) conn.close();
        }
        
        return product;
    }
    
    public boolean updateProduct(ProductDTO product) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean result = false;
        try{
            conn = DBUtils.getConnection();
            if(conn!=null){
                ptm = conn.prepareStatement(UPDATE_PRODUCT);
                ptm.setInt(1, product.getBrandId());
                ptm.setInt(2,product.getUserOjectId());
                ptm.setString(3, product.getDetail());
                ptm.setString(4, product.getName());
                ptm.setFloat(5, product.getPrice());
                ptm.setString(6, product.getColor());
                ptm.setFloat(7, product.getSale());
                ptm.setInt(8, product.getWarrantyPeriod());
                ptm.setInt(9,product.getProductId());
                result = ptm.executeUpdate()>0;
                
            }
        }finally{
            if(ptm!=null) ptm.close();
            if(conn!=null) conn.close();
        }
        
        return result;
    }
    
    public int addProduct(ProductDTO product) throws SQLException, ClassNotFoundException {
        int productId = -1;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet generatedKeys = null;
        
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ADD_PRODUCT, PreparedStatement.RETURN_GENERATED_KEYS);
                ptm.setInt(1, product.getProductId());
                ptm.setInt(2, product.getBrandId());
                ptm.setInt(3, product.getUserOjectId());
                ptm.setString(4, product.getDetail());
                ptm.setBoolean(5, product.isHot());
                ptm.setString(6, product.getName());
                ptm.setString(7, product.getColor());
                ptm.setFloat(8, product.getPrice());
                ptm.setFloat(9, product.getSale());
                ptm.setInt(10, product.getWarrantyPeriod());
                ptm.setBoolean(11, product.isProductStatus());

                // Thực thi câu lệnh SQL
                int rowsInserted = ptm.executeUpdate();

                // Kiểm tra nếu có hàng được chèn
                if (rowsInserted > 0) {
                    // Lấy ID tự động tạo ra từ kết quả
                    generatedKeys = ptm.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        productId = generatedKeys.getInt(1);  // Lấy productId được tạo
                    }
                }
            }
        } finally {
            if (generatedKeys != null) generatedKeys.close();
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }
        return productId;
    }

    public boolean updateHot(int productId, boolean hot) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean result = false;
        try{
            conn = DBUtils.getConnection();
            if(conn!=null){
                ptm = conn.prepareStatement(UPDATE_HOT);
                ptm.setBoolean(1, hot);
                ptm.setInt(2,productId);
                result = ptm.executeUpdate()>0;         
            }
        }finally{
            if(ptm!=null) ptm.close();
            if(conn!=null) conn.close();
        }
        
        return result;
    }

    public boolean updateProductStatus(int productId, boolean productStatus) throws ClassNotFoundException, SQLException {
Connection conn = null;
        PreparedStatement ptm = null;
        boolean result = false;
        try{
            conn = DBUtils.getConnection();
            if(conn!=null){
                ptm = conn.prepareStatement(UPDATE_PRODUCT_STATUS);
                ptm.setBoolean(1, productStatus);
                ptm.setInt(2,productId);
                result = ptm.executeUpdate()>0;         
            }
        }finally{
            if(ptm!=null) ptm.close();
            if(conn!=null) conn.close();
        }
        
        return result;    }

    public List<ProductDTO> searchProductsByName(String searchTerm) throws ClassNotFoundException, SQLException {
        List<ProductDTO> products = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBUtils.getConnection();
            if(con!=null){
                ps = con.prepareStatement(SEARCH_PRODUCT_BY_NAME);
                ps.setString(1, "%" + searchTerm + "%");
                rs = ps.executeQuery();

                while (rs.next()) {
                int productId = rs.getInt("productId");
                    int brandId = rs.getInt("brandId");
                    int userObjectId = rs.getInt("userObjectId");
                    String detail = rs.getString("detail");
                    boolean hot = rs.getBoolean("hot");
                    String productName = rs.getString("name");
                    float price = rs.getFloat("price");
                    String color = rs.getString("color");
                    float sale = rs.getFloat("sale");
                    int warrantPeriod = rs.getInt("warrantPeriod");
                    boolean productStatus = rs.getBoolean("productStatus");

                    ProductDTO product = new ProductDTO(productId, brandId, userObjectId, detail, hot, productName, color, price, sale, warrantPeriod, productStatus);
                    products.add(product);
            }
            
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

        return products;
    }
    
}
