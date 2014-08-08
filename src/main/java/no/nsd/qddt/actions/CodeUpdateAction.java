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

public class CodeUpdateAction extends AbstractAction {

   private ModuleVersion moduleVersion;
   private Integer codeId;
   
   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.setRequestAndResponse(request, response);
      this.setMembers();
      
      this.executeDaoAndClose();
      
      this.forwardPage();
   }

   private void setMembers() {
      moduleVersion = (ModuleVersion) request.getAttribute("moduleVersion");
      codeId = ServletUtil.getRequestParamAsInteger(request, "cid");
   }
   
   @Override
   protected void executeDao() throws SQLException {
      this.setCategories();
      this.setCode();
   }

   private void setCategories() throws SQLException {
      List<Category> categories = (new CategoryService(daoManager)).getCategoriesForCategoryScheme(moduleVersion.getCategorySchemeId());
      request.setAttribute("categories", categories);
   }
   
   private void setCode() throws SQLException {
      Code code = (new CodeService(daoManager)).getCode(codeId);
      request.setAttribute("code", code);
   }
   
   private void forwardPage() throws ServletException, IOException {
      ServletUtil.forward("/WEB-INF/jsp/code_update.jsp", request, response);
   }
   
}
