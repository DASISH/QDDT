package no.nsd.qddt.actions;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.nsd.qddt.servlets.ServletUtil;

public class TitleAction {

   private HttpServletRequest request;
   private HttpServletResponse response;
   
   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.request = request;
      this.response = response;

      this.forwardPage();
   }
   
   
   private void forwardPage() throws ServletException, IOException {
      ServletUtil.forward("/WEB-INF/jsp/title.jsp", request, response);
   }
   
}
