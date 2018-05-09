package personal.alexgarland.helloworldmvc.service;

import static personal.alexgarland.helloworldmvc.service.util.PropertyManagement.readPropertyFile;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import personal.alexgarland.helloworldmvc.model.Employee;
import personal.alexgarland.helloworldmvc.service.util.database.IQueryExecutor;
import personal.alexgarland.helloworldmvc.service.util.database.PreparedStatementFunction;


public final class DatabaseEmployeeRepository implements IEmployeeRepository {

	private final Properties queryProperties;
	private final IQueryExecutor queryExecutor;
	
	public DatabaseEmployeeRepository(String queryPropertyFilename, IQueryExecutor queryExecutor) {
		this.queryProperties = readPropertyFile(queryPropertyFilename);
		this.queryExecutor = queryExecutor;
	}
		
	static final Employee addEmployeeWithPreparedStatement(PreparedStatement ps, Employee e) throws SQLException {
		
		ps.setString(1, e.getFirstName());
		ps.setString(2, e.getLastName());
		ps.setString(3, e.getNickName());
		
		try (ResultSet rs = ps.executeQuery()) {
			rs.next();
			int id = rs.getInt("id");
			
			return e.copyWithNewId(id);
		}
		
	}
	
	static final List<Employee> getEmployeeListWithPreparedStatement(PreparedStatement ps) throws SQLException {
		
		List<Employee> list = new ArrayList<Employee>();
		
		try (ResultSet rs = ps.executeQuery()) {
			while(rs.next()) {
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
	
	static final Integer deleteEmployeeWithPreparedStatement(PreparedStatement ps, int employeeId) throws SQLException {
		ps.setInt(1, employeeId);
		return ps.executeUpdate();
	}
	
	@Override
	public Employee addEmployee(Employee e) {
		String query = queryProperties.getProperty("add_employee_query");
		PreparedStatementFunction<Employee> f = (ps) -> addEmployeeWithPreparedStatement(ps, e);
		return queryExecutor.withPreparedStatement(query, f);
	}
	
	@Override
	public List<Employee> getEmployeeList() {
		String query = queryProperties.getProperty("get_employees_query");
		PreparedStatementFunction<List<Employee>> f = (ps) -> getEmployeeListWithPreparedStatement(ps);
		return queryExecutor.withPreparedStatement(query, f);
	}

	@Override
	public void deleteEmployeeById(int employeeId) {
		String query = queryProperties.getProperty("delete_query");
		PreparedStatementFunction<Integer> f = (ps) -> deleteEmployeeWithPreparedStatement(ps, employeeId);
		queryExecutor.withPreparedStatement(query, f);
	}
	
	@Override
	public void updateEmployee(Employee e) {
		String query = queryProperties.getProperty("update_employee_query");
		PreparedStatementFunction<Employee> f = (ps) -> addEmployeeWithPreparedStatement(ps, e);
		queryExecutor.withPreparedStatement(query, f);
	}

	@Override
	public Employee getEmployeeById(int employeeId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
