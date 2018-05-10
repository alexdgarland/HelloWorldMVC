package personal.alexgarland.helloworldmvc.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import personal.alexgarland.helloworldmvc.model.Employee;


public abstract class AbstractDatabaseEmployeeRepository implements IEmployeeRepository {

	private final Properties dbProperties;

	protected abstract String getDbPropertiesFileName();
	
	public AbstractDatabaseEmployeeRepository() {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		String path = classLoader.getResource(getDbPropertiesFileName()).getPath();
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(path));
		}
		catch (IOException ex) {
			throw new RuntimeException(ex);
		}
		this.dbProperties = properties;
	}
	
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(dbProperties.getProperty("url"), dbProperties);
	}
	
	@FunctionalInterface
	private interface PreparedStatementFunction<R> {
		R apply(PreparedStatement ps) throws SQLException;
	}

	private <A> A withPreparedStatement(String sql, PreparedStatementFunction<A> function) {
		 try (Connection con = getConnection();
				 PreparedStatement ps = con.prepareStatement(sql);) {		 
			 return function.apply(ps);
			 }
		 catch (SQLException ex) {
			 throw new RuntimeException(ex.getMessage());
			 }
	}
	
	protected abstract Employee addEmployeeWithPreparedStatement(PreparedStatement ps, Employee e) throws SQLException;
	protected abstract String getAddEmployeeQuery();

	@Override
	public Employee addEmployee(Employee e) {
		return withPreparedStatement(getAddEmployeeQuery(), (ps) -> addEmployeeWithPreparedStatement(ps, e));
	}
	
	protected abstract List<Employee> getEmployeeListWithPreparedStatement(PreparedStatement ps) throws SQLException;
	protected abstract String getGetEmployeeListQuery();

	@Override
	public List<Employee> getEmployeeList() {
		return withPreparedStatement(getGetEmployeeListQuery(), (ps) -> getEmployeeListWithPreparedStatement(ps));
	}
	
	protected abstract Integer deleteEmployeeWithPreparedStatement(PreparedStatement ps, int employeeId) throws SQLException;
	protected abstract String getDeleteQuery();

	@Override
	public void deleteEmployeeById(int employeeId) {
		withPreparedStatement(getDeleteQuery(),(ps) -> deleteEmployeeWithPreparedStatement(ps, employeeId));
	}
	
	protected abstract Integer updateEmployeeWithPreparedStatement(PreparedStatement ps, Employee e) throws SQLException;
	protected abstract String getUpdateQuery();

	@Override
	public void updateEmployee(Employee e) {
		withPreparedStatement(getUpdateQuery(), (ps) -> updateEmployeeWithPreparedStatement(ps, e));
	}
	
	protected abstract Employee getEmployeeByIdWithPreparedStatement(PreparedStatement ps, int employeeId) throws SQLException;
	protected abstract String getGetEmployeeByIdQuery();
	
	@Override
	public Employee getEmployeeById(int employeeId) {
		return withPreparedStatement(getGetEmployeeByIdQuery(), (ps) -> getEmployeeByIdWithPreparedStatement(ps, employeeId));
	}
	
}
