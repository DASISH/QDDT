package no.nsd.qddt.logic.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import no.nsd.qddt.logic.SqlCommand;
import no.nsd.qddt.logic.orm.ModuleOrm;
import no.nsd.qddt.model.Agency;
import no.nsd.qddt.model.Module;
import no.nsd.qddt.model.Study;

public class ModuleDao {

   private final Connection conn;
   private final Map<Integer, Agency> agencies;
   private final Map<Integer, Study> studies;
   
   public ModuleDao(Connection conn) throws SQLException {
      this.conn = conn;
      AgencyDao al = new AgencyDao(conn);
      this.agencies = al.getAgencyMap();
      StudyDao sl = new StudyDao(conn);
      this.studies = sl.getStudyMap();
   }
   
   public List<Module> getModules() throws SQLException {
      String sql = "select * from module"; 
      SortedMap[] rows = SqlCommand.executeSqlQueryOnConnection(sql, conn);
      return ModuleOrm.getModuleList(rows, agencies, studies);
   }

   public Module getModule(Integer id) throws SQLException {
      String sql = "select * from module where module_id = ?";
      
      List<Integer> values = new ArrayList<Integer>();
      values.add(id);
      
      SortedMap[] rows = SqlCommand.executeSqlQueryWithValuesOnConnection(sql, values, conn);
      return ModuleOrm.getModuleFromFirstRow(rows, agencies, studies);
   }
   
   
}
