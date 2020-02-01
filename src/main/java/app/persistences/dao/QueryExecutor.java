package app.persistences.dao;

import app.persistences.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;

public class QueryExecutor {

    Logger logger = Logger.getLogger(QueryExecutor.class);

    /**
     * PreparedStatement instance
     */
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    /**
     * Connection instance
     */
    private Connection connection = ConnectionPool.getConnection();

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
        }
    }
    /**
     * Executes insert(returns id), update and delete queries.
     *
     * @param query
     * @param args
     * @return if if request is insert
     */
    public void executeStatement(String query, Object... args) {
        try {
            connection.setAutoCommit(false);
            preparedStatement = (PreparedStatement) connection.prepareStatement(query);
            setValues(preparedStatement, args);
            preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            logger.error("Execute statement error " + e.getMessage());
        }
    }

    /**
     * Executes select query and returns resultset.
     *
     * @param query to be executed
     * @param args
     * @return result of select queries
     * @throws SQLException
     */
    public ResultSet getResultSet(String query, Object... args) {
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement(query);
            setValues(preparedStatement, args);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
    /**
     * Returns connection to pool.
     */
    public void close() {
        try {
            if (resultSet != null){
                resultSet.close();
            }
            if(preparedStatement != null){
                preparedStatement.close();
            }
            connection.close();

        } catch (SQLException e) {
            logger.error("Error while closing connection");
        }
    }
}
