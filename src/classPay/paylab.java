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
public class paylab {
    
    Connection conn = null;
    PreparedStatement pst=null;
    ResultSet rs = null;
    
    public void sendToDB(String Pname,int Tnum ,String Tname,float Charge1){
        conn = DBconnect.getConnection();
        
        try
        {
            String sql;
            sql = "INSERT INTO paylab (PName,TNum,TName,Charge) VALUES('"+Pname+"','"+Tnum+"','"+Tname+"','"+Charge1+"')";
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
    
    public void delete(String Pname){
       
        conn = DBconnect.getConnection();
        String n = Pname;
        
        int y= JOptionPane.showConfirmDialog(null,"Do you want to delete this ?");
        
        if (y==0){
        
            
        String q="DELETE from paylab where pName = '"+Pname+"';";
            
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
    
    
    public void updateDB(String PName1,int TNum1,String TName1,float Charge1){
        conn = DBconnect.getConnection();
        try
        {
            String sql;
            
             
            sql = "UPDATE paylab SET TNum = '"+TNum1+"',TName = '"+TName1+"',Charge = '"+Charge1+"' WHERE  PName = '"+PName1+"'";
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
}