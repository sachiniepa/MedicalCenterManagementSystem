/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cls;

import java.sql.Connection;
import java.sql.Date;
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
public class Order {
   Connection conn = DBconnect.getConnection();
   PreparedStatement pst =null;
   ResultSet rs = null;
    public Order()
    {
    
    }
    private int orderCode;
    private String drugCode;
    private String drugName;
    private String drugType;
    private int OrderQu;
    private String Supplier;
    private Date DateToS;
       public void AddOrder(String a,int d,String e,Date f)
   {
       this.DateToS = f;
       this.OrderQu = d;
       this.drugCode=e;
       this.Supplier=a;
      
        try{
            //conn = DBconnect.getConnection();//getting the DB connection
            
            //JOptionPane.showMessageDialog(null,"baaaaa","Error",JOptionPane.ERROR_MESSAGE);
            Statement st= conn.createStatement();
            st.executeUpdate("INSERT INTO `orders`(`dateOfPurchase`, `qtyOrdered`,`drugCode`, `suppName`) values ('"+f+"','"+d+"','"+e+"','"+a+"')");//inserting a sells   
            
        }
        catch(Exception q){
            JOptionPane.showMessageDialog(null, q.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    
   }
        public void editOrder(int sc,int d,String e,Date f)
    {   
      this.orderCode=sc;  
      this.OrderQu=d;
      this.Supplier=e;
      this.DateToS=f;
        String q1= "UPDATE `orders` SET `qtyOrdered`='"+d+"',`suppCode`='"+e+"',`dateOfPurchase`='"+f+"' where orderCode='"+sc+"'";
            try {
                pst = conn.prepareStatement(q1);
                pst.execute();
            } catch (SQLException ex) {
                Logger.getLogger(supplier.class.getName()).log(Level.SEVERE, null, ex);
            } 
    }
        public void deleteSupplier(int sc)
    {
        
        
            this.orderCode=sc;
            //String que="DELETE from su where NIC ='"+aa+"'";
            String que="DELETE FROM `orders` WHERE  orderCode='"+sc+"'";
            try {
                pst=conn.prepareStatement(que);
                pst.execute();
                
            } catch (SQLException ex) {
                Logger.getLogger(supplier.class.getName()).log(Level.SEVERE, null, ex);
            }

        
    }
}
