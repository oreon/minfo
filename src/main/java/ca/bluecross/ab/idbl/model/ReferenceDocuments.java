/*
 * $Id: ReferenceDocuments.java,v 1.1.2.2 2018/11/16 18:05:43 myang Exp $
 */
package ca.bluecross.ab.idbl.model;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

/**
 * The instance of {@code ReferenceDocuments} contains the reference documents,
 * which are stored in the table {@code IDBL_REF_INFO_PDF_CFG}. 
 */
public class ReferenceDocuments {
   
   private final List<Document> _policyGuidelineForms = new ArrayList<>();
   private final List<Document> _specAuthForms = new ArrayList<>();
   private final List<Document> _prescriberRegistrationForms = new ArrayList<>();
   private final List<Document> _physicianForms = new ArrayList<>();
   private final List<Document> _physicianCommentsForms = new ArrayList<>();
   private final List<Document> _recentUpdatesForms = new ArrayList<>();
   private final List<Document> _dblReports = new ArrayList<>();
   
   private Document _glossary, _dblSec1Prpol, _palliativePage1, _dblSec2;
   
   public ReferenceDocuments put(final String groupingCode, final Document doc) {      
      switch (groupingCode) {
      case "policies.and.guidelines.form":        _policyGuidelineForms.add(doc); break;
      case "special.authorization.form":          _specAuthForms.add(doc); break;
      case "recent.updates.to.drug.benefit.list": _recentUpdatesForms.add(doc); break;
      case "prescriber.registration.form":        _prescriberRegistrationForms.add(doc); break;
      case "physician.form":                      _physicianForms.add(doc); break;
      case "phy.comments.form":                   _physicianCommentsForms.add(doc); break;
      case "dbl.reports":                         _dblReports.add(doc); break;
      case "idbl.glossary.pdf":                   _glossary = doc; break;
      case "dbl.sec1.prpol.pdf":                  _dblSec1Prpol = doc; break;
      case "palliative.page1.pdf":                _palliativePage1 = doc; break;
      case "dbl.sec2.pdf":                        _dblSec2 = doc; break;
      }
      
      return this;                                         
   }
   
   public List<Document> getPolicyGuidelineForms() {
      return unmodifiableList(_policyGuidelineForms);
   }
   
   public List<Document> getSpecialAuthorizationForms() {
      return unmodifiableList(_specAuthForms);
   }
   
   public List<Document> getPrescriberRegistrationForms() {
      return unmodifiableList(_prescriberRegistrationForms);
   }
   
   public List<Document> getPhysicianForms() {
      return unmodifiableList(_physicianForms);
   }   
   
   public List<Document> getPhysicianCommentsForms() {
      return unmodifiableList(_physicianCommentsForms);
   }      
   
   public List<Document> getRecentUpdatesForms() {
      return unmodifiableList(_recentUpdatesForms);
   }      
   
   public List<Document> getDblReports() {
      return unmodifiableList(_dblReports);
   }   
   
   public Document getIdblGlossary() {
      return _glossary;
   }
   
   public Document getDblSec1Prpol() {
      return _dblSec1Prpol;
   }
   
   public Document getPalliativePage1() {
      return _palliativePage1;
   }
   
   public Document getDblSec2() {
      return _dblSec2;
   }   
}
