package no.nsd.qddt.actions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.nsd.qddt.model.CodeList;
import no.nsd.qddt.model.ModuleVersion;
import no.nsd.qddt.model.Question;
import no.nsd.qddt.service.CodeListService;
import no.nsd.qddt.service.QuestionService;
import no.nsd.qddt.servlets.ServletUtil;

public class QuestionCodeListAction extends AbstractAction {

   private Integer questionId;
   private ModuleVersion moduleVersion;
   
   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.setRequestAndResponse(request, response);
      
      this.getRequestParams();
      this.executeDaoAndClose();
      this.forwardPage();
   }

   private void getRequestParams() {
      this.questionId = ServletUtil.getRequestParamAsInteger(request, "qid");
      this.moduleVersion = (ModuleVersion) request.getAttribute("moduleVersion");
   }
   
   @Override
   protected void executeDao() throws SQLException {
      this.setQuestion();
      this.setCodeLists();
   }
   
   private void setQuestion() throws SQLException {
      Question question = (new QuestionService(daoManager)).getQuestion(questionId);
      request.setAttribute("question", question);
   }

   private void setCodeLists() throws SQLException {
      List<CodeList> codeLists = (new CodeListService(daoManager)).getCodeListsForModule(moduleVersion.getModule().getId());
      request.setAttribute("codeLists", codeLists);
   }
   
   private void forwardPage() throws ServletException, IOException {
      ServletUtil.forward("/WEB-INF/jsp/question_code_list.jsp", request, response);
   }

   
}
