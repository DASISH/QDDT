package no.nsd.qddt.actions;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.nsd.qddt.servlets.ServletUtil;

public class QuestionSchemeAction extends AbstractAction {

   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.setRequestAndResponse(request, response);

      this.forwardPage();
   }

   
   @Override
   protected void executeDao() throws SQLException {
      
   }
   
   
   private void forwardPage() throws ServletException, IOException {
      ServletUtil.forward("/WEB-INF/jsp/question_scheme.jsp", request, response);
   }

   
}
