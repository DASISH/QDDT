package no.nsd.qddt.service;

import java.sql.Connection;
import java.util.List;
import javax.servlet.ServletException;
import no.nsd.qddt.factories.DatabaseConnectionFactory;
import no.nsd.qddt.logic.SqlUtil;
import no.nsd.qddt.logic.orm.ModuleVersionLogic;
import no.nsd.qddt.logic.orm.persistence.ModuleVersionPersistenceLogic;
import no.nsd.qddt.model.ModuleVersion;

public class ModuleVersionService {
   
   private ModuleVersionService() {
   }

   
   public static List<ModuleVersion> getModuleVersions(Integer moduleId) throws ServletException {
      Connection conn = null;
      try {
         conn = DatabaseConnectionFactory.getConnection();
         ModuleVersionLogic logic = new ModuleVersionLogic(conn);
         return logic.getModuleVersions(moduleId);
      } catch (Exception e) {
         throw new ServletException(e);
      } finally {
         SqlUtil.close(conn);
      }
   }
   
   
   public static ModuleVersion getModuleVersion(Integer moduleVersionId) throws ServletException {
      Connection conn = null;
      try {
         conn = DatabaseConnectionFactory.getConnection();
         ModuleVersionLogic logic = new ModuleVersionLogic(conn);
         return logic.getModuleVersion(moduleVersionId);
      } catch (Exception e) {
         throw new ServletException(e);
      } finally {
         SqlUtil.close(conn);
      }
   }

   public static void registerNewModuleVersion(ModuleVersion mv) throws ServletException {
      Connection conn = null;
      try {
         conn = DatabaseConnectionFactory.getConnection();
         ModuleVersionPersistenceLogic logic = new ModuleVersionPersistenceLogic(conn);
         logic.registerNewVersionModule(mv);
      } catch (Exception e) {
         throw new ServletException(e);
      } finally {
         SqlUtil.close(conn);
      }
   }

}
