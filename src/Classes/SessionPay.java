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
import javax.swing.JOptionPane;
import itpproject.DBconnect;

/**
 *
 * @author Admin
 */
public class SessionPay {
    private String sessionId;
    private String doctor;
    private String doctorId;
    private String time;
    private Date date;
    private double docCharge;
    Connection conn;
    
    public SessionPay(){
         conn = DBconnect.getConnection();
    }
    
    public ResultSet getSessionDetails(String sID, String dID){
        String q1 ="SELECT s.date, s.startTime as time , d.doctorFee , COUNT(a.apRefNo) as count FROM session s, appointment a , doctor d WHERE s.sessioNID = a.sessionId AND s.empNo = d.empNo AND a.sessionId= ? AND d.empNo = ?";
        ResultSet rs = null;
        try{
            PreparedStatement pst = conn.prepareStatement(q1);
            pst.setString(1, sID);
            pst.setString(2, dID);
            rs = pst.executeQuery();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
        }
        return rs;
    }
    
    public void storePay(String sID, double pay){
        String q1 = "UPDATE session SET fee =? WHERE sessionID = ?";
        
        try{
           PreparedStatement pst = conn.prepareStatement(q1);
           pst.setDouble(1, pay);
           pst.setString(2, sID);
           pst.executeUpdate();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
