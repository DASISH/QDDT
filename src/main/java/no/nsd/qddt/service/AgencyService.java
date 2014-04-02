package no.nsd.qddt.service;

import java.sql.Connection;
import java.util.List;
import javax.servlet.ServletException;
import no.nsd.qddt.factories.DatabaseConnectionFactory;
import no.nsd.qddt.logic.SqlUtil;
import no.nsd.qddt.logic.dao.AgencyDao;
import no.nsd.qddt.model.Agency;

public class AgencyService {

   private AgencyService() {
   }

   public static List<Agency> getAgencies() throws ServletException {
      Connection conn = null;
      try {
         conn = DatabaseConnectionFactory.getConnection();
         AgencyDao logic = new AgencyDao(conn);
         return logic.getAgencies();
      } catch (Exception e) {
         throw new ServletException(e);
      } finally {
         SqlUtil.close(conn);
      }
   }

}
