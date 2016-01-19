package businessRuleGenerator.dao;

import businessRuleGenerator.domain.database.ConnectionDetails;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by william on 19-Jan-16.
 */
public abstract class DAO {

    ConnectionDetails connectionDetails = null;

    public DAO(ConnectionDetails con) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        this.connectionDetails = con;
        Class.forName(con.dbDriver).newInstance();
    }

    public Connection connect() throws SQLException {
        Connection connection = DriverManager.getConnection(connectionDetails.dbUrl, connectionDetails.dbUsername, connectionDetails.dbPassword);
        return connection;
    }

    public void close(Connection connection) throws SQLException {
        connection.close();
    }

    public void getTables() throws SQLException {
        Connection connection = connect();
        getTables(connection);
        close(connection);
    }

    //todo: getCoumlfgk  namens

    //todo: query methode maken
    //public void query();

    protected abstract void getTables(Connection connection) throws SQLException;

}
