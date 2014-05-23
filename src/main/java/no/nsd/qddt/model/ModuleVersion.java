package no.nsd.qddt.model;

import java.io.Serializable;

public class ModuleVersion implements Serializable {
   
   private Integer id;
   private Module module;
   private Actor actor;
   private Integer status;
   private String urnVersion;
   private String versionNumber;
   private String versionDescription;
   private String title;
   private String authors;
   private String authorsAffiliation;
   private String moduleAbstract;

   private Integer conceptSchemeId;
   private Integer questionSchemeId;

   
   public Module getModule() {
      return module;
   }

   public void setModule(Module module) {
      this.module = module;
   }
   
   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public Actor getActor() {
      return actor;
   }

   public void setActor(Actor actor) {
      this.actor = actor;
   }

   public Integer getStatus() {
      return status;
   }
   public Long getStatusAsLong() {
      if (status == null) {
         return null;
      }
      return status.longValue();
   }

   public void setStatus(Integer status) {
      this.status = status;
   }

   public String getUrnVersion() {
      return urnVersion;
   }

   public void setUrnVersion(String urnVersion) {
      this.urnVersion = urnVersion;
   }

   public String getVersionNumber() {
      return versionNumber;
   }

   public void setVersionNumber(String versionNumber) {
      this.versionNumber = versionNumber;
   }

   public String getVersionDescription() {
      return versionDescription;
   }

   public void setVersionDescription(String versionDescription) {
      this.versionDescription = versionDescription;
   }


   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
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


   public Integer getConceptSchemeId() {
      return conceptSchemeId;
   }

   public void setConceptSchemeId(Integer conceptSchemeId) {
      this.conceptSchemeId = conceptSchemeId;
   }

   public Integer getQuestionSchemeId() {
      return questionSchemeId;
   }

   public void setQuestionSchemeId(Integer questionSchemeId) {
      this.questionSchemeId = questionSchemeId;
   }

   
   
   public String getVersionText() {
      if (this.isDraft()) {
         return "Draft " + this.getSubMinorVersion();
      } else {
         return urnVersion;
      }
   }

   public boolean isDraft() {
      if (this.urnVersion == null) {
         return false;
      }
      return this.urnVersion.startsWith("0.0.");
   }

   public String getSubMinorVersion() {
      try {
         String s = this.urnVersion;
         int index = s.lastIndexOf(".");
         return s.substring(index + 1);
      } catch (Exception ignored) {
         return null;
      }
   }
   
   public String getStatusText() {
      if (this.status == null) {
         return "";
      }
      if (this.status == 1) {
         return "Development";
      }
      if (this.status == 2) {
         return "Comment";
      }
      if (this.status == 3) {
         return "Closed";
      }
      if (this.status == 4) {
         return "Closed - publ. int.";
      }
      if (this.status == 5) {
         return "Closed - publ. ext.";
      }
      return "";
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
      final ModuleVersion other = (ModuleVersion) obj;
      return this.id != null && this.id.equals(other.id);
   }

   @Override
   public String toString() {
      return this.title;
   }
   
}
