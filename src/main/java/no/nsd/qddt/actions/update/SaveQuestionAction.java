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
   private String action;

   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.setRequestAndResponse(request, response);
      this.moduleVersion = (ModuleVersion) request.getAttribute("moduleVersion");
      this.action = request.getParameter("action");

      this.createNewQuestion();
      this.executeDaoAndClose();
      this.redirectSuccessPage();
   }

   private void createNewQuestion() throws ServletException {
      newQuestion = new Question();
      newQuestion.setId(ServletUtil.getRequestParamAsInteger(request, "qid"));
      newQuestion.setName(request.getParameter("name"));
      newQuestion.setQuestionIntent(request.getParameter("question_intent"));
      newQuestion.setQuestionText(request.getParameter("question_text"));
      newQuestion.setQuestionText2(request.getParameter("question_text_2"));
      newQuestion.setVersionDescription(request.getParameter("version_description"));
      newQuestion.setQuestionSchemeId(moduleVersion.getQuestionSchemeId());
      newQuestion.setVersionUpdated(Boolean.TRUE);
   }
   
   @Override
   protected void executeDao() throws SQLException {
      if ("Delete question".equals(action)) {
         this.deleteQuestion();
      } else if (newQuestion.getId() == null) {
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
      
      (new QuestionService(daoManager)).registerNewQuestion(newQuestion);
   }

   private void updateQuestion() throws SQLException {
      (new QuestionService(daoManager)).updateQuestion(newQuestion);
   }

   private void deleteQuestion() throws SQLException {
      (new QuestionService(daoManager)).deleteQuestion(newQuestion);
   }
   
   private void redirectSuccessPage() throws IOException {
      if ("Delete question".equals(action)) {
         ServletUtil.redirect("/u/questionscheme?mvid=" + moduleVersion.getId() + "&saved", request, response);
      } else {
         ServletUtil.redirect("/u/question?mvid=" + moduleVersion.getId() + "&qid=" + newQuestion.getId() + "&saved", request, response);
      }
   }


}
