package no.nsd.qddt.actions.update;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.nsd.qddt.model.Concept;
import no.nsd.qddt.model.ModuleVersion;
import no.nsd.qddt.service.ConceptService;
import no.nsd.qddt.servlets.ServletUtil;

public class SaveConceptAction {

   private Concept newConcept;
   private ModuleVersion moduleVersion;
   private HttpServletRequest request;
   private HttpServletResponse response;

   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.request = request;
      this.response = response;
      this.moduleVersion = (ModuleVersion) request.getAttribute("moduleVersion");

      this.createNewConcept();
      this.conceptAction();
      this.redirectSuccessPage();
   }

   private void createNewConcept() throws ServletException {
      newConcept = new Concept();
      newConcept.setId(ServletUtil.getRequestParamAsInteger(request, "cid"));
      newConcept.setName(request.getParameter("name"));
      newConcept.setLabel(request.getParameter("label"));
      newConcept.setDescription(request.getParameter("description"));
      newConcept.setRelationshipConcept(request.getParameter("relationship_concept"));
      newConcept.setVersionDescription(request.getParameter("version_description"));
   }
   
   private void conceptAction() throws ServletException {
      String action = request.getParameter("action");
      
      if ("Remove concept".equals(action)) {
         this.deleteConcept();
      } else {
         this.updateConcept();
      }
   }

   private void updateConcept() throws ServletException {
      newConcept.setVersionUpdated(Boolean.TRUE);
      ConceptService.updateConcept(newConcept);
   }

   private void deleteConcept() throws ServletException {
      
   }
   
   private void redirectSuccessPage() throws IOException {
      String url = "/u/conceptscheme?mvid=" + moduleVersion.getId() + "&cid=" + newConcept.getId();
      ServletUtil.redirect(url, request, response);
   }

}
