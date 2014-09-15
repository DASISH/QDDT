package no.nsd.qddt.actions.update;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.nsd.qddt.actions.AbstractAction;
import no.nsd.qddt.model.ConceptScheme;
import no.nsd.qddt.model.ModuleVersion;
import no.nsd.qddt.service.ConceptSchemeService;
import no.nsd.qddt.servlets.ServletUtil;

public class SaveConceptSchemeAction extends AbstractAction {

   private ConceptScheme newConceptScheme;
   private ModuleVersion moduleVersion;
   private Integer oldModuleVersionId;

   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.setRequestAndResponse(request, response);
      this.moduleVersion = (ModuleVersion) request.getAttribute("moduleVersion");

      this.createNewConcept();
      this.executeDaoAndClose();
      this.redirectSuccessPage();
   }

   private void createNewConcept() throws ServletException {
      newConceptScheme = new ConceptScheme();
      newConceptScheme.setId(ServletUtil.getRequestParamAsInteger(request, "csid"));
      newConceptScheme.setModuleVersionId(moduleVersion.getId());
      newConceptScheme.setName(request.getParameter("name"));
      newConceptScheme.setLabel(request.getParameter("label"));
      newConceptScheme.setDescription(request.getParameter("description"));
      newConceptScheme.setVersionDescription(request.getParameter("version_description"));
      newConceptScheme.setVersionChangeCode(ServletUtil.getRequestParamAsInteger(request, "version_change_code"));
   }
   
   @Override
   protected void executeDao() throws SQLException {
      if (moduleVersion.getId().equals(oldModuleVersionId)) {
         this.updateConceptScheme();
      } else {
         this.newVersionOfConceptScheme();
      }
   }

   private void updateConceptScheme() throws SQLException {
      (new ConceptSchemeService(daoManager)).updateConceptScheme(newConceptScheme);
   }

   private void newVersionOfConceptScheme() throws SQLException {
      ConceptScheme oldCS = (new ConceptSchemeService(daoManager)).getConceptScheme(newConceptScheme.getId());
      
      newConceptScheme.setUrn(oldCS.getUrn());
      newConceptScheme.setModuleVersionId(moduleVersion.getId());
      (new ConceptSchemeService(daoManager)).registerNewConceptScheme(newConceptScheme);
   }

   
   private void redirectSuccessPage() throws IOException {
      ServletUtil.redirect("/u/conceptscheme?mvid=" + moduleVersion.getId() + "&saved", request, response);
   }


}
