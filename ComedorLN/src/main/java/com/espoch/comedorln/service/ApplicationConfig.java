/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espoch.comedorln.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author corebitsas
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.espoch.comedorln.service.CostoFacadeREST.class);
        resources.add(com.espoch.comedorln.service.CostousuarioFacadeREST.class);
        resources.add(com.espoch.comedorln.service.MenuFacadeREST.class);
        resources.add(com.espoch.comedorln.service.PlanificacionmenuFacadeREST.class);
        resources.add(com.espoch.comedorln.service.TipomenuFacadeREST.class);
        resources.add(com.espoch.comedorln.service.TipousuarioFacadeREST.class);
        resources.add(com.espoch.comedorln.service.VentaFacadeREST.class);
    }
    
}
