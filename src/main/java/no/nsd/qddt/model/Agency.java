package no.nsd.qddt.model;

import java.io.Serializable;

public class Agency implements Serializable {

   private Integer id;
   private String urnId;
   private String name;
   private String shortName;


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

   public String getUrnId() {
      return urnId;
   }

   public void setUrnId(String urnId) {
      this.urnId = urnId;
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
      final Agency other = (Agency) obj;
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
