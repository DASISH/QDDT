package no.nsd.qddt.logic.orm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import no.nsd.qddt.logic.SqlUtil;
import no.nsd.qddt.model.Agency;
import no.nsd.qddt.model.Module;
import no.nsd.qddt.model.Study;

public final class ModuleOrm {

   private ModuleOrm() {
   }
   
   public static List<Module> getModuleList(SortedMap[] rows, Map<Integer, Agency> agencies, Map<Integer, Study> studies) throws SQLException {
      if (rows == null || rows.length == 0) {
         return null;
      }
      List<Module> modules = new ArrayList<Module>();
      for (SortedMap row : rows) {
         modules.add(getModule(row, agencies, studies));
      }
      return modules;
   }

   public static Module getModuleFromFirstRow(SortedMap[] rows, Map<Integer, Agency> agencies, Map<Integer, Study> studies) throws SQLException {
      if (rows == null || rows.length == 0) {
         return null;
      }
      return getModule(rows[0], agencies, studies);
   }
   
   public static Module getModule(Map map, Map<Integer, Agency> agencies, Map<Integer, Study> studies) throws SQLException {
      Module module = new Module();
      
      module.setId((Integer) map.get("module_id"));
      
      Integer studyId = (Integer) map.get("study_id");
      module.setStudy(studies.get(studyId));
      Integer agencyId = (Integer) map.get("agency_id");
      module.setAgency(agencies.get(agencyId));
      
      module.setUrnId(SqlUtil.getString("urn_id", map));
      module.setName(SqlUtil.getString("name", map));
      module.setRepeat((Boolean) map.get("repeat_module"));
      return module;
   }   
   
   
}
