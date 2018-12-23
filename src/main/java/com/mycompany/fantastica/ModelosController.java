/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fantastica;

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
public class ModelosController 
{
    public static Route
            getOneInstance=(Request req, Response res )->
            {
                res.header("Access-Control-Allow-Origin", "*");
              HashMap model=new HashMap();
              ModeloDAO mDAO=new ModeloDAO();
              List<Modelo> modelo;
              System.out.println(req.queryParams("id_modelo"));
              modelo=mDAO.obtenerUnModelo(Integer.parseInt(req.queryParams("id_modelo")));
              model.put("modelo",modelo.get(0));
              model.put("id_modeloselected",modelo.get(0).getId_Modelo());
              
                return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/modelo.vsl"));  
            };
}
