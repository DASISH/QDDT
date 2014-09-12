package no.nsd.qddt.service;

import java.sql.SQLException;
import no.nsd.qddt.logic.dao.DaoManager;
import no.nsd.qddt.model.CodeList;
import no.nsd.qddt.model.Question;

public class QuestionService {

   private final DaoManager daoManager;
   
   public QuestionService(DaoManager daoManager) {
      this.daoManager = daoManager;
   }

   
   public Question getQuestion(Integer questionId) throws SQLException {
      Question question = daoManager.getQuestionDao().getQuestion(questionId);
      if (question == null) {
         return question;
      }
      
      if (question.getCodeListId() != null) {
         question.setCodeList(this.getCodeList(question.getCodeListId()));
      }
      
      return question;
   }

   private CodeList getCodeList(Integer codeListId) throws SQLException {
      CodeList cl = daoManager.getCodeListDao().getCodeList(codeListId);
      if (cl == null) {
         return null;
      }
      
      if (cl.isCombined()) {
         cl.setValidCodeList(this.getCodeList(cl.getValidCodeListId()));
         cl.setMissingCodeList(this.getCodeList(cl.getMissingCodeListId()));
      } else {
         cl.setCodes(daoManager.getCodeDao().getCodesForCodeList(cl.getId()));
      }
      
      return cl;
   }
   
   
   
   public void registerNewQuestion(Question question) throws SQLException {
      try {
         daoManager.beginTransaction();
         
         Integer questionId = daoManager.getQuestionDaoUpdate().registerNewQuestion(question);
         question.setId(questionId);
         
         daoManager.getQuestionSchemeDaoUpdate().addQuestionToScheme(question);
         daoManager.getQuestionSchemeDaoUpdate().setQuestionSchemeUpdated(question.getQuestionSchemeId());
         
         daoManager.endTransaction();
      } catch (SQLException e) {
         daoManager.abortTransaction();
         throw e;
      }
   }

   public void updateQuestion(Question question) throws SQLException {
      try {
         daoManager.beginTransaction();

         daoManager.getQuestionDaoUpdate().updateQuestion(question);
         daoManager.getQuestionSchemeDaoUpdate().setQuestionSchemeUpdated(question.getQuestionSchemeId());
         
         daoManager.endTransaction();
      } catch (SQLException e) {
         daoManager.abortTransaction();
         throw e;
      }
   }

   public void updateResponseCardinality(Question question) throws SQLException {
      try {
         daoManager.beginTransaction();

         daoManager.getQuestionDaoUpdate().updateResponseCardinality(question);
         
         daoManager.endTransaction();
      } catch (SQLException e) {
         daoManager.abortTransaction();
         throw e;
      }
   }

   public void deleteQuestion(Question question) throws SQLException {
      try {
         daoManager.beginTransaction();
         
         daoManager.getQuestionSchemeDaoUpdate().deleteQuestionFromScheme(question);
         daoManager.getQuestionDaoUpdate().deleteQuestion(question);
         daoManager.getQuestionSchemeDaoUpdate().setQuestionSchemeUpdated(question.getQuestionSchemeId());

         daoManager.endTransaction();
      } catch (SQLException e) {
         daoManager.abortTransaction();
         throw e;
      }
   }


   public void updateCodeListForQuestion(Question question) throws SQLException {
      try {
         daoManager.beginTransaction();

         daoManager.getQuestionDaoUpdate().updateCodeListForQuestion(question);
         
         daoManager.endTransaction();
      } catch (SQLException e) {
         daoManager.abortTransaction();
         throw e;
      }
   }
   
   
}
