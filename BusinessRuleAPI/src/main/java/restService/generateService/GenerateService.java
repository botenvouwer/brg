package restService.generateService;

import businessRuleGenerator.domain.BusinessRule;
import businessRuleGenerator.domain.TestClass;
import com.sun.jersey.core.spi.scanning.uri.BundleSchemeScanner;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

/**
 * Created by william on 17-Dec-15.
 */

@Path("/generate")
public class GenerateService {

    @GET
    @Path("/generate")
    public Response generateRules(){


        return  Response.status(200).entity("testetst").build();

    }

    @GET
    @Path("/getter")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<BusinessRule> getBusinessRulesInJSON() {

        ArrayList<BusinessRule> businessRules = new ArrayList<BusinessRule>();

        BusinessRule br1 = new BusinessRule("Tuple Compare", "STATIC", "TCBRG", "TestTable", "Insert", "this is a description", "this is a type description");
        businessRules.add(br1);

        return businessRules;
    }

    @GET
    @Path("/getterzz")
    @Produces(MediaType.APPLICATION_JSON)
    public TestClass getTestInJSON() {

        TestClass tc = new TestClass(55);

        return tc;
    }

    @POST
    @Path("/post")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTrackInJSON() {

        String result = "Track saved : ";
        return Response.status(201).entity(result).build();

    }
}
