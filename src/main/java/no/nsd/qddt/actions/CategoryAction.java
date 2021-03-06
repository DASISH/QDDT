package no.nsd.qddt.actions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.nsd.qddt.model.Category;
import no.nsd.qddt.model.ModuleVersion;
import no.nsd.qddt.service.CategoryService;
import no.nsd.qddt.servlets.ServletUtil;

public class CategoryAction extends AbstractAction {

   private ModuleVersion moduleVersion;
   private Integer categoryId;
   
   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.setRequestAndResponse(request, response);
      
      this.setModuleVersion();
      this.setCategoryId();
      
      this.executeDaoAndClose();
      this.forwardPage();
   }

   private void setModuleVersion() {
      this.moduleVersion = (ModuleVersion) request.getAttribute("moduleVersion");
   }
   private void setCategoryId() {
      categoryId = ServletUtil.getRequestParamAsInteger(request, "cid");
   }
   
   @Override
   protected void executeDao() throws SQLException {
      this.setCategories();
      this.setCategory();
   }

   private void setCategories() throws SQLException {
      List<Category> categories = (new CategoryService(daoManager)).getCategoriesForCategoryScheme(moduleVersion.getCategorySchemeId());
      request.setAttribute("categories", categories);
   }

   private void setCategory() throws SQLException {
      Category category = (new CategoryService(daoManager)).getCategory(categoryId);
      request.setAttribute("category", category);
   }
   
   private void forwardPage() throws ServletException, IOException {
      ServletUtil.forward("/WEB-INF/jsp/category.jsp", request, response);
   }
   
}
