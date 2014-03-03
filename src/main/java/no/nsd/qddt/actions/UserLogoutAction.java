package no.nsd.qddt.actions;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import no.nsd.qddt.servlets.ServletUtil;

public class UserLogoutAction {
   
   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      HttpSession session = request.getSession();
      session.invalidate();
      
      ServletUtil.redirect("/", request, response);
   }
   
   
}
