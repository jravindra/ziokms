package com.walmartlabs.testdemo;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.walmartlabs.testdemo.model.Animal;
import com.walmartlabs.testdemo.model.Cat;
import com.walmartlabs.testdemo.model.Dog;

@Path("/hello")
public class HelloWorld {

    @GET
    @Path("/echo/{input}")
    @Produces("text/plain")
    public String ping(@PathParam("input") String input) {
        return input;
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/jsonBean")
    public Response modifyJson(JsonBean input) {
	input.setVal2(input.getVal1());
	return Response.ok().entity(input).build();
    }
    
    @POST
    @Consumes("application/xml")
    @Path("/diffmessage")
    public Response diffMessage(Animal input) {
	final ResponseBuilder ok = Response.ok();
	if ( input instanceof Dog ) {
		Dog d = (Dog) input;
		System.out.println("Dog - " + d.getName());
	} else if ( input instanceof Cat ) {
		Cat c = (Cat) input;
		System.out.println("Cat - " + c.getName());
	}
	return ok.build();
    }
}

