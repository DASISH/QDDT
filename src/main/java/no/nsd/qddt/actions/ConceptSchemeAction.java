package no.nsd.qddt.actions;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.nsd.qddt.model.Concept;
import no.nsd.qddt.model.ConceptScheme;
import no.nsd.qddt.model.ModuleVersion;
import no.nsd.qddt.service.ConceptSchemeService;
import no.nsd.qddt.service.ConceptService;
import no.nsd.qddt.servlets.ServletUtil;

public class ConceptSchemeAction extends AbstractAction {

   private Integer conceptId;
   private ModuleVersion moduleVersion;

   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.setRequestAndResponse(request, response);

      this.getRequestParams();
      this.executeDaoAndClose();
      this.forwardPage();
   }

   private void getRequestParams() {
      this.conceptId = ServletUtil.getRequestParamAsInteger(request, "cid");
      this.moduleVersion = (ModuleVersion) request.getAttribute("moduleVersion");
   }
   
   @Override
   protected void executeDao() throws SQLException {
      this.setConceptScheme();
      this.setConcept();
   }

   private void setConceptScheme() throws SQLException {
      ConceptScheme conceptScheme = (new ConceptSchemeService(daoManager)).getConceptScheme(moduleVersion.getConceptSchemeId());
      request.setAttribute("conceptScheme", conceptScheme);
   }

   private void setConcept() throws SQLException {
      if (this.conceptId != null) {
         Concept c = (new ConceptService(daoManager)).getConcept(conceptId);
         request.setAttribute("concept", c);
      }
   }

   private void forwardPage() throws ServletException, IOException {
      ServletUtil.forward("/WEB-INF/jsp/concept_scheme.jsp", request, response);
   }

}
