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
public class PaymentMethodDAO {
    private static final String GET_PAY_METHOD = "SELECT paymentMethodId,paymentName FROM paymentMethods";
    
    public List<PaymentMethodDTO> getPaymentMethod() throws ClassNotFoundException, SQLException{
        List<PaymentMethodDTO> methodList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try{
            conn = DBUtils.getConnection();
            if(conn!=null){
                ptm = conn.prepareStatement(GET_PAY_METHOD);
                rs = ptm.executeQuery();
                while(rs.next()){
                    int id = rs.getInt("paymentMethodId");
                    String method = rs.getString("paymentName");
                    methodList.add(new PaymentMethodDTO(id, method));
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
