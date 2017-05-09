/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kareem.bridgejson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/**
 *
 * @author km
 */

@Model
public class MainController{
    
    private Part part ;

    private List<String> urlsList = new ArrayList<>();
    
    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }
    
    public void generateUrls() throws IOException
    {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()  ;
        
        String url = request.getRequestURL().toString() ;
        
        Files.copy(part.getInputStream(), Paths.get(System.getProperty("jboss.server.data.dir")+"/db.json")
                ,StandardCopyOption.REPLACE_EXISTING) ;
        
        JsonObject jsonObject = Json.createReader(part.getInputStream()).readObject() ;

        jsonObject.keySet().stream().forEach(k -> {
        
            urlsList.add(url+"/"+k) ;
        });
    }

    public List<String> getUrlsList() {
        return urlsList;
    }

    public void setUrlsList(List<String> urlsList) {
        this.urlsList = urlsList;
    }

    
    
}
