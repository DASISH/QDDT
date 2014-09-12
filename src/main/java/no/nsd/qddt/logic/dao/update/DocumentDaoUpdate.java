package no.nsd.qddt.logic.dao.update;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import no.nsd.qddt.logic.SqlCommand;
import no.nsd.qddt.logic.SqlUtil;
import org.apache.commons.fileupload.FileItem;

public class DocumentDaoUpdate {

   private final Connection conn;
   
   public DocumentDaoUpdate(Connection conn) {
      this.conn = conn;
   }
   
   
   public Integer registerNewDocument(FileItem fileItem, Integer moduleVersionId) throws SQLException {
      PreparedStatement statement = null;
      ResultSet rs = null;
      
      try {
         String sqlString = "insert into document (module_version_id, name, data) values (?, ?, ?)";
         
         statement = conn.prepareStatement(sqlString, PreparedStatement.RETURN_GENERATED_KEYS);
         
         statement.setInt(1, moduleVersionId);
         statement.setString(2, fileItem.getName());
         statement.setBinaryStream(3, fileItem.getInputStream(), (int) fileItem.getSize());
         
         statement.executeUpdate();
         
         rs = statement.getGeneratedKeys();
         
         if (rs != null && rs.next()) {
            Number n = (Number) rs.getObject(1);
            if (n != null) {
               return n.intValue();
            } else {
               return null;
            }
         }         
         
      } catch (SQLException ex) {
         throw ex;
      } catch (IOException ex) {
         throw new SQLException(ex);
      } finally {
         SqlUtil.close(rs);
         SqlUtil.close(statement);
      }
      return null;
   }

   
   public void addDocumentToModuleVersion(Integer documentId, Integer moduleVersionId) throws SQLException {
      String sql = "insert into document_in_module_version (document_id, module_version_id) values (?, ?)";

      List values = new ArrayList();
      values.add(documentId);
      values.add(moduleVersionId);

      SqlCommand.executeSqlUpdateWithValuesOnConnection(sql, values, conn);
   }
   
   
   public void deleteDocument(Integer documentId) throws SQLException {
      String sql = "delete from document where document_id = ?";

      List values = new ArrayList();
      values.add(documentId);

      SqlCommand.executeSqlUpdateWithValuesOnConnection(sql, values, conn);
   }

   
   
}
