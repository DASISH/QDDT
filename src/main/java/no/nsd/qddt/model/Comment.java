package no.nsd.qddt.model;

import java.io.Serializable;

public class Comment implements Serializable {
   
   private Integer id;
   private String text;
   private String source;
   
   
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

   public String getSource() {
      return source;
   }

   public void setSource(String source) {
      this.source = source;
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
      final Comment other = (Comment) obj;
      return this.id != null && this.id.equals(other.id);
   }

   @Override
   public String toString() {
      return this.text;
   }
   
}
