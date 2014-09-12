package no.nsd.qddt.actions.update;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.nsd.qddt.actions.AbstractAction;
import no.nsd.qddt.model.ModuleVersion;
import no.nsd.qddt.servlets.ServletUtil;

public class DownloadFileAction extends AbstractAction {

   private ModuleVersion moduleVersion;
   private Integer documentId;

   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.setRequestAndResponse(request, response);
      
      this.documentId = ServletUtil.getRequestParamAsInteger(request, "id");
      
      this.executeDaoAndClose();
   }
   
   @Override
   protected void executeDao() throws SQLException {
      this.readFile();
   }

   
   public void readFile() throws SQLException {
      try {
         this.daoManager.getDocumentDao().readFile(documentId, response);
      } catch (IOException e) {
         throw new SQLException(e);
      }
   }

}
