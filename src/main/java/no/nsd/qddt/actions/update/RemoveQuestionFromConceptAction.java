package no.nsd.qddt.actions.update;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.nsd.qddt.actions.AbstractAction;
import no.nsd.qddt.model.Concept;
import no.nsd.qddt.model.ModuleVersion;
import no.nsd.qddt.service.ConceptSchemeService;
import no.nsd.qddt.servlets.ServletUtil;

public class RemoveQuestionFromConceptAction extends AbstractAction {

   private Concept concept;
   private Integer questionId;
   private ModuleVersion moduleVersion;

   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.setRequestAndResponse(request, response);
      this.moduleVersion = (ModuleVersion) request.getAttribute("moduleVersion");

      this.createNewConcept();
      this.executeDaoAndClose();
      this.redirectSuccessPage();
   }

   private void createNewConcept() throws ServletException {
      concept = new Concept();
      concept.setId(ServletUtil.getRequestParamAsInteger(request, "cid"));
      concept.setConceptSchemeId(moduleVersion.getConceptSchemeId());

      questionId = ServletUtil.getRequestParamAsInteger(request, "qid");
   }

   @Override
   protected void executeDao() throws SQLException {
      this.removeQuestionFromConcept();
   }

   private void removeQuestionFromConcept() throws SQLException {
      (new ConceptSchemeService(daoManager)).removeQuestionFromConcept(questionId, concept);
   }
   
   private void redirectSuccessPage() throws IOException {
      String url = "/u/conceptscheme?mvid=" + moduleVersion.getId() + "&cid=" + concept.getId() + "&saved";

      ServletUtil.redirect(url, request, response);
   }

}
