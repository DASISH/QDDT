package no.nsd.qddt.service;

import java.sql.Connection;
import java.util.SortedMap;
import javax.servlet.ServletException;
import no.nsd.qddt.factories.DatabaseConnectionFactory;
import no.nsd.qddt.logic.SqlUtil;
import no.nsd.qddt.logic.orm.CommentSourceLogic;
import no.nsd.qddt.model.CommentSource;

public class CommentSourceService {
   
   private CommentSourceService() {
   }
   
   public static SortedMap<Integer, CommentSource> getCommentSourceMap(Integer surveyId) throws ServletException {
      Connection conn = null;
      try {
         conn = DatabaseConnectionFactory.getConnection();
         CommentSourceLogic logic = new CommentSourceLogic(conn);
         return logic.getCommentSourceMap(surveyId);
      } catch (Exception e) {
         throw new ServletException(e);
      } finally {
         SqlUtil.close(conn);
      }
   }


}
