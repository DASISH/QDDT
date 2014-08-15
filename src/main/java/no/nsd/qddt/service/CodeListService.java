package no.nsd.qddt.service;

import java.sql.SQLException;
import java.util.List;
import no.nsd.qddt.logic.dao.DaoManager;
import no.nsd.qddt.model.Code;
import no.nsd.qddt.model.CodeList;

public class CodeListService {

   private final DaoManager daoManager;
   
   public CodeListService(DaoManager daoManager) {
      this.daoManager = daoManager;
   }

   public CodeList getCodeList(Integer codeListId) throws SQLException {
      return daoManager.getCodeListDao().getCodeList(codeListId);
   }

   public CodeList getCodeListWithCodes(Integer codeListId) throws SQLException {
      CodeList codeList = daoManager.getCodeListDao().getCodeList(codeListId);

      List<Code> codes = daoManager.getCodeDao().getCodesForCodeList(codeListId);
      codeList.setCodes(codes);
      
      return codeList;
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

   public void addCodeToCodeList(Integer codeId, Integer codeListId) throws SQLException {
      Integer maxSortOrder = daoManager.getCodeListDao().getMaxSortOrderForCodeList(codeListId);
      daoManager.getCodeListDaoUpdate().addCodeToCodeList(codeId, codeListId, maxSortOrder + 1);
   }

   public void removeCodeFromCodeListAndUpdateSortOrder(Integer codeId, Integer codeListId, Integer sortOrder) throws SQLException {
      try {
         daoManager.beginTransaction();

         daoManager.getCodeListDaoUpdate().removeCodeFromCodeList(codeId, codeListId);
         
         daoManager.getCodeListDaoUpdate().moveUpCodesBelowSortOrderInCodeList(sortOrder, codeListId);

         daoManager.endTransaction();
      } catch (SQLException e) {
         daoManager.abortTransaction();
         throw e;
      }
   }

   public void moveCodeFromOldPositionToNewInCodeList(Integer codeId, Integer oldSortOrder, Integer newSortOrder, Integer codeListId) throws SQLException {
      try {
         daoManager.beginTransaction();

         // moves the code above/under down/up.
         daoManager.getCodeListDaoUpdate().changeSortOrderForCode(newSortOrder, oldSortOrder, codeListId);
         
         // moves the code one position up/down.
         daoManager.getCodeListDaoUpdate().setSortOrderForCodeInCodeList(newSortOrder, codeId, codeListId);

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
