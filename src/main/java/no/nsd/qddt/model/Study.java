package no.nsd.qddt.model;

import java.io.Serializable;

public class Study implements Serializable {

   private Integer id;
   private String title;
   private String description;

   private Survey survey;

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public Survey getSurvey() {
      return survey;
   }

   public void setSurvey(Survey survey) {
      this.survey = survey;
   }


   @Override
   public int hashCode() {
      if (this.id != null) {
         return this.id;
      } else {
         return 7;
      }
   }

   @Override
   public boolean equals(Object obj) {
      if (obj == null) {
         return false;
      }
      if (getClass() != obj.getClass()) {
         return false;
      }
      final Study other = (Study) obj;
      if (this.id == null || other.id == null) {
         return false;
      }
      return this.id.equals(other.id);
   }

   @Override
   public String toString() {
      return title;
   }
   
   
}
