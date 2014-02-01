package no.nsd.qddt.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.nsd.qddt.actions.LoginAction;
import no.nsd.qddt.actions.LogoutAction;
import no.nsd.qddt.actions.ModuleAction;
import no.nsd.qddt.actions.ModuleConceptAction;
import no.nsd.qddt.actions.ModuleDocumentAction;
import no.nsd.qddt.actions.ModuleQuestionnaireAction;
import no.nsd.qddt.actions.ModuleReportAction;
import no.nsd.qddt.actions.ModuleSchemeAction;
import no.nsd.qddt.actions.ModuleStatusAction;
import no.nsd.qddt.actions.RegModuleAction;
import no.nsd.qddt.actions.SaveModuleAction;
import no.nsd.qddt.actions.UserHomeAction;

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
      
      String uri = ServletUtil.getUriWithoutJsessionId(request.getRequestURI());
      String context = request.getContextPath();
      
      if (uri.equals(context + "/")) {
         ServletUtil.forward("/WEB-INF/jsp/index.jsp", request, response);
      }
      
      else if (uri.equals(context + "/login")) {
         new LoginAction().process(request, response);
      }
      else if (uri.equals(context + "/logout")) {
         new LogoutAction().process(request, response);
      }

      else if (uri.equals(context + "/u/")) {
         new UserHomeAction().process(request, response);
      }

      else if (uri.equals(context + "/u/r/regmodule")) {
         new RegModuleAction().process(request, response);
      }
      else if (uri.equals(context + "/u/r/moduledoc")) {
         new ModuleDocumentAction().process(request, response);
      }
      else if (uri.equals(context + "/u/r/moduleconcept")) {
         new ModuleConceptAction().process(request, response);
      }
      else if (uri.equals(context + "/u/r/modulescheme")) {
         new ModuleSchemeAction().process(request, response);
      }
      else if (uri.equals(context + "/u/r/modulequest")) {
         new ModuleQuestionnaireAction().process(request, response);
      }
      else if (uri.equals(context + "/u/r/modulereport")) {
         new ModuleReportAction().process(request, response);
      }
      else if (uri.equals(context + "/u/r/modulestatus")) {
         new ModuleStatusAction().process(request, response);
      }

      
      else if (uri.equals(context + "/u/r/savemodule")) {
         new SaveModuleAction().process(request, response);
      }
      else if (uri.equals(context + "/u/module")) {
         new ModuleAction().process(request, response);
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
