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
 * @author gonza
 */
public class UserController {
 public static Route 
        getLogin = (Request req, Response res) -> {
            res.header("Access-Control-Allow-Origin", "*");
            HashMap model = new HashMap();                         
            Menu menu = new Menu();
            model.put("menu", menu.getMenu());  
            model.put("menuActivo", "login");   
     
                               
            
            if(req.queryParams("clave")!=null && req.queryParams("mail")!=null)
            {  
                UserDAO uDAO = new UserDAO();
                List<User> user = uDAO.verificarPersonas(req.queryParams("mail"),req.queryParams("clave"));
                

                if(user.size() > 0){
                   req.session().attribute("tipo",user.get(0).getId_Tipo());
                   req.session().attribute("id_Usuario",user.get(0).getId_Usuario());
                    //CREAR SEASION/COOKIE                    
                    Menu men= new Menu();
                    Menu menSide= new Menu();
                    PublicidadController p= new PublicidadController();
                    model.put("publicidades",p.getPublicidades() );
                    model.put("menu", men.getMenuLog(req.session().attribute("tipo")));
                    model.put("menuSide", menSide.getSideMenu(req.session().attribute("tipo")));
                    model.put("template", "templates/main.vsl");
                    
                    
                }else{
                    model.put("template", "templates/login.vsl");
                    model.put("request",req);
                    model.put("error", "La contraseña o el mail es incorrecto.");
                }
                
            }else{
                model.put("mail","");
                model.put("template", "templates/login.vsl");   
            }
            
            return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/layout.vsl")); 
        }; 
    
    public static Route 
            Logout = (Request req, Response res) -> {
            res.header("Access-Control-Allow-Origin", "*");
            req.session().removeAttribute("id");
            req.session().removeAttribute("mail");
            req.session().removeAttribute("tipo");
            req.session().removeAttribute("id_Usuario");
            res.redirect("/main");
            return null;
        };
}
