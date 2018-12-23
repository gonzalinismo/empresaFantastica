package com.mycompany.fantastica;


import java.util.HashMap;
import spark.Filter;
import spark.Request;
import spark.Response;
import spark.Spark;
import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.options;
import static spark.Spark.post;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gonza
 */
public class Main {   
    
    public static void main(String[] args) {
            
           Spark.staticFileLocation("public");
            get("/", IndexController.getIndex);
            get("/crearPublicidad", PublicidadController.crearPublicidad);
            get("/insertarPublicidad", PublicidadController.insertarPublicidad);
            get("/login", UserController.getLogin); 
            post("/login", UserController.getLogin);
            get("/logout", UserController.Logout); 
            get("/buscarVentasModelo",VentasController.getVentasxArt);
            get("/getModelo",ModelosController.getOneInstance);
            get("/verCarrito",CarritoController.verCarrito);
            get("/borrarCarrito",CarritoController.borrarCarrito);
            get("/agregarCarrito",CarritoController.agregarCarrito);
    }
}



