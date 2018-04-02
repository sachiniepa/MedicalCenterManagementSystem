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
import itpproject.DBconnect;;

/**
 *
 * @author Rashan
 */
    
public class PayEquip {
    
    Connection conn = null;
    PreparedStatement pst=null;
    ResultSet rs = null;
    
    public void sendToDB(String sno1, String name1,String type1,String manuf1,String supp1, float price1, int qty1, float tcost1){
        conn = DBconnect.getConnection();
        try
        {
            String sql;
            sql = "INSERT INTO payequip (sno,name,type,manuf,supp,price,qty,tcost)VALUES('"+sno1+"','"+name1+"','"+type1+"','"+manuf1+"','"+supp1+"','"+price1+"','"+qty1+"','"+tcost1+"')";
            pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null,"Entry Successfull");
        }
        
      catch(SQLException e)
                {
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null,"Entry UN Successfull");
                }
    
    }
     public void delete(String sno1){
       
        conn = DBconnect.getConnection();
        String n = sno1;
        
        int y= JOptionPane.showConfirmDialog(null,"Do you want to delete this ?");
        
        if (y==0){
        
            
        String q="DELETE from payequip where sno = '"+sno1+"';";
            
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
     
        public void updateDB( String sno1,float price1, int qty1, float tcost1){
        conn = DBconnect.getConnection();
        try
        {
            String sql;
            sql = "UPDATE payequip SET price='"+price1+"',qty = '"+qty1+"',tcost = '"+tcost1+"' WHERE sno='"+sno1+"'";
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
        
   public float calEquip(float price2,float quantity2)
          
   {
       float sum= price2 * quantity2;
       return sum;
        
   }
      
        
}
