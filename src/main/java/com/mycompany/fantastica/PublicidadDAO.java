/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fantastica;
import org.sql2o.Connection;
import java.util.List;
import java.sql.Date;

/**
 *
 * @author Usuario
 */
public class PublicidadDAO
{
    private List<Publicidad> publicidades;
        public boolean insertarPublicidad(Publicidad publicidad){

            boolean insercion=false;
            String sql = "INSERT INTO publicidad (id_ModeloPublicidad,fecha_Inicio,fecha_Fin,imagen_Banner,ubicacion,id_UsuarioPublicidad,descripcion) VALUE(:id_ModeloPublicidad,:fecha_inicio,:fecha_fin,:imagen_Banner,:ubicacion,:id_UsuarioPublicidad,:descripcion)";
            try (Connection con = Sql2oDAO.getSql2o().open()) {
                con.createQuery(sql)
                        .bind(publicidad)
                        .executeUpdate();
             insercion=true;
        } catch(Exception e){
            System.err.println("SQL exception: "+e.getMessage());            
        }                   
            return insercion;
        }
        
        public List<Publicidad> getPublicidades(){
            Date fechaActual= new Date(System.currentTimeMillis());
            String sql="SELECT imagen_Banner FROM publicidad where :fecha <fecha_Fin AND :fecha>fecha_Inicio ";
            try (Connection con = Sql2oDAO.getSql2o().open()) {
                publicidades= con
                        .createQuery(sql)
                        .addParameter("fecha",fechaActual )
                        .executeAndFetch(Publicidad.class);             
        } catch(Exception e){
            System.err.println("SQL exception: "+e.getMessage());            
        }                                          
                return publicidades;
        }
}
