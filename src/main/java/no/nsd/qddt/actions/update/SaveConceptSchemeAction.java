package no.nsd.qddt.actions.update;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.nsd.qddt.actions.AbstractAction;
import no.nsd.qddt.logic.UrnUtil;
import no.nsd.qddt.model.ConceptScheme;
import no.nsd.qddt.model.ModuleVersion;
import no.nsd.qddt.model.Urn;
import no.nsd.qddt.service.ConceptSchemeService;
import no.nsd.qddt.servlets.ServletUtil;

public class SaveConceptSchemeAction extends AbstractAction {

   private ConceptScheme newConceptScheme;
   private ModuleVersion moduleVersion;

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
      newConceptScheme.setName(request.getParameter("name"));
      newConceptScheme.setLabel(request.getParameter("label"));
      newConceptScheme.setDescription(request.getParameter("description"));
      newConceptScheme.setVersionDescription(request.getParameter("version_description"));
   }
   
   @Override
   protected void executeDao() throws SQLException {
      if (newConceptScheme.getId() == null) {
         this.registerNewConceptScheme();
      } else {
         this.updateConceptScheme();
      }
   }

   private void registerNewConceptScheme() throws SQLException {
      Urn urn = UrnUtil.createNewUrn();
      urn.setAgency(moduleVersion.getModule().getAgency());
      newConceptScheme.setUrn(urn);
      newConceptScheme.setModuleVersionId(moduleVersion.getId());
      newConceptScheme.setVersionUpdated(Boolean.TRUE);
      
      (new ConceptSchemeService(daoManager)).registerNewConceptScheme(newConceptScheme);
   }

   private void updateConceptScheme() throws SQLException {
      newConceptScheme.setVersionUpdated(Boolean.TRUE);
      (new ConceptSchemeService(daoManager)).updateConceptScheme(newConceptScheme);
   }

   
   private void redirectSuccessPage() throws IOException {
      ServletUtil.redirect("/u/conceptscheme?mvid=" + moduleVersion.getId() + "&saved", request, response);
   }


}
