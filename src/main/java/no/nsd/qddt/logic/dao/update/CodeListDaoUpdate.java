package no.nsd.qddt.logic.dao.update;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import no.nsd.qddt.logic.SqlCommand;
import no.nsd.qddt.model.CodeList;

public class CodeListDaoUpdate {

   private final Connection conn;
   
   public CodeListDaoUpdate(Connection conn) {
      this.conn = conn;
   }
   
   public Integer registerNewCodeList(CodeList codeList) throws SQLException {
      String sql = "insert into "
              + "code_list(agency_id, "
              + "module_version_id, "
              + "urn_id, "
              + "urn_version, "
              + "name, "
              + "label, "
              + "code_list_type, "
              + "description, "
              + "version_description) "
              + "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

      List values = new ArrayList();
      values.add(codeList.getUrn().getAgency().getId());
      values.add(codeList.getModuleVersionId());
      values.add(codeList.getUrn().getId());
      values.add(codeList.getUrn().getVersion());

      values.add(codeList.getName());
      values.add(codeList.getLabel());
      values.add(codeList.getCodeListType());
      values.add(codeList.getDescription());
      values.add(codeList.getVersionDescription());

      SqlCommand sqlCommand = new SqlCommand(conn);
      sqlCommand.setSqlString(sql);
      sqlCommand.setValues(values);
      Integer generatedId = sqlCommand.executeAndReturnGeneratedKey();
      return generatedId;
   }

   public void updateCodeList(CodeList codeList) throws SQLException {
      String sql = "update code_list set "
              + "name = ?, "
              + "label = ?, "
              + "description = ?, "
              + "version_description = ? "
              + "where code_list_id = ?";

      List values = new ArrayList();
      values.add(codeList.getName());
      values.add(codeList.getLabel());
      values.add(codeList.getDescription());
      values.add(codeList.getVersionDescription());

      values.add(codeList.getId());

      SqlCommand.executeSqlUpdateWithValuesOnConnection(sql, values, conn);
   }

   public void updateValidCodeListId(CodeList codeList) throws SQLException {
      String sql = "update code_list set "
              + "valid_code_list_id = ? "
              + "where code_list_id = ?";

      List values = new ArrayList();
      values.add(codeList.getValidCodeListId());
      values.add(codeList.getId());

      SqlCommand.executeSqlUpdateWithValuesOnConnection(sql, values, conn);
   }

   public void updateMissingCodeListId(CodeList codeList) throws SQLException {
      String sql = "update code_list set "
              + "missing_code_list_id = ? "
              + "where code_list_id = ?";

      List values = new ArrayList();
      values.add(codeList.getMissingCodeListId());
      values.add(codeList.getId());

      SqlCommand.executeSqlUpdateWithValuesOnConnection(sql, values, conn);
   }

   public void deleteCodeList(CodeList codeList) throws SQLException {
      String sql = "delete from code_list where code_list_id = ?";

      List values = new ArrayList();
      values.add(codeList.getId());

      SqlCommand.executeSqlUpdateWithValuesOnConnection(sql, values, conn);
   }
   
   
   public void addCodeToCodeList(Integer codeId, Integer codeListId, Integer sortOrder) throws SQLException {
      String sql = "insert into code_in_code_list (code_id, code_list_id, sort_order) values (?, ?, ?)";

      List values = new ArrayList();
      values.add(codeId);
      values.add(codeListId);
      values.add(sortOrder);

      SqlCommand.executeSqlUpdateWithValuesOnConnection(sql, values, conn);
   }
   
   public void removeCodeFromCodeList(Integer codeId, Integer codeListId) throws SQLException {
      String sql = "delete from code_in_code_list where code_id = ? and code_list_id = ?";

      List values = new ArrayList();
      values.add(codeId);
      values.add(codeListId);

      SqlCommand.executeSqlUpdateWithValuesOnConnection(sql, values, conn);
   }

   public void changeSortOrderForCode(Integer oldSortOrder, Integer newSortOrder, Integer codeListId) throws SQLException {
      String sql = "update code_in_code_list set sort_order = ? where sort_order = ? and code_list_id = ?";

      List values = new ArrayList();
      values.add(newSortOrder);
      values.add(oldSortOrder);
      values.add(codeListId);

      SqlCommand.executeSqlUpdateWithValuesOnConnection(sql, values, conn);
   }

   public void setSortOrderForCodeInCodeList(Integer newSortOrder, Integer codeId, Integer codeListId) throws SQLException {
      String sql = "update code_in_code_list set sort_order = ? where code_id = ? and code_list_id = ?";

      List values = new ArrayList();
      values.add(newSortOrder);
      values.add(codeId);
      values.add(codeListId);

      SqlCommand.executeSqlUpdateWithValuesOnConnection(sql, values, conn);
   }

   
   public void moveUpCodesBelowSortOrderInCodeList(Integer sortOrder, Integer codeListId) throws SQLException {
      String sql = "update code_in_code_list set sort_order = sort_order - 1 where sort_order > ? and code_list_id = ?";

      List values = new ArrayList();
      values.add(sortOrder);
      values.add(codeListId);

      SqlCommand.executeSqlUpdateWithValuesOnConnection(sql, values, conn);
   }
   
   
}
