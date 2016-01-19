package businessRuleGenerator.dao;

import businessRuleGenerator.domain.database.ConnectionDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by william on 19-Jan-16.
 */
public class OracleDAO extends DAO {


    public OracleDAO(ConnectionDetails con) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        super(con);
    }

    @Override
    protected void getTables(Connection connection) throws SQLException {

        PreparedStatement statement = connection.prepareStatement("SELECT \"TABLE\"_NAME FROM all_tables WHERE owner = ?");
        statement.setString(1, connectionDetails.dbName);

        ResultSet res = statement.executeQuery();

        System.out.println("Tabel namen:");
        while (res.next()){
            String test = res.getString("TABLE_NAME");
            System.out.println(test);

            //todo: haal kollom namen op

        }

        //todo: maak Table object en return deze

    }

    //todo: get collumns names
}
