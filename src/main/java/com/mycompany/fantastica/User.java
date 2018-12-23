/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fantastica;


/**
 *
 * @author gonza
 */
import lombok.Data;

@Data
public class User {
    private String mail;
    private String clave ;
    private int id_Usuario;     
    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;
    private int id_Tipo;
    
}