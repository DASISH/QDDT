package no.nsd.qddt.actions.update;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import no.nsd.qddt.actions.AbstractAction;
import no.nsd.qddt.model.Actor;
import no.nsd.qddt.model.Module;
import no.nsd.qddt.model.ModuleVersion;
import no.nsd.qddt.model.User;
import no.nsd.qddt.service.ActorService;
import no.nsd.qddt.service.ModuleService;
import no.nsd.qddt.service.ModuleVersionService;
import no.nsd.qddt.servlets.ServletUtil;

public class NewModuleVersionAction extends AbstractAction {

   private Module module;
   private List<ModuleVersion> moduleVersions;
   private User user;
   private Actor actor;

   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.setRequestAndResponse(request, response);
      
      this.executeDaoAndClose();
      this.redirectSuccessPage();
   }

   @Override
   protected void executeDao() throws SQLException {
      this.setModule();
      this.setUserActor();
      this.registerNewModuleVersion();
   }
   
   private void setModule() throws SQLException {
      Integer moduleId = ServletUtil.getRequestParamAsInteger(request, "mid");
      module = (new ModuleService(daoManager)).getModule(moduleId);
      moduleVersions = (new ModuleVersionService(daoManager)).getModuleVersions(moduleId);
   }

   private void setUserActor() throws SQLException {
      HttpSession httpSession = request.getSession();
      user = (User) httpSession.getAttribute("user");
      actor = (new ActorService(daoManager)).getActorForUserAndModule(user.getId(), module.getId());
   }

   private void registerNewModuleVersion() throws SQLException {
      if (moduleVersions == null || moduleVersions.isEmpty()) {
         this.registerModuleVersionFirst();
      } else {
         this.registerModuleVersionCopyOfLast();
      }
   }

   private void registerModuleVersionFirst() throws SQLException {
      ModuleVersion mv = new ModuleVersion();
      mv.setModule(module);
      mv.setActor(actor);
      mv.setStatus(1);
      
      (new ModuleVersionService(daoManager)).registerNewModuleVersion(mv);
   }
   
   private void registerModuleVersionCopyOfLast() throws SQLException {
      ModuleVersion mv = moduleVersions.get(moduleVersions.size() - 1);
      mv.setActor(actor);
      mv.setStatus(1);
      
      (new ModuleVersionService(daoManager)).registerNewModuleVersion(mv);
   }
   
   private void redirectSuccessPage() throws IOException {
      ServletUtil.redirect("/u/history?id=" + module.getId(), request, response);
   }

}
