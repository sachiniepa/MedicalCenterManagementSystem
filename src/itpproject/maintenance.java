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
import javax.swing.JOptionPane;

/**
 *
 * @author hiranabayaratne
 */
public class maintenance {
    
    private int taskNo;
    private String serialNo;
    private String desc;
    private String assignedTo;
    private String assignedDate;
    private String compDate;
    private double productCost;
    private double servCost;
    private String remarks;
 
    Connection con = null;
    PreparedStatement pt = null;
    
    maintenance (String s)
    {
       this.serialNo = s;
        
    }
    
    
    
    maintenance (String s,String d, String at, String ad, String cd, double pc, double sc, String r )
    {
        
        this.serialNo = s;
        this.desc = d;
        this.assignedTo = at;
        this.assignedDate = ad;
        this.compDate = cd;
        this.productCost = pc;
        this.servCost = sc;
        this.remarks = r;
        
        Connection con=null;
        
        try{
            con = DBconnect.getConnection();
            
            
           String q1 = "insert into maintainance (serialNo,description,assignedTo,assignedDate,compDate,proCost,serviceCost,remarks) values ('"+s+"','"+d+"','"+at+"','"+ad+"','"+cd+"','"+pc+"','"+sc+"','"+r+"') ";
            pt = con.prepareStatement(q1);
                      
            pt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Equipment repaired !");
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
        
        
    }
    
    maintenance (int t, String s, String d, String at, String ad, String cd )
    {
        this.taskNo = t;
        this.serialNo = s;
        this.desc = d;
        this.assignedTo = at;
        this.assignedDate = ad;
        this.compDate = cd;
        
        
    }
    
    public void updateMain (String s,String d, String at,Date ad, Date cd, double pc, double sc, String r)
    {
        con = DBconnect.getConnection();
        
        int x = JOptionPane.showConfirmDialog(null, "Do you want to update?");
        
        String q1 = "update maintainance set description='"+s+"', assignedTo='"+at+"', assignedDate='"+ad+"', compDate='"+cd+"', proCost='"+pc+"', serviceCost='"+sc+"', remarks='"+r+"' where serialNo='"+s+"' ";
      
         /*String q1 = "UPDATE maintainance "
                + "SET description = ?, assignedTo = ?, assignedDate = ?, compDate = ?, proCost = ?, serviceCost = ?, remarks =? "
                + "WHERE serialNo = ?";*/
        
        try{
            
             pt = con.prepareStatement(q1);
             
            /*pt.setString(1, s);
            pt.setString(2, d);
            pt.setString(3, at);
            pt.setDate(4, ad);
            pt.setDate(5, cd);
            pt.setDouble(6, pc);
            pt.setDouble(7, sc);
            pt.setString(8, r);*/
           
            pt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "updated!");
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
            String query = "select * from maintainance where serialNo = '"+this.serialNo+"'";
            String dquery ="DELETE FROM maintainance WHERE serialNo = ?";
            PreparedStatement pst1=con.prepareStatement(query);
            ResultSet rs = pst1.executeQuery();
            if(rs.next()){ 
                 PreparedStatement pst2=con.prepareStatement(dquery);
                 pst2.setString(1, this.serialNo);
                 pst2.executeUpdate();
                 JOptionPane.showMessageDialog(null, "Deleted!!");
            }
            else{
                JOptionPane.showMessageDialog(null, "This record doesn't exist!!","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
        }
    }
   
   public void removeEquip(){
       String r = "removed";
        int p = JOptionPane.showConfirmDialog(null,"Do you really want to delete?","Delete",JOptionPane.YES_NO_OPTION);
        //asking for confirmation of the delete
        if (p== 0){
        try{
            Connection con = DBconnect.getConnection();
            String query = "select * from assests where serialNo = '"+this.serialNo+"'";
            String dquery ="DELETE FROM assests WHERE serialNo = ?";
            PreparedStatement pst1=con.prepareStatement(query);
            ResultSet rs = pst1.executeQuery();
            if(rs.next()){ 
                 PreparedStatement pst2=con.prepareStatement(dquery);
                 pst2.setString(1, this.serialNo);
                 pst2.executeUpdate();
                 JOptionPane.showMessageDialog(null, "Deleted!!");
            }
            else{
                JOptionPane.showMessageDialog(null, "This record doesn't exist!!","Error",JOptionPane.ERROR_MESSAGE);
            }
            
            String sql = "update maintainance set remarks='"+r+"' where serialNo = '"+this.serialNo+"'";
            pt = con.prepareStatement(sql);
            pt.executeUpdate();
            
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
        }
    }
    
    }


   
    
    
    
    
