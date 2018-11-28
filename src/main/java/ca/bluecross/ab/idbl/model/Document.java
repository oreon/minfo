/*
 * $Id: Document.java,v 1.1.2.1 2018/11/15 17:38:00 myang Exp $
 */
package ca.bluecross.ab.idbl.model;

public class Document {
   
   private String _fileName;
   private String _description;
   
   public Document(final String fileName,
                   final String description) {
      _fileName = fileName;
      _description = description;
   }
   
   public String getFileName() {
      return _fileName;
   }
   
   public String getDescription() {
      return _description;
   }
}
