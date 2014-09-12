package no.nsd.qddt.logic.orm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import no.nsd.qddt.logic.SqlUtil;
import no.nsd.qddt.model.Document;

public final class DocumentOrm {

   private DocumentOrm() {
   }
   
   public static List<Document> getDocumentList(SortedMap[] rows) throws SQLException {
      if (rows == null || rows.length == 0) {
         return null;
      }
      List<Document> list = new ArrayList<Document>();
      for (SortedMap row : rows) {
         list.add(getDocument(row));
      }
      return list;
   }

   public static Document getDocument(Map map) throws SQLException {
      Document d = new Document();
      
      d.setId((Integer) map.get("document_id"));
      d.setName(SqlUtil.getString("name", map));
      d.setModuleVersionId((Integer) map.get("module_version_id"));
      
      return d;
   }
   
}
