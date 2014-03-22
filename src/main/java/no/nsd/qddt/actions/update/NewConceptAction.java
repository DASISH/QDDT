package no.nsd.qddt.actions.update;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.nsd.qddt.logic.UrnUtil;
import no.nsd.qddt.model.Concept;
import no.nsd.qddt.model.ModuleVersion;
import no.nsd.qddt.model.Urn;
import no.nsd.qddt.service.ConceptService;
import no.nsd.qddt.servlets.ServletUtil;

public class NewConceptAction {

   private ModuleVersion moduleVersion;
   private Concept newConcept;
   private HttpServletRequest request;
   private HttpServletResponse response;

   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.request = request;
      this.response = response;
      this.moduleVersion = (ModuleVersion) request.getAttribute("moduleVersion");

      this.registerNewConcept();
      this.redirectSuccessPage();
   }

   private void registerNewConcept() throws ServletException {
      newConcept = new Concept();
      Urn urn = UrnUtil.createNewUrn();
      urn.setAgency(moduleVersion.getModule().getAgency());
      newConcept.setUrn(urn);
      newConcept.setModuleVersionId(moduleVersion.getId());
      newConcept.setVersionUpdated(Boolean.TRUE);
      newConcept.setConceptSchemeId(ServletUtil.getRequestParamAsInteger(request, "csid"));
      newConcept.setParentConceptId(ServletUtil.getRequestParamAsInteger(request, "pcid"));
      
      ConceptService.registerNewConcept(newConcept);
   }

   
   private void redirectSuccessPage() throws IOException {
      String url = "/u/conceptscheme?mvid=" + moduleVersion.getId() + "&cid=" + newConcept.getId();
      ServletUtil.redirect(url, request, response);
   }

}
