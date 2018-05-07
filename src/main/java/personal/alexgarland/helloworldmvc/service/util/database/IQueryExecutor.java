package personal.alexgarland.helloworldmvc.service.util.database;

public interface IQueryExecutor {

	<A> A withPreparedStatement(String sql, PreparedStatementFunction<A> function);

}