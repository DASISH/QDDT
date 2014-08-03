package no.nsd.qddt.model;

import java.io.Serializable;

public class Code extends Element implements Serializable {
   
   private Integer categoryId;
   private String value;
   
   public Code() {
   }

   public Integer getCategoryId() {
      return categoryId;
   }

   public void setCategoryId(Integer categoryId) {
      this.categoryId = categoryId;
   }

   public String getValue() {
      return value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   
   
}
