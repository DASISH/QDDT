package no.nsd.qddt.logic.dao.update;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import no.nsd.qddt.logic.SqlCommand;
import org.apache.commons.fileupload.FileItem;

public class FileDaoUpdate {

   private final Connection conn;
   
   public FileDaoUpdate(Connection conn) {
      this.conn = conn;
   }
   
   
   public Integer registerNewFile(FileItem fileItem, Integer moduleVersionId) throws SQLException {
      PreparedStatement statement = null;
      try {
         statement = conn.prepareStatement("insert into document (module_version_id, name, data) values (?, ?, ?)");
         statement.setInt(1, moduleVersionId);
         statement.setString(2, fileItem.getName());
         statement.setBinaryStream(3, fileItem.getInputStream(), (int) fileItem.getSize());
         statement.executeUpdate();
      } catch (SQLException ex) {
         throw ex;
      } catch (IOException ex) {
         throw new SQLException(ex);
      } finally {
         try {
            statement.close();
         } catch (Exception ignored) {
         }
      }
      return 1;
   }

   public void deleteFile(Integer documentId) throws SQLException {
      String sql = "delete from document where document_id = ?";

      List values = new ArrayList();
      values.add(documentId);

      SqlCommand.executeSqlUpdateWithValuesOnConnection(sql, values, conn);
   }

   
   
}
