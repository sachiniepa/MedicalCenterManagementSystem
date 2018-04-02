/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import itpproject.DBconnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class MedicalRecord {
    String pid;
    float weight;
    float height;
    String allergies;
    String surgHis;
    String specialIss;
    String medi;
    Connection con;
    
    public MedicalRecord(){
        con = DBconnect.getConnection();
    }
    
    public ResultSet getRecord(String pID){
        ResultSet rs = null;
         try 
        {
            String sql = "Select * from medicalhistory WHERE patientID = ?";
             PreparedStatement pt = (PreparedStatement) con.prepareStatement(sql);
             pid = pID;
             pt.setString(1, pid);
            rs = pt.executeQuery();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return rs;
    }
    
    public void addRecord(String paID, float h, float w, String a, String sH, String med, String sI){
        String sq = "INSERT INTO medicalhistory (patientID, height, weight, allergies , sugicalHis,specialIssues, medications) VALUES (?,?,?,?,?,?,?)";
        pid = paID;
        height = h;
        weight = w;
        allergies = a;
        surgHis = sH;
        medi = med;
        specialIss = sI;
        
        try{
            PreparedStatement pst =con.prepareStatement(sq);
            
            pst.setString(1, pid);
            pst.setFloat(2, height);
            pst.setFloat(3, weight);
            pst.setString(4, allergies);
            pst.setString(5, surgHis);
            pst.setString(6, specialIss);
            pst.setString(7, medi);
            
            pst.execute();
            JOptionPane.showMessageDialog(null, "Medical Record added!"); 
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
        }
    }
    
    public void updateRecord(String paID, float h, float w, String a, String sH, String med, String sI){
        String sq = "UPDATE medicalhistory SET height= ?, weight=?, allergies=? , sugicalHis=?,specialIssues=?, medications=? WHERE patientID = ?";
        
        pid = paID;
        height = h;
        weight = w;
        allergies = a;
        surgHis = sH;
        medi = med;
        specialIss = sI;
        
        try{
            PreparedStatement pst =con.prepareStatement(sq);
            
            pst.setString(7, pid);
            pst.setFloat(1, height);
            pst.setFloat(2, weight);
            pst.setString(3, allergies);
            pst.setString(4, surgHis);
            pst.setString(5, specialIss);
            pst.setString(6, medi);
            
            pst.execute();
            JOptionPane.showMessageDialog(null, "Medical Record updated!"); 
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
        }
        
    }
}
