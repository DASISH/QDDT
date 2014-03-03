package no.nsd.qddt.actions;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.nsd.qddt.model.Module;
import no.nsd.qddt.model.Urn;
import no.nsd.qddt.service.ModuleService;
import no.nsd.qddt.servlets.ServletUtil;

public class HistoryAction {

   private Urn urn;
   private HttpServletRequest request;
   private HttpServletResponse response;
   
   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.request = request;
      this.response = response;

      this.setUrn();
      this.setModules();
      this.forwardPage();
   }
   
   private void setUrn() {
      urn = new Urn();
      urn.setAgency(request.getParameter("agency"));
      urn.setId(request.getParameter("id"));
   }
   
   private void setModules() throws ServletException {
      List<Module> modules = ModuleService.getModules(urn);
      request.setAttribute("modules", modules);
   }
   
   private void forwardPage() throws ServletException, IOException {
      ServletUtil.forward("/WEB-INF/jsp/history.jsp", request, response);
   }
   
}
