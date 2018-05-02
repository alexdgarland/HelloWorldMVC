package personal.alexgarland.helloworldmvc.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import personal.alexgarland.helloworldmvc.model.Employee;


public final class PostgresEmployeeManager implements IEmployeeManager {
	
	// TODO Clear up what needs to happen with error and resource handling.
	// TODO Do we really need prepared statements and a shared connection ...
	// TODO ... does this get in the way of clean error handling?
	// TODO - Might want to use try-with-resources.
	
	// TODO - Could refactor this into a generic JDBCEmployeeManager and specialise it to use Postgres.
	// TODO - Could implement a version that uses an ORM (Hibernate?)
	
	private Connection conn;
	
	private static final String ROOT_PATH = Thread
			.currentThread()
			.getContextClassLoader()
			.getResource("")
			.getPath();
	
	private static final String ADD_EMPLOYEE_QUERY_TEMPLATE =
			"INSERT INTO employees "
			+ "(first_name, last_name, nick_name) "
			+ "VALUES (?, ?, ?) "
			+ "RETURNING id";
	
	private PreparedStatement addEmployeeStatement;
	
	private static final String GET_EMPLOYEE_LIST_QUERY_TEMPLATE =
			"SELECT id, first_name, last_name, nick_name "
			+ "FROM employees";
	
	private PreparedStatement getEmployeeListStatement;
	
	private static Properties getProperties(String fileName) throws FileNotFoundException, IOException {
		Properties properties = new Properties();
		properties.load(new FileInputStream(ROOT_PATH + fileName));
		return properties;
	}
	
	public PostgresEmployeeManager() throws FileNotFoundException, IOException, SQLException, ClassNotFoundException {
		Properties dbProperties = getProperties("database.properties");
		conn = DriverManager.getConnection(dbProperties.getProperty("url"), dbProperties);	
		addEmployeeStatement = conn.prepareStatement(ADD_EMPLOYEE_QUERY_TEMPLATE);
		getEmployeeListStatement = conn.prepareStatement(GET_EMPLOYEE_LIST_QUERY_TEMPLATE);
	}
	
	public List<Employee> getEmployeeList() {

		List<Employee> list = new ArrayList<Employee>();
		
		ResultSet results = null;
		
		try {
			results = getEmployeeListStatement.executeQuery();
			while(results.next()) {
				Employee e = new Employee(
						results.getInt("id"),
						results.getString("first_name"),
						results.getString("last_name"),
						results.getString("nick_name")
						);
				list.add(e);
			}
		}
		catch (SQLException ex) {
			throw new RuntimeException(ex.getMessage());
		}
		finally {
			if (results != null) {
				try {
					results.close();
				}
				catch (SQLException ex) {
					// do nothing
				}
			}
		}

		return list;
	}

	public Employee addEmployee(Employee e) {
		
		ResultSet results = null;
		
		try {
			addEmployeeStatement.setString(1, e.getFirstName());
			addEmployeeStatement.setString(2, e.getLastName());
			addEmployeeStatement.setString(3, e.getNickName());			
			results = addEmployeeStatement.executeQuery();
			results.next();
			int id = results.getInt("id");
			return e.copyWithNewId(id);
		}
		catch (SQLException ex){
			throw new RuntimeException(ex.getMessage());
		}
		finally {
			if (results != null) {
				try {
					results.close();
				}
				catch (SQLException ex) {
					// do nothing
				}
			}
		}
		
	}

}
