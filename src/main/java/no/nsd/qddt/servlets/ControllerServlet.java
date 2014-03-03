package no.nsd.qddt.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.nsd.qddt.actions.UserLoginAction;
import no.nsd.qddt.actions.UserLogoutAction;
import no.nsd.qddt.actions.HistoryAction;
import no.nsd.qddt.actions.ConceptSchemeAction;
import no.nsd.qddt.actions.DocumentAction;
import no.nsd.qddt.actions.InstrumentAction;
import no.nsd.qddt.actions.ReportAction;
import no.nsd.qddt.actions.QuestionSchemeAction;
import no.nsd.qddt.actions.StatusAction;
import no.nsd.qddt.actions.TitleAction;
import no.nsd.qddt.actions.update.SaveModuleAction;
import no.nsd.qddt.actions.UserHomeAction;
import no.nsd.qddt.model.Module;
import no.nsd.qddt.service.ModuleService;

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
      
      
      this.setModule(request);
      this.urlMapping(request, response);
   }
   
   private void setModule(HttpServletRequest request) throws ServletException {
      Integer moduleId = ServletUtil.getRequestParamAsInteger(request, "mid");

      if (moduleId == null) {
         return;
      }
      
      Module module = ModuleService.getModule(moduleId);
      request.setAttribute("module", module);
   }
   
   private void urlMapping(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String uri = ServletUtil.getUriWithoutJsessionId(request.getRequestURI());
      String context = request.getContextPath();
      
      if (uri.equals(context + "/")) {
         ServletUtil.forward("/WEB-INF/jsp/index.jsp", request, response);
      }
      
      else if (uri.equals(context + "/login")) {
         new UserLoginAction().process(request, response);
      }
      else if (uri.equals(context + "/logout")) {
         new UserLogoutAction().process(request, response);
      }

      else if (uri.equals(context + "/u/")) {
         new UserHomeAction().process(request, response);
      }

      else if (uri.equals(context + "/u/title")) {
         new TitleAction().process(request, response);
      }
      else if (uri.equals(context + "/u/document")) {
         new DocumentAction().process(request, response);
      }
      else if (uri.equals(context + "/u/conceptscheme")) {
         new ConceptSchemeAction().process(request, response);
      }
      else if (uri.equals(context + "/u/questionscheme")) {
         new QuestionSchemeAction().process(request, response);
      }
      else if (uri.equals(context + "/u/instrument")) {
         new InstrumentAction().process(request, response);
      }
      else if (uri.equals(context + "/u/report")) {
         new ReportAction().process(request, response);
      }
      else if (uri.equals(context + "/u/status")) {
         new StatusAction().process(request, response);
      }

      
      else if (uri.equals(context + "/u/history")) {
         new HistoryAction().process(request, response);
      }

      else if (uri.equals(context + "/u/r/savemodule")) {
         new SaveModuleAction().process(request, response);
      }
      
      else {
         ServletUtil.forward("/WEB-INF/jsp/error/404.jsp", request, response);
      }
   }
   
   
   
   
   @Override
   public String getServletInfo() {
      return "qddt";
   }

}
