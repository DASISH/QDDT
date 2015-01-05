package no.nsd.qddt.actions.update;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.nsd.qddt.actions.AbstractAction;
import no.nsd.qddt.model.ModuleVersion;
import no.nsd.qddt.model.exception.VersionException;
import no.nsd.qddt.service.ModuleVersionService;
import no.nsd.qddt.servlets.ServletUtil;

public class SavePublishInfoAction extends AbstractAction {

   private ModuleVersion oldModuleVersion;
   private ModuleVersion newModuleVersion;
   private boolean error;

   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.setRequestAndResponse(request, response);
      
      this.setOldModuleVersion();
      this.setNewModuleVersion();
      this.executeDaoAndClose();
      this.redirectPage();
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
      try {
         (new ModuleVersionService(daoManager)).updatePublishInfo(newModuleVersion, oldModuleVersion);
      } catch (VersionException e) {
         error = true;
         request.setAttribute("error", e.getMessage());
      }
   }

   private void redirectPage() throws ServletException, IOException {
      if (error) {
         this.forwardErrorPage();
      } else {
         this.redirectSuccessPage();
      }
   }
   
   private void forwardErrorPage() throws ServletException, IOException {
      ServletUtil.forward("/WEB-INF/jsp/publish_info.jsp", request, response);
   }
   
   private void redirectSuccessPage() throws IOException {
      ServletUtil.redirect("/u/publishinfo?mvid=" + oldModuleVersion.getId() + "&saved", request, response);
   }

}
