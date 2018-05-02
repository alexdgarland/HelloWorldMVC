package personal.alexgarland.helloworldmvc.service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import personal.alexgarland.helloworldmvc.model.Employee;
import personal.alexgarland.helloworldmvc.service.util.database.PreparedStatementFunction;
import personal.alexgarland.helloworldmvc.service.util.database.QueryExecutor;


public final class PostgresEmployeeManager implements IEmployeeManager {
	
	private static final QueryExecutor executor = new QueryExecutor("database.properties");
	
	private static final String ADD_EMPLOYEE_QUERY =
			"INSERT INTO employees (first_name, last_name, nick_name) "
			+ "VALUES (?, ?, ?) RETURNING id";
		
	public Employee addEmployee(Employee e) {

		PreparedStatementFunction<Employee> addEmployeeFunction = (ps) -> {
			ps.setString(1, e.getFirstName());
			ps.setString(2, e.getLastName());
			ps.setString(3, e.getNickName());
			try (ResultSet rs = ps.executeQuery()) {
				rs.next();
				int id = rs.getInt("id");
				return e.copyWithNewId(id);
				}
			};
			
			return executor.withPreparedStatement(ADD_EMPLOYEE_QUERY, addEmployeeFunction);
			
	}
	
	private static final String GET_EMPLOYEES_QUERY = "SELECT id, first_name, last_name, nick_name FROM employees";
	
	private static final PreparedStatementFunction<List<Employee>> GET_EMPLOYEES_FUNCTION = (ps) -> {
		List<Employee> list = new ArrayList<Employee>();
		try (ResultSet rs = ps.executeQuery()) {
			while(rs.next()) {
				Employee e = new Employee(rs.getInt("id"), rs.getString("first_name"),
						rs.getString("last_name"), rs.getString("nick_name"));
				list.add(e);
			}
		}
		return list;			
	};
	
	public List<Employee> getEmployeeList() {
		return executor.withPreparedStatement(GET_EMPLOYEES_QUERY, GET_EMPLOYEES_FUNCTION);
	}
	
}
