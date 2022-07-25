/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project5_schoolmanagement;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author MarcoMan
 */
public class database {
    
    public static Connection connectDb(){
        
        try{
        
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection connect = 
                DriverManager.getConnection("jdbc:mysql://localhost/marco", "root", "");
            
            return connect;
            
        }catch(Exception e){e.printStackTrace();} 
        
        return null;
    }
    
}
