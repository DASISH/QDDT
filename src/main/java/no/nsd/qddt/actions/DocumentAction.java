package no.nsd.qddt.actions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.nsd.qddt.model.Document;
import no.nsd.qddt.model.ModuleVersion;
import no.nsd.qddt.service.DocumentService;
import no.nsd.qddt.servlets.ServletUtil;

public class DocumentAction extends AbstractAction {
   
   private ModuleVersion moduleVersion;
   
   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.setRequestAndResponse(request, response);
      this.moduleVersion = (ModuleVersion) request.getAttribute("moduleVersion");
      
      this.executeDaoAndClose();
      this.forwardPage();
   }
   
   @Override
   protected void executeDao() throws SQLException {
      this.getDocuments();
   }
   
   public void getDocuments() throws SQLException {
      List<Document> documents = (new DocumentService(daoManager)).getDocumentsForModuleVersion(moduleVersion.getId());
      request.setAttribute("documents", documents);      
   }
   
   
   private void forwardPage() throws ServletException, IOException {
      ServletUtil.forward("/WEB-INF/jsp/document.jsp", request, response);
   }

   
}
