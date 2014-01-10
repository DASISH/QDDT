package no.nsd.qddt.model;

import java.io.Serializable;

public class User implements Serializable {

   private Integer id;
   private String username;


   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }
   
   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
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
      final User other = (User) obj;
      if (this.id == null || other.id == null) {
         return false;
      }
      return this.id.equals(other.id);
   }

   @Override
   public String toString() {
      return username;
   }
   
   
}
