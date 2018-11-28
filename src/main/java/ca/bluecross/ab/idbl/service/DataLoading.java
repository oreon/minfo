/*
 * $Id: DataLoading.java,v 1.1.2.4 2018/11/15 17:38:00 myang Exp $
 */
package ca.bluecross.ab.idbl.service;

import ca.bluecross.ab.idbl.model.Document;
import ca.bluecross.ab.idbl.model.ListingCategory;
import ca.bluecross.ab.idbl.model.Manufacturer;

import ca.bluecross.ab.idbl.model.PharmaTheraClassification;

import ca.bluecross.ab.idbl.model.ReferenceDocuments;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static ca.bluecross.ab.idbl.service.SampleData.getManufacturers;

import static java.util.Collections.*;
import java.util.List;

/**
 * The implementation of this interface provides ways to load manufacturers,
 * listing category, PTC and others.
 */
public interface DataLoading {

   /**
    * Loads the manufacturers.
    * @return an instance of Map, the key of which is the manufacturer code
    *         and the value is the manufacturer instance
    */
   default Map<String, Manufacturer> loadManufacturers(){
      return getManufacturers()
         .stream().collect(Collectors.toMap(Manufacturer::getCode, Function.identity()));   
   }
   
   /**
    * Loads the {@coce idbl_ptc} records, and put them into a map keyed with
    * the {@code ptc_id}.
    */
   default Map<String, PharmaTheraClassification> loadPharmaTheraClassifications() {
      return emptyMap();   
   }
   
   /**
    * Loads listing categories.
    */
   default List<ListingCategory> loadListingCategories(){
      return emptyList();   
   }   
   
   /**
    * Loads reference documents from the table IDBL_REF_INFO_PDF_CFG
    */
   default ReferenceDocuments loadRefDocuments() {
      return null;   
   }
}
