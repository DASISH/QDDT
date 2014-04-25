package no.nsd.qddt.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import no.nsd.qddt.logic.dao.DaoManager;
import no.nsd.qddt.model.Actor;
import no.nsd.qddt.model.Module;
import no.nsd.qddt.model.ModuleVersion;
import no.nsd.qddt.model.User;
import no.nsd.qddt.service.ActorService;
import no.nsd.qddt.service.ModuleService;
import no.nsd.qddt.service.ModuleVersionService;

public class ControllerFilter {

   private final HttpServletRequest request;
   
   public ControllerFilter(HttpServletRequest request) {
      this.request = request;
   }
   
   
   public void doFilterModule() throws ServletException {
      Integer moduleVersionId = ServletUtil.getRequestParamAsInteger(request, "mvid");

      if (moduleVersionId == null) {
         return;
      }
      
      this.doFilterDao(moduleVersionId);
   }
   
   private void doFilterDao(Integer moduleVersionId) throws ServletException {
      DaoManager daoManager = null;
      try {
         daoManager = DaoManager.createDaoManager();
         
         HttpSession httpSession = request.getSession();
         User user = (User) httpSession.getAttribute("user");

         ModuleVersion moduleVersion = (new ModuleVersionService(daoManager)).getModuleVersion(moduleVersionId);
         Module module = (new ModuleService(daoManager)).getModule(moduleVersion.getModule().getId());
         moduleVersion.setModule(module);
         Actor actor = (new ActorService(daoManager)).getActorForUserAndModule(user.getId(), module.getId());
         request.setAttribute("moduleVersion", moduleVersion);
         request.setAttribute("actor", actor);

      } catch (Exception e) {
         throw new ServletException(e);
      } finally {
         if (daoManager != null) {
            daoManager.close();
         }
      }
   }
   
}
