package no.nsd.qddt.actions;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.nsd.qddt.model.Question;
import no.nsd.qddt.service.QuestionService;
import no.nsd.qddt.servlets.ServletUtil;

public class QuestionAction extends AbstractAction {

   private Integer questionId;
   
   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.setRequestAndResponse(request, response);
      
      this.getRequestParams();
      this.executeDaoAndClose();
      this.forwardPage();
   }

   private void getRequestParams() {
      this.questionId = ServletUtil.getRequestParamAsInteger(request, "qid");
   }
   
   @Override
   protected void executeDao() throws SQLException {
      this.setQuestion();
   }
   
   private void setQuestion() throws SQLException {
      Question question = (new QuestionService(daoManager)).getQuestion(questionId);
      request.setAttribute("question", question);
   }
   
   private void forwardPage() throws ServletException, IOException {
      ServletUtil.forward("/WEB-INF/jsp/question.jsp", request, response);
   }

   
}
