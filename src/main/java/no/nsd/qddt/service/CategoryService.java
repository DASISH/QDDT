package no.nsd.qddt.service;

import java.sql.SQLException;
import java.util.List;
import no.nsd.qddt.logic.dao.DaoManager;
import no.nsd.qddt.model.Category;
import no.nsd.qddt.model.CategoryScheme;

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

   public void registerNewCategoryForSurvey(Category category, Integer surveyId) throws SQLException {
      try {
         daoManager.beginTransaction();
         
         Integer categoryId = daoManager.getCategoryDaoUpdate().registerNewCategory(category);
         category.setId(categoryId);
         
         CategoryScheme cs = daoManager.getCategorySchemeDao().getDefaultCategorySchemeForSurvey(surveyId);
         daoManager.getCategorySchemeDaoUpdate().addCategoryToScheme(category, cs.getId());
         
         daoManager.endTransaction();
      } catch (SQLException e) {
         daoManager.abortTransaction();
         throw e;
      }
   }



}
