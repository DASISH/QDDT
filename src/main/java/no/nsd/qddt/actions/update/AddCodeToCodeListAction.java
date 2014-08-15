package no.nsd.qddt.actions.update;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.nsd.qddt.actions.AbstractAction;
import no.nsd.qddt.model.ModuleVersion;
import no.nsd.qddt.service.CodeListService;
import no.nsd.qddt.servlets.ServletUtil;

public class AddCodeToCodeListAction extends AbstractAction {

   private ModuleVersion moduleVersion;
   private Integer codeId;
   private Integer codeListId;

   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.setRequestAndResponse(request, response);
      this.moduleVersion = (ModuleVersion) request.getAttribute("moduleVersion");

      this.getParams();
      this.executeDaoAndClose();
      this.redirectSuccessPage();
   }

   private void getParams() throws ServletException {
      codeId = ServletUtil.getRequestParamAsInteger(request, "codeid");
      codeListId = ServletUtil.getRequestParamAsInteger(request, "clid");
   }
   
   @Override
   protected void executeDao() throws SQLException {
      this.addCodeToCodeList();
   }

   private void addCodeToCodeList() throws SQLException {
      (new CodeListService(daoManager)).addCodeToCodeList(codeId, codeListId);
   }
   
   private void redirectSuccessPage() throws IOException {
      ServletUtil.redirect("/u/updatecodelist?mvid=" + moduleVersion.getId() + "&clid=" + codeListId, request, response);
   }

}
