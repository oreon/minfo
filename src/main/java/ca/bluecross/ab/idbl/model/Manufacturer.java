/*
 * $Id: Manufacturer.java,v 1.1.2.1 2018/11/08 19:59:38 myang Exp $
 */
package ca.bluecross.ab.idbl.model;

/**
 * 
 */
public class Manufacturer {
   
   private String _name;
   private String _code;
   
   private String _addrLine1;
   private String _addrLine2;
   private String _addrLine3;
   
   private String _city;
   private String _provCode;
   private String _postalCode;
   
   private String _phoneNumber;
   private String _faxNumber;
   private String _tollPhoneNumber;
   
   public Manufacturer(final String code, final String name) {
      _name = name;
      _code = code;
   }

   public String getName() {
      return _name;
   }
   
   public String getCode() {
      return _code;
   }   
   
   public String getPhoneNumber() {
      return _phoneNumber;
   }
   
   public Manufacturer setPhoneNumber(final String nbr) {
      _phoneNumber = nbr;
      return this;
   }
   
   public String getFaxNumber() {
      return _faxNumber;
   }
   
   public Manufacturer setFaxNumber(final String nbr) {
      _faxNumber = nbr;
      return this;
   }
   
   public String getTollFreePhoneNumber() {
      return _tollPhoneNumber;
   }
   
   public Manufacturer setTollFreePhoneNumber(final String nbr) {
      _tollPhoneNumber = nbr;
      return this;
   }   
   
   public String getAddressLine1() {
      return _addrLine1;
   }
   
   public Manufacturer setAddressLine1(final String addr) {
      _addrLine1 = addr;
      return this;
   } 

   public String getAddressLine2() {
      return _addrLine2;
   }
   
   public Manufacturer setAddressLine2(final String addr) {
      _addrLine2 = addr;
      return this;
   }  
   
   public String getAddressLine3() {
      return _addrLine3;
   }
   
   public Manufacturer setAddressLine3(final String addr) {
      _addrLine3 = addr;
      return this;
   }

   public String getProvCode() {
      return _provCode;
   }
   
   public Manufacturer setProvCode(final String provCode) {
      _provCode = provCode;
      return this;
   }  
   
   public String getPostalCode() {
      return _postalCode;
   }
   
   public Manufacturer setPostalCode(final String postalCode) {
      _postalCode = postalCode;
      return this;
   }
   
   public String getAddress() {
      return String.format("%s %s %s", _addrLine1, _provCode, _postalCode);
   }
}
