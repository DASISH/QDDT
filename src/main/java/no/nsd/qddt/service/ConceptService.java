package no.nsd.qddt.service;

import java.sql.SQLException;
import java.util.List;
import no.nsd.qddt.logic.dao.DaoManager;
import no.nsd.qddt.model.Concept;

public class ConceptService {

   private final DaoManager daoManager;
   
   public ConceptService(DaoManager daoManager) {
      this.daoManager = daoManager;
   }

   public Concept getConcept(Integer conceptId) throws SQLException {
      return daoManager.getConceptDao().getConcept(conceptId);
   }

   public void registerNewConcept(Concept concept) throws SQLException {
      try {
         daoManager.beginTransaction();
         
         Integer conceptId = daoManager.getConceptDaoPersist().registerNewConcept(concept);
         concept.setId(conceptId);
         
         daoManager.getConceptSchemeDaoPersist().addConceptToScheme(concept);
         daoManager.getConceptSchemeDaoPersist().setConceptSchemeUpdated(concept.getConceptSchemeId());
         
         daoManager.endTransaction();
      } catch (SQLException e) {
         daoManager.abortTransaction();
         throw e;
      }
   }

   public void updateConcept(Concept concept) throws SQLException {
      try {
         daoManager.beginTransaction();

         daoManager.getConceptDaoPersist().updateConcept(concept);
         daoManager.getConceptSchemeDaoPersist().setConceptSchemeUpdated(concept.getConceptSchemeId());
         
         daoManager.endTransaction();
      } catch (SQLException e) {
         daoManager.abortTransaction();
         throw e;
      }
   }

   public void deleteConcept(Concept concept) throws SQLException {
      try {
         daoManager.beginTransaction();
         
         List<Concept> subConcepts = concept.getSubConcepts();
         for (Concept sub : subConcepts) {
            sub.setConceptSchemeId(concept.getConceptSchemeId());
            deleteConceptAndRemoveFromScheme(sub);
         }

         deleteConceptAndRemoveFromScheme(concept);
         daoManager.getConceptSchemeDaoPersist().setConceptSchemeUpdated(concept.getConceptSchemeId());

         daoManager.endTransaction();
      } catch (SQLException e) {
         daoManager.abortTransaction();
         throw e;
      }
   }

   private void deleteConceptAndRemoveFromScheme(Concept concept) throws SQLException {
      daoManager.getConceptDaoPersist().deleteConceptFromScheme(concept);
      daoManager.getConceptDaoPersist().deleteConcept(concept);
   }

}
