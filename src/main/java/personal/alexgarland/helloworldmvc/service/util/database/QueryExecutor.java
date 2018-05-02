package personal.alexgarland.helloworldmvc.service.util.database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class QueryExecutor {

	private static final String ROOT_PATH = Thread.currentThread().getContextClassLoader().getResource("").getPath();
	
	private String dbPropertiesFileName;
	
	public QueryExecutor(String dbPropertiesFileName) {
		this.dbPropertiesFileName = dbPropertiesFileName;
	}
	
	private Connection getConnection() throws SQLException {
		try {
			Properties dbProperties = new Properties();
			dbProperties.load(new FileInputStream(ROOT_PATH + dbPropertiesFileName));
			return DriverManager.getConnection(dbProperties.getProperty("url"), dbProperties);
		}
		catch(FileNotFoundException ex) {
			throw new RuntimeException(ex.getMessage());
			}
		catch(IOException ex) {
			throw new RuntimeException(ex.getMessage());
			}
	}

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

