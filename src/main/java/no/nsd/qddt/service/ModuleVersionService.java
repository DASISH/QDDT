package no.nsd.qddt.service;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import no.nsd.qddt.logic.dao.DaoManager;
import no.nsd.qddt.model.Concept;
import no.nsd.qddt.model.ConceptScheme;
import no.nsd.qddt.model.ModuleVersion;
import no.nsd.qddt.model.Question;
import no.nsd.qddt.model.QuestionScheme;

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
         mv.setId(newModuleVersionId);

         if (mv.getConceptSchemeId() != null) {
            ConceptScheme cs = daoManager.getConceptSchemeDao().getConceptScheme(mv.getConceptSchemeId());

            daoManager.getConceptDao().getConceptsForScheme(cs);
            Collection<Concept> concepts = cs.getConcepts();

            cs.setModuleVersionId(newModuleVersionId);
            cs.setVersionUpdated(Boolean.FALSE);

            daoManager.getConceptSchemeDaoUpdate().registerNewConceptScheme(cs);
            
            daoManager.getModuleVersionDaoUpdate().updateConceptScheme(cs);

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

         this.copyQuestionScheme(mv);

         
         daoManager.endTransaction();
      } catch (SQLException e) {
         daoManager.abortTransaction();
         throw e;
      }
   }

   private void copyQuestionScheme(ModuleVersion mv) throws SQLException {
      if (mv.getQuestionSchemeId() == null) {
         return;
      }
      
      QuestionScheme qs = daoManager.getQuestionSchemeDao().getQuestionScheme(mv.getQuestionSchemeId());
      List<Question> questions = daoManager.getQuestionDao().getQuestionsForScheme(mv.getQuestionSchemeId());
      
      qs.setModuleVersionId(mv.getId());
      qs.setVersionUpdated(Boolean.FALSE);
      
      daoManager.getQuestionSchemeDaoUpdate().registerNewQuestionScheme(qs);
      
      daoManager.getModuleVersionDaoUpdate().updateQuestionScheme(qs);
      
      if (questions != null) {
         for (Question q : questions) {
            q.setModuleVersionId(mv.getId());
            q.setQuestionSchemeId(qs.getId());
            this.copyQuestion(q);
         }
      }

   }

   private void copyQuestion(Question q) throws SQLException {
      q.setVersionUpdated(Boolean.FALSE);
      
      Integer newQuestionId = daoManager.getQuestionDaoUpdate().registerNewQuestion(q);
      q.setId(newQuestionId);
      
      daoManager.getQuestionSchemeDaoUpdate().addQuestionToScheme(q);
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
