package no.nsd.qddt.actions.update;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

public class SaveModuleAction {

   private Module module;
   private User user;
   private Actor actor;
   private HttpServletRequest request;
   private HttpServletResponse response;

   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.request = request;
      this.response = response;

      this.createNewModule();
      this.setUserAndActor();
      this.registerNewOrUpdateModule();
      this.redirectSuccessPage();
   }

   private void createNewModule() throws ServletException {
      module = new Module();
      module.setId(ServletUtil.getRequestParamAsInteger(request, "id"));

      if (module.getId() == null) {
         module.setUrnId(UrnUtil.createNewId());
         module.setStudy(this.getStudy());
         module.setAgency(this.getAgency());
      }
      module.setName(request.getParameter("name"));
      module.setRepeat(this.getRepeatValue());
   }

   private Study getStudy() throws ServletException {
      Integer studyId = ServletUtil.getRequestParamAsInteger(request, "study");
      Study study = StudyService.getStudy(studyId);
      return study;
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

   private void setUserAndActor() throws ServletException {
      HttpSession session = this.request.getSession();
      this.user = (User) session.getAttribute("user");
      this.actor = ActorService.getActorForUserSurveyAndAgency(user.getId(), module.getStudy().getSurvey().getId(), module.getAgency().getId());
   }
   
   private void registerNewOrUpdateModule() throws ServletException {
      if (module.getId() == null) {
         Integer moduleId = ModuleService.registerNewModule(module, user, actor);
         module.setId(moduleId);
      } else {
         ModuleService.updateModule(module);
      }
   }

   private void redirectSuccessPage() throws IOException {
      ServletUtil.redirect("/u/history?id=" + module.getId(), request, response);
   }

}
