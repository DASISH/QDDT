package no.nsd.qddt.actions;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import no.nsd.qddt.model.User;
import no.nsd.qddt.service.UserService;
import no.nsd.qddt.servlets.ServletUtil;

public class UserLoginAction extends AbstractAction {

   private String username;
   private String password;
   private User user;
   
   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.setRequestAndResponse(request, response);

      this.setRequestParameters();
      this.getUser();
      this.logInUserOrShowError();
   }
   
   private void setRequestParameters() {
      username = request.getParameter("u");
      password = request.getParameter("p");
   }

   private void getUser() throws ServletException {
      if (this.isParameterValuesEmpty()) {
         return; // returns early.
      }
      this.executeDaoAndClose();
   }
   
   private boolean isParameterValuesEmpty() {
      return username == null || username.isEmpty() || password == null || password.isEmpty();
   }

   @Override
   protected void executeDao() throws SQLException {
      this.user = (new UserService(daoManager)).getUser(username, password);
   }

   private void logInUserOrShowError() throws ServletException, IOException {
      if (user == null) {
         this.forwardErrorPage();
      } else {
         this.setSession();
         this.redirectSuccessPage();
      }
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
