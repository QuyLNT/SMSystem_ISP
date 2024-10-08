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
public class ProductVariantDAO {
    private static final String GET_ALL ="SELECT variantId,productId,size,stock FROM productVariants";
    
    public List<ProductVariantDTO> getAllVariant() throws SQLException, ClassNotFoundException {
        ProductVariantDTO variant;
        List<ProductVariantDTO> allListProduct = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try{
            conn = DBUtils.getConnection();
            if(conn!=null){
                ptm = conn.prepareStatement(GET_ALL);
                rs = ptm.executeQuery();
                while(rs.next()){
                    variant = new ProductVariantDTO(rs.getInt("variantId"),
                            rs.getInt("productid"),
                            rs.getFloat("size"),
                            rs.getInt("stock"));
                    allListProduct.add(variant);
                }
            }
        }finally{
            if(rs!=null) rs.close();
            if(ptm!=null) ptm.close();
            if(conn!=null) conn.close();
        }
        return allListProduct;
    }
    
}