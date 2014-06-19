package no.nsd.qddt.actions;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.nsd.qddt.model.Survey;
import no.nsd.qddt.service.SurveyService;
import no.nsd.qddt.servlets.ServletUtil;

public class SurveyAction extends AbstractAction {

   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.request = request;
      this.response = response;

      this.executeDaoAndClose();
      this.forwardPage();
   }
   
   @Override
   protected void executeDao() throws SQLException {
      this.setSurvey();
   }
   
   private void setSurvey() throws SQLException {
      Integer surveyId = ServletUtil.getRequestParamAsInteger(request, "id");
      
      Survey survey = (new SurveyService(daoManager)).getSurvey(surveyId);
      request.setAttribute("survey", survey);
   }
   
   private void forwardPage() throws ServletException, IOException {
      ServletUtil.forward("/WEB-INF/jsp/survey.jsp", request, response);
   }
   
}
