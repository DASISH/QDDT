package no.nsd.qddt.actions.update;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.nsd.qddt.actions.AbstractAction;
import no.nsd.qddt.model.Code;
import no.nsd.qddt.model.ModuleVersion;
import no.nsd.qddt.service.CodeService;
import no.nsd.qddt.servlets.ServletUtil;

public class SaveCodeCategoryAction extends AbstractAction {

   private ModuleVersion moduleVersion;
   private Code newCode;

   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.setRequestAndResponse(request, response);
      this.moduleVersion = (ModuleVersion) request.getAttribute("moduleVersion");

      this.createNewCode();
      this.executeDaoAndClose();
      this.redirectSuccessPage();
   }
   
   private void createNewCode() throws ServletException {
      newCode = new Code();
      newCode.setId(ServletUtil.getRequestParamAsInteger(request, "codeid"));
      newCode.setCategoryId(ServletUtil.getRequestParamAsInteger(request, "catid"));
      newCode.setVersionUpdated(Boolean.TRUE);
   }

   @Override
   protected void executeDao() throws SQLException {
      (new CodeService(daoManager)).updateCodeCategory(newCode);
   }
   
   private void redirectSuccessPage() throws IOException {
      String url = "/u/updatecode?mvid=" + moduleVersion.getId() + "&cid=" + newCode.getId() + "&saved";
      ServletUtil.redirect(url, request, response);
   }

}
