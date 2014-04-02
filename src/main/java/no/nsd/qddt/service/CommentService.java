package no.nsd.qddt.service;

import java.sql.Connection;
import java.util.List;
import javax.servlet.ServletException;
import no.nsd.qddt.factories.DatabaseConnectionFactory;
import no.nsd.qddt.logic.dao.CommentDao;
import no.nsd.qddt.logic.SqlUtil;
import no.nsd.qddt.logic.dao.persist.CommentDaoPersist;
import no.nsd.qddt.model.Comment;
import no.nsd.qddt.model.Urn;

public class CommentService {
   
   private CommentService() {
   }
   
   public static List<Comment> getCommentsForElementVersionAndModuleVersion(Urn elementUrn, Integer elementId, Integer moduleVersionId) throws ServletException {
      Connection conn = null;
      try {
         conn = DatabaseConnectionFactory.getConnection();
         CommentDao logic = new CommentDao(conn);
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
         CommentDaoPersist logic = new CommentDaoPersist(conn);
         logic.registerNewComment(comment);
      } catch (Exception e) {
         throw new ServletException(e);
      } finally {
         SqlUtil.close(conn);
      }
   }
   
   
}
