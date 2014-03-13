package no.nsd.qddt.service;

import java.sql.Connection;
import java.util.List;
import javax.servlet.ServletException;
import no.nsd.qddt.factories.DatabaseConnectionFactory;
import no.nsd.qddt.logic.SqlUtil;
import no.nsd.qddt.logic.orm.StudyLogic;
import no.nsd.qddt.model.Study;

public class StudyService {

   private StudyService() {
   }

   public static List<Study> getStudies() throws ServletException {
      Connection conn = null;
      try {
         conn = DatabaseConnectionFactory.getConnection();
         StudyLogic logic = new StudyLogic(conn);
         return logic.getStudies();
      } catch (Exception e) {
         throw new ServletException(e);
      } finally {
         SqlUtil.close(conn);
      }
   }

}
