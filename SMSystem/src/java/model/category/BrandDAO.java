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
public class BrandDAO {
    public List<BrandDTO> getAllBrand(){
        List<BrandDTO> listBrand = new ArrayList();
        return listBrand;
    }
    
    public BrandDTO getBrandById(int brandId){
        BrandDTO brand = new BrandDTO();
        return brand;
    }
}
