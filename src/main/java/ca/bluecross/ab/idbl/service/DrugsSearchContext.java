/*
 * $Id: DrugsSearchContext.java,v 1.1.2.1 2018/11/26 21:52:57 myang Exp $
 */
package ca.bluecross.ab.idbl.service;

public class DrugsSearchContext {
   
   private String _searchTerm;
   private String _listingCategory;
   private String _classification;
   private String _mfg;


   public DrugsSearchContext setSearchTerm(final String searchTerm) {
      _searchTerm = searchTerm;
      return this;
   }

   public String getSearchTerm() {
      return _searchTerm;
   }

   public DrugsSearchContext setListingCategory(final String listingCategory) {
      _listingCategory = listingCategory;
      return this;
   }

   public String getListingCategory() {
      return _listingCategory;
   }

   public DrugsSearchContext setClassification(final String classification) {
      _classification = classification;
      return this;
   }

   public String getClassification() {
      return _classification;
   }

   public DrugsSearchContext setMfgCode(final String mfrCode) {
      _mfg = mfrCode;
      return this;
   }

   public String getMfgCode() {
      return _mfg;
   }
}
