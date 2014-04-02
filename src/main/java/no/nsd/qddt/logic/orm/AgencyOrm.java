package no.nsd.qddt.logic.orm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import no.nsd.qddt.logic.SqlUtil;
import no.nsd.qddt.model.Agency;

public final class AgencyOrm {

   private AgencyOrm() {
   }
   
   public static List<Agency> getAgencyList(SortedMap[] rows) throws SQLException {
      if (rows == null || rows.length == 0) {
         return null;
      }
      List<Agency> list = new ArrayList<Agency>();
      for (SortedMap row : rows) {
         list.add(getAgency(row));
      }
      return list;
   }

   public static Agency getAgencyFromFirstRow(SortedMap[] rows) throws SQLException {
      if (rows == null || rows.length == 0) {
         return null;
      }
      return getAgency(rows[0]);
   }
   
   public static Agency getAgency(Map map) throws SQLException {
      Agency agency = new Agency();
      
      agency.setId((Integer) map.get("agency_id"));
      agency.setUrnId(SqlUtil.getString("agency_urn_id", map));
      agency.setName(SqlUtil.getString("name", map));
      agency.setShortName(SqlUtil.getString("short_name", map));
      
      return agency;
   }   
   

   
}
