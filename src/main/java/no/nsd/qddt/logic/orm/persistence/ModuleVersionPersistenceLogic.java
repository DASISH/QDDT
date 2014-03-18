package no.nsd.qddt.logic.orm.persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import no.nsd.qddt.logic.SqlCommand;
import no.nsd.qddt.model.ModuleVersion;

public class ModuleVersionPersistenceLogic {

   private final Connection conn;
   
   public ModuleVersionPersistenceLogic(Connection conn) {
      this.conn = conn;
   }

   
   public void registerNewVersionModule(ModuleVersion mv) throws SQLException {
      String sql = "insert into "
              + "module_version(module_id, actor_id, module_status) "
              + "values (?, ?, ?)";

      List values = new ArrayList();
      values.add(mv.getModule().getId());
      values.add(mv.getActor().getId());
      values.add(mv.getStatus());

      SqlCommand.executeSqlUpdateWithValuesOnConnection(sql, values, conn);
   }
   
   
   
   public void registerNewModule(ModuleVersion mv) throws SQLException {
      String sql = "insert into "
              + "module_version(module_id, module_status, urn_version, module_title, module_authors, module_authors_affiliation, module_abstract) "
              + "values (?, ?, ?, ?, ?, ?, ?)";

      List values = new ArrayList();
      values.add(mv.getModule().getId());
      values.add(mv.getStatus());
      values.add(mv.getUrnVersion());
      values.add(mv.getTitle());
      values.add(mv.getAuthors());
      values.add(mv.getAuthorsAffiliation());
      values.add(mv.getModuleAbstract());

      SqlCommand.executeSqlUpdateWithValuesOnConnection(sql, values, conn);
   }

   public void updateModule(ModuleVersion mv) throws SQLException {
      String sql = "update module_version set "
              + "module_id = ?, "
              + "module_study = ?, "
              + "urn_version = ?, "
              + "module_title = ?, "
              + "module_authors = ?, "
              + "module_authors_affiliation = ?, "
              + "module_abstract = ?, "
              + "where module_id = ?";

      List values = new ArrayList();
      values.add(mv.getModule().getId());
      values.add(mv.getStatus());
      values.add(mv.getUrnVersion());
      values.add(mv.getTitle());
      values.add(mv.getAuthors());
      values.add(mv.getAuthorsAffiliation());
      values.add(mv.getModuleAbstract());
      values.add(mv.getId());

      SqlCommand.executeSqlUpdateWithValuesOnConnection(sql, values, conn);
   }
   
   
}
