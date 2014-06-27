package no.nsd.qddt.model;

import java.io.Serializable;

public class CategoryScheme extends Element implements Serializable {

   private Integer surveyId;
   private Boolean surveyDefault;
   private Boolean versionPublished;
   
   
   public CategoryScheme() {
   }

   public Integer getSurveyId() {
      return surveyId;
   }

   public void setSurveyId(Integer surveyId) {
      this.surveyId = surveyId;
   }

   public Boolean isSurveyDefault() {
      return surveyDefault;
   }

   public void setSurveyDefault(Boolean surveyDefault) {
      this.surveyDefault = surveyDefault;
   }

   public Boolean isVersionPublished() {
      return versionPublished;
   }

   public void setVersionPublished(Boolean versionPublished) {
      this.versionPublished = versionPublished;
   }

   
   
}
