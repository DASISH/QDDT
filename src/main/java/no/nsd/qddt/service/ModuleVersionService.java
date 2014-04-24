package no.nsd.qddt.service;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import no.nsd.qddt.logic.dao.DaoManager;
import no.nsd.qddt.model.Concept;
import no.nsd.qddt.model.ConceptScheme;
import no.nsd.qddt.model.ModuleVersion;

public class ModuleVersionService {

   private final DaoManager daoManager;
   
   public ModuleVersionService(DaoManager daoManager) {
      this.daoManager = daoManager;
   }

   public List<ModuleVersion> getModuleVersions(Integer moduleId) throws SQLException {
      return daoManager.getModuleVersionDao().getModuleVersions(moduleId);
   }

   public ModuleVersion getModuleVersion(Integer moduleVersionId) throws SQLException {
      return daoManager.getModuleVersionDao().getModuleVersion(moduleVersionId);
   }

   public void registerNewModuleVersion(ModuleVersion mv) throws SQLException {
      try {
         daoManager.beginTransaction();

         Integer newModuleVersionId = daoManager.getModuleVersionDaoUpdate().registerNewModuleVersion(mv);
         
         if (mv.getConceptSchemeId() != null) {
            ConceptScheme cs = daoManager.getConceptSchemeDao().getConceptScheme(mv.getConceptSchemeId());
            
            daoManager.getConceptDao().getConceptsForScheme(cs);
            Collection<Concept> concepts = cs.getConcepts();

            cs.setModuleVersionId(newModuleVersionId);
            cs.setVersionUpdated(Boolean.FALSE);
            
            daoManager.getConceptSchemeDaoUpdate().registerNewConceptScheme(cs);
            
            for (Concept c : concepts) {
               c.setModuleVersionId(newModuleVersionId);
               c.setConceptSchemeId(cs.getId());
               this.copyConcept(c);
               List<Concept> subConcepts = c.getSubConcepts();
               for (Concept sub : subConcepts) {
                  sub.setModuleVersionId(newModuleVersionId);
                  sub.setConceptSchemeId(cs.getId());
                  sub.setParentConceptId(c.getId());
                  this.copyConcept(sub);
               }
            }
         }

         daoManager.endTransaction();
      } catch (SQLException e) {
         daoManager.abortTransaction();
         throw e;
      }
   }

   private void copyConcept(Concept c) throws SQLException {
      c.setVersionUpdated(Boolean.FALSE);
      
      Integer newConceptId = daoManager.getConceptDaoUpdate().registerNewConcept(c);
      c.setId(newConceptId);
      
      daoManager.getConceptSchemeDaoUpdate().addConceptToScheme(c);
   }

   public void updateTitle(ModuleVersion mv) throws SQLException {
      daoManager.getModuleVersionDaoUpdate().updateTitle(mv);
   }

   public void updateVersionInfo(ModuleVersion mv) throws SQLException {
      daoManager.getModuleVersionDaoUpdate().updateVersionInfo(mv);
   }

}
