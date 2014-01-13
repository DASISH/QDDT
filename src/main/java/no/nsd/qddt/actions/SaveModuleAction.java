package no.nsd.qddt.actions;

import java.io.IOException;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.nsd.qddt.factories.DatabaseConnectionFactory;
import no.nsd.qddt.logic.ModuleLogic;
import no.nsd.qddt.logic.SqlUtil;
import no.nsd.qddt.logic.UrnUtil;
import no.nsd.qddt.model.Module;
import no.nsd.qddt.servlets.ServletUtil;

public class SaveModuleAction {

   private Connection conn;
   private Module module;
   private HttpServletRequest request;
   private HttpServletResponse response;
   
   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.request = request;
      this.response = response;

      this.createNewModule();
      this.registerNewModule();
      this.redirectSuccessPage();
   }
   
   private void createNewModule() {
      module = new Module();
      module.setUrn(UrnUtil.createNewUrn());
      module.setStudy(request.getParameter("study"));
      module.setTitle(request.getParameter("title"));
      module.setAuthors(request.getParameter("authors"));
      module.setAuthorsAffiliation(request.getParameter("affiliation"));
      module.setModuleAbstract(request.getParameter("abstract"));
      module.setRepeat(this.getRepeatValue());
   }
   
   private Boolean getRepeatValue() {
      String s = request.getParameter("repeat");
      if ("yes".equals(s)) {
         return true;
      }
      if ("no".equals(s)) {
         return false;
      }
      return null;
   }
   
   private void registerNewModule() throws ServletException {
      try {
         this.registerNewModuleDb();
      } catch (Exception e) {
         throw new ServletException(e);
      } finally {
         SqlUtil.close(conn);
      }
   }
   
   private void registerNewModuleDb() throws Exception {
      conn = DatabaseConnectionFactory.getConnection();
      ModuleLogic logic = new ModuleLogic(conn);
      logic.registerNewModule(module);
   }
   
   private void redirectSuccessPage() throws IOException {
      ServletUtil.redirect("/u/", request, response);
   }
   
}
