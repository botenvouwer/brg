package businessRuleGenerator.domain;

import java.sql.*;

/**
 * Created by melvin on 14-1-2016.
 */
public class JDBC {

    public JDBC()
    {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void insertTrigger(String sql) throws Exception {
        try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@ondora02.hu.nl:8521/cursus02.hu.nl", "tosad_2015_2a_team1_target", "732r78tt3vngy873"))
        {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);

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
