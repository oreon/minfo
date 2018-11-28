/*
 * $Id: DrugsInfoLookupDAO.java,v 1.1.2.3 2018/11/26 21:52:57 myang Exp $
 */
package ca.bluecross.ab.idbl.service;

import ca.bluecross.ab.idbl.model.DrugDetail;
import ca.bluecross.ab.idbl.model.ReviewDetail;

import ca.bluecross.ab.idbl.model.ReviewDocument;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Collections;
import static java.util.Collections.emptyList;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

import org.springframework.stereotype.Service;

@Service
public class DrugsInfoLookupDAO extends IdblDAO implements DrugsInfoLookup {
   
   
   @Override
   public DrugDetail findDrugDetailById(final String drugId) {
      final DrugDetail drug =
         queryForObject("select * from idbl_details where product_id = ?",
                        (rs, rowNum) -> {
            return getDrugDetail(rs);
      }, drugId);
      
      return populateIcProducts(drug);
   }
   
   /**
    * Looks up drug reviews for the given drug ID.
    */
   public Map<String, List<ReviewDetail>> findDrugReviews(final String drugId) {
      final String sql = "select x.* from idbl_drug_review_details x, idbl_details y "
         + "where x.din_gp_pin = y.din_gp_pin and y.product_id = ? ";
      final List<ReviewDetail> reviews =
         queryForList(sql, (rs, rowNum) -> {
//         try {
            return new ReviewDetail(rs.getString("title"),
                                    rs.getString("review_type"))
               .setCdrRecommendationId(rs.getInt("CDR_RECOMMENDATION_ID"));
//         }
//         catch (final SQLException ex) {
//            throw new RuntimeException(ex);
//         }
      }, drugId);      
      return reviews.stream().collect(groupingBy(ReviewDetail::getReviewType));
   }      
   
   /**
    * Populates the interchangeable products for the given drug instance.
    * @return the argument drug instance
    */
   @Override
   public DrugDetail populateIcProducts(final DrugDetail drug) {
      if (!drug.isInterchangeable()) {
         return drug;
      }
      final List<DrugDetail> icProducts =
         queryForList("select * from idbl_details where INTERCHANGE_GRP_NBR = ? "
                      + "and aig_code = ? and form_code = ? and route_code = ? "
                      + "and IDBL_INTERCHANGEABLE_IND = 'Y'",    
                      (rs, rowNum) -> {
            return getDrugDetail(rs);
         }, drug.getInterchangeableGroupNumber(), 
            drug.getAigCode(), drug.getFormCode(), drug.getRouteCode());
      
      return drug.setInterchangeableProducts(icProducts);   
   }
   
   /**
    * Finds a list of {@link ReviewDocument> instances based on the given 
    * recommendation ID.
    */
   @Override
   public List<ReviewDocument> findReviewDocuments(final int recommendationId) {      
      final String sql = 
         "select * from idbl_review_documents where cdr_recommendation_id = ?";
      return queryForList(sql, (rs, rowNum) -> {
         return new ReviewDocument(rs.getInt("DOCUMENT_ID"))
            .setFileName(rs.getString("FILENAME"))
            .setFileType(rs.getString("file_type"))
            .setContentType(rs.getString("content_type"));
      }, recommendationId);
   }

   /**
    * Finds a list of drugs with the provided search context.
    */
   @Override
   public List<DrugDetail> findDrugs(final DrugsSearchContext dsc) {
      return Collections.emptyList();
   }
   
   /**
    * Finds a list of drugs with the given manufacturer code. 
    * @param mfgCode manufacturer code
    * @return a list of {@link DrugDetail} instances
    */
   @Override
   public List<DrugDetail> findDrugsByMfgCode(final String mfgCode) {
      return drugsList("select * from idbl_details where mfg_code = ?", mfgCode);
   } 
   
   
   /**
    * Finds a list of drugs with the given DIN/PIN. 
    * @param dinPin DIN/PIN
    * @return a list of {@link DrugDetail} instances
    */
   @Override
   public List<DrugDetail> findDrugsByDinPin(final String dinPin) {
      return drugsList("select * from idbl_details where din_gp_pin = ?", dinPin);
   } 
   
   /**
    * Finds a list of drugs with the given generic name. 
    * @param genericName generic name
    * @return a list of {@link DrugDetail} instances
    */
   @Override
   public List<DrugDetail> findDrugsByGenericName(final String genericName) {
      return drugsList("select * from idbl_details where generic_name = ?", genericName);
   }   
   
   /**
    * Finds a list of drugs with the given brand name. 
    * @param brandName brand name
    * @return a list of {@link DrugDetail} instances
    */
   @Override
   public List<DrugDetail> findDrugsByBrandName(final String brandName) {
      return drugsList("select * from idbl_details where brand_name = ?", brandName);
   } 
   
   /**
    * Finds a list of drugs with the given query and argument.
    */
   private List<DrugDetail> drugsList(final String sql, final String arg) {
      return queryForList(sql, (rs, rowNum) -> {
            return getDrugDetail(rs);
         }, arg);  
   }
   
   /**
    * 
    * @param rs
    * @return
    * @throws SQLException
    */
   private DrugDetail getDrugDetail(final ResultSet rs) throws SQLException {
      final String drugId = rs.getString("product_id");
      return new DrugDetail(drugId)
         .setCoverageStatus(rs.getString("COVERAGE_STATUS"))
         .setInterchangeable(rs.getString("IDBL_INTERCHANGEABLE_IND"))
         .setPharmaTheraClassificationId(rs.getString("ptc_id"))
         .setManufacturerCode(rs.getString("mfg_code"))
         .setBrandName(rs.getString("brand_name"))
         .setGenericName(rs.getString("generic_name"))
         .setDinPin(rs.getString("din_gp_pin"))
         .setClientsAppliedTo(rs.getString("APPLIES_TO_CLIENTS_OF"))
         .setProvScheduleCode(rs.getString("PROVINCIAL_SCHEDULE_CODE"))
         .setAigCode(rs.getString("AIG_CODE"))
         .setFormCode(rs.getString("FORM_CODE"))        
         .setRouteCode(rs.getString("route_code"))     
         .setInterchangeableGroupNumber(rs.getInt("INTERCHANGE_GRP_NBR"))
         .setReviews(findDrugReviews(drugId)); 
   }
}
