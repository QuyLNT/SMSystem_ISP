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
    
    private static final String GET_BRAND_BY_ID= "SELECT brandId,brandName\n" +
                                                    "FROM brands\n" +
                                                    "WHERE brandId = ?";
    private static final String GET_ALL= "SELECT brandId,brandName\n" +
                                                    "FROM brands";
    public List<BrandDTO> getAllBrand() throws ClassNotFoundException, SQLException{
        List<BrandDTO> listBrand = new ArrayList();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try{
            conn = DBUtils.getConnection();
            if(conn!=null){
                ptm = conn.prepareStatement(GET_ALL);
                rs = ptm.executeQuery();
                while(rs.next()){
                    BrandDTO brand = new BrandDTO(rs.getInt("brandId"), rs.getString("brandName"));
                    listBrand.add(brand);
                }
            }
        }finally{
            if(rs!=null) rs.close();
            if(ptm!=null) ptm.close();
            if(conn!=null) conn.close();
        }
        return listBrand;
    }
    
    public BrandDTO getBrandById(int brandId) throws SQLException, ClassNotFoundException{
        BrandDTO brand = new BrandDTO();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try{
            conn = DBUtils.getConnection();
            if(conn!=null){
                ptm = conn.prepareStatement(GET_BRAND_BY_ID);
                ptm.setInt(1, brandId);
                rs = ptm.executeQuery();
                if(rs.next()){
                    brand = new BrandDTO(brandId, rs.getString("brandName"));
                }
            }
        }finally{
            if(rs!=null) rs.close();
            if(ptm!=null) ptm.close();
            if(conn!=null) conn.close();
        }
        return brand;
    }
}
