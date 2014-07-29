package no.nsd.qddt.service;

import java.sql.SQLException;
import java.util.List;
import no.nsd.qddt.logic.dao.DaoManager;
import no.nsd.qddt.model.CodeList;

public class CodeListService {

   private final DaoManager daoManager;
   
   public CodeListService(DaoManager daoManager) {
      this.daoManager = daoManager;
   }

   public CodeList getCodeList(Integer codeListId) throws SQLException {
      return daoManager.getCodeListDao().getCodeList(codeListId);
   }

   public List<CodeList> getCodeListsForModuleVersion(Integer moduleVersionId) throws SQLException {
      return daoManager.getCodeListDao().getCodeListsForModuleVersion(moduleVersionId);
   }
   
   
   public void registerNewCodeList(CodeList codeList) throws SQLException {
      try {
         daoManager.beginTransaction();
         
         Integer codeListId = daoManager.getCodeListDaoUpdate().registerNewCodeList(codeList);
         codeList.setId(codeListId);
         
         daoManager.endTransaction();
      } catch (SQLException e) {
         daoManager.abortTransaction();
         throw e;
      }
   }

   /*
   public void updateQuestion(Question question) throws SQLException {
      try {
         daoManager.beginTransaction();

         daoManager.getQuestionDaoUpdate().updateQuestion(question);
         daoManager.getQuestionSchemeDaoUpdate().setQuestionSchemeUpdated(question.getQuestionSchemeId());
         
         daoManager.endTransaction();
      } catch (SQLException e) {
         daoManager.abortTransaction();
         throw e;
      }
   }

   public void deleteQuestion(Question question) throws SQLException {
      try {
         daoManager.beginTransaction();
         
         daoManager.getQuestionSchemeDaoUpdate().deleteQuestionFromScheme(question);
         daoManager.getQuestionDaoUpdate().deleteQuestion(question);
         daoManager.getQuestionSchemeDaoUpdate().setQuestionSchemeUpdated(question.getQuestionSchemeId());

         daoManager.endTransaction();
      } catch (SQLException e) {
         daoManager.abortTransaction();
         throw e;
      }
   }

   */

}
