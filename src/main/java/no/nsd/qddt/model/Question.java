package no.nsd.qddt.model;

import java.io.Serializable;

public class Question extends Element implements Serializable {
   
   private String questionText;
   private String questionIntent;
   
   public Question() {
   }

   public String getQuestionText() {
      return questionText;
   }

   public void setQuestionText(String questionText) {
      this.questionText = questionText;
   }

   public String getQuestionIntent() {
      return questionIntent;
   }

   public void setQuestionIntent(String questionIntent) {
      this.questionIntent = questionIntent;
   }
   
   
   
}
