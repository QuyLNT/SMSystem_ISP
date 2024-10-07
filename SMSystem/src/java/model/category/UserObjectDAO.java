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
import model.product.ProductDTO;
import utils.DBUtils;

/**
 *
 * @author LENOVO
 */
public class UserObjectDAO {
    
     private static final String GET_UO_BY_ID= "SELECT userObjectId,userObjectName,detail\n" +
                                                "FROM userObjects\n" +
                                                "WHERE userObjectId=?";
     private static final String GET_ALL= "SELECT *" +
                                                "FROM userObjects";
    
    public List<UserObjectDTO> getAllUserObject() throws ClassNotFoundException, SQLException{
        List<UserObjectDTO> listUO = new ArrayList();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try{
            conn = DBUtils.getConnection();
            if(conn!=null){
                ptm = conn.prepareStatement(GET_ALL);
                rs = ptm.executeQuery();
                while(rs.next()){
                    UserObjectDTO uOb = new UserObjectDTO(rs.getInt("userObjectId"), rs.getString("userObjectName"), rs.getString("detail"));
                    listUO.add(uOb);
                }
            }
        }finally{
            if(rs!=null) rs.close();
            if(ptm!=null) ptm.close();
            if(conn!=null) conn.close();
        }
        return listUO;
    }
    
    public UserObjectDTO getUserObjectById(int userObjectId) throws ClassNotFoundException, SQLException{
        UserObjectDTO uOb = new UserObjectDTO();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try{
            conn = DBUtils.getConnection();
            if(conn!=null){
                ptm = conn.prepareStatement(GET_UO_BY_ID);
                ptm.setInt(1, userObjectId);
                rs = ptm.executeQuery();
                if(rs.next()){
                    uOb = new UserObjectDTO(rs.getInt("userObjectId"), rs.getString("userObjectName"), rs.getString("detail"));
                }
            }
        }finally{
            if(rs!=null) rs.close();
            if(ptm!=null) ptm.close();
            if(conn!=null) conn.close();
        }
        return uOb;
    }
    
    
}
