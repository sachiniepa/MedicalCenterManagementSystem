/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classPay;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import itpproject.DBconnect;

/**
 *
 * @author User
 */
public class paychanel {
    
    Connection conn = null;
    PreparedStatement pst=null;
    ResultSet rs = null;
    
    public void sendToDB(String AppRef, String PName1,String DocName1 ,float HosFee1,float DocFee1,float Total1){
        conn = DBconnect.getConnection();
        try
        {
            String sql;
            sql = "INSERT INTO paychanel1 (AppRefNo,DocName,HosFee,DocFee,Total)VALUES('"+AppRef+"','"+PName1+"','"+DocName1+"','"+HosFee1+"','"+DocFee1+"','"+Total1+"')";
            pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null,"Entry Successfull");
        }
        
      catch(SQLException e)
                {
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null,"Entry UNSuccessfull");
                }
     
    
}
    
    
      public void updateDB( String PID1,float HosFee1, float DocFee1, float Total1){
        conn = DBconnect.getConnection();
        try
        {
            String sql;
            
             
            sql = "UPDATE paychanel SET HosFee='"+HosFee1+"',DocFee = '"+DocFee1+"',Total = '"+Total1+"' WHERE PID='"+PID1+"'";
            pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null,"Entry Successfull");
            //fillcombo();
        }
        
      catch(SQLException e)
                {
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null,"Entry UNSuccessfull");
                }

}
      
      
        public void delete(String PID1){
       
        conn = DBconnect.getConnection();
        String n = PID1;
        
        int y= JOptionPane.showConfirmDialog(null,"Do you want to delete this ?");
        
        if (y==0){
        
            
        String q="DELETE from paychanel where PID = '"+PID1+"';";
            
        try{
            pst=conn.prepareStatement(q);
            pst.execute();
            
            //clear();
            
            }
            catch(SQLException e){
            
            JOptionPane.showMessageDialog(null,e+"Error");
            
            }
  
        }
        }
        
        
           public float calCh(float hfee,float dfee)
          
   {
       float sum=hfee+dfee;
       return sum;
        
   }
}
     