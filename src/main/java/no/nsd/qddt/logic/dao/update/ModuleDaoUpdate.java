package no.nsd.qddt.logic.dao.update;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import no.nsd.qddt.logic.SqlCommand;
import no.nsd.qddt.model.Module;

public class ModuleDaoUpdate {

   private final Connection conn;
   
   public ModuleDaoUpdate(Connection conn) {
      this.conn = conn;
   }
   
   public Integer registerNewModule(Module module) throws SQLException {
      String sql = "insert into "
              + "module(agency_id, study_id, urn_id, name, repeat_module) "
              + "values (?, ?, ?, ?, ?)";

      List values = new ArrayList();
      values.add(module.getAgency().getId());
      values.add(module.getStudy().getId());
      values.add(module.getUrnId());
      values.add(module.getName());
      values.add(module.getRepeat());

      SqlCommand sqlCommand = new SqlCommand(conn);
      sqlCommand.setSqlString(sql);
      sqlCommand.setValues(values);
      Integer moduleId = sqlCommand.executeAndReturnGeneratedKey();
      return moduleId;
   }

   public void updateModule(Module module) throws SQLException {
      String sql = "update module set "
              + "name = ?, "
              + "repeat_module = ? "
              + "where module_id = ?";

      List values = new ArrayList();
      values.add(module.getName());
      values.add(module.getRepeat());
      values.add(module.getId());

      SqlCommand.executeSqlUpdateWithValuesOnConnection(sql, values, conn);
   }
   
   
}
