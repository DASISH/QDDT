package no.nsd.qddt.actions.update;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.nsd.qddt.actions.AbstractAction;
import no.nsd.qddt.model.Concept;
import no.nsd.qddt.model.ConceptScheme;
import no.nsd.qddt.model.ModuleVersion;
import no.nsd.qddt.service.ConceptSchemeService;
import no.nsd.qddt.service.ConceptService;
import no.nsd.qddt.servlets.ServletUtil;

public class SaveConceptAction extends AbstractAction {

   private Concept newConcept;
   private ModuleVersion moduleVersion;
   private boolean delete = false;

   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.setRequestAndResponse(request, response);
      this.moduleVersion = (ModuleVersion) request.getAttribute("moduleVersion");

      this.createNewConcept();
      this.executeDaoAndClose();
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


   @Override
   protected void executeDao() throws SQLException {
      String action = request.getParameter("action");

      if ("Remove concept".equals(action)) {
         this.deleteConcept();
      } else {
         this.updateConcept();
      }
   }
   
   
   private void updateConcept() throws SQLException {
      newConcept.setVersionUpdated(Boolean.TRUE);
      (new ConceptService(daoManager)).updateConcept(newConcept);
   }

   private void deleteConcept() throws SQLException {
      ConceptScheme conceptScheme = (new ConceptSchemeService(daoManager)).getConceptScheme(moduleVersion.getConceptSchemeId());
      Concept deleteConcept = conceptScheme.getConcept(newConcept.getId());

      if (deleteConcept == null) { // no sub-concepts
         newConcept.setConceptSchemeId(conceptScheme.getId());
         (new ConceptService(daoManager)).deleteConcept(newConcept);
      } else {
         deleteConcept.setConceptSchemeId(conceptScheme.getId());
         (new ConceptService(daoManager)).deleteConcept(deleteConcept);
      }

      delete = true;
   }

   private void redirectSuccessPage() throws IOException {
      String url = "/u/conceptscheme?mvid=" + moduleVersion.getId() + "&cid=" + newConcept.getId() + "&saved";
      if (delete) {
         url = "/u/conceptscheme?mvid=" + moduleVersion.getId();
      }

      ServletUtil.redirect(url, request, response);
   }


}
