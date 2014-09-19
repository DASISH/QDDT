package no.nsd.qddt.tags;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import no.nsd.qddt.model.Element;
import no.nsd.qddt.model.ModuleVersion;

public class VersionTag extends SimpleTagSupport {

   private JspWriter out;
   private ModuleVersion moduleVersion;
   
   private Element element;
   private boolean onlyVersionChange;


   public void setElement(Element element) {
      this.element = element;
   }

   public void setOnlyVersionChange(boolean onlyVersionChange) {
      this.onlyVersionChange = onlyVersionChange;
   }

   @Override
   public void doTag() throws JspException {
      this.setModuleVersion();

      try {
         this.printHtml();
      } catch (IOException e) {
         throw new JspException(e);
      }
   }

   private void setModuleVersion() {
      PageContext pageContext = (PageContext) this.getJspContext();
      HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
      this.moduleVersion = (ModuleVersion) request.getAttribute("moduleVersion");
   }

   private void printHtml() throws IOException {
      if (element == null) {
         return;
      }
      
      out = getJspContext().getOut();

      this.printVersionInfo();

   }

   private void printVersionInfo() throws IOException {
      out.println("<div class=\"box topmarg\">");
      
      this.printVersionCode();
      this.printVersionDescription();
      
      out.print("</div>");
   }

   private void printVersionCode() throws IOException {
      out.println("<h4 class=\"notopmarg\">Change type:</h4>");      

      if (!element.isPublished()) {
         out.println("<p>[New element - not published]</p>");
         return;
      }
      
      out.println("<select name=\"version_change_code\">");

      out.print("<option value=\"\"></option>");
      
      if (!onlyVersionChange) {
         out.print("<option value=\"-1\">New element</option>");
         out.print("<option value=\"0\">New element based on old</option>");
      }
      
      int versionLevels = moduleVersion.getModule().getStudy().getSurvey().getVersionLevels();
      
      if (versionLevels >= 1) {
         out.print("<option value=\"1\">New version - Major</option>");
      }
      if (versionLevels >= 2) {
         out.print("<option value=\"2\">New version - Minor</option>");
      }
      if (versionLevels >= 3) {
         out.print("<option value=\"3\">New version - Sub-minor</option>");
      }
      
      out.print("</select>");
   }

   private void printVersionDescription() throws IOException {
      out.println("<h4>Version rationale description:</h4>");
      out.print("<textarea class=\"w10\" name=\"version_description\" rows=\"4\">");
      this.printElementVersionDescription();
      out.print("</textarea>");
   }

   private void printElementVersionDescription() throws IOException {
      // only print version description if element is a 'new' element, i.e not published.
      if (!element.isPublished()) {
         out.print(escapeXml(element.getVersionDescription()));
      }
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
