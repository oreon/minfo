/*
 * $Id: ReviewDetail.java,v 1.1.2.2 2018/11/09 23:23:35 myang Exp $
 */
package ca.bluecross.ab.idbl.model;

import java.util.Date;
import java.util.List;

public class ReviewDetail {
   
   private String _dinGpPin;
   private int _cdrRecommendationId;
   private Date _decisionEffectiveDate;
   private Date _finalEffectiveDate;
   
   private String _indicationCode;
   private Date _meetingDate;
   
   private String _rationale;
   
   private String _reviewType;
   private Date _submissionCompletionDate;
   private Date _submissionReceivedDate;
   private String _title;
   
   private List<ReviewDocument> _docs;
   
   public ReviewDetail(final String title, final String type) {
      _title = title;
      _reviewType = type;
   }
   
   public String getTitle() {
      return _title;
   }
   
   public String getReviewType() {
      return _reviewType;
   }
   
   public ReviewDetail setCdrRecommendationId(final int recId) {
      _cdrRecommendationId = recId;
      return this;
   }
   
   public int getCdrRecommendationId() {
      return _cdrRecommendationId;
   }
   
   public ReviewDetail setReviewDocuments(final List<ReviewDocument> docs) {
      _docs = docs;
      return this;
   }
   
   public List<ReviewDocument> getReviewDocuments() {
      return _docs;
   }
   
}
