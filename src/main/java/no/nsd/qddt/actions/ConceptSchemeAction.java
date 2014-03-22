package no.nsd.qddt.actions;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.nsd.qddt.model.Comment;
import no.nsd.qddt.model.Concept;
import no.nsd.qddt.model.ConceptScheme;
import no.nsd.qddt.model.ModuleVersion;
import no.nsd.qddt.service.CommentService;
import no.nsd.qddt.service.ConceptSchemeService;
import no.nsd.qddt.service.ConceptService;
import no.nsd.qddt.servlets.ServletUtil;

public class ConceptSchemeAction {

   private Integer conceptId;
   private ModuleVersion moduleVersion;
   private HttpServletRequest request;
   private HttpServletResponse response;
   
   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.request = request;
      this.response = response;
      this.conceptId = ServletUtil.getRequestParamAsInteger(request, "cid");
      this.moduleVersion = (ModuleVersion) request.getAttribute("moduleVersion");
      
      this.setConceptScheme();
      this.setConcept();
      this.setComment();
      
      this.forwardPage();
   }
   
   private void setConceptScheme() throws ServletException {
      ConceptScheme conceptScheme = ConceptSchemeService.getConceptScheme(moduleVersion.getConceptSchemeId());
      request.setAttribute("conceptScheme", conceptScheme);
   }
   
   private void setConcept() throws ServletException {
      if (this.conceptId != null) {
         Concept c = ConceptService.getConcept(conceptId);
         request.setAttribute("concept", c);
      }
   }
   
   private void setComment() throws ServletException {
      if (this.conceptId != null) {
         List<Comment> c = CommentService.getCommentForModuleAndElement(moduleVersion.getId(), conceptId);
         request.setAttribute("comments", c);
      }
   }
   
   private void forwardPage() throws ServletException, IOException {
      ServletUtil.forward("/WEB-INF/jsp/concept_scheme.jsp", request, response);
   }
   
}
