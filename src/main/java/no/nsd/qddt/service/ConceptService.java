package no.nsd.qddt.service;

import java.sql.Connection;
import javax.servlet.ServletException;
import no.nsd.qddt.factories.DatabaseConnectionFactory;
import no.nsd.qddt.logic.orm.ConceptLogic;
import no.nsd.qddt.logic.SqlUtil;
import no.nsd.qddt.logic.orm.persistence.ConceptPersistenceLogic;
import no.nsd.qddt.logic.orm.persistence.ConceptSchemePersistenceLogic;
import no.nsd.qddt.model.Concept;

public class ConceptService {
   
   private ConceptService() {
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


   public static void registerNewConcept(Concept concept) throws ServletException {
      Connection conn = null;
      try {
         conn = DatabaseConnectionFactory.getConnection();
         conn.setAutoCommit(false);
         
         ConceptPersistenceLogic logic = new ConceptPersistenceLogic(conn);
         Integer conceptId = logic.registerNewConcept(concept);
         concept.setId(conceptId);
         
         ConceptSchemePersistenceLogic csLogic = new ConceptSchemePersistenceLogic(conn);
         csLogic.addConceptToScheme(concept);
         csLogic.setConceptSchemeUpdated(concept.getConceptSchemeId());
         
         conn.commit();
      } catch (Exception e) {
         SqlUtil.rollback(conn);
         throw new ServletException(e);
      } finally {
         SqlUtil.close(conn);
      }
   }
   

   public static void updateConcept(Concept concept) throws ServletException {
      Connection conn = null;
      try {
         conn = DatabaseConnectionFactory.getConnection();
         conn.setAutoCommit(false);
         
         ConceptPersistenceLogic logic = new ConceptPersistenceLogic(conn);
         logic.updateConcept(concept);
         
         ConceptSchemePersistenceLogic csLogic = new ConceptSchemePersistenceLogic(conn);
         csLogic.setConceptSchemeUpdated(concept.getConceptSchemeId());
         
         conn.commit();
      } catch (Exception e) {
         SqlUtil.rollback(conn);
         throw new ServletException(e);
      } finally {
         SqlUtil.close(conn);
      }
   }

   
   
}
