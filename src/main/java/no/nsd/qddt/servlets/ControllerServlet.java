package no.nsd.qddt.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import no.nsd.qddt.actions.UserLoginAction;
import no.nsd.qddt.actions.UserLogoutAction;
import no.nsd.qddt.actions.HistoryAction;
import no.nsd.qddt.actions.ConceptSchemeAction;
import no.nsd.qddt.actions.DocumentAction;
import no.nsd.qddt.actions.InstrumentAction;
import no.nsd.qddt.actions.ModuleAction;
import no.nsd.qddt.actions.ReportAction;
import no.nsd.qddt.actions.QuestionSchemeAction;
import no.nsd.qddt.actions.StatusAction;
import no.nsd.qddt.actions.TitleAction;
import no.nsd.qddt.actions.update.SaveModuleAction;
import no.nsd.qddt.actions.UserHomeAction;
import no.nsd.qddt.model.Actor;
import no.nsd.qddt.model.Module;
import no.nsd.qddt.model.ModuleVersion;
import no.nsd.qddt.model.User;
import no.nsd.qddt.service.ActorService;
import no.nsd.qddt.service.ModuleService;
import no.nsd.qddt.service.ModuleVersionService;

public class ControllerServlet extends HttpServlet {

   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      processRequest(request, response);
   }

   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      processRequest(request, response);
   }

   protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("UTF-8");
      response.setContentType("text/html;charset=UTF-8");
      
      
      this.doFilterModule(request);
      this.urlMapping(request, response);
   }
   
   private void doFilterModule(HttpServletRequest request) throws ServletException {
      Integer moduleVersionId = ServletUtil.getRequestParamAsInteger(request, "mvid");

      if (moduleVersionId == null) {
         return;
      }
      
      HttpSession httpSession = request.getSession();
      User user = (User) httpSession.getAttribute("user");
      
      ModuleVersion moduleVersion = ModuleVersionService.getModuleVersion(moduleVersionId);
      Module module = ModuleService.getModule(moduleVersion.getModule().getId());
      moduleVersion.setModule(module);
      Actor actor = ActorService.getActorForUserAndModule(user.getId(), module.getId());
      request.setAttribute("moduleVersion", moduleVersion);
      request.setAttribute("actor", actor);
   }
   
   private void urlMapping(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String uri = ServletUtil.getUriWithoutJsessionId(request.getRequestURI());
      String context = request.getContextPath();
      
      if (uri.equals(context + "/")) { ServletUtil.forward("/WEB-INF/jsp/index.jsp", request, response); }
      
      else if (uri.equals(context + "/login")) { new UserLoginAction().process(request, response); }
      else if (uri.equals(context + "/logout")) { new UserLogoutAction().process(request, response); }

      else if (uri.equals(context + "/u/")) { new UserHomeAction().process(request, response); }

      else if (uri.equals(context + "/u/title")) { new TitleAction().process(request, response); }
      else if (uri.equals(context + "/u/document")) { new DocumentAction().process(request, response); }
      else if (uri.equals(context + "/u/conceptscheme")) { new ConceptSchemeAction().process(request, response); }
      else if (uri.equals(context + "/u/questionscheme")) { new QuestionSchemeAction().process(request, response); }
      else if (uri.equals(context + "/u/instrument")) { new InstrumentAction().process(request, response); }
      else if (uri.equals(context + "/u/report")) { new ReportAction().process(request, response); }
      else if (uri.equals(context + "/u/status")) { new StatusAction().process(request, response); }
      else if (uri.equals(context + "/u/versioninfo")) { ServletUtil.forward("/WEB-INF/jsp/version_info.jsp", request, response); }

      
      else if (uri.equals(context + "/u/history")) { new HistoryAction().process(request, response); }

      else if (uri.equals(context + "/u/module")) { new ModuleAction().process(request, response); }

      else if (uri.equals(context + "/u/r/savemodule")) { new SaveModuleAction().process(request, response); }
      
      else { ServletUtil.forward("/WEB-INF/jsp/error/404.jsp", request, response); }
      
   }
   
   
   
   
   @Override
   public String getServletInfo() {
      return "qddt";
   }

}
