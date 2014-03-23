package no.nsd.qddt.model;

import java.io.Serializable;

public class CommentSource implements Serializable, Comparable<CommentSource> {
   
   private Integer id;
   private String text;
   private String description;
   private Integer sortOrder;
   
   
   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getText() {
      return text;
   }

   public void setText(String text) {
      this.text = text;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public Integer getSortOrder() {
      return sortOrder;
   }

   public void setSortOrder(Integer sortOrder) {
      this.sortOrder = sortOrder;
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
      final CommentSource other = (CommentSource) obj;
      return this.id != null && this.id.equals(other.id);
   }

   @Override
   public String toString() {
      return this.text;
   }

   @Override
   public int compareTo(CommentSource o) {
      if (this.sortOrder != null && o.sortOrder == null) {
         return -1;
      }
      if (this.sortOrder == null && o.sortOrder != null) {
         return 1;
      }
      if (this.sortOrder < o.sortOrder) {
         return -1;
      }
      if (this.sortOrder > o.sortOrder) {
         return 1;
      }
      return this.id.compareTo(o.getId());
   }
   
}
