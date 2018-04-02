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

/**
 *
 * @author Admin
 */
public class Prescription {
    Connection con;
    
    public Prescription(){
        con = DBconnect.getConnection();
    }
    
    public int savePrecription(Date d, String iss, String med, String empN, String labT, String pID){
        int pNo=0;
        String sq = "INSERT INTO prescription (date, issue, medicines, empNo,labtests , patientID)VALUES (?,?,?,?,?,?)";
        String sq2 = "SELECT max(presNo) as no FROM prescription";
        try{
            PreparedStatement pst =con.prepareStatement(sq);
            PreparedStatement pst2 =con.prepareStatement(sq2);
            
            pst.setDate(1, d);
            pst.setString(2, iss);
            pst.setString(3, med);
            pst.setString(4, empN);
            pst.setString(5, labT);
            pst.setString(6, pID);
            
            
            pst.execute();
            ResultSet rs = pst2.executeQuery();
            
            
            if(rs.next()){
                pNo= rs.getInt("no");
                
            }
            JOptionPane.showMessageDialog(null, "Prescription saved!"); 
        }
        catch(Exception e){
           JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
        }
        return pNo;
    }
    
    public int savePrescription(Date d, String iss, String med, String empN,String pID){
       int pNo = this.savePrecription(d, iss, med, empN, "none", pID);
       return pNo;
    }
    
    public ResultSet getPrescription(String pID){
        ResultSet rs = null;
        int prNo = 0;
        String sq2 = "SELECT max(presNo) as no FROM prescription WHERE patientID = ?";
         try 
        {
             String sql = "Select * from prescription WHERE patientID = ? AND presNo = ?";
             PreparedStatement pt = (PreparedStatement) con.prepareStatement(sql);
             PreparedStatement pt2 = (PreparedStatement) con.prepareStatement(sq2);
             pt2.setString(1, pID);
             ResultSet rst= pt2.executeQuery();
             
             if(rst.next()){
                 prNo = rst.getInt("no");
             }
             
             pt.setString(1, pID);
             pt.setInt(2, prNo);
             rs = pt.executeQuery();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return rs;
        
    }
    
    public void addDrugs(String drug, String pID, String pNo, int qty, int nodays, int mqty, int aqty, int nqty){
        String sq = "INSERT INTO buy VALUES (?,?,?,?,?,?,?,?)";
        String sq2 = "SELECT drugCode FROM drugs WHERE drugName = ?";
        String dCode = "";
        try{
           PreparedStatement pst =con.prepareStatement(sq);
           PreparedStatement pst2 =con.prepareStatement(sq2);
           pst2.setString(1, drug);
           ResultSet rs = pst2.executeQuery();
           if(rs.next()){
               dCode = rs.getString("drugCode");
           }
           
               pst.setString(1, dCode);
               pst.setString(2, pID);
               pst.setString(3, pNo);
               pst.setInt(4, qty);
               pst.setInt(5, nodays);
               pst.setInt(6, mqty);
               pst.setInt(7, aqty);
               pst.setInt(8, nqty);
               
               pst.execute();
           
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            
        }
        
    }
    
}
