package no.nsd.qddt.actions.update;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.nsd.qddt.actions.AbstractAction;
import no.nsd.qddt.logic.UrnUtil;
import no.nsd.qddt.model.Agency;
import no.nsd.qddt.model.Category;
import no.nsd.qddt.model.ModuleVersion;
import no.nsd.qddt.model.Urn;
import no.nsd.qddt.service.CategoryService;
import no.nsd.qddt.servlets.ServletUtil;

public class SaveCategoryAction extends AbstractAction {

   private Category newCategory;
   private ModuleVersion moduleVersion;
   private String action;

   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.setRequestAndResponse(request, response);
      this.action = request.getParameter("action");

      this.setModuleVersion();
      
      this.createNewQuestion();
      this.executeDaoAndClose();
      this.redirectSuccessPage();
   }

   private void createNewQuestion() throws ServletException {
      newCategory = new Category();
      newCategory.setId(ServletUtil.getRequestParamAsInteger(request, "cid"));
      newCategory.setLabel(request.getParameter("label"));
      newCategory.setLabelShort(request.getParameter("label_short"));
      newCategory.setDescription(request.getParameter("description"));
      newCategory.setVersionDescription(request.getParameter("version_description"));
      newCategory.setVersionUpdated(Boolean.TRUE);
   }
   
   @Override
   protected void executeDao() throws SQLException {
      if (newCategory.getId() == null) {
         this.registerNewCategory();
      } else {
         this.updateCategory();
      }
   }

   private void setModuleVersion() {
      this.moduleVersion = (ModuleVersion) request.getAttribute("moduleVersion");
   }
   
   
   private void registerNewCategory() throws SQLException {
      Urn urn = UrnUtil.createNewUrn();
      Agency agency = new Agency();
      agency.setId(moduleVersion.getModule().getAgency().getId());
      urn.setAgency(agency);
      newCategory.setUrn(urn);
      
      (new CategoryService(daoManager)).registerNewCategoryAndAddToSchemeForModuleVersion(newCategory, moduleVersion);
   }

   private void updateCategory() throws SQLException {
   }

   
   private void redirectSuccessPage() throws IOException {
      ServletUtil.redirect("/u/category?mvid=" + moduleVersion.getId() + "&saved", request, response);
   }


}
