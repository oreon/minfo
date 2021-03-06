package hello;

import ca.bluecross.ab.idbl.model.Manufacturer;

import java.util.Arrays;
import java.util.List;

public final class SampleData {
   
   private SampleData() {}
   
   public static final Manufacturer ALC =
      newManufacturer("ALC", "ALCON CANADA INC.", "9058266700", "9058261448", "8002684574");

   public static final Manufacturer AMG = 
      newManufacturer("AMG", "AMGEN INC.", "9052853000", "9052853100", "8006654273");
   
   public static final Manufacturer GPM = 
      newManufacturer("GPM", "GENPHARM INC.", "4162362631", "4162362940", "8006683174");  
   
   /**
    *
    * @return
    */
   public static List<Manufacturer> getManufacturers() {
      return Arrays.asList(ALC, AMG, GPM);
         
   }   
   
   private static Manufacturer newManufacturer(final String code,
                                               final String name,
                                               final String phoneNumber,
                                               final String faxNumber,
                                               final String tollPhoneNumber) {
      return new Manufacturer(code, name).setFaxNumber(faxNumber)
                                         .setPhoneNumber(phoneNumber)
                                         .setTollFreePhoneNumber(tollPhoneNumber);
   }
}