package no.nsd.qddt.actions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.nsd.qddt.model.Category;
import no.nsd.qddt.model.Code;
import no.nsd.qddt.model.ModuleVersion;
import no.nsd.qddt.service.CategoryService;
import no.nsd.qddt.service.CodeService;
import no.nsd.qddt.servlets.ServletUtil;

public class CodeAction extends AbstractAction {

   private ModuleVersion moduleVersion;
   
   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.setRequestAndResponse(request, response);
      this.setModuleVersion();
      
      this.executeDaoAndClose();
      this.forwardPage();
   }

   private void setModuleVersion() {
      moduleVersion = (ModuleVersion) request.getAttribute("moduleVersion");
   }
   
   @Override
   protected void executeDao() throws SQLException {
      this.setCategories();
      this.setCodes();
   }

   private void setCategories() throws SQLException {
      List<Category> categories = (new CategoryService(daoManager)).getCategoriesForCategoryScheme(moduleVersion.getCategorySchemeId());
      request.setAttribute("categories", categories);
   }

   private void setCodes() throws SQLException {
      Map<Integer, List<Code>> codes = (new CodeService(daoManager)).getAllCodesCategoryMap();
      request.setAttribute("codes", codes);
   }
   
   private void forwardPage() throws ServletException, IOException {
      ServletUtil.forward("/WEB-INF/jsp/code.jsp", request, response);
   }
   
}
