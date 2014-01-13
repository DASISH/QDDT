package no.nsd.qddt.logic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import no.nsd.qddt.model.Module;

public class ModuleLogic {

   private final Connection conn;
   
   public ModuleLogic(Connection conn) {
      this.conn = conn;
   }
   
   public void registerNewModule(Module module) throws SQLException {
      String sql = "insert into "
              + "module(module_urn, module_study, module_title, module_authors, module_authors_affiliation, module_abstract, repeat_module) "
              + "values (?, ?, ?, ?, ?, ?, ?)";

      List values = new ArrayList();
      values.add(module.getUrn());
      values.add(module.getStudy());
      values.add(module.getTitle());
      values.add(module.getAuthors());
      values.add(module.getAuthorsAffiliation());
      values.add(module.getModuleAbstract());
      values.add(module.getRepeat());

      SqlCommand.executeSqlUpdateWithValuesOnConnection(sql, values, conn);
   }
   

   public List<Module> getModules() throws SQLException {
      String sql = "select * from module";
      SortedMap[] rows = SqlCommand.executeSqlQueryOnConnection(sql, conn);
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
   
   private Module getModule(Map map) throws SQLException {
      Module module = new Module();
      module.setId((Integer) map.get("module_id"));
      module.setUrn(SqlUtil.getString("module_urn", map));
      module.setStudy(SqlUtil.getString("module_study", map));
      module.setTitle(SqlUtil.getString("module_title", map));
      module.setAuthors(SqlUtil.getString("module_authors", map));
      module.setAuthorsAffiliation(SqlUtil.getString("module_authors_affiliation", map));
      module.setModuleAbstract(SqlUtil.getString("module_abstract", map));
      module.setRepeat((Boolean) map.get("repeat_module"));
      return module;
   }   
   
   
   
}
