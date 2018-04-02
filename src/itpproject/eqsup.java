/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itpproject;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author hiranabayaratne
 */
public class eqsup {
    
   private String supid;
   private String name;
   private String address;
   private int telno;
   private String email;
   private String doj;
   private String remarks;
   
   Connection con = null;
       PreparedStatement pt = null;
       ResultSet rs = null;
       
       eqsup(String s){
        this.supid = s;
    }
   
   eqsup (String s, String n, String a, int t, String e, String d, String r)
   {
       this.supid = s;
       this.name = n;
       this.address = a;
       this.telno = t;
       this.email = e;
       this.doj = d;
       this.remarks = r;
       
       
       
       try{
       con = DBconnect.getConnection();
       String query="insert into eqsupplier values (?,?,?,?,?,?,?)";
       
        pt= con.prepareStatement(query);
      
        pt.setString(1, supid);
        pt.setString(2, name);
        pt.setString(3,address);
        pt.setInt(4,telno);
        pt.setString(5,email);
        pt.setString(6,doj);
        pt.setString(7,remarks);
        
        pt.executeUpdate();     //execution of the query
            JOptionPane.showMessageDialog(null, "Supplier added!");
            
       
       }
       catch(Exception ex)
       {
           JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
       }
       
   }
   
   public void updateSup(String s, String n, String a, int t, String e, Date d, String r){
        
      
        con = DBconnect.getConnection();
        int x = JOptionPane.showConfirmDialog(null, "Do you want to update?");
        
        //String sql = "update eqsupplier set name = '"+n+"', address = '"+a+"', telno = '"+t+"', email = '"+e+"', doj = '"+d+"' remarks = '"+r+"' where supid = '"+s+"' " ;
        
        String q2 = "update eqsupplier set name='"+n+"', address='"+a+"', telno='"+t+"',email='"+e+"', DOJ='"+d+"', remarks='"+r+"' where supid='"+s+"' ";
        
        try{
            pt = con.prepareStatement(q2);
            pt.execute();
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
             
    }
   
   public void deleteSup(){
        int p = JOptionPane.showConfirmDialog(null,"Do you really want to delete?","Delete",JOptionPane.YES_NO_OPTION);
        //asking for confirmation of the delete
        if (p== 0){
        try{
            Connection con = DBconnect.getConnection();
            String query = "select * from eqsupplier where supid = '"+this.supid+"'";
            String dquery ="DELETE FROM eqsupplier WHERE supid = ?";
            PreparedStatement pst1=con.prepareStatement(query);
            ResultSet rs = pst1.executeQuery();
            if(rs.next()){ //checking whether the bookID exist
                 PreparedStatement pst2=con.prepareStatement(dquery);
                 pst2.setString(1, this.supid);
                 pst2.executeUpdate();
                 JOptionPane.showMessageDialog(null, "Deleted!!");
            }
            else{
                JOptionPane.showMessageDialog(null, "This supplier doesn't exist!!","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
        } 
    }
   
    
}
