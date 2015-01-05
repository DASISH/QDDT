package no.nsd.qddt.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import no.nsd.qddt.logic.dao.DaoManager;
import no.nsd.qddt.model.Actor;
import no.nsd.qddt.model.Module;
import no.nsd.qddt.model.User;

public class ModuleService {
   
   private final DaoManager daoManager;
   
   public ModuleService(DaoManager daoManager) {
      this.daoManager = daoManager;
   }

   
   public List<Module> getModules(User user) throws SQLException {
      List<Module> allModules = daoManager.getModuleDao().getModules();
      Set<Integer> moduleIds = daoManager.getUserDao().getModuleIdsForUser(user.getId());

      List<Module> modules = new ArrayList<Module>();
      if (allModules == null) {
         return null;
      }
      for (Module m : allModules) {
         if (moduleIds.contains(m.getId())) {
            modules.add(m);
         }
      }
      return modules;
   }
   
   public Module getModule(Integer moduleId) throws SQLException {
      return daoManager.getModuleDao().getModule(moduleId);
   }

   
   
   public Integer registerNewModule(Module module, User user, Actor actor) throws SQLException {
      try {
         daoManager.beginTransaction();
         
         Integer moduleId = daoManager.getModuleDaoUpdate().registerNewModule(module);
         daoManager.getUserDaoUpdate().registerNewUserModuleActor(user.getId(), moduleId, actor.getId());
         
         daoManager.endTransaction();
         
         return moduleId;
      } catch (SQLException e) {
         daoManager.abortTransaction();
         throw e;
      }
   }
   
   public void updateModule(Module module) throws SQLException {
      daoManager.getModuleDaoUpdate().updateModule(module);
   }
   

}
