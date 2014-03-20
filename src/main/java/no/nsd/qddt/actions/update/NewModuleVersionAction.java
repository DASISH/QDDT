package no.nsd.qddt.actions.update;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import no.nsd.qddt.model.Actor;
import no.nsd.qddt.model.Module;
import no.nsd.qddt.model.ModuleVersion;
import no.nsd.qddt.model.User;
import no.nsd.qddt.service.ActorService;
import no.nsd.qddt.service.ModuleService;
import no.nsd.qddt.service.ModuleVersionService;
import no.nsd.qddt.servlets.ServletUtil;

public class NewModuleVersionAction {

   private Module module;
   private List<ModuleVersion> moduleVersions;
   private User user;
   private Actor actor;
   private HttpServletRequest request;
   private HttpServletResponse response;

   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.request = request;
      this.response = response;
      
      this.setModule();
      this.setUserActor();
      this.registerNewModuleVersion();
      this.redirectSuccessPage();
   }

   private void setModule() throws ServletException {
      Integer moduleId = ServletUtil.getRequestParamAsInteger(request, "mid");
      module = ModuleService.getModule(moduleId);
      moduleVersions = ModuleVersionService.getModuleVersions(moduleId);
   }

   private void setUserActor() throws ServletException {
      HttpSession httpSession = request.getSession();
      user = (User) httpSession.getAttribute("user");
      actor = ActorService.getActorForUserAndModule(user.getId(), module.getId());
   }
   

   private void registerNewModuleVersion() throws ServletException {
      if (moduleVersions == null || moduleVersions.isEmpty()) {
         this.registerModuleVersionFirst();
      } else {
         this.registerModuleVersionCopyOfLast();
      }
   }

   private void registerModuleVersionFirst() throws ServletException {
      ModuleVersion mv = new ModuleVersion();
      mv.setModule(module);
      mv.setActor(actor);
      mv.setStatus(1);
      
      ModuleVersionService.registerNewModuleVersion(mv);
   }
   
   private void registerModuleVersionCopyOfLast() throws ServletException {
      ModuleVersion mv = moduleVersions.get(moduleVersions.size() - 1);
      mv.setActor(actor);
      mv.setStatus(1);
      
      ModuleVersionService.registerNewModuleVersion(mv);
   }
   
   private void redirectSuccessPage() throws IOException {
      ServletUtil.redirect("/u/history?id=" + module.getId(), request, response);
   }

}
