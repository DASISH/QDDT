package no.nsd.qddt.actions;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.nsd.qddt.model.Agency;
import no.nsd.qddt.model.Module;
import no.nsd.qddt.model.Study;
import no.nsd.qddt.service.AgencyService;
import no.nsd.qddt.service.ModuleService;
import no.nsd.qddt.service.StudyService;
import no.nsd.qddt.servlets.ServletUtil;

public class ModuleAction {

   private Integer moduleId;
   private HttpServletRequest request;
   private HttpServletResponse response;
   
   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.request = request;
      this.response = response;
      this.moduleId = ServletUtil.getRequestParamAsInteger(request, "id");

      this.setModule();
      this.setStudies();
      this.setAgencies();
      this.forwardPage();
   }
   
   
   private void setModule() throws ServletException {
      Module module = ModuleService.getModule(moduleId);
      request.setAttribute("module", module);
   }
   private void setStudies() throws ServletException {
      List<Study> studies = StudyService.getStudies();
      request.setAttribute("studies", studies);
   }
   private void setAgencies() throws ServletException {
      List<Agency> agencies = AgencyService.getAgencies();
      request.setAttribute("agencies", agencies);
   }
   
   private void forwardPage() throws ServletException, IOException {
      ServletUtil.forward("/WEB-INF/jsp/module.jsp", request, response);
   }
   
}
