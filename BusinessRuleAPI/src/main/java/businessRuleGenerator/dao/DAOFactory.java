package businessRuleGenerator.dao;

import businessRuleGenerator.domain.database.ConnectionDetails;
import businessRuleGenerator.generator.BusinessRuleGenerator;
import businessRuleGenerator.generator.GeneratorException;

/**
 * Created by melvin on 19-1-2016.
 */
public class DAOFactory {

    public static DAO build(ConnectionDetails connectionDetails) throws DAOException {

        DAO dao;
        switch (connectionDetails.dbDriver) {
            case "oracle.jdbc.driver.OracleDriver":
                dao = new OracleDAO(connectionDetails);
                break;
            default:
                throw new DAOException("Unsupported driver used: " + connectionDetails.dbDriver);
        }

        return dao;
    }
}
