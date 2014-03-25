package no.nsd.qddt.service;

import java.sql.Connection;
import javax.servlet.ServletException;
import no.nsd.qddt.factories.DatabaseConnectionFactory;
import no.nsd.qddt.logic.orm.ConceptLogic;
import no.nsd.qddt.logic.SqlUtil;
import no.nsd.qddt.logic.orm.ConceptSchemeLogic;
import no.nsd.qddt.logic.orm.persistence.ConceptSchemePersistenceLogic;
import no.nsd.qddt.model.ConceptScheme;

public class ConceptSchemeService {

   private ConceptSchemeService() {
   }

   public static ConceptScheme getConceptScheme(Integer conceptSchemeId) throws ServletException {
      Connection conn = null;
      try {
         conn = DatabaseConnectionFactory.getConnection();

         ConceptSchemeLogic csLogic = new ConceptSchemeLogic(conn);
         ConceptScheme conceptScheme = csLogic.getConceptScheme(conceptSchemeId);

         if (conceptScheme != null) {
            ConceptLogic logic = new ConceptLogic(conn);
            logic.getConceptsForScheme(conceptScheme);
         }

         return conceptScheme;
      } catch (Exception e) {
         throw new ServletException(e);
      } finally {
         SqlUtil.close(conn);
      }
   }

   public static void registerNewConceptScheme(ConceptScheme conceptScheme) throws ServletException {
      Connection conn = null;
      try {
         conn = DatabaseConnectionFactory.getConnection();
         conn.setAutoCommit(false);

         ConceptSchemePersistenceLogic logic = new ConceptSchemePersistenceLogic(conn);
         logic.registerNewConceptScheme(conceptScheme);

         conn.commit();
      } catch (Exception e) {
         SqlUtil.rollback(conn);
         throw new ServletException(e);
      } finally {
         SqlUtil.close(conn);
      }
   }

   public static void updateConceptScheme(ConceptScheme conceptScheme) throws ServletException {
      Connection conn = null;
      try {
         conn = DatabaseConnectionFactory.getConnection();
         ConceptSchemePersistenceLogic logic = new ConceptSchemePersistenceLogic(conn);
         logic.updateConceptScheme(conceptScheme);
      } catch (Exception e) {
         throw new ServletException(e);
      } finally {
         SqlUtil.close(conn);
      }
   }
   
   
}