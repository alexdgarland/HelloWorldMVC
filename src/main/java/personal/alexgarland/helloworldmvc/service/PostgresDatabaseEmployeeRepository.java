package personal.alexgarland.helloworldmvc.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import personal.alexgarland.helloworldmvc.model.Employee;

public final class PostgresDatabaseEmployeeRepository extends AbstractDatabaseEmployeeRepository {

  @Override
  protected String getDbPropertiesFileName() {
    return "postgres.database.properties";
  }

  @Override
  protected Employee addEmployeeWithPreparedStatement(PreparedStatement ps, Employee e)
      throws SQLException {
    ps.setString(1, e.getFirstName());
    ps.setString(2, e.getLastName());
    ps.setString(3, e.getNickName());
    try (ResultSet rs = ps.executeQuery()) {
      rs.next();
      int id = rs.getInt("id");
      return e.copyWithNewId(id);
    }
  }

  @Override
  protected String getAddEmployeeQuery() {
    return "INSERT INTO employees (first_name, last_name, nick_name) VALUES (?, ?, ?) RETURNING id";
  }

  @Override
  protected List<Employee> getEmployeeListWithPreparedStatement(PreparedStatement ps)
      throws SQLException {
    List<Employee> list = new ArrayList<Employee>();
    try (ResultSet rs = ps.executeQuery()) {
      while (rs.next()) {
        int id = rs.getInt("id");
        String firstName = rs.getString("first_name");
        String lastName = rs.getString("last_name");
        String nickName = rs.getString("nick_name");
        Employee e = new Employee(id, firstName, lastName, nickName);
        list.add(e);
      }
    }
    return list;
  }

  @Override
  protected String getGetEmployeeListQuery() {
    return "SELECT id, first_name, last_name, nick_name FROM employees WHERE logical_delete = false";
  }

  @Override
  protected Integer deleteEmployeeWithPreparedStatement(PreparedStatement ps, int employeeId)
      throws SQLException {
    ps.setInt(1, employeeId);
    return ps.executeUpdate();
  }

  @Override
  protected String getDeleteQuery() {
    return "UPDATE employees SET logical_delete = true WHERE id = ?";
  }

  @Override
  protected Integer updateEmployeeWithPreparedStatement(PreparedStatement ps, Employee e)
      throws SQLException {
    ps.setString(1, e.getFirstName());
    ps.setString(2, e.getLastName());
    ps.setString(3, e.getNickName());
    ps.setInt(4, e.getId());
    return ps.executeUpdate();
  }

  @Override
  protected String getUpdateQuery() {
    return "UPDATE employees SET first_name = ?, last_name = ?, nick_name = ? WHERE id = ?";
  }

  @Override
  protected Employee getEmployeeByIdWithPreparedStatement(PreparedStatement ps, int employeeId)
      throws SQLException {
    ps.setInt(1, employeeId);
    try (ResultSet rs = ps.executeQuery()) {
      rs.next();
      String firstName = rs.getString("first_name");
      String lastName = rs.getString("last_name");
      String nickName = rs.getString("nick_name");
      return new Employee(employeeId, firstName, lastName, nickName);
    }
  }

  @Override
  protected String getGetEmployeeByIdQuery() {
    return "SELECT first_name, last_name, nick_name FROM employees WHERE logical_delete = false AND id = ?";
  }

}
