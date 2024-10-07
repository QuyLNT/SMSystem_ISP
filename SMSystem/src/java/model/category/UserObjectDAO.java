/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.category;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class UserObjectDAO {
    public List<UserObjectDTO> getAllUserObject(){
        List<UserObjectDTO> listUO = new ArrayList();
        return listUO;
    }
    
    public UserObjectDTO getUserObjectById(int userObjectId){
        UserObjectDTO uOb = new UserObjectDTO();
        return uOb;
    }
    
    
}
