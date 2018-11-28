/*
 * $Id: AutoCompleteMockup.java,v 1.1.2.2 2018/11/26 15:59:41 myang Exp $
 */
package ca.bluecross.ab.idbl.service;

import static java.util.Arrays.asList;
import org.springframework.stereotype.Service;

@Service("autoCompleteMockup")
public class AutoCompleteMockup implements AutoComplete {

   @Override
   public Results doSearch(final String term) {
      return new Results()
         //.setBrandNames(asList("a:" + term, "b:" + term, "c:" + term))
         .setGenericNames(asList("A:" + term, "B:" + term, "C:" + term))
         .setDinPins(asList("dp1" + term, "dp2" + term, "dp3" + term))
         //.setManufacturerNames(asList("mfr1" + term, "mfr2" + term, "mfr3" + term))
         ;   
   }
}
