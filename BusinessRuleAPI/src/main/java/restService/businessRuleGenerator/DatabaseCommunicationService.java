package restService.businessRuleGenerator;

import businessRuleGenerator.domain.JDBC;
import businessRuleGenerator.domain.database.ConnectionDetails;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Created by william on 19-Jan-16.
 */

@Path("/database")
public class DatabaseCommunicationService{

    @POST
    @Path("/tablenames")
    @Consumes("application/json")
    @Produces("application/json")
    public Response getTableNames(ConnectionDetails dbdetails) {

        //todo: get all tebbelellen
        return  Response.status(200).entity("").build();
    }


    //todo: get all coulmnbnames

    //todo: query uitvoeren
}
