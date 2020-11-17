package com.stevenhoyosc.rouletteservice.rest.service;

import java.util.Set;
import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("query")
public class ApplicationConfig extends Application{
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.stevenhoyosc.rouletteservice.rest.service.RouletteFacadeRest.class);
    }
}
