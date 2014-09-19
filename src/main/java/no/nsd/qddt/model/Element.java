package no.nsd.qddt.model;

import java.io.Serializable;

public class Element implements Serializable {
   
   
   private Integer id;
   private String name;
   private String label;
   private String description;
   private Urn urn;
   private String versionDescription;
   private Boolean versionUpdated;
   private Integer moduleVersionId;
   private Integer versionChangeCode;
   private Integer versionPublishCode;
   
   
   
   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getLabel() {
      return label;
   }

   public void setLabel(String label) {
      this.label = label;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public Urn getUrn() {
      return urn;
   }

   public void setUrn(Urn urn) {
      this.urn = urn;
   }

   public String getVersionDescription() {
      return versionDescription;
   }

   public void setVersionDescription(String versionDescription) {
      this.versionDescription = versionDescription;
   }

   public Integer getModuleVersionId() {
      return moduleVersionId;
   }

   public void setModuleVersionId(Integer moduleVersionId) {
      this.moduleVersionId = moduleVersionId;
   }

   public Boolean getVersionUpdated() {
      return versionUpdated;
   }

   public void setVersionUpdated(Boolean versionUpdated) {
      this.versionUpdated = versionUpdated;
   }

   public Integer getVersionChangeCode() {
      return versionChangeCode;
   }

   public void setVersionChangeCode(Integer versionChangeCode) {
      this.versionChangeCode = versionChangeCode;
   }

   public Integer getVersionPublishCode() {
      return versionPublishCode;
   }

   public void setVersionPublishCode(Integer versionPublishCode) {
      this.versionPublishCode = versionPublishCode;
   }

   public boolean isPublished() {
      if (versionPublishCode == null) {
         return false;
      }
      return versionPublishCode >= 1;
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
      final Element other = (Element) obj;
      return this.id != null && this.id.equals(other.id);
   }

   @Override
   public String toString() {
      return this.name;
   }
   
}
