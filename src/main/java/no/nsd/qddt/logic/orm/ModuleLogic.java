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
import no.nsd.qddt.model.Urn;

public class ModuleLogic {

   private final Connection conn;
   
   public ModuleLogic(Connection conn) {
      this.conn = conn;
   }
   
   public List<Module> getModules() throws SQLException {
      String sql = "select * from module " 
              + "where module_id in (select max(module_id) from module group by urn_id) " 
              + "order by module_study, module_title";
      SortedMap[] rows = SqlCommand.executeSqlQueryOnConnection(sql, conn);
      return this.getModuleList(rows);
   }

   public Module getModule(Integer id) throws SQLException {
      String sql = "select * from module where module_id = ?";
      
      List<Integer> values = new ArrayList<Integer>();
      values.add(id);
      
      SortedMap[] rows = SqlCommand.executeSqlQueryWithValuesOnConnection(sql, values, conn);
      return this.getModuleFromFirstRow(rows);
   }
   
   public List<Module> getModules(Urn urn) throws SQLException {
      String sql = "select * from module where urn_agency = ? and urn_id = ? order by module_id";
      
      List<String> values = new ArrayList<String>();
      values.add(urn.getAgency());
      values.add(urn.getId());
      
      SortedMap[] rows = SqlCommand.executeSqlQueryWithValuesOnConnection(sql, values, conn);
      return this.getModuleList(rows);
   }
   
   
   private List<Module> getModuleList(SortedMap[] rows) throws SQLException {
      if (rows == null || rows.length == 0) {
         return null;
      }
      List<Module> modules = new ArrayList<Module>();
      for (SortedMap row : rows) {
         modules.add(this.getModule(row));
      }
      return modules;
   }

   private Module getModuleFromFirstRow(SortedMap[] rows) throws SQLException {
      if (rows == null || rows.length == 0) {
         return null;
      }
      return this.getModule(rows[0]);
   }
   
   private Module getModule(Map map) throws SQLException {
      Module module = new Module();
      module.setId((Integer) map.get("module_id"));
      module.setStatus((Integer) map.get("module_status"));
      module.setUrn(this.getUrn(map));
      module.setStudy(SqlUtil.getString("module_study", map));
      module.setTitle(SqlUtil.getString("module_title", map));
      module.setAuthors(SqlUtil.getString("module_authors", map));
      module.setAuthorsAffiliation(SqlUtil.getString("module_authors_affiliation", map));
      module.setModuleAbstract(SqlUtil.getString("module_abstract", map));
      module.setRepeat((Boolean) map.get("repeat_module"));
      module.setConceptSchemeId((Integer) map.get("concept_scheme_id"));
      return module;
   }   
   
   private Urn getUrn(Map map) throws SQLException {
      Urn urn = new Urn();
      urn.setAgency(SqlUtil.getString("urn_agency", map));
      urn.setId(SqlUtil.getString("urn_id", map));
      urn.setVersion(SqlUtil.getString("urn_version", map));
      return urn;
   }   
   
   
}
