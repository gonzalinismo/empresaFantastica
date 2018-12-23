/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fantastica;

import java.sql.Date;
import java.util.List;
import org.sql2o.Connection;

/**
 *
 * @author Usuario
 */
public class ModeloxMarcaxCategoriaDAO 
{
    private List<ModeloxMarcaxCategoria> modeloxmarcaxcategoria;

    public List<ModeloxMarcaxCategoria> obtenerTodosLosModelos() {
        String sql = "SELECT id_Modelo,nombre_Categoria, nombre_Marca, nombre_Modelo,descripcion FROM modelo,categoria,marca WHERE modelo.id_Marca=marca.id_Marca AND modelo.id_Categoria=categoria.id_Categoria ORDER BY nombre_Categoria ";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            modeloxmarcaxcategoria = con
                    .createQuery(sql)
                    .executeAndFetch(ModeloxMarcaxCategoria.class);

        } catch (Exception e) {
            System.err.println("SQL exception: " + e.getMessage());

        }

        return modeloxmarcaxcategoria;
    }
}
