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
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import itpproject.DBconnect;


/**
 *
 * @author Admin
 */
public class Salary {
    private String empNo;
    private Date datepayed;
    private double bSal;
    private double otPay;
    private int leaves;
    private double otRate;
    private double lDeduc;
    private double bonus= 0;
    private float otHrs;
    private double etf;
    private double netSal;
   
    Connection conn;
    
    public Salary(){
        conn = DBconnect.getConnection();
    }
    public double getBsal(){
        return (this.bSal);
    }
    public double getOtRate(){
        return (this.otRate);
    }
    
    public ResultSet getSalaryDetails(String eN){
        ResultSet rs = null;
        String q1 = "SELECT name, position, basicSal, otRate FROM otherstaff WHERE empNo = ?";
        try{
            empNo = eN;
            PreparedStatement pst = conn.prepareStatement(q1);
            pst.setString(1, empNo);
            rs = pst.executeQuery();
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
        }
        return rs;
        
    }
    
    public double updateSal( double baSal){
        String q1 = "UPDATE otherstaff SET basicSal = ?, otRate = ? WHERE empNo = ?";
     
        bSal = baSal;
        otRate = ((baSal/25)/8)*1.5;
        try{
            PreparedStatement pst = conn.prepareStatement(q1);
            pst.setDouble(1, bSal);
            pst.setDouble(2, otRate);
            pst.setString(3, empNo);
            
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salary details of employee "+empNo+"successfully updated !");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
        }
        return otRate;
    }
    
    public void addMonthlySal(Date dp, double bs, double op,int l, float oH, double bo, double et, double ld, double ns){
        String q1 = "INSERT INTO salary VALUES(?,?,?,?,?,?,?,?,?,?)";
        datepayed = dp;
        bSal = bs;
        otPay = op;
        leaves = l;
        otHrs = oH;
        bonus = bo;
        etf = et;
        lDeduc = ld;
        netSal = ns;
        
        try{
            PreparedStatement pst = conn.prepareStatement(q1);
            pst.setDate(1, datepayed);
            pst.setString(2, empNo);
            pst.setDouble(3, bSal);
            pst.setDouble(4, leaves);
            pst.setDouble(5, bonus);
            pst.setDouble(6, otHrs);
            pst.setDouble(7, lDeduc);
            pst.setDouble(8, etf );
            pst.setDouble(9, otPay);
            pst.setDouble(10, netSal);  
            
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Monthly salary of employee "+empNo+"calculated and added to the system");
        }
        catch(Exception e ){
             JOptionPane.showMessageDialog(null, e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    public ResultSet checkPayedDate(int mon, int year){
        ResultSet rs = null;
      
        String q1 = "SELECT * FROM salary WHERE month(datePayed) = ? AND year(datePayed) = ? AND empNo = ?";
        
        try{
            PreparedStatement pst = conn.prepareStatement(q1);
            
            int mon1 = mon+1;
            
            pst.setInt(1, mon1);
            pst.setInt(2, year);
            pst.setString(3, empNo);
            
            
            rs= pst.executeQuery();
            /*if(rs.next()){
                JOptionPane.showMessageDialog(null, "Monthly salary of employee "+empNo+" for this month is already been calculated");
                return rs;
            }
            else{
                return null;
            }*/
            return rs;
        }
        catch(Exception e){
             JOptionPane.showMessageDialog(null, e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
             return null;
        }
        
        
    }
    
    public void updateMonthySal(int m,int y, double bs, double op,int l, float oH, double bo, double et, double ld, double ns){
        String q1 = "UPDATE salary "
                + "SET basicSal = ?, No_of_leaves =?, seasonalBonus = ?, OThrs = ?, leaveDed = ?, ETF = ? , OTpay = ?, netsal =?"
                + " WHERE month(datePayed) = ? AND year(datePayed) = ? AND empNo = ?";
        bSal = bs;
        otPay = op;
        leaves = l;
        otHrs = oH;
        bonus = bo;
        etf = et;
        lDeduc = ld;
        netSal = ns;
      
        try{
            PreparedStatement pst = conn.prepareStatement(q1);
            pst.setInt(9, (m+1));
            pst.setInt(10, y);
            pst.setString(11, empNo);
            pst.setDouble(1, bSal);
            pst.setDouble(2, leaves);
            pst.setDouble(3, bonus);
            pst.setDouble(4, otHrs);
            pst.setDouble(5, lDeduc);
            pst.setDouble(6, etf );
            pst.setDouble(7, otPay);
            pst.setDouble(8, netSal);  
            
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Monthly salary of employee "+empNo+" updated");
        }
        catch(Exception e){
             JOptionPane.showMessageDialog(null, e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    
    public ResultSet getMonthlySalary(String en, int m, int y){
        ResultSet rs = null;
        String q = "SELECT * FROM salary WHERE month(datePayed) = ? AND year(datePayed) = ? AND empNo = ?";
        try{
            PreparedStatement pst = conn.prepareStatement(q);
            
            int mon1 = m+1;
            
            pst.setInt(1, mon1);
            pst.setInt(2, y);
            pst.setString(3, en);
            
            rs = pst.executeQuery();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
        }
        return rs;
    }
}
