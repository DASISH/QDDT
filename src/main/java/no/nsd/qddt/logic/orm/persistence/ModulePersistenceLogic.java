package no.nsd.qddt.logic.orm.persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import no.nsd.qddt.logic.SqlCommand;
import no.nsd.qddt.model.Module;

public class ModulePersistenceLogic {

   private final Connection conn;
   
   public ModulePersistenceLogic(Connection conn) {
      this.conn = conn;
   }
   
   public void registerNewModule(Module module) throws SQLException {
      String sql = "insert into "
              + "module(urn_agency, urn_id, urn_version, module_study, module_title, module_authors, module_authors_affiliation, module_abstract, repeat_module) "
              + "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

      List values = new ArrayList();
      values.add(module.getUrn().getAgency());
      values.add(module.getUrn().getId());
      values.add(module.getUrn().getVersion());
      values.add(module.getStudy());
      values.add(module.getTitle());
      values.add(module.getAuthors());
      values.add(module.getAuthorsAffiliation());
      values.add(module.getModuleAbstract());
      values.add(module.getRepeat());

      SqlCommand.executeSqlUpdateWithValuesOnConnection(sql, values, conn);
   }

   public void updateModule(Module module) throws SQLException {
      String sql = "update module set "
              + "module_study = ?, "
              + "module_title = ?, "
              + "module_authors = ?, "
              + "module_authors_affiliation = ?, "
              + "module_abstract = ?, "
              + "repeat_module = ? "
              + "where module_id = ?";

      List values = new ArrayList();
      values.add(module.getStudy());
      values.add(module.getTitle());
      values.add(module.getAuthors());
      values.add(module.getAuthorsAffiliation());
      values.add(module.getModuleAbstract());
      values.add(module.getRepeat());
      values.add(module.getId());

      SqlCommand.executeSqlUpdateWithValuesOnConnection(sql, values, conn);
   }
   
   
}
