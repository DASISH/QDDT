package no.nsd.qddt.actions.update;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.nsd.qddt.actions.AbstractAction;
import no.nsd.qddt.model.ModuleVersion;
import no.nsd.qddt.service.ModuleVersionService;
import no.nsd.qddt.servlets.ServletUtil;

public class SaveVersionInfoAction extends AbstractAction {

   private ModuleVersion newModuleVersion;
   private Integer moduleVersionId;

   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.setRequestAndResponse(request, response);
      this.moduleVersionId = ServletUtil.getRequestParamAsInteger(request, "mvid");

      this.setNewModuleVersion();
      this.executeDaoAndClose();
      this.redirectSuccessPage();
   }

   private void setNewModuleVersion() throws ServletException {
      newModuleVersion = new ModuleVersion();
      newModuleVersion.setId(moduleVersionId);
      newModuleVersion.setVersionNumber(request.getParameter("version_number"));
      newModuleVersion.setVersionDescription(request.getParameter("version_description"));
      newModuleVersion.setStatus(ServletUtil.getRequestParamAsInteger(request, "status"));
   }
   
   @Override
   protected void executeDao() throws SQLException {
      (new ModuleVersionService(daoManager)).updateVersionInfo(newModuleVersion);
   }

   private void redirectSuccessPage() throws IOException {
      ServletUtil.redirect("/u/versioninfo?mvid=" + moduleVersionId + "&saved", request, response);
   }

}
