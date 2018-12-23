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

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap; 

public class Menu {
    List<HashMap> menu = new ArrayList();
    List<HashMap> menuSide = new ArrayList();
    public void init() throws UnknownHostException {
            HashMap item = new HashMap();
            item.put("label", "Iniciar Sesion");
            item.put("url", "http://"+InetAddress.getLocalHost().getHostAddress()+":4567/login");
            menu.add(item);
           
    }
    public void initLogClient() throws UnknownHostException {
            HashMap item = new HashMap();
            item.put("label", "Ver carrito");
            item.put("url", "http://"+InetAddress.getLocalHost().getHostAddress()+":4567/index");
            menu.add(item);
            item = new HashMap();
            item.put("label", "Logout");
            item.put("url", "http://"+InetAddress.getLocalHost().getHostAddress()+":4567/");
            menu.add(item);
    }
    
    public void initLogAdmin() throws UnknownHostException {
            HashMap item = new HashMap();           
            item = new HashMap();
            item.put("label", "Logout");
            item.put("url", "http://"+InetAddress.getLocalHost().getHostAddress()+":4567/");
            
            menu.add(item);
    }
    public void sideMenuAdmin() throws UnknownHostException{
         HashMap item = new HashMap();           
            
            item = new HashMap();
            item.put("label", "Buscar Modelo");
            item.put("url", "http://"+InetAddress.getLocalHost().getHostAddress()+":4567/buscarVentasModelo");
            menuSide.add(item);
    }
    public void sideMenuClient() throws UnknownHostException{
         HashMap item = new HashMap();           
            item = new HashMap();
            item.put("label", "Cliente 1 ");
            item.put("url", "http://"+InetAddress.getLocalHost().getHostAddress()+":4567/");
            menuSide.add(item);
            item = new HashMap();
            item.put("label", "Cliente 2");
            item.put("url", "/");
            menuSide.add(item);
    }
    
    
    
    public List<HashMap> getMenu() throws UnknownHostException {
        init();
        return menu;
    }
    
    public List<HashMap> getMenuLog(int  tipo) throws UnknownHostException {
        if(tipo==1)
         initLogAdmin();
        else
            initLogClient();
        return menu;
    }
    
    public List<HashMap> getSideMenu(int tipo) throws UnknownHostException{
        if(tipo==1)
         sideMenuAdmin();
        else
            sideMenuClient();
        return menuSide;
    }
}
