package no.nsd.qddt.actions;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.nsd.qddt.model.Module;
import no.nsd.qddt.model.ModuleVersion;
import no.nsd.qddt.service.ModuleService;
import no.nsd.qddt.service.ModuleVersionService;
import no.nsd.qddt.servlets.ServletUtil;

public class HistoryAction {

   private Integer moduleId;
   private HttpServletRequest request;
   private HttpServletResponse response;
   
   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.request = request;
      this.response = response;
      this.moduleId = ServletUtil.getRequestParamAsInteger(request, "id");

      this.setModule();
      this.setModuleVersions();
      this.forwardPage();
   }
   
   
   private void setModule() throws ServletException {
      Module module = ModuleService.getModule(moduleId);
      request.setAttribute("module", module);
   }

   private void setModuleVersions() throws ServletException {
      List<ModuleVersion> moduleVersions = ModuleVersionService.getModuleVersions(moduleId);
      request.setAttribute("moduleVersions", moduleVersions);
      
      if (moduleVersions != null && !moduleVersions.isEmpty()) {
         ModuleVersion lastModuleVersion = moduleVersions.get(moduleVersions.size() - 1);
         request.setAttribute("lastModuleVersion", lastModuleVersion);
      }
   }
   
   private void forwardPage() throws ServletException, IOException {
      ServletUtil.forward("/WEB-INF/jsp/history.jsp", request, response);
   }
   
}
