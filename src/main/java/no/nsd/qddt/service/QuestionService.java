package no.nsd.qddt.service;

import java.sql.SQLException;
import java.util.List;
import no.nsd.qddt.logic.dao.DaoManager;
import no.nsd.qddt.model.Concept;
import no.nsd.qddt.model.Question;

public class QuestionService {

   private final DaoManager daoManager;
   
   public QuestionService(DaoManager daoManager) {
      this.daoManager = daoManager;
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


}
