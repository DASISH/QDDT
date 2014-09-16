package no.nsd.qddt.service;

import java.sql.SQLException;
import java.util.List;
import no.nsd.qddt.logic.UrnUtil;
import no.nsd.qddt.logic.dao.DaoManager;
import no.nsd.qddt.model.Category;
import no.nsd.qddt.model.CategoryScheme;
import no.nsd.qddt.model.ModuleVersion;
import no.nsd.qddt.model.Urn;

public class CategoryService {

   private final DaoManager daoManager;
   
   public CategoryService(DaoManager daoManager) {
      this.daoManager = daoManager;
   }

   
   public Category getCategory(Integer categoryId) throws SQLException {
      return daoManager.getCategoryDao().getCategory(categoryId);
   }

   public List<Category> getCategoriesForCategoryScheme(Integer categorySchemeId) throws SQLException {
      return daoManager.getCategoryDao().getCategoriesForScheme(categorySchemeId);
   }

   public void registerNewCategoryAndAddToSchemeForModuleVersion(Category category, ModuleVersion moduleVersion) throws SQLException {
      try {
         daoManager.beginTransaction();
         
         Integer categoryId = daoManager.getCategoryDaoUpdate().registerNewCategory(category);
         category.setId(categoryId);
         
         daoManager.getCategorySchemeDaoUpdate().addCategoryToScheme(category, moduleVersion.getCategorySchemeId());
         
         daoManager.endTransaction();
      } catch (SQLException e) {
         daoManager.abortTransaction();
         throw e;
      }
   }

   



}
