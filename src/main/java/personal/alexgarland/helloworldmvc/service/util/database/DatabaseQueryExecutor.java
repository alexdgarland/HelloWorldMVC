package personal.alexgarland.helloworldmvc.service.util.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import static personal.alexgarland.helloworldmvc.service.util.PropertyManagement.readPropertyFile;

public class DatabaseQueryExecutor implements IQueryExecutor {
	
	private final Properties dbProperties;
	
	public DatabaseQueryExecutor(String dbPropertiesFileName) {
		this.dbProperties = readPropertyFile(dbPropertiesFileName);
	}
	
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(dbProperties.getProperty("url"), dbProperties);
	}

	@Override
	public <A> A withPreparedStatement(String sql, PreparedStatementFunction<A> function) {
		 try (Connection con = getConnection();
				 PreparedStatement ps = con.prepareStatement(sql);) {		 
			 return function.apply(ps);
			 }
		 catch (SQLException ex) {
			 throw new RuntimeException(ex.getMessage());
			 }
	}
	
}
