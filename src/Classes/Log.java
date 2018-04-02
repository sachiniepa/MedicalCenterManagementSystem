/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import itpproject.DBconnect;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class Log {
    Connection con;
    String user;
    Date login;
    Date logout;
    PreparedStatement pst;
    public Log(String us){
        con = DBconnect.getConnection();
        String sq = "INSERT INTO log (userName, login, logout)VALUES (?,?,?)";
        
        try{
            pst =con.prepareStatement(sq);
            user = us;
            pst.setString(1, user);
            pst.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
        }
    }
    
    
    
    public void executeLog(){
        try{
            pst.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            pst.execute();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
        }
    }
    
}
