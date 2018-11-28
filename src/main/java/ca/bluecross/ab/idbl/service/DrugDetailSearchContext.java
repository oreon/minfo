/*
 * $Id: DrugDetailSearchContext.java,v 1.1.2.1 2018/11/08 19:59:38 myang Exp $
 */
package ca.bluecross.ab.idbl.service;

/**
 * {@code DrugDetailSearchContext} defines conditions used to search for
 * drug details.
 */
public class DrugDetailSearchContext {
   
   private String _brandName;
   private String _genericName;
   private String _manufacturerName;
   private String _searchTerm;
   
   public DrugDetailSearchContext setBrandName(final String brandName) {
      _brandName = brandName;
      return this;
   }

   public DrugDetailSearchContext setGenericName(final String genericName) {
      _genericName = genericName;
      return this;
   }
   
   public DrugDetailSearchContext setManufactureName(final String manufacturerName) {
      _manufacturerName = manufacturerName;
      return this;
   }
   
   public DrugDetailSearchContext setTerm(final String term) {
      _searchTerm = term;
      return this;
   }  
   
   public String getTerm() {return _searchTerm;}   
}
