/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kareem.bridgejson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author km
 */
@Path("db")
public class BridgeJsonResource {

    @GET
    @Path("{key}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getData(@PathParam("key") String key) {

        try {
            
            java.nio.file.Path path = Paths.get(System.getProperty("jboss.server.data.dir")+"/db.json");
            
            JsonObject jsonObject = (JsonObject) Json.createReader(Files.newInputStream(path)).readObject() ;
      
            JsonArray array = (JsonArray)jsonObject.get(key) ;
            
            if(array == null)
            {
                return Response.status(Response.Status.NOT_FOUND).build() ;
                
            }
            else
                
                return Response.ok(array).build() ;
            
        } catch (IOException ex) {
            Logger.getLogger(BridgeJsonResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null ;
    }

}
