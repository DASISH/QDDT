package no.nsd.qddt.actions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.nsd.qddt.model.Agency;
import no.nsd.qddt.model.Module;
import no.nsd.qddt.model.Study;
import no.nsd.qddt.service.AgencyService;
import no.nsd.qddt.service.ModuleService;
import no.nsd.qddt.service.StudyService;
import no.nsd.qddt.servlets.ServletUtil;

public class ModuleAction extends AbstractAction {

   private Integer moduleId;
   
   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.setRequestAndResponse(request, response);
      
      this.getRequestParams();
      this.executeDaoAndClose();
      this.forwardPage();
   }

   private void getRequestParams() {
      this.moduleId = ServletUtil.getRequestParamAsInteger(request, "id");
   }
   
   @Override
   protected void executeDao() throws SQLException {
      this.setModule();
      this.setStudies();
      this.setAgencies();
   }
   
   private void setModule() throws SQLException {
      Module module = (new ModuleService(daoManager)).getModule(moduleId);
      request.setAttribute("module", module);
   }
   private void setStudies() throws SQLException {
      List<Study> studies = (new StudyService(daoManager)).getStudies();
      request.setAttribute("studies", studies);
   }
   private void setAgencies() throws SQLException {
      List<Agency> agencies = (new AgencyService(daoManager)).getAgencies();
      request.setAttribute("agencies", agencies);
   }
   
   private void forwardPage() throws ServletException, IOException {
      ServletUtil.forward("/WEB-INF/jsp/module.jsp", request, response);
   }

   
}
