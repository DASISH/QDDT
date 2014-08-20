package no.nsd.qddt.actions.update;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.nsd.qddt.actions.AbstractAction;
import no.nsd.qddt.model.CodeList;
import no.nsd.qddt.model.ModuleVersion;
import no.nsd.qddt.service.CodeListService;
import no.nsd.qddt.servlets.ServletUtil;

public class ChangeCodeListCombinedAction extends AbstractAction {

   private ModuleVersion moduleVersion;
   private CodeList codeList;
   private String action;

   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.setRequestAndResponse(request, response);
      this.moduleVersion = (ModuleVersion) request.getAttribute("moduleVersion");
      this.action = request.getParameter("action");

      this.createCodeListFromParams();
      this.executeDaoAndClose();
      this.redirectSuccessPage();
   }

   private void createCodeListFromParams() throws ServletException {
      codeList = new CodeList();
      codeList.setId(ServletUtil.getRequestParamAsInteger(request, "clid"));
      codeList.setValidCodeListId(ServletUtil.getRequestParamAsInteger(request, "vclid"));
      codeList.setMissingCodeListId(ServletUtil.getRequestParamAsInteger(request, "mclid"));
   }
   
   @Override
   protected void executeDao() throws SQLException {
      if (codeList.getValidCodeListId() != null) {
         this.updateValidCodeList();
      } else {
         this.updateMissingCodeList();
      }
   }

   private void updateValidCodeList() throws SQLException {
      if ("Remove".equals(action)) {
         codeList.setValidCodeListId(null);
      }
      
      (new CodeListService(daoManager)).updateValidCodeListId(codeList);
   }

   private void updateMissingCodeList() throws SQLException {
      if ("Remove".equals(action)) {
         codeList.setMissingCodeListId(null);
      }
      
      (new CodeListService(daoManager)).updateMissingCodeListId(codeList);
   }
   
   private void redirectSuccessPage() throws IOException {
      ServletUtil.redirect("/u/updatecodelist?mvid=" + moduleVersion.getId() + "&clid=" + codeList.getId(), request, response);
   }

}
