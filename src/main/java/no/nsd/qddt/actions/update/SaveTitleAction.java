package no.nsd.qddt.actions.update;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.nsd.qddt.model.ModuleVersion;
import no.nsd.qddt.service.ModuleVersionService;
import no.nsd.qddt.servlets.ServletUtil;

public class SaveTitleAction {

   private ModuleVersion newModuleVersion;
   private Integer moduleVersionId;
   private HttpServletRequest request;
   private HttpServletResponse response;

   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.request = request;
      this.response = response;
      this.moduleVersionId = ServletUtil.getRequestParamAsInteger(request, "mvid");

      this.setNewModuleVersion();
      this.saveModuleVersionTitle();
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
   
   private void saveModuleVersionTitle() throws ServletException {
      ModuleVersionService.updateTitle(newModuleVersion);
   }

   private void redirectSuccessPage() throws IOException {
      ServletUtil.redirect("/u/title?mvid=" + moduleVersionId, request, response);
   }

}
