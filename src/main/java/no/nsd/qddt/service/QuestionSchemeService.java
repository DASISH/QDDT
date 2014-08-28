package no.nsd.qddt.service;

import java.sql.SQLException;
import java.util.List;
import no.nsd.qddt.logic.dao.DaoManager;
import no.nsd.qddt.model.Question;
import no.nsd.qddt.model.QuestionScheme;

public class QuestionSchemeService {

   private final DaoManager daoManager;

   public QuestionSchemeService(DaoManager daoManager) {
      this.daoManager = daoManager;
   }

   public QuestionScheme getQuestionScheme(Integer questionSchemeId) throws SQLException {
      QuestionScheme questionScheme = daoManager.getQuestionSchemeDao().getQuestionScheme(questionSchemeId);
      
      if (questionScheme != null) {
         List<Question> questions = daoManager.getQuestionDao().getQuestionsForScheme(questionSchemeId);
         questionScheme.setQuestions(questions);
         (new CodeListService(daoManager)).addResponseDomainToQuestions(questions);
      }
      
      return questionScheme;
   }

   
   public void registerNewQuestionScheme(QuestionScheme questionScheme) throws SQLException {
      try {
         daoManager.beginTransaction();
         daoManager.getQuestionSchemeDaoUpdate().registerNewQuestionScheme(questionScheme);
         daoManager.getModuleVersionDaoUpdate().updateQuestionScheme(questionScheme);
         daoManager.endTransaction();
      } catch (SQLException e) {
         daoManager.abortTransaction();
         throw e;
      }
   }

   public void updateQuestionScheme(QuestionScheme questionScheme) throws SQLException {
      daoManager.getQuestionSchemeDaoUpdate().updateQuestionScheme(questionScheme);
   }   

}
