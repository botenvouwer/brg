package businessRuleGenerator.domain.database;

import businessRuleGenerator.domain.Validator;
import businessRuleGenerator.domain.ValidatorException;

/**
 * Created by william on 19-Jan-16.
 */
public class ConnectionDetails implements Validator {
    public String dbDriver;
    public String dbUrl;
    public String dbUsername;
    public String dbPassword;
    public String dbName;

    public ConnectionDetails() {
    }

    public ConnectionDetails(String dbDriver, String dbUrl, String dbUsername, String dbPassword, String dbName) {
        this.dbDriver = dbDriver;
        this.dbUrl = dbUrl;
        this.dbUsername = dbUsername;
        this.dbPassword = dbPassword;
        this.dbName = dbName;
    }

    @Override
    public void validate() throws ValidatorException {
        //TODO: validatie maken
    }
}
