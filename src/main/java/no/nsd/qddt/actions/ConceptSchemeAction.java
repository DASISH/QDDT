package no.nsd.qddt.actions;

import java.io.IOException;
import java.util.List;
import java.util.SortedMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.nsd.qddt.model.Comment;
import no.nsd.qddt.model.CommentSource;
import no.nsd.qddt.model.Concept;
import no.nsd.qddt.model.ConceptScheme;
import no.nsd.qddt.model.Element;
import no.nsd.qddt.model.ModuleVersion;
import no.nsd.qddt.model.Urn;
import no.nsd.qddt.service.CommentService;
import no.nsd.qddt.service.CommentSourceService;
import no.nsd.qddt.service.ConceptSchemeService;
import no.nsd.qddt.service.ConceptService;
import no.nsd.qddt.servlets.ServletUtil;

public class ConceptSchemeAction {

   private Integer conceptId;
   private ModuleVersion moduleVersion;
   private HttpServletRequest request;
   private HttpServletResponse response;
   private ConceptScheme conceptScheme;
   
   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.request = request;
      this.response = response;
      this.conceptId = ServletUtil.getRequestParamAsInteger(request, "cid");
      this.moduleVersion = (ModuleVersion) request.getAttribute("moduleVersion");
      
      this.setConceptScheme();
      this.setConcept();
      this.setComment();
      this.setCommentSources();
      
      this.forwardPage();
   }
   
   private void setConceptScheme() throws ServletException {
      conceptScheme = ConceptSchemeService.getConceptScheme(moduleVersion.getConceptSchemeId());
      request.setAttribute("conceptScheme", conceptScheme);
   }
   
   private void setConcept() throws ServletException {
      if (this.conceptId != null) {
         Concept c = ConceptService.getConcept(conceptId);
         request.setAttribute("concept", c);
      }
   }
   
   private void setComment() throws ServletException {
      if (conceptId == null) {
         this.setCommentForScheme();
      } else {
         this.setCommentForConcept();
      }
   }
   
   private void setCommentForScheme() throws ServletException {
      if (conceptScheme == null) {
         return;
      }
      this.setCommentForElement(conceptScheme);
   }
   
   private void setCommentForConcept() throws ServletException {
      if (conceptScheme == null) {
         return;
      }
      Concept concept = conceptScheme.getConcept(conceptId);
      if (concept == null) {
         return;
      }
      this.setCommentForElement(concept);
   }
   
   private void setCommentForElement(Element e) throws ServletException {
      Urn urn = e.getUrn();
      Integer elementId = e.getId();
      
      List<Comment> comments = CommentService.getCommentsForElementVersionAndModuleVersion(urn, elementId, moduleVersion.getId());
      request.setAttribute("comments", comments);
   }

   private void setCommentSources() throws ServletException {
      Integer surveyId = moduleVersion.getModule().getStudy().getSurvey().getId();
      SortedMap<Integer, CommentSource> commentSourceMap = CommentSourceService.getCommentSourceMap(surveyId);
      request.setAttribute("commentSourceMap", commentSourceMap);
   }
   
   
   private void forwardPage() throws ServletException, IOException {
      ServletUtil.forward("/WEB-INF/jsp/concept_scheme.jsp", request, response);
   }
   
}
