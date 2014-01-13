package no.nsd.qddt.model;

import java.io.Serializable;

public class Module implements Serializable {
   
   private Integer id;
   private String urn;
   private String study;
   private String title;
   private String authors;
   private String authorsAffiliation;
   private String moduleAbstract;
   private Boolean repeat;

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getUrn() {
      return urn;
   }

   public void setUrn(String urn) {
      this.urn = urn;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getStudy() {
      return study;
   }

   public void setStudy(String study) {
      this.study = study;
   }

   public String getAuthors() {
      return authors;
   }

   public void setAuthors(String authors) {
      this.authors = authors;
   }

   public String getAuthorsAffiliation() {
      return authorsAffiliation;
   }

   public void setAuthorsAffiliation(String authorsAffiliation) {
      this.authorsAffiliation = authorsAffiliation;
   }

   public String getModuleAbstract() {
      return moduleAbstract;
   }

   public void setModuleAbstract(String moduleAbstract) {
      this.moduleAbstract = moduleAbstract;
   }

   public Boolean getRepeat() {
      return repeat;
   }

   public void setRepeat(Boolean repeat) {
      this.repeat = repeat;
   }


   @Override
   public int hashCode() {
      if (this.id == null) {
         return 11;
      } else {
         return this.id;
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
      final Module other = (Module) obj;
      return this.id != null && this.id.equals(other.id);
   }

   @Override
   public String toString() {
      return this.title;
   }
   
}
