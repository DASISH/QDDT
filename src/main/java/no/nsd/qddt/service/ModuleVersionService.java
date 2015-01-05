package no.nsd.qddt.service;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import no.nsd.qddt.logic.UrnUtil;
import no.nsd.qddt.logic.dao.DaoManager;
import no.nsd.qddt.model.exception.AuthorisationException;
import no.nsd.qddt.model.CategoryScheme;
import no.nsd.qddt.model.Concept;
import no.nsd.qddt.model.ConceptScheme;
import no.nsd.qddt.model.ModuleVersion;
import no.nsd.qddt.model.Question;
import no.nsd.qddt.model.QuestionScheme;
import no.nsd.qddt.model.Urn;
import no.nsd.qddt.model.exception.VersionException;

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

         this.registerNewConceptSchemeIfNull(mv);
         this.registerNewQuestionSchemeIfNull(mv);
         this.registerNewCategorySchemeIfNull(mv);
         
         daoManager.endTransaction();
      } catch (SQLException e) {
         daoManager.abortTransaction();
         throw e;
      }
   }

   
   private void registerNewConceptSchemeIfNull(ModuleVersion mv) throws SQLException {
      if (mv.getConceptSchemeId() != null) {
         return;
      }
      
      Urn urn = UrnUtil.createNewUrn();
      urn.setAgency(mv.getModule().getAgency());
      ConceptScheme cs = new ConceptScheme();
      cs.setUrn(urn);
      cs.setModuleVersionId(mv.getId());
      cs.setName(mv.getModule().getName());
      cs.setLabel(mv.getModule().getName());
      cs.setDescription("Concepts for module: " + mv.getModule().getName());
      
      daoManager.getConceptSchemeDaoUpdate().registerNewConceptScheme(cs);
      daoManager.getModuleVersionDaoUpdate().updateConceptScheme(cs);
   }
   
   private void registerNewQuestionSchemeIfNull(ModuleVersion mv) throws SQLException {
      if (mv.getQuestionSchemeId() != null) {
         return;
      }
      
      Urn urn = UrnUtil.createNewUrn();
      urn.setAgency(mv.getModule().getAgency());
      QuestionScheme qs = new QuestionScheme();
      qs.setUrn(urn);
      qs.setModuleVersionId(mv.getId());
      qs.setName(mv.getModule().getName());
      qs.setLabel(mv.getModule().getName());
      qs.setDescription("Questions for module: " + mv.getModule().getName());
      
      daoManager.getQuestionSchemeDaoUpdate().registerNewQuestionScheme(qs);
      daoManager.getModuleVersionDaoUpdate().updateQuestionScheme(qs);
   }   
   
   private void registerNewCategorySchemeIfNull(ModuleVersion mv) throws SQLException {
      if (mv.getCategorySchemeId() != null) {
         return;
      }

      CategoryScheme cs = new CategoryScheme();
      Urn urn = UrnUtil.createNewUrn();
      urn.setAgency(mv.getModule().getAgency());
      cs.setUrn(urn);
      cs.setModuleVersionId(mv.getId());
      cs.setName(mv.getModule().getName());
      cs.setLabel(mv.getModule().getName());
      cs.setDescription("Default category scheme for module: " + mv.getModule().getName());
      cs.setModuleDefaultScheme(Boolean.TRUE);

      daoManager.getCategorySchemeDaoUpdate().registerNewCategoryScheme(cs);
      mv.setCategorySchemeId(cs.getId());

      daoManager.getModuleVersionDaoUpdate().updateCategoryScheme(cs);

   }


   private void xxxcopyConceptScheme(ModuleVersion mv) throws SQLException {
      if (mv.getConceptSchemeId() == null) {
         return;
      }
      
      ConceptScheme cs = daoManager.getConceptSchemeDao().getConceptScheme(mv.getConceptSchemeId());

      daoManager.getConceptDao().getConceptsForScheme(cs);
      Collection<Concept> concepts = cs.getConcepts();

      cs.setModuleVersionId(mv.getId());
      cs.setVersionUpdated(Boolean.FALSE);

      daoManager.getConceptSchemeDaoUpdate().registerNewConceptScheme(cs);

      daoManager.getModuleVersionDaoUpdate().updateConceptScheme(cs);

      this.copyConceptList(concepts, mv, cs, null);
   }

   private void copyConceptList(Collection<Concept> concepts, ModuleVersion mv, ConceptScheme cs, Integer parentConceptId) throws SQLException {
      for (Concept c : concepts) {
         c.setModuleVersionId(mv.getId());
         c.setConceptSchemeId(cs.getId());
         c.setParentConceptId(parentConceptId);
         this.copyConcept(c);
         this.copyConceptList(c.getSubConcepts(), mv, cs, c.getId());
      }
   }
   
   
   private void copyConcept(Concept c) throws SQLException {
      c.setVersionUpdated(Boolean.FALSE);

      Integer newConceptId = daoManager.getConceptDaoUpdate().registerNewConcept(c);
      c.setId(newConceptId);

      daoManager.getConceptSchemeDaoUpdate().addConceptToScheme(c);
   }
   
   
   private void xxxcopyQuestionScheme(ModuleVersion mv) throws SQLException {
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
   
   
   

   public void updateTitle(ModuleVersion mv) throws SQLException {
      daoManager.getModuleVersionDaoUpdate().updateTitle(mv);
   }

   public void updateVersionInfo(ModuleVersion newModuleVersion, ModuleVersion oldModuleVersion) throws SQLException {
      this.checkVersionStatus(newModuleVersion, oldModuleVersion);
      daoManager.getModuleVersionDaoUpdate().updateVersionInfo(newModuleVersion);
   }

   private void checkVersionStatus(ModuleVersion newModuleVersion, ModuleVersion oldModuleVersion) {
      if (oldModuleVersion.isPublished()) {
         throw new AuthorisationException("Cannot update version info on published version.");
      }
   }
   
   
   public void updatePublishInfo(ModuleVersion newModuleVersion, ModuleVersion oldModuleVersion) throws SQLException {
      
      this.checkPublishStatus(newModuleVersion, oldModuleVersion);
      this.checkVersionChange(oldModuleVersion);

      try {
         daoManager.beginTransaction();

         
         
         this.updateUrnVersion(newModuleVersion, oldModuleVersion);
         
         daoManager.getModuleVersionDaoUpdate().updatePublishInfo(newModuleVersion);
         
         
         daoManager.endTransaction();
      } catch (SQLException e) {
         daoManager.abortTransaction();
         throw e;
      }
      
   }


   private void checkPublishStatus(ModuleVersion newModuleVersion, ModuleVersion oldModuleVersion) {
      int newCode = newModuleVersion.getVersionPublishCode();
      int oldCode = (oldModuleVersion.getVersionPublishCode() != null ? oldModuleVersion.getVersionPublishCode() : 0);
      
      if (newCode <= oldCode) {
         throw new VersionException("New publish status is the same as or more restrictive than old status.");
      }
   }

   private void checkVersionChange(ModuleVersion oldModuleVersion) {
      if (oldModuleVersion.getVersionChangeCode() == null) {
         throw new VersionException("No module version change code.");
      }
   }
   
   
   public void updateUrnVersion(ModuleVersion newModuleVersion, ModuleVersion oldModuleVersion) throws SQLException {
      if (oldModuleVersion.isPublished()) {
         return;
      }
      
      int levels = oldModuleVersion.getModule().getStudy().getSurvey().getVersionLevels();
      
      if (oldModuleVersion.getUrnVersion() == null) {
         String newUrnVersion = UrnUtil.createNewVersion(levels);
         newModuleVersion.setUrnVersion(newUrnVersion);
      } else {
         String newUrnVersion = UrnUtil.computeNewVersion(oldModuleVersion.getUrnVersion(), oldModuleVersion.getVersionChangeCode(), levels);
         newModuleVersion.setUrnVersion(newUrnVersion);
      }
      
      daoManager.getModuleVersionDaoUpdate().updateUrnVersion(newModuleVersion);
   }
   
   
   
   

}
