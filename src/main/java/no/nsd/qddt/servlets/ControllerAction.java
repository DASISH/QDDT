package no.nsd.qddt.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.nsd.qddt.actions.CategoryAction;
import no.nsd.qddt.actions.ConceptSchemeAction;
import no.nsd.qddt.actions.DocumentAction;
import no.nsd.qddt.actions.HistoryAction;
import no.nsd.qddt.actions.InstrumentAction;
import no.nsd.qddt.actions.ModuleAction;
import no.nsd.qddt.actions.QuestionAction;
import no.nsd.qddt.actions.QuestionSchemeAction;
import no.nsd.qddt.actions.ReportAction;
import no.nsd.qddt.actions.StatusAction;
import no.nsd.qddt.actions.SurveyAction;
import no.nsd.qddt.actions.TitleAction;
import no.nsd.qddt.actions.UserHomeAction;
import no.nsd.qddt.actions.UserLoginAction;
import no.nsd.qddt.actions.UserLogoutAction;
import no.nsd.qddt.actions.update.NewCommentAction;
import no.nsd.qddt.actions.update.NewConceptAction;
import no.nsd.qddt.actions.update.NewModuleVersionAction;
import no.nsd.qddt.actions.update.SaveCategoryAction;
import no.nsd.qddt.actions.update.SaveConceptAction;
import no.nsd.qddt.actions.update.SaveConceptSchemeAction;
import no.nsd.qddt.actions.update.SaveModuleAction;
import no.nsd.qddt.actions.update.SaveQuestionAction;
import no.nsd.qddt.actions.update.SaveQuestionSchemeAction;
import no.nsd.qddt.actions.update.SaveTitleAction;
import no.nsd.qddt.actions.update.SaveVersionInfoAction;

public class ControllerAction {
   
   private final HttpServletRequest request;
   private final HttpServletResponse response;
   
   public ControllerAction(HttpServletRequest request, HttpServletResponse response) {
      this.request = request;
      this.response = response;
   }
   
   public void urlMapping() throws ServletException, IOException {
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
      else if (uri.equals(context + "/u/comment")) { ServletUtil.forward("/WEB-INF/jsp/comment.jsp", request, response); }
      
      else if (uri.equals(context + "/u/history")) { new HistoryAction().process(request, response); }

      else if (uri.equals(context + "/u/module")) { new ModuleAction().process(request, response); }

      else if (uri.equals(context + "/u/question")) { new QuestionAction().process(request, response); }
      
      else if (uri.equals(context + "/u/survey")) { new SurveyAction().process(request, response); }
      else if (uri.equals(context + "/u/category")) { new CategoryAction().process(request, response); }
      
      
      else if (uri.equals(context + "/u/r/savemodule")) { new SaveModuleAction().process(request, response); }
      else if (uri.equals(context + "/u/r/newmoduleversion")) { new NewModuleVersionAction().process(request, response); }
      else if (uri.equals(context + "/u/r/savetitle")) { new SaveTitleAction().process(request, response); }
      else if (uri.equals(context + "/u/r/saveconceptscheme")) { new SaveConceptSchemeAction().process(request, response); }
      else if (uri.equals(context + "/u/r/savequestionscheme")) { new SaveQuestionSchemeAction().process(request, response); }
      else if (uri.equals(context + "/u/r/newconcept")) { new NewConceptAction().process(request, response); }
      else if (uri.equals(context + "/u/r/saveconcept")) { new SaveConceptAction().process(request, response); }
      else if (uri.equals(context + "/u/r/saveversioninfo")) { new SaveVersionInfoAction().process(request, response); }

      else if (uri.equals(context + "/u/r/savequestion")) { new SaveQuestionAction().process(request, response); }

      else if (uri.equals(context + "/u/r/savecategory")) { new SaveCategoryAction().process(request, response); }
      
      else if (uri.equals(context + "/u/r/newcomment")) { new NewCommentAction().process(request, response); }
      
      else { ServletUtil.forward("/WEB-INF/jsp/error/404.jsp", request, response); }
      
   }
   
}
