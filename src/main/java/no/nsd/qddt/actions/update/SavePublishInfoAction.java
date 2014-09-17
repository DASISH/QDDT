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

public class SavePublishInfoAction extends AbstractAction {

   private ModuleVersion oldModuleVersion;
   private ModuleVersion newModuleVersion;

   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.setRequestAndResponse(request, response);
      
      this.setOldModuleVersion();
      this.setNewModuleVersion();
      this.executeDaoAndClose();
      this.redirectSuccessPage();
   }

   private void setOldModuleVersion() {
      this.oldModuleVersion = (ModuleVersion) request.getAttribute("moduleVersion");
   }
   
   private void setNewModuleVersion() {
      newModuleVersion = new ModuleVersion();
      newModuleVersion.setId(oldModuleVersion.getId());
      newModuleVersion.setVersionPublishCode(ServletUtil.getRequestParamAsInteger(request, "version_publish_code"));
   }
   
   @Override
   protected void executeDao() throws SQLException {
      (new ModuleVersionService(daoManager)).updatePublishInfo(newModuleVersion, oldModuleVersion);
   }

   private void redirectSuccessPage() throws IOException {
      ServletUtil.redirect("/u/versioninfo?mvid=" + oldModuleVersion.getId() + "&saved", request, response);
   }

}
