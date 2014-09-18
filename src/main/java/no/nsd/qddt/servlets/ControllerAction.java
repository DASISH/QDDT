package no.nsd.qddt.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.nsd.qddt.actions.*;
import no.nsd.qddt.actions.update.*;

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
      else if (uri.equals(context + "/u/conceptquestion")) { new ConceptQuestionAction().process(request, response); }
      
      else if (uri.equals(context + "/u/questionscheme")) { new QuestionSchemeAction().process(request, response); }
      else if (uri.equals(context + "/u/instrument")) { new InstrumentAction().process(request, response); }
      else if (uri.equals(context + "/u/report")) { new ReportAction().process(request, response); }
      else if (uri.equals(context + "/u/versioninfo")) { ServletUtil.forward("/WEB-INF/jsp/version_info.jsp", request, response); }
      else if (uri.equals(context + "/u/publishinfo")) { ServletUtil.forward("/WEB-INF/jsp/publish_info.jsp", request, response); }
      else if (uri.equals(context + "/u/comment")) { ServletUtil.forward("/WEB-INF/jsp/comment.jsp", request, response); }
      
      else if (uri.equals(context + "/u/history")) { new HistoryAction().process(request, response); }

      else if (uri.equals(context + "/u/module")) { new ModuleAction().process(request, response); }

      else if (uri.equals(context + "/u/question")) { new QuestionAction().process(request, response); }
      else if (uri.equals(context + "/u/questionresponsedomain")) { new QuestionResponseDomainAction().process(request, response); }
      else if (uri.equals(context + "/u/questioncodelist")) { new QuestionCodeListAction().process(request, response); }

      else if (uri.equals(context + "/u/responsedomain")) { new ResponseDomainAction().process(request, response); }
      else if (uri.equals(context + "/u/codelist")) { new CodeListAction().process(request, response); }
      else if (uri.equals(context + "/u/updatecodelist")) { new CodeListUpdateAction().process(request, response); }
      else if (uri.equals(context + "/u/newcodelist")) { new CodeListNewAction().process(request, response); }
      
      else if (uri.equals(context + "/u/survey")) { new SurveyAction().process(request, response); }
      else if (uri.equals(context + "/u/category")) { new CategoryAction().process(request, response); }
      else if (uri.equals(context + "/u/code")) { new CodeAction().process(request, response); }
      else if (uri.equals(context + "/u/updatecode")) { new CodeUpdateAction().process(request, response); }
      
      else if (uri.equals(context + "/u/downloadfile")) { new DownloadFileAction().process(request, response); }
      
      
      else if (uri.equals(context + "/u/r/savemodule")) { new SaveModuleAction().process(request, response); }
      else if (uri.equals(context + "/u/r/newmoduleversion")) { new NewModuleVersionAction().process(request, response); }
      else if (uri.equals(context + "/u/r/savetitle")) { new SaveTitleAction().process(request, response); }
      else if (uri.equals(context + "/u/r/saveconceptscheme")) { new SaveConceptSchemeAction().process(request, response); }
      else if (uri.equals(context + "/u/r/savequestionscheme")) { new SaveQuestionSchemeAction().process(request, response); }
      else if (uri.equals(context + "/u/r/newconcept")) { new NewConceptAction().process(request, response); }
      else if (uri.equals(context + "/u/r/saveconcept")) { new SaveConceptAction().process(request, response); }
      else if (uri.equals(context + "/u/r/saveversioninfo")) { new SaveVersionInfoAction().process(request, response); }
      else if (uri.equals(context + "/u/r/savepublishinfo")) { new SavePublishInfoAction().process(request, response); }

      else if (uri.equals(context + "/u/r/savequestion")) { new SaveQuestionAction().process(request, response); }
      else if (uri.equals(context + "/u/r/addcodelisttoquestion")) { new AddCodeListToQuestionAction().process(request, response); }
      else if (uri.equals(context + "/u/r/saveresponsecardinality")) { new SaveResponseCardinalityAction().process(request, response); }

      else if (uri.equals(context + "/u/r/savecategory")) { new SaveCategoryAction().process(request, response); }
      
      else if (uri.equals(context + "/u/r/savecodelist")) { new SaveCodeListAction().process(request, response); }
      else if (uri.equals(context + "/u/r/addcodetocodelist")) { new AddCodeToCodeListAction().process(request, response); }
      else if (uri.equals(context + "/u/r/changecodelist")) { new ChangeCodeListAction().process(request, response); }
      else if (uri.equals(context + "/u/r/changecodelistcombined")) { new ChangeCodeListCombinedAction().process(request, response); }
      
      else if (uri.equals(context + "/u/r/newcomment")) { new NewCommentAction().process(request, response); }

      else if (uri.equals(context + "/u/r/newcode")) { new NewCodeAction().process(request, response); }
      else if (uri.equals(context + "/u/r/savecode")) { new SaveCodeAction().process(request, response); }
      else if (uri.equals(context + "/u/r/savecodecategory")) { new SaveCodeCategoryAction().process(request, response); }
      
      else if (uri.matches(context + "/u/r/uploadfile/\\d+")) { new UploadFileAction().process(request, response); }
      
      else if (uri.equals(context + "/u/r/addquestiontoconcept")) { new AddQuestionToConceptAction().process(request, response); }
      else if (uri.equals(context + "/u/r/removequestionfromconcept")) { new RemoveQuestionFromConceptAction().process(request, response); }
      
      
      else { ServletUtil.forward("/WEB-INF/jsp/error/404.jsp", request, response); }
      
   }
   
}
