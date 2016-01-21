package businessRuleGenerator.dao;

import businessRuleGenerator.domain.database.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by william on 19-Jan-16.
 */
public class OracleDAO extends DAO {


    public OracleDAO(ConnectionDetails con) throws DAOException {
        super(con);
    }

    @Override
    protected TableList getTables(Connection connection) throws DAOException, SQLException {
        //Haal alle tabllen op
        PreparedStatement tableQuery = connection.prepareStatement("SELECT TABLE_NAME FROM all_tables WHERE owner = ?");
        tableQuery.setString(1, connectionDetails.dbName);

        ResultSet tableResult = tableQuery.executeQuery();
        ArrayList<String> tableNames = new ArrayList<>();
        TableList tableList = new TableList();
        while (tableResult.next()) {
            String tableName = tableResult.getString("TABLE_NAME");
            tableNames.add(tableName);
            tableList.tables.add(new Table(tableName));
        }

        //Haal alle columns op
        PreparedStatement columnQuery = connection.prepareStatement("SELECT TABLE_NAME, COLUMN_NAME, DATA_TYPE FROM user_tab_columns WHERE TABLE_NAME in('"+String.join("', '", tableNames)+"')");

        //todo: uitzoeken hoe we dit netjes kunnen preparen
        //columnQuery.setArray(1, connection.createArrayOf("text", tableNames.toArray()));

        ResultSet columnResult = columnQuery.executeQuery();
        Map<String, ArrayList<Column>> columns = new HashMap<>();
        while (columnResult.next()) {
            String tableName = columnResult.getString("TABLE_NAME");
            String columnName = columnResult.getString("COLUMN_NAME");
            String dataType = columnResult.getString("DATA_TYPE");

            if(!columns.containsKey(tableName)){
                columns.put(tableName, new ArrayList<Column>());
            }

            ArrayList<Column> columnsList = columns.get(tableName);
            Column column = new Column();
            column.name = columnName;
            column.dataType = dataType;
            columnsList.add(column);
        }

        //bind alles aan elkaar
        for(Table table : tableList.tables){
            table.columns = columns.get(table.name);
        }

        return tableList;
    }

    @Override
    protected ColumnList getColumns(Connection connection, String tableName) throws DAOException, SQLException {

        //Haal alle column-informatie op van een specifieke tabel
        PreparedStatement columnQuery = connection.prepareStatement("SELECT COLUMN_NAME, DATA_TYPE FROM user_tab_columns WHERE TABLE_NAME = ?");
        columnQuery.setString(1, tableName);
        ResultSet columnResult = columnQuery.executeQuery();

        ColumnList columnList = new ColumnList();
        while (columnResult.next()) {
            String columnName = columnResult.getString("COLUMN_NAME");
            String dataType = columnResult.getString("DATA_TYPE");
            columnList.columns.add(new Column(dataType, columnName));
        }

        return columnList;
    }
}
