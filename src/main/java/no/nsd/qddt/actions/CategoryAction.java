package no.nsd.qddt.actions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.nsd.qddt.model.Category;
import no.nsd.qddt.model.Survey;
import no.nsd.qddt.service.CategoryService;
import no.nsd.qddt.service.SurveyService;
import no.nsd.qddt.servlets.ServletUtil;

public class CategoryAction extends AbstractAction {

   private Integer surveyId;
   
   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.setRequestAndResponse(request, response);
      
      this.setSurveyId();
      this.executeDaoAndClose();
      this.forwardPage();
   }

   private void setSurveyId() {
      surveyId = ServletUtil.getRequestParamAsInteger(request, "sid");
   }
   
   @Override
   protected void executeDao() throws SQLException {
      this.setSurvey();
      this.setCategories();
   }
   
   private void setSurvey() throws SQLException {
      Survey survey = (new SurveyService(daoManager)).getSurvey(surveyId);
      request.setAttribute("survey", survey);
   }

   private void setCategories() throws SQLException {
      List<Category> categories = (new CategoryService(daoManager)).getCategoriesForDefaultSchemeForSurvey(surveyId);
      request.setAttribute("categories", categories);
   }
   
   private void forwardPage() throws ServletException, IOException {
      ServletUtil.forward("/WEB-INF/jsp/category.jsp", request, response);
   }
   
}
