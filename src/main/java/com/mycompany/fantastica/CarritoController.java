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
public class CarritoController {
    public static Route 
            verCarrito=(Request req,Response res)->
            {
                res.header("Access-Control-Allow-Origin", "*");
                HashMap model=new HashMap();
                List<Carrito> productos;
                Carrito carrito= new Carrito();
                CarritoDAO carritodao=new CarritoDAO();
                productos=carritodao.getTodos(req.session().attribute("id_Usuario"));
                model.put("productos",productos);
                return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/carrito.vsl"));
            };
    public static Route 
            borrarCarrito=(Request req,Response res)->
            {
                res.header("Access-Control-Allow-Origin", "*");
                CarritoDAO carritodao=new CarritoDAO();
                if(carritodao.borrarItem(req.queryParams("id_modelo"),req.session().attribute("id_Usuario")))
                    res.redirect("/verCarrito");
                return null;
            };
    public static Route
                agregarCarrito=(Request req, Response res)->{
                    System.out.println("entro peola amewo");
                    res.header("Access-Control-Allow-Origin", "*");
                    CarritoDAO carritoDAO= new CarritoDAO();
                    Carrito carrito= new Carrito();
                    carrito.setId_Modelo(Integer.parseInt(req.queryParams("id_modelo")));
                    carrito.setId_Usuario(req.session().attribute("id_Usuario"));
                    carrito.setCantidad(Integer.parseInt(req.queryParams("cant")));
                    carrito.setSub_Precio(0);                    
                    if(carritoDAO.agregarItem(carrito)){
                       System.out.println("agrega2 peola amewo");
                    }
                    return "al toque perro";
                };
    
}
