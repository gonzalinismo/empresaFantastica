/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fantastica;

/**
 *
 * @author Usuario
 */
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.sql.Date;
import java.util.ArrayList;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.velocity.VelocityTemplateEngine;

public class PublicidadController {

    public static Route insertarPublicidad = (Request req, Response res)
            -> {
        HashMap model = new HashMap();
        res.header("Access-Control-Allow-Origin", "*");
        Menu menu = new Menu();
        model.put("menu", menu.getMenu());
        model.put("menuActivo", "login");
        if ( req.queryParams("descripcion") != null && req.queryParams("imagen_banner") != null ) {             
            PublicidadDAO pDAO = new PublicidadDAO();
            Publicidad publicidad=new Publicidad();
            System.out.println(Integer.parseInt(req.session().attribute("id_modeloBuscado"))+" jajajja");
            publicidad.setId_ModeloPublicidad(Integer.parseInt(req.session().attribute("id_modeloBuscado")));
            publicidad.setFecha_inicio(Date.valueOf(req.queryParams("fecha_inicio")));
            publicidad.setFecha_fin(Date.valueOf(req.queryParams("fecha_fin")));
            publicidad.setDescripcion(req.queryParams("descripcion"));
            publicidad.setImagen_Banner(req.queryParams("imagen_banner"));
            publicidad.setId_UsuarioPublicidad(req.session().attribute("id_Usuario"));
            publicidad.setUbicacion(req.queryParams("ubicacion"));
            
            if (pDAO.insertarPublicidad(publicidad)) {
                //CREAR SEASION/COOKIE
                Menu men = new Menu();
                Menu menSide = new Menu();
                
                req.session().removeAttribute("id_modeloBuscado");
                model.put("menu", men.getMenuLog(req.session().attribute("tipo")));
                model.put("menuSide", menSide.getSideMenu(req.session().attribute("tipo")));
                model.put("template", "templates/publicidadCargada.vsl");

            } else {
                model.put("template", "templates/login.vsl");
                model.put("request", req);
                model.put("error", "La contraseña o el mail es incorrecto.");
            }

        } else {
            model.put("mail", "");
            model.put("template", "templates/login.vsl");
        }

        return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/layout.vsl"));
             };
    
    
     public static Route crearPublicidad = (Request req, Response res)
            -> {
              res.header("Access-Control-Allow-Origin", "*");
              HashMap model = new HashMap();
            Menu menu = new Menu();
            Menu menuSide = new Menu();
             model.put("menu", menu.getMenuLog(req.session().attribute("tipo")));
             model.put("menuSide", menuSide.getSideMenu(req.session().attribute("tipo")));
            model.put("menuActivo", "login");
            model.put("template", "templates/insertarPublicidad.vsl");
            
             return new VelocityTemplateEngine().render(new ModelAndView(model, "templates/layout.vsl"));
            } ;
    
     
           public List<HashMap>  getPublicidades (){
               List<HashMap> publis = new ArrayList();
                HashMap item ;
                PublicidadDAO pDAO= new PublicidadDAO();
                List<Publicidad> p= pDAO.getPublicidades();
                
                for(int i=0; i<p.size(); i++){
                    item = new HashMap();
                    item.put("ubicacion",p.get(i).getImagen_Banner());
                    publis.add(item);
                    System.out.println(p.get(i).getImagen_Banner());
                }
               
                              
               return publis  ;             
           }
    
     
}


