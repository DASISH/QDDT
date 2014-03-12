package no.nsd.qddt.service;

import java.sql.Connection;
import java.util.List;
import javax.servlet.ServletException;
import no.nsd.qddt.factories.DatabaseConnectionFactory;
import no.nsd.qddt.logic.orm.ModuleLogic;
import no.nsd.qddt.logic.SqlUtil;
import no.nsd.qddt.model.Module;

public class ModuleService {
   
   private ModuleService() {
   }

   
   public static List<Module> getModules() throws ServletException {
      Connection conn = null;
      try {
         conn = DatabaseConnectionFactory.getConnection();
         ModuleLogic logic = new ModuleLogic(conn);
         return logic.getModules();
      } catch (Exception e) {
         throw new ServletException(e);
      } finally {
         SqlUtil.close(conn);
      }
   }
   
   
   public static Module getModule(Integer moduleId) throws ServletException {
      Connection conn = null;
      try {
         conn = DatabaseConnectionFactory.getConnection();
         ModuleLogic logic = new ModuleLogic(conn);
         return logic.getModule(moduleId);
      } catch (Exception e) {
         throw new ServletException(e);
      } finally {
         SqlUtil.close(conn);
      }
   }


}
