/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fantastica;

import java.net.InetAddress;
import java.util.HashMap;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.velocity.VelocityTemplateEngine;

/**
 *
 * @author gonza
 */
public class IndexController {
    public static Route 
        getIndex = (Request req, Response res) -> {
    
            HashMap model = new HashMap();
            String email = req.session().attribute("mail");
            System.out.println(InetAddress.getLocalHost().getHostAddress());
            
            res.header("Access-Control-Allow-Origin", "*");
            if(email == null){
                Menu menu = new Menu();
                model.put("menu", menu.getMenu());  
                model.put("menuActivo", "index");   
                model.put("template", "templates/index.vsl");
            }else{
                Menu menu = new Menu();
                model.put("menu", menu.getMenuLog(req.session().attribute("tipo"))); 
                model.put("email-usuario", email);   
                model.put("menuActivo", "index");     
                model.put("template", "templates/main.vsl");
            }
            
            
            return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/layout.vsl")); 
        };
}
