package no.nsd.qddt.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import no.nsd.qddt.logic.dao.DaoManager;
import no.nsd.qddt.model.Code;
import no.nsd.qddt.model.CodeList;
import no.nsd.qddt.model.Question;

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

      if (codeList == null) {
         return null;
      }
      
      List<Code> codes = daoManager.getCodeDao().getCodesForCodeList(codeListId);
      codeList.setCodes(codes);
      
      return codeList;
   }

   public List<CodeList> getCodeListsForModule(Integer moduleId) throws SQLException {
      List<CodeList> codeLists = daoManager.getCodeListDao().getCodeListsForModule(moduleId);
      
      Map<Integer, Code> mapCodeIdToCode = daoManager.getCodeDao().getAllCodesWithCategory();
      Map<Integer, List<Code>> mapCodeListIdToCodes = daoManager.getCodeDao().getCodesForAllCodeLists();
      
      if (codeLists == null) {
         return null;
      }

      SortedMap<Integer, CodeList> mapCodeListIdToCodeList = new TreeMap<Integer, CodeList>();
      
      for (CodeList cl : codeLists) {
         mapCodeListIdToCodeList.put(cl.getId(), cl);
      }
      
      // type valid or missing.
      for (CodeList cl : codeLists) {
         List<Code> list = mapCodeListIdToCodes.get(cl.getId());
         if (list == null) {
            continue;
         }
         for (Code c : list) {
            cl.addCode(mapCodeIdToCode.get(c.getId()));
         }
      }
      
      // type combined.
      for (CodeList cl : codeLists) {
         if (!cl.isCombined()) {
            continue;
         }
         cl.setValidCodeList(mapCodeListIdToCodeList.get(cl.getValidCodeListId()));
         cl.setMissingCodeList(mapCodeListIdToCodeList.get(cl.getMissingCodeListId()));
      }
      
      return codeLists;
   }
   
   
   public void addResponseDomainToQuestions(List<Question> questions) throws SQLException {
      if (questions == null) {
         return;
      }
      
      List<CodeList> codeLists = daoManager.getCodeListDao().getAllCodeLists();
      
      Map<Integer, Code> mapCodeIdToCode = daoManager.getCodeDao().getAllCodesWithCategory();
      Map<Integer, List<Code>> mapCodeListIdToCodes = daoManager.getCodeDao().getCodesForAllCodeLists();
      
      if (codeLists == null) {
         return;
      }

      SortedMap<Integer, CodeList> mapCodeListIdToCodeList = new TreeMap<Integer, CodeList>();
      
      for (CodeList cl : codeLists) {
         mapCodeListIdToCodeList.put(cl.getId(), cl);
      }
      
      // type valid or missing.
      for (CodeList cl : codeLists) {
         List<Code> list = mapCodeListIdToCodes.get(cl.getId());
         if (list == null) {
            continue;
         }
         for (Code c : list) {
            cl.addCode(mapCodeIdToCode.get(c.getId()));
         }
      }
      
      // type combined.
      for (CodeList cl : codeLists) {
         if (!cl.isCombined()) {
            continue;
         }
         cl.setValidCodeList(mapCodeListIdToCodeList.get(cl.getValidCodeListId()));
         cl.setMissingCodeList(mapCodeListIdToCodeList.get(cl.getMissingCodeListId()));
      }
      
      
      for (Question q : questions) {
         q.setCodeList(mapCodeListIdToCodeList.get(q.getCodeListId()));
      }
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

   public void updateCodeList(CodeList codeList) throws SQLException {
      daoManager.getCodeListDaoUpdate().updateCodeList(codeList);
   }

   public void updateValidCodeListId(CodeList codeList) throws SQLException {
      try {
         daoManager.beginTransaction();
         
         daoManager.getCodeListDaoUpdate().updateValidCodeListId(codeList);
         
         daoManager.endTransaction();
      } catch (SQLException e) {
         daoManager.abortTransaction();
         throw e;
      }
   }

   public void updateMissingCodeListId(CodeList codeList) throws SQLException {
      try {
         daoManager.beginTransaction();
         
         daoManager.getCodeListDaoUpdate().updateMissingCodeListId(codeList);
         
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
      
   

}
