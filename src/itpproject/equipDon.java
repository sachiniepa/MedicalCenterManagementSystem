/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itpproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author hiranabayaratne
 */
public class equipDon {
    
    
    private String serialNo;
    private String name;
    private String type;
    private int lifeExp;
    private String desc;
    private String manufact;
    private String purchDate;
    private String donDate;
    private double price;
    private int qty;
    
     Connection con = null;
        PreparedStatement pt = null;
    
    equipDon(String a){
        this.serialNo = a;
    }
    
    
    equipDon (String s, String n, String t, int l, String d, String m, String p, String w, double pr, int q)
    {
        
        this.serialNo=s;
        this.name=n;
        this.type=t;
        this.lifeExp=l;
        this.desc=d;
        this.manufact=m;
        this.purchDate=p;
        this.donDate=w;
        this.price=pr;
        this.qty=q;
        
        Connection con=null;
        PreparedStatement pt = null;
        
        try{
            con = DBconnect.getConnection();
            
            
            /*Statement st= con.createStatement();
            st.executeUpdate("INSERT INTO donaequip (serialNo,type,name,lifeExpect,description,manuf,purDate,DonDate,price,qty) "+
                    "values ('"+s+"','"+n+"','"+t+"','"+l+"','"+d+ "','"+m+"','"+p+ "','"+w+ "','"+pr+ "','"+q+ "')");
            JOptionPane.showMessageDialog(null, "Equipment donated !");*/
            
            String q1 = "insert into donaequip (serialNo,type,name,lifeExpect,description,manuf,purDate,DonDate,price,qty) values ('"+s+"','"+t+"','"+n+"','"+l+"','"+d+"','"+m+"','"+p+"','"+w+"','"+pr+"','"+q+"')";
            pt = con.prepareStatement(q1);
                      
            pt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Equipment donated !");
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
        
        
    }
    
    equipDon (String s, String n, String t, String m, String p, String w, double pr, int q)
    {
        
        this.serialNo=s;
        this.name=n;
        this.type=t;     
        this.manufact=m;
        this.purchDate=p;
        this.donDate=w;
        this.price=pr;        
        this.qty=q;
        
    }

    public void deleteEquip(){
        int p = JOptionPane.showConfirmDialog(null,"Do you really want to delete?","Delete",JOptionPane.YES_NO_OPTION);
        //asking for confirmation of the delete
        if (p== 0){
        try{
            Connection con = DBconnect.getConnection();
            String query = "select * from donaequip where serialNo = '"+this.serialNo+"'";
            String dquery ="DELETE FROM donaequip WHERE serialNo = ?";
            PreparedStatement pst1=con.prepareStatement(query);
            ResultSet rs = pst1.executeQuery();
            if(rs.next()){ 
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
    
    public void updateDona (String s, String t, String n, int l, String d, String m, double pr, int q)
    {
       
        
        
        con = DBconnect.getConnection();
        int x = JOptionPane.showConfirmDialog(null, "Do you want to update?");
        
        String sql = "update donaequip set type = '"+t+"' , name = '"+n+"', lifeExpect = '"+l+"', description = '"+d+"', Manuf = '"+m+"', Price = '"+pr+"', Qty = '"+q+"'  where serialNo = '"+ s +"' " ;
        try{
            pt = con.prepareStatement(sql);
            pt.execute();
            
             JOptionPane.showMessageDialog(null, "updated!");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
}
