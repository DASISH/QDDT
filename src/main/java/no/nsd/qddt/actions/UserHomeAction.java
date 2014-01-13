package no.nsd.qddt.actions;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.nsd.qddt.factories.DatabaseConnectionFactory;
import no.nsd.qddt.logic.ModuleLogic;
import no.nsd.qddt.logic.SqlUtil;
import no.nsd.qddt.model.Module;
import no.nsd.qddt.servlets.ServletUtil;

public class UserHomeAction {

   private Connection conn;
   private HttpServletRequest request;
   private HttpServletResponse response;
   
   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.request = request;
      this.response = response;

      this.setModules();
      this.forwardPage();
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
      List<Module> modules = logic.getModules();
      request.setAttribute("modules", modules);
   }
   
   private void forwardPage() throws ServletException, IOException {
      ServletUtil.forward("/WEB-INF/jsp/user_home.jsp", request, response);
   }
   
}
