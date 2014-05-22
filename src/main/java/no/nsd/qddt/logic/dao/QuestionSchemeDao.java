package no.nsd.qddt.logic.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import no.nsd.qddt.logic.SqlCommand;
import no.nsd.qddt.logic.orm.QuestionSchemeOrm;
import no.nsd.qddt.model.QuestionScheme;

public class QuestionSchemeDao {

   private final Connection conn;
   
   public QuestionSchemeDao(Connection conn) {
      this.conn = conn;
   }

   public QuestionScheme getQuestionScheme(Integer id) throws SQLException {
      String sql = "select * from question_scheme where question_scheme_id = ?";
      
      List<Integer> values = new ArrayList<Integer>();
      values.add(id);
      
      SortedMap[] rows = SqlCommand.executeSqlQueryWithValuesOnConnection(sql, values, conn);
      return QuestionSchemeOrm.getQuestionSchemeFromFirstRow(rows);
   }
   
   
}
