/*
 * $Id: AutoCompleteDAO.java,v 1.1.2.1 2018/11/26 15:59:41 myang Exp $
 */
package ca.bluecross.ab.idbl.service;


import ca.bluecross.ab.idbl.model.Manufacturer;

import java.sql.SQLException;

import java.util.HashMap;
import java.util.List;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service("autoCompleteDAO")
public class AutoCompleteDAO extends IdblDAO implements AutoComplete {

   @Override
   public Results doSearch(final String term) {

//      select mfg_code, mfg_name from idbl_manufacturers where mfg_name like upper('act%')
//      select distinct generic_name from idbl_details where generic_name like upper('ac%')
//      select distinct brand_name from idbl_details where brand_name like upper('ac%')
//      select distinct din_gp_pin from idbl_details where din_gp_pin like upper('0227%')
      final String t = term.toUpperCase() + "%";
      
      final Map<String, String> mans = new HashMap<>();
      query("select mfg_code, mfg_name from idbl_manufacturers where mfg_name like ?",
            rs -> {
            mans.put(rs.getString("mfg_code"), rs.getString("mfg_name"));
         }, t);
      
      final List<String> genericNames = 
         queryForList("select distinct generic_name from idbl_details where generic_name like ?",
                      (rs, rowNum) -> rs.getString("generic_name"), t);


      final List<String> brandNames = 
         queryForList("select distinct brand_name from idbl_details where brand_name like ?",
                      (rs, rowNum) -> rs.getString("brand_name"), t);      

      final List<String> dinPins = 
         queryForList("select distinct din_gp_pin from idbl_details where din_gp_pin like ?",
                      (rs, rowNum) -> rs.getString("din_gp_pin"), t);  
      
      return new Results().setManufacturers(mans).setGenericNames(genericNames)
                          .setBrandNames(brandNames).setDinPins(dinPins);
   }
}
