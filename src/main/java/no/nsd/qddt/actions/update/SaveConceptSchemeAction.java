package no.nsd.qddt.actions.update;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.nsd.qddt.logic.UrnUtil;
import no.nsd.qddt.model.ConceptScheme;
import no.nsd.qddt.model.ModuleVersion;
import no.nsd.qddt.model.Urn;
import no.nsd.qddt.service.ConceptSchemeService;
import no.nsd.qddt.servlets.ServletUtil;

public class SaveConceptSchemeAction {

   private ConceptScheme newConceptScheme;
   private ModuleVersion moduleVersion;
   private HttpServletRequest request;
   private HttpServletResponse response;

   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.request = request;
      this.response = response;
      this.moduleVersion = (ModuleVersion) request.getAttribute("moduleVersion");

      this.createNewConcept();
      this.conceptSchemeAction();
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
   
   private void conceptSchemeAction() throws ServletException {
      if (newConceptScheme.getId() == null) {
         this.registerNewConceptScheme();
      } else {
         this.updateConceptScheme();
      }
   }

   private void registerNewConceptScheme() throws ServletException {
      Urn urn = UrnUtil.createNewUrn();
      urn.setAgency(moduleVersion.getModule().getAgency());
      newConceptScheme.setUrn(urn);
      newConceptScheme.setModuleVersionId(moduleVersion.getId());
      newConceptScheme.setVersionUpdated(Boolean.TRUE);
      
      ConceptSchemeService.registerNewConceptScheme(newConceptScheme);
   }

   private void updateConceptScheme() throws ServletException {
      newConceptScheme.setVersionUpdated(Boolean.TRUE);
      ConceptSchemeService.updateConceptScheme(newConceptScheme);
   }

   private void deleteConcept() throws ServletException {
      
      
      
   }
   
   private void redirectSuccessPage() throws IOException {
      ServletUtil.redirect("/u/conceptscheme?mvid=" + moduleVersion.getId(), request, response);
   }

}
