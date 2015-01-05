package no.nsd.qddt.servlets;

import java.sql.SQLException;
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
   private final Integer moduleVersionId;
   
   private DaoManager daoManager;
   
   public ControllerFilter(HttpServletRequest request) {
      this.request = request;
      moduleVersionId = ServletUtil.getRequestParamAsInteger(request, "mvid");
   }
   
   
   public void doFilter() throws ServletException {

      this.doFilterDao();
      
   }
   
   private void doFilterDao() throws ServletException {
      try {
         daoManager = DaoManager.createDaoManager();

         if (moduleVersionId != null) {
            this.doFilterModule();
         }

      } catch (Exception e) {
         throw new ServletException(e);
      } finally {
         if (daoManager != null) {
            daoManager.close();
         }
      }
   }
   
   private void doFilterUserManagement() throws SQLException {
      
      
   }
   
   private void doFilterModule() throws SQLException {
      HttpSession httpSession = request.getSession();
      User user = (User) httpSession.getAttribute("user");

      ModuleVersion moduleVersion = (new ModuleVersionService(daoManager)).getModuleVersion(moduleVersionId);
      Module module = (new ModuleService(daoManager)).getModule(moduleVersion.getModule().getId());
      moduleVersion.setModule(module);
      Actor actor = (new ActorService(daoManager)).getActorForUserAndModule(user.getId(), module.getId());
      request.setAttribute("moduleVersion", moduleVersion);
      request.setAttribute("actor", actor);
   }
   
   
   
}
