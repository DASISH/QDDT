package no.nsd.qddt.tags;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import no.nsd.qddt.model.Concept;
import no.nsd.qddt.model.ConceptScheme;
import no.nsd.qddt.model.ModuleVersion;

public class ConceptSchemeTag extends SimpleTagSupport {

   private ConceptScheme conceptScheme;
   private ModuleVersion moduleVersion;
   private String currentConceptId;

   private JspWriter out;

   public void setConceptScheme(ConceptScheme conceptScheme) {
      this.conceptScheme = conceptScheme;
   }

   public void setCurrentConceptId(String currentConceptId) {
      this.currentConceptId = currentConceptId;
   }

   @Override
   public void doTag() throws JspException {
      this.setModuleVersion();

      try {
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

   private void printHtml() throws IOException {
      if (this.conceptScheme == null) {
         return;
      }
      
      out = getJspContext().getOut();

      out.println("<ul class=\"link-list\">");

      Collection<Concept> cons = this.conceptScheme.getConcepts();

      for (Concept c : cons) {
         this.printHtmlConcept(c);
      }

      out.println("</ul>");
   }

   private void printHtmlConcept(Concept c) throws IOException {
      out.println("<li>");
      out.print("<a ");

      try {
         if (c.getId().equals(Integer.valueOf(currentConceptId))) {
            out.print(" class=\"current\" ");
         }
      } catch (Exception ignored) {
      }
      
      out.print("href=\"?mvid=" + this.moduleVersion.getId() + "&cid=" + c.getId() + "\">");
      
      out.print(fixName(c.getName()));
      out.println("</a>");
      
      this.printHtmlSubConcepts(c);

      out.println("</li>");
   }

   private void printHtmlSubConcepts(Concept c) throws IOException {
      List<Concept> subConcepts = c.getSubConcepts();
      if (subConcepts.isEmpty()) {
         return;
      }
      out.println("<ul>");
      for (Concept sub : subConcepts) {
         this.printHtmlConcept(sub);
      }
      out.println("</ul>");
   }

   private String fixName(String name) {
      if (name == null) {
         return "(name missing)";
      }
      return escapeXml(name);
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

}
