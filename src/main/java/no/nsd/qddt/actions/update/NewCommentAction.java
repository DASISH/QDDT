package no.nsd.qddt.actions.update;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import no.nsd.qddt.model.Actor;
import no.nsd.qddt.model.Agency;
import no.nsd.qddt.model.Comment;
import no.nsd.qddt.model.ModuleVersion;
import no.nsd.qddt.model.Urn;
import no.nsd.qddt.model.User;
import no.nsd.qddt.service.CommentService;
import no.nsd.qddt.servlets.ServletUtil;

public class NewCommentAction {

   private ModuleVersion moduleVersion;
   private Comment comment;
   private HttpServletRequest request;
   private HttpServletResponse response;

   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.request = request;
      this.response = response;
      this.moduleVersion = (ModuleVersion) request.getAttribute("moduleVersion");

      this.createNewComment();
      this.registerComment();
      this.redirectSuccessPage();
   }

   private void createNewComment() {
      comment = new Comment();
      comment.setElementUrn(this.getUrn());
      comment.setModuleVersionId(moduleVersion.getId());
      comment.setElementId(ServletUtil.getRequestParamAsInteger(request, "eid"));
      comment.setSourceId(ServletUtil.getRequestParamAsInteger(request, "sourceid"));
      comment.setText(request.getParameter("comment_text"));
      comment.setUser(this.getUser());
      comment.setActor(this.getActor());
      comment.setDate(this.getDate());
   }

   private Urn getUrn() {
      Urn urn = new Urn();
      urn.setAgency(this.getAgency());
      urn.setId(request.getParameter("urnid"));
      return urn;
   }

   private Agency getAgency() {
      Agency a = new Agency();
      a.setId(ServletUtil.getRequestParamAsInteger(request, "aid"));
      return a;
   }

   private User getUser() {
      HttpSession httpSession = request.getSession();
      User user = (User) httpSession.getAttribute("user");
      return user;
   }

   private Actor getActor() {
      Actor actor = (Actor) request.getAttribute("actor");
      return actor;
   }

   private Date getDate() {
      try {
         int day = ServletUtil.getRequestParamAsInteger(request, "day");
         int month = ServletUtil.getRequestParamAsInteger(request, "day");
         int year = ServletUtil.getRequestParamAsInteger(request, "day");
         Calendar cal = new GregorianCalendar();
         cal.setLenient(false);
         cal.clear();
         cal.set(year, month - 1, day);
         return cal.getTime();
      } catch (Exception e) {
         return new Date();
      }
   }

   private void registerComment() throws ServletException {
      CommentService.registerNewComment(comment);
   }

   private void redirectSuccessPage() throws IOException {
      String url = request.getParameter("fromurl");
      ServletUtil.redirect(url, request, response);
   }

}
