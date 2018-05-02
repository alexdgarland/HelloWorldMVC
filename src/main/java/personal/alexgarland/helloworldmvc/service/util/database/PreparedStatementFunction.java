package personal.alexgarland.helloworldmvc.service.util.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface PreparedStatementFunction<R> {
	R apply(PreparedStatement ps) throws SQLException;
}
