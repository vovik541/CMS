package app.persistences.dao;

import app.persistences.ConnectionDB;
import app.persistences.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;

public class QueryExecutor {

    Logger logger = Logger.getLogger(QueryExecutor.class);

    /**
     * PreparedStatement instance
     */
    private PreparedStatement preparedStatement;

    /**
     * Connection instance
     */
    private Connection connection;

    {
        try {
            connection = ConnectionDB.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Singleton instance
     */

    public QueryExecutor() {

    }

    /**
     * Inserts an array of objects into prepared statement.
     *
     * @param preparedStatement statement to be executed
     * @param values            array of objects to be inserted
     * @throws SQLException
     */
    private void setValues(PreparedStatement preparedStatement, Object... values) throws SQLException {
        for (int i = 0; i < values.length; i++) {
            preparedStatement.setObject(i + 1, values[i]);
            System.out.println(values[i].toString());
        }
    }
    /**
     * Executes insert(returns id), update and delete queries.
     *
     * @param query
     * @param args
     * @return if if request is insert
     */
    public int executeStatement(String query, Object... args) {
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            setValues(preparedStatement, args);
            int res = preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                return res;
            }
        } catch (SQLException e) {
            logger.error("Execute statement error " + e.getMessage());
        }
        return 0;
    }

    /**
     * Executes select query and returns resultset.
     *
     * @param query to be executed
     * @param args
     * @return result of select queries
     * @throws SQLException
     */
    public ResultSet getResultSet(String query, Object... args) throws SQLException {
        preparedStatement = (PreparedStatement) connection.prepareStatement(query);
        setValues(preparedStatement, args);
        return preparedStatement.executeQuery();
    }
    /**
     * Returns connection to pool.
     */
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error("Error while closing connection");
        }
    }
}
