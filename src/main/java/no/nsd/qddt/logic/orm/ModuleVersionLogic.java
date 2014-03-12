package no.nsd.qddt.logic.orm;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import no.nsd.qddt.logic.SqlCommand;
import no.nsd.qddt.logic.SqlUtil;
import no.nsd.qddt.model.Module;
import no.nsd.qddt.model.ModuleVersion;

public class ModuleVersionLogic {

   private final Connection conn;
   
   public ModuleVersionLogic(Connection conn) {
      this.conn = conn;
   }

   public ModuleVersion getModuleVersion(Integer moduleVersionId) throws SQLException {
      String sql = "select * from module_version where module_version_id = ?";
      
      List<Integer> values = new ArrayList<Integer>();
      values.add(moduleVersionId);
      
      SortedMap[] rows = SqlCommand.executeSqlQueryWithValuesOnConnection(sql, values, conn);
      return this.getModuleFromFirstRow(rows);
   }
   
   public List<ModuleVersion> getModuleVersions(Integer moduleId) throws SQLException {
      String sql = "select * from module_version where module_id = ? order by module_version_id";
      
      List<Integer> values = new ArrayList<Integer>();
      values.add(moduleId);
      
      SortedMap[] rows = SqlCommand.executeSqlQueryWithValuesOnConnection(sql, values, conn);
      return this.getModuleVersionList(rows);
   }
   
   
   private List<ModuleVersion> getModuleVersionList(SortedMap[] rows) throws SQLException {
      if (rows == null || rows.length == 0) {
         return null;
      }
      List<ModuleVersion> list = new ArrayList<ModuleVersion>();
      for (SortedMap row : rows) {
         list.add(this.getModuleVersion(row));
      }
      return list;
   }

   private ModuleVersion getModuleFromFirstRow(SortedMap[] rows) throws SQLException {
      if (rows == null || rows.length == 0) {
         return null;
      }
      return this.getModuleVersion(rows[0]);
   }
   
   private ModuleVersion getModuleVersion(Map map) throws SQLException {
      ModuleVersion mv = new ModuleVersion();
      Module module = new Module();
      mv.setModule(module);
      
      mv.setId((Integer) map.get("module_version_id"));
      module.setId((Integer) map.get("module_id"));
      mv.setStatus((Integer) map.get("module_status"));
      mv.setUrnVersion(SqlUtil.getString("urn_version", map));
      mv.setVersionNumber(SqlUtil.getString("version_number", map));
      mv.setVersionDescription(SqlUtil.getString("version_description", map));
      mv.setTitle(SqlUtil.getString("module_title", map));
      mv.setAuthors(SqlUtil.getString("module_authors", map));
      mv.setAuthorsAffiliation(SqlUtil.getString("module_authors_affiliation", map));
      mv.setModuleAbstract(SqlUtil.getString("module_abstract", map));
      mv.setConceptSchemeId((Integer) map.get("concept_scheme_id"));
      return mv;
   }   
   
   
   
}
