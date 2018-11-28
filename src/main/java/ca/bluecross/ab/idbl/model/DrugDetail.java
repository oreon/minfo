/*
 * $Id: DrugDetail.java,v 1.1.2.2 2018/11/09 22:11:38 myang Exp $
 */
package ca.bluecross.ab.idbl.model;


import java.math.BigDecimal;

import java.util.Date;
import java.util.List;
import java.util.Map;


public class DrugDetail {
   private String _productId;
   
   private PharmaTheraClassification _ptc;   
   private ListingCategory _cat;
   private Manufacturer _mfr;
   
   private String _genericName;
   private String _brandName;
   
   private BigDecimal _aicStrength;
   private String _strengthUnit;
   private String _strengthUnitDesc;
   
   private String _dinGpPin;
   
   private BigDecimal _quotePrice;
   
   private Date _listed;
   private Date _discontinued;
   
   private BigDecimal _lcaMacPrice;
   
   private String _manufacturerCode;
   
   private boolean _interchangeable;
   
   private String _cvgStatus;
   
   private String _ptcId;
   
   private String _clientsAppliedTo;
   
   private String _provScheduleCode;
   
   private String _aigCode;   
   private String _formCode;
   private String _routeCode;
   
   private List<ReviewDetail> _cdrReviews;
   private List<ReviewDetail> _trodsReviews;
   
   private Integer _interchangeableGroupNumber;
   
   private List<DrugDetail> _interchangeableProducts;
   
   public DrugDetail(final String productId) {
      _productId = productId;
   }
   
   public String getProductId() {
      return _productId;
   }
   
   public DrugDetail setGenericName(final String genericName) {
      _genericName = genericName;
      return this;
   }
   
   public String getGenericName() {
      return _genericName;
   }
   
   public DrugDetail setBrandName(final String brandName) {
      _brandName = brandName;
      return this;
   }
   
   public String getBrandName() {
      return _brandName;
   } 
   
   public DrugDetail setDinPin(final String dinPin) {
      _dinGpPin = dinPin;
      return this;
   }
   
   public String getDinPin() {
      return _dinGpPin;
   } 
   
   public DrugDetail setManufacturer(final Manufacturer manufacturer) {
      _mfr = manufacturer;
      _manufacturerCode = _mfr.getCode();
      return this;
   }
   
   public Manufacturer getManufacturer() {
      return _mfr;
   }   
   
   public DrugDetail setDateListed(final Date listed) {
      _listed = listed;
      return this;
   }
   
   public Date getDateListed() {
      return _listed;
   }
   
   public DrugDetail setDateDiscontinued(final Date discontinued) {
      _discontinued = discontinued;
      return this;
   }
   
   public Date getDateDiscontinued() {
      return _discontinued;
   }   
   
   public DrugDetail setQuotePrice(final BigDecimal price) {
      _quotePrice = price;
      return this;
   }
   
   public BigDecimal getQuotePrice() {
      return _quotePrice;
   }
   
   public DrugDetail setLcaMacPrice(final BigDecimal price) {
      _lcaMacPrice = price;
      return this;
   }
   
   public BigDecimal getLcaMacPrice() {
      return _lcaMacPrice;
   }
   
   public DrugDetail setManufacturerCode(final String code) {
      _manufacturerCode = code;
      _mfr = null;
      return this;
   }
   
   public String getManufacturerCode() {
      return _manufacturerCode;
   }   
   
   public DrugDetail setInterchangeable(final String ind) {
      _interchangeable = "Y".equals(ind);
      return this;
   }
   
   public boolean isInterchangeable() {
      return _interchangeable;
   }

   public DrugDetail setCoverageStatus(final String cvgStatus) {
      _cvgStatus = cvgStatus;
      return this;
   }
   
   public String getCoverageStatus() {
      return _cvgStatus;
   }   
   
   public DrugDetail setPharmaTheraClassificationId(final String ptcId) {
      _ptcId = ptcId;
      return this;
   }
   
   public String getPharmaTheraClassificationId() {
      return _ptcId;
   } 
   
   public DrugDetail setClientsAppliedTo(final String clientsApplied) {
      _clientsAppliedTo = clientsApplied;
      return this;
   }
   
   public String getClientsAppliedTo() {
      return _clientsAppliedTo;
   }     
   
   public DrugDetail setProvScheduleCode(final String provScheduleCode) {
      _provScheduleCode = provScheduleCode;
      return this;
   }
   
   public String getProvScheduleCode() {
      return _provScheduleCode;
   } 
   
   public DrugDetail setAigCode(final String aigCode) {
      _aigCode = aigCode;
      return this;
   }
   
   public String getAigCode() {
      return _aigCode;
   }  
   
   public DrugDetail setFormCode(final String formCode) {
      _formCode = formCode;
      return this;
   }
   
   public String getFormCode() {
      return _formCode;
   }  
   
   public DrugDetail setCdrReviews(final List<ReviewDetail> reviews) {
      _cdrReviews = reviews;
      return this;
   }
   
   public List<ReviewDetail> getCdrReviews() {
      return _cdrReviews;
   }     
   
   public DrugDetail setTrodsReviews(final List<ReviewDetail> reviews) {
      _trodsReviews = reviews;
      return this;
   }
   
   public List<ReviewDetail> getTrodsReviews() {
      return _trodsReviews;
   }   
   
   /**
    * Sets both {@code CDR} and {@code TRODS} reviews.
    */
   public DrugDetail setReviews(final Map<String, List<ReviewDetail>> reviews) {
      return setCdrReviews(reviews.get("CDR")).setTrodsReviews(reviews.get("TRODS"));
   }
   
   public DrugDetail setInterchangeableGroupNumber(final Integer grpNbr) {
      _interchangeableGroupNumber = grpNbr;
      return this;
   }
   
   public Integer getInterchangeableGroupNumber() {
      return _interchangeableGroupNumber;
   }  
   
   public DrugDetail setInterchangeableProducts(final List<DrugDetail> drugs) {
      _interchangeableProducts = drugs;
      return this;
   }
   
   public List<DrugDetail> getInterchangeableProducts() {
      return _interchangeableProducts;
   }    
   
   public DrugDetail setRouteCode(final String routeCode) {
      _routeCode = routeCode;
      return this;
   }
   
   public String getRouteCode() {
      return _routeCode;
   }     
}
