/*
 * $Id: PharmaTheraClassification.java,v 1.1.2.2 2018/11/21 17:28:32 myang Exp $
 */
package ca.bluecross.ab.idbl.model;

public class PharmaTheraClassification {
   private String _id;
   private String _group;
   private String _class;
   private String _sub;
   private String _subSub;
   private String _desc;
   
   public PharmaTheraClassification(final String id) {
      _id = id;
   }
   
   public String getId() {
      return _id;
   }
   
   public String getGroup() {
      return _group;
   }
   
   public PharmaTheraClassification setGroup(final String group) {
      _group = group;
      return this;
   }
   
   public String getPtcClass() {
      return _class;
   }
   
   public PharmaTheraClassification setPtcClass(final String clz) {
      _class = clz;
      return this;
   }
   
   public String getSub() {
      return _sub;
   }
   
   public PharmaTheraClassification setSub(final String sub) {
      _sub = sub;
      return this;
   }
   
   public String getSubSub() {
      return _subSub;
   }
   
   public PharmaTheraClassification setSubSub(final String subSub) {
      _subSub = subSub;
      return this;
   }   
   
   public String getDescription() {
      return _desc;
   }
   
   public PharmaTheraClassification setDescription(final String desc) {
      _desc = desc;
      return this;
   }    
   
   @Override
   public String toString() {
      return String.format("%-9s%s", _id, _desc).replace(" ", "&nbsp;");
   }
}
