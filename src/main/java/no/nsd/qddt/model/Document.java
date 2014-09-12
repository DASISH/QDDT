package no.nsd.qddt.model;

import java.io.Serializable;

public class Document implements Serializable {
   
   private Integer id;
   private Integer moduleVersionId;
   private String name;
   
   
   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public Integer getModuleVersionId() {
      return moduleVersionId;
   }

   public void setModuleVersionId(Integer moduleVersionId) {
      this.moduleVersionId = moduleVersionId;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
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
      final Document other = (Document) obj;
      return this.id != null && this.id.equals(other.id);
   }

   @Override
   public String toString() {
      return this.name;
   }
   
}
