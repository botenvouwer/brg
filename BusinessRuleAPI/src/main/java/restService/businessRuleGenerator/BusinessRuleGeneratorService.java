package restService.businessRuleGenerator;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created by william on 17-Dec-15.
 */

@Path("/bsn")
public class BusinessRuleGeneratorService {

    @GET
    @Path("/*")
    public Response index(){

        return  Response.status(200).entity("home alone").build();

    }

    @GET
    @Path("/generate")
    public Response generateRules(){

        return  Response.status(200).entity("testetst").build();

    }

}
