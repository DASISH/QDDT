package no.nsd.qddt.actions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.nsd.qddt.model.Code;
import no.nsd.qddt.model.CodeList;
import no.nsd.qddt.model.ModuleVersion;
import no.nsd.qddt.service.CodeListService;
import no.nsd.qddt.service.CodeService;
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
      this.setAllCodes();
      this.setCodesInList();
   }

   private void setCodeList() throws SQLException {
      CodeList codeList = (new CodeListService(daoManager)).getCodeList(codeListId);
      request.setAttribute("codeList", codeList);
   }

   private void setAllCodes() throws SQLException {
      List<Code> codes = (new CodeService(daoManager)).getCodesForModule(moduleVersion.getModule());
      request.setAttribute("codes", codes);
   }

   private void setCodesInList() throws SQLException {
      List<Code> codesInList = (new CodeService(daoManager)).getCodesForCodeList(codeListId);
      request.setAttribute("codesInList", codesInList);
      
      this.setCodeMap(codesInList);
   }
   
   private void setCodeMap(List<Code> codesInList) {
      if (codesInList == null) {
         return;
      }
      // mapping: codeId --> code, uses this in jsp to look up codes in this list by codeId.
      Map<Integer, Code> codeMap = new HashMap<Integer, Code>();
      for (Code c : codesInList) {
         codeMap.put(c.getId(), c);
      }
      request.setAttribute("codeMap", codeMap);
   }
   
   
   private void forwardPage() throws ServletException, IOException {
      ServletUtil.forward("/WEB-INF/jsp/code_list_update.jsp", request, response);
   }

   
}
