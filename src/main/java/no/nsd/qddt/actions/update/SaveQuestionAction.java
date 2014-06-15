package no.nsd.qddt.actions.update;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.nsd.qddt.actions.AbstractAction;
import no.nsd.qddt.logic.UrnUtil;
import no.nsd.qddt.model.ModuleVersion;
import no.nsd.qddt.model.Question;
import no.nsd.qddt.model.Urn;
import no.nsd.qddt.service.QuestionService;
import no.nsd.qddt.servlets.ServletUtil;

public class SaveQuestionAction extends AbstractAction {

   private Question newQuestion;
   private ModuleVersion moduleVersion;

   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.setRequestAndResponse(request, response);
      this.moduleVersion = (ModuleVersion) request.getAttribute("moduleVersion");

      this.createNewQuestion();
      this.executeDaoAndClose();
      this.redirectSuccessPage();
   }

   private void createNewQuestion() throws ServletException {
      newQuestion = new Question();
      newQuestion.setId(ServletUtil.getRequestParamAsInteger(request, "qid"));
      newQuestion.setName(request.getParameter("name"));
      newQuestion.setLabel(request.getParameter("label"));
      newQuestion.setQuestionIntent(request.getParameter("question_intent"));
      newQuestion.setQuestionText(request.getParameter("question_text"));
      newQuestion.setDescription(request.getParameter("description"));
      newQuestion.setVersionDescription(request.getParameter("version_description"));
   }
   
   @Override
   protected void executeDao() throws SQLException {
      if (newQuestion.getId() == null) {
         this.registerNewQuestion();
      } else {
         this.updateQuestion();
      }
   }

   private void registerNewQuestion() throws SQLException {
      Urn urn = UrnUtil.createNewUrn();
      urn.setAgency(moduleVersion.getModule().getAgency());
      newQuestion.setUrn(urn);
      newQuestion.setModuleVersionId(moduleVersion.getId());
      newQuestion.setVersionUpdated(Boolean.TRUE);
      
      (new QuestionService(daoManager)).registerNewQuestion(newQuestion);
   }

   private void updateQuestion() throws SQLException {
      newQuestion.setVersionUpdated(Boolean.TRUE);
      (new QuestionService(daoManager)).updateQuestion(newQuestion);
   }

   
   private void redirectSuccessPage() throws IOException {
      ServletUtil.redirect("/u/questionscheme?mvid=" + moduleVersion.getId() + "&saved", request, response);
   }


}
