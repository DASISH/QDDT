package no.nsd.qddt.logic.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import no.nsd.qddt.logic.SqlCommand;
import no.nsd.qddt.logic.orm.ModuleVersionOrm;
import no.nsd.qddt.model.Actor;
import no.nsd.qddt.model.ModuleVersion;

public class ModuleVersionDao {

   private final Connection conn;
   private final Map<Integer, Actor> actors;
   
   public ModuleVersionDao(Connection conn) throws SQLException {
      this.conn = conn;
      ActorDao logic = new ActorDao(conn);
      this.actors = logic.getActorMap();
   }

   public ModuleVersion getModuleVersion(Integer moduleVersionId) throws SQLException {
      String sql = "select * from module_version where module_version_id = ?";
      
      List<Integer> values = new ArrayList<Integer>();
      values.add(moduleVersionId);
      
      SortedMap[] rows = SqlCommand.executeSqlQueryWithValuesOnConnection(sql, values, conn);
      return ModuleVersionOrm.getModuleFromFirstRow(rows, actors);
   }
   
   public List<ModuleVersion> getModuleVersions(Integer moduleId) throws SQLException {
      String sql = "select * from module_version where module_id = ? order by module_version_id";
      
      List<Integer> values = new ArrayList<Integer>();
      values.add(moduleId);
      
      SortedMap[] rows = SqlCommand.executeSqlQueryWithValuesOnConnection(sql, values, conn);
      return ModuleVersionOrm.getModuleVersionList(rows, actors);
   }
   
   
}
