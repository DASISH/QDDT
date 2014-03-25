package no.nsd.qddt.tags;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.SortedMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import no.nsd.qddt.model.Actor;
import no.nsd.qddt.model.Agency;
import no.nsd.qddt.model.Comment;
import no.nsd.qddt.model.CommentSource;
import no.nsd.qddt.model.ModuleVersion;
import no.nsd.qddt.model.Urn;
import no.nsd.qddt.service.CommentService;
import no.nsd.qddt.service.CommentSourceService;

public class CommentsTag extends SimpleTagSupport {

   private List<Comment> comments;
   private SortedMap<Integer, CommentSource> commentSourceMap;
   private ModuleVersion moduleVersion;

   private Integer agencyId;
   private String urnId;
   private Integer elementId;
   private Integer moduleVersionId;

   public void setAgencyId(Integer agencyId) {
      this.agencyId = agencyId;
   }

   public void setUrnId(String urnId) {
      this.urnId = urnId;
   }

   public void setElementId(Integer elementId) {
      this.elementId = elementId;
   }

   public void setModuleVersionId(Integer moduleVersionId) {
      this.moduleVersionId = moduleVersionId;
   }

   @Override
   public void doTag() throws JspException {
      this.setModuleVersion();

      try {
         this.setComments();
         this.setCommentSources();
         this.printHtml();
      } catch (Exception e) {
         throw new JspException(e);
      }
   }

   private void setModuleVersion() {
      PageContext pageContext = (PageContext) this.getJspContext();
      HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
      this.moduleVersion = (ModuleVersion) request.getAttribute("moduleVersion");
   }

   private void setComments() throws ServletException {
      Urn urn = new Urn();
      Agency a = new Agency();
      urn.setAgency(a);
      urn.setId(urnId);
      a.setId(agencyId);

      comments = CommentService.getCommentsForElementVersionAndModuleVersion(urn, elementId, moduleVersion.getId());
   }

   private void setCommentSources() throws ServletException {
      Integer surveyId = moduleVersion.getModule().getStudy().getSurvey().getId();
      commentSourceMap = CommentSourceService.getCommentSourceMap(surveyId);
   }

   private void printHtml() throws IOException {
      if (comments == null) {
         return;
      }
      for (Comment c : comments) {
         this.printHtmlComment(c);
      }
   }

   private void printHtmlComment(Comment c) throws IOException {
      JspWriter out = getJspContext().getOut();
      
      out.println("<div class=\"comment w10\">");

      out.println("<ul class=\"comment-list\">");
      out.println("<li>Author: " + this.getAuthorHtml(c.getActor()) + "</li>");
      out.println("<li>Source: " + this.getSourceHtml(c.getSourceId()) + "</li>");
      out.println("<li>Comment date: " + this.getDateHtml(c.getDate()) + "</li>");
      out.println("</ul>");

      String commentText = replaceNewlineWithBr(escapeXml(c.getText()));
      out.println("<p>" + commentText + "</p>");
      out.println("</div>");
   }

   private String getAuthorHtml(Actor a) {
      if (a != null) {
         return escapeXml(a.getName());
      }
      return "";
   }

   private String getSourceHtml(Integer sourceId) {
      if (commentSourceMap == null || sourceId == null) {
         return "";
      }
      CommentSource cs = commentSourceMap.get(sourceId);
      if (cs == null) {
         return "";
      }
      return escapeXml(cs.getText());
   }

   private String getDateHtml(Date date) {
      if (date == null) {
         return "";
      }
      DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, Locale.UK);
      return df.format(date);
   }
   
   private String escapeXml(String s) {
      if (s == null) {
         return "";
      }
      s = s.replaceAll("&", "&#38;");
      s = s.replaceAll("\"", "&#34;");
      s = s.replaceAll("'", "&#39;");
      s = s.replaceAll("<", "&#60;");
      s = s.replaceAll(">", "&#62;");
      return s;
   }

   private String replaceNewlineWithBr(String s) {
      if (s == null) {
         return null;
      }
      return s.replaceAll("\n", "<br>");
   }

}
