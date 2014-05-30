package no.nsd.qddt.service;

import java.sql.SQLException;
import no.nsd.qddt.logic.dao.DaoManager;
import no.nsd.qddt.model.ConceptScheme;

public class ConceptSchemeService {

   private final DaoManager daoManager;

   public ConceptSchemeService(DaoManager daoManager) {
      this.daoManager = daoManager;
   }

   public ConceptScheme getConceptScheme(Integer conceptSchemeId) throws SQLException {
      ConceptScheme conceptScheme = daoManager.getConceptSchemeDao().getConceptScheme(conceptSchemeId);
      
      if (conceptScheme != null) {
         daoManager.getConceptDao().getConceptsForScheme(conceptScheme);
      }
      
      return conceptScheme;
   }

   public void registerNewConceptScheme(ConceptScheme conceptScheme) throws SQLException {
      try {
         daoManager.beginTransaction();
         daoManager.getConceptSchemeDaoUpdate().registerNewConceptScheme(conceptScheme);
         daoManager.getModuleVersionDaoUpdate().updateConceptScheme(conceptScheme);
         daoManager.endTransaction();
      } catch (SQLException e) {
         daoManager.abortTransaction();
         throw e;
      }
   }

   public void updateConceptScheme(ConceptScheme conceptScheme) throws SQLException {
      daoManager.getConceptSchemeDaoUpdate().updateConceptScheme(conceptScheme);
   }

}
