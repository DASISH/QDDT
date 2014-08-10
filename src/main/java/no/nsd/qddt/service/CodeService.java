package no.nsd.qddt.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import no.nsd.qddt.logic.dao.DaoManager;
import no.nsd.qddt.model.Category;
import no.nsd.qddt.model.Code;
import no.nsd.qddt.model.Module;

public class CodeService {

   private final DaoManager daoManager;
   
   public CodeService(DaoManager daoManager) {
      this.daoManager = daoManager;
   }

   
   public Code getCode(Integer codeId) throws SQLException {
      Code code = daoManager.getCodeDao().getCode(codeId);
      
      if (code != null && code.getCategoryId() != null) {
         Category c = daoManager.getCategoryDao().getCategory(code.getCategoryId());
         code.setCategory(c);
      }
      
      return code;
   }

   public List<Code> getCodesForCodeList(Integer codeListId) throws SQLException {
      return daoManager.getCodeDao().getCodesForCodeList(codeListId);
   }

   // mapping: categoryid --> list of codes.
   public Map<Integer, List<Code>> getAllCodesCategoryMap() throws SQLException {
      
      List<Code> allCodes = daoManager.getCodeDao().getAllCodes();
      
      if (allCodes == null) {
         return null;
      }
      
      Map<Integer, List<Code>> map = new HashMap<Integer, List<Code>>();
      
      for (Code c : allCodes) {
         List<Code> codes = map.get(c.getCategoryId());
         if (codes == null) {
            codes = new ArrayList<Code>();
            map.put(c.getCategoryId(), codes);
         }
         codes.add(c);
      }
      
      return map;
   }
   
   public List<Code> getCodesForModule(Module module) throws SQLException {
      return daoManager.getCodeDao().getCodesForModule(module);
   }

   public void registerNewCode(Code code) throws SQLException {
      try {
         daoManager.beginTransaction();
         
         Integer codeId = daoManager.getCodeDaoUpdate().registerNewCode(code);
         code.setId(codeId);
         
         daoManager.endTransaction();
      } catch (SQLException e) {
         daoManager.abortTransaction();
         throw e;
      }
   }

   public void updateCode(Code code) throws SQLException {
      try {
         daoManager.beginTransaction();
         
         daoManager.getCodeDaoUpdate().updateCode(code);
         
         daoManager.endTransaction();
      } catch (SQLException e) {
         daoManager.abortTransaction();
         throw e;
      }
   }

   public void updateCodeCategory(Code code) throws SQLException {
      try {
         daoManager.beginTransaction();
         
         daoManager.getCodeDaoUpdate().updateCategoryForCode(code);
         
         daoManager.endTransaction();
      } catch (SQLException e) {
         daoManager.abortTransaction();
         throw e;
      }
   }


}
