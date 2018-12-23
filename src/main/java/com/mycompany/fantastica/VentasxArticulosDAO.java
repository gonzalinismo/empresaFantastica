/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fantastica;

import java.util.List;
import java.sql.Date;
import org.sql2o.Connection;

/**
 *
 * @author Usuario
 */
public class VentasxArticulosDAO {

    private List<VentasxArticulos> ventasxarticulos;

    public List<VentasxArticulos> consultarVentasDeArticulosEnFecha(int id_modelo,Date fecha_desde, Date fecha_hasta) {
        String sql = "SELECT * FROM ventasxarticulos WHERE id_articulos IN (SELECT id_articulo FROM articulo where id_modelo=:id_modelo)  AND id_ventas IN (SELECT nro_factura FROM venta WHERE :fecha_desde<fecha_venta and fecha_venta<:fecha_hasta) ";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            ventasxarticulos = con
                    .createQuery(sql)
                    .addParameter("id_modelo", id_modelo)
                    .addParameter("fecha_desde",fecha_desde)
                    .addParameter("fecha_hasta",fecha_hasta)
                    .executeAndFetch(VentasxArticulos.class);

        } catch (Exception e) {
            System.err.println("SQL exception: " + e.getMessage());

        }

        return ventasxarticulos;
    }
}
