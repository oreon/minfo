/*
 * $Id: DrugsInfoLookup.java,v 1.1.2.3 2018/11/26 21:52:57 myang Exp $
 */
package ca.bluecross.ab.idbl.service;

import ca.bluecross.ab.idbl.model.DrugDetail;

import ca.bluecross.ab.idbl.model.ReviewDetail;

import ca.bluecross.ab.idbl.model.ReviewDocument;

import java.util.List;
import java.util.Map;

/**
 * The interface defines sorts of methods for looking up drug related information.
 */
public interface DrugsInfoLookup {
   
   /**
    * Looks up drug details by the provided drug ID.
    */
   default DrugDetail findDrugDetailById(String drugId) {
      throw new UnsupportedOperationException();   
   }
   
   /**
    * Looks up drug reviews for the given drug ID.
    */
   default Map<String, List<ReviewDetail>> findDrugReviews(String drugId) {
      throw new UnsupportedOperationException();  
   }
   
   /**
    * Populates the interchangeable products for the given drug instance.
    * @return the argument drug instance
    */
   default DrugDetail populateIcProducts(DrugDetail drug) {
      return drug;   
   }
   
   default List<ReviewDocument> findReviewDocuments(int recommendationId) {
      throw new UnsupportedOperationException();
   }
   
   default List<DrugDetail> findDrugs(DrugsSearchContext dsc) {
      throw new UnsupportedOperationException();
   }

   /**
    * Finds a list of drugs with the given brand name.
    */ 
   default List<DrugDetail> findDrugsByBrandName(String brandName) {
      throw new UnsupportedOperationException();
   }   

   /**
    * Finds a list of drugs with the given generic name.
    */   
   default List<DrugDetail> findDrugsByGenericName(String genericName) {
      throw new UnsupportedOperationException();
   } 

   /**
    * Finds a list of drugs with the given DIN/PIN.
    */   
   default List<DrugDetail> findDrugsByDinPin(String dinPin) {
      throw new UnsupportedOperationException();
   } 
   
   /**
    * Finds a list of drugs with the given manufacturer code.
    */
   default List<DrugDetail> findDrugsByMfgCode(String mfgCode) {
      throw new UnsupportedOperationException();
   } 
}
