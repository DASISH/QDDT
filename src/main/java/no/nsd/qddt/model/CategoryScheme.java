package no.nsd.qddt.model;

import java.io.Serializable;

public class CategoryScheme extends Element implements Serializable {

   private Boolean moduleDefaultScheme;
   private Boolean versionPublished;
   
   
   public CategoryScheme() {
   }

   public Boolean isModuleDefaultScheme() {
      return moduleDefaultScheme;
   }

   public void setModuleDefaultScheme(Boolean moduleDefaultScheme) {
      this.moduleDefaultScheme = moduleDefaultScheme;
   }


   public Boolean isVersionPublished() {
      return versionPublished;
   }

   public void setVersionPublished(Boolean versionPublished) {
      this.versionPublished = versionPublished;
   }

   
   
}
