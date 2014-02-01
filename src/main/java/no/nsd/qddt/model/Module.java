package no.nsd.qddt.model;

import java.io.Serializable;

public class Module implements Serializable {
   
   private Integer id;
   private Integer status;
   private Urn urn;
   private String study;
   private String title;
   private String authors;
   private String authorsAffiliation;
   private String moduleAbstract;
   private Boolean repeat;

   private Integer conceptSchemeId;
   
   
   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
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
   

   public Urn getUrn() {
      return urn;
   }

   public void setUrn(Urn urn) {
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

   public Integer getConceptSchemeId() {
      return conceptSchemeId;
   }

   public void setConceptSchemeId(Integer conceptSchemeId) {
      this.conceptSchemeId = conceptSchemeId;
   }

   public String getVersionText() {
      if (this.isDraft()) {
         return "Draft " + this.getSubMinorVersion();
      } else {
         return urn.getVersion();
      }
   }

   public boolean isDraft() {
      if (this.urn == null || this.urn.getVersion() == null) {
         return false;
      }
      return this.urn.getVersion().startsWith("0.0.");
   }

   public String getSubMinorVersion() {
      try {
         String s = this.urn.getVersion();
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
      final Module other = (Module) obj;
      return this.id != null && this.id.equals(other.id);
   }

   @Override
   public String toString() {
      return this.title;
   }
   
}
