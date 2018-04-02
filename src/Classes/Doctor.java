/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class Doctor extends Employee {
    private String regNo;
    private String speciality;
    private String qualifications;
    private float fee = 0;
    
    public void insertDoctors(String n,String g,Date dob,Date doj, String a, String c, String r,
            String s, String q,float f){
        String q1 = "INSERT INTO emp_seq (name) VALUES ('aaaaa')";
        String q2 = "SELECT CONCAT('EMP', LPAD(LAST_INSERT_ID(), 3, '0')) as eid FROM emp_seq";
        String q3 = "INSERT INTO doctor VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        
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
                
                gender = g;
                dateOfbirth = dob;
                address = a;
                dateOfjoin = doj;
                contactNo = c;
                regNo = r;
                speciality = s;
                qualifications = q;
                fee = f;
                
                
                pst2.setString(1, empNo);
                pst2.setString(2, name);
                pst2.setString(3, address);
                pst2.setDate(4, dateOfbirth);
                pst2.setString(5, gender);
                pst2.setDate(6, dateOfjoin);
                pst2.setString(7, contactNo);
                pst2.setString(8, regNo);
                pst2.setString(9, speciality);
                pst2.setString(10, qualifications);
                pst2.setFloat(11, fee);
                
                pst2.execute();
                Users u = new Users();
                u.addUser(empNo, contactNo, "Doctor");
                JOptionPane.showMessageDialog(null, "1 Doctor successfully added");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
            
        }
        
    }
    
    public void insertDoctors(String n,String g,Date dob,Date doj, String a, String c, String r,String q){
        this.insertDoctors(n, g, dob, doj, a, c, r, "non", q, fee);
        
    }
    
    public void insertDoctors(String n,String g,Date dob,Date doj, String a, String c, String r,
            String s, String q){
        this.insertDoctors(n, g, dob, doj, a, c, r, s, q, fee);
    }
    
    public void updateDoctor(String en,String n,String g,Date dob,Date doj, String a, String c, String r,
            String s, String q,float f){
         String q1 = "UPDATE doctor "
                + "SET name = ?, addr = ?, Dob = ?, Gender = ?, Date_of_Join = ?, ContactNo = ?, RegNo =?, Speciality = ?, Qualifcations = ?, doctorFee = ? "
                + "WHERE empNo = ?";
        try{
            PreparedStatement pst =con.prepareStatement(q1);
            empNo = en;
            name = n;
           
            gender = g;
            dateOfbirth = dob;
            address = a;
            dateOfjoin = doj;
            contactNo = c;
            regNo =r;
            speciality = s;
            qualifications = q;
            fee = f;
            
            pst.setString(1, name);
            pst.setString(2, address);
            pst.setDate(3, dateOfbirth);
            pst.setString(4, gender);
            pst.setDate(5, dateOfjoin);
            pst.setString(6, contactNo);
            pst.setString(7, regNo);
            pst.setString(8, speciality);
            pst.setString(9, qualifications);
            pst.setFloat(10, fee);
            pst.setString(11, empNo);
            
            
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Doctor "+empNo+" successfully updated");
            
            
        }
        catch(Exception e){
             JOptionPane.showMessageDialog(null, e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void deleteDoc(String empN){
        String delQuery = "DELETE FROM doctor WHERE empNo = ?";
         int p = JOptionPane.showConfirmDialog(null,"Do you really want to delete Doctor "+empN+ " ?","Delete",JOptionPane.YES_NO_OPTION);
        //asking for confirmation of the delete
        if (p== 0){
            try{
                 PreparedStatement pst =con.prepareStatement(delQuery);
                 empNo = empN;
                 pst.setString(1, empNo);
                 Users u = new Users();
                 u.deleteUser(empN);
                 pst.execute();
                 
                 JOptionPane.showMessageDialog(null, "Doctor "+empNo+" deleted!");
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
}
