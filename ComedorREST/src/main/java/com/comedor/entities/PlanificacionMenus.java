/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comedor.entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author corebitsas
 */
public class PlanificacionMenus {
    private List<Planificacionmenu> planificacionMenus = new ArrayList<Planificacionmenu>();

    public List<Planificacionmenu> getPlanificionMenus() {
        return planificacionMenus;
    }

    public void setPlanificionMenus(List<Planificacionmenu> planificacionMenus) {
        this.planificacionMenus = planificacionMenus;
    }
    
    
}
