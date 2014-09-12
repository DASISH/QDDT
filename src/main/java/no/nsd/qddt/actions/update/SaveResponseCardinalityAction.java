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

public class SaveResponseCardinalityAction extends AbstractAction {

   private Question question;
   private ModuleVersion moduleVersion;

   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.setRequestAndResponse(request, response);
      this.moduleVersion = (ModuleVersion) request.getAttribute("moduleVersion");

      this.getParamValues();
      this.executeDaoAndClose();
      this.redirectSuccessPage();
   }

   private void getParamValues() throws ServletException {
      question = new Question();
      question.setId(ServletUtil.getRequestParamAsInteger(request, "qid"));
      question.setMinimumResponses(ServletUtil.getRequestParamAsInteger(request, "min"));
      question.setMaximumResponses(ServletUtil.getRequestParamAsInteger(request, "max"));
   }
   
   @Override
   protected void executeDao() throws SQLException {
      this.updateResponseCardinality();
   }

   private void updateResponseCardinality() throws SQLException {
      (new QuestionService(daoManager)).updateResponseCardinality(question);
   }
   
   private void redirectSuccessPage() throws IOException {
      ServletUtil.redirect("/u/question?mvid=" + moduleVersion.getId() + "&qid=" + question.getId() + "&saved", request, response);
   }

}
