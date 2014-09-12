package no.nsd.qddt.logic.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import javax.servlet.http.HttpServletResponse;
import no.nsd.qddt.logic.FileStreamer;
import no.nsd.qddt.logic.SqlCommand;
import no.nsd.qddt.logic.SqlUtil;
import no.nsd.qddt.logic.orm.DocumentOrm;
import no.nsd.qddt.model.Document;

public class DocumentDao {

   private final Connection conn;
   
   public DocumentDao(Connection conn) throws SQLException {
      this.conn = conn;
   }

   
   public void readFile(Integer documentId, HttpServletResponse response) throws SQLException, IOException {
      ResultSet rs = null;
      PreparedStatement pstmt = null;
      try {

         pstmt = this.conn.prepareStatement("select name, len(data) as fileLength, data from document where document_id = ?");
         
         pstmt.setInt(1, documentId);
         
         rs = pstmt.executeQuery();
         
         rs.next();
         
         String filename = rs.getString("name");
         
         Object o = rs.getObject("fileLength");
         int fileLength = ((Number) o).intValue();
         
         InputStream ins = rs.getBinaryStream("data");
         
         FileStreamer fs = new FileStreamer();
         fs.setFilename(filename);
         fs.setFileLength(fileLength);
         fs.setResponse(response);
         fs.setIns(ins);
         
         fs.streamFile();
         
      } finally {
         SqlUtil.close(rs);
         SqlUtil.close(pstmt);
      }
   }
   
   
   
   
   public List<Document> getDocumentsForModuleVersion(Integer moduleVersionId) throws SQLException {
      String sql = "select d.document_id, d.module_version_id, d.name "
              + "from document as d inner join document_in_module_version as dimv "
              + "on d.document_id = dimv.document_id "
              + "where dimv.module_version_id = ? "
              + "order by d.name";
      
      List values = new ArrayList();
      
      values.add(moduleVersionId);
      
      SortedMap[] rows = SqlCommand.executeSqlQueryWithValuesOnConnection(sql, values, conn);
      List<Document> list = DocumentOrm.getDocumentList(rows);
      return list;
   }
   

   
   
}
