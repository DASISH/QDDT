package no.nsd.qddt.actions.update;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.nsd.qddt.actions.AbstractAction;
import no.nsd.qddt.logic.UrnUtil;
import no.nsd.qddt.model.Concept;
import no.nsd.qddt.model.ModuleVersion;
import no.nsd.qddt.model.Urn;
import no.nsd.qddt.service.ConceptService;
import no.nsd.qddt.servlets.ServletUtil;

public class NewConceptAction extends AbstractAction {

   private ModuleVersion moduleVersion;
   private Concept newConcept;

   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.setRequestAndResponse(request, response);
      this.moduleVersion = (ModuleVersion) request.getAttribute("moduleVersion");

      this.createNewConcept();
      this.executeDaoAndClose();
      this.redirectSuccessPage();
   }

   private void createNewConcept() throws ServletException {
      newConcept = new Concept();
      Urn urn = UrnUtil.createNewUrn();
      urn.setAgency(moduleVersion.getModule().getAgency());
      newConcept.setUrn(urn);
      newConcept.setModuleVersionId(moduleVersion.getId());
      newConcept.setVersionUpdated(Boolean.TRUE);
      newConcept.setConceptSchemeId(ServletUtil.getRequestParamAsInteger(request, "csid"));
      newConcept.setParentConceptId(ServletUtil.getRequestParamAsInteger(request, "pcid"));
   }

   @Override
   protected void executeDao() throws SQLException {
      (new ConceptService(daoManager)).registerNewConcept(newConcept);
   }
   
   private void redirectSuccessPage() throws IOException {
      String url = "/u/conceptscheme?mvid=" + moduleVersion.getId() + "&cid=" + newConcept.getId();
      ServletUtil.redirect(url, request, response);
   }

}
