package no.nsd.qddt.actions.update;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import no.nsd.qddt.actions.AbstractAction;
import no.nsd.qddt.logic.UrnUtil;
import no.nsd.qddt.model.Actor;
import no.nsd.qddt.model.Agency;
import no.nsd.qddt.model.Module;
import no.nsd.qddt.model.Study;
import no.nsd.qddt.model.User;
import no.nsd.qddt.service.ActorService;
import no.nsd.qddt.service.ModuleService;
import no.nsd.qddt.service.StudyService;
import no.nsd.qddt.servlets.ServletUtil;

public class SaveModuleAction extends AbstractAction {

   private Module module;
   private User user;
   private Actor actor;

   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.setRequestAndResponse(request, response);
      
      this.executeDaoAndClose();
      this.redirectSuccessPage();
   }

   @Override
   protected void executeDao() throws SQLException {
      this.createNewModule();
      this.registerNewOrUpdateModule();
   }
   
   private void createNewModule() throws SQLException {
      module = new Module();
      module.setId(ServletUtil.getRequestParamAsInteger(request, "id"));

      if (module.getId() == null) {
         module.setUrnId(UrnUtil.createNewId());
         module.setStudy(this.getStudy());
         module.setAgency(this.getAgency());
         this.setUserAndActor();
      }
      module.setName(request.getParameter("name"));
      module.setRepeat(this.getRepeatValue());
   }

   private Study getStudy() throws SQLException {
      Integer studyId = ServletUtil.getRequestParamAsInteger(request, "study");
      return (new StudyService(daoManager)).getStudy(studyId);
   }

   private Agency getAgency() {
      Agency agency = new Agency();
      agency.setId(ServletUtil.getRequestParamAsInteger(request, "agency"));
      return agency;
   }

   private Boolean getRepeatValue() {
      String s = request.getParameter("repeat");
      if ("yes".equals(s)) {
         return true;
      }
      if ("no".equals(s)) {
         return false;
      }
      return null;
   }

   private void setUserAndActor() throws SQLException {
      HttpSession session = this.request.getSession();
      this.user = (User) session.getAttribute("user");
      this.actor = (new ActorService(daoManager)).getActorForUserSurveyAndAgency(user.getId(), module.getStudy().getSurvey().getId(), module.getAgency().getId());
   }
   
   private void registerNewOrUpdateModule() throws SQLException {
      if (module.getId() == null) {
         Integer moduleId = (new ModuleService(daoManager)).registerNewModule(module, user, actor);
         module.setId(moduleId);
      } else {
         (new ModuleService(daoManager)).updateModule(module);
      }
   }

   private void redirectSuccessPage() throws IOException {
      ServletUtil.redirect("/u/history?id=" + module.getId(), request, response);
   }

}
