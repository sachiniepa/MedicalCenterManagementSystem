/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itpproject;

import cls.emailValidation;
import cls.supplier;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JSpinner;
import javax.swing.RowFilter;
import javax.swing.SpinnerDateModel;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;






/**
 *
 * @author Admin
 */
public class appointments extends javax.swing.JInternalFrame {
    ResultSet rs =null;
     ResultSet rs2 =null;
    DefaultTableModel dm;
    float hospitalCharges=400;

    /**
     * Creates new form appointments
     */
    public appointments() throws SQLException {
        initComponents();
      w.setSelected(true);
 
      ResultSet rs=Session.fillCombo();
     // ResultSet rs2=Session.fillNameCombo();
      
        spcombo1.addItem("--SelectAll--");
        spcombo21.addItem("--SelectAll--");
        spcombo23.addItem("--SelectAll--");
        spcombo22.addItem("--SelectAll--");
        spcombo24.addItem("--SelectAll--");
         
         //dnamecombo1.addItem("None");
        while(rs.next()){
                String Speciality=rs.getString("Speciality");
                spcombo1.addItem(Speciality);
                spcombo21.addItem(Speciality);
                spcombo22.addItem(Speciality);
                spcombo23.addItem(Speciality);
                spcombo24.addItem(Speciality);
              // jComboBox1.addItem(Speciality);
               jComboBox2.addItem(Speciality);
            }
       
        sessionTableload(jTable2);
        patientTableLoad(jTable5);
        patientTableLoad(jTable16);
        
        tableload(jTable7);
        tableload(jTable8);
        tableload(jTable11);
        tableload(jTable12);
        
        sessionVisibility();
        
       
     
       
    }
    void sessionVisibility(){
        spinnerS1.setVisible(false);
        spinnerE1.setVisible(false);
        appCount1.setVisible(false);
        
        spinnerS2.setVisible(false);
        spinnerE2.setVisible(false);
        appCount2.setVisible(false);
        
        spinnerS3.setVisible(false);
        spinnerE3.setVisible(false);
        appCount3.setVisible(false);
        
        spinnerS4.setVisible(false);
        spinnerE4.setVisible(false);
        appCount4.setVisible(false);
        
        spinnerS5.setVisible(false);
        spinnerE5.setVisible(false);
        appCount5.setVisible(false);
        
        spinnerS6.setVisible(false);
        spinnerE6.setVisible(false);
        appCount6.setVisible(false);
        
        spinnerS7.setVisible(false);
        spinnerE7.setVisible(false);
        appCount7.setVisible(false);
        
        java.util.Calendar cals1 = Calendar.getInstance();
        java.util.Date utilDateS1 = new java.util.Date(); // your util date
        cals1.setTime(utilDateS1);

        cals1.set(Calendar.HOUR_OF_DAY, 0);
        cals1.set(Calendar.MINUTE,0);

        java.sql.Date sTime = new java.sql.Date(cals1.getTime().getTime()); // your sql date

        spinnerS1.setValue(sTime);
        spinnerE1.setValue(sTime);
        appCount1.setValue(0);
        
        spinnerS2.setValue(sTime);
        spinnerE2.setValue(sTime);
        appCount2.setValue(0);
        
        spinnerS3.setValue(sTime);
        spinnerE3.setValue(sTime);
        appCount3.setValue(0);
        
        spinnerS4.setValue(sTime);
        spinnerE4.setValue(sTime);
        appCount4.setValue(0);
        
        spinnerS5.setValue(sTime);
        spinnerE5.setValue(sTime);
        appCount5.setValue(0);
        
        spinnerS6.setValue(sTime);
        spinnerE6.setValue(sTime);
        appCount6.setValue(0);
        
        spinnerS7.setValue(sTime);
        spinnerE7.setValue(sTime);
        appCount7.setValue(0);
        
    }
     void fillDateCombo(String empNo){
        PreparedStatement pst= null;
        ResultSet rs = null;
        Connection con=null;
        try{
            con=DBconnect.getConnection();
            String query="select distinct date from session where status='Available' AND date >= CURDATE() and  empNo=? order by date " ;
            pst=con.prepareStatement(query); 
            pst.setString(1,empNo);
           
            rs=pst.executeQuery();
            while(rs.next()){
                String date=rs.getString("date");
                dateCombo1.addItem(date);
                dateCombo2.addItem(date);
           

        } }
        catch (SQLException ex) {
            Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
        }
        
   }
    void fillTimeCombo(String empNo,Date date,javax.swing.JComboBox c){
        PreparedStatement pst= null;
        ResultSet rs = null;
        Connection con=null;
        try{
            con=DBconnect.getConnection();
            String query="select startTime from session  where status='Available' AND date >= CURDATE() and  empNo = ? and date = ? order by date";
            pst=con.prepareStatement(query); 
            pst.setString(1,empNo);
            pst.setDate(2,date);
           
            rs=pst.executeQuery();
            while(rs.next()){
                String time=rs.getString("startTime");
                c.addItem(time);
                
           

        } }
        catch (SQLException ex) {
            Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
        }
        
   }
     void filldocNameCombo(String sp,javax.swing.JComboBox c){
        PreparedStatement pst= null;
        ResultSet rs = null;
        Connection con=null;
        try{
            con=DBconnect.getConnection();
            String query="select name from doctor where speciality=?";
            pst=con.prepareStatement(query); 
            pst.setString(1,sp);
           
           
            rs=pst.executeQuery();
            while(rs.next()){
                String name=rs.getString("name");
                c.addItem(name);


        } }
        catch (SQLException ex) {
            Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
        }
        
   }
 
    
   
 void filter( String query,javax.swing.JTable j){
        DefaultTableModel dm = (DefaultTableModel) j.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel> (dm);
        j.setRowSorter(tr);
        
        tr.setRowFilter(RowFilter.regexFilter(query));
        
    }
 void reloadNewApp(){
 
         sessionTableload(jTable2);
         patientTableLoad(jTable5);
       
         docSearch.setText("");
         jTextField2.setText("");
         p.setSelected(false);
         w.setSelected(true);
        
         
         spcombo1.setSelectedIndex(0);
         jLabel105.setText("");
         jLabel37.setText("");
         jLabel55.setText("");
         jLabel106.setText("");
         jLabel109.setText("");
         java.util.Date date= new java.util.Date();
       //  fcal.setDate(date);
       //  tcal.setCalendar(null);
        // sourceText.setVisible(false);
         
//***************************************************************************         
      //   dm=(DefaultTableModel) jTable5.getModel();
       //  dm.setRowCount(0);
         dm=(DefaultTableModel) jTable6.getModel();
         dm.setRowCount(0);
 
 }
 void reloadCreateSes(){
        tableload(jTable7);
        docSearch1.setText("");
        spcombo21.setSelectedIndex(0);
        sDate.setCalendar(null);
        String query="select empNo,name,Speciality,Gender  from doctor where Speciality=?";
        spsearch("--SelectAll--",jTable7,query);
    
        denyTelCheck2.setSelected(false);
        denyTelCheck1.setSelected(false);
        sun.setSelected(false);
        mon.setSelected(false);
        tue.setSelected(false);
        wed.setSelected(false);
        thu.setSelected(false);
        fri.setSelected(false);
        sat.setSelected(false);
        eDate1.setCalendar(null);
        sDate1.setCalendar(null);
        sessionVisibility();
         
        java.util.Calendar cals1 = Calendar.getInstance();
        java.util.Date utilDateS1 = new java.util.Date(); // your util date
        cals1.setTime(utilDateS1);

        cals1.set(Calendar.HOUR_OF_DAY, 0);
        cals1.set(Calendar.MINUTE,0);

        java.sql.Date sTime = new java.sql.Date(cals1.getTime().getTime()); // your sql date

        spinnerS.setValue(sTime);
        spinnerE.setValue(sTime);
        jSpinner2.setValue(0);
        dm=(DefaultTableModel) jTable4.getModel();
        dm.setRowCount(0);
 }
      
  void reloadCancelApp(){
      
        sessionTableload(jTable2);
        patientTableLoad(jTable16);
       
         jTextField28.setText("");
         jTextField29.setText("");
         jTextField30.setText("");
         p1.setSelected(false);
         w1.setSelected(true);
        
         
         
         jLabel119.setText("");
         jLabel120.setText("");
         jLabel54.setText("");
         jLabel123.setText("");
         jLabel73.setText("");
        
//***************************************************************************         
        dm=(DefaultTableModel) jTable17.getModel();
        dm.setRowCount(0);
         dm=(DefaultTableModel) jTable18.getModel();
         dm.setRowCount(0);
 
 }
             
            
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton5 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        sourceR = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel47 = new javax.swing.JPanel();
        jPanel48 = new javax.swing.JPanel();
        jLabel100 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        spcombo1 = new javax.swing.JComboBox<>();
        docSearch = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jDateChooser5 = new com.toedter.calendar.JDateChooser();
        jDateChooser6 = new com.toedter.calendar.JDateChooser();
        jButton33 = new javax.swing.JButton();
        jScrollPane25 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel102 = new javax.swing.JLabel();
        jScrollPane26 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jLabel103 = new javax.swing.JLabel();
        jButton44 = new javax.swing.JButton();
        jButton45 = new javax.swing.JButton();
        jPanel24 = new javax.swing.JPanel();
        jScrollPane27 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jLabel104 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jButton18 = new javax.swing.JButton();
        w = new javax.swing.JRadioButton();
        p = new javax.swing.JRadioButton();
        jTextField2 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jTextField27 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel110 = new javax.swing.JLabel();
        patSearch1 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jTextField28 = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jTextField29 = new javax.swing.JTextField();
        jScrollPane28 = new javax.swing.JScrollPane();
        jTable16 = new javax.swing.JTable();
        jScrollPane29 = new javax.swing.JScrollPane();
        jTable17 = new javax.swing.JTable();
        jLabel113 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel118 = new javax.swing.JLabel();
        jLabel119 = new javax.swing.JLabel();
        jButton47 = new javax.swing.JButton();
        jButton46 = new javax.swing.JButton();
        jLabel117 = new javax.swing.JLabel();
        jLabel120 = new javax.swing.JLabel();
        jLabel121 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable18 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton48 = new javax.swing.JButton();
        jLabel122 = new javax.swing.JLabel();
        jLabel123 = new javax.swing.JLabel();
        jLabel124 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        w1 = new javax.swing.JRadioButton();
        p1 = new javax.swing.JRadioButton();
        jLabel36 = new javax.swing.JLabel();
        jTextField30 = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        spcombo21 = new javax.swing.JComboBox<>();
        jButton7 = new javax.swing.JButton();
        jLabel68 = new javax.swing.JLabel();
        docSearch1 = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel34 = new javax.swing.JLabel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        jLabel87 = new javax.swing.JLabel();
        sDate1 = new com.toedter.calendar.JDateChooser();
        jLabel88 = new javax.swing.JLabel();
        eDate1 = new com.toedter.calendar.JDateChooser();
        jPanel18 = new javax.swing.JPanel();
        sun = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        mon = new javax.swing.JCheckBox();
        tue = new javax.swing.JCheckBox();
        wed = new javax.swing.JCheckBox();
        thu = new javax.swing.JCheckBox();
        fri = new javax.swing.JCheckBox();
        sat = new javax.swing.JCheckBox();
        java.util.Calendar cals1 = Calendar.getInstance();
        java.util.Date utilDateS1 = new java.util.Date(); // your util date
        cals1.setTime(utilDateS1);

        cals1.set(Calendar.HOUR_OF_DAY, 0);
        cals1.set(Calendar.MINUTE,0);

        java.sql.Date sTimeS1 = new java.sql.Date(cals1.getTime().getTime()); // your sql date

        cals1.set(Calendar.HOUR, 23);
        cals1.set(Calendar.MINUTE,59);

        java.sql.Date eTimeS1 = new java.sql.Date(cals1.getTime().getTime());
        SpinnerDateModel smS1 = new SpinnerDateModel(sTimeS1, null,eTimeS1, Calendar.MINUTE);
        spinnerS1 = new javax.swing.JSpinner(smS1);
        java.util.Calendar calE1 = Calendar.getInstance();
        java.util.Date utilDateE1 = new java.util.Date(); // your util date
        calE1.setTime(utilDateE1);

        calE1.set(Calendar.HOUR_OF_DAY, 0);
        calE1.set(Calendar.MINUTE,0);

        java.sql.Date sTimeE1 = new java.sql.Date(calE1.getTime().getTime()); // your sql date

        calE1.set(Calendar.HOUR, 23);
        calE1.set(Calendar.MINUTE,59);

        java.sql.Date eTimeE1 = new java.sql.Date(calE1.getTime().getTime());
        SpinnerDateModel smE1 = new SpinnerDateModel(sTimeE1, null,eTimeE1, Calendar.MINUTE);
        spinnerE1 = new javax.swing.JSpinner(smE1);
        appCount1 = new javax.swing.JSpinner();
        java.util.Calendar calS2 = Calendar.getInstance();
        java.util.Date utilDateS2 = new java.util.Date(); // your util date
        calS2.setTime(utilDateS2);

        calS2.set(Calendar.HOUR_OF_DAY, 0);
        calS2.set(Calendar.MINUTE,0);

        java.sql.Date sTimeS2 = new java.sql.Date(calS2.getTime().getTime()); // your sql date

        calS2.set(Calendar.HOUR, 23);
        calS2.set(Calendar.MINUTE,59);

        java.sql.Date eTimeS2 = new java.sql.Date(calS2.getTime().getTime());
        SpinnerDateModel smS2 = new SpinnerDateModel(sTimeS2, null,eTimeS2, Calendar.MINUTE);
        spinnerS2 = new javax.swing.JSpinner(smS2);
        java.util.Calendar calE2 = Calendar.getInstance();
        java.util.Date utilDateE2 = new java.util.Date(); // your util date
        calE2.setTime(utilDateE2);

        calE2.set(Calendar.HOUR_OF_DAY, 0);
        calE2.set(Calendar.MINUTE,0);

        java.sql.Date sTimeE2 = new java.sql.Date(calE2.getTime().getTime()); // your sql date

        calE2.set(Calendar.HOUR, 23);
        calE2.set(Calendar.MINUTE,59);

        java.sql.Date eTimeE2 = new java.sql.Date(calE2.getTime().getTime());
        SpinnerDateModel smE2 = new SpinnerDateModel(sTimeE2, null,eTimeE2, Calendar.MINUTE);
        spinnerE2 = new javax.swing.JSpinner(smE2);
        appCount2 = new javax.swing.JSpinner();
        java.util.Calendar calS3 = Calendar.getInstance();
        java.util.Date utilDateS3 = new java.util.Date(); // your util date
        calS3.setTime(utilDateS3);

        calS3.set(Calendar.HOUR_OF_DAY, 0);
        calS3.set(Calendar.MINUTE,0);

        java.sql.Date sTimeS3 = new java.sql.Date(calS3.getTime().getTime()); // your sql date

        calS3.set(Calendar.HOUR, 23);
        calS3.set(Calendar.MINUTE,59);

        java.sql.Date eTimeS3= new java.sql.Date(calS3.getTime().getTime());
        SpinnerDateModel smS3 = new SpinnerDateModel(sTimeS3, null,eTimeS3, Calendar.MINUTE);
        spinnerS3 = new javax.swing.JSpinner(smS3);
        java.util.Calendar calE3 = Calendar.getInstance();
        java.util.Date utilDateE3 = new java.util.Date(); // your util date
        calE3.setTime(utilDateE3);

        calE3.set(Calendar.HOUR_OF_DAY, 0);
        calE3.set(Calendar.MINUTE,0);

        java.sql.Date sTimeE3 = new java.sql.Date(calE3.getTime().getTime()); // your sql date

        calE3.set(Calendar.HOUR, 23);
        calE3.set(Calendar.MINUTE,59);

        java.sql.Date eTimeE3 = new java.sql.Date(calE3.getTime().getTime());
        SpinnerDateModel smE3 = new SpinnerDateModel(sTimeE3, null,eTimeE3, Calendar.MINUTE);
        spinnerE3 = new javax.swing.JSpinner(smE3);
        appCount3 = new javax.swing.JSpinner();
        java.util.Calendar calS4 = Calendar.getInstance();
        java.util.Date utilDateS4 = new java.util.Date(); // your util date
        calS4.setTime(utilDateS4);

        calS4.set(Calendar.HOUR_OF_DAY, 0);
        calS4.set(Calendar.MINUTE,0);

        java.sql.Date sTimeS4 = new java.sql.Date(calS4.getTime().getTime()); // your sql date

        calS4.set(Calendar.HOUR, 23);
        calS4.set(Calendar.MINUTE,59);

        java.sql.Date eTimeS4 = new java.sql.Date(calS4.getTime().getTime());
        SpinnerDateModel smS4 = new SpinnerDateModel(sTimeS4, null,eTimeS4, Calendar.MINUTE);
        spinnerS4 = new javax.swing.JSpinner(smS4);
        java.util.Calendar calE4 = Calendar.getInstance();
        java.util.Date utilDateE4 = new java.util.Date(); // your util date
        calE4.setTime(utilDateE4);

        calE4.set(Calendar.HOUR_OF_DAY, 0);
        calE4.set(Calendar.MINUTE,0);

        java.sql.Date sTimeE4 = new java.sql.Date(calE4.getTime().getTime()); // your sql date

        calE4.set(Calendar.HOUR, 23);
        calE4.set(Calendar.MINUTE,59);

        java.sql.Date eTimeE4 = new java.sql.Date(calE4.getTime().getTime());
        SpinnerDateModel smE4 = new SpinnerDateModel(sTimeE4, null,eTimeE4, Calendar.MINUTE);
        spinnerE4 = new javax.swing.JSpinner(smE4);
        appCount4 = new javax.swing.JSpinner();
        java.util.Calendar calS5 = Calendar.getInstance();
        java.util.Date utilDateS5 = new java.util.Date(); // your util date
        calS5.setTime(utilDateS5);

        calS5.set(Calendar.HOUR_OF_DAY, 0);
        calS5.set(Calendar.MINUTE,0);

        java.sql.Date sTimeS5 = new java.sql.Date(calS5.getTime().getTime()); // your sql date

        calS5.set(Calendar.HOUR, 23);
        calS5.set(Calendar.MINUTE,59);

        java.sql.Date eTimeS5 = new java.sql.Date(calS5.getTime().getTime());
        SpinnerDateModel smS5 = new SpinnerDateModel(sTimeS5, null,eTimeS5, Calendar.MINUTE);
        spinnerS5 = new javax.swing.JSpinner(smS5);
        java.util.Calendar calE5 = Calendar.getInstance();
        java.util.Date utilDateE5 = new java.util.Date(); // your util date
        calE5.setTime(utilDateE5);

        calE5.set(Calendar.HOUR_OF_DAY, 0);
        calE5.set(Calendar.MINUTE,0);

        java.sql.Date sTimeE5 = new java.sql.Date(calE5.getTime().getTime()); // your sql date

        calE5.set(Calendar.HOUR, 23);
        calE5.set(Calendar.MINUTE,59);

        java.sql.Date eTimeE5 = new java.sql.Date(calE5.getTime().getTime());
        SpinnerDateModel smE5 = new SpinnerDateModel(sTimeE5, null,eTimeE5, Calendar.MINUTE);
        spinnerE5 = new javax.swing.JSpinner(smE5);
        appCount5 = new javax.swing.JSpinner();
        java.util.Calendar calS6 = Calendar.getInstance();
        java.util.Date utilDateS6 = new java.util.Date(); // your util date
        calS6.setTime(utilDateS6);

        calS6.set(Calendar.HOUR_OF_DAY, 0);
        calS6.set(Calendar.MINUTE,0);

        java.sql.Date sTimeS6 = new java.sql.Date(calS6.getTime().getTime()); // your sql date

        calS6.set(Calendar.HOUR, 23);
        calS6.set(Calendar.MINUTE,59);

        java.sql.Date eTimeS6 = new java.sql.Date(calS6.getTime().getTime());
        SpinnerDateModel smS6 = new SpinnerDateModel(sTimeS6, null,eTimeS6, Calendar.MINUTE);
        spinnerS6 = new javax.swing.JSpinner(smS6);
        java.util.Calendar calE6 = Calendar.getInstance();
        java.util.Date utilDateE6 = new java.util.Date(); // your util date
        calE6.setTime(utilDateE6);

        calE6.set(Calendar.HOUR_OF_DAY, 0);
        calE6.set(Calendar.MINUTE,0);

        java.sql.Date sTimeE6 = new java.sql.Date(calE6.getTime().getTime()); // your sql date

        calE6.set(Calendar.HOUR, 23);
        calE6.set(Calendar.MINUTE,59);

        java.sql.Date eTimeE6 = new java.sql.Date(calE6.getTime().getTime());
        SpinnerDateModel smE6 = new SpinnerDateModel(sTimeE6, null,eTimeE6, Calendar.MINUTE);
        spinnerE6 = new javax.swing.JSpinner(smE6);
        appCount6 = new javax.swing.JSpinner();
        java.util.Calendar calS7 = Calendar.getInstance();
        java.util.Date utilDateS7 = new java.util.Date(); // your util date
        calS7.setTime(utilDateS7);

        calS7.set(Calendar.HOUR_OF_DAY, 0);
        calS7.set(Calendar.MINUTE,0);

        java.sql.Date sTimeS7 = new java.sql.Date(calS7.getTime().getTime()); // your sql date

        calS7.set(Calendar.HOUR, 23);
        calS7.set(Calendar.MINUTE,59);

        java.sql.Date eTimeS7 = new java.sql.Date(calS7.getTime().getTime());
        SpinnerDateModel smS7 = new SpinnerDateModel(sTimeS7, null,eTimeS7, Calendar.MINUTE);
        spinnerS7 = new javax.swing.JSpinner(smS7);
        java.util.Calendar calE7 = Calendar.getInstance();
        java.util.Date utilDateE7 = new java.util.Date(); // your util date
        calE7.setTime(utilDateE7);

        calE7.set(Calendar.HOUR_OF_DAY, 0);
        calE7.set(Calendar.MINUTE,0);

        java.sql.Date sTimeE7 = new java.sql.Date(calE7.getTime().getTime()); // your sql date

        calE7.set(Calendar.HOUR, 23);
        calE7.set(Calendar.MINUTE,59);

        java.sql.Date eTimeE7 = new java.sql.Date(calE7.getTime().getTime());
        SpinnerDateModel smE7 = new SpinnerDateModel(sTimeE7, null,eTimeE7, Calendar.MINUTE);
        spinnerE7 = new javax.swing.JSpinner(smE7);
        appCount7 = new javax.swing.JSpinner();
        jButton2 = new javax.swing.JButton();
        denyTelCheck2 = new javax.swing.JCheckBox();
        jButton31 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        sDate = new com.toedter.calendar.JDateChooser();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jButton29 = new javax.swing.JButton();
        denyTelCheck1 = new javax.swing.JCheckBox();
        sessionB = new javax.swing.JButton();
        java.util.Calendar cal = Calendar.getInstance();
        java.util.Date utilDate = new java.util.Date(); // your util date
        cal.setTime(utilDate);

        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE,0);

        java.sql.Date sTime = new java.sql.Date(cal.getTime().getTime()); // your sql date

        cal.set(Calendar.HOUR, 23);
        cal.set(Calendar.MINUTE,59);

        java.sql.Date eTime = new java.sql.Date(cal.getTime().getTime());
        SpinnerDateModel sm = new SpinnerDateModel(sTime, null,eTime, Calendar.MINUTE);
        spinnerS = new javax.swing.JSpinner(sm);
        java.util.Calendar calE = Calendar.getInstance();
        java.util.Date utilDateE = new java.util.Date(); // your util date
        calE.setTime(utilDateE);

        calE.set(Calendar.HOUR_OF_DAY, 0);
        calE.set(Calendar.MINUTE,0);

        java.sql.Date sTimeE = new java.sql.Date(calE.getTime().getTime()); // your sql date

        calE.set(Calendar.HOUR, 23);
        calE.set(Calendar.MINUTE,59);

        java.sql.Date eTimeE = new java.sql.Date(calE.getTime().getTime());
        SpinnerDateModel smE = new SpinnerDateModel(sTimeE, null,eTimeE, Calendar.MINUTE);
        spinnerE = new javax.swing.JSpinner(smE);
        jSpinner2 = new javax.swing.JSpinner();
        sessionB1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        spcombo22 = new javax.swing.JComboBox<>();
        jButton23 = new javax.swing.JButton();
        jLabel69 = new javax.swing.JLabel();
        docSearch2 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTable12 = new javax.swing.JTable();
        jLabel39 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        jTable14 = new javax.swing.JTable();
        jPanel14 = new javax.swing.JPanel();
        timecombo = new javax.swing.JComboBox<>();
        informPat = new javax.swing.JCheckBox();
        jButton24 = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jButton30 = new javax.swing.JButton();
        spinnerH = new javax.swing.JSpinner();
        spinnerM = new javax.swing.JSpinner();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        spcombo23 = new javax.swing.JComboBox<>();
        jButton10 = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable8 = new javax.swing.JTable();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTable9 = new javax.swing.JTable();
        jLabel19 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        docSearch3 = new javax.swing.JTextField();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable10 = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        jButton15 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        eCal2 = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        sCal2 = new com.toedter.calendar.JDateChooser();
        jPanel7 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        spcombo24 = new javax.swing.JComboBox<>();
        jButton19 = new javax.swing.JButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTable11 = new javax.swing.JTable();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTable13 = new javax.swing.JTable();
        jLabel27 = new javax.swing.JLabel();
        timeCombo1 = new javax.swing.JComboBox<>();
        jLabel29 = new javax.swing.JLabel();
        timeCombo2 = new javax.swing.JComboBox<>();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        jTable15 = new javax.swing.JTable();
        jLabel71 = new javax.swing.JLabel();
        docSearch4 = new javax.swing.JTextField();
        dateCombo2 = new javax.swing.JComboBox<>();
        dateCombo1 = new javax.swing.JComboBox<>();
        jButton21 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        jButton27 = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jLabel60 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jLabel65 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jLabel74 = new javax.swing.JLabel();
        jButton16 = new javax.swing.JButton();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel80 = new javax.swing.JLabel();
        jButton22 = new javax.swing.JButton();
        jLabel81 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel85 = new javax.swing.JLabel();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();

        jButton5.setBackground(new java.awt.Color(0, 31, 82));
        jButton5.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Confirm");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel5.setText("jLabel5");

        jCheckBox1.setText("jCheckBox1");

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Session ID", "Date", "Start Time", "End Time", "Room No", "Action"
            }
        ));
        jScrollPane3.setViewportView(jTable3);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(null);
        setFrameIcon(null);
        setPreferredSize(new java.awt.Dimension(964, 592));

        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jTabbedPane1.setMaximumSize(new java.awt.Dimension(964, 592));
        jTabbedPane1.setMinimumSize(new java.awt.Dimension(964, 592));
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(964, 592));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(964, 592));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane4.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane4.setToolTipText("  ");
        jTabbedPane4.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N

        jPanel47.setBackground(new java.awt.Color(255, 255, 255));
        jPanel47.setPreferredSize(new java.awt.Dimension(964, 592));
        jPanel47.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel48.setBackground(new java.awt.Color(255, 255, 255));

        jLabel100.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jLabel100.setText("Specialty");

        jLabel101.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jLabel101.setText("Doctor Name");

        spcombo1.setFont(new java.awt.Font("Segoe UI Symbol", 0, 13)); // NOI18N
        spcombo1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                spcombo1spcombo2ItemStateChanged(evt);
            }
        });
        spcombo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spcombo1spcombo2ActionPerformed(evt);
            }
        });

        docSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                docSearchActionPerformed(evt);
            }
        });
        docSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                docSearchdocSearch5KeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jLabel6.setText("FromDate");

        jLabel8.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jLabel8.setText("ToDate");

        jButton33.setBackground(new java.awt.Color(0, 31, 82));
        jButton33.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton33.setForeground(new java.awt.Color(255, 255, 255));
        jButton33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Search_26px_2.png"))); // NOI18N
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel48Layout = new javax.swing.GroupLayout(jPanel48);
        jPanel48.setLayout(jPanel48Layout);
        jPanel48Layout.setHorizontalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel100, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spcombo1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel101)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(docSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel48Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jDateChooser6, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel48Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(jDateChooser5, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(278, Short.MAX_VALUE))
        );
        jPanel48Layout.setVerticalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel48Layout.createSequentialGroup()
                        .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel100, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spcombo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel101)
                            .addComponent(docSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel48Layout.createSequentialGroup()
                        .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton33)
                            .addGroup(jPanel48Layout.createSequentialGroup()
                                .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jDateChooser5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jDateChooser6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING))))
                        .addGap(22, 22, 22))))
        );

        jPanel47.add(jPanel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 60));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Doctor Name", "Speciality", "Date", "Session", "Fee", "Total app", "Available", "Status", "Select"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane25.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(5).setHeaderValue("Total app");
            jTable2.getColumnModel().getColumn(6).setHeaderValue("Available");
            jTable2.getColumnModel().getColumn(7).setHeaderValue("Status");
            jTable2.getColumnModel().getColumn(8).setHeaderValue("Select");
        }

        jPanel47.add(jScrollPane25, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 800, 160));

        jLabel102.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jLabel102.setText("Search Patient");
        jPanel47.add(jLabel102, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 230, 110, -1));

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "patientID", "name", "dob", "gender", "contactNo"
            }
        ));
        jTable5.setToolTipText("");
        jScrollPane26.setViewportView(jTable5);

        jPanel47.add(jScrollPane26, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 310, 420, 140));

        jLabel103.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jLabel103.setText("Appointment Details");
        jPanel47.add(jLabel103, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 295, 30));

        jButton44.setBackground(new java.awt.Color(0, 31, 82));
        jButton44.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton44.setForeground(new java.awt.Color(255, 255, 255));
        jButton44.setText("Reset");
        jButton44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton44ActionPerformed(evt);
            }
        });
        jPanel47.add(jButton44, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 480, 80, 30));

        jButton45.setText("Select");
        jPanel47.add(jButton45, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 150, -1, -1));

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "AppNo", "ScheduleTime", "PatientName", "Source", "BilledStatus", "select"
            }
        ));
        jScrollPane27.setViewportView(jTable6);

        jLabel104.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel104.setText("DoctorName:");

        jLabel105.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel106.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel107.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel107.setText("Date");

        jLabel108.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel108.setText("Start Time:");

        jLabel109.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel37.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel111.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel111.setText("SessionID:");

        jLabel112.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel112.setText("Fee:");

        jLabel55.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane27, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel24Layout.createSequentialGroup()
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel112)
                            .addComponent(jLabel104)
                            .addComponent(jLabel111))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel55, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                            .addComponent(jLabel105, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel37, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel107)
                            .addComponent(jLabel108))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel109, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                            .addComponent(jLabel106, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(19, 19, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel104)
                    .addComponent(jLabel105, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel107)
                    .addComponent(jLabel106, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel108)
                    .addComponent(jLabel109, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel111)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel112)
                    .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane27, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel47.add(jPanel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 390, 250));

        jButton18.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton18.setText("Place an appointment");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        jPanel47.add(jButton18, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 450, 200, 30));

        sourceR.add(w);
        w.setText("Walkin");
        jPanel47.add(w, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 450, -1, -1));

        sourceR.add(p);
        p.setText("Phone");
        jPanel47.add(p, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 450, -1, -1));

        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });
        jPanel47.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 250, 120, -1));

        jLabel28.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel28.setText("Tel No:");
        jPanel47.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 250, -1, -1));

        jTextField27.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField27KeyReleased(evt);
            }
        });
        jPanel47.add(jTextField27, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 250, 100, -1));

        jLabel26.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel26.setText("Patient ID :");
        jPanel47.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 250, 87, 22));

        jLabel110.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel110.setText("Patient Name:");
        jPanel47.add(jLabel110, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 280, -1, -1));

        patSearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patSearch1ActionPerformed(evt);
            }
        });
        patSearch1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                patSearch1docSearch5KeyReleased(evt);
            }
        });
        jPanel47.add(patSearch1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 280, 270, -1));

        jTabbedPane4.addTab("New Appointment", jPanel47);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel33.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jLabel33.setText("Patient ID :");

        jTextField28.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField28KeyReleased(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jLabel35.setText("Tel No:");

        jTextField29.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField29KeyReleased(evt);
            }
        });

        jTable16.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "patientID", "name", "dob", "gender", "contactNo"
            }
        ));
        jTable16.setToolTipText("");
        jTable16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable16MouseClicked(evt);
            }
        });
        jScrollPane28.setViewportView(jTable16);

        jTable17.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "AppNo", "ScheduleTime", "PatientName", "Source", "BilledStatus", "select"
            }
        ));
        jTable17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable17MouseClicked(evt);
            }
        });
        jScrollPane29.setViewportView(jTable17);

        jLabel113.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jLabel113.setText("Appointment Details");

        jLabel118.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel118.setText("DoctorName:");

        jButton47.setBackground(new java.awt.Color(0, 31, 82));
        jButton47.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton47.setForeground(new java.awt.Color(255, 255, 255));
        jButton47.setText("ReSchedule Appointment");
        jButton47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton47ActionPerformed(evt);
            }
        });

        jButton46.setBackground(new java.awt.Color(0, 31, 82));
        jButton46.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton46.setForeground(new java.awt.Color(255, 255, 255));
        jButton46.setText("Cancel Appointment");
        jButton46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton46ActionPerformed(evt);
            }
        });

        jLabel117.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel117.setText("Date");

        jLabel120.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel121.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel121.setText("Time:");

        jLabel54.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTable18.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable18);

        jButton1.setText("ReSchedule to:");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton48.setBackground(new java.awt.Color(0, 31, 82));
        jButton48.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton48.setForeground(new java.awt.Color(255, 255, 255));
        jButton48.setText("Reset");
        jButton48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton48ActionPerformed(evt);
            }
        });

        jLabel122.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel122.setText("SessionID");

        jLabel123.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel124.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel124.setText("Fee");

        jLabel73.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        sourceR.add(w1);
        w1.setText("Walkin");

        sourceR.add(p1);
        p1.setText("Phone");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton46, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(w1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(p1)
                        .addGap(33, 33, 33)
                        .addComponent(jButton47, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton48, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel117)
                            .addComponent(jLabel118)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel121)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel119, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel122)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel123, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel120, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                                            .addComponent(jLabel54, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton1))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel124)
                                .addGap(54, 54, 54)
                                .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)))
                .addGap(48, 48, 48))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel119, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel118))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel123, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel122))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel120, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel117))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel121)
                                    .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jButton1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel124))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(137, 137, 137)
                                .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(w1)
                                        .addComponent(p1))
                                    .addComponent(jButton46, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton47, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton48, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(223, Short.MAX_VALUE))
        );

        jLabel36.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jLabel36.setText("Patient Name:");

        jTextField30.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField30KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField28, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel35)
                .addGap(18, 18, 18)
                .addComponent(jTextField29, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel36)
                .addGap(18, 18, 18)
                .addComponent(jTextField30, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane29, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 792, Short.MAX_VALUE)
                            .addComponent(jLabel113, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane28, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(0, 16, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 808, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35)
                    .addComponent(jTextField29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36)
                    .addComponent(jTextField30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane28, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel113)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane29, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Cancel/ReSchedule", jPanel3);

        jPanel1.add(jTabbedPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 970, 560));
        jTabbedPane4.getAccessibleContext().setAccessibleName("Appointment          ");

        jTabbedPane1.addTab("          Appointment          ", jPanel1);

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane2.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane2.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jTabbedPane2.setVerifyInputWhenFocusTarget(false);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jLabel9.setText("Specialty");

        spcombo21.setFont(new java.awt.Font("Segoe UI Symbol", 0, 13)); // NOI18N
        spcombo21.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                spcombo21ItemStateChanged(evt);
            }
        });
        spcombo21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spcombo21ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(0, 31, 82));
        jButton7.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Reset");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel68.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jLabel68.setText("Doctor Name");

        docSearch1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                docSearch1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spcombo21, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(jLabel68)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(docSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(jButton7)
                .addGap(27, 27, 27))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spcombo21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel68, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(docSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        jTable7.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "DocID", "Name", "Specialty", "Sex", "Tel No", "Select"
            }
        ));
        jTable7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable7MouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(jTable7);

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Session ID", "From date", "Todate", "day", "NoOf App", "Select"
            }
        ));
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable4);
        if (jTable4.getColumnModel().getColumnCount() > 0) {
            jTable4.getColumnModel().getColumn(5).setHeaderValue("Select");
        }

        jLabel34.setFont(new java.awt.Font("Segoe UI Symbol", 1, 12)); // NOI18N
        jLabel34.setText("Session Details");

        jTabbedPane3.setFont(new java.awt.Font("Segoe UI Symbol", 1, 12)); // NOI18N

        jPanel9.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N

        jLabel87.setFont(new java.awt.Font("Segoe UI Symbol", 1, 12)); // NOI18N
        jLabel87.setText("From Date:");

        jLabel88.setFont(new java.awt.Font("Segoe UI Symbol", 1, 12)); // NOI18N
        jLabel88.setText("To Date");

        sun.setText("SUN");
        sun.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                sunItemStateChanged(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        jLabel3.setText("Day Of The week");

        jLabel4.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        jLabel4.setText("StartTime");

        jLabel22.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        jLabel22.setText("EndTime");

        jLabel7.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        jLabel7.setText("NoOfAppointments");

        mon.setText("MON");
        mon.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                monItemStateChanged(evt);
            }
        });

        tue.setText("TUE");
        tue.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tueItemStateChanged(evt);
            }
        });

        wed.setText("WED");
        wed.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                wedItemStateChanged(evt);
            }
        });

        thu.setText("THU");
        thu.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                thuItemStateChanged(evt);
            }
        });

        fri.setText("FRI");
        fri.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                friItemStateChanged(evt);
            }
        });

        sat.setText("SAT");
        sat.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                satItemStateChanged(evt);
            }
        });

        JSpinner.DateEditor deS1 = new JSpinner.DateEditor(spinnerS1, "HH:mm");
        spinnerS1.setEditor(deS1);
        spinnerS1.setValue(sTime);

        JSpinner.DateEditor deE1 = new JSpinner.DateEditor(spinnerE1, "HH:mm");
        spinnerE1.setEditor(deE1);
        spinnerE1.setValue(sTime);

        appCount1.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        JSpinner.DateEditor deS2 = new JSpinner.DateEditor(spinnerS2, "HH:mm");
        spinnerS2.setEditor(deS2);
        spinnerS2.setValue(sTime);

        JSpinner.DateEditor deE2 = new JSpinner.DateEditor(spinnerE2, "HH:mm");
        spinnerE2.setEditor(deE2);
        spinnerE2.setValue(sTime);

        appCount2.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        JSpinner.DateEditor deS3 = new JSpinner.DateEditor(spinnerS3, "HH:mm");
        spinnerS3.setEditor(deS3);
        spinnerS3.setValue(sTime);

        JSpinner.DateEditor deE3 = new JSpinner.DateEditor(spinnerE3, "HH:mm");
        spinnerE3.setEditor(deE3);
        spinnerE3.setValue(sTime);

        appCount3.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        JSpinner.DateEditor deS4 = new JSpinner.DateEditor(spinnerS4, "HH:mm");
        spinnerS4.setEditor(deS4);
        spinnerS4.setValue(sTime);

        JSpinner.DateEditor deE4 = new JSpinner.DateEditor(spinnerE4, "HH:mm");
        spinnerE4.setEditor(deE4);
        spinnerE4.setValue(sTime);

        appCount4.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        JSpinner.DateEditor deS5 = new JSpinner.DateEditor(spinnerS5, "HH:mm");
        spinnerS5.setEditor(deS5);
        spinnerS5.setValue(sTime);

        JSpinner.DateEditor deE5 = new JSpinner.DateEditor(spinnerE5, "HH:mm");
        spinnerE5.setEditor(deE5);
        spinnerE5.setValue(sTime);

        appCount5.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        JSpinner.DateEditor deS6 = new JSpinner.DateEditor(spinnerS6, "HH:mm");
        spinnerS6.setEditor(deS6);
        spinnerS6.setValue(sTime);

        JSpinner.DateEditor deE6 = new JSpinner.DateEditor(spinnerE6, "HH:mm");
        spinnerE6.setEditor(deE6);
        spinnerE6.setValue(sTime);

        appCount6.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        JSpinner.DateEditor deS7 = new JSpinner.DateEditor(spinnerS7, "HH:mm");
        spinnerS7.setEditor(deS7);
        spinnerS7.setValue(sTime);

        JSpinner.DateEditor deE7 = new JSpinner.DateEditor(spinnerE7, "HH:mm");
        spinnerE7.setEditor(deE7);
        spinnerE7.setValue(sTime);

        appCount7.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(sat, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(fri, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addComponent(thu, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(wed, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tue, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sun, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spinnerS1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinnerS2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinnerS3, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinnerS4, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(spinnerS6, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(spinnerS5, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(spinnerS7, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spinnerE4, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinnerE3, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinnerE2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinnerE1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinnerE5, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinnerE6, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinnerE7, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(appCount7, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(appCount6, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(appCount5, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(appCount4, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(appCount2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(appCount1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(appCount3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel22)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sun, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(spinnerS1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(spinnerE1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(appCount1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinnerE2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinnerS2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(appCount2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mon))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(appCount3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(spinnerE3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(spinnerS3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tue)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(appCount4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(spinnerS4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(spinnerE4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(wed)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinnerS5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinnerE5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(appCount5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(thu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinnerS6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinnerE6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(appCount6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fri))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sat)
                    .addComponent(spinnerS7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinnerE7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(appCount7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jButton2.setBackground(new java.awt.Color(0, 31, 82));
        jButton2.setFont(new java.awt.Font("Segoe UI Symbol", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Create ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        denyTelCheck2.setFont(new java.awt.Font("Segoe UI Symbol", 1, 11)); // NOI18N
        denyTelCheck2.setForeground(new java.awt.Color(0, 51, 153));
        denyTelCheck2.setText("Deny Telephone Booking");

        jButton31.setBackground(new java.awt.Color(0, 31, 82));
        jButton31.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton31.setForeground(new java.awt.Color(255, 255, 255));
        jButton31.setText("Reset");
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(eDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(454, 454, 454)
                        .addComponent(denyTelCheck2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(sDate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(eDate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(denyTelCheck2)
                .addGap(27, 27, 27)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("  Multi Session Creation  ", jPanel9);

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Symbol", 1, 14))); // NOI18N

        jLabel56.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        jLabel56.setText("SessionDate");

        jLabel57.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        jLabel57.setText("Time(HH      :     MM)");

        jLabel61.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        jLabel61.setText("StartTime");

        jLabel62.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        jLabel62.setText("endTime");

        jLabel67.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        jLabel67.setText("No of appointments:");

        jButton29.setBackground(new java.awt.Color(0, 31, 82));
        jButton29.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton29.setForeground(new java.awt.Color(255, 255, 255));
        jButton29.setText("Reset");
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        denyTelCheck1.setFont(new java.awt.Font("Segoe UI Symbol", 1, 11)); // NOI18N
        denyTelCheck1.setForeground(new java.awt.Color(0, 51, 153));
        denyTelCheck1.setText("Deny Telephone Booking");

        sessionB.setBackground(new java.awt.Color(0, 31, 82));
        sessionB.setFont(new java.awt.Font("Segoe UI Symbol", 1, 12)); // NOI18N
        sessionB.setForeground(new java.awt.Color(255, 255, 255));
        sessionB.setText("Create");
        sessionB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sessionBActionPerformed(evt);
            }
        });

        JSpinner.DateEditor de = new JSpinner.DateEditor(spinnerS, "HH:mm");
        spinnerS.setEditor(de);
        spinnerS.setValue(sTime);

        JSpinner.DateEditor deE = new JSpinner.DateEditor(spinnerE, "HH:mm");
        spinnerE.setEditor(deE);
        spinnerE.setValue(sTime);

        jSpinner2.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        sessionB1.setBackground(new java.awt.Color(0, 31, 82));
        sessionB1.setFont(new java.awt.Font("Segoe UI Symbol", 1, 12)); // NOI18N
        sessionB1.setForeground(new java.awt.Color(255, 255, 255));
        sessionB1.setText("Update");
        sessionB1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sessionB1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(442, 442, 442))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel67))
                        .addGap(54, 54, 54)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sDate, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                            .addComponent(spinnerS)
                            .addComponent(spinnerE)
                            .addComponent(jSpinner2))
                        .addGap(403, 403, 403))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(denyTelCheck1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sessionB, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sessionB1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(292, 292, 292))))
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addComponent(sDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel57)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinnerS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel62, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                    .addComponent(spinnerE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel67)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(denyTelCheck1)
                            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(sessionB, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(sessionB1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("  Single Session Creation/Update  ", jPanel15);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 734, Short.MAX_VALUE)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 734, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane3)
                .addGap(16, 16, 16))
        );

        jTabbedPane2.addTab("Create Session        ", jPanel4);

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        spcombo22.setFont(new java.awt.Font("Segoe UI Symbol", 0, 13)); // NOI18N
        spcombo22.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                spcombo22ItemStateChanged(evt);
            }
        });
        spcombo22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spcombo22ActionPerformed(evt);
            }
        });

        jButton23.setBackground(new java.awt.Color(0, 31, 82));
        jButton23.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton23.setForeground(new java.awt.Color(255, 255, 255));
        jButton23.setText("Reset");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        jLabel69.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jLabel69.setText("Doctor Name");

        docSearch2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                docSearch2KeyReleased(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jLabel21.setText("Specialty");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spcombo22, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel69)
                .addGap(18, 18, 18)
                .addComponent(docSearch2, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83)
                .addComponent(jButton23)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel69)
                    .addComponent(docSearch2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spcombo22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable12.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "DocID", "Name", "Specialty", "Sex", "Tel No", "Select"
            }
        ));
        jTable12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable12MouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(jTable12);

        jLabel39.setFont(new java.awt.Font("Segoe UI Symbol", 1, 12)); // NOI18N
        jLabel39.setText("Session Details");

        jTable14.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Session ID", "From date", "Todate", "day", "NoOf App", "Select"
            }
        ));
        jTable14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable14MouseClicked(evt);
            }
        });
        jScrollPane14.setViewportView(jTable14);
        if (jTable14.getColumnModel().getColumnCount() > 0) {
            jTable14.getColumnModel().getColumn(5).setHeaderValue("Select");
        }

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "Doctor in-out-Time Entry", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Symbol", 1, 12)))); // NOI18N

        timecombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "+", "-" }));

        informPat.setText("Inform Patients");

        jButton24.setBackground(new java.awt.Color(0, 31, 82));
        jButton24.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton24.setForeground(new java.awt.Color(255, 255, 255));
        jButton24.setText("Update");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        jLabel41.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        jLabel41.setText("Delay+/Advance-");

        jLabel40.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        jLabel40.setText("Time(HH      :     MM)");

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel51.setText(":");

        jButton30.setBackground(new java.awt.Color(0, 31, 82));
        jButton30.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton30.setForeground(new java.awt.Color(255, 255, 255));
        jButton30.setText("Reset");
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });

        spinnerH.setModel(new javax.swing.SpinnerNumberModel(0, 0, 23, 1));

        spinnerM.setModel(new javax.swing.SpinnerNumberModel(0, 0, 59, 1));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(timecombo, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(informPat))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton30))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(spinnerH, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spinnerM, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(jLabel41))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(timecombo, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(spinnerH, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(spinnerM, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton24)
                    .addComponent(informPat)
                    .addComponent(jButton30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jLabel42.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel42.setText("DoctorName:");

        jLabel43.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel43.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel44.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel44.setText("Date:");

        jLabel45.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel45.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel46.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel46.setText("SessionID");

        jLabel48.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel48.setText("StartTime");

        jLabel49.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel49.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel50.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel50.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel52.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel52.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel53.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel53.setText("endTime");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(26, 26, 26)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 745, Short.MAX_VALUE)
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel42)
                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel44)
                            .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel46)
                            .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel48)
                            .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel53)
                            .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(70, 70, 70))))
        );

        jTabbedPane2.addTab(" Doctor in-out Time Entry", jPanel5);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jLabel17.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jLabel17.setText("Specialty");

        spcombo23.setFont(new java.awt.Font("Segoe UI Symbol", 0, 13)); // NOI18N
        spcombo23.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                spcombo23ItemStateChanged(evt);
            }
        });
        spcombo23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spcombo23ActionPerformed(evt);
            }
        });

        jButton10.setBackground(new java.awt.Color(0, 31, 82));
        jButton10.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("Reset");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jTable8.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "DocID", "Name", "Speciality", "Sex", "Tel No", "Select"
            }
        ));
        jTable8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable8MouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(jTable8);

        jTable9.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Session ID", "From date", "Todate", "day", "NoOf App", "App Availability", "Select"
            }
        ));
        jTable9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable9MouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(jTable9);
        if (jTable9.getColumnModel().getColumnCount() > 0) {
            jTable9.getColumnModel().getColumn(5).setHeaderValue("");
            jTable9.getColumnModel().getColumn(6).setHeaderValue("Select");
        }

        jLabel19.setFont(new java.awt.Font("Segoe UI Symbol", 1, 12)); // NOI18N
        jLabel19.setText("Available Sessions");

        jLabel70.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jLabel70.setText("Doctor Name");

        docSearch3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                docSearch3KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spcombo23, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel70)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(docSearch3, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83)
                .addComponent(jButton10)
                .addContainerGap(84, Short.MAX_VALUE))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 729, Short.MAX_VALUE)
                    .addComponent(jScrollPane9))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel70)
                        .addComponent(docSearch3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(spcombo23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTable10.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Session ID", "From date", "Todate", "day", "Time", "NoOf App", "Select"
            }
        ));
        jScrollPane10.setViewportView(jTable10);
        if (jTable10.getColumnModel().getColumnCount() > 0) {
            jTable10.getColumnModel().getColumn(6).setHeaderValue("Select");
        }

        jLabel20.setFont(new java.awt.Font("Segoe UI Symbol", 1, 12)); // NOI18N
        jLabel20.setText("Blocked Sessions");

        jButton15.setFont(new java.awt.Font("Segoe UI Symbol", 1, 12)); // NOI18N
        jButton15.setText("Block Session");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton20.setFont(new java.awt.Font("Segoe UI Symbol", 1, 12)); // NOI18N
        jButton20.setText("Unblock Session");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jLabel10.setText("ToDate");

        jLabel11.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jLabel11.setText("FromDate");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 729, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(152, 152, 152)
                                .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(49, 49, 49)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(eCal2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(sCal2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(sCal2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(eCal2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
        );

        jTabbedPane2.addTab("Session Blocking", jPanel6);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        jLabel23.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jLabel23.setText("Specialty");

        spcombo24.setFont(new java.awt.Font("Segoe UI Symbol", 0, 13)); // NOI18N
        spcombo24.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                spcombo24ItemStateChanged(evt);
            }
        });
        spcombo24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spcombo24ActionPerformed(evt);
            }
        });

        jButton19.setBackground(new java.awt.Color(0, 31, 82));
        jButton19.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton19.setForeground(new java.awt.Color(255, 255, 255));
        jButton19.setText("Reset");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jTable11.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "DocID", "Name", "Speciality", "Sex", "Tel No", "Select"
            }
        ));
        jTable11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable11MouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(jTable11);

        jTable13.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Select", "AppNo", "Patient", "Type", "Source", "Phone No"
            }
        ));
        jScrollPane13.setViewportView(jTable13);

        jLabel27.setText("Session");

        timeCombo1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                timeCombo1ItemStateChanged(evt);
            }
        });

        jLabel29.setText("Date");

        timeCombo2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                timeCombo2ItemStateChanged(evt);
            }
        });

        jLabel30.setText("Session");

        jLabel31.setText("Date");

        jTable15.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Select", "AppNo", "Patient", "Type", "Source", "Phone No"
            }
        ));
        jScrollPane15.setViewportView(jTable15);

        jLabel71.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jLabel71.setText("Doctor Name");

        docSearch4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                docSearch4KeyReleased(evt);
            }
        });

        dateCombo2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dateCombo2MouseClicked(evt);
            }
        });

        dateCombo1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                dateCombo1ItemStateChanged(evt);
            }
        });
        dateCombo1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dateCombo1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dateCombo1MouseEntered(evt);
            }
        });
        dateCombo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateCombo1ActionPerformed(evt);
            }
        });

        jButton21.setFont(new java.awt.Font("Segoe UI Symbol", 1, 10)); // NOI18N
        jButton21.setForeground(new java.awt.Color(0, 51, 153));
        jButton21.setText("Move");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jButton4.setText("Select");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton14.setText("Select");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jLabel1.setText("Appointments");

        jSpinner1.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jLabel2.setText(" last:");

        jButton27.setFont(new java.awt.Font("Segoe UI Symbol", 1, 10)); // NOI18N
        jButton27.setForeground(new java.awt.Color(0, 51, 153));
        jButton27.setText("Cancel");
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spcombo24, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jLabel71)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(docSearch4, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                        .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton27, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)
                        .addGap(522, 522, 522)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27)
                            .addComponent(jLabel29))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(timeCombo1, 0, 126, Short.MAX_VALUE)
                            .addComponent(dateCombo1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(44, 44, 44)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel30)
                            .addComponent(jLabel31))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(timeCombo2, 0, 128, Short.MAX_VALUE)
                            .addComponent(dateCombo2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 729, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(spcombo24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel71)
                        .addComponent(docSearch4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dateCombo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel31)
                            .addComponent(jButton14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(timeCombo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(dateCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(timeCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(9, 9, 9)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton27)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(98, 98, 98))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 18, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 553, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Bulk Re-Scheduling", jPanel7);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );

        jTabbedPane1.addTab("          Session Management          ", jPanel12);

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));

        jLabel60.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jLabel60.setText("daily Appointments detils");

        jButton11.setBackground(new java.awt.Color(0, 31, 82));
        jButton11.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Report_Card_32px_2.png"))); // NOI18N
        jButton11.setText("Appointments");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jLabel65.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jLabel65.setText("Appointments");

        jButton12.setBackground(new java.awt.Color(0, 31, 82));
        jButton12.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton12.setForeground(new java.awt.Color(255, 255, 255));
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Report_Card_32px_2.png"))); // NOI18N
        jButton12.setText("Daily Appointments");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jLabel74.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jLabel74.setText("Sessions");

        jButton16.setBackground(new java.awt.Color(0, 31, 82));
        jButton16.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton16.setForeground(new java.awt.Color(255, 255, 255));
        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Report_Card_32px_2.png"))); // NOI18N
        jButton16.setText("Session");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jLabel77.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel77.setText("To Date:");

        jLabel78.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel78.setText("From Date:");

        jLabel79.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jLabel79.setText("Sessions Based on doctor ");

        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });

        jLabel80.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel80.setText("Speciality:");

        jButton22.setBackground(new java.awt.Color(0, 31, 82));
        jButton22.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton22.setForeground(new java.awt.Color(255, 255, 255));
        jButton22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Report_Card_32px_2.png"))); // NOI18N
        jButton22.setText("Created sessions");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jLabel81.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel81.setText("Doctor Name:");

        jLabel85.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel85.setText("Date:");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel65)
                            .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(161, 161, 161)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel74)
                            .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(jLabel85)
                                .addGap(26, 26, 26)
                                .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(54, 54, 54)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel16Layout.createSequentialGroup()
                                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel81)
                                            .addComponent(jLabel80)
                                            .addComponent(jLabel78)
                                            .addComponent(jLabel77))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jComboBox3, 0, 167, Short.MAX_VALUE)
                                                .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))))))))
                .addContainerGap(147, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel65)
                            .addComponent(jLabel74))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton11))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDateChooser3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel85))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel79)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel80)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel81)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel77)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 156, Short.MAX_VALUE)
                        .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel78)
                        .addContainerGap())))
        );

        jTabbedPane1.addTab("            Reports          ", jPanel16);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 972, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    void tableload(javax.swing.JTable j){
        try{
            Connection con=DBconnect.getConnection();
            String query="Select empNo,name,Speciality,Gender from doctor";
            PreparedStatement pst=(PreparedStatement)con.prepareStatement(query);
            ResultSet rs=pst.executeQuery();
            j.setModel(DbUtils.resultSetToTableModel(rs));
           
        }
        catch(Exception e){
            
               JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    void patientTableLoad(javax.swing.JTable j){
    
     try{
            Connection con=DBconnect.getConnection();
            String query="Select patientID,name,dob,gender,contactNo from patient";
            PreparedStatement pst=(PreparedStatement)con.prepareStatement(query);
            ResultSet rs=pst.executeQuery();
            j.setModel(DbUtils.resultSetToTableModel(rs));
           
        }
        catch(Exception e){
            
               JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    void sessionTableload(javax.swing.JTable j){
        try{
            Connection con=DBconnect.getConnection();
            String query="select d.empNo,d.name,d.Speciality,s.sessionID,s.date,s.startTime,s.noOfApp,s.avilableApp,s.TelBooking,s.action "
                      + "from session s,doctor d where s.empNo=d.empNo AND  s.status='Available' AND "
                      + "s.date >= CURDATE() order by s.date" ;
            PreparedStatement pst=(PreparedStatement)con.prepareStatement(query);
            ResultSet rs=pst.executeQuery();
            j.setModel(DbUtils.resultSetToTableModel(rs));
           
        }
        catch(Exception e){
            
               JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
    
    }
  

   /* 
    void filter(String query,javax.swing.JTable j){
        DefaultTableModel dm =(DefaultTableModel) j.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter <DefaultTableModel>(dm);
        j.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(query));
 
    }*/
   
    boolean isInteger(String str){
        int len = str.length();

    // an empty string is not an integer
            if (len == 0) {
                return false;
            }
            for (int i = 0; i < len; ++i) {
                if (!Character.isDigit(str.charAt(i))) {
                return false;
                }       
            }      
            return true;
    } 
    
    void search(String search,javax.swing.JTable j,String query){
         try{
            Connection con=DBconnect.getConnection();
            
            PreparedStatement pst=con.prepareStatement(query); 
            pst.setString(1,search);
            rs=pst.executeQuery();
            j.setModel(DbUtils.resultSetToTableModel(rs));
           
        }
        catch(SQLException ex){
               JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
    }
        void spsearch(String search,javax.swing.JTable j,String query){
         try{
            Connection con=DBconnect.getConnection();
          if(!(search.equals("--SelectAll--") ) ){
            PreparedStatement pst=con.prepareStatement(query); 
            pst.setString(1,search);
            rs=pst.executeQuery();
            j.setModel(DbUtils.resultSetToTableModel(rs));
          }
          else
          {
              sessionTableload(jTable2);
          }
        }
        catch(SQLException ex){
               JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
    }
    void search2(int search,javax.swing.JTable j,String query){
         try{
            Connection con=DBconnect.getConnection();
            
            PreparedStatement pst=con.prepareStatement(query); 
            pst.setInt(1,search);
            rs=pst.executeQuery();
            j.setModel(DbUtils.resultSetToTableModel(rs));
           
        }
        catch(SQLException ex){
               JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
    }
       void search3(String sp,String name,javax.swing.JTable j,String query){
         try{
            Connection con=DBconnect.getConnection();
            
            PreparedStatement pst=con.prepareStatement(query); 
            pst.setString(1,sp);
            pst.setString(2,name);
            rs=pst.executeQuery();
            j.setModel(DbUtils.resultSetToTableModel(rs));
           
        }
        catch(SQLException ex){
               JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
    }
       void search4(String empID,Date d,String time,javax.swing.JTable j,String query){
         try{
            Connection con=DBconnect.getConnection();
            
            PreparedStatement pst=con.prepareStatement(query); 
            pst.setString(1,empID);
            pst.setDate(2,d);
            pst.setString(3,time);
            rs=pst.executeQuery();
            j.setModel(DbUtils.resultSetToTableModel(rs));
           
        }
        catch(SQLException ex){
               JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
    }
    
    void fillNameComboFiltered(String select){
      
        try{
            Connection con=DBconnect.getConnection();
            String query="select * from doctor where Speciality=?";
            PreparedStatement pst=con.prepareStatement(query); 
            pst.setString(1,select);
           
            rs=pst.executeQuery();
            while(rs.next()){
                String name=rs.getString("name");
                //dnamecombo21.addItem(name);
            }
            
           
        }
        catch(SQLException ex){
               JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
       
   }
    
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void docSearch4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_docSearch4KeyReleased

        
        
        String query="select empNo,name,Speciality,Gender from doctor where Speciality=? AND name like ? ";
        String sp=spcombo24.getSelectedItem().toString();
        String name=docSearch4.getText()+"%";
        search3(sp,name,jTable11,query);
    }//GEN-LAST:event_docSearch4KeyReleased

    private void spcombo24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_spcombo24ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_spcombo24ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        DefaultTableModel model1 =(DefaultTableModel) jTable10.getModel();
        Connection con = DBconnect.getConnection();
        int sid=Integer.parseInt(model1.getValueAt(jTable10.getSelectedRow(),0).toString());
        Session s1=new Session();
        int j=s1.UpdateStatusAvailable(sid);
        if(j>0){

            JOptionPane.showMessageDialog(this,"Session UnBlocked ");
            DefaultTableModel model =(DefaultTableModel) jTable8.getModel();

            try{

                String did=model.getValueAt(jTable8.getSelectedRow(),0).toString();
                String query="select sessionID,startDate,endDate,date,startTime,noOfApp,avilableApp ,status,action from session where empNo=? AND status= 'Available'";
                String query2="select sessionID,startDate,endDate,date,startTime,noOfApp,avilableApp ,status,action  from session where empNo=? AND status= 'Blocked'";
                PreparedStatement pst=con.prepareStatement(query);
                PreparedStatement pst2=con.prepareStatement(query2);
                pst.setString(1,did);
                pst2.setString(1,did);

                rs=pst.executeQuery();

                rs2=pst2.executeQuery();
                jTable9.setModel(DbUtils.resultSetToTableModel(rs));
                jTable10.setModel(DbUtils.resultSetToTableModel(rs2));
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }

        }
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        Connection con = DBconnect.getConnection();
        
        
        DefaultTableModel model1 =(DefaultTableModel) jTable9.getModel();
        DefaultTableModel model =(DefaultTableModel) jTable8.getModel();
        

        
       
        
        
        Session s1=new Session();
   if(jTable8.getSelectedRow()>-1){
        String did=model.getValueAt(jTable8.getSelectedRow(),0).toString();
        String sDate4= sCal2.getDateFormatString();
        String eDate4=eCal2.getDateFormatString();
     if(jTable9.getSelectedRow()>-1){
        int sid=Integer.parseInt(model1.getValueAt(jTable9.getSelectedRow(),0).toString());
        int availableApp=Integer.parseInt(model1.getValueAt(jTable9.getSelectedRow(),6).toString());
       
     if(availableApp <= 0) {
        int j=s1.UpdateStatusBlocked(sid);
        if(j>0){

            JOptionPane.showMessageDialog(this,"Session Blocked ");
           
            try{

               
                String query="select sessionID,startDate,endDate,date,startTime,noOfApp,avilableApp ,status,action from session where empNo=? AND date >= CURDATE() AND status= 'Available'";
                String query2="select sessionID,startDate,endDate,date,startTime,noOfApp,avilableApp ,status,action from session where empNo=? AND date >= CURDATE() AND status= 'Blocked'";
                PreparedStatement pst=con.prepareStatement(query);
                PreparedStatement pst2=con.prepareStatement(query2);
                pst.setString(1,did);
                pst2.setString(1,did);

                rs=pst.executeQuery();

                rs2=pst2.executeQuery();
                jTable9.setModel(DbUtils.resultSetToTableModel(rs));
                jTable10.setModel(DbUtils.resultSetToTableModel(rs2));
                reloadNewApp();
                reloadCancelApp();
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }

        }
       }
       else
       
        
          JOptionPane.showMessageDialog(null,"Please Reschedule/cancel the available "+availableApp+" appointment(s) ","SessionID:"+sid+" CANNOT BE BLOCKED!",JOptionPane.ERROR_MESSAGE); 
    }
     
     else if(!(sDate4.equals("")) && !(eDate4.equals(""))  ){
    
           if(sCal2.getDate().before(eCal2.getDate())) {
            java.util.Date startDate=sCal2.getDate();
            java.util.Date endDate=eCal2.getDate();
            s1.blockMultiSessions(did, startDate, endDate);
            
            try{

               
                String query="select sessionID,startDate,endDate,date,startTime,noOfApp,avilableApp ,status,action from session where empNo=? AND date >= CURDATE() AND status= 'Available'";
                String query2="select sessionID,startDate,endDate,date,startTime,noOfApp,avilableApp ,status,action from session where empNo=? AND date >= CURDATE() AND status= 'Blocked'";
                PreparedStatement pst=con.prepareStatement(query);
                PreparedStatement pst2=con.prepareStatement(query2);
                pst.setString(1,did);
                pst2.setString(1,did);

                rs=pst.executeQuery();

                rs2=pst2.executeQuery();
                jTable9.setModel(DbUtils.resultSetToTableModel(rs));
                jTable10.setModel(DbUtils.resultSetToTableModel(rs2));
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
             reloadNewApp();
             reloadCancelApp();
           
           }
          else{
             JOptionPane.showMessageDialog(null,"From Date is greater than To Date","INVALID INPUT",JOptionPane.ERROR_MESSAGE); }
               
               }
      else{
           JOptionPane.showMessageDialog(null,"Select a session or time period to block session(s) ","EMPTY FIELDS",JOptionPane.ERROR_MESSAGE); }
   }
   else{
       JOptionPane.showMessageDialog(null,"Select a Doctor to block session(s) ","ERROR!",JOptionPane.ERROR_MESSAGE); 
   }
  
    }//GEN-LAST:event_jButton15ActionPerformed

    private void docSearch3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_docSearch3KeyReleased
        String query="select empNo,name,Speciality,Gender  from doctor where  Speciality=? AND name like ? ";
        String sp=spcombo23.getSelectedItem().toString();
        String name=docSearch3.getText()+"%";
        search3(sp,name,jTable8,query);
    }//GEN-LAST:event_docSearch3KeyReleased

    private void jTable9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable9MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jTable9MouseClicked

    private void jTable8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable8MouseClicked

        DefaultTableModel model =(DefaultTableModel) jTable8.getModel();

        try{
            Connection con=DBconnect.getConnection();
            String did=model.getValueAt(jTable8.getSelectedRow(),0).toString();
            String query="select sessionID,startDate,endDate,date,startTime,noOfApp,avilableApp ,status ,action from session where empNo=? AND status= 'Available' AND date >= CURDATE()";
            String query2="select sessionID,startDate,endDate,date,startTime,noOfApp,avilableApp ,status ,action from session where empNo=? AND status= 'Blocked' AND date >= CURDATE()";
            PreparedStatement pst=con.prepareStatement(query);
            PreparedStatement pst2=con.prepareStatement(query2);
            pst.setString(1,did);
            pst2.setString(1,did);

            rs=pst.executeQuery();

            rs2=pst2.executeQuery();
            jTable9.setModel(DbUtils.resultSetToTableModel(rs));
            jTable10.setModel(DbUtils.resultSetToTableModel(rs2));
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }//GEN-LAST:event_jTable8MouseClicked

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        tableload(jTable8);
        docSearch3.setText("");
        spcombo23.setSelectedIndex(0);
        dm=(DefaultTableModel) jTable9.getModel();
        dm.setRowCount(0);
        dm=(DefaultTableModel) jTable10.getModel();
        dm.setRowCount(0);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void spcombo23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_spcombo23ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_spcombo23ActionPerformed

    private void spcombo23ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_spcombo23ItemStateChanged

        String selectsp =spcombo23.getSelectedItem().toString();
        String query="select empNo,name,Speciality,gender  from doctor where Speciality=?";
        search(selectsp,jTable8,query);

    }//GEN-LAST:event_spcombo23ItemStateChanged

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
       spinnerH.setValue(0);
       spinnerM.setValue(0);
       jTextField2.setText("");
       informPat.setSelected(false);
        
    }//GEN-LAST:event_jButton30ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
       Session s1=new Session();
        try {
            if(!(spinnerH.getValue().equals(0))||!(spinnerM.getValue().equals(0))){
            
            
            Connection con=DBconnect.getConnection();
            String select=(String)timecombo.getSelectedItem();

            
            int hrs=Integer.parseInt(spinnerH.getValue().toString());
            int min=Integer.parseInt(spinnerM.getValue().toString());
            
            int sid=Integer.parseInt(jLabel49.getText());
            String docName=jLabel43.getText();
            
            String sTime=jLabel50.getText();
            String eTime=jLabel52.getText();
            int totalmin=((hrs*60)+(min));
            SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
            java.util.Date date=new java.util.Date();
            java.util.Date s2=df.parse(sTime);
            java.util.Date e2=df.parse(eTime);
            java.sql.Date s3=new java.sql.Date(s2.getTime());
            java.sql.Date e3=new java.sql.Date(e2.getTime());
            Calendar cal = Calendar.getInstance();
            Calendar cale = Calendar.getInstance();
            cal.setTime(s3);
            cale.setTime(e3);
            
            String query2 ="select a.time,a.apRefNO,p.name,p.contactNo,a.appNo,a.date from appointment a ,patient p where a.patientID=p.patientID and a.sessionID=?";
            PreparedStatement pst2=con.prepareStatement(query2);
            pst2.setInt(1,sid);

                
                if(select.equals("+")){
                    cal.add(Calendar.MINUTE, totalmin);
                    cale.add(Calendar.MINUTE, totalmin);
                    
                    String newTime = df.format(cal.getTime());
                    String newTime2 = df.format(cale.getTime());
                    

                    jLabel50.setText(newTime);
                    jLabel52.setText(newTime2);
                    String query="update session set startTime=?, endTime=? where sessionID=?";              
                    PreparedStatement pst=con.prepareStatement(query);
                    pst.setString(1,newTime);
                    pst.setString(2,newTime2);
                    pst.setInt(3,sid);

                    
                    int status=pst.executeUpdate();
                    if(status>0 ){
                        JOptionPane.showMessageDialog(this, "Time Updated");
                           ResultSet rs2=pst2.executeQuery();
//   -----------------------------------------------------------------------------         
          while(rs2.next()){ 
               String appTime=rs2.getString("time");
               String apRef =rs2.getString("apRefNo");
               String patientName=rs2.getString("name");
               String conNo =rs2.getString("contactNo");
               int appNo=rs2.getInt("appNo");
               Date dbDate=rs2.getDate("date");
               String contactNo="+94"+conNo.substring(1);
               
                java.util.Date appTime1=df.parse(appTime);
                java.sql.Date appTime2=new java.sql.Date(appTime1.getTime());
                Calendar calApp = Calendar.getInstance();
                calApp.setTime(appTime2);
                    calApp.add(Calendar.MINUTE, totalmin); //add aditional time to aprxTime
                    String newAprxTime = df.format(calApp.getTime());//new aprx time to string
                        DefaultTableModel model =(DefaultTableModel) jTable12.getModel();
                        String empno=model.getValueAt(jTable12.getSelectedRow(),0).toString();
                        String query1="select s.sessionID,s.date,s.startTime,s.endTime,s.noOfApp,s.avilableApp,s.status,s.action from session s,doctor d where s.empNo=d.empNo AND s.date >= CURDATE() AND d.empNo=?";
                        search(empno,jTable14,query1);
                        
            //Update Aprx time----------------------------------------------------------------         
                    String query3="update appointment set time=? where apRefNo=? and sessionID=?";
                    PreparedStatement pst3=con.prepareStatement(query3);
                    pst3.setString(1,newAprxTime);
                    pst3.setString(2,apRef);
                    pst3.setInt(3,sid);
                    pst3.executeUpdate();
            //Send Message----------------------------------------------------------------------        
                     String message="Dear "+patientName+" Your Appointment with Dr."+docName+ " is re-Scheduled to AppNo."+appNo+" on "+dbDate+" at "+newAprxTime+". Deegayuu-Kuliyapitiya";
                     System.out.println(contactNo+message);
                     s1.alertSMS(message,contactNo); 
                   
                    }
                    
                }
                else{
                    
                    int deletemins=totalmin*(-1);
                    cal.add(Calendar.MINUTE, deletemins);
                    cale.add(Calendar.MINUTE, deletemins);
                    //calApp.add(Calendar.MINUTE, deletemins); //add aditional time to aprxTime
                    String newTime3 = df.format(cal.getTime());
                    String newTime4 = df.format(cale.getTime());
                    
                    //String newAprxTime = df.format(calApp.getTime());//new aprx time to string
                    
                    System.out.println(newTime3);
                    jLabel50.setText(newTime3);
                    jLabel52.setText(newTime4);
                    String query4="update session set startTime=?, endTime=? where sessionID=?";
                    PreparedStatement pst4=con.prepareStatement(query4);
                    pst4.setString(1,newTime3);
                    pst4.setString(2,newTime4);
                    pst4.setInt(3,sid);
  
                    
                    int status2=pst4.executeUpdate();
                    if(status2>0){
                        JOptionPane.showMessageDialog(this, "Time Updated");
                        while(rs2.next()){ 
                        String appTime=rs2.getString("time");
                        String apRef =rs2.getString("apRefNo");
                        String patientName=rs2.getString("name");
                        String conNo =rs2.getString("contactNo");
                        int appNo=rs2.getInt("appNo");
                        Date dbDate=rs2.getDate("date");
                        String contactNo="+94"+conNo.substring(1);
               
                        java.util.Date appTime1=df.parse(appTime);
                        java.sql.Date appTime2=new java.sql.Date(appTime1.getTime());
                        Calendar calApp = Calendar.getInstance();
                        calApp.setTime(appTime2);
                        calApp.add(Calendar.MINUTE, totalmin); //add aditional time to aprxTime
                        String newAprxTime = df.format(calApp.getTime());//new aprx time to string
                        DefaultTableModel model =(DefaultTableModel) jTable12.getModel();
                        String empno=model.getValueAt(jTable12.getSelectedRow(),0).toString();
                        String query1="select s.sessionID,s.date,s.startTime,s.endTime,s.noOfApp,s.avilableApp,s.status,s.action from session s,doctor d where s.empNo=d.empNo AND s.date >= CURDATE() AND d.empNo=?";
                        search(empno,jTable14,query1);
                        //Update Aprx time----------------------------------------------------------------         
                    String query3="update appointment set time=? where apRefNo=? and sessionID=?";
                    PreparedStatement pst3=con.prepareStatement(query3);
                    pst3.setString(1,newAprxTime);
                    pst3.setString(2,apRef);
                     pst3.setInt(3,sid);
                    pst3.executeUpdate();
                    //Send Message----------------------------------------------------------------------        
                     String message="Dear "+patientName+" Your Appointment with Dr."+docName+ " is re-Scheduled to AppNo."+appNo+" on "+dbDate+" at "+newAprxTime+". Deegayuu-Kuliyapitiya";
                     System.out.println(contactNo+message);
                     s1.alertSMS(message,contactNo); 
                    
                    }
                        
                        
                        
                        DefaultTableModel model =(DefaultTableModel) jTable12.getModel();
                        String empno=model.getValueAt(jTable12.getSelectedRow(),0).toString();
                        String query1="select s.sessionID,s.date,s.startTime,s.endTime,s.noOfApp,s.avilableApp,s.status,s.action from session s,doctor d where s.empNo=d.empNo AND s.date >= CURDATE() AND d.empNo=?";
                        search(empno,jTable14,query1);
                    }
                }
                
                /*
                
                Connection con=DbConnect.getConnection();
                String query="select TIME_TO_SEC(startTime) as sTimeSec where sessionID=?";
                PreparedStatement pst=con.prepareStatement(query);
                pst.setInt(1,Integer.parseInt(jLabel49.getText()));
                ResultSet rs=pst.executeQuery();
                while(rs.next()){
                String bStatus=rs.getString("sTimeSec");
                System.out.println(bStatus);
                
                }
                
                con.close();*/
                
                
                
                /* } else{
                JOptionPane.showMessageDialog(this, "Hrs or Min field is EMPTY!");
                
                }
            */  //
                }   }
            else{
                JOptionPane.showMessageDialog(this, "Hrs or Min field is EMPTY!");
            }
                
        } catch (ParseException ex) {
            Logger.getLogger(appointments.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(appointments.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jTable14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable14MouseClicked
        DefaultTableModel model =(DefaultTableModel) jTable14.getModel();
        DefaultTableModel model1 =(DefaultTableModel) jTable12.getModel();

        String name  = model1.getValueAt(jTable12.getSelectedRow(),1).toString();
        String date  = model.getValueAt(jTable14.getSelectedRow(),1).toString();
        String stime = model.getValueAt(jTable14.getSelectedRow(),2).toString();
        String sid2  = model.getValueAt(jTable14.getSelectedRow(),0).toString();
        String eTime = model.getValueAt(jTable14.getSelectedRow(),3).toString();

        jLabel43.setText(name);
        jLabel45.setText(date);
        jLabel49.setText(sid2);
        jLabel50.setText(stime);
        jLabel52.setText(eTime);

    }//GEN-LAST:event_jTable14MouseClicked

    private void jTable12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable12MouseClicked
        DefaultTableModel model =(DefaultTableModel) jTable12.getModel();
        String empno=model.getValueAt(jTable12.getSelectedRow(),0).toString();
        String query="select s.sessionID,s.date,s.startTime,s.endTime,s.noOfApp,s.avilableApp,s.status,s.action from session s,doctor d where s.empNo=d.empNo AND s.date >= CURDATE() AND d.empNo=?";
        search(empno,jTable14,query);
        /*    try{
            Connection con=DBconnect.getConnection();
            String empno=model.getValueAt(jTable12.getSelectedRow(),0).toString();
            String query="select s.sessionID, d.name,d.Speciality,s.date,s.startTime,s.endTime,s.noOfApp,s.avilableApp from session s,doctor d where s.empNo=d.empNo AND d.empNo=?";
            PreparedStatement pst=con.prepareStatement(query);
            pst.setString(1,empno);
            rs=pst.executeQuery();
            jTable14.setModel(DbUtils.resultSetToTableModel(rs));

        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }*/

    }//GEN-LAST:event_jTable12MouseClicked

    private void docSearch2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_docSearch2KeyReleased
        String query="select empNo,name,Speciality,Gender  from doctor where  Speciality=? AND name like ? ";
        String sp=spcombo22.getSelectedItem().toString();
        String name=docSearch2.getText()+"%";
        search3(sp,name,jTable12,query);

    }//GEN-LAST:event_docSearch2KeyReleased

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
       
        tableload(jTable12);
        docSearch2.setText("");
        spcombo22.setSelectedIndex(0);
        spinnerH.setValue(0);
        spinnerM.setValue(0);
        informPat.setSelected(false);
       
        jTextField2.setText("");
        jLabel43.setText("");
        jLabel45.setText("");
        jLabel49.setText("");
        jLabel50.setText("");
        jLabel52.setText("");
        dm=(DefaultTableModel) jTable14.getModel();
        dm.setRowCount(0);
    }//GEN-LAST:event_jButton23ActionPerformed

    private void spcombo22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_spcombo22ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_spcombo22ActionPerformed

    private void spcombo22ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_spcombo22ItemStateChanged
        String selectsp =spcombo22.getSelectedItem().toString();
        String query="select empNo,name,Speciality,Gender  from doctor where Speciality=?";
        spsearch(selectsp,jTable12,query);

        /*   try{
            Connection con=DBconnect.getConnection();

            PreparedStatement pst=con.prepareStatement(query);
            pst.setString(1,selectsp);
            rs=pst.executeQuery();
            jTable12.setModel(DbUtils.resultSetToTableModel(rs));

        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        */
    }//GEN-LAST:event_spcombo22ItemStateChanged

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
       
        denyTelCheck1.setSelected(false); 
        sDate.setCalendar(null);
        java.util.Calendar cals1 = Calendar.getInstance();
        java.util.Date utilDateS1 = new java.util.Date(); // your util date
        cals1.setTime(utilDateS1);

        cals1.set(Calendar.HOUR_OF_DAY, 0);
        cals1.set(Calendar.MINUTE,0);

        java.sql.Date sTime = new java.sql.Date(cals1.getTime().getTime()); // your sql date

        spinnerS.setValue(sTime);
        spinnerE.setValue(sTime);
        jSpinner2.setValue(0);
        

    }//GEN-LAST:event_jButton29ActionPerformed

    private void jTable7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable7MouseClicked
        denyTelCheck2.setSelected(false);
        sun.setSelected(false);
        mon.setSelected(false);
        tue.setSelected(false);
        wed.setSelected(false);
        thu.setSelected(false);
        fri.setSelected(false);
        sat.setSelected(false);
        
        eDate1.setCalendar(null);
        sDate1.setCalendar(null);
        sessionVisibility();
        DefaultTableModel model =(DefaultTableModel) jTable7.getModel();

        try{
            Connection con=DBconnect.getConnection();
            String empno=model.getValueAt(jTable7.getSelectedRow(),0).toString();
            String query1="select  s.sessionID,d.empNo,s.date,s.startTime,s.endTime,s.noOfApp,s.avilableApp,s.TelBooking from session s,doctor d where s.empNo=d.empNo AND s.date >= CURDATE() AND d.empNo=? order by s.date";
            PreparedStatement pst=con.prepareStatement(query1);
            pst.setString(1,empno);
            rs=pst.executeQuery();
            jTable4.setModel(DbUtils.resultSetToTableModel(rs));

        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }//GEN-LAST:event_jTable7MouseClicked

    private void docSearch1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_docSearch1KeyReleased

        String query="select empNo,name,Speciality,Gender  from doctor where  Speciality=? AND name like ? ";
        String sp=spcombo21.getSelectedItem().toString();
        String name=docSearch1.getText()+"%";
        search3(sp,name,jTable7,query);
    }//GEN-LAST:event_docSearch1KeyReleased

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
       /*tableload(jTable7);
        docSearch1.setText("");
        spcombo21.setSelectedIndex(0);
        sDate.setCalendar(null);
     
        denyTelCheck2.setSelected(false);
        denyTelCheck1.setSelected(false);
        sun.setSelected(false);
        mon.setSelected(false);
        tue.setSelected(false);
        wed.setSelected(false);
        thu.setSelected(false);
        fri.setSelected(false);
        sat.setSelected(false);
        eDate1.setCalendar(null);
        sDate1.setCalendar(null);
        sessionVisibility();
         
        java.util.Calendar cals1 = Calendar.getInstance();
        java.util.Date utilDateS1 = new java.util.Date(); // your util date
        cals1.setTime(utilDateS1);

        cals1.set(Calendar.HOUR_OF_DAY, 0);
        cals1.set(Calendar.MINUTE,0);

        java.sql.Date sTime = new java.sql.Date(cals1.getTime().getTime()); // your sql date

        spinnerS.setValue(sTime);
        spinnerE.setValue(sTime);
        jSpinner2.setValue(0);
        dm=(DefaultTableModel) jTable4.getModel();
        dm.setRowCount(0);
   */     
      reloadCreateSes();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void spcombo21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_spcombo21ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_spcombo21ActionPerformed

    private void spcombo21ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_spcombo21ItemStateChanged

        String selectsp =spcombo21.getSelectedItem().toString();
        String query="select empNo,name,Speciality,Gender  from doctor where Speciality=?";
        spsearch(selectsp,jTable7,query);

    }//GEN-LAST:event_spcombo21ItemStateChanged

    private void spcombo1spcombo2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_spcombo1spcombo2ItemStateChanged
     String query="select d.empNo,d.name,d.Speciality,s.sessionID,s.date,s.startTime,s.noOfApp,s.avilableApp,s.TelBooking,s.action "
                      + "from session s,doctor d where s.empNo=d.empNo AND d.Speciality=? AND s.status='Available' AND "
                      + "s.date >= CURDATE() order by s.date" ;
        String select=spcombo1.getSelectedItem().toString();
        spsearch(select,jTable2,query); 
    }//GEN-LAST:event_spcombo1spcombo2ItemStateChanged

    private void spcombo1spcombo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_spcombo1spcombo2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_spcombo1spcombo2ActionPerformed

    private void docSearchdocSearch5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_docSearchdocSearch5KeyReleased
       /* String query="select d.empNo,d.name,d.Speciality,s.sessionID,s.date,s.startTime,s.noOfApp,s.avilableApp,s.TelBooking,s.action "
                      + "from session s,doctor d where s.empNo=d.empNo AND d.Speciality=? AND s.status='Available' AND "
                      + "s.date >= CURDATE()AND d.name like ? order by s.date" ;
        
        String sp=spcombo1.getSelectedItem().toString();
        String name=docSearch.getText()+"%";
        search3(sp,name,jTable2,query);
       */
        if(spcombo1.getSelectedIndex()>0){
        String query="select d.empNo,d.name,d.Speciality,s.sessionID,s.date,s.startTime,s.noOfApp,s.avilableApp,s.TelBooking,s.action "
                      + "from session s,doctor d where s.empNo=d.empNo AND d.Speciality=? AND s.status='Available' AND "
                      + "s.date >= CURDATE()AND d.name like ? order by s.date" ;
        //String select=spcombo1.getSelectedItem().toString();
       // String query="select d.empNo,d.name,d.Speciality,s.sessionID,s.date,s.startTime,s.noOfApp,s.avilableApp,s.action from session s,doctor d where s.empNo=d.empNo AND d.speciality=? AND d.name like ? AND s.status='Available'" ;
        String sp=spcombo1.getSelectedItem().toString();
        String name=docSearch.getText()+"%";
        search3(sp,name,jTable2,query);
       }
       else{
           String query="select d.empNo,d.name,d.Speciality,s.sessionID,s.date,s.startTime,s.noOfApp,s.avilableApp,s.TelBooking,s.action "
                      + "from session s,doctor d where s.empNo=d.empNo AND s.status='Available' AND "
                      + "s.date >= CURDATE()AND d.name like ? order by s.date";
            String name=docSearch.getText()+"%";
           search(name,jTable2,query); 
            
       }
    
    }//GEN-LAST:event_docSearchdocSearch5KeyReleased

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
    try {
            DefaultTableModel model =(DefaultTableModel) jTable2.getModel();
            
            
            Connection con = DBconnect.getConnection();
           
            //int sid=Integer.parseInt(model.getValueAt(jTable2.getSelectedRow(),3).toString());
            String name =model.getValueAt(jTable2.getSelectedRow(),1).toString();
            String date =model.getValueAt(jTable2.getSelectedRow(),4).toString();
            String time =model.getValueAt(jTable2.getSelectedRow(),5).toString();
            String sid2 =model.getValueAt(jTable2.getSelectedRow(),3).toString();
           // int sid=Integer.parseInt(jLabel37.getText());
            String query4="select d.doctorFee from doctor d,session s where d.empNo=s.empNo AND s.sessionID=?";
            PreparedStatement pst4=con.prepareStatement(query4);
            pst4.setInt(1,Integer.parseInt(sid2));
            ResultSet rs4=pst4.executeQuery();
            
            while(rs4.next()){
                float f=rs4.getFloat("doctorFee");
                float fee=f+hospitalCharges;
                String fee2=Float.toString(fee);
                
                
                String query="select a.apRefNo,a.appNo,a.time,p.name,a.source,a.billStatus from appointment a,patient p where a.patientID=p.patientID and a.sessionID= ? ";
                //s.sessionID=a.sessionID,a.patientID=p.patientID ,
                /*PreparedStatement pst=con.prepareStatement(query);
                
                pst.setInt(1,Integer.parseInt(sid2));
                rs=pst.executeQuery();
                
                jTable6.setModel(DbUtils.resultSetToTableModel(rs));*/
                //search2(Integer.parseInt(sid2),jTable6,query);
                jLabel105.setText(name);
                jLabel106.setText(date);
                jLabel109.setText(time);
                jLabel37.setText(sid2);
                jLabel55.setText(fee2);
                search2(Integer.parseInt(jLabel37.getText()),jTable6,query);
                
                
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
       
                                       
    }//GEN-LAST:event_jTable2MouseClicked

    private void jButton44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton44ActionPerformed
    
            reloadNewApp();
         
    }//GEN-LAST:event_jButton44ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        try {
            //get PiD from selected row and place the appointment-----------------------------------------------------
        if(jTable2.getSelectedRow()>-1) {
               
              if(jTable5.getSelectedRow()>-1){
                 if(w.isSelected()||p.isSelected()){
                 
            Session s1=new Session();
            
            
           
            DefaultTableModel model =(DefaultTableModel) jTable2.getModel();
            DefaultTableModel model2 =(DefaultTableModel) jTable5.getModel();
            
            
            
            Connection con = DBconnect.getConnection(); //--------------------------------------------------------------------------------------------------------
            int sid=Integer.parseInt(model.getValueAt(jTable2.getSelectedRow(),3).toString());
            int pid=Integer.parseInt(model2.getValueAt(jTable5.getSelectedRow(),0).toString());
            int appNo=s1.generateAppNo(sid);
            float fee=Float.parseFloat(jLabel55.getText());
             String source = null;
             if(w.isSelected())
                 source = "Walkin";
            else if(p.isSelected())
               source = "Phone";
           
          
    
            String query1="select action ,endTime,date,empNo,TelBooking from session where sessionID=?";
            PreparedStatement pst=con.prepareStatement(query1);
            pst.setInt(1,sid);
            rs=pst.executeQuery();
            
            while(rs.next()){
                String action=rs.getString("action");
                Time dbTime=rs.getTime("endTime");
                
                String empNo=rs.getString("empNo");
                String TelBooking=rs.getString("TelBooking");
                long dbLong =dbTime.getTime();
                long now=System.currentTimeMillis();
                
                boolean enterApp=false;
                if(TelBooking.equals("Allow")){
                
                    enterApp=true;
                }
                else if (TelBooking.equals("Deny") && source.equals("Walkin"))
                    enterApp=true;
                
             if(enterApp)   {
                java.sql.Date dbDate=rs.getDate("date");
                java.sql.Date today=  new Date(System.currentTimeMillis());
                int x=today.compareTo(dbDate);
                
              
                
                if(action.equals("UnFilled")){
                    //if(x<=0){
                       // if(dbLong>=now){
                            String aprxTime=s1.calAprxTime(sid);
            
                            s1.EnterAppointment(aprxTime, appNo, sid, pid,fee,source);
                            s1.UpdateAvailableApp(sid);
                            s1.UpdateCurrentApp(sid, appNo);
                            String query4="select contactNo ,name from patient where patientID=?";
                            String query5="select name from doctor where empNo=?";
                            PreparedStatement pst4=con.prepareStatement(query4);
                            PreparedStatement pst5=con.prepareStatement(query5);
                            pst4.setInt(1,pid);
                            pst5.setString(1,empNo);
                                rs=pst4.executeQuery();
                                
                                 while(rs.next()){
                                 String contactNo1=rs.getString("contactNo");
                                 String contactNo="+94"+contactNo1.substring(1);
                                 
                                 String patName= rs.getString("name");
                                 ResultSet rs2=pst5.executeQuery();
                                 while(rs2.next()){
                                 String docName=rs2.getString("name");
                            
                                String message="Dear "+patName+" Your Appointment No."+appNo+" with Dr."+docName+" on "+dbDate+" at "+aprxTime+" is confirmed. Deegayuu-Kuliyapitiya";
                              
                               s1.alertSMS(message,contactNo);
                            
                            //CHECK THIS IF AVAILABLE APPOINTMENTS >= TOTAL APPOINTMENTS ACTION COLUMN IN SESSION TABLE SHOULD BE
                            //UPDATED AS FILLED
                            String query3="select avilableApp,noOfApp from session where sessionID=?";
                            PreparedStatement pst1=con.prepareStatement(query3);
                            pst1.setInt(1,sid);
                            ResultSet rs1=pst1.executeQuery();
                            while(rs1.next()){
                                int totalApp=rs1.getInt("noOfApp");
                                int availableApp=rs1.getInt("avilableApp");
                                if(availableApp==totalApp){
                                    s1.UpdateFillAction(sid);
                                    
                                }
                            
                            }
                           
                      /*  }
                        else{
                            JOptionPane.showMessageDialog(this,"Session has been passed");
                        } */
                  /*  }
                    else{
                    JOptionPane.showMessageDialog(this,"Day has been passed");
                    }*/
                  sessionTableload(jTable2);
                  w.setSelected(true);
                }}}
                else{
                    JOptionPane.showMessageDialog(this,"Cannot place the appointment.Session is Full","SESSION FULL",JOptionPane.ERROR_MESSAGE);
                }
             }
             else {
                 JOptionPane.showMessageDialog(this,"Telephone Bookings are not ALLOWED","ERROR",JOptionPane.ERROR_MESSAGE );
             }
            }
           
            //table load
            // DefaultTableModel model =(DefaultTableModel) jTable2.getModel();
            //int sid2=Integer.parseInt(model.getValueAt(jTable2.getSelectedRow(),3).toString());
            int sid3 =Integer.parseInt(jLabel37.getText());
            String query="select a.apRefNo, a.appNo,a.time,p.name,a.source,a.billStatus from appointment a,patient p where a.patientID=p.patientID and a.sessionID= ? ";
   
            search2(sid3,jTable6,query);
            reloadCancelApp();
                }
                    else{
                        JOptionPane.showMessageDialog(this,"Select SOURCE before placing an appointment","ERROR",JOptionPane.ERROR_MESSAGE );
                        } 
              }   else{
                        JOptionPane.showMessageDialog(this,"Select PATIENT before placing an appointment","ERROR",JOptionPane.ERROR_MESSAGE );
                        } 
           }else{
               JOptionPane.showMessageDialog(this,"Select a SESSION before placing an appointment","ERROR",JOptionPane.ERROR_MESSAGE );
           }
         } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_jButton18ActionPerformed

    private void docSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_docSearchActionPerformed
        String query="select d.empNo,d.name,d.Speciality,s.sessionID,s.date,s.startTime,s.noOfApp,s.avilableApp,s.action "
                      + "from session s,doctor d where s.empNo=d.empNo AND d.Speciality=? AND s.status='Available' AND "
                      + "s.date >= CURDATE()AND d.name like ?" ;
        //String select=spcombo1.getSelectedItem().toString();
       // String query="select d.empNo,d.name,d.Speciality,s.sessionID,s.date,s.startTime,s.noOfApp,s.avilableApp,s.action from session s,doctor d where s.empNo=d.empNo AND d.speciality=? AND d.name like ? AND s.status='Available'" ;
        String sp=spcombo1.getSelectedItem().toString();
        String name=docSearch.getText()+"%";
        search3(sp,name,jTable2,query);
       
    }//GEN-LAST:event_docSearchActionPerformed

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
      // String q = jTextField2.getText();
        String query="select patientID,name,dob,gender,contactNo from patient where contactNo like ?" ;
        //String select=spcombo1.getSelectedItem().toString();
       // String query="select d.empNo,d.name,d.Speciality,s.sessionID,s.date,s.startTime,s.noOfApp,s.avilableApp,s.action from session s,doctor d where s.empNo=d.empNo AND d.speciality=? AND d.name like ? AND s.status='Available'" ;
        
        String contactNo=jTextField2.getText()+"%";
        search(contactNo,jTable5   ,query);
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jTextField29KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField29KeyReleased
       
        String query="select patientID,name,dob,gender,contactNo from patient where contactNo like ?" ;
      
        
        String contactNo=jTextField29.getText()+"%";
        search(contactNo,jTable16  ,query);
    }//GEN-LAST:event_jTextField29KeyReleased

    private void jButton46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton46ActionPerformed
     try {
            DefaultTableModel model =(DefaultTableModel) jTable17.getModel();
            
            // TODO add your handling code here:
            Connection con = DBconnect.getConnection();
            String appRef=model.getValueAt(jTable17.getSelectedRow(),0).toString();
           // int sid=Integer.parseInt(model.getValueAt(jTable17.getSelectedRow(),1).toString());
            
            String query="select billStatus ,sessionID ,patientID from appointment where apRefNo=?";
            PreparedStatement pst=con.prepareStatement(query);
            pst.setString(1,appRef);
            
            rs=pst.executeQuery();
            Session s1= new Session();
            while(rs.next()){
                
            String bStatus=rs.getString("billStatus");
            int sid=rs.getInt("sessionID");
            int patid=rs.getInt("patientID");
            
             if(bStatus.equals("UnBilled")){

                        s1.deleteAppointment(appRef);
                        s1.deleteAvailableApp(sid);
                         String query3="select avilableApp,noOfApp from session where sessionID=?";
                            PreparedStatement pst1=con.prepareStatement(query3);
                            pst1.setInt(1,sid);
                            ResultSet rs1=pst1.executeQuery();
                            while(rs1.next()){
                                int totalApp=rs1.getInt("noOfApp");
                                int availableApp=rs1.getInt("avilableApp");
                                if(availableApp<totalApp){
                                    s1.UpdateUnFillAction(sid);
                                }
                            
            }}
             else{
                        s1.refund(appRef);
                        s1.deleteAppointment(appRef);
                        s1.deleteAvailableApp(sid);
                            String query3="select avilableApp,noOfApp from session where sessionID=?";
                            PreparedStatement pst1=con.prepareStatement(query3);
                            pst1.setInt(1,sid);
                            ResultSet rs1=pst1.executeQuery();
                            while(rs1.next()){
                                int totalApp=rs1.getInt("noOfApp");
                                int availableApp=rs1.getInt("avilableApp");
                                if(availableApp<totalApp){
                                    s1.UpdateUnFillAction(sid);
                                }
                            
            }
             
             }
            
                 
        
        String query2="select a.apRefNo,s.sessionID,d.empNo,d.name,a.appNo,a.time,a.date,a.fee,a.billStatus from appointment a,doctor d,session s where a.sessionID=s.sessionID and s.empNo=d.empNo and a.date >= CURDATE() AND a.patientID = ?";
        search2(patid,jTable17,query2);
        reloadNewApp();
            }
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_jButton46ActionPerformed

    private void jButton47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton47ActionPerformed
            DefaultTableModel model =(DefaultTableModel) jTable17.getModel();
            DefaultTableModel model2 =(DefaultTableModel) jTable16.getModel();
            DefaultTableModel model3 =(DefaultTableModel) jTable18.getModel();
            String appRef=model.getValueAt(jTable17.getSelectedRow(),0).toString();
            int pid=Integer.parseInt(model2.getValueAt(jTable16.getSelectedRow(),0).toString());
            int newSid=Integer.parseInt(model3.getValueAt(jTable18.getSelectedRow(),0).toString());
            String TelBooking=model3.getValueAt(jTable16.getSelectedRow(),5).toString();
           String docName=jLabel119.getText(); 
          
           String source=null;
           
            if(w1.isSelected())
                 source = "Walkin";
            else if(p1.isSelected())
                 source = "Phone";
            boolean enterApp=false;
                if(TelBooking.equals("Allow")){
                
                    enterApp=true;
                }
                else if (TelBooking.equals("Deny") && source.equals("Walkin"))
                    enterApp=true;
          Session s1=new Session();
          
          s1.reScheduleAppointment(appRef, newSid, source, docName,enterApp);
     
        
        /*    try {  Session s2=new Session();
                
            DefaultTableModel model =(DefaultTableModel) jTable17.getModel();
            DefaultTableModel model2 =(DefaultTableModel) jTable16.getModel();
            DefaultTableModel model3 =(DefaultTableModel) jTable18.getModel();
            
            Connection con = DBconnect.getConnection();
            int appRef=Integer.parseInt(model.getValueAt(jTable17.getSelectedRow(),0).toString());
            
            int pid=Integer.parseInt(model2.getValueAt(jTable16.getSelectedRow(),0).toString());
            int newSid=Integer.parseInt(model3.getValueAt(jTable18.getSelectedRow(),0).toString());
           float  fee=Float.parseFloat(model.getValueAt(jTable17.getSelectedRow(),7).toString());
          
            
            //APPOINTMENT REMOVING PROCESS 
            String query="select sessionID ,patientID from appointment where apRefNo=?";
            PreparedStatement pst=con.prepareStatement(query);
            pst.setInt(1,appRef);
            
            rs=pst.executeQuery();
         
            while(rs.next()){
                
            
            int sid=rs.getInt("sessionID");
            int patid=rs.getInt("patientID");
            
             

                        s2.deleteAppointment(appRef);
                        s2.deleteAvailableApp(sid);
                         String query3="select avilableApp,empNo,action,date,noOfApp from session where sessionID=?";
                            PreparedStatement pst1=con.prepareStatement(query3);
                            pst1.setInt(1,sid);
                            ResultSet rs1=pst1.executeQuery();
                            while(rs1.next()){
                                int totalApp=rs1.getInt("noOfApp");
                                int availableApp=rs1.getInt("avilableApp");
                                String empNo=rs1.getString("empNo");
                                if(availableApp<totalApp){
                                    s2.UpdateUnFillAction(sid);
                                
                            
           ////APPOINTMENT RE ENTERING PROCESS
           
                String source = null;
             if(w1.isSelected())
                 source = "Walkin";
            else if(p1.isSelected())
               source = "Phone";
                int appNo=s2.generateAppNo(newSid);
                String action=rs1.getString("action");
                java.sql.Date dbDate=rs1.getDate("date");
                java.sql.Date today=  new Date(System.currentTimeMillis());
                int x=today.compareTo(dbDate);
                
              
                
                if(action.equals("UnFilled")){
                    
                            String aprxTime=s2.calAprxTime(sid);
            
                            s2.EnterAppointment(aprxTime, appNo, sid, pid,fee,source);
                            s2.UpdateAvailableApp(sid);
                            s2.UpdateCurrentApp(sid, appNo);
                         //sending a SMS to patient   
                            String query4="select contactNo ,name from patient where patientID=?";
                            PreparedStatement pst4=con.prepareStatement(query4);
                            pst4.setInt(1,pid);
                            
                                rs=pst4.executeQuery();
                                
                                 while(rs.next()){
                                 String contactNo=rs.getString("contactNo");
                                 String patName= rs.getString("name");
                                 String docName=jLabel119.getText();
                            
                                String message="Dear "+patName+" Your Re-Scheduled Appointment No."+appNo+" with Dr."+docName+" on "+dbDate+" at "+aprxTime+" is confirmed. Deegayuu-Kuliyapitiya";
                                System.out.println(contactNo+message);
                             //   s1.alertSMS(message,contactNo); 
                            
                            //CHECK THIS IF AVAILABLE APPOINTMENTS >= TOTAL APPOINTMENTS ACTION COLUMN IN SESSION TABLE SHOULD BE
                            //UPDATED AS FILLED
                           // String query4="select avilableApp,noOfApp from session where sessionID=?";
                          
                            
                                totalApp=rs1.getInt("noOfApp");
                                 availableApp=rs1.getInt("avilableApp");
                                if(availableApp==totalApp){
                                    s2.UpdateFillAction(sid);
                                    
                                }
                            
                         
                           
                      /*  }
                        else{
                            JOptionPane.showMessageDialog(this,"Session has been passed");
                        } */
                  /*  }
                    else{
                    JOptionPane.showMessageDialog(this,"Day has been passed");
                    }
                }}
                else{
                    JOptionPane.showMessageDialog(this,"Cannot Re-Schedule the appointment.Session is Full");
                }
         }  */ 
                            
        
      String query2="select a.apRefNo,s.sessionID,d.empNo,d.name,a.appNo,a.time,a.date,a.fee,a.billStatus from appointment a,doctor d,session s where a.sessionID=s.sessionID and s.empNo=d.empNo and a.date >= CURDATE() AND a.patientID = ?";
      search2(pid,jTable17,query2);
      reloadNewApp();
     
            
      /*  } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, ex.getMessage());
        }*/
    }//GEN-LAST:event_jButton47ActionPerformed

    private void jTextField30KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField30KeyReleased
        String query="select patientID,name,dob,gender,contactNo from patient where name like ?" ;
      
        //String contactNo=jTextField29.getText();
        String name=jTextField30.getText()+"%";
        search(name,jTable16  ,query);
    }//GEN-LAST:event_jTextField30KeyReleased

    private void jTable16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable16MouseClicked
         
        DefaultTableModel model =(DefaultTableModel) jTable16.getModel();       
        int patID=Integer.parseInt(model.getValueAt(jTable16.getSelectedRow(),0).toString());
        String query="select a.apRefNo,s.sessionID,d.empNo,d.name,a.appNo,a.time,a.date,a.fee ,a.billStatus from appointment a,doctor d,session s where a.sessionID=s.sessionID and s.empNo=d.empNo and a.date >= CURDATE() AND a.patientID = ?";
        search2(patID,jTable17,query);
    }//GEN-LAST:event_jTable16MouseClicked

    private void jTextField28KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField28KeyReleased
       
        String query="select patientID,name,dob,gender,contactNo from patient where patientID like ?" ;
      
        
        String contactNo=jTextField28.getText()+"%";
        search(contactNo,jTable16  ,query);
    }//GEN-LAST:event_jTextField28KeyReleased

    private void jTable17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable17MouseClicked
            DefaultTableModel model =(DefaultTableModel) jTable17.getModel();
            String sid =model.getValueAt(jTable17.getSelectedRow(),1).toString();
            String docname =model.getValueAt(jTable17.getSelectedRow(),3).toString();
            String date =model.getValueAt(jTable17.getSelectedRow(),6).toString();
            String time =model.getValueAt(jTable17.getSelectedRow(),5).toString();
            String fee =model.getValueAt(jTable17.getSelectedRow(),7).toString();
            jLabel119.setText(docname);
            jLabel123.setText(sid);
            jLabel120.setText(date);
            jLabel54.setText(time);
            jLabel73.setText(fee);
    }//GEN-LAST:event_jTable17MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       String query="select s.sessionID,s.date,s.startTime,s.noOfApp,s.avilableApp,s.TelBooking "
                      + "from session s,doctor d where s.empNo=d.empNo AND s.status='Available' AND s.action='UnFilled' AND "
                      + "s.date >= CURDATE()AND s.empNo = ? order by s.date" ;
        
        DefaultTableModel model =(DefaultTableModel) jTable17.getModel();       
        String docID=model.getValueAt(jTable17.getSelectedRow(),2).toString();
        search(docID,jTable18,query);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton48ActionPerformed
       reloadCancelApp();
    }//GEN-LAST:event_jButton48ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
            Session s2 =new Session();
      
            DefaultTableModel model =(DefaultTableModel) jTable7.getModel();
         if(jTable7.getSelectedRow()>-1){     
             if(sDate1.getDate().before(eDate1.getDate())) {
            java.util.Date startDate=sDate1.getDate();
            java.util.Date endDate=eDate1.getDate();
            String empNo =model.getValueAt(jTable7.getSelectedRow(),0).toString();
            String TelBooking="Allow";
            if(denyTelCheck2.isSelected()){
              TelBooking="Deny";
            }
            
            if(sun.isSelected()){
                int noOfApp1=Integer.parseInt(appCount1.getValue().toString());
            
                JSpinner.DateEditor deS1=new JSpinner.DateEditor(spinnerS1,"HH:mm ");
                String sTime1 =deS1.getFormat().format(spinnerS1.getValue());
            
                JSpinner.DateEditor deE1=new JSpinner.DateEditor(spinnerE1,"HH:mm ");
                String eTime1 =deE1.getFormat().format(spinnerE1.getValue());
        
                 
                s2.createMultiSession(startDate, endDate,1,noOfApp1,sTime1,eTime1, empNo,TelBooking, jTable4);
                JOptionPane.showMessageDialog(null,"Sunday Sessions Created Successfully");
                
                
            }
            if(mon.isSelected()){
                int noOfApp2=Integer.parseInt(appCount2.getValue().toString());
            
                JSpinner.DateEditor deS2=new JSpinner.DateEditor(spinnerS2,"HH:mm ");
                String sTime2 =deS2.getFormat().format(spinnerS2.getValue());
            
                JSpinner.DateEditor deE2=new JSpinner.DateEditor(spinnerE2,"HH:mm ");
                String eTime2 =deE2.getFormat().format(spinnerE2.getValue());
                
                s2.createMultiSession(startDate, endDate,2,noOfApp2,sTime2,eTime2, empNo,TelBooking, jTable4);
                JOptionPane.showMessageDialog(null,"Monday Sessions Created Successfully");
                
            }
            if(tue.isSelected()){
                int noOfApp3=Integer.parseInt(appCount3.getValue().toString());
            
                JSpinner.DateEditor deS3=new JSpinner.DateEditor(spinnerS3,"HH:mm ");
                String sTime3 =deS3.getFormat().format(spinnerS3.getValue());
            
                JSpinner.DateEditor deE3=new JSpinner.DateEditor(spinnerE3,"HH:mm ");
                String eTime3 =deE3.getFormat().format(spinnerE3.getValue());
                
                s2.createMultiSession(startDate, endDate,3,noOfApp3,sTime3,eTime3, empNo,TelBooking, jTable4);
                JOptionPane.showMessageDialog(null,"Tuesday Sessions Created Successfully");
                
                
            }
            if(wed.isSelected()){
                int noOfApp4=Integer.parseInt(appCount4.getValue().toString());
            
                JSpinner.DateEditor deS4=new JSpinner.DateEditor(spinnerS4,"HH:mm ");
                String sTime4 =deS4.getFormat().format(spinnerS4.getValue());
            
                JSpinner.DateEditor deE4=new JSpinner.DateEditor(spinnerE4,"HH:mm ");
                String eTime4 =deE4.getFormat().format(spinnerE4.getValue());
                
                s2.createMultiSession(startDate, endDate,4,noOfApp4,sTime4,eTime4, empNo,TelBooking, jTable4);
                JOptionPane.showMessageDialog(null,"Wednesday Sessions Created Successfully");
                
                
            }
            if(thu.isSelected()){
               int noOfApp5=Integer.parseInt(appCount5.getValue().toString());
            
                JSpinner.DateEditor deS5=new JSpinner.DateEditor(spinnerS5,"HH:mm ");
                String sTime5 =deS5.getFormat().format(spinnerS5.getValue());
            
                JSpinner.DateEditor deE5=new JSpinner.DateEditor(spinnerE5,"HH:mm ");
                String eTime5 =deE5.getFormat().format(spinnerE5.getValue());
                 
                s2.createMultiSession(startDate, endDate,5,noOfApp5,sTime5,eTime5, empNo,TelBooking, jTable4);
                JOptionPane.showMessageDialog(null,"Thursday Sessions Created Successfully");
                
                
            }
             if(fri.isSelected()){
                int noOfApp6=Integer.parseInt(appCount6.getValue().toString());
            
                JSpinner.DateEditor deS6=new JSpinner.DateEditor(spinnerS6,"HH:mm ");
                String sTime6 =deS6.getFormat().format(spinnerS6.getValue());
            
                JSpinner.DateEditor deE6=new JSpinner.DateEditor(spinnerE6,"HH:mm ");
                String eTime6 =deE6.getFormat().format(spinnerE6.getValue());
                  
                s2.createMultiSession(startDate, endDate,6,noOfApp6,sTime6,eTime6, empNo,TelBooking, jTable4);
               JOptionPane.showMessageDialog(null,"Friday Sessions Created Successfully");
                 
            }
              if(sat.isSelected()){
                int noOfApp7=Integer.parseInt(appCount7.getValue().toString());
            
                JSpinner.DateEditor deS7=new JSpinner.DateEditor(spinnerS7,"HH:mm");
                String sTime7 =deS7.getFormat().format(spinnerS7.getValue());
            
                JSpinner.DateEditor deE7=new JSpinner.DateEditor(spinnerE7,"HH:mm");
                String eTime7 =deE7.getFormat().format(spinnerE7.getValue());
                 
                s2.createMultiSession(startDate, endDate,7,noOfApp7,sTime7,eTime7, empNo,TelBooking, jTable4);
                JOptionPane.showMessageDialog(null,"Saturday Sessions Created Successfully");
                 
                
            }
              
         }
         else{
             JOptionPane.showMessageDialog(null,"From Date is greater than To Date","INVALID INPUT",JOptionPane.ERROR_MESSAGE); }
         } else{
             JOptionPane.showMessageDialog(null,"Select a Doctor before creating a session ","ERROR",JOptionPane.ERROR_MESSAGE);
         }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable11MouseClicked
        DefaultTableModel model =(DefaultTableModel) jTable11.getModel();
        String empNo =model.getValueAt(jTable11.getSelectedRow(),0).toString();
        dateCombo1.removeAllItems();
        dateCombo2.removeAllItems();
        timeCombo1.removeAllItems();
        timeCombo2.removeAllItems();
         jSpinner1.setValue(0);
       /// jSpinner2.setValue(0);
        
        dm=(DefaultTableModel) jTable15.getModel();
        dm.setRowCount(0);
        dm=(DefaultTableModel) jTable13.getModel();
        dm.setRowCount(0);
        fillDateCombo(empNo);
    }//GEN-LAST:event_jTable11MouseClicked

    private void dateCombo1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dateCombo1MouseClicked
      /*  try {
            DefaultTableModel model =(DefaultTableModel) jTable11.getModel();
            SimpleDateFormat day = new SimpleDateFormat("dd-MM-yyyy");
            String date = dateCombo1.getSelectedItem().toString();
            java.util.Date d1=day.parse(date);
            java.sql.Date d2=new java.sql.Date(d1.getTime());
            String empNo =model.getValueAt(jTable11.getSelectedRow(),0).toString();
            
          
            fillTimeCombo(empNo,d2,timeCombo1);
        } catch (ParseException ex) {
            Logger.getLogger(appointments.class.getName()).log(Level.SEVERE, null, ex);
        } */
    }//GEN-LAST:event_dateCombo1MouseClicked

    private void dateCombo2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dateCombo2MouseClicked
      
    }//GEN-LAST:event_dateCombo2MouseClicked

    private void dateCombo1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dateCombo1MouseEntered
    
                                          
    }//GEN-LAST:event_dateCombo1MouseEntered

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
         try {
            DefaultTableModel model =(DefaultTableModel) jTable11.getModel();
            SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd");
            String date = dateCombo1.getSelectedItem().toString();
            java.util.Date d1=day.parse(date);
            java.sql.Date d2=new java.sql.Date(d1.getTime());
            String empNo =model.getValueAt(jTable11.getSelectedRow(),0).toString();
            
          
            timeCombo1.removeAllItems();
            fillTimeCombo(empNo,d2,timeCombo1);
        } catch (ParseException ex) {
            Logger.getLogger(appointments.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
         try {
            DefaultTableModel model =(DefaultTableModel) jTable11.getModel();
            SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd");
            String date = dateCombo2.getSelectedItem().toString();
            java.util.Date d1=day.parse(date);
            java.sql.Date d2=new java.sql.Date(d1.getTime());
            String empNo =model.getValueAt(jTable11.getSelectedRow(),0).toString();
            
          
            timeCombo2.removeAllItems();
            fillTimeCombo(empNo,d2,timeCombo2);
        } catch (ParseException ex) {
            Logger.getLogger(appointments.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
               Connection con = DBconnect.getConnection();
                Session s1=new Session();
                DefaultTableModel model =(DefaultTableModel) jTable11.getModel();
                DefaultTableModel model2 =(DefaultTableModel) jTable15.getModel();
                
                String docName=model.getValueAt(jTable11.getSelectedRow(),1).toString();
                String empNo =model.getValueAt(jTable11.getSelectedRow(),0).toString();
                
          
        try {
            SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd");
            String date = dateCombo2.getSelectedItem().toString();
            java.util.Date d1=day.parse(date);
            java.sql.Date d2=new java.sql.Date(d1.getTime());
            String time=timeCombo2.getSelectedItem().toString();
            
            String query2="select a.apRefNo,a.appNo,a.time,p.name,a.billStatus,a.source from appointment a,patient p,Session s "
                    + "where a.patientID=p.patientID and a.sessionID=s.sessionID and s.empNo= ? and s.date=? and s.startTime=? order by a.appNo";
            
            String query="select sessionID ,avilableApp ,noOfApp from session where empNo= ? and date = ? and startTime =?";
            PreparedStatement pst=con.prepareStatement(query);
             pst.setString(1,empNo);
             pst.setDate(2,d2);  //newDate             
             pst.setString(3,time); //newTime                  
             ResultSet rs1=pst.executeQuery();//s2
           
        
           
            SimpleDateFormat day2 = new SimpleDateFormat("yyyy-MM-dd");
            String date3 = dateCombo1.getSelectedItem().toString();
            java.util.Date d3=day2.parse(date3);
            java.sql.Date d4=new java.sql.Date(d3.getTime());
            String time2=timeCombo1.getSelectedItem().toString();
            
            PreparedStatement pst3=con.prepareStatement(query);
             pst3.setString(1,empNo);
             pst3.setDate(2,d4);       //S1         
             pst3.setString(3,time2);   //S1                
            
            
             while(rs1.next()){
             int newSid=Integer.parseInt(rs1.getString("sessionID")); //S2
             int avApp=rs1.getInt("avilableApp");//S2
             int appTot=rs1.getInt("noOfApp");
             
           ResultSet rs2=pst3.executeQuery();//S1
                 while(rs2.next()){
                
                    int avApp1=rs2.getInt("avilableApp"); //S1
                    int oldSid=rs2.getInt("sessionID"); //oldSid
                    boolean enterApp=true;
  if(oldSid != newSid)  {         
     if(jTable15.getSelectedRow()> -1){    
        
            String appRef=model2.getValueAt(jTable15.getSelectedRow(),0).toString();
            String source=model2.getValueAt(jTable15.getSelectedRow(),5).toString();

            s1.reScheduleAppointment(appRef, newSid, source, docName,enterApp);
         
            search4(empNo,d4,time2,jTable15,query2);
            search4(empNo,d2,time,jTable13,query2);
            reloadNewApp();
            
        }
     else if(Integer.parseInt(jSpinner1.getValue().toString())>0){
         
         int spVal=Integer.parseInt(jSpinner1.getValue().toString());
         if(avApp1>= spVal){
             int i=0;
             int count=0;
             for(i=(avApp1-spVal);i<avApp1;i++){
              if(count<=appTot-avApp){
              String appRef2=model2.getValueAt(i,0).toString();
              String source2=model2.getValueAt(i,5).toString();

              s1.reScheduleAppointment(appRef2, newSid, source2, docName,enterApp);
                count++;
                search4(empNo,d4,time2,jTable15,query2);
                search4(empNo,d2,time,jTable13,query2);
                reloadNewApp();
              } 
              else
              {  JOptionPane.showMessageDialog(null,"Session is full cannot reSchedule the appointments","SESSION FULL",JOptionPane.ERROR_MESSAGE);}
                  
             }
         }
         else
         {  JOptionPane.showMessageDialog(null,"Entered No.of Appointments are exceeding the available no of appointments","ERROR",JOptionPane.ERROR_MESSAGE); }
             
       
    }
     else
     {  JOptionPane.showMessageDialog(null,"Select the appointment or enter no. of appointments to be moved","ERROR",JOptionPane.ERROR_MESSAGE); }
          
             
                 } 
        else{
         JOptionPane.showMessageDialog(null,"Selected Sesssion to be moved is similar to the current session. please select diffrent session!","SIMILAR SESSIONS",JOptionPane.ERROR_MESSAGE); 
          
                }} } }
        catch (ParseException ex) {
            Logger.getLogger(appointments.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"XXXXXXXXXX","XXXXXXXX",JOptionPane.ERROR_MESSAGE); 
        } 
        catch (SQLException ex) {
            Logger.getLogger(appointments.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton21ActionPerformed

    private void dateCombo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateCombo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateCombo1ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        Connection con = DBconnect.getConnection();
                Session s1=new Session();
                DefaultTableModel model =(DefaultTableModel) jTable11.getModel();
                DefaultTableModel model2 =(DefaultTableModel) jTable15.getModel();
                
                String docName=model.getValueAt(jTable11.getSelectedRow(),1).toString();
                String empNo =model.getValueAt(jTable11.getSelectedRow(),0).toString();
                
          
        try {
           
            
            String query2="select a.apRefNo,a.appNo,a.time,p.name,a.billStatus,a.source from appointment a,patient p,Session s where a.patientID=p.patientID and a.sessionID=s.sessionID and s.empNo= ? and s.date=? and s.startTime=? order by a.appNo";
            String query="select sessionID ,avilableApp ,noOfApp from session where empNo= ? and date = ? and startTime =?";
            
        
           
            SimpleDateFormat day2 = new SimpleDateFormat("yyyy-MM-dd");
            String date3 = dateCombo1.getSelectedItem().toString();
            java.util.Date d3=day2.parse(date3);
            java.sql.Date d4=new java.sql.Date(d3.getTime());
            String time2=timeCombo1.getSelectedItem().toString();
            
            PreparedStatement pst3=con.prepareStatement(query);
             pst3.setString(1,empNo);
             pst3.setDate(2,d4);       //S1         
             pst3.setString(3,time2);   //S1                
            
            
             
           ResultSet rs2=pst3.executeQuery();//S1
                 while(rs2.next()){
                
                    int avApp1=rs2.getInt("avilableApp"); //S1
               
             
     if(jTable15.getSelectedRow()> -1){    
        
            String appRef=model2.getValueAt(jTable15.getSelectedRow(),0).toString();
            

            s1.removeAppointment(appRef, docName);
         
            search4(empNo,d4,time2,jTable15,query2);
           
            reloadNewApp();
            
        }
     else if(Integer.parseInt(jSpinner1.getValue().toString())>0){
         
         int spVal=Integer.parseInt(jSpinner1.getValue().toString());
         if(avApp1>= spVal){
             int i=0;
            
             for(i=(avApp1-spVal);i<avApp1;i++){
              
                String appRef2=model2.getValueAt(i,0).toString();
                s1.removeAppointment(appRef2, docName); 
                search4(empNo,d4,time2,jTable15,query2);
                reloadNewApp();
             }
         }
         else
         {  JOptionPane.showMessageDialog(null,"Entered No.of Appointments are exceeding the available no of appointments","ERROR",JOptionPane.ERROR_MESSAGE); }
             
       
    }
     else
     {  JOptionPane.showMessageDialog(null,"select the appointment or enter no. of appointments to be Canceled","ERROR",JOptionPane.ERROR_MESSAGE); }
             
             
                 } } 
        catch (ParseException ex) {
            Logger.getLogger(appointments.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (SQLException ex) {
            Logger.getLogger(appointments.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        tableload(jTable11);
        jSpinner1.setValue(0);
      //  jSpinner2.setValue(0);
        timeCombo1.removeAllItems();
        timeCombo2.removeAllItems();
        dateCombo1.removeAllItems();
        dateCombo2.removeAllItems();
        dm=(DefaultTableModel) jTable15.getModel();
        dm.setRowCount(0);
        dm=(DefaultTableModel) jTable13.getModel();
        dm.setRowCount(0);
 
    }//GEN-LAST:event_jButton19ActionPerformed

    private void patSearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patSearch1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_patSearch1ActionPerformed

    private void patSearch1docSearch5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_patSearch1docSearch5KeyReleased
        String query="select patientID,name,dob,gender,contactNo from patient where name like ?" ;
        String name=patSearch1.getText()+"%";
        search(name,jTable5,query);
    }//GEN-LAST:event_patSearch1docSearch5KeyReleased

    private void jTextField27KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField27KeyReleased
         String query="select patientID,name,dob,gender,contactNo from patient where patientID like ?" ;
        
        String pid=jTextField27.getText()+"%";
        search(pid,jTable5,query);
    }//GEN-LAST:event_jTextField27KeyReleased

    private void spcombo24ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_spcombo24ItemStateChanged
        String query= "select empNo,name,Speciality,Gender  from doctor where  Speciality=?  ";
        String select=spcombo24.getSelectedItem().toString();
        search(select,jTable11,query); 
    }//GEN-LAST:event_spcombo24ItemStateChanged

    private void dateCombo1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_dateCombo1ItemStateChanged
    
    }//GEN-LAST:event_dateCombo1ItemStateChanged

    private void timeCombo1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_timeCombo1ItemStateChanged
       try {
            DefaultTableModel model =(DefaultTableModel) jTable11.getModel();
            String empNo =model.getValueAt(jTable11.getSelectedRow(),0).toString();
            SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd");
            String date = dateCombo1.getSelectedItem().toString();
            java.util.Date d1=day.parse(date);
            java.sql.Date d2=new java.sql.Date(d1.getTime());
            String time=timeCombo1.getSelectedItem().toString();
            String query="select a.apRefNo,a.appNo,a.time,p.name,a.billStatus,a.source from appointment a,patient p,Session s where a.patientID=p.patientID and a.sessionID=s.sessionID and s.empNo= ? and s.date=? and s.startTime=? order by a.appNo";
            search4(empNo,d2,time,jTable15,query);
        } catch (ParseException ex) {
            Logger.getLogger(appointments.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_timeCombo1ItemStateChanged

    private void timeCombo2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_timeCombo2ItemStateChanged
       try {
            DefaultTableModel model =(DefaultTableModel) jTable11.getModel();
            String empNo =model.getValueAt(jTable11.getSelectedRow(),0).toString();
            SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd");
            String date = dateCombo2.getSelectedItem().toString();
            java.util.Date d1=day.parse(date);
            java.sql.Date d2=new java.sql.Date(d1.getTime());
            String time=timeCombo2.getSelectedItem().toString();
            String query="select a.apRefNo,a.appNo,a.time,p.name,a.billStatus, a.source from appointment a,patient p,Session s where a.patientID=p.patientID and a.sessionID=s.sessionID and s.empNo= ? and s.date=? and s.startTime=? order by a.appNo";
            search4(empNo,d2,time,jTable13,query);
        } catch (ParseException ex) {
            Logger.getLogger(appointments.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_timeCombo2ItemStateChanged

    private void sessionBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sessionBActionPerformed
        // TODO add your handling code here:
        if(jTable7.getSelectedRow()>-1){
          Session s2 =new Session();
        
            DefaultTableModel model =(DefaultTableModel) jTable7.getModel();
            
            String TelBooking="Allow";
            if(denyTelCheck1.isSelected()){
              TelBooking="Deny";
            }
          
            
            java.util.Date dd=sDate.getDate();
            String empNo =model.getValueAt(jTable7.getSelectedRow(),0).toString();
            int noOfApp=Integer.parseInt(jSpinner2.getValue().toString());
            
            JSpinner.DateEditor deS=new JSpinner.DateEditor(spinnerS,"HH:mm ");
            String sTime =deS.getFormat().format(spinnerS.getValue());
            
            JSpinner.DateEditor deE=new JSpinner.DateEditor(spinnerE,"HH:mm ");
            String eTime =deE.getFormat().format(spinnerE.getValue());
        
        int i=s2.createSession2(dd,dd,dd, noOfApp, sTime,eTime, empNo,TelBooking, jTable4);
        if(i>0){
             JOptionPane.showMessageDialog(null,"Session created Successfully");
            
        }
        else{
            JOptionPane.showMessageDialog(null,"Session cannot be created","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        
    }else{
            JOptionPane.showMessageDialog(null,"Please select the Doctor before Placing an appointment","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        
        
    }//GEN-LAST:event_sessionBActionPerformed

    private void sunItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_sunItemStateChanged
        spinnerS1.setVisible(true);
        spinnerE1.setVisible(true);
        appCount1.setVisible(true);
    }//GEN-LAST:event_sunItemStateChanged

    private void monItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_monItemStateChanged
        spinnerS2.setVisible(true);
        spinnerE2.setVisible(true);
        appCount2.setVisible(true);
    }//GEN-LAST:event_monItemStateChanged

    private void tueItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tueItemStateChanged
        spinnerS3.setVisible(true);
        spinnerE3.setVisible(true);
        appCount3.setVisible(true);
    }//GEN-LAST:event_tueItemStateChanged

    private void wedItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_wedItemStateChanged
        spinnerS4.setVisible(true);
        spinnerE4.setVisible(true);
        appCount4.setVisible(true);
    }//GEN-LAST:event_wedItemStateChanged

    private void thuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_thuItemStateChanged
        spinnerS5.setVisible(true);
        spinnerE5.setVisible(true);
        appCount5.setVisible(true);
    }//GEN-LAST:event_thuItemStateChanged

    private void friItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_friItemStateChanged
         spinnerS6.setVisible(true);
        spinnerE6.setVisible(true);
        appCount6.setVisible(true);
    }//GEN-LAST:event_friItemStateChanged

    private void satItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_satItemStateChanged
       spinnerS7.setVisible(true);
        spinnerE7.setVisible(true);
        appCount7.setVisible(true);
    }//GEN-LAST:event_satItemStateChanged

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        //tableload(jTable7);
        denyTelCheck2.setSelected(false);
        sun.setSelected(false);
        mon.setSelected(false);
        tue.setSelected(false);
        wed.setSelected(false);
        thu.setSelected(false);
        fri.setSelected(false);
        sat.setSelected(false);
        eDate1.setCalendar(null);
        sDate1.setCalendar(null);
        sessionVisibility();
      //  dm=(DefaultTableModel) jTable4.getModel();
       // dm.setRowCount(0);
        
    }//GEN-LAST:event_jButton31ActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
      java.util.Date fromDate=jDateChooser5.getDate();
        java.util.Date toDate=jDateChooser6.getDate();

        java.sql.Date fromSQL=new java.sql.Date(fromDate.getTime());
        java.sql.Date toSQL=new java.sql.Date(toDate.getTime());
        
        if(spcombo1.getSelectedIndex()>0){
        String query="select d.empNo,d.name,d.Speciality,s.sessionID,s.date,s.startTime,s.noOfApp,s.avilableApp,s.TelBooking,s.action "
                      + "from session s,doctor d where s.empNo=d.empNo AND d.Speciality=? AND s.status='Available' AND "
                      + "s.date >= CURDATE() and s.date BETWEEN ? and ? order by s.date" ;
        String select=spcombo1.getSelectedItem().toString();
        
        try{
            Connection con=DBconnect.getConnection();
       
            PreparedStatement pst=con.prepareStatement(query); 
            pst.setString(1,select);
            pst.setDate(2,fromSQL);
            pst.setDate(3,toSQL);
            
            rs=pst.executeQuery();
            jTable2.setModel(DbUtils.resultSetToTableModel(rs));
          }
         
        
        catch(SQLException ex){
               JOptionPane.showMessageDialog(null, ex.getMessage());
        }
      }
        if (spcombo1.getSelectedIndex()>0 && !(docSearch.getText() == null)){
             String query="select d.empNo,d.name,d.Speciality,s.sessionID,s.date,s.startTime,s.noOfApp,s.avilableApp,s.TelBooking,s.action "
                      + "from session s,doctor d where s.empNo=d.empNo AND d.Speciality=? AND s.status='Available' AND "
                      + "s.date >= CURDATE()AND d.name like ? and s.date BETWEEN ? and ? order by s.date" ;
       
        String sp=spcombo1.getSelectedItem().toString();
        String name=docSearch.getText()+"%";
        try{
            Connection con=DBconnect.getConnection();
            
            PreparedStatement pst=con.prepareStatement(query); 
            pst.setString(1,sp);
            pst.setString(2,name);
            pst.setDate(3,fromSQL);
            pst.setDate(4,toSQL);
            rs=pst.executeQuery();
            jTable2.setModel(DbUtils.resultSetToTableModel(rs));
           
        }
        catch(SQLException ex){
               JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        } 
       else if (spcombo1.getSelectedIndex()<=0 && !(docSearch.getText() == null)){
             String query="select d.empNo,d.name,d.Speciality,s.sessionID,s.date,s.startTime,s.noOfApp,s.avilableApp,s.TelBooking,s.action "
                      + "from session s,doctor d where s.empNo=d.empNo AND s.status='Available' AND "
                      + "s.date >= CURDATE()AND d.name like ? and s.date BETWEEN ? and ? order by s.date" ;
       
        
        String name=docSearch.getText()+"%";
        try{
            Connection con=DBconnect.getConnection();
            
            PreparedStatement pst=con.prepareStatement(query); 
            
            pst.setString(1,name);
            pst.setDate(2,fromSQL);
            pst.setDate(3,toSQL);
            rs=pst.executeQuery();
            jTable2.setModel(DbUtils.resultSetToTableModel(rs));
           
        }
        catch(SQLException ex){
               JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        } 
       else {
             String query="select d.empNo,d.name,d.Speciality,s.sessionID,s.date,s.startTime,s.noOfApp,s.avilableApp,s.TelBooking,s.action "
                      + "from session s,doctor d where s.empNo=d.empNo AND s.status='Available' AND "
                      + "s.date >= CURDATE() and s.date BETWEEN ? and ? order by s.date" ;
    
        try{
            Connection con=DBconnect.getConnection();
       
            PreparedStatement pst=con.prepareStatement(query); 
            
            pst.setDate(1,fromSQL);
            pst.setDate(2,toSQL);
            
            rs=pst.executeQuery();
            jTable2.setModel(DbUtils.resultSetToTableModel(rs));
          }
         
        
        catch(SQLException ex){
               JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        }

    }//GEN-LAST:event_jButton33ActionPerformed

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        String sp= jComboBox2.getSelectedItem().toString();

        jComboBox3.removeAllItems();
        filldocNameCombo(sp,jComboBox3);

    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        try{
            Connection conn = DBconnect.getConnection();
            String report = "C:\\Users\\HP User\\Desktop\\itp\\ITPproject\\src\\allApp.jrxml";
            JasperReport jr = JasperCompileManager.compileReport(report);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
            JasperViewer.viewReport(jp,false);
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
         try{
            Connection conn = DBconnect.getConnection();
            String report = "C:\\Users\\HP User\\Desktop\\itp\\ITPproject\\src\\sess.jrxml";
            JasperReport jr = JasperCompileManager.compileReport(report);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
            JasperViewer.viewReport(jp,false);
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        
        java.util.Date ed = jDateChooser3.getDate();
        java.sql.Date endate = new java.sql.Date(ed.getTime());
        String en = endate.toString();
         HashMap param = new HashMap();
       
        param.put("dDate",en);
        try{
            Connection conn = DBconnect.getConnection();
            String report = "C:\\Users\\HP User\\Desktop\\itp\\ITPproject\\src\\dailyApp.jrxml";
            JasperReport jr = JasperCompileManager.compileReport(report);
            JasperPrint jp = JasperFillManager.fillReport(jr, param, conn);
            JasperViewer.viewReport(jp,false);
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        // TODO add your handling code here:
        String name = jComboBox3.getSelectedItem().toString();
        java.util.Date sd = jDateChooser1.getDate();
        java.sql.Date stdate = new java.sql.Date(sd.getTime());
        String s = stdate.toString();
        java.util.Date ed = jDateChooser2.getDate();
        java.sql.Date endate = new java.sql.Date(ed.getTime());
        String en = endate.toString();
         HashMap param = new HashMap();
          param.put("eName",name);
       param.put("sDate",s);
        param.put("eDate",en);
        try{
            Connection conn = DBconnect.getConnection();
            String report = "C:\\Users\\HP User\\Desktop\\itp\\ITPproject\\src\\docSess.jrxml";
            JasperReport jr = JasperCompileManager.compileReport(report);
            JasperPrint jp = JasperFillManager.fillReport(jr, param, conn);
            JasperViewer.viewReport(jp,false);
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_jButton22ActionPerformed

    private void sessionB1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sessionB1ActionPerformed
       if(jTable4.getSelectedRow()>-1){
        DefaultTableModel model2 =(DefaultTableModel) jTable4.getModel();
        DefaultTableModel model =(DefaultTableModel) jTable7.getModel();
        
        int sid = Integer.parseInt(model2.getValueAt(jTable4.getSelectedRow(),0).toString());
        
          Session s2 =new Session();
 
            String TelBooking="Allow";
            if(denyTelCheck1.isSelected()){
              TelBooking="Deny";
            }
          
            
            java.util.Date dd=sDate.getDate();
            String empNo =model.getValueAt(jTable7.getSelectedRow(),0).toString();
            int noOfApp=Integer.parseInt(jSpinner2.getValue().toString());
            
            JSpinner.DateEditor deS=new JSpinner.DateEditor(spinnerS,"HH:mm ");
            String sTime =deS.getFormat().format(spinnerS.getValue());
            
            JSpinner.DateEditor deE=new JSpinner.DateEditor(spinnerE,"HH:mm ");
            String eTime =deE.getFormat().format(spinnerE.getValue());
        
        int i=s2.updateSession(dd,sTime,eTime,noOfApp,TelBooking,sid,jTable4,empNo);
        
        if(i>0){
             JOptionPane.showMessageDialog(null,"Session Updated Successfully");
            
        }
        else{
            JOptionPane.showMessageDialog(null,"Session cannot be updated","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        
    }else{
            JOptionPane.showMessageDialog(null,"Please select the Session before Updating the session","ERROR",JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_sessionB1ActionPerformed

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
       DefaultTableModel model =(DefaultTableModel) jTable7.getModel();
        DefaultTableModel model2 =(DefaultTableModel) jTable4.getModel();
        String empno=model.getValueAt(jTable7.getSelectedRow(),0).toString();
        int sid = Integer.parseInt(model2.getValueAt(jTable4.getSelectedRow(),0).toString());
        
        String date=model2.getValueAt(jTable4.getSelectedRow(),2).toString();
        String sTime=model2.getValueAt(jTable4.getSelectedRow(),3).toString();
        String eTime=model2.getValueAt(jTable4.getSelectedRow(),4).toString();
        int count=Integer.parseInt(model2.getValueAt(jTable4.getSelectedRow(),5).toString());
        String check=model2.getValueAt(jTable4.getSelectedRow(),7).toString();
        int availableApp=Integer.parseInt(model2.getValueAt(jTable4.getSelectedRow(),6).toString());
    if(availableApp<=0){ 
       if(check.equals("Deny")) {
        denyTelCheck1.setSelected(true);}
       else
       {denyTelCheck1.setSelected(false);}
       
       
       
         try{
            SimpleDateFormat date1 = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date datevalue = date1.parse(date);
            sDate.setDate(datevalue);
            

        SimpleDateFormat time = new SimpleDateFormat("HH:mm");
        java.util.Date utilS = time.parse(sTime); // your util date
        java.sql.Date sTimeset = new java.sql.Date(utilS.getTime()); // your sql date
         java.util.Date utilE = time.parse(eTime); // your util date
        java.sql.Date eTimeset = new java.sql.Date(utilE.getTime()); 
    
        spinnerS.setValue(sTimeset);
        spinnerE.setValue(eTimeset);
        jSpinner2.setValue(count);
        
          }
        catch(Exception e){}
    }
    else   
    {
        JOptionPane.showMessageDialog(null,"Please Reschedule/cancel the available "+availableApp+" appointment(s) ","SID:"+sid+" CANNOT BE BLOCKED!",JOptionPane.ERROR_MESSAGE);  
    }
                                      

    }//GEN-LAST:event_jTable4MouseClicked

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner appCount1;
    private javax.swing.JSpinner appCount2;
    private javax.swing.JSpinner appCount3;
    private javax.swing.JSpinner appCount4;
    private javax.swing.JSpinner appCount5;
    private javax.swing.JSpinner appCount6;
    private javax.swing.JSpinner appCount7;
    private javax.swing.JComboBox<String> dateCombo1;
    private javax.swing.JComboBox<String> dateCombo2;
    private javax.swing.JCheckBox denyTelCheck1;
    private javax.swing.JCheckBox denyTelCheck2;
    private javax.swing.JTextField docSearch;
    private javax.swing.JTextField docSearch1;
    private javax.swing.JTextField docSearch2;
    private javax.swing.JTextField docSearch3;
    private javax.swing.JTextField docSearch4;
    private com.toedter.calendar.JDateChooser eCal2;
    private com.toedter.calendar.JDateChooser eDate1;
    private javax.swing.JCheckBox fri;
    private javax.swing.JCheckBox informPat;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton44;
    private javax.swing.JButton jButton45;
    private javax.swing.JButton jButton46;
    private javax.swing.JButton jButton47;
    private javax.swing.JButton jButton48;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private com.toedter.calendar.JDateChooser jDateChooser5;
    private com.toedter.calendar.JDateChooser jDateChooser6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane25;
    private javax.swing.JScrollPane jScrollPane26;
    private javax.swing.JScrollPane jScrollPane27;
    private javax.swing.JScrollPane jScrollPane28;
    private javax.swing.JScrollPane jScrollPane29;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable10;
    private javax.swing.JTable jTable11;
    private javax.swing.JTable jTable12;
    private javax.swing.JTable jTable13;
    private javax.swing.JTable jTable14;
    private javax.swing.JTable jTable15;
    private javax.swing.JTable jTable16;
    private javax.swing.JTable jTable17;
    private javax.swing.JTable jTable18;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTable jTable7;
    private javax.swing.JTable jTable8;
    private javax.swing.JTable jTable9;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField28;
    private javax.swing.JTextField jTextField29;
    private javax.swing.JTextField jTextField30;
    private javax.swing.JCheckBox mon;
    private javax.swing.JRadioButton p;
    private javax.swing.JRadioButton p1;
    private javax.swing.JTextField patSearch1;
    private com.toedter.calendar.JDateChooser sCal2;
    private com.toedter.calendar.JDateChooser sDate;
    private com.toedter.calendar.JDateChooser sDate1;
    private javax.swing.JCheckBox sat;
    private javax.swing.JButton sessionB;
    private javax.swing.JButton sessionB1;
    private javax.swing.ButtonGroup sourceR;
    private javax.swing.JComboBox<String> spcombo1;
    private javax.swing.JComboBox<String> spcombo21;
    private javax.swing.JComboBox<String> spcombo22;
    private javax.swing.JComboBox<String> spcombo23;
    private javax.swing.JComboBox<String> spcombo24;
    private javax.swing.JSpinner spinnerE;
    private javax.swing.JSpinner spinnerE1;
    private javax.swing.JSpinner spinnerE2;
    private javax.swing.JSpinner spinnerE3;
    private javax.swing.JSpinner spinnerE4;
    private javax.swing.JSpinner spinnerE5;
    private javax.swing.JSpinner spinnerE6;
    private javax.swing.JSpinner spinnerE7;
    private javax.swing.JSpinner spinnerH;
    private javax.swing.JSpinner spinnerM;
    private javax.swing.JSpinner spinnerS;
    private javax.swing.JSpinner spinnerS1;
    private javax.swing.JSpinner spinnerS2;
    private javax.swing.JSpinner spinnerS3;
    private javax.swing.JSpinner spinnerS4;
    private javax.swing.JSpinner spinnerS5;
    private javax.swing.JSpinner spinnerS6;
    private javax.swing.JSpinner spinnerS7;
    private javax.swing.JCheckBox sun;
    private javax.swing.JCheckBox thu;
    private javax.swing.JComboBox<String> timeCombo1;
    private javax.swing.JComboBox<String> timeCombo2;
    private javax.swing.JComboBox<String> timecombo;
    private javax.swing.JCheckBox tue;
    private javax.swing.JRadioButton w;
    private javax.swing.JRadioButton w1;
    private javax.swing.JCheckBox wed;
    // End of variables declaration//GEN-END:variables
}
