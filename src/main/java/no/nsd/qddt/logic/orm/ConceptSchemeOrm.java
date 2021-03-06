package no.nsd.qddt.logic.orm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import no.nsd.qddt.logic.SqlUtil;
import no.nsd.qddt.model.ConceptScheme;
import no.nsd.qddt.model.Urn;

public final class ConceptSchemeOrm {

   private ConceptSchemeOrm() {
   }
   
   public static ConceptScheme getConceptSchemeFromFirstRow(SortedMap[] rows) throws SQLException {
      if (rows == null || rows.length == 0) {
         return null;
      }
      return getConceptScheme(rows[0]);
   }
   
   public static List<ConceptScheme> getConceptSchemeList(SortedMap[] rows) throws SQLException {
      if (rows == null || rows.length == 0) {
         return null;
      }
      List<ConceptScheme> list = new ArrayList<ConceptScheme>();
      for (SortedMap row : rows) {
         list.add(getConceptScheme(row));
      }
      return list;
   }

   public static ConceptScheme getConceptScheme(Map map) throws SQLException {
      ConceptScheme cs = new ConceptScheme();
      
      Urn urn = UrnOrm.getUrn(map);
      cs.setUrn(urn);
      
      cs.setId((Integer) map.get("concept_scheme_id"));
      cs.setModuleVersionId((Integer) map.get("module_version_id"));
      cs.setName(SqlUtil.getString("name", map));
      cs.setLabel(SqlUtil.getString("label", map));
      cs.setDescription(SqlUtil.getString("description", map));
      cs.setVersionDescription(SqlUtil.getString("version_description", map));
      cs.setVersionChangeCode((Integer) map.get("version_change_code"));
      return cs;
   }
   
}
