package no.nsd.qddt.tags;

import java.util.SortedMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import no.nsd.qddt.model.CommentSource;
import no.nsd.qddt.model.ModuleVersion;
import no.nsd.qddt.service.CommentSourceService;

public class CommentSourceTag extends SimpleTagSupport {

   private HttpServletRequest request;
   private ModuleVersion moduleVersion;
   
   private String var;


   public void setVar(String var) {
      this.var = var;
   }


   @Override
   public void doTag() throws JspException {
      this.setRequest();
      this.setModuleVersion();

      try {
         this.setCommentSources();
      } catch (Exception e) {
         throw new JspException(e);
      }
   }

   private void setRequest() {
      PageContext pageContext = (PageContext) this.getJspContext();
      request = (HttpServletRequest) pageContext.getRequest();
   }

   private void setModuleVersion() {
      this.moduleVersion = (ModuleVersion) request.getAttribute("moduleVersion");
   }
   
   private void setCommentSources() throws ServletException {
      Integer surveyId = moduleVersion.getModule().getStudy().getSurvey().getId();
      SortedMap<Integer, CommentSource> commentSourceMap = CommentSourceService.getCommentSourceMap(surveyId);
      request.setAttribute(var, commentSourceMap);
   }
   

}
