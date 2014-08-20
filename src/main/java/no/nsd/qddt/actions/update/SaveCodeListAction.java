package no.nsd.qddt.actions.update;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.nsd.qddt.actions.AbstractAction;
import no.nsd.qddt.logic.UrnUtil;
import no.nsd.qddt.model.CodeList;
import no.nsd.qddt.model.ModuleVersion;
import no.nsd.qddt.model.Urn;
import no.nsd.qddt.service.CodeListService;
import no.nsd.qddt.servlets.ServletUtil;

public class SaveCodeListAction extends AbstractAction {

   private CodeList newCodeList;
   private ModuleVersion moduleVersion;
   private String action;

   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.setRequestAndResponse(request, response);
      this.moduleVersion = (ModuleVersion) request.getAttribute("moduleVersion");
      this.action = request.getParameter("action");

      this.createNewCodeList();
      this.executeDaoAndClose();
      this.redirectSuccessPage();
   }

   private void createNewCodeList() throws ServletException {
      newCodeList = new CodeList();
      newCodeList.setId(ServletUtil.getRequestParamAsInteger(request, "clid"));
      newCodeList.setName(request.getParameter("name"));
      newCodeList.setLabel(request.getParameter("label"));
      newCodeList.setDescription(request.getParameter("description"));
      newCodeList.setCodeListType(this.getCodeListType());
      newCodeList.setVersionDescription(request.getParameter("version_description"));
      newCodeList.setVersionUpdated(Boolean.TRUE);
   }
   
   private Integer getCodeListType() {
      String type = request.getParameter("type");
      if ("v".equals(type)) {
         return CodeList.CODE_LIST_TYPE_VALID;
      }
      if ("m".equals(type)) {
         return CodeList.CODE_LIST_TYPE_MISSING;
      }
      if ("c".equals(type)) {
         return CodeList.CODE_LIST_TYPE_COMBINED;
      }
      return null;
   }
   
   @Override
   protected void executeDao() throws SQLException {
      if ("Delete".equals(action)) {
         this.deleteQuestion();
      } else if (newCodeList.getId() == null) {
         this.registerNewQuestion();
      } else {
         this.updateQuestion();
      }
   }

   private void registerNewQuestion() throws SQLException {
      Urn urn = UrnUtil.createNewUrn();
      urn.setAgency(moduleVersion.getModule().getAgency());
      newCodeList.setUrn(urn);
      newCodeList.setModuleVersionId(moduleVersion.getId());
      
      (new CodeListService(daoManager)).registerNewCodeList(newCodeList);
   }

   private void updateQuestion() throws SQLException {
      (new CodeListService(daoManager)).updateCodeList(newCodeList);
   }

   private void deleteQuestion() throws SQLException {
      //(new QuestionService(daoManager)).deleteQuestion(newQuestion);
   }
   
   private void redirectSuccessPage() throws IOException {
      if ("Delete".equals(action)) {
         ServletUtil.redirect("/u/codelist?mvid=" + moduleVersion.getId() + "&saved", request, response);
      } else {
         ServletUtil.redirect("/u/updatecodelist?mvid=" + moduleVersion.getId() + "&clid=" + newCodeList.getId() + "&saved", request, response);
      }
   }


}
