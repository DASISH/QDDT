package no.nsd.qddt.service;

import java.sql.SQLException;
import java.util.List;
import no.nsd.qddt.logic.dao.DaoManager;
import no.nsd.qddt.model.Document;
import org.apache.commons.fileupload.FileItem;

public class DocumentService {

   private final DaoManager daoManager;
   
   public DocumentService(DaoManager daoManager) {
      this.daoManager = daoManager;
   }


   public List<Document> getDocumentsForModuleVersion(Integer moduleVersionId) throws SQLException {
      return daoManager.getDocumentDao().getDocumentsForModuleVersion(moduleVersionId);
   }

   public void registerNewDocuemnt(FileItem fileItem, Integer moduleVersionId) throws SQLException {
      try {
         daoManager.beginTransaction();
         
         Integer newId = daoManager.getDocumentDaoUpdate().registerNewDocument(fileItem, moduleVersionId);
         daoManager.getDocumentDaoUpdate().addDocumentToModuleVersion(newId, moduleVersionId);
         
         daoManager.endTransaction();
      } catch (SQLException e) {
         daoManager.abortTransaction();
         throw e;
      }
   }



}
