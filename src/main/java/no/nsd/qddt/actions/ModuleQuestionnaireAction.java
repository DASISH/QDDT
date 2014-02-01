package no.nsd.qddt.actions;

import java.io.IOException;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.nsd.qddt.factories.DatabaseConnectionFactory;
import no.nsd.qddt.logic.ModuleLogic;
import no.nsd.qddt.logic.SqlUtil;
import no.nsd.qddt.model.Module;
import no.nsd.qddt.model.Urn;
import no.nsd.qddt.servlets.ServletUtil;

public class ModuleQuestionnaireAction {

   private Integer id;
   private Urn urn;
   private Connection conn;
   private HttpServletRequest request;
   private HttpServletResponse response;
   
   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.request = request;
      this.response = response;

      this.setId();
      this.setUrn();
      this.setModules();
      this.forwardPage();
   }
   
   private void setId() {
      try {
         id = Integer.valueOf(request.getParameter("id"));
      } catch (Exception ignored) {
      }
   }

   private void setUrn() {
      urn = new Urn();
      urn.setAgency(request.getParameter("agency"));
      urn.setId(request.getParameter("uuid"));
   }
   
   
   private void setModules() throws ServletException {
      try {
         this.setModulesDb();
      } catch (Exception e) {
         throw new ServletException(e);
      } finally {
         SqlUtil.close(conn);
      }
   }
   
   private void setModulesDb() throws Exception {
      conn = DatabaseConnectionFactory.getConnection();
      ModuleLogic logic = new ModuleLogic(conn);
      Module module = logic.getModule(id);
      request.setAttribute("module", module);
   }
   
   private void forwardPage() throws ServletException, IOException {
      ServletUtil.forward("/WEB-INF/jsp/module_questionnaire.jsp", request, response);
   }
   
}
