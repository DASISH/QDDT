package no.nsd.qddt.actions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.nsd.qddt.model.Concept;
import no.nsd.qddt.model.ModuleVersion;
import no.nsd.qddt.model.Question;
import no.nsd.qddt.service.ConceptService;
import no.nsd.qddt.service.QuestionService;
import no.nsd.qddt.servlets.ServletUtil;

public class ConceptQuestionAction extends AbstractAction {

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
      List<Question> questions = (new QuestionService(daoManager)).getQuestionsForScheme(moduleVersion.getQuestionSchemeId());
      request.setAttribute("questions", questions);
   }

   private void setConcept() throws SQLException {
      Concept concept = (new ConceptService(daoManager)).getConcept(conceptId);
      request.setAttribute("concept", concept);
   }

   private void forwardPage() throws ServletException, IOException {
      ServletUtil.forward("/WEB-INF/jsp/concept_question.jsp", request, response);
   }

}
