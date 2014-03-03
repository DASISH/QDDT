package no.nsd.qddt.service;

import java.sql.Connection;
import java.util.List;
import javax.servlet.ServletException;
import no.nsd.qddt.factories.DatabaseConnectionFactory;
import no.nsd.qddt.logic.ModuleLogic;
import no.nsd.qddt.logic.SqlUtil;
import no.nsd.qddt.model.Module;
import no.nsd.qddt.model.Urn;

public class ModuleService {
   
   private ModuleService() {
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

   public static List<Module> getModules(Urn urn) throws ServletException {
      Connection conn = null;
      try {
         conn = DatabaseConnectionFactory.getConnection();
         return getModulesFromDb(urn, conn);
      } catch (Exception e) {
         throw new ServletException(e);
      } finally {
         SqlUtil.close(conn);
      }
   }
   
   private static List<Module> getModulesFromDb(Urn urn, Connection conn) throws Exception {
      ModuleLogic logic = new ModuleLogic(conn);
      return logic.getModules(urn);
   }

}
