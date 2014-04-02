package no.nsd.qddt.actions;

import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.nsd.qddt.logic.dao.DaoManager;

public abstract class AbstractAction {

   protected DaoManager daoManager;
   protected HttpServletRequest request;
   protected HttpServletResponse response;

   protected void setRequestAndResponse(HttpServletRequest request, HttpServletResponse response) {
      this.request = request;
      this.response = response;
   }

   protected void executeDaoAndClose() throws ServletException {
      try {
         daoManager = DaoManager.createDaoManager();
         
         this.executeDao();

      } catch (Exception e) {
         throw new ServletException(e);
      } finally {
         if (daoManager != null) {
            daoManager.close();
         }
      }
   }

   protected abstract void executeDao() throws SQLException;


}
