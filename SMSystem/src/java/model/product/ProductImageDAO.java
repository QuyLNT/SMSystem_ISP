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
public class ProductImageDAO {
    private static final String GET_PRODUCT_IMAGE =    "SELECT imageId,imagePath,isAvatar\n" +
                                                "FROM productImages\n" +
                                                "WHERE productId = ?";
    
    private static final String UPDATE_AVATAR = "UPDATE productImages "
                                                + "SET imagePath=? "
                                                + "WHERE productId=? AND isAvatar =1" ;
    public List<ProductImageDTO> getAllImage(int productId) throws ClassNotFoundException, SQLException{
        List<ProductImageDTO> listImage = new ArrayList();
        ProductDTO product = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try{
            conn = DBUtils.getConnection();
            if(conn!=null){
                ptm = conn.prepareStatement(GET_PRODUCT_IMAGE);
                ptm.setInt(1, productId);
                rs = ptm.executeQuery();
                while(rs.next()){
                    int imageId = rs.getInt("imageId");
                    String path = rs.getString("imagePath");
                    boolean isAvatar = rs.getBoolean("isAvatar");
                    ProductImageDTO image = new ProductImageDTO(imageId, productId, path, isAvatar);
                    listImage.add(image);
                }
            }
        }finally{
            if(rs!=null) rs.close();
            if(ptm!=null) ptm.close();
            if(conn!=null) conn.close();
        }
        
        return listImage;
    }
    
    public boolean updateAvatar(ProductImageDTO avatar) throws ClassNotFoundException, SQLException{
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean result = false;
        try{
            conn = DBUtils.getConnection();
            if(conn!=null){
                ptm = conn.prepareStatement(UPDATE_AVATAR);
                ptm.setString(1, avatar.getImagePath());
                ptm.setInt(2, avatar.getImageId());
                result = ptm.executeUpdate()>0;
            }
        }finally{
            if(ptm!=null) ptm.close();
            if(conn!=null) conn.close();
        }
        
        return result;
    }
}
