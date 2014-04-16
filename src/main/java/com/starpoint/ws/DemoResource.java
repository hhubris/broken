package com.starpoint.ws;

import com.starpoint.domain.SimplePojo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

/**
 */
@Path("/demo")
@Produces({"application/json"})
public interface DemoResource {

    @GET
    List<SimplePojo> getAll();

    @GET
    @Path("{id}")
    public SimplePojo getDomainObject(@PathParam("id") Integer id);

    @GET
    @Path("some/{min}/{max}")
    public List<SimplePojo> findSome(@PathParam("min") Integer min, @PathParam("max") Integer max);

}
