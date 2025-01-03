/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.application.appvalidation;


import com.application.appvalidation.dto.Request;
import java.util.HashMap;
import com.application.appvalidation.util.Configuracion;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.HttpHeaders;

/**
 * REST Web Service
 *
 * @author user
 */
@Path("validation")
public class AppvalidationResource {

    private final String URL_SERVICE = Configuracion.getInstance().GetProperty("path_url");
    private final String userid_proxy = Configuracion.getInstance().GetProperty("userid_proxy");
    private final String password_proxy = Configuracion.getInstance().GetProperty("password_proxy");
    

    @Context
    private UriInfo context;

   

    /**
     * Creates a new instance of AppvalidationResource
     */
    public AppvalidationResource() {
    }

    /**
     * Retrieves representation of an instance of
     * com.application.appvalidation.AppvalidationResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object

        return "{\"hello\":\"word\"}";
    }

    /**
     * POST method for updating or creating an instance of
     * AppvalidationResource
     *
     * @param reqobj
     * @param content representation for the resource
     * @return
     */
    @POST
    @Path("/pathtovalidate")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response putJson(Request reqobj) {
        System.out.println("request" + reqobj);

        return Response.ok().entity(reqobj)
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_TYPE.withCharset("utf-8")).build();
    }



}
