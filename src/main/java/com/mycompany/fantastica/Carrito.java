/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fantastica;
import lombok.Data;
/**
 *
 * @author Usuario
 */
@Data
public class Carrito 
{
    private int id_Modelo;
    private String nombre;
    private int cantidad;
    private float sub_Precio;
    private int id_Usuario;
}
