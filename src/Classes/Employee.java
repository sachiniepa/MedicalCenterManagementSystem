/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import itpproject.DBconnect;

/**
 *
 * @author Admin
 */
public class Employee {
    String empNo;
    String NIC;
    String gender;
    String name;
    String position;
    Date dateOfbirth;
    Date dateOfjoin;
    String address;
    String contactNo;
    Connection con;
    
    public Employee(){
        con = DBconnect.getConnection();
    }
     
    public void insertEmployee(String n,String nic,String g, String p, Date dob,Date doj, String a, String c ){
        String q1 = "INSERT INTO emp_seq (name) VALUES ('aaaaa')";
        String q2 = "SELECT CONCAT('EMP', LPAD(LAST_INSERT_ID(), 3, '0')) as eid FROM emp_seq";
        String q3 = "INSERT INTO otherstaff(empNo,NICNo, name, position, Dob, Gender, Date_of_Join, ContactNo, address) VALUES(?,?,?,?,?,?,?,?,?)";
        
        try{
               PreparedStatement pst =con.prepareStatement(q1);
                pst.executeUpdate();
            
               Statement st = con.createStatement();
               ResultSet rs = st.executeQuery(q2);
               
               PreparedStatement pst2 =con.prepareStatement(q3);
               
               
                if(rs.next()){
                     empNo = rs.getString("eid");
                }
                name = n;
                NIC = nic;
                position = p;
                gender = g;
                dateOfbirth = dob;
                address = a;
                dateOfjoin = doj;
                contactNo = c;
                
                
                pst2.setString(1, empNo);
                pst2.setString(2, NIC);
                pst2.setString(3, name);
                pst2.setString(4, position);
                pst2.setDate(5, dateOfbirth);
                pst2.setString(6, gender);
                pst2.setDate(7, dateOfjoin);
                pst2.setString(8, contactNo);
                pst2.setString(9, address);
                
                pst2.execute();
                
                Users u = new Users();
                u.addUser(empNo, NIC, position);
                JOptionPane.showMessageDialog(null, "1 Employee successfully added");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
            
        }
        
    }
    
    public void updateEmployee(String en,String nic,String n,String g, String p, Date dob,Date doj, String a, String c){
        String q1 = "UPDATE otherstaff "
                + "SET name = ?,NICNo = ? ,position = ?, Dob = ?, Gender = ?, Date_of_Join = ?, ContactNo = ?, address =? "
                + "WHERE empNo = ?";
        try{
            PreparedStatement pst =con.prepareStatement(q1);
            empNo = en;
            NIC = nic;
            name = n;
            position = p;
            gender = g;
            dateOfbirth = dob;
            address = a;
            dateOfjoin = doj;
            contactNo = c;
            
            
            pst.setString(1, name);
            pst.setString(2, NIC);
            pst.setString(3, position);
            pst.setDate(4, dateOfbirth);
            pst.setString(5, gender);
            pst.setDate(6, dateOfjoin);
            pst.setString(7, contactNo);
            pst.setString(8, address);
            pst.setString(9, empNo);
            
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Employee "+empNo+" successfully updated");
            
            
        }
        catch(Exception e){
             JOptionPane.showMessageDialog(null, e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    public void deleteEmployee(String empN){
        String delQuery = "DELETE FROM otherstaff WHERE empNo = ?";
         int p = JOptionPane.showConfirmDialog(null,"Do you really want to delete Employee "+empN+ " ?","Delete",JOptionPane.YES_NO_OPTION);
        //asking for confirmation of the delete
        if (p== 0){
            try{
                 PreparedStatement pst =con.prepareStatement(delQuery);
                 empNo = empN;
                 pst.setString(1, empNo);
                 pst.execute();
                 Users u = new Users();
                 u.deleteUser(empN);
                 JOptionPane.showMessageDialog(null, "Employee "+empNo+" deleted !");
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
            
    
    
}
