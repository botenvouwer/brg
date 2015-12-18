package restService.testService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/class")
public class HelloWorldService {

    @GET
    @Path("/method/{param}")
    public Response getMsg(@PathParam("param") String msg) {

        String output = "je hebt me uitgevoerd test/class/method/"+msg;

        return Response
                .status(200)
                .type("text/plain")
                .entity(output)
                .build();
    }

}