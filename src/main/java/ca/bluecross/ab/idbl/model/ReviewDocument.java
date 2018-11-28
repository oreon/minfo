/*
 * $Id: ReviewDocument.java,v 1.1.2.1 2018/11/09 23:23:35 myang Exp $
 */
package ca.bluecross.ab.idbl.model;

/**
 * IDBL_REVIEW_DOCUMENTS
 */
public class ReviewDocument {
   
   private byte[] _doc;
   private int _cdrRecommendationId;
   private String _contentType;
   private int _docId;
   private String _fileName;
   private String _fileType;
   
   public ReviewDocument(final int docId) {
      _docId = docId;
   }
   
   public ReviewDocument setFileName(final String fileName) {
      _fileName = fileName;
      return this;
   }
   
   public String getFileName() {
      return _fileName;
   }
   
   public ReviewDocument setFileType(final String fileType) {
      _fileType = fileType;
      return this;
   }
   
   public String getFileType() {
      return _fileType;
   }  
   
   public ReviewDocument setContentType(final String contentType) {
      _contentType = contentType;
      return this;
   }
   
   public String getContentType() {
      return _contentType;
   }     
}
