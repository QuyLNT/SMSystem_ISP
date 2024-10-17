/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.discount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.category.BrandDTO;
import utils.DBUtils;

/**
 *
 * @author LENOVO
 */
public class DiscountDAO {
    private static final String GET_ALL= "SELECT discountId,discountCode,detail,discountAmount,startDay,endDay,usageLimit,usage,discountStatus\n" +
"                                          FROM discountCodes";
    private static final String UPDATE_STATUS = "UPDATE discountCodes\n" +
                                        "SET discountStatus=?\n" +
                                        "WHERE discountId=?";
    public List<DiscountDTO> getALlDiscount() throws SQLException, ClassNotFoundException{
        List<DiscountDTO> discountList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try{
            conn = DBUtils.getConnection();
            if(conn!=null){
                ptm = conn.prepareStatement(GET_ALL);
                rs = ptm.executeQuery();
                while(rs.next()){
                    
                    DiscountDTO discount = new DiscountDTO(rs.getInt("discountId"), 
                            rs.getString("discountCode"), 
                            rs.getString("detail"), 
                            rs.getFloat("discountAmount"), 
                            rs.getDate("startDay"), 
                            rs.getDate("endDay"), 
                            rs.getInt("usageLimit"),
                            rs.getInt("usage"),
                            rs.getString("discountStatus"));
                    
                    discountList.add(discount);
                }
            }
        }finally{
            if(rs!=null) rs.close();
            if(ptm!=null) ptm.close();
            if(conn!=null) conn.close();
        }
        return discountList;
    }

    public boolean updateStatus(int discountId, String status) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean result = false;
        try{
            conn = DBUtils.getConnection();
            if(conn!=null){
                ptm = conn.prepareStatement(UPDATE_STATUS);
                ptm.setString(1, status);
                ptm.setInt(2,discountId);
                result = ptm.executeUpdate()>0;         
            }
        }finally{
            if(ptm!=null) ptm.close();
            if(conn!=null) conn.close();
        }
        
        return result;

    }
}
