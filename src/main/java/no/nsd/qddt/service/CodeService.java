package no.nsd.qddt.service;

import java.sql.SQLException;
import java.util.List;
import no.nsd.qddt.logic.dao.DaoManager;
import no.nsd.qddt.model.Code;
import no.nsd.qddt.model.Module;

public class CodeService {

   private final DaoManager daoManager;
   
   public CodeService(DaoManager daoManager) {
      this.daoManager = daoManager;
   }

   
   public Code getCode(Integer codeId) throws SQLException {
      return daoManager.getCodeDao().getCode(codeId);
   }

   public List<Code> getCodesForCodeList(Integer codeListId) throws SQLException {
      return daoManager.getCodeDao().getCodesForCodeList(codeListId);
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



}
