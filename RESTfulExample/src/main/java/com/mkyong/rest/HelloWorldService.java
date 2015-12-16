package com.mkyong.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/hello")
public class HelloWorldService {

    @GET
    @Path("/{param}/{param2}")
    public Response getMsg(@PathParam("param") String msg, @PathParam("param2") String naam) {

        String output = "[3434,234234,234234,234234]";

        return Response
                .status(200)
                .type("application/json")
                .entity(output)
                .build();

    }

}