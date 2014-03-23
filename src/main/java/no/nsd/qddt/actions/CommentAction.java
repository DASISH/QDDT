package no.nsd.qddt.actions;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.nsd.qddt.model.Comment;
import no.nsd.qddt.model.CommentSource;
import no.nsd.qddt.model.ModuleVersion;
import no.nsd.qddt.model.Urn;
import no.nsd.qddt.service.CommentService;
import no.nsd.qddt.service.CommentSourceService;
import no.nsd.qddt.servlets.ServletUtil;

public class CommentAction {

   private HttpServletRequest request;
   private HttpServletResponse response;
   
   private ModuleVersion moduleVersion;
   
   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.request = request;
      this.response = response;
      
      this.moduleVersion = (ModuleVersion) request.getAttribute("moduleVersion");

      this.setComments();
      this.setCommentSources();
      this.forwardPage();
   }
   
   
   private void setComments() throws ServletException {
      Urn urn = new Urn();
      urn.setAgency(moduleVersion.getModule().getAgency());
      urn.setId(moduleVersion.getModule().getUrnId());
      
      Integer elementId = moduleVersion.getId();
      
      List<Comment> c = CommentService.getCommentsForElementVersionAndModuleVersion(urn, elementId, moduleVersion.getId());
      request.setAttribute("comments", c);
   }
   
   private void setCommentSources() throws ServletException {
      Integer surveyId = moduleVersion.getModule().getStudy().getSurvey().getId();
      SortedMap<Integer, CommentSource> commentSourceMap = CommentSourceService.getCommentSourceMap(surveyId);
      request.setAttribute("commentSourceMap", commentSourceMap);
   }
   
   
   private void forwardPage() throws ServletException, IOException {
      ServletUtil.forward("/WEB-INF/jsp/comment.jsp", request, response);
   }
   
}
