/*
 * $Id: ListingCategory.java,v 1.1.2.1 2018/11/08 19:59:38 myang Exp $
 */
package ca.bluecross.ab.idbl.model;

/**
 * 
 */
public class ListingCategory {

   private String _priceListId;
   private String _productListingCategory;
   
   public ListingCategory(final String listId) {
      _priceListId = listId;
   }
   
   public String getPriceListId() {
      return _priceListId;
   }
   
   public ListingCategory setProductListingCategory(final String category) {
      _productListingCategory = category;
      return this;
   }
   
   public String getProductListingCategory() {
      return _productListingCategory;
   }
}
