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
import net.proteanit.sql.DbUtils;
import itpproject.DBconnect;
//import testhms.paymenthandeling;

/**
 *
 * @author User
 */
public class PayMaintain {
    
     Connection conn = null;
    PreparedStatement pst=null;
    ResultSet rs = null;
    
    public void sendToDB(String EquiNo, float PCost,float SCost ,float TCost){
        conn = DBconnect.getConnection();
        try
        {
            String sql;
            sql = "INSERT INTO paymaintain (Equipment_No,Product_Cost,Service_Cost,Total_Cost)VALUES('"+EquiNo+"','"+PCost+"','"+SCost+"','"+TCost+"')";
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
    
  
    public void delete(String EquNo){
       
        conn = DBconnect.getConnection();
        String n = EquNo;
        
        int y= JOptionPane.showConfirmDialog(null,"Do you want to delete this ?");
        
        if (y==0){
        
            
        String q="DELETE from paymaintain where Equipment_No = '"+EquNo+"';";
            
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
    
    
    
    
   public void updateDB( String EquiNo, float PCost, float SCost, float TCost){
        conn = DBconnect.getConnection();
        try
        {
            String sql;
            
             
            sql = "UPDATE paymaintain SET Equipment_No='"+EquiNo+"',Product_Cost = '"+PCost+"',Service_Cost = '"+SCost+"',Total_Cost='"+TCost+"' WHERE Equipment_No='"+EquiNo+"'";
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
   public float calMaintain(float pcost,float scost)
          
   {
       float sum=pcost+scost;
       return sum;
        
   }


    
   
   
}
    

