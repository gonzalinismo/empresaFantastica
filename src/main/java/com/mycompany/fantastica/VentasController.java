/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fantastica;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.velocity.VelocityTemplateEngine;

/**
 *
 * @author Usuario
 */
public class VentasController
{
    public static Route
            getVentasxArt=(Request req,Response res)->
            {
               res.header("Access-Control-Allow-Origin", "*");
                boolean bandera=false;
                HashMap model = new HashMap();
                ModeloxMarcaxCategoriaDAO mDAO=new ModeloxMarcaxCategoriaDAO();
                List <ModeloxMarcaxCategoria> modeloxmarcaxcategoria;
                modeloxmarcaxcategoria=mDAO.obtenerTodosLosModelos();
                model.put("modeloxmarcaxcategoria",modeloxmarcaxcategoria);
                VentasxArticulosxFechaDAO vDAO=new VentasxArticulosxFechaDAO();
                List <VentasxArticulosxFecha> ventasxarticulosxfecha;
                if(req.queryParams("fecha_desde")!=null && req.queryParams("fecha_hasta")!=null)
                {
                  
                   req.session().attribute("id_modeloBuscado",req.queryParams("id_modelo"));
                   model.put("id_modeloBuscado",Integer.parseInt(req.session().attribute("id_modeloBuscado")));
                   ventasxarticulosxfecha= vDAO.consultarVentasDeArticulosEnFecha(Integer.parseInt(req.queryParams("id_modelo")),Date.valueOf(req.queryParams("fecha_desde")) ,Date.valueOf(req.queryParams("fecha_hasta")) );
                   model.put("ventasxarticulosxfecha",ventasxarticulosxfecha);
                   bandera=true;
                   
                   
                }
                   
                   Menu menu = new Menu();
                   Menu menuSide = new Menu();
                   model.put("bandera",bandera);
                   model.put("menu", menu.getMenuLog(req.session().attribute("tipo")));
                   model.put("menuSide", menuSide.getSideMenu(req.session().attribute("tipo")));
                   model.put("menuActivo", "login");
                   model.put("template", "templates/buscarVentasModelo.vsl");
                   return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/layout.vsl"));
             
                        
            };
    
}
