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
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Admin
 */
public class Patient {
    int pid;
    String name;
    Date dob;
    String address;
    String gender;
    String bloodgrp;
    String email;
    String contactNo;
    Connection con;
    
    
    public Patient(){
        con = DBconnect.getConnection();
    }
    public ResultSet getDetails(){
        ResultSet rs = null;
         try 
        {
            String sql = "Select * from patient";
             PreparedStatement pt = (PreparedStatement) con.prepareStatement(sql);
            rs = pt.executeQuery();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return rs;
    }
   
    public void addPatient(String n, Date db, String addr, String gen, String em, String bg, String cont){
        String sq = "INSERT INTO patient(name, dob,contactNo,gender,address,email,bgroup) VALUES (?,?,?,?,?,?,?)";
        name = n;
        dob = db;
        address = addr;
        gender = gen;
        email = em;
        bloodgrp = bg;
        contactNo = cont;
        
        try{
            PreparedStatement pst =con.prepareStatement(sq);
            pst.setString(1, name);
            pst.setDate(2, dob);
            pst.setString(3, contactNo);
            pst.setString(4, gender);
            pst.setString(5, address);
            pst.setString(6, email);
            pst.setString(7, bloodgrp);
            
            pst.execute();
            JOptionPane.showMessageDialog(null, "1 Patient succesfully registered!"); 
        }
        catch(Exception e){
           JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
        }
        
    }
    
    public void updatePatient(int pID,String n, Date db, String addr, String gen, String em, String bg, String cont){
        String sq = "UPDATE patient SET name=?, dob=?,contactNo=?,gender=?,address=?,email=?,bgroup=? WHERE patientID =?";
        name = n;
        dob = db;
        address = addr;
        gender = gen;
        email = em;
        bloodgrp = bg;
        contactNo = cont;
        pid = pID;
        
        try{
            PreparedStatement pst =con.prepareStatement(sq);
            pst.setString(1, name);
            pst.setDate(2, dob);
            pst.setString(3, contactNo);
            pst.setString(4, gender);
            pst.setString(5, address);
            pst.setString(6, email);
            pst.setString(7, bloodgrp);
            pst.setInt(8, pid);
            
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "1 Patient succesfully updated!"); 
        }
        catch(Exception e){
           JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
        }
    }
    
    public void deletePatient(int pID,String na){
        String delQuery = "DELETE FROM patient WHERE patientID = ?";
         int p = JOptionPane.showConfirmDialog(null,"Do you really want to delete Employee "+na+ " ?","Delete",JOptionPane.YES_NO_OPTION);
        //asking for confirmation of the delete
        if (p== 0){
            try{
                 PreparedStatement pst =con.prepareStatement(delQuery);
                 pid = pID;
                 pst.setInt(1, pid);
                 pst.execute();
                 JOptionPane.showMessageDialog(null, "Patient "+na+" deleted !");
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public boolean checkPatient(int pID){
        boolean check =false;
        String sq = "SELECT * FROM patient WHERE patientID = ?";
        pid = pID;
        try{
            PreparedStatement pst =con.prepareStatement(sq);
            pst.setInt(1, pid);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                check = true;
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
        }
        return check;
    }
    
    public ResultSet getPatientDetails(int pID){
        ResultSet rs = null;
         try 
        {
            String sql = "Select * from patient WHERE patientID = ?";
             PreparedStatement pt = (PreparedStatement) con.prepareStatement(sql);
             pid = pID;
             pt.setInt(1, pid);
            rs = pt.executeQuery();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return rs;
    }
    
}
