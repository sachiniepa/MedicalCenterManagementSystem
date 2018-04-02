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
public class supplier {
   Connection conn = DBconnect.getConnection();
   PreparedStatement pst =null;
   ResultSet rs = null;
   
    private String supcode;
    private String name;
    private  String address;
    private String email;
    private String tell;
    private String fax;
    private Date jdate;
    private String rating;
    private String remark;

    public supplier()
    {
    
    }
    public supplier (String n, String a, String e, String t, String f, Date j, String r, String re)
    {
        //this.supcode = sc;
        this.name = n;
        this.address = a;
        this.email = e;
        this.tell = t;
        this.fax = f;
        this.jdate = j;
        this.rating = r;
        this.remark = re;
        
        
        
        try{
            //conn = DBconnect.getConnection();//getting the DB connection
            
            
            Statement s= conn.createStatement();
            s.executeUpdate("INSERT INTO supplier (name,SupplierAddress,SupplierEmail,SupplierTelphoneNo,Fax,DateOfJoin,rating,remark) "
                    + "values ('"+n+"','"+a+"','"+e+"','"+t+"','"+f+ "','"+j+"','"+r+"','"+re+"')");//inserting a book
            JOptionPane.showMessageDialog(null, "Succesfuly Added !!");
            
        }
        catch(Exception q){
            JOptionPane.showMessageDialog(null, q.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
        
        
    }
    
 
    
    public void editSupplier(String sn,String sa,String se,String st,String sf,Date sj,String sr,String rm,String sc)
    {   
        this.supcode=sc;
        this.name=sn;
        this.address=sa;
        this.email=se;
        this.tell=st;
        this.fax=sf;
        this.jdate=sj;
        this.rating=sr;
        this.remark=rm;
        String q1= "UPDATE supplier SET name='"+sn+"',SupplierAddress='"+sa+"',SupplierEmail='"+se+"',SupplierTelphoneNo='"+st+"',Fax='"+sf+"',DateOfJoin='"+sj+"',rating='"+sr+"',remark='"+rm+"' where supCode='"+sc+"'";
            try {
                pst = conn.prepareStatement(q1);
                pst.execute();
            } catch (SQLException ex) {
                Logger.getLogger(supplier.class.getName()).log(Level.SEVERE, null, ex);
            } 
    }
    public void deleteSupplier(String sc)
    {
        
        
            this.supcode=sc;
            //String que="DELETE from su where NIC ='"+aa+"'";
            String que="DELETE FROM supplier WHERE  supCode='"+sc+"'";
            try {
                pst=conn.prepareStatement(que);
                pst.execute();
                
            } catch (SQLException ex) {
                Logger.getLogger(supplier.class.getName()).log(Level.SEVERE, null, ex);
            }

        
    }
   
   
        
}
