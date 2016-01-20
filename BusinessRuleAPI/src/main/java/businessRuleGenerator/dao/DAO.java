package businessRuleGenerator.dao;

import businessRuleGenerator.domain.database.ColumnList;
import businessRuleGenerator.domain.database.ConnectionDetails;
import businessRuleGenerator.domain.database.TableList;

import java.sql.*;

/**
 * Created by william on 19-Jan-16.
 */
public abstract class DAO {

    ConnectionDetails connectionDetails = null;

    public DAO(ConnectionDetails con) throws DAOException {
        this.connectionDetails = con;
        try {
            Class.forName(con.dbDriver).newInstance();
        } catch (Exception e) {
            throw new DAOException("Failed to initialize database-driver: " + e.getMessage());
        }
    }

    public Connection connect() throws DAOException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(connectionDetails.dbUrl, connectionDetails.dbUsername, connectionDetails.dbPassword);
        } catch (Exception e) {
            throw new DAOException("Failed to connect to the database: " + e.getMessage());
        }
        return connection;
    }

    public void close(Connection connection) throws DAOException {
        try {
            connection.close();
        } catch (Exception e) {
            throw new DAOException("Closing the database-connection failed: " + e.getMessage());
        }
    }

    public TableList getTables() throws DAOException, SQLException {
        Connection connection = connect();
        TableList tableList = getTables(connection);
        close(connection);

        return tableList;
    }

    public ColumnList getColumns(String tableName) throws DAOException, SQLException {
        Connection connection = connect();
        ColumnList columnList = getColumns(connection, tableName);
        close(connection);

        return columnList;
    }

    protected abstract ColumnList getColumns(Connection connection, String tableName) throws DAOException, SQLException;

    public String doQuery(String sql) throws DAOException {
        try {
            Connection connection = connect();
            Statement stmt = connection.createStatement();

            ResultSet result = stmt.executeQuery(sql);

            SQLWarning sqlWarning = result.getWarnings();
            String warningString = null;
            while (sqlWarning != null) {
                warningString += sqlWarning.getMessage() + "\r\n";
                sqlWarning = sqlWarning.getNextWarning();
            }

            stmt.close();
            close(connection);
            return warningString;
        } catch(Exception e) {
            throw new DAOException("DAOException: " + e.getMessage());
        }
    }

    protected abstract TableList getTables(Connection connection) throws DAOException, SQLException;

}
