package no.nsd.qddt.actions;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.nsd.qddt.model.CodeList;
import no.nsd.qddt.model.ModuleVersion;
import no.nsd.qddt.service.CodeListService;
import no.nsd.qddt.servlets.ServletUtil;

public class CodeListUpdateAction extends AbstractAction {


   private Integer codeListId;
   private ModuleVersion moduleVersion;
   
   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.setRequestAndResponse(request, response);
      
      this.getRequestParams();
      this.executeDaoAndClose();
      this.forwardPage();
   }

   private void getRequestParams() {
      this.codeListId = ServletUtil.getRequestParamAsInteger(request, "clid");
      this.moduleVersion = (ModuleVersion) request.getAttribute("moduleVersion");
   }
   
   @Override
   protected void executeDao() throws SQLException {
      this.setCodeList();
   }

   private void setCodeList() throws SQLException {
      CodeList codeList = (new CodeListService(daoManager)).getCodeList(codeListId);
      request.setAttribute("codeList", codeList);
   }
   
   private void forwardPage() throws ServletException, IOException {
      ServletUtil.forward("/WEB-INF/jsp/code_list_update.jsp", request, response);
   }

   
}
