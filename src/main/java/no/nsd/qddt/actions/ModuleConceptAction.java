package no.nsd.qddt.actions;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.nsd.qddt.factories.DatabaseConnectionFactory;
import no.nsd.qddt.logic.ConceptLogic;
import no.nsd.qddt.logic.ModuleLogic;
import no.nsd.qddt.logic.SqlUtil;
import no.nsd.qddt.model.Concept;
import no.nsd.qddt.model.ConceptScheme;
import no.nsd.qddt.model.Module;
import no.nsd.qddt.model.Urn;
import no.nsd.qddt.servlets.ServletUtil;

public class ModuleConceptAction {

   private Integer id;
   private Integer conceptId;
   private Module module;
   private Urn urn;
   private Connection conn;
   private HttpServletRequest request;
   private HttpServletResponse response;
   
   public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.request = request;
      this.response = response;

      this.setId();
      this.setConceptId();
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
   private void setConceptId() {
      try {
         conceptId = Integer.valueOf(request.getParameter("cid"));
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
         conn = DatabaseConnectionFactory.getConnection();
         this.setModulesDb();
         this.setConceptsDb();
         this.setConceptDb();
      } catch (Exception e) {
         throw new ServletException(e);
      } finally {
         SqlUtil.close(conn);
      }
   }
   
   private void setModulesDb() throws Exception {
      ModuleLogic logic = new ModuleLogic(conn);
      module = logic.getModule(id);
      request.setAttribute("module", module);
   }
   private void setConceptsDb() throws Exception {
      ConceptLogic logic = new ConceptLogic(conn);
      ConceptScheme conceptScheme = logic.getConceptsForScheme(module.getConceptSchemeId());
      request.setAttribute("conceptScheme", conceptScheme);
   }
   private void setConceptDb() throws Exception {
      if (this.conceptId != null) {
         ConceptLogic logic = new ConceptLogic(conn);
         Concept c = logic.getConcept(conceptId);
         request.setAttribute("concept", c);
      }
   }
   
   
   private void forwardPage() throws ServletException, IOException {
      ServletUtil.forward("/WEB-INF/jsp/module_concept.jsp", request, response);
   }
   
}
