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

public class SaveTitleAction extends AbstractAction {

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
      newModuleVersion.setTitle(request.getParameter("title"));
      newModuleVersion.setAuthors(request.getParameter("authors"));
      newModuleVersion.setAuthorsAffiliation(request.getParameter("affiliation"));
      newModuleVersion.setModuleAbstract(request.getParameter("abstract"));
   }

   @Override
   protected void executeDao() throws SQLException {
      (new ModuleVersionService(daoManager)).updateTitle(newModuleVersion);
   }

   private void redirectSuccessPage() throws IOException {
      ServletUtil.redirect("/u/title?mvid=" + moduleVersionId + "&saved", request, response);
   }

}
