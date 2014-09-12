package no.nsd.qddt.actions.update;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import no.nsd.qddt.actions.AbstractAction;
import no.nsd.qddt.model.Actor;
import no.nsd.qddt.model.Module;
import no.nsd.qddt.model.ModuleVersion;
import no.nsd.qddt.model.User;
import no.nsd.qddt.service.ActorService;
import no.nsd.qddt.service.ModuleService;
import no.nsd.qddt.service.ModuleVersionService;
import no.nsd.qddt.servlets.ServletUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadFileAction extends AbstractAction {

   private ModuleVersion moduleVersion;

   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.setRequestAndResponse(request, response);
      
      this.executeDaoAndClose();
      this.redirectSuccessPage();
   }

   
   @Override
   protected void executeDao() throws SQLException {
      this.getModule();
      this.saveFile();
   }

   
   private void getModule() throws SQLException {
      HttpSession httpSession = request.getSession();
      User user = (User) httpSession.getAttribute("user");

      String uri = ServletUtil.getUriWithoutJsessionId(request.getRequestURI());
      Integer moduleVersionId = getId(uri);
      
      moduleVersion = (new ModuleVersionService(daoManager)).getModuleVersion(moduleVersionId);
      Module module = (new ModuleService(daoManager)).getModule(moduleVersion.getModule().getId());
      moduleVersion.setModule(module);
      Actor actor = (new ActorService(daoManager)).getActorForUserAndModule(user.getId(), module.getId());
      request.setAttribute("moduleVersion", moduleVersion);
      request.setAttribute("actor", actor);   
   }
   
   
   public Integer getId(String url) {
      try {
         int i = url.lastIndexOf('/');
         String s = url.substring(i + 1);
         return Integer.valueOf(s);
      } catch (Exception e) {
         return null;
      }
   }
   
   
   public void saveFile() throws SQLException {
      FileItemFactory factory = new DiskFileItemFactory();
      ServletFileUpload upload = new ServletFileUpload(factory);

      try {
         List items = upload.parseRequest(request);

         for (Object o : items) {
            FileItem fileItem = (FileItem) o;
            if (fileItem.getName() != null && fileItem.getName().length() != 0) {
               daoManager.getFileDaoUpdate().registerNewFile(fileItem, moduleVersion.getId());
            }
         }
      } catch (Exception e) {
         throw new SQLException(e);
      }
   }

   
   private void redirectSuccessPage() throws IOException {
      ServletUtil.redirect("/u/document?mvid=" + moduleVersion.getId(), request, response);
   }

}
