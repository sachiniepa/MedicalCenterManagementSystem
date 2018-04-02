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
public class paysup {
    
      Connection conn = null;
    PreparedStatement pst=null;
    ResultSet rs = null;
    
    public void sendToDB(String DrugC1, String DrugN1,String DrugTy1 ,float UnitP1,float SellP1,int Qty1,float TotP1){
        conn = DBconnect.getConnection();
        try
        {
            String sql;
            sql = "INSERT INTO paysup (DrugC,DrugN,DrugTy,UnitP,SellP,Qty,TotP)VALUES('"+DrugC1+"','"+DrugN1+"','"+DrugTy1+"','"+UnitP1+"','"+SellP1+"','"+Qty1+"','"+TotP1+"')";
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
    
     public void updateDB( int bill1,float UnitP1, float SellP1, int Qty1, float TotP1){
        conn = DBconnect.getConnection();;
        try
        {
            String sql;
            
             
            sql = "UPDATE `paysup` SET `UnitP`='"+UnitP1+"',`SellP`='"+SellP1+"',`Qty`='"+Qty1+"',`TotP`='"+TotP1+"' WHERE BiLL='"+bill1+"'";
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
     
     
     public void delete(String DrugC1){
       
        conn = DBconnect.getConnection();;
        String n = DrugC1;
        
        int y= JOptionPane.showConfirmDialog(null,"Do you want to delete this ?");
        
        if (y==0){
        
            
        String q="DELETE from paysup where DrugC = '"+DrugC1+"';";
            
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

     
     public float calsup(float upr,int qty2)
          
   {
       float sum= upr * qty2;
       return sum;
        
   }
     
     
}