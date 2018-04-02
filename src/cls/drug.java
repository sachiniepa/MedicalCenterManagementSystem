/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class drug {
   Connection conn = DBconnect.getConnection();
   PreparedStatement pst =null;
   ResultSet rs = null;
   
    private String drugCode;
    private String SupCode;
    private double Bprice;
   public drug()
   {
   }
   public void Adddrug(String a,String s,double p)
   {
       this.SupCode=s;
       this.drugCode=a;
       this.Bprice=p;
        try{
            //conn = DBconnect.getConnection();//getting the DB connection
            
            
            Statement st= conn.createStatement();
            st.executeUpdate("INSERT INTO sells(drugCode,supCode,buyPrice)"
                    + "values ('"+s+"','"+a+"','"+p+"')");//inserting a sells            
        }
        catch(Exception q){
            JOptionPane.showMessageDialog(null, q.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    
   }
   public void Updatedrug(String a,String s,double p)
   {
       this.SupCode=s;
       this.drugCode=a;
       this.Bprice=p;
        try{
            //conn = DBconnect.getConnection();//getting the DB connection
            
            
            Statement st= conn.createStatement();
            st.executeUpdate("UPDATE `sells` SET `buyPrice`='"+p+"' WHERE drugCode ='"+a+"' AND supCode='"+s+"'");//inserting a sells            
        }
        catch(Exception q){
            JOptionPane.showMessageDialog(null, q.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    
   }
    public void deleteDrug(String sc,String dc)
    {
        
        
            this.SupCode=sc;
            this.drugCode=dc;
            //String que="DELETE from su where NIC ='"+aa+"'";
            String que="DELETE FROM `sells` WHERE drugCode='"+sc+"' AND supCode='"+dc+"'";
            try {
                pst=conn.prepareStatement(que);
                pst.execute();
                
            } catch (SQLException ex) {
                Logger.getLogger(supplier.class.getName()).log(Level.SEVERE, null, ex);
            }

        
    }
    
    
    
}
