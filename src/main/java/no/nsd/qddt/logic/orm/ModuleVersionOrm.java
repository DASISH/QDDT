package no.nsd.qddt.logic.orm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import no.nsd.qddt.logic.SqlUtil;
import no.nsd.qddt.model.Actor;
import no.nsd.qddt.model.Module;
import no.nsd.qddt.model.ModuleVersion;

public final class ModuleVersionOrm {

   private ModuleVersionOrm() {
   }
   
   public static List<ModuleVersion> getModuleVersionList(SortedMap[] rows, Map<Integer, Actor> actors) throws SQLException {
      if (rows == null || rows.length == 0) {
         return null;
      }
      List<ModuleVersion> list = new ArrayList<ModuleVersion>();
      for (SortedMap row : rows) {
         list.add(getModuleVersion(row, actors));
      }
      return list;
   }

   public static ModuleVersion getModuleFromFirstRow(SortedMap[] rows, Map<Integer, Actor> actors) throws SQLException {
      if (rows == null || rows.length == 0) {
         return null;
      }
      return getModuleVersion(rows[0], actors);
   }
   
   public static ModuleVersion getModuleVersion(Map map, Map<Integer, Actor> actors) throws SQLException {
      ModuleVersion mv = new ModuleVersion();
      Module module = new Module();
      mv.setModule(module);
      
      mv.setId((Integer) map.get("module_version_id"));
      module.setId((Integer) map.get("module_id"));
      
      Integer actorId = (Integer) map.get("actor_id");
      mv.setActor(actors.get(actorId));
      
      mv.setStatus((Integer) map.get("module_status"));
      mv.setVersionPublishCode((Integer) map.get("version_publish_code"));
      mv.setVersionChangeCode((Integer) map.get("version_change_code"));
      mv.setUrnVersion(SqlUtil.getString("urn_version", map));
      mv.setVersionNumber(SqlUtil.getString("version_number", map));
      mv.setVersionDescription(SqlUtil.getString("version_description", map));
      mv.setTitle(SqlUtil.getString("module_title", map));
      mv.setAuthors(SqlUtil.getString("module_authors", map));
      mv.setAuthorsAffiliation(SqlUtil.getString("module_authors_affiliation", map));
      mv.setModuleAbstract(SqlUtil.getString("module_abstract", map));
      mv.setConceptSchemeId((Integer) map.get("concept_scheme_id"));
      mv.setQuestionSchemeId((Integer) map.get("question_scheme_id"));
      mv.setCategorySchemeId((Integer) map.get("category_scheme_id"));
      return mv;
   }
   
}
