package no.nsd.qddt.actions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.nsd.qddt.model.Module;
import no.nsd.qddt.model.ModuleVersion;
import no.nsd.qddt.service.ModuleService;
import no.nsd.qddt.service.ModuleVersionService;
import no.nsd.qddt.servlets.ServletUtil;

public class HistoryAction  extends AbstractAction {

   private Integer moduleId;
   
   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.setRequestAndResponse(request, response);

      this.getRequestParams();
      this.executeDaoAndClose();
      this.forwardPage();
   }

   private void getRequestParams() {
      this.moduleId = ServletUtil.getRequestParamAsInteger(request, "id");
   }
   
   @Override
   protected void executeDao() throws SQLException {
      this.setModule();
      this.setModuleVersions();
   }
   
   private void setModule() throws SQLException {
      Module module = (new ModuleService(daoManager)).getModule(moduleId);
      request.setAttribute("module", module);
   }

   private void setModuleVersions() throws SQLException {
      List<ModuleVersion> moduleVersions = (new ModuleVersionService(daoManager)).getModuleVersions(moduleId);
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
