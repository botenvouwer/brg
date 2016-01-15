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


    public void doQuery() {
        try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@ondora02.hu.nl:8521:cursus02.hu.nl", "tosad_2015_2a_team1", "iutq4wt5lky'[ekh"))
        {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT DESCRIPTION FROM TYPE WHERE PK_TYPE = 22");

            while(rs.next()) {
                String description = rs.getString("DESCRIPTION");
                System.out.println("Description: " + description);
            }
            stmt.close();
            rs.close();
            con.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
