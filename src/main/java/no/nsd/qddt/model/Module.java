package no.nsd.qddt.model;

import java.io.Serializable;

public class Module implements Serializable {
   
   private Integer id;
   private Agency agency;
   private String urnId;
   private Study study;
   private String name;
   private Boolean repeat;
   
   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public Agency getAgency() {
      return agency;
   }

   public void setAgency(Agency agency) {
      this.agency = agency;
   }

   public String getUrnId() {
      return urnId;
   }

   public void setUrnId(String urnId) {
      this.urnId = urnId;
   }

   public Study getStudy() {
      return study;
   }

   public void setStudy(Study study) {
      this.study = study;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
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
      return this.name;
   }
   
}
