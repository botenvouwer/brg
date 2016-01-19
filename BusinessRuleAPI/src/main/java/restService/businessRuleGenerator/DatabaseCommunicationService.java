package restService.businessRuleGenerator;

import businessRuleGenerator.dao.DAO;
import businessRuleGenerator.dao.DAOException;
import businessRuleGenerator.dao.DAOFactory;
import businessRuleGenerator.domain.database.ConnectionDetails;
import restService.response.Result;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.sql.SQLException;

/**
 * Created by william on 19-Jan-16.
 */

@Path("/database")
public class DatabaseCommunicationService{

    @POST
    @Path("/tablenames")
    @Consumes("application/json")
    @Produces("application/json")
    public Result getTableNames(ConnectionDetails dbdetails) throws SQLException {

        Result result = new Result();
        result.status = "success";

        DAO dao = null;
        try {
            dao = DAOFactory.build(dbdetails);
            result.result = dao.getTables();
        } catch (DAOException e) {
            result.error = e.getMessage();
            result.status = "error";
        }

        return result;
    }


    //todo: get all coulmnbnames

    //todo: query uitvoeren
}
