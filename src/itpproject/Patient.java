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
import net.proteanit.sql.DbUtils;


public class Patient {
    
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    String patientId;
    String name; 
    String dob;
    String address;
    String contactNo;
    String email;
    String gender;
    String bloodGroup;
    
    
    
      
    String recID;
    String height;
    String weight;
    String allergy;
    String surgicalHist;
    String specialIssue;
    String medication;
   
    //private String patientId;
   // private String pid;
   // private String pid;
   
    
    
    Patient( )
    {
        
    }
    
    Patient(String pid)
    {
       this.patientId=pid;   
    }
    
    
    
    
   Patient( String pname, String birth, String add, String cNo, String mail, String gen, String bgroup )
    {
       
    // this.patientId=pid;
     this.name = pname;
     this.dob = birth;
     this.address = add;
     this.contactNo = cNo;
     this.email = mail ;
     this.gender = gen;
     this.bloodGroup = bgroup;
     
    
     try
     {
        con=DBconnect.getConnection(); //getting db connection
        
        
        
        
        Statement s = con.createStatement();
       // System.out.println("ddddd");
        s.executeUpdate("INSERT INTO `patient`(`name`, `dob`, `contactNo`, `gender`, `address`, `email`, `bgroup`)"+
                        "values('"+pname+"','"+birth+"','"+cNo+"','"+gen+"','"+add+"','"+mail+"','"+bgroup+"')"); //Inserting a new patient
         
        JOptionPane.showMessageDialog(null,"New Patient Registered!!");
         
         
         }
     
     catch(HeadlessException | SQLException e){
     
             JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
             
            
     
         }
     
    }

  /*  Patient(String patientId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    */

   /*Patient(String pid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        this.pid = pid;
    }*/

   

    public void updatePatientDetails(String pId, String pname, String birth, String add, String cNo, String mail, String gen, String bgroup)
     {
         
         patientId = pId;
         name = pname;
         dob = birth;
         contactNo = cNo;
         gender = gen;
         address = add;
         email = mail;
         bloodGroup = bgroup;
          
        Connection con = null;
         
         try
         {
             con=DBconnect.getConnection();
             
             String query1= "Update patient set name='"+name+"',dob='"+dob+"',contactNo='"+contactNo+"',gender='"+gender+"',address= '"+address+"',email='"+email+"',bgroup='"+bloodGroup+"' where patientID='"+patientId+"'";
             ps=con.prepareStatement(query1); 
             ps.executeUpdate(); 
             JOptionPane.showMessageDialog(null, "One Patient Updated!!");
             
            
            
             
             
              
           /*  ps.setString(2, name);
             ps.setString(3, dob);
             ps.setString(4, contactNo);
             ps.setString(5, address);
             ps.setString(6, email);
             ps.setString(7, gender);
             ps.setString(8, bloodGroup);
             ps.setString(1, pid);*/
           
             
            
             
             
         }
         
         catch(Exception e)
         {
             JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
         }
         
         
         
         
        // TableLoad1();
     
    
     }
   
     
     
   public void deletePatient(String key)
     {
       /*  int p = JOptionPane.showConfirmDialog(null,"Do you really want to delete?","Delete",JOptionPane.YES_NO_OPTION);
        //asking for confirmation of the delete
        if (p== 0){
        try{
            con = dbconnect.connect();
            String query = "select * from patient where patientID = '"+this.patientId+"'";
            String dquery ="DELETE FROM patient WHERE patientID = ?";
            PreparedStatement ps2=con.prepareStatement(query);
            rs = ps2.executeQuery();
            if(rs.next()){ //checking whether the pID exist
                 PreparedStatement pst2=con.prepareStatement(dquery);
                 pst2.setString(1, patientId);
                int executeUpdate = pst2.executeUpdate(); //Deleting the patient if it exist
                 JOptionPane.showMessageDialog(null, "Deleted!!");
            }
            else{
                JOptionPane.showMessageDialog(null, "This patientID doesn't exist!!","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
        catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);*/
             String id = key;
             String q = "Delete from patient where patientID = '"+id+"'";
             try
             {
                 Connection conn = DBconnect.getConnection();
                 ps = conn.prepareStatement(q);
                 ps.executeUpdate();
                 JOptionPane.showMessageDialog(null, "Patient record successfilly deleted!");
             }
             
             catch(Exception e)
             {
                 JOptionPane.showMessageDialog(null, e.getMessage());
             }
        
        
        
        
     
     }
        
    

     
     
     


     

  /*  void deletePatient(String a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void updateMedicalHistory(String height, String weight, String allergy, String surgicalHist, String specialIssue, String medication) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void updateMedicalHistory(String patientId, String height, String weight, String allergy, String surgicalHist, String specialIssue, String medication) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

     
 public void updateMedicalHistory(String pid,String h,String w,String al,String history,String issue ,String med)
{
    patientId=pid;
   //recID=rid;
   height=h;
   weight=w;
   allergy=al;
   surgicalHist=history;
   specialIssue=issue;
   medication=med;
   
   
        
        Connection con = null;
         
         try
         {
             con=DBconnect.getConnection();
             
             String query2= "UPDATE medicalhistory SET height='"+height+"',weight='"+weight+"',allergies='"+allergy+"',surgicalHistory='"+surgicalHist+"',specialIssues=''"+specialIssue+"',medications='"+medication+"' WHERE patientID='"+ patientId+"' ";
             ps=con.prepareStatement(query2);
             ps.executeUpdate();
             JOptionPane.showMessageDialog(null, "One record Updated!");
             
         }
         
         
         catch(Exception e)
         {
             
             JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
         }
}
}

        
    
    
    
    

