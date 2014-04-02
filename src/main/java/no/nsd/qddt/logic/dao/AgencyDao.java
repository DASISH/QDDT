package no.nsd.qddt.logic.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import no.nsd.qddt.logic.SqlCommand;
import no.nsd.qddt.logic.orm.AgencyOrm;
import no.nsd.qddt.model.Agency;

public class AgencyDao {

   private final Connection conn;
   
   public AgencyDao(Connection conn) {
      this.conn = conn;
   }
   
   public List<Agency> getAgencies() throws SQLException {
      String sql = "select * from agency order by name"; 
      SortedMap[] rows = SqlCommand.executeSqlQueryOnConnection(sql, conn);
      return AgencyOrm.getAgencyList(rows);
   }

   public Map<Integer, Agency> getAgencyMap() throws SQLException {
      List<Agency> list = getAgencies();
      Map<Integer, Agency> map = new HashMap<Integer, Agency>();
      if (list == null) {
         return null;
      }
      for (Agency a : list) {
         map.put(a.getId(), a);
      }
      return map;
   }
   
   public Agency getAgency(Integer id) throws SQLException {
      String sql = "select * from agency where agency_id = ?";
      
      List<Integer> values = new ArrayList<Integer>();
      values.add(id);
      
      SortedMap[] rows = SqlCommand.executeSqlQueryWithValuesOnConnection(sql, values, conn);
      return AgencyOrm.getAgencyFromFirstRow(rows);
   }
   
   
}
