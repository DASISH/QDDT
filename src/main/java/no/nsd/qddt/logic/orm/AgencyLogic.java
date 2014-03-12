package no.nsd.qddt.logic.orm;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import no.nsd.qddt.logic.SqlCommand;
import no.nsd.qddt.logic.SqlUtil;
import no.nsd.qddt.model.Agency;

public class AgencyLogic {

   private final Connection conn;
   
   public AgencyLogic(Connection conn) {
      this.conn = conn;
   }
   
   public List<Agency> getAgencies() throws SQLException {
      String sql = "select * from agency"; 
      SortedMap[] rows = SqlCommand.executeSqlQueryOnConnection(sql, conn);
      return this.getAgencyList(rows);
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
      return this.getAgencyFromFirstRow(rows);
   }
   
   
   
   private List<Agency> getAgencyList(SortedMap[] rows) throws SQLException {
      if (rows == null || rows.length == 0) {
         return null;
      }
      List<Agency> list = new ArrayList<Agency>();
      for (SortedMap row : rows) {
         list.add(this.getAgency(row));
      }
      return list;
   }

   private Agency getAgencyFromFirstRow(SortedMap[] rows) throws SQLException {
      if (rows == null || rows.length == 0) {
         return null;
      }
      return this.getAgency(rows[0]);
   }
   
   private Agency getAgency(Map map) throws SQLException {
      Agency agency = new Agency();
      
      agency.setId((Integer) map.get("agency_id"));
      agency.setUrnId(SqlUtil.getString("agency_urn_id", map));
      agency.setName(SqlUtil.getString("name", map));
      agency.setShortName(SqlUtil.getString("short_name", map));
      
      return agency;
   }   
   
   
   
}
