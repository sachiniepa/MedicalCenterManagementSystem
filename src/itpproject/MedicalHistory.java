/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itpproject;

/**
 *
 * @author ICT
 */
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class MedicalHistory {
    
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    String patientID;
    float height;
    float weight;
    String allergy;
    String surgicalHist;
    String specialIssue;
    String medication;
    
    MedicalHistory(String pid)
    {
        this.patientID=pid;
    }
    
    
    
     MedicalHistory( String pid,float h, float w, String al, String history, String issue, String med)
    {
       
              
               
         this.patientID=pid;
         this.height=h;
         this.weight=w;
         this.allergy=al;
         this.surgicalHist=history;
         this.specialIssue=issue;
         this.medication=med;
     
     
     try
     {
        con=DBconnect.getConnection(); //getting db connection
        
        Statement s = con.createStatement();
        s.executeUpdate("Insert into medicalhistory(patientID,height,weight,allergies,sugicalHis,specialIssues,medications)" +
                        "values('"+pid+"','"+h+"','"+w+"','"+al+"','"+history+"','"+issue+"','"+med+"')"); //Inserting a new patient
         
        JOptionPane.showMessageDialog(null,"First record of medical history entered!!");
         
         
         }
     
     catch(HeadlessException | SQLException e){
     
             JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
             
            
     
         }
    }
     
     
     
     
        public void updateMedicalHistory(double h,double w,String al,String history,String issue ,String med)
        {
         
          con =null;
         
         try
         {
             con=DBconnect.getConnection();
             
             String query2= "update patient set address=?,contactNo=?,email=? where patientID=? ";
            
             PreparedStatement ps2=con.prepareStatement(query2);
             
             ps2.setString(2, this.patientID);
             ps2.setDouble(3, h);
             ps2.setDouble(4, w);
             ps2.setString(5, al);
             ps2.setString(6, history);
             ps2.setString(7, issue);
             ps2.setString(8, med);
           
             
             
             
             ps2.executeUpdate();
             
               JOptionPane.showMessageDialog(null, "One Patient record Updated!!");
             
             
         }
         
         catch(HeadlessException | SQLException e)
         {
             JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
         }
         
         
         
         
         
     
    
     }
     
   
    
    }

