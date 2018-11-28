/*
 * $Id: AutoComplete.java,v 1.1.2.3 2018/11/26 15:59:41 myang Exp $
 */
package ca.bluecross.ab.idbl.service;

import ca.bluecross.ab.idbl.model.Manufacturer;

import static java.util.Arrays.asList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

import java.util.stream.Collectors;
import static java.util.stream.Collectors.toMap;

import org.springframework.stereotype.Service;

@Service("autoComplete")
public interface AutoComplete {
   
   public static class Results {
      private List<String> _brandNames;
      private List<String> _genericNames;
      private Map<String, String> _manufacturers;
      private List<String> _dinPins;


      public Results setBrandNames(List<String> brandNames) {
         _brandNames = brandNames;
         return this;
      }

      public List<String> getBrandNames() {
         return _brandNames;
      }

      public Results setGenericNames(List<String> genericNames) {
         _genericNames = genericNames;
         return this;
      }

      public List<String> getGenericNames() {
         return _genericNames;
      }

      public Results setManufacturers(Map<String, String> manufacturers) {
         _manufacturers = manufacturers;
         return this;
      }

      public Map<String, String> getManufacturers() {
         return _manufacturers;
      }
      
      public Results setDinPins(final List<String> dinPins) {
         _dinPins = dinPins;
         return this;
      }
      
      public List<String> getDinPins() {
         return _dinPins;
      }
      
      public Map<String, Object> asMap() {         
         return new HashMap<String, Object>() {{
            put("brandNames", _brandNames);
            put("genericNames", _genericNames);
            put("dinPins", _dinPins);
            put("manufacturers", _manufacturers);
         }};   
      }

   }
   
   default Results doSearch(String term) {
      return new Results().setBrandNames(asList("a", "b", "c"));   
   }
}
