package no.nsd.qddt.actions.update;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.nsd.qddt.actions.AbstractAction;
import no.nsd.qddt.model.ModuleVersion;
import no.nsd.qddt.model.Question;
import no.nsd.qddt.service.QuestionService;
import no.nsd.qddt.servlets.ServletUtil;

public class AddCodeListToQuestionAction extends AbstractAction {

   private ModuleVersion moduleVersion;
   private Question question;

   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.setRequestAndResponse(request, response);
      this.moduleVersion = (ModuleVersion) request.getAttribute("moduleVersion");

      this.getParams();
      this.executeDaoAndClose();
      this.redirectSuccessPage();
   }

   private void getParams() throws ServletException {
      question = new Question();
      question.setId(ServletUtil.getRequestParamAsInteger(request, "qid"));
      question.setCodeListId(ServletUtil.getRequestParamAsInteger(request, "clid"));
   }
   
   @Override
   protected void executeDao() throws SQLException {
      this.updateCodeListForQuestion();
   }

   private void updateCodeListForQuestion() throws SQLException {
      (new QuestionService(daoManager)).updateCodeListForQuestion(question);
   }
   
   private void redirectSuccessPage() throws IOException {
      ServletUtil.redirect("/u/question?mvid=" + moduleVersion.getId() + "&qid=" + question.getId(), request, response);
   }

}
