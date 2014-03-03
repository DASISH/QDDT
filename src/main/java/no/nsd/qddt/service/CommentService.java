package no.nsd.qddt.service;

import java.sql.Connection;
import java.util.List;
import javax.servlet.ServletException;
import no.nsd.qddt.factories.DatabaseConnectionFactory;
import no.nsd.qddt.logic.CommentLogic;
import no.nsd.qddt.logic.SqlUtil;
import no.nsd.qddt.model.Comment;

public class CommentService {
   
   private CommentService() {
   }
   
   public static List<Comment> getCommentForModuleAndElement(Integer moduleId, Integer elementId) throws ServletException {
      Connection conn = null;
      try {
         conn = DatabaseConnectionFactory.getConnection();
         CommentLogic logic = new CommentLogic(conn);
         return logic.getComments(moduleId, elementId);
      } catch (Exception e) {
         throw new ServletException(e);
      } finally {
         SqlUtil.close(conn);
      }
   }


}
