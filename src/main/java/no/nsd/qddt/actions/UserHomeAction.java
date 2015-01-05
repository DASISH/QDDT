package no.nsd.qddt.actions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import no.nsd.qddt.model.Module;
import no.nsd.qddt.model.Survey;
import no.nsd.qddt.model.User;
import no.nsd.qddt.service.ModuleService;
import no.nsd.qddt.service.SurveyService;
import no.nsd.qddt.servlets.ServletUtil;

public class UserHomeAction extends AbstractAction {

   private User user;
   
   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.setRequestAndResponse(request, response);
      
      this.setUser();
      this.executeDaoAndClose();
      this.forwardPage();
   }

   private void setUser() {
      HttpSession httpSession = request.getSession();
      user = (User) httpSession.getAttribute("user");
   }
   
   @Override
   protected void executeDao() throws SQLException {
      this.setModules();
      this.setSurveys();
   }
   
   private void setModules() throws SQLException {
      List<Module> modules = (new ModuleService(daoManager)).getModules(user);
      request.setAttribute("modules", modules);
   }

   private void setSurveys() throws SQLException {
      List<Survey> surveys = (new SurveyService(daoManager)).getSurveys();
      request.setAttribute("surveys", surveys);
   }
   
   private void forwardPage() throws ServletException, IOException {
      ServletUtil.forward("/WEB-INF/jsp/user_home.jsp", request, response);
   }
   
}
