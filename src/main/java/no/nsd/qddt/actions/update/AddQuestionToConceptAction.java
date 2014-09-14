package no.nsd.qddt.actions.update;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.nsd.qddt.actions.AbstractAction;
import no.nsd.qddt.model.ModuleVersion;
import no.nsd.qddt.service.ConceptSchemeService;
import no.nsd.qddt.servlets.ServletUtil;

public class AddQuestionToConceptAction extends AbstractAction {

   private ModuleVersion moduleVersion;
   private Integer questionId;
   private Integer conceptId;
   

   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.setRequestAndResponse(request, response);
      this.moduleVersion = (ModuleVersion) request.getAttribute("moduleVersion");

      this.getParams();
      this.executeDaoAndClose();
      this.redirectSuccessPage();
   }

   private void getParams() throws ServletException {
      questionId = ServletUtil.getRequestParamAsInteger(request, "qid");
      conceptId = ServletUtil.getRequestParamAsInteger(request, "cid");
   }
   
   @Override
   protected void executeDao() throws SQLException {
      this.addQuestionToConcept();
   }

   private void addQuestionToConcept() throws SQLException {
      (new ConceptSchemeService(daoManager)).addQuestionToConcept(questionId, conceptId, moduleVersion.getConceptSchemeId());
   }
   
   private void redirectSuccessPage() throws IOException {
      ServletUtil.redirect("/u/conceptscheme?mvid=" + moduleVersion.getId() + "&cid=" + conceptId, request, response);
   }

}
