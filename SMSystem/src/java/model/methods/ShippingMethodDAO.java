/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.methods;

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
public class ShippingMethodDAO {
  private static final String GET_SHIPPING_METHOD = "SELECT shippingMethodId,methodName FROM shippingMethods";
    
    public List<ShippingMethodDTO> getShippingMethod() throws ClassNotFoundException, SQLException{
        List<ShippingMethodDTO> methodList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try{
            conn = DBUtils.getConnection();
            if(conn!=null){
                ptm = conn.prepareStatement(GET_SHIPPING_METHOD);
                rs = ptm.executeQuery();
                while(rs.next()){
                    int id = rs.getInt("shippingMethodId");
                    String method = rs.getString("methodName");
                    methodList.add(new ShippingMethodDTO(id, method));
                }
            }
        }finally{
            if(rs!=null) rs.close();
            if(ptm!=null) ptm.close();
            if(conn!=null) conn.close();
        }
        return methodList;
    }   
}
