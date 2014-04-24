package no.nsd.qddt.actions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.nsd.qddt.model.Module;
import no.nsd.qddt.service.ModuleService;
import no.nsd.qddt.servlets.ServletUtil;

public class UserHomeAction extends AbstractAction {

   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.setRequestAndResponse(request, response);
      
      this.executeDaoAndClose();
      this.forwardPage();
   }

   @Override
   protected void executeDao() throws SQLException {
      this.setModules();
   }
   
   private void setModules() throws SQLException {
      List<Module> modules = (new ModuleService(daoManager)).getModules();
      request.setAttribute("modules", modules);
   }
   
   private void forwardPage() throws ServletException, IOException {
      ServletUtil.forward("/WEB-INF/jsp/user_home.jsp", request, response);
   }
   
}
