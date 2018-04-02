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
public class payDrugs {
    
    
    Connection conn = null;
    PreparedStatement pst=null;
    ResultSet rs = null;
    
    public void sendToDB(String PresC1,String DrugC1, String DrugN1,int qty1 ,float totCost1){
        conn = DBconnect.getConnection();
        try
        {
            String sql;
            sql = "INSERT INTO paydrugs (PresNo,DrugC,DrugN,TotQty,totCost)VALUES('"+PresC1+"','"+DrugC1+"','"+DrugN1+"','"+qty1+"','"+totCost1+"')";
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
    
    
      public void updateDB( String PresNo1,String DrugC1,String DrugN1, int qty1, String UnitP1,String totCost1){
        conn = DBconnect.getConnection();
        try
        {
       
            String sql;
            
             
            sql = "UPDATE payDrugs SET DrugC='"+DrugC1+"',DrugN = '"+DrugN1+"',TotQty = '"+qty1+"',totCost = '"+totCost1+"' WHERE PresNo='"+PresNo1+"'";
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
       
        conn = DBconnect.getConnection();
        String n = DrugC1;
        
        int y= JOptionPane.showConfirmDialog(null,"Do you want to delete this ?");
        
        if (y==0){
        
            
        String q="DELETE from payDrugs where DrugC = '"+DrugC1+"'";
            
        try{
            pst=conn.prepareStatement(q);
            pst.execute();
            
            
            
            }
            catch(SQLException e){
            
            JOptionPane.showMessageDialog(null,e+"Error");
            
            }
  

    
}
        
        
}
       
        
         public float caldrugs1(int qty2,float up2)
          
   {
       float sum=qty2*up2;
       return sum;
        
   }
        
        
        
}