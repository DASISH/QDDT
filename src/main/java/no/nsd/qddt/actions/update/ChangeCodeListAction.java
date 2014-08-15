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

public class ChangeCodeListAction extends AbstractAction {

   private ModuleVersion moduleVersion;
   private Integer codeId;
   private Integer codeListId;
   private Integer sortOrder;
   private String action;

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
      sortOrder = ServletUtil.getRequestParamAsInteger(request, "sort");
      action = request.getParameter("action");
   }
   
   @Override
   protected void executeDao() throws SQLException {
      if ("up".equals(action)) {
         this.moveCodeUp();
      } else if ("down".equals(action)) {
         this.moveCodeDown();
      } else if ("remove".equals(action)) {
         this.removeCode();
      }
   }

   private void moveCodeUp() throws SQLException {
      (new CodeListService(daoManager)).moveCodeFromOldPositionToNewInCodeList(codeId, sortOrder, sortOrder - 1, codeListId);
   }
   
   private void moveCodeDown() throws SQLException {
      (new CodeListService(daoManager)).moveCodeFromOldPositionToNewInCodeList(codeId, sortOrder, sortOrder + 1, codeListId);
   }

   private void removeCode() throws SQLException {
      (new CodeListService(daoManager)).removeCodeFromCodeListAndUpdateSortOrder(codeId, codeListId, sortOrder);
   }
   
   private void redirectSuccessPage() throws IOException {
      ServletUtil.redirect("/u/updatecodelist?mvid=" + moduleVersion.getId() + "&clid=" + codeListId, request, response);
   }

}
