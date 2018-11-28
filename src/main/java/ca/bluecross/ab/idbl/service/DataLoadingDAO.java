/*
 * $Id: DataLoadingDAO.java,v 1.1.2.2 2018/11/26 18:02:07 myang Exp $
 */
package ca.bluecross.ab.idbl.service;

import ca.bluecross.ab.idbl.model.Document;
import ca.bluecross.ab.idbl.model.ListingCategory;
import ca.bluecross.ab.idbl.model.Manufacturer;

import ca.bluecross.ab.idbl.model.PharmaTheraClassification;

import ca.bluecross.ab.idbl.model.ReferenceDocuments;

import java.sql.SQLException;

import static java.util.Collections.emptyMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class DataLoadingDAO extends IdblDAO implements DataLoading {
   
   @Override
   public Map<String, Manufacturer> loadManufacturers() {
      return queryForList("select * from idbl_manufacturers", (rs, rowNum) -> {
//         try {
            return new Manufacturer(rs.getString("mfg_code"),
                                    rs.getString("mfg_name"))
               .setFaxNumber(rs.getString("fax_nbr"))
               .setPhoneNumber(rs.getString("phone_nbr"))
               .setTollFreePhoneNumber(rs.getString("toll_phone_nbr"))
               .setAddressLine1(rs.getString("address_line_1"))
               .setAddressLine2(rs.getString("address_line_2"))
               .setAddressLine3(rs.getString("address_line_3"))
               .setProvCode(rs.getString("prov_code"))
               .setPostalCode(rs.getString("postal_code"));
//         }
//         catch (final SQLException ex) {
//            throw new RuntimeException(ex);
//         }
      })
      .stream()
      .collect(Collectors.toMap(Manufacturer::getCode, Function.identity()));
   }
   
   @Override
   public Map<String, PharmaTheraClassification> loadPharmaTheraClassifications() {
      return queryForList("select * from idbl_ptcs", (rs, rowNum) -> {
//         try {
            return new PharmaTheraClassification(rs.getString("ptc_id"))
               .setDescription(rs.getString("ptc_desc"))
               .setGroup(rs.getString("ptc_group"))
               .setPtcClass(rs.getString("ptc_class"))
               .setSub(rs.getString("ptc_sub"))
               .setSubSub(rs.getString("ptc_sub_sub"));
//         }
//         catch (final SQLException ex) {
//            throw new RuntimeException(ex);
//         }
      })
      .stream()
      .collect(Collectors.toMap(PharmaTheraClassification::getId,
                                Function.identity()));
   }   
   
   @Override
   public List<ListingCategory> loadListingCategories() {
      return queryForList("select * from idbl_categories", (rs, rowNum) -> {
//         try {
            return new ListingCategory(rs.getString("PRICE_LIST_ID"))
                   .setProductListingCategory(rs.getString("PRODUCT_LISTING_CATEGORY"));
//         }
//         catch (final SQLException ex) {
//            throw new RuntimeException(ex);
//         }
      });
   }   
   
   /**
    * Loads reference documents from the table IDBL_REF_INFO_PDF_CFG
    */
   @Override
   public ReferenceDocuments loadRefDocuments() {
      final ReferenceDocuments docs = new ReferenceDocuments();
      query("select PDF_GROUPING_CODE, ORDER_NUMBER, PDF_FILE_NAME, " +
            "PDF_FILE_DESCRIPTION from IDBL_REF_INFO_PDF_CFG " +
            "ORDER BY PDF_GROUPING_CODE, ORDER_NUMBER", rs -> {
         //try {
            docs.put(rs.getString("PDF_GROUPING_CODE"), 
                     new Document(rs.getString("PDF_FILE_NAME"), 
                                  rs.getString("PDF_FILE_DESCRIPTION")));
         //}
         //catch (final SQLException ex) {
         //   throw new RuntimeException(ex);
         //}
      });
      return docs;   
   }
}
