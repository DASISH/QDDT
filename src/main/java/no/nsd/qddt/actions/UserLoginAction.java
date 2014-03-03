package no.nsd.qddt.actions;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import no.nsd.qddt.model.User;
import no.nsd.qddt.service.UserService;
import no.nsd.qddt.servlets.ServletUtil;

public class UserLoginAction {

   private String username;
   private String password;
   private User user;
   private HttpServletRequest request;
   private HttpServletResponse response;
   
   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.request = request;
      this.response = response;

      this.setRequestParameters();
      
      if (!this.isParameterValuesEmpty()) {      
         this.user = UserService.getUser(username, password);
      }
      
      if (user == null) {
         this.forwardErrorPage();
      } else {
         this.setSession();
         this.redirectSuccessPage();
      }
   }
   
   private void setRequestParameters() {
      username = request.getParameter("u");
      password = request.getParameter("p");
   }
   
   private boolean isParameterValuesEmpty() {
      return username == null || username.isEmpty() || password == null || password.isEmpty();
   }
   
   private void forwardErrorPage() throws ServletException, IOException {
      request.setAttribute("error", true);
      ServletUtil.forward("/WEB-INF/jsp/index.jsp", request, response);
   }
   
   private void setSession() {
      HttpSession session = request.getSession();
      session.setAttribute("user", user);
   }
   
   private void redirectSuccessPage() throws IOException {
      ServletUtil.redirect("/u/", request, response);
   }
   
}
