package no.nsd.qddt.actions.update;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.nsd.qddt.actions.AbstractAction;
import no.nsd.qddt.logic.UrnUtil;
import no.nsd.qddt.model.Code;
import no.nsd.qddt.model.ModuleVersion;
import no.nsd.qddt.model.Urn;
import no.nsd.qddt.service.CodeService;
import no.nsd.qddt.servlets.ServletUtil;

public class NewCodeAction extends AbstractAction {

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
      Urn urn = UrnUtil.createNewUrn();
      urn.setAgency(moduleVersion.getModule().getAgency());
      newCode.setUrn(urn);
      newCode.setModuleVersionId(moduleVersion.getId());
      newCode.setCategoryId(ServletUtil.getRequestParamAsInteger(request, "cid"));
      newCode.setVersionUpdated(Boolean.TRUE);
   }

   @Override
   protected void executeDao() throws SQLException {
      (new CodeService(daoManager)).registerNewCode(newCode);
   }
   
   private void redirectSuccessPage() throws IOException {
      String url = "/u/code?mvid=" + moduleVersion.getId() + "&cid=" + newCode.getId();
      ServletUtil.redirect(url, request, response);
   }

}
