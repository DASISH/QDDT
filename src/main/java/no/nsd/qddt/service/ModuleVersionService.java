package no.nsd.qddt.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import javax.servlet.ServletException;
import no.nsd.qddt.factories.DatabaseConnectionFactory;
import no.nsd.qddt.logic.SqlUtil;
import no.nsd.qddt.logic.dao.ConceptDao;
import no.nsd.qddt.logic.dao.ConceptSchemeDao;
import no.nsd.qddt.logic.dao.ModuleVersionDao;
import no.nsd.qddt.logic.dao.persist.ConceptDaoPersist;
import no.nsd.qddt.logic.dao.persist.ConceptSchemeDaoPersist;
import no.nsd.qddt.logic.dao.persist.ModuleVersionDaoPersist;
import no.nsd.qddt.model.Concept;
import no.nsd.qddt.model.ConceptScheme;
import no.nsd.qddt.model.ModuleVersion;

public class ModuleVersionService {

   private ModuleVersionService() {
   }

   public static List<ModuleVersion> getModuleVersions(Integer moduleId) throws ServletException {
      Connection conn = null;
      try {
         conn = DatabaseConnectionFactory.getConnection();
         ModuleVersionDao logic = new ModuleVersionDao(conn);
         return logic.getModuleVersions(moduleId);
      } catch (Exception e) {
         throw new ServletException(e);
      } finally {
         SqlUtil.close(conn);
      }
   }

   public static ModuleVersion getModuleVersion(Integer moduleVersionId) throws ServletException {
      Connection conn = null;
      try {
         conn = DatabaseConnectionFactory.getConnection();
         ModuleVersionDao logic = new ModuleVersionDao(conn);
         return logic.getModuleVersion(moduleVersionId);
      } catch (Exception e) {
         throw new ServletException(e);
      } finally {
         SqlUtil.close(conn);
      }
   }

   public static void registerNewModuleVersion(ModuleVersion mv) throws ServletException {
      Connection conn = null;
      try {
         conn = DatabaseConnectionFactory.getConnection();
         conn.setAutoCommit(false);

         ModuleVersionDaoPersist logic = new ModuleVersionDaoPersist(conn);
         Integer newModuleVersionId = logic.registerNewModuleVersion(mv);

         if (mv.getConceptSchemeId() != null) {
            ConceptSchemeDao csLogic = new ConceptSchemeDao(conn);
            ConceptScheme cs = csLogic.getConceptScheme(mv.getConceptSchemeId());
            
            ConceptDao cLogic = new ConceptDao(conn);
            cLogic.getConceptsForScheme(cs);
            Collection<Concept> concepts = cs.getConcepts();

            cs.setModuleVersionId(newModuleVersionId);
            cs.setVersionUpdated(Boolean.FALSE);
            ConceptSchemeDaoPersist cspLogic = new ConceptSchemeDaoPersist(conn);
            cspLogic.registerNewConceptScheme(cs);

            for (Concept c : concepts) {
               c.setModuleVersionId(newModuleVersionId);
               c.setConceptSchemeId(cs.getId());
               copyConcept(c, conn);
               List<Concept> subConcepts = c.getSubConcepts();
               for (Concept sub : subConcepts) {
                  sub.setModuleVersionId(newModuleVersionId);
                  sub.setConceptSchemeId(cs.getId());
                  sub.setParentConceptId(c.getId());
                  copyConcept(sub, conn);
               }
            }
         }

         conn.commit();
      } catch (Exception e) {
         SqlUtil.rollback(conn);
         throw new ServletException(e);
      } finally {
         SqlUtil.close(conn);
      }
   }

   private static void copyConcept(Concept c, Connection conn) throws SQLException {
      c.setVersionUpdated(Boolean.FALSE);
      ConceptDaoPersist cpLogic = new ConceptDaoPersist(conn);
      Integer newConceptId = cpLogic.registerNewConcept(c);
      c.setId(newConceptId);

      ConceptSchemeDaoPersist cspLogic = new ConceptSchemeDaoPersist(conn);
      cspLogic.addConceptToScheme(c);
   }

   public static void updateTitle(ModuleVersion mv) throws ServletException {
      Connection conn = null;
      try {
         conn = DatabaseConnectionFactory.getConnection();
         ModuleVersionDaoPersist logic = new ModuleVersionDaoPersist(conn);
         logic.updateTitle(mv);
      } catch (Exception e) {
         throw new ServletException(e);
      } finally {
         SqlUtil.close(conn);
      }
   }

   public static void updateVersionInfo(ModuleVersion mv) throws ServletException {
      Connection conn = null;
      try {
         conn = DatabaseConnectionFactory.getConnection();
         ModuleVersionDaoPersist logic = new ModuleVersionDaoPersist(conn);
         logic.updateVersionInfo(mv);
      } catch (Exception e) {
         throw new ServletException(e);
      } finally {
         SqlUtil.close(conn);
      }
   }

}
