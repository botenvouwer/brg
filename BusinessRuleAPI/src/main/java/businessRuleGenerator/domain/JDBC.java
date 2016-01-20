package businessRuleGenerator.domain;

import java.sql.*;

/**
 * Created by melvin on 14-1-2016.
 */
public class JDBC {
    public String dbDriver;
    public String dbUrl;
    public String dbUsername;
    public String dbPassword;

    public JDBC()
    {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void insertTrigger(String sql) throws Exception {
        try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@ondora02.hu.nl:8521/cursus02.hu.nl", "", ""))
        {
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery(sql);

            SQLWarning sqlWarning = result.getWarnings();
            while(sqlWarning != null) {
                System.out.println("Warning: " + sqlWarning.getMessage());
                sqlWarning = sqlWarning.getNextWarning();
            }

            stmt.close();
            con.close();
        }
        catch(Exception e) {
            throw new Exception("Inserting trigger into database failed because the syntax was invalid...");
        }
    }

    public String getTables() {
        return "";
    }

}
