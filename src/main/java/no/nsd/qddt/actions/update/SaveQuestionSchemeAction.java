package no.nsd.qddt.actions.update;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.nsd.qddt.actions.AbstractAction;
import no.nsd.qddt.logic.UrnUtil;
import no.nsd.qddt.model.ModuleVersion;
import no.nsd.qddt.model.QuestionScheme;
import no.nsd.qddt.model.Urn;
import no.nsd.qddt.service.QuestionSchemeService;
import no.nsd.qddt.servlets.ServletUtil;

public class SaveQuestionSchemeAction extends AbstractAction {

   private QuestionScheme newQuestionScheme;
   private ModuleVersion moduleVersion;

   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.setRequestAndResponse(request, response);
      this.moduleVersion = (ModuleVersion) request.getAttribute("moduleVersion");

      this.createNewQuestionScheme();
      this.executeDaoAndClose();
      this.redirectSuccessPage();
   }

   private void createNewQuestionScheme() throws ServletException {
      newQuestionScheme = new QuestionScheme();
      newQuestionScheme.setId(ServletUtil.getRequestParamAsInteger(request, "qsid"));
      newQuestionScheme.setName(request.getParameter("name"));
      newQuestionScheme.setLabel(request.getParameter("label"));
      newQuestionScheme.setDescription(request.getParameter("description"));
      newQuestionScheme.setVersionDescription(request.getParameter("version_description"));
   }
   
   @Override
   protected void executeDao() throws SQLException {
      if (newQuestionScheme.getId() == null) {
         this.registerNewQuestionScheme();
      } else {
         this.updateQuestionScheme();
      }
   }

   private void registerNewQuestionScheme() throws SQLException {
      Urn urn = UrnUtil.createNewUrn();
      urn.setAgency(moduleVersion.getModule().getAgency());
      newQuestionScheme.setUrn(urn);
      newQuestionScheme.setModuleVersionId(moduleVersion.getId());
      newQuestionScheme.setVersionUpdated(Boolean.TRUE);
      
      (new QuestionSchemeService(daoManager)).registerNewQuestionScheme(newQuestionScheme);
   }

   private void updateQuestionScheme() throws SQLException {
      newQuestionScheme.setVersionUpdated(Boolean.TRUE);
      (new QuestionSchemeService(daoManager)).updateQuestionScheme(newQuestionScheme);
   }

   
   private void redirectSuccessPage() throws IOException {
      ServletUtil.redirect("/u/questionscheme?mvid=" + moduleVersion.getId() + "&saved", request, response);
   }


}
