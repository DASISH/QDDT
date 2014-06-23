package no.nsd.qddt.service;

import java.sql.SQLException;
import java.util.List;
import no.nsd.qddt.logic.dao.DaoManager;
import no.nsd.qddt.model.Category;

public class CategoryService {

   private final DaoManager daoManager;
   
   public CategoryService(DaoManager daoManager) {
      this.daoManager = daoManager;
   }

   
   public Category getCategory(Integer categoryId) throws SQLException {
      return daoManager.getCategoryDao().getCategory(categoryId);
   }

   public List<Category> getCategoriesForDefaultSchemeForSurvey(Integer surveyId) throws SQLException {
      return daoManager.getCategoryDao().getCategoriesForDefaultSchemeForSurvey(surveyId);
   }

   public void registerNewCategory(Category category) throws SQLException {
      try {
         daoManager.beginTransaction();
         
         //Integer categoryId = daoManager.getQuestionDaoUpdate().registerNewQuestion(question);
         //question.setId(questionId);
         
         //daoManager.getQuestionSchemeDaoUpdate().addQuestionToScheme(question);
         //daoManager.getQuestionSchemeDaoUpdate().setQuestionSchemeUpdated(question.getQuestionSchemeId());
         
         daoManager.endTransaction();
      } catch (SQLException e) {
         daoManager.abortTransaction();
         throw e;
      }
   }



}
