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
public class VentasxArticulosxFechaDAO 
{
    private List<VentasxArticulosxFecha> ventasxarticulos;

    public List<VentasxArticulosxFecha> consultarVentasDeArticulosEnFecha(int id_modelo,Date fecha_desde, Date fecha_hasta) {
        String sql = "SELECT fecha_venta,COUNT(*) as cantidad FROM ventasxarticulos,venta WHERE id_ventas=nro_factura AND id_articulos IN (SELECT id_articulo FROM articulo where id_modelo=:id_modelo)  AND id_ventas IN (SELECT nro_factura FROM venta WHERE :fecha_desde<fecha_venta and fecha_venta<:fecha_hasta) GROUP BY fecha_venta ";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            ventasxarticulos = con
                    .createQuery(sql)
                    .addParameter("id_modelo", id_modelo)
                    .addParameter("fecha_desde",fecha_desde)
                    .addParameter("fecha_hasta",fecha_hasta)
                    .executeAndFetch(VentasxArticulosxFecha.class);

        } catch (Exception e) {
            System.err.println("SQL exception: " + e.getMessage());

        }

        return ventasxarticulos;
    }
}
