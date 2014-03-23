package no.nsd.qddt.model;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {
   
   private Integer id;
   private Integer moduleVersionId;
   private Urn elementUrn;
   private Integer elementId;
   
   private String text;
   private Date date;
   private Integer sourceId;
   private Actor actor;
   private User user;
   
   
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

   public Integer getModuleVersionId() {
      return moduleVersionId;
   }

   public void setModuleVersionId(Integer moduleVersionId) {
      this.moduleVersionId = moduleVersionId;
   }

   public Urn getElementUrn() {
      return elementUrn;
   }

   public void setElementUrn(Urn elementUrn) {
      this.elementUrn = elementUrn;
   }


   public Integer getElementId() {
      return elementId;
   }

   public void setElementId(Integer elementId) {
      this.elementId = elementId;
   }

   public Date getDate() {
      return date;
   }

   public java.sql.Date getSqlDate() {
      if (date == null) {
         return null;
      }
      java.sql.Date sqlDate = new java.sql.Date(date.getTime());
      return sqlDate;
   }
   
   
   public void setDate(Date date) {
      this.date = date;
   }

   public Integer getSourceId() {
      return sourceId;
   }

   public void setSourceId(Integer sourceId) {
      this.sourceId = sourceId;
   }

   public Actor getActor() {
      return actor;
   }

   public void setActor(Actor actor) {
      this.actor = actor;
   }

   public User getUser() {
      return user;
   }

   public void setUser(User user) {
      this.user = user;
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
