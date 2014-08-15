package no.nsd.qddt.model;

import java.io.Serializable;

public class Code extends Element implements Serializable {
   
   private Integer categoryId;
   private String value;
   private Category category; 
   private Integer sortOrder;
   
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

   public Category getCategory() {
      return category;
   }

   public void setCategory(Category category) {
      this.category = category;
   }

   public Integer getSortOrder() {
      return sortOrder;
   }

   public void setSortOrder(Integer sortOrder) {
      this.sortOrder = sortOrder;
   }

   
   
}
