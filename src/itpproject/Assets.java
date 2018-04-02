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
import java.sql.Statement;
//import java.util.*;
import javax.swing.JOptionPane;
//import testhms.assetsManagemant.
/**
 *
 * @author hiranabayaratne
 */

public class Assets {
    
    private String serialNo;
    private String name;
    private String type;
    private int lifeExp;
    private String desc;
    private String manufact;
    private String supplier;
    private String purchDate;
    private String warExpDate;
    private double price;
    private int qty;
    
    Connection con=null;
    PreparedStatement pt = null;
    ResultSet rs = null;
    
    Assets(String a){
        this.serialNo = a;
    }
    
    Assets (String s, String t, String n, int l, String d, String m, String sp, String p, String w, double pr, int q)
    {
        this.serialNo=s;
        this.type=t;
        this.name=n;        
        this.lifeExp=l;
        this.desc=d;
        this.manufact=m;
        this.supplier=sp;
        this.purchDate=p;
        this.warExpDate=w;
        this.price=pr;
        this.qty=q;
        
        
       
        System.out.println("fsfs");
        try{
            con = DBconnect.getConnection();
          
            
        String query="insert into assests values (?,?,?,?,?,?,?,?,?,?,?)";     //inserting to the database
            pt= con.prepareStatement(query);
       //System.out.println("dsds");
            pt.setString(1, serialNo);
            pt.setString(2, type);
            pt.setString(3,name);
            pt.setInt(4,lifeExp);
            pt.setString(5,desc);
            pt.setString(6,manufact);
            pt.setString(7,supplier);
            pt.setString(8,purchDate);
            pt.setString(9,warExpDate);
            pt.setDouble(10,price);
            pt.setInt(11, qty);          
                        
             //System.out.println("fsdsdsdfs");          
            pt.executeUpdate();     //execution of the query
            JOptionPane.showMessageDialog(null, "Equipment added!");
        //System.out.println("fsewewewfs");
        
        
        //String q1 = "insert into assests value (?)";
        //pt = con.prepareStatement(q1);
        //pt.executeUpdate();
        
        
        
        
        }
         
      catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
         }
        
    }
    
    Assets (String s, String t, String n, String m, String sp, String p, String w, double pr, int q)
    {
        this.serialNo=s;
        this.type=t;
        this.name=n;             
        this.manufact=m;
        this.supplier=sp;
        this.purchDate=p;
        this.warExpDate=w;
        this.price=pr;        
        this.qty=q;
        
        try{
            con = DBconnect.getConnection();
            
            String query="insert into assests (serialNo,type, name, manufacturer, supplier, purchDate, warExpDate, price, qty) values (?,?,?,?,?,?,?,?,?)";     //inserting to the database
            pt= con.prepareStatement(query);
       //System.out.println("dsds");
            pt.setString(1, serialNo);
            pt.setString(2, type);
            pt.setString(3,name);
            pt.setString(6,manufact);
            pt.setString(7,supplier);
            pt.setString(8,purchDate);
            pt.setString(9,warExpDate);
            pt.setDouble(10,price);
            pt.setInt(11, qty);          
                        
             //System.out.println("fsdsdsdfs");          
            pt.executeUpdate();     //execution of the query
            
           
        //System.out.println("fsewewewfs");
            
        }
        catch (Exception e)
        {
             JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            
        }
    }
    
    
    

    
    
    public void updateEuip(String s, String t, String n, int l, String d, String m, String sp, Date sd, Date sdd,double pr, int q){
        
      
        con = DBconnect.getConnection();
        int x = JOptionPane.showConfirmDialog(null, "Do you want to update?");
        
        String sql = "update assests set type = '"+t+"' , name = '"+n+"', lifeExp = '"+l+"', description = '"+d+"', manufacturer = '"+m+"', supplier = '"+sp+"',purchDate = '"+sd+"', warExpDate = '"+sdd+"', price = '"+pr+"', qty = '"+q+"'  where serialNo = '"+ s +"' " ;
        try{
            pt = con.prepareStatement(sql);
            pt.execute();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
             
    }
    
    public void deleteEquip(){
        int p = JOptionPane.showConfirmDialog(null,"Do you really want to delete?","Delete",JOptionPane.YES_NO_OPTION);
        //asking for confirmation of the delete
        if (p== 0){
        try{
            Connection con = DBconnect.getConnection();
            String query = "select * from assests where serialNo = '"+this.serialNo+"'";
            String dquery ="DELETE FROM assests WHERE serialNo = ?";
            PreparedStatement pst1=con.prepareStatement(query);
            ResultSet rs = pst1.executeQuery();
            if(rs.next()){ //checking whether the bookID exist
                 PreparedStatement pst2=con.prepareStatement(dquery);
                 pst2.setString(1, this.serialNo);
                 pst2.executeUpdate();
                 JOptionPane.showMessageDialog(null, "Deleted!!");
            }
            else{
                JOptionPane.showMessageDialog(null, "This equipment doesn't exist!!","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
        } 
    }
   
    
    
}
