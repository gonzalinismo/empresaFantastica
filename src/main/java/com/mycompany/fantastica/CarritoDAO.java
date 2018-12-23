/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fantastica;

import java.util.List;
import org.sql2o.Connection;

/**
 *
 * @author Usuario
 */
public class CarritoDAO 
{
    private List<Carrito> productos;
    public List<Carrito> getTodos(int id_Usuario)
    {
        String sql = "SELECT carrito.id_Modelo,CONCAT( nombre_Marca,"+" '  ' "+" ,nombre_Modelo) as nombre, cantidad, sub_Precio FROM modelo,categoria,marca,carrito WHERE modelo.id_Marca=marca.id_Marca AND modelo.id_Categoria=categoria.id_Categoria AND modelo.id_Modelo=carrito.id_Modelo AND carrito.id_Usuario=:id_Usuario ORDER BY nombre_Categoria ";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            productos = con
                    .createQuery(sql)
                    .addParameter("id_Usuario", id_Usuario)
                    .executeAndFetch(Carrito.class);
        } catch (Exception e) {
            System.err.println("SQL exception: " + e.getMessage());

        }
        return productos;
    }
    
    
    public boolean borrarItem(String id_modelo, int id_usuario)
    {
        boolean bandera=false;
        String deleteSQL = "DELETE FROM carrito WHERE id_Modelo = :id and id_Usuario=:id_usuario";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
           con.createQuery(deleteSQL)
                    .addParameter("id", id_modelo)
                    .addParameter("id_usuario", id_usuario)
                    .executeUpdate();
           bandera=true;
        } catch(Exception e){
            
        }
        return bandera;
    }
    
    public boolean agregarItem(Carrito carrito ){
        boolean insercion=false;

        //Primero busco el precio del articulo
         String sql = "SELECT precio FROM modelo  WHERE  id_Modelo = :id";
         float subPrecio=0;
         try (Connection con = Sql2oDAO.getSql2o().open()) {
         subPrecio = con
                    .createQuery(sql)
                    .addParameter("id", carrito.getId_Modelo())
                     .executeScalar(float.class);
         carrito.setSub_Precio(subPrecio*carrito.getCantidad());
//insertamos el nuevo carrito
                    String sql1 = "INSERT INTO carrito (id_Usuario, id_Modelo, cantidad, sub_Precio) VALUE(:id_Usuario,:id_Modelo, :cantidad, :sub_Precio)";
                     try  {
                         con.createQuery(sql1)
                                 .bind(carrito)
                                 .executeUpdate();
                      insercion=true;
                 } catch(Exception e){
                     System.err.println("SQL exception: "+e.getMessage());            
                 }  
                                            
        } catch (Exception e) {
            System.err.println("SQL exception: " + e.getMessage());
        }
                 
        

                 
                                 
            return insercion;        
    }
    
}
