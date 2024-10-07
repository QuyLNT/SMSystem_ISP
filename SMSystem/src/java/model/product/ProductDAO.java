/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.product;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.user.UserDTO;
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

    public Map<Integer,ProductDTO> getAllProduct() throws ClassNotFoundException, SQLException{
        Map<Integer,ProductDTO> listProduct = new HashMap<>();
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
                    listProduct.put(product.getProductId(), product);
                    
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
                ptm.setFloat(6, product.getSale());
                ptm.setInt(7, product.getWarrantyPeriod());
                ptm.setInt(8,product.getProductId());
                result = ptm.executeUpdate()>0;
                
            }
        }finally{
            if(ptm!=null) ptm.close();
            if(conn!=null) conn.close();
        }
        
        return result;
    }
    
    
}
