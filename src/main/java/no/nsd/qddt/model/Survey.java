package no.nsd.qddt.model;

import java.io.Serializable;

public class Survey implements Serializable {

   private Integer id;
   private String name;
   private String shortName;
   private Integer versionLevels;

   private Integer agencyId;
   

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

   public String getShortName() {
      return shortName;
   }

   public void setShortName(String shortName) {
      this.shortName = shortName;
   }

   public Integer getAgencyId() {
      return agencyId;
   }

   public void setAgencyId(Integer agencyId) {
      this.agencyId = agencyId;
   }

   public Integer getVersionLevels() {
      return versionLevels;
   }

   public void setVersionLevels(Integer versionLevels) {
      this.versionLevels = versionLevels;
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
      final Survey other = (Survey) obj;
      if (this.id == null || other.id == null) {
         return false;
      }
      return this.id.equals(other.id);
   }

   @Override
   public String toString() {
      return name;
   }
   
   
}
