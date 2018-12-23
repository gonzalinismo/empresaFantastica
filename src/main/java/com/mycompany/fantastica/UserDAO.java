package com.mycompany.fantastica;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.List;
import org.sql2o.*;

/**
 *
 * @author gonza
 */
public class UserDAO {
    private List<User> personas;
    
    public List<User> verificarPersonas(String mail, String clave){
        
        
        try(Connection con = Sql2oDAO.getSql2o().open()){
            String sql = "SELECT * FROM usuario WHERE mail = :mail and  clave = :clave";
                        
         personas= con
                .createQuery(sql)
                .addParameter("mail", mail)
                .addParameter("clave", clave)
                .executeAndFetch(User.class);
        }
            return personas;
        }
        
        
        
    }
    
    

