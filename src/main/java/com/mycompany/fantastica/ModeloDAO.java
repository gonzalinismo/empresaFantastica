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
public class ModeloDAO 
{
    private List<Modelo> modelo;

    public List<Modelo> obtenerUnModelo(int id_modelo) {
        String sql = "SELECT nombre_Categoria, nombre_Marca, nombre_Modelo,descripcion FROM modelo,categoria,marca WHERE modelo.id_Modelo=:id_modelo AND modelo.id_Marca=marca.id_Marca AND modelo.id_Categoria=categoria.id_Categoria";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            modelo = con
                    .createQuery(sql)
                    .addParameter("id_modelo",id_modelo)
                    .executeAndFetch(Modelo.class);

        } catch (Exception e) {
            System.err.println("SQL exception: " + e.getMessage());

        }

        return modelo;
    }
}
