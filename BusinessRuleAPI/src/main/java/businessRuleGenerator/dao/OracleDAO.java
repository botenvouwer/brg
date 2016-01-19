package businessRuleGenerator.dao;

import businessRuleGenerator.domain.database.ConnectionDetails;
import businessRuleGenerator.domain.database.Table;
import businessRuleGenerator.domain.database.TableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by william on 19-Jan-16.
 */
public class OracleDAO extends DAO {


    public OracleDAO(ConnectionDetails con) throws DAOException {
        super(con);
    }

    @Override
    protected TableList getTables(Connection connection) throws DAOException {

        try {
            TableList tableList = new TableList();

            PreparedStatement statement = connection.prepareStatement("SELECT \"TABLE\"_NAME FROM all_tables WHERE owner = ?");
            statement.setString(1, connectionDetails.dbName);

            ResultSet res = statement.executeQuery();

            System.out.println("Tabel namen:");
            while (res.next()) {
                String table_name = res.getString("TABLE_NAME");
                tableList.tables.add(new Table(table_name));
            }

            return tableList;
        } catch(Exception e) {
            throw new DAOException("Failed to retrieve table-names");
        }
    }

    //todo: get collumns names
}
