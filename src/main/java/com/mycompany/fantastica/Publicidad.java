/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fantastica;
import java.sql.Date;

import lombok.Data;



/**
 *
 * @author gonza
 */
@Data
public class Publicidad {
    private int id_ModeloPublicidad;
    private String descripcion;
    private String imagen_Banner;
    private String ubicacion;
    private int id_UsuarioPublicidad;
    private Date fecha_inicio;
    private Date fecha_fin;    
}
