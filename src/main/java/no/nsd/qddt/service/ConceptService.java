package no.nsd.qddt.service;

import java.sql.Connection;
import javax.servlet.ServletException;
import no.nsd.qddt.factories.DatabaseConnectionFactory;
import no.nsd.qddt.logic.ConceptLogic;
import no.nsd.qddt.logic.SqlUtil;
import no.nsd.qddt.model.Concept;
import no.nsd.qddt.model.ConceptScheme;

public class ConceptService {
   
   private ConceptService() {
   }
   
   public static ConceptScheme getConceptScheme(Integer conceptSchemeId) throws ServletException {
      Connection conn = null;
      try {
         conn = DatabaseConnectionFactory.getConnection();
         ConceptLogic logic = new ConceptLogic(conn);
         return logic.getConceptsForScheme(conceptSchemeId);
      } catch (Exception e) {
         throw new ServletException(e);
      } finally {
         SqlUtil.close(conn);
      }
   }

   public static Concept getConcept(Integer conceptId) throws ServletException {
      Connection conn = null;
      try {
         conn = DatabaseConnectionFactory.getConnection();
         ConceptLogic logic = new ConceptLogic(conn);
         return logic.getConcept(conceptId);
      } catch (Exception e) {
         throw new ServletException(e);
      } finally {
         SqlUtil.close(conn);
      }
   }


}
