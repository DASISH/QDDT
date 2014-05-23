package no.nsd.qddt.model;

import java.io.Serializable;
import java.util.List;

public class QuestionScheme extends Element implements Serializable {

   private String note;
   private List<Question> questions;
   
   public QuestionScheme() {
   }

   public String getNote() {
      return note;
   }

   public void setNote(String note) {
      this.note = note;
   }

   public List<Question> getQuestions() {
      return questions;
   }

   public void setQuestions(List<Question> questions) {
      this.questions = questions;
   }
   
   
}
