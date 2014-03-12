package no.nsd.qddt.logic.orm;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import no.nsd.qddt.logic.SqlCommand;
import no.nsd.qddt.logic.SqlUtil;
import no.nsd.qddt.model.Agency;
import no.nsd.qddt.model.Module;
import no.nsd.qddt.model.Study;
import no.nsd.qddt.model.Urn;

public class ModuleLogic {

   private final Connection conn;
   private final Map<Integer, Agency> agencies;
   private final Map<Integer, Study> studies;
   
   public ModuleLogic(Connection conn) throws SQLException {
      this.conn = conn;
      AgencyLogic al = new AgencyLogic(conn);
      this.agencies = al.getAgencyMap();
      StudyLogic sl = new StudyLogic(conn);
      this.studies = sl.getStudyMap();
   }
   
   public List<Module> getModules() throws SQLException {
      String sql = "select * from module"; 
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
      
      Integer studyId = (Integer) map.get("study_id");
      module.setStudy(this.studies.get(studyId));
      Integer agencyId = (Integer) map.get("agency_id");
      module.setAgency(this.agencies.get(agencyId));
      
      module.setUrnId(SqlUtil.getString("urn_id", map));
      module.setName(SqlUtil.getString("name", map));
      module.setRepeat((Boolean) map.get("repeat_module"));
      return module;
   }   
   
   private Urn getUrn(Map map) throws SQLException {
      Urn urn = new Urn();
      Agency a = new Agency();
      urn.setAgency(a);
      a.setId((Integer) map.get("agency_id"));
      urn.setId(SqlUtil.getString("urn_id", map));
      urn.setVersion(SqlUtil.getString("urn_version", map));
      return urn;
   }   
   
   
}
