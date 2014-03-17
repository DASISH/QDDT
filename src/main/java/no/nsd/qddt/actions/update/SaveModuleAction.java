package no.nsd.qddt.actions.update;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.nsd.qddt.logic.UrnUtil;
import no.nsd.qddt.model.Agency;
import no.nsd.qddt.model.Module;
import no.nsd.qddt.model.Study;
import no.nsd.qddt.service.ModuleService;
import no.nsd.qddt.servlets.ServletUtil;

public class SaveModuleAction {

   private Module module;
   private HttpServletRequest request;
   private HttpServletResponse response;

   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.request = request;
      this.response = response;

      this.createNewModule();
      this.registerNewOrUpdateModule();
      this.redirectSuccessPage();
   }

   private void createNewModule() {
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

   private Study getStudy() {
      Study study = new Study();
      study.setId(ServletUtil.getRequestParamAsInteger(request, "study"));
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

   private void registerNewOrUpdateModule() throws ServletException {
      if (module.getId() == null) {
         Integer moduleId = ModuleService.registerNewModule(module);
         module.setId(moduleId);
      } else {
         ModuleService.updateModule(module);
      }
   }

   private void redirectSuccessPage() throws IOException {
      ServletUtil.redirect("/u/history?id=" + module.getId(), request, response);
   }

}
