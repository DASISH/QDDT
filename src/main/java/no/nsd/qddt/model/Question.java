package no.nsd.qddt.model;

import java.io.Serializable;

public class Question extends Element implements Serializable {
   
   private String questionText;
   private String questionText2;
   private String questionIntent;
   private Integer questionSchemeId;
   private Integer questionOrder;
   private Integer minimumResponses;
   private Integer maximumResponses;
   
   private Integer codeListId;
   private CodeList codeList;
   
   public Question() {
   }

   public String getQuestionText() {
      return questionText;
   }

   public void setQuestionText(String questionText) {
      this.questionText = questionText;
   }

   public String getQuestionText2() {
      return questionText2;
   }

   public void setQuestionText2(String questionText2) {
      this.questionText2 = questionText2;
   }
   
   public String getQuestionIntent() {
      return questionIntent;
   }

   public void setQuestionIntent(String questionIntent) {
      this.questionIntent = questionIntent;
   }

   public Integer getQuestionSchemeId() {
      return questionSchemeId;
   }

   public void setQuestionSchemeId(Integer questionSchemeId) {
      this.questionSchemeId = questionSchemeId;
   }

   public Integer getQuestionOrder() {
      return questionOrder;
   }

   public void setQuestionOrder(Integer questionOrder) {
      this.questionOrder = questionOrder;
   }

   public Integer getMinimumResponses() {
      return minimumResponses;
   }

   public void setMinimumResponses(Integer minimumResponses) {
      this.minimumResponses = minimumResponses;
   }

   public Integer getMaximumResponses() {
      return maximumResponses;
   }

   public void setMaximumResponses(Integer maximumResponses) {
      this.maximumResponses = maximumResponses;
   }

   public Integer getCodeListId() {
      return codeListId;
   }

   public void setCodeListId(Integer codeListId) {
      this.codeListId = codeListId;
   }

   public CodeList getCodeList() {
      return codeList;
   }

   public void setCodeList(CodeList codeList) {
      this.codeList = codeList;
   }
   
   
   @Override
   public String toString() {
      String s = "";
      if (questionText != null) {
         s += questionText;
      }
      if (questionText2 != null) {
         s += questionText2;
      }
      return s;
   }
   
}
