/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import itpproject.DBconnect;

/**
 *
 * @author Admin
 */
public class Users {
    private String user;
    private String pass;
    private String role;
    Connection conn = null;
    
    public Users(){
        conn = DBconnect.getConnection();
    }
    
    public void addUser(String u, String p, String r){
        String q1 = "INSERT INTO users VALUES (?, ?, ?)";
        try{
             PreparedStatement pst = conn.prepareStatement(q1);
             user = u;
             pass = p;
             role = r;
             pst.setString(1, user);
             pst.setString(2, pass);
             pst.setString(3, role);
             pst.executeUpdate();
              JOptionPane.showMessageDialog(null, "New user Account created!\n USERNAME = "+user+"   PASSWORD = "+pass);
        }
        catch(Exception e){
             JOptionPane.showMessageDialog(null, e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public ResultSet validateUser(String u, String p){
        ResultSet rs = null;
    String q1 = "SELECT role FROM users WHERE username = ? AND password = ?";
        try{
             PreparedStatement pst = conn.prepareStatement(q1);
             user = u;
             pass = p;
             
             pst.setString(1, user);
             pst.setString(2, pass);
             
             rs = pst.executeQuery();
             
        }
        catch(Exception e){
             JOptionPane.showMessageDialog(null, e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
        }
        return rs;
    }
     public String getPassword(String us){
         ResultSet rs = null;
         String pass = null;
    String q1 = "SELECT password FROM users WHERE username = ? ";
        try{
             PreparedStatement pst = conn.prepareStatement(q1);
             user = us;
             
             
             pst.setString(1, user);
          
             
             rs = pst.executeQuery();
             if(rs.next()){
                 pass = rs.getString("password");
             }
        }
        catch(Exception e){
             JOptionPane.showMessageDialog(null, e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
        }
        return pass;
    }
    
    public void changePass(String us, String pa){
        String q1 = "UPDATE users SET password = ? WHERE username = ? ";
        try{
             PreparedStatement pst = conn.prepareStatement(q1);
             
             
             
             pst.setString(1, pa);
             pst.setString(2, us);
             pst.executeUpdate();
             
              JOptionPane.showMessageDialog(null, "Your password has been changed");
        }
        catch(Exception e){
             JOptionPane.showMessageDialog(null, e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    public void deleteUser(String us){
        String query = "DELETE FROM users WHERE username = ?";
        try{
          PreparedStatement pst = conn.prepareStatement(query); 
          pst.setString(1, us);
          pst.executeUpdate();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
