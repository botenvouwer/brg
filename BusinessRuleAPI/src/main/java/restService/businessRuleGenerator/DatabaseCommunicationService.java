package restService.businessRuleGenerator;

import businessRuleGenerator.dao.DAO;
import businessRuleGenerator.dao.DAOException;
import businessRuleGenerator.dao.DAOFactory;
import businessRuleGenerator.domain.database.ConnectionDetails;
import businessRuleGenerator.domain.database.TableList;

import javax.ws.rs.*;

/**
 * Created by william on 19-Jan-16.
 */

@Path("/database")
public class DatabaseCommunicationService{

    @POST
    @Path("/tablenames")
    @Consumes("application/json")
    @Produces("application/json")
    public TableList getTableNames(ConnectionDetails dbdetails) throws DAOException {

        DAO dao = DAOFactory.build(dbdetails);

        return dao.getTables();
    }


    //todo: get all coulmnbnames

    //todo: query uitvoeren
}
