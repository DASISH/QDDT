package no.nsd.qddt.actions;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.nsd.qddt.model.ModuleVersion;
import no.nsd.qddt.model.QuestionScheme;
import no.nsd.qddt.service.QuestionSchemeService;
import no.nsd.qddt.servlets.ServletUtil;

public class QuestionSchemeAction extends AbstractAction {

   private ModuleVersion moduleVersion;

   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.setRequestAndResponse(request, response);
      this.moduleVersion = (ModuleVersion) request.getAttribute("moduleVersion");

      this.forwardPage();
   }
   
   @Override
   protected void executeDao() throws SQLException {
      QuestionScheme questionScheme = (new QuestionSchemeService(daoManager)).getQuestionScheme(moduleVersion.getQuestionSchemeId());
      request.setAttribute("questionScheme", questionScheme);
   }
   
   private void forwardPage() throws ServletException, IOException {
      ServletUtil.forward("/WEB-INF/jsp/question_scheme.jsp", request, response);
   }

   
}
