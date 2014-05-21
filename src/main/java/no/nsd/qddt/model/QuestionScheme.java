package no.nsd.qddt.model;

import java.io.Serializable;

public class QuestionScheme extends Element implements Serializable {

   private String note;
   
   public QuestionScheme() {
   }

   public String getNote() {
      return note;
   }

   public void setNote(String note) {
      this.note = note;
   }
   
   
}
