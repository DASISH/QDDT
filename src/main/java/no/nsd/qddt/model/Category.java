package no.nsd.qddt.model;

import java.io.Serializable;

public class Category extends Element implements Serializable {
   
   private String labelShort;
   
   public Category() {
   }

   public String getLabelShort() {
      return labelShort;
   }

   public void setLabelShort(String labelShort) {
      this.labelShort = labelShort;
   }

   
   
}
