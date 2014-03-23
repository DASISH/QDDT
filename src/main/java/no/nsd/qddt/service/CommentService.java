package no.nsd.qddt.service;

import java.sql.Connection;
import java.util.List;
import javax.servlet.ServletException;
import no.nsd.qddt.factories.DatabaseConnectionFactory;
import no.nsd.qddt.logic.orm.CommentLogic;
import no.nsd.qddt.logic.SqlUtil;
import no.nsd.qddt.logic.orm.persistence.CommentPersistenceLogic;
import no.nsd.qddt.model.Comment;
import no.nsd.qddt.model.Urn;

public class CommentService {
   
   private CommentService() {
   }
   
   public static List<Comment> getCommentsForElementVersionAndModuleVersion(Urn elementUrn, Integer elementId, Integer moduleVersionId) throws ServletException {
      Connection conn = null;
      try {
         conn = DatabaseConnectionFactory.getConnection();
         CommentLogic logic = new CommentLogic(conn);
         return logic.getCommentsForElementVersionAndModuleVersion(elementUrn, elementId, moduleVersionId);
      } catch (Exception e) {
         throw new ServletException(e);
      } finally {
         SqlUtil.close(conn);
      }
   }


   public static void registerNewComment(Comment comment) throws ServletException {
      Connection conn = null;
      try {
         conn = DatabaseConnectionFactory.getConnection();
         CommentPersistenceLogic logic = new CommentPersistenceLogic(conn);
         logic.registerNewComment(comment);
      } catch (Exception e) {
         throw new ServletException(e);
      } finally {
         SqlUtil.close(conn);
      }
   }
   
   
}
