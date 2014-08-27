package no.nsd.qddt.tags;

import java.io.IOException;
import java.util.List;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import no.nsd.qddt.model.Code;
import no.nsd.qddt.model.Question;

public class QuestionResponseDomainTag extends SimpleTagSupport {

   private Question question;


   public void setQuestion(Question question) {
      this.question = question;
   }

   @Override
   public void doTag() throws JspException {
      try {
         this.printHtml();
      } catch (Exception e) {
         throw new JspException(e);
      }
   }

   private void printHtml() throws IOException {
      if (question == null) {
         return;
      }
      if (question.getCodeList() != null) {
         this.printCodeList();
      }
   }

   private void printCodeList() throws IOException {
      if (question.getCodeList().isCombined()) {
         this.printCodeListCombined();
      } else {
         this.printCodeListValidOrMissing();
      }
   }

   private void printCodeListValidOrMissing() throws IOException {
      JspWriter out = getJspContext().getOut();
      
      List<Code> codes = question.getCodeList().getCodes();
      if (codes == null) {
         return;
      }
      
      out.println("<table>");
      out.println("<tbody>");
      
      for (Code c : codes) {
         out.println("<tr>");
         out.println("<td>" + c.getValue() + "</td>");
         out.println("<td>" + escapeXml(c.getCategory().getLabel()) + "</td>");
         out.println("</tr>");
      }
      
      out.println("</tbody>");
      out.println("</table>");
   }

   private void printCodeListCombined() throws IOException {
      JspWriter out = getJspContext().getOut();
      
      List<Code> codes = question.getCodeList().getCodes();
      if (codes == null) {
         return;
      }
      
      out.println("<table>");
      out.println("<tbody>");
      
      this.printCodeListCombinedValid();
      this.printCodeListCombinedMissing();
      
      out.println("</tbody>");
      out.println("</table>");
   }
   
      
   private void printCodeListCombinedValid() throws IOException {
      JspWriter out = getJspContext().getOut();
      if (question.getCodeList().getValidCodeList() == null ||
              question.getCodeList().getValidCodeList().getCodes() == null) {
         return;
      }
      for (Code c : question.getCodeList().getValidCodeList().getCodes()) {
         out.println("<tr>");
         out.println("<td>" + c.getValue() + "</td>");
         out.println("<td>" + escapeXml(c.getCategory().getLabel()) + "</td>");
         out.println("</tr>");
      }
   }
   private void printCodeListCombinedMissing() throws IOException {
      JspWriter out = getJspContext().getOut();
      if (question.getCodeList().getMissingCodeList() == null ||
              question.getCodeList().getMissingCodeList().getCodes() == null) {
         return;
      }
      for (Code c : question.getCodeList().getMissingCodeList().getCodes()) {
         out.println("<tr>");
         out.println("<td>" + c.getValue() + "</td>");
         out.println("<td>" + escapeXml(c.getCategory().getLabel()) + "</td>");
         out.println("</tr>");
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
