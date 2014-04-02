package no.nsd.qddt.service;

import java.sql.Connection;
import java.util.List;
import javax.servlet.ServletException;
import no.nsd.qddt.factories.DatabaseConnectionFactory;
import no.nsd.qddt.logic.dao.ModuleDao;
import no.nsd.qddt.logic.SqlUtil;
import no.nsd.qddt.logic.dao.persist.ConceptDaoPersist;
import no.nsd.qddt.logic.dao.persist.ModuleDaoPersist;
import no.nsd.qddt.logic.dao.persist.UserDaoPersist;
import no.nsd.qddt.model.Actor;
import no.nsd.qddt.model.Module;
import no.nsd.qddt.model.User;

public class ModuleService {
   
   private ModuleService() {
   }

   
   public static List<Module> getModules() throws ServletException {
      Connection conn = null;
      try {
         conn = DatabaseConnectionFactory.getConnection();
         ModuleDao logic = new ModuleDao(conn);
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
         ModuleDao logic = new ModuleDao(conn);
         return logic.getModule(moduleId);
      } catch (Exception e) {
         throw new ServletException(e);
      } finally {
         SqlUtil.close(conn);
      }
   }

   
   
   public static Integer registerNewModule(Module module, User user, Actor actor) throws ServletException {
      Connection conn = null;
      try {
         conn = DatabaseConnectionFactory.getConnection();
         conn.setAutoCommit(false);
         
         ModuleDaoPersist moduleLogic = new ModuleDaoPersist(conn);
         Integer moduleId = moduleLogic.registerNewModule(module);
         
         UserDaoPersist userLogic = new UserDaoPersist(conn);
         userLogic.registerNewUserModuleActor(user.getId(), moduleId, actor.getId());
         
         conn.commit();
         
         return moduleId;
      } catch (Exception e) {
         SqlUtil.rollback(conn);
         throw new ServletException(e);
      } finally {
         SqlUtil.close(conn);
      }
   }
   
   public static void updateModule(Module module) throws ServletException {
      Connection conn = null;
      try {
         conn = DatabaseConnectionFactory.getConnection();
         ModuleDaoPersist logic = new ModuleDaoPersist(conn);
         logic.updateModule(module);
      } catch (Exception e) {
         throw new ServletException(e);
      } finally {
         SqlUtil.close(conn);
      }
   }
   

}
