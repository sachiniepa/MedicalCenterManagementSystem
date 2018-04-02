/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itpproject;

/**
 *
 * @author HP User
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Drugs 
{
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    String drugCode;
    String drName;
    String type;
    String supplier;
    String manuf;
    String chName;
    int mrgLevel;
    String ordCode;
    int qty;
    double unitPrice;
    String dop;
    String doe;
    String status;
    int tot;
    
    Drugs()
    {
        
    }
    
    Drugs(String drCode,String poCode,String pqty, String pdop,String pdoe, String pun)
    {
        //System.out.println("ch");
        drugCode  = drCode;
        ordCode = poCode;
        qty = Integer.parseInt(pqty);
        dop = pdop;
        doe = pdoe;        
        
        status = "Recieved";
        
        try
        {
           conn = DBconnect.getConnection();
           String q5 = "Insert into orders(orderCode,dateOfPurchase,qtyRecieved,status,dateOfExp) values('"+ordCode+"','"+dop+"','"+qty+"','"+status+"','"+doe+"')";
           pst = conn.prepareStatement(q5);
           pst.executeUpdate();
           
           JOptionPane.showMessageDialog(null, "Add to Inventory!", "Ok", JOptionPane.INFORMATION_MESSAGE);
        }
        
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    Drugs(String drCode,String cName, String dName,String ptype,String pmanuf,String poCode,String pqty,String puPrice,String pmL,String pdoe, String pun)
    {       
       /* 
       try
       {
       String q2 = "Select drugCode from Drugs";
       pst = conn.prepareStatement(q2);
       rs = pst.executeQuery();
       }
       
       catch(Exception e)
       {
       }*/
        
          //String q1 = "Insert into drug_seq(name) values('aaaa')";
          //String q2 = "Select concat('DR',lpad(LAST_INSERT_ID(),5,'0'))as drugCode from drug_seq";
          //String q3 = "Insert into drugs values(?,?,?,?,?,?,?)";
          
          
         /* try
          {
             pst = conn.prepareStatement(q1);
              pst.executeUpdate();
              Statement st = conn.createStatement();
              rs = st.executeQuery(q2);
               
             // PreparedStatement pst1 = conn.prepareStatement(q3);
              
              if(rs.next())
              {
                  drugCode = rs.getString("drugCode");
              }
 
          }
          
          catch(Exception ex)
          {
              JOptionPane.showMessageDialog(null,ex.getMessage());
          }*/
          
          drugCode = drCode;
          drName = dName;
          chName = cName;
          type = ptype;
          manuf = pmanuf;
          mrgLevel = Integer.parseInt(pmL);
          ordCode = poCode;
          qty = Integer.parseInt(pqty);
          unitPrice = Double.parseDouble(puPrice);
          doe = pdoe;        
          status = "Recieved"; 
          
          if(availability(drName)==1)
          {
              String q6 = "Select * from drugs where drugCode = '"+drugCode+"'";
             
              try{
                 conn = DBconnect.getConnection();
                 pst = conn.prepareStatement(q6);
                 rs = pst.executeQuery();
                 
                 while(rs.next())
                 {
                 tot = this.qty + Integer.parseInt(rs.getString("totQty"));
                 }
              }
              
              catch(Exception ex)
              {
                  JOptionPane.showMessageDialog(null,ex.getMessage());
              }
                   
          }
          else
          {
               tot = this.qty;
          }
        
       try
       {
          conn = DBconnect.getConnection();
          
          /*pst = conn.prepareStatement(q1);
          pst.executeUpdate();
          
          pst = conn.prepareStatement(q2);
          rs = pst.executeQuery();
          if(rs.next())
              drugCode = rs.getString("drugCode");*/
          
       /*   String q3 = "Select totQty from drugs where drugCode = '"+drugCode+"'";
          pst = conn.prepareStatement(q3);
          rs = pst.executeQuery();*/
          //System.out.println(rs);
          
          String q4 = "Insert into drugs(drugCode,chName,drugName,type,totQty,manufact,unitPrice,mrgLvl) values('"+drugCode+"','"+chName+"','"+drName+"','"+type+"','"+tot+"','"+manuf+"','"+unitPrice+"','"+mrgLevel+"')";
          pst = conn.prepareStatement(q4);
          pst.executeUpdate();
          
          String q5 = "Update orders set status = '"+status+"',qtyRecieved = '"+qty+"', dateOfExp = '"+doe+"' where orderCode = '"+ordCode+"'";
          pst = conn.prepareStatement(q5);
          pst.executeUpdate();
          
          JOptionPane.showMessageDialog(null, "Added to Inventory!", "Ok", JOptionPane.INFORMATION_MESSAGE);
       }
    
       catch(Exception ex) 
       {
          JOptionPane.showMessageDialog(null,ex.getMessage());
       }
    }
    
   
    
    public int availability(String dName)
    {
       int r = 0;
        try 
        {
            conn = DBconnect.getConnection();
            String q = "Select drugName from drugs";
            //System.out.println(q);
            pst = conn.prepareStatement(q);
            //System.out.println("*");
            rs = pst.executeQuery();
            //System.out.println(rs);
            while(rs.next())
            {
                if(rs.getString("drugName").equals(dName))
                {
                    r = 1;
                    return r;
                }
            }
        } 
        
        catch (SQLException ex) 
        {
           //System.out.println("****");
           Logger.getLogger(Drugs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
    
  //  public int calcTot(String )
    public void updateDrugs(String dCode,String cName, String dName,String ptype,String pmanuf,String uPrice,String mrgL)
    {
        drugCode = dCode;
        drName = dName;
        type = ptype;
        manuf = pmanuf;
        chName = cName;
        mrgLevel  = Integer.parseInt(mrgL);
        unitPrice = Double.parseDouble(uPrice);
    
        
        try
        {
           conn = DBconnect.getConnection();
           String q5 = "Update drugs set drugName = '"+drName+"', chName = '"+chName+"', type= '"+type+"',manufact = '"+manuf+"',unitPrice = '"+unitPrice+"',mrgLvl = '"+mrgLevel+"' where drugCode = '"+drugCode+"' ";
           pst = conn.prepareStatement(q5);
           pst.executeUpdate();
           JOptionPane.showMessageDialog(null,"Inventory Updated!");
        }
        
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
     public void updateOrder(String oCode,String pQty,String pdoe,String pdop)
    {
        System.out.println("8888oye");
       // supplier = psup;
        ordCode = oCode;
        qty = Integer.parseInt(pQty);
        dop = pdop;
        doe = pdoe;  
    
        
        try
        {
           System.out.println("8888oye");
           //System.out.println("8888");
           conn = DBconnect.getConnection();
           String q5 = "Update orders set suppName = '"+supplier+"',QtyRecieved = '"+qty+"',dateOfExp = '"+doe+"',dateOfPurchase= '"+dop+"' where orderCode = '"+oCode+"'";
           pst = conn.prepareStatement(q5);
           pst.executeUpdate();
           JOptionPane.showMessageDialog(null,"Inventory Updated!");
        }
        
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void delete(String key)
    {
       String k = key;
       String q = "Delete from drugs where drugCode = '"+k+"'";
       try
       {
           conn = DBconnect.getConnection();
           pst = conn.prepareStatement(q);
           pst.executeUpdate();
           JOptionPane.showMessageDialog(null,"Record Deleted!!");
       }
       
       catch(Exception e)
       {
           JOptionPane.showMessageDialog(null,e.getMessage());
       }
    }
    
  /*  public ResultSet getPrescriptionDrugs(String pID){
        String q1 = "SELECT d.drugName FROM buy b, drug d WHERE b.prescriptionNo = ? AND b.drugCode= d.drugCode ";
        
        try{
           conn =DBconnect.getConnection();
            pst = conn.prepareStatement(q1);
            pst.setString(1, pID);
            rs = pst.executeQuery();
            
        }
        catch(Exception e){
             JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return rs;
    }*/
    
    void filter(String query, javax.swing.JTable j)
    {
        DefaultTableModel dm = (DefaultTableModel) j.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel> (dm);
        j.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(query));
    }
}
    
    
  
    



