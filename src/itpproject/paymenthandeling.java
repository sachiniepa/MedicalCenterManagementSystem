/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itpproject;

import classPay.PayEquip;
import classPay.PayMaintain;
import classPay.payDrugs;
import classPay.paychanel;
import classPay.paylab;
import classPay.paysup;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import net.proteanit.sql.DbUtils;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage;
import javax.swing.JFileChooser;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author HP User
 */
public class paymenthandeling extends javax.swing.JInternalFrame {

   
     Connection conn = null;
    PreparedStatement pst=null;
    ResultSet rs = null;
    float sum=0;
    String hospitalFee= "400.00";
    
    public paymenthandeling() {
        initComponents();
       
        LoadTable2();
        LoadTable2();
        LoadTable1();
        LoadTable3();
        AppTableLoad();
        LoadTable5();
        LoadTable6();
        patientTableLoad(jTable16);
        LoadTablechanel();

        
       LoadTableDrug();
        
       jTextField33.setVisible(false);
       jLabel31.setVisible(false);
       jTextField43.setVisible(false);
       jLabel43.setVisible(false);
       jTextField34.setVisible(false);
       jLabel42.setVisible(false);
        jTextField21.setVisible(false);
       jLabel20.setVisible(false);
     //  jTextField31.setVisible(false);
     //  jLabel33.setVisible(false);
       jTextField32.setVisible(false);
       jLabel32.setVisible(false);
       jTextField15.setVisible(false);
       jLabel16.setVisible(false);
       
     
       
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

      public void getSum()
       {
        Double sum=0.00;
        for (int i=0;i<drugT.getRowCount();i++)
        {
            
            Double mul=Double.valueOf(drugT.getValueAt(i, 2).toString()) * Double.valueOf(drugT.getValueAt(i,4).toString());
            sum=sum+mul;
        }
        jTextField16.setText(Double.toString(sum));
       }
    
      
      public void getsumqty()
      {
          int sum=0;
          for(int i=0;i<drugT.getRowCount();i++)
          {
              sum=sum+ Integer.parseInt(drugT.getValueAt(i, 2).toString());
          }
          jSpinner2.setValue(Integer.valueOf(sum));
      }
      
      
      
      
      
      
      
      
      public void getdrugstot()
      {
          String drugs="";
          
          for(int i=0;i<drugT.getRowCount();i++)
          {
             drugs = drugs+ ","+drugT.getValueAt(i, 1).toString();
          }
          jTextField8.setText(drugs);
      }

      
      
            public void getnametot()
      {
          String nme="";
          
          for(int i=0;i<drugT.getRowCount();i++)
          {
             nme = nme+ ","+drugT.getValueAt(i, 3).toString();
          }
          jTextField12.setText(nme);
      }
      
            
        private void LoadTablechanel()
        {
        Connection conn = DBconnect.getConnection();
        
        try
            {
            String sql;
            sql= "SELECT * from paychanel";
            pst = conn.prepareStatement(sql);
            rs=pst.executeQuery(sql);
            jTabledb.setModel(DbUtils.resultSetToTableModel(rs));
        
            }
        catch(Exception e )
            {
             System.out.println(e);
             JOptionPane.showMessageDialog(null,"UN Successfull");
            }
        
        }
      
     
        private void LoadTable2(){
        
        Connection conn = DBconnect.getConnection();
        
        try
        {
            
            String sql;
            sql = "SELECT * from payequip ";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery(sql);
           equipTable.setModel(DbUtils.resultSetToTableModel(rs));
            //JOptionPane.showMessageDialog(null,"Successfull");
        
        }
        
        catch(SQLException e)
                {
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null,"UN Successfull");
                }
     }
        
        
        private void AppTableLoad(){
            Connection conn = DBconnect.getConnection();
         try {
             String sql="select a.patientID,p.name,d.name, d.doctorFee,a.apRefNo,a.appNo,a.date,a.fee from appointment a,patient p ,session s,doctor d where a.patientID=p.patientID AND a.sessionID=s.sessionID and s.empNo= d.empNo and  a.billStatus='UnBilled'";
             pst = conn.prepareStatement(sql);
             rs = pst.executeQuery(sql);
             jTable17.setModel(DbUtils.resultSetToTableModel(rs));
         } catch (SQLException ex) {
            // Logger.getLogger(paymenthandeling.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"cannot load the appointment table");
         }
        }
        
        
        
         private void LoadTable3(){
        
        Connection conn = DBconnect.getConnection();
        
        try
        {
            
            String sql;
            sql = "SELECT * from paylab ";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery(sql);
           jTable4.setModel(DbUtils.resultSetToTableModel(rs));
            //JOptionPane.showMessageDialog(null,"Successfull");
        
        }
        
        catch(SQLException e)
                {
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null,"UN Successfull");
                }
        
                }
        
         
      /*   private void LoadTable4(){
        
        Connection conn = DBconnect.getConnection();
        
        try
        {
            
            String sql;
            sql = "SELECT * from paychanel ";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery(sql);
           jTable3.setModel(DbUtils.resultSetToTableModel(rs));
            //JOptionPane.showMessageDialog(null,"Successfull");
        
        }
        
        catch(SQLException e)
                {
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null,"UN Successfull");
                }
         }
         
         */
        private void LoadTable5(){
        
        Connection conn = DBconnect.getConnection();
        
        try
        {
            
            String sql;
            sql = "SELECT * from paysup ";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery(sql);
           supTable.setModel(DbUtils.resultSetToTableModel(rs));
            //JOptionPane.showMessageDialog(null,"Successfull");
        
        }
        
        catch(SQLException e)
                {
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null,"UN Successfull");
                }
         }
         
        
                private void LoadTable6(){
        
        Connection conn = DBconnect.getConnection();
        
        try
        {
            
            String sql;
            sql = "SELECT * from paydrugs ";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery(sql);
           jTable2.setModel(DbUtils.resultSetToTableModel(rs));
            //JOptionPane.showMessageDialog(null,"Successfull");
        
        }
        
        catch(SQLException e)
                {
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null,"UN Successfull");
                }
         }
        
        
        
        
        void filter(String query,javax.swing.JTable j)
        {
            DefaultTableModel dm= (DefaultTableModel) j.getModel();
            TableRowSorter<DefaultTableModel> tr= new TableRowSorter<DefaultTableModel> (dm);
            j.setRowSorter(tr);
            
            tr.setRowFilter(RowFilter.regexFilter(query));
        }
                
         
         private void LoadTableDrug()
         {
             
             Connection conn=DBconnect.getConnection();
                     
            // String q1="Select b.drugCode, d.drugName, b.qty, d.unitPrice  from drugs d, issued b where d.drugCode=b.drugCode" ;
            // String q1="SELECT i.presNo,i.drugCode,i.qty,d.drugName,d.unitPrice FROM issued i,drugs d WHERE i.drugCode=d.drugCode and i.presNo='PR56'" ;
              String q1="SELECT i.presNo,i.drugCode,i.qty,d.drugName,d.unitPrice FROM issued i,drugs d ";
            // System.out.println("dsdsds");
             try
             {
                pst=conn.prepareStatement(q1);
                rs=pst.executeQuery();
                
//               while(rs.next())
//               { 
               // System.out.println("fdf="+rs.getObject("drugCode"));
                
                drugT.setModel(DbUtils.resultSetToTableModel(rs));
                
               //} 
                
             }
             catch(Exception e)
             {
                 System.out.println("Error: "+e);
             }
             
             
         }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton18 = new javax.swing.JButton();
        jTextField28 = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jTextField32 = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jTextField12 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jSpinner2 = new javax.swing.JSpinner();
        unpError = new javax.swing.JLabel();
        totErr = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        drugT = new javax.swing.JTable();
        jButton41 = new javax.swing.JButton();
        jButton45 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton40 = new javax.swing.JButton();
        jButton61 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jTextField21 = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jButton16 = new javax.swing.JButton();
        jTextField30 = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jTextField27 = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jTextField23 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jTextField24 = new javax.swing.JTextField();
        jTextField26 = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        jButton39 = new javax.swing.JButton();
        jButton46 = new javax.swing.JButton();
        jButton60 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jTextField34 = new javax.swing.JTextField();
        jTextField42 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jTextField49 = new javax.swing.JTextField();
        jButton30 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jTextField35 = new javax.swing.JTextField();
        jTextField39 = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jTextField36 = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jTextField37 = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jTextField38 = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jTextField40 = new javax.swing.JTextField();
        jTextField41 = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel28 = new javax.swing.JLabel();
        jButton38 = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        equipTable = new javax.swing.JTable();
        jButton43 = new javax.swing.JButton();
        jButton58 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        jTextField43 = new javax.swing.JTextField();
        jButton26 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jTextField48 = new javax.swing.JTextField();
        jButton31 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jTextField47 = new javax.swing.JTextField();
        jTextField44 = new javax.swing.JTextField();
        jTextField45 = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel48 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jButton37 = new javax.swing.JButton();
        jButton42 = new javax.swing.JButton();
        jButton57 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jTextField17 = new javax.swing.JTextField();
        jButton17 = new javax.swing.JButton();
        jTextField6 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jTextField33 = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jTextField50 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        bpErr = new javax.swing.JLabel();
        qtyErr = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel27 = new javax.swing.JLabel();
        jButton35 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        supTable = new javax.swing.JTable();
        jButton47 = new javax.swing.JButton();
        jTextField14 = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jButton56 = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        jTextField46 = new javax.swing.JTextField();
        jScrollPane28 = new javax.swing.JScrollPane();
        jTable16 = new javax.swing.JTable();
        jLabel113 = new javax.swing.JLabel();
        jScrollPane29 = new javax.swing.JScrollPane();
        jTable17 = new javax.swing.JTable();
        jPanel17 = new javax.swing.JPanel();
        jTextField53 = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jTextField54 = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        jTextField55 = new javax.swing.JTextField();
        jTextField56 = new javax.swing.JTextField();
        jTextField57 = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        jTextField58 = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        jButton48 = new javax.swing.JButton();
        jButton49 = new javax.swing.JButton();
        jButton50 = new javax.swing.JButton();
        jButton54 = new javax.swing.JButton();
        jButton55 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTabledb = new javax.swing.JTable();

        jLabel1.setText("jLabel1");

        setPreferredSize(new java.awt.Dimension(964, 592));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jButton6.setBackground(new java.awt.Color(0, 31, 82));
        jButton6.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Add_32px.png"))); // NOI18N
        jButton6.setText("Add");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(0, 31, 82));
        jButton7.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Edit_32px_1.png"))); // NOI18N
        jButton7.setText("Edit");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jButton18.setBackground(new java.awt.Color(0, 31, 82));
        jButton18.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton18.setForeground(new java.awt.Color(255, 255, 255));
        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Bill_32px.png"))); // NOI18N
        jButton18.setText("Generate Bill");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jTextField28.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField28.setBorder(null);

        jLabel32.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jLabel32.setText("Bill No");

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Drug details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Symbol", 1, 14))); // NOI18N

        jTextField12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField12ActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel17.setText("Total");

        jLabel16.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel16.setText("Unit price");

        jLabel13.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel13.setText("Drug Name");

        jLabel9.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel9.setText("Drug Code");

        jTextField15.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField15KeyPressed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel15.setText("Quantity");

        jTextField16.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField16KeyPressed(evt);
            }
        });

        jLabel10.setText("Priscription No");

        jTextField13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField13ActionPerformed(evt);
            }
        });

        jSpinner2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jSpinner2KeyPressed(evt);
            }
        });

        unpError.setForeground(new java.awt.Color(255, 51, 51));

        totErr.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addGap(56, 56, 56)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(totErr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48)
                                .addComponent(unpError, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jTextField13, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTextField8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(unpError))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(totErr, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43))
        );

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Search_26px_1.png"))); // NOI18N

        drugT.setModel(new javax.swing.table.DefaultTableModel(
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
        drugT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                drugTMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(drugT);

        jButton41.setText("search");
        jButton41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton41ActionPerformed(evt);
            }
        });

        jButton45.setBackground(new java.awt.Color(0, 31, 82));
        jButton45.setForeground(new java.awt.Color(255, 255, 255));
        jButton45.setText("Reset");
        jButton45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton45ActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(0, 31, 82));
        jButton8.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Calculator_32px.png"))); // NOI18N
        jButton8.setText("Calculate");
        jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton8MouseClicked(evt);
            }
        });
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton40.setText("Refresh");
        jButton40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton40ActionPerformed(evt);
            }
        });

        jButton61.setBackground(new java.awt.Color(0, 31, 82));
        jButton61.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton61.setForeground(new java.awt.Color(255, 255, 255));
        jButton61.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Report_Card_32px_2.png"))); // NOI18N
        jButton61.setText("Report");
        jButton61.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton61ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jButton40)
                                .addGap(295, 295, 295))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(jButton61)
                        .addGap(51, 51, 51)
                        .addComponent(jButton45, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jButton8)
                        .addGap(48, 48, 48)
                        .addComponent(jButton18))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7)))
                .addContainerGap(69, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel32)
                .addGap(54, 54, 54)
                .addComponent(jTextField32, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(246, 246, 246)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField28)
                    .addComponent(jSeparator1))
                .addGap(2, 2, 2)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton41)
                .addGap(215, 215, 215))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32)
                            .addComponent(jTextField32, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                                .addComponent(jTextField28))
                            .addComponent(jButton41, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton6)
                            .addComponent(jButton7)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton40)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)))
                .addGap(29, 29, 29)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton61)
                    .addComponent(jButton45, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton18))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Drugs", jPanel4);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jLabel20.setText("Bill No");
        jPanel3.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, -1, -1));
        jPanel3.add(jTextField21, new org.netbeans.lib.awtextra.AbsoluteConstraints(159, 13, 152, -1));

        jButton12.setBackground(new java.awt.Color(0, 31, 82));
        jButton12.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton12.setForeground(new java.awt.Color(255, 255, 255));
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Add_32px.png"))); // NOI18N
        jButton12.setText("Add");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 420, 100, 40));

        jButton13.setBackground(new java.awt.Color(0, 31, 82));
        jButton13.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton13.setForeground(new java.awt.Color(255, 255, 255));
        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Edit_32px_1.png"))); // NOI18N
        jButton13.setText("Edit");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 420, 110, 40));

        jButton14.setBackground(new java.awt.Color(0, 31, 82));
        jButton14.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton14.setForeground(new java.awt.Color(255, 255, 255));
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Calculator_32px.png"))); // NOI18N
        jButton14.setText("Calculate");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 360, 150, 40));

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "BillNO", "PatientName", "TestNum", "TestName", "Charge"
            }
        ));
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable4);

        jPanel3.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 230, 450, 90));

        jButton16.setBackground(new java.awt.Color(0, 31, 82));
        jButton16.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton16.setForeground(new java.awt.Color(255, 255, 255));
        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Bill_32px.png"))); // NOI18N
        jButton16.setText("Generate Bill");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 420, -1, -1));

        jTextField30.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jTextField30.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField30.setBorder(null);
        jTextField30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField30ActionPerformed(evt);
            }
        });
        jPanel3.add(jTextField30, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 30, 168, -1));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Patient details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Symbol", 1, 14))); // NOI18N

        jLabel11.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel11.setText("Patient Name");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addGap(39, 39, 39)
                .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 67, -1, -1));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Test details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Symbol", 1, 14))); // NOI18N

        jLabel12.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel12.setText("Test Number");

        jLabel19.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel19.setText("Charge");

        jLabel18.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel18.setText("Test Name");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19))
                .addGap(60, 60, 60)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField24)
                    .addComponent(jTextField26)
                    .addComponent(jTextField23))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 291, 170));
        jPanel3.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 52, 280, -1));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Search_26px_1.png"))); // NOI18N
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 20, 40, 30));

        jButton39.setText("search");
        jButton39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton39ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton39, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 30, -1, -1));

        jButton46.setBackground(new java.awt.Color(0, 31, 82));
        jButton46.setForeground(new java.awt.Color(255, 255, 255));
        jButton46.setText("Reset");
        jButton46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton46ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton46, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 420, 80, 40));

        jButton60.setBackground(new java.awt.Color(0, 31, 82));
        jButton60.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton60.setForeground(new java.awt.Color(255, 255, 255));
        jButton60.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Report_Card_32px_2.png"))); // NOI18N
        jButton60.setText("Report");
        jButton60.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton60ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton60, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 420, -1, -1));

        jTabbedPane1.addTab("Lab report", jPanel3);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel41.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel41.setText("Total Cost");
        jPanel2.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, -1, -1));

        jLabel42.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jLabel42.setText("Bill No");
        jPanel2.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(92, 11, -1, -1));
        jPanel2.add(jTextField34, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 13, 144, -1));

        jTextField42.setEditable(false);
        jTextField42.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.add(jTextField42, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 420, 120, -1));

        jButton4.setBackground(new java.awt.Color(0, 31, 82));
        jButton4.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Add_32px.png"))); // NOI18N
        jButton4.setText("Add");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 460, -1, 40));

        jButton20.setBackground(new java.awt.Color(0, 31, 82));
        jButton20.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton20.setForeground(new java.awt.Color(255, 255, 255));
        jButton20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Edit_32px_1.png"))); // NOI18N
        jButton20.setText("Edit");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton20, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 460, 100, 40));

        jButton25.setBackground(new java.awt.Color(0, 31, 82));
        jButton25.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton25.setForeground(new java.awt.Color(255, 255, 255));
        jButton25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Calculator_32px.png"))); // NOI18N
        jButton25.setText("Calculate");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton25, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 410, -1, 40));

        jTextField49.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField49.setBorder(null);
        jTextField49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField49ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField49, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 30, 209, 20));

        jButton30.setBackground(new java.awt.Color(0, 31, 82));
        jButton30.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton30.setForeground(new java.awt.Color(255, 255, 255));
        jButton30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Bill_32px.png"))); // NOI18N
        jButton30.setText("Generate Bill");
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton30, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 460, -1, 40));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Equipment details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Symbol", 1, 14))); // NOI18N

        jLabel39.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel39.setText("Price");

        jLabel40.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel40.setText("Quantity");

        jTextField36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField36ActionPerformed(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel37.setText("Manufacture");

        jLabel34.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel34.setText("Serial No");

        jLabel38.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel38.setText("Supplier");

        jLabel35.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel35.setText("Name");

        jTextField41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField41ActionPerformed(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel36.setText("Type");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel37)
                            .addComponent(jLabel38)
                            .addComponent(jLabel39)
                            .addComponent(jLabel40))
                        .addGap(56, 56, 56)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField39)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField41, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField40, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 9, Short.MAX_VALUE))
                            .addComponent(jTextField38, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel35)
                            .addComponent(jLabel36)
                            .addComponent(jLabel34))
                        .addGap(80, 80, 80)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField35)
                            .addComponent(jTextField37)
                            .addComponent(jTextField36))))
                .addGap(58, 58, 58))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(jTextField35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(jTextField36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(jTextField37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(jTextField38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(jTextField39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(jTextField41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
        );

        jPanel2.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 48, 380, 350));
        jPanel2.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 50, 250, -1));

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Search_26px_1.png"))); // NOI18N
        jLabel28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel28MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 24, 30, -1));

        jButton38.setText("search");
        jButton38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton38ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton38, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 30, -1, -1));

        equipTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "BiLLNo", "sno", "name", "type", "manuf", "supp", "price", "qty", "tcost"
            }
        ));
        equipTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                equipTableMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(equipTable);

        jPanel2.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 280, 450, 90));

        jButton43.setBackground(new java.awt.Color(0, 31, 82));
        jButton43.setForeground(new java.awt.Color(255, 255, 255));
        jButton43.setText("Reset");
        jButton43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton43ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton43, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 460, 70, 40));

        jButton58.setBackground(new java.awt.Color(0, 31, 82));
        jButton58.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton58.setForeground(new java.awt.Color(255, 255, 255));
        jButton58.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Report_Card_32px_2.png"))); // NOI18N
        jButton58.setText("Report");
        jButton58.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton58ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton58, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 460, -1, -1));

        jTabbedPane1.addTab("Equipment", jPanel2);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel43.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jLabel43.setText("Bill No");
        jPanel6.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 21, -1, -1));
        jPanel6.add(jTextField43, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 23, 76, -1));

        jButton26.setBackground(new java.awt.Color(0, 31, 82));
        jButton26.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton26.setForeground(new java.awt.Color(255, 255, 255));
        jButton26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Add_32px.png"))); // NOI18N
        jButton26.setText("Add");
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton26, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, -1, 40));

        jButton27.setBackground(new java.awt.Color(0, 31, 82));
        jButton27.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton27.setForeground(new java.awt.Color(255, 255, 255));
        jButton27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Edit_32px_1.png"))); // NOI18N
        jButton27.setText("Edit");
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton27, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 410, -1, 40));

        jButton29.setBackground(new java.awt.Color(0, 31, 82));
        jButton29.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton29.setForeground(new java.awt.Color(255, 255, 255));
        jButton29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Calculator_32px.png"))); // NOI18N
        jButton29.setText("Calculate");
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton29, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 300, -1, -1));

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Bill No", "Equip No", "Product Cost", "Service Cost", "ServiceCost"
            }
        ));
        jTable6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable6MouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jTable6);

        jPanel6.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 260, 460, 90));

        jTextField48.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField48.setBorder(null);
        jTextField48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField48ActionPerformed(evt);
            }
        });
        jTextField48.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField48KeyReleased(evt);
            }
        });
        jPanel6.add(jTextField48, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 20, 191, 20));

        jButton31.setBackground(new java.awt.Color(0, 31, 82));
        jButton31.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton31.setForeground(new java.awt.Color(255, 255, 255));
        jButton31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Bill_32px.png"))); // NOI18N
        jButton31.setText("Generate Bill");
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton31, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 410, -1, 40));

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Maintainance ceatils", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Symbol", 1, 14))); // NOI18N

        jLabel45.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel45.setText("Product Cost");

        jLabel44.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel44.setText("Equipment No");

        jLabel47.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel47.setText("Service Cost");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel45)
                    .addComponent(jLabel47)
                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField44, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                    .addComponent(jTextField45)
                    .addComponent(jTextField47))
                .addGap(45, 45, 45))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(jTextField44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(jTextField45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(jTextField47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );

        jPanel6.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 61, -1, -1));

        jLabel46.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel46.setText("Total Cost");
        jPanel6.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, -1, -1));
        jPanel6.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 42, 220, 10));

        jLabel48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Search_26px_1.png"))); // NOI18N
        jLabel48.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel48MouseClicked(evt);
            }
        });
        jPanel6.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 10, 30, 30));
        jPanel6.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 310, 100, -1));

        jButton37.setText("Search");
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton37ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton37, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 20, -1, -1));

        jButton42.setBackground(new java.awt.Color(0, 31, 82));
        jButton42.setForeground(new java.awt.Color(255, 255, 255));
        jButton42.setText("Reset");
        jButton42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton42ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton42, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 410, 90, 40));

        jButton57.setBackground(new java.awt.Color(0, 31, 82));
        jButton57.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton57.setForeground(new java.awt.Color(255, 255, 255));
        jButton57.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Report_Card_32px_2.png"))); // NOI18N
        jButton57.setText("Report");
        jButton57.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton57ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton57, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 410, -1, -1));

        jTabbedPane1.addTab("Maintanance", jPanel6);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(0, 31, 82));
        jButton1.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Add_32px.png"))); // NOI18N
        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, -1, 40));

        jButton2.setBackground(new java.awt.Color(0, 31, 82));
        jButton2.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Edit_32px_1.png"))); // NOI18N
        jButton2.setText("Edit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 450, -1, 40));

        jButton3.setBackground(new java.awt.Color(0, 31, 82));
        jButton3.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Calculator_32px.png"))); // NOI18N
        jButton3.setText("Calculate");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 390, -1, -1));

        jTextField17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField17ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField17, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 390, 98, -1));

        jButton17.setBackground(new java.awt.Color(0, 31, 82));
        jButton17.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton17.setForeground(new java.awt.Color(255, 255, 255));
        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Bill_32px.png"))); // NOI18N
        jButton17.setText("Records");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton17, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 450, 160, 40));

        jTextField6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField6.setBorder(null);
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 30, 133, 20));

        jLabel31.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel31.setText("Bill No");
        jPanel1.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 10, -1, -1));
        jPanel1.add(jTextField33, new org.netbeans.lib.awtextra.AbsoluteConstraints(163, 12, 95, -1));

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Drug details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Symbol", 1, 14))); // NOI18N
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jPanel14.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 120, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel2.setText("Drug Code");
        jPanel14.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 75, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel3.setText("Drug Name");
        jPanel14.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel4.setText("Drug Type");
        jPanel14.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));
        jPanel14.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 120, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel5.setText("Unit price");
        jPanel14.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));
        jPanel14.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 120, -1));

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jPanel14.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 120, -1));

        jPanel1.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 350, 170));

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Supplier details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Symbol", 1, 14))); // NOI18N

        jLabel50.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel50.setText("Quantity");

        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField5KeyPressed(evt);
            }
        });

        jTextField50.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField50KeyPressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel6.setText("Buying Price");

        bpErr.setForeground(new java.awt.Color(255, 51, 51));

        qtyErr.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bpErr, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel50))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(jTextField50, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(qtyErr, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(63, 63, 63))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(bpErr)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(qtyErr))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel50))))
                .addGap(66, 66, 66))
        );

        jPanel1.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 340, 140));

        jLabel29.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel29.setText("Total price");
        jPanel1.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, -1, -1));
        jPanel1.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 50, 184, 10));

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Search_26px_1.png"))); // NOI18N
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 20, 40, 30));

        jButton35.setText("Search");
        jButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton35ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton35, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 30, -1, -1));

        supTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "BiLL", "DrugC", "DrugN", "DrugTy", "UnitP", "SellP", "Qty", "Sup", "TotP"
            }
        ));
        supTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                supTableMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(supTable);

        jPanel1.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 270, -1, 100));

        jButton47.setBackground(new java.awt.Color(0, 31, 82));
        jButton47.setForeground(new java.awt.Color(255, 255, 255));
        jButton47.setText("Reset");
        jButton47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton47ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton47, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 450, -1, 40));

        jTextField14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField14ActionPerformed(evt);
            }
        });
        jTextField14.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField14KeyReleased(evt);
            }
        });
        jPanel1.add(jTextField14, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 230, 90, -1));

        jLabel49.setText("Search BiLL");
        jPanel1.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 230, -1, 20));

        jButton56.setBackground(new java.awt.Color(0, 31, 82));
        jButton56.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton56.setForeground(new java.awt.Color(255, 255, 255));
        jButton56.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Report_Card_32px_2.png"))); // NOI18N
        jButton56.setText("Report");
        jButton56.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton56ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton56, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 450, -1, -1));

        jTabbedPane1.addTab("Supplier", jPanel1);

        jLabel51.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jLabel51.setText("Patient ID :");

        jTextField46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField46ActionPerformed(evt);
            }
        });
        jTextField46.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField46KeyReleased(evt);
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

        jLabel113.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jLabel113.setText("Appointment Details");

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

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chanelling details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Symbol", 1, 14))); // NOI18N

        jLabel54.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel54.setText("Hospital fee");

        jLabel55.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel55.setText("Doctor fee");

        jLabel56.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel56.setText("Doctor Name");

        jLabel57.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel57.setText("Total");

        jLabel58.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel58.setText("Patient Name:");

        jLabel59.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel59.setText("AppRefNo:");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel55)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField54, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel54)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField53, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(jTextField55, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel56)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField56, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField57, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel59)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField58, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel59)
                    .addComponent(jTextField58, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58)
                    .addComponent(jTextField57, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel56)
                    .addComponent(jTextField56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel54, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField53, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel55))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField55, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jButton48.setBackground(new java.awt.Color(0, 31, 82));
        jButton48.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton48.setForeground(new java.awt.Color(255, 255, 255));
        jButton48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Calculator_32px.png"))); // NOI18N
        jButton48.setText("Calculate");
        jButton48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton48ActionPerformed(evt);
            }
        });

        jButton49.setBackground(new java.awt.Color(0, 31, 82));
        jButton49.setForeground(new java.awt.Color(255, 255, 255));
        jButton49.setText("Reset");
        jButton49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton49ActionPerformed(evt);
            }
        });

        jButton50.setBackground(new java.awt.Color(0, 31, 82));
        jButton50.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton50.setForeground(new java.awt.Color(255, 255, 255));
        jButton50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Add_32px.png"))); // NOI18N
        jButton50.setText("Add");
        jButton50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton50ActionPerformed(evt);
            }
        });

        jButton54.setBackground(new java.awt.Color(0, 31, 82));
        jButton54.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton54.setForeground(new java.awt.Color(255, 255, 255));
        jButton54.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Report_Card_32px_2.png"))); // NOI18N
        jButton54.setText("Report");
        jButton54.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton54ActionPerformed(evt);
            }
        });

        jButton55.setBackground(new java.awt.Color(0, 31, 82));
        jButton55.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton55.setForeground(new java.awt.Color(255, 255, 255));
        jButton55.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Bill_32px.png"))); // NOI18N
        jButton55.setText("Generate Bill");
        jButton55.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton55ActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Segoe UI Symbol", 1, 11)); // NOI18N
        jLabel21.setText("BiLL NO");

        jTabledb.setModel(new javax.swing.table.DefaultTableModel(
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
        jTabledb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabledbMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTabledb);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel21)
                .addGap(18, 18, 18)
                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel113, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(416, 416, 416))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(jTextField46, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(232, 232, 232))))
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jButton50, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(63, 63, 63)
                                .addComponent(jButton48)))
                        .addGap(93, 93, 93)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane29, javax.swing.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
                            .addComponent(jScrollPane28)
                            .addComponent(jScrollPane1)))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(235, 235, 235)
                        .addComponent(jButton55, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jButton54, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton49, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton48, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton50, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jScrollPane28, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel113)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane29, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGap(28, 28, 28)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton55, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton54, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton49, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(81, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Channelling", jPanel16);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField17ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
         paysup ps=new paysup();
       
        
         
        try
        {
        String a=jTextField1.getText();
        String b=jTextField2.getText();
        String c=jTextField3.getText();        
        float d=Float.parseFloat(jTextField4.getText());
        float e=Float.parseFloat(jTextField5.getText());
        int f=Integer.parseInt(jTextField50.getText());
//        String g=jComboBox1.getSelectedItem().toString();
        float h=Float.parseFloat(jTextField17.getText());
        
        ps.sendToDB(a, b, c, d, e, f, h);
        LoadTable5();
            
            
           
            int i=Integer.parseInt(jTextField50.getText());
            qtyErr.setText("");
            
            Float j=Float.parseFloat(jTextField5.getText());
            bpErr.setText("");
        }
        
        catch(NumberFormatException e1)
        {
            qtyErr.setText("Invalid Syntax ");
            bpErr.setText("Invalid Syntax ");
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField12ActionPerformed

    private void jTextField41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField41ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField41ActionPerformed

    private void jTextField30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField30ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField30ActionPerformed

    private void jTextField36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField36ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField36ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        // TODO add your handling code here:
        float pcost=Float.parseFloat(jTextField45.getText());
        float scost=Float.parseFloat(jTextField47.getText());
        PayMaintain m=new PayMaintain();
        float sum=m.calMaintain(pcost, scost);
        jTextField7.setText(String.valueOf(sum));
   
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        // TODO add your handling code here:
        JFileChooser dialog= new JFileChooser();
        dialog.setSelectedFile(new File("Maintanance_BiLL"+jTextField43.getText()+".pdf"));
        int dialogResult = dialog.showSaveDialog(null);
        if(dialogResult == JFileChooser.APPROVE_OPTION){
            String filePath = dialog.getSelectedFile().getPath();
            try{
                Document myDocument = new Document();
                PdfWriter myWriter = PdfWriter.getInstance(myDocument, new FileOutputStream(filePath));
                myDocument.open();
                
                myDocument.add(new Paragraph("Deegayuu - Medical Center", FontFactory.getFont(FontFactory.TIMES_BOLD, 16, Font.BOLD)));
                myDocument.add(new Paragraph("Bill", FontFactory.getFont(FontFactory.TIMES_BOLD, 15, Font.BOLD)));
                myDocument.add(new Paragraph(new Date().toString()));
                myDocument.add(new Paragraph("-------------------------------------------------------------------------", FontFactory.getFont(FontFactory.TIMES_BOLD, 20, Font.BOLD)));
                myDocument.add(new Paragraph("\nBill No                   : "+jTextField43.getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD)));
                myDocument.add(new Paragraph("\nEquipment No       : "+jTextField44.getText() ,FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
                myDocument.add(new Paragraph("\nProduct Cost          : "+jTextField45.getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
                myDocument.add(new Paragraph("\nService Cost            : "+jTextField47.getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
   
              
                myDocument.add(new Paragraph("\nTotal                        : "+jTextField7.getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD)));

                
                myDocument.close();
                
                JOptionPane.showMessageDialog(null, "Bill successfuly saved");
            }
            catch(Exception e){
                
            }
        }
    }//GEN-LAST:event_jButton31ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        payDrugs pd=new payDrugs();
        String aa=jTextField13.getText();
        String a=jTextField8.getText();
        String b=jTextField12.getText();
        int c=(Integer)jSpinner2.getValue();
       // float d=Float.parseFloat(jTextField15.getText());
        float e=Float.parseFloat(jTextField16.getText());
        
        
        pd.sendToDB(aa,a, b, c, e);
        LoadTable6();
         
       
        jTextField13.setText("");
        jTextField32.setText("");
        jTextField8.setText("");
        jTextField12.setText("");
        
        jTextField15.setText("");
        jTextField16.setText("");
        jTextField28.setText("");
        jSpinner2.setValue(0);
        
    }//GEN-LAST:event_jButton6ActionPerformed

    
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        PayEquip eq=new PayEquip();
        String a=jTextField35.getText();
        String b=jTextField36.getText();
        String c=jTextField37.getText();
        String d=jTextField38.getText();
        String e=jTextField39.getText();
        float f=Float.parseFloat(jTextField40.getText());
        int g=Integer.parseInt(jTextField41.getText());
        float h=Float.parseFloat(jTextField42.getText());
        
        eq.sendToDB(a,b,c,d,e,f,g,h);
        LoadTable2();
        
        
        
       // e.sendToDB(sno1, name1, title, title, title, TOP_ALIGNMENT, WIDTH, TOP_ALIGNMENT);
        
          
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        // TODO add your handling code here:

        
        
        
//        String Lab1 =jTextField49.getText();
       // String sql="SELECT (***) FROM *** where ppp='"+ppp+"'";
        
//        try {
//            pst=conn.prepareStatement(sql);
//            rs=pst.executeQuery();
            
        //    t1.setModel(DbUtils.resultSetToTableModel(rs));
            
//        } catch (Exception e) {
//            
//        }
        

        
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jLabel28MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel28MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel28MouseClicked

    private void jTextField48KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField48KeyReleased
        // TODO add your handling code here:
        
//     try{
//        String sql1="select equipNo,proCost,servCost from maintenance where taskNo=?";
//        
//        pst =conn.prepareStatement(sql1);
//        pst.setString(1, jTextField48.getText());
//        
//        rs=pst.executeQuery();
//        if(rs.next())
//        {
//         String add1=rs.getString("equipNo");
//         jTextField44.setText(add1);
//         String add2=rs.getString("proCost");
//         jTextField45.setText(add2);
//         String add3=rs.getString("servCost");
//         jTextField47.setText(add3);
//         
//        }
//     }
//     catch(Exception e)
//     {
//     System.out.println(e);
//   JOptionPane.showMessageDialog(null,"Entry UNSuccessfull");
//           
//     }
      
    }//GEN-LAST:event_jTextField48KeyReleased

    
    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        // TODO add your handling code here:
        
        jTextField42.setVisible(true);
       jLabel34.setVisible(true);
        PayEquip eq= new PayEquip();
        String a=jTextField35.getText();
        String b=jTextField36.getText();
        String c=jTextField37.getText();
        String d=jTextField38.getText();
        String e=jTextField39.getText();
        float f=Float.parseFloat(jTextField40.getText());
        int g=Integer.parseInt(jTextField41.getText());
        float h=Float.parseFloat(jTextField42.getText());
       
       eq.updateDB(a,f, g, h);
       
        LoadTable2();
        
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        paylab pl=new paylab();
    //   String a=jTextField22.getText();
       String b=jTextField27.getText();
       int c= Integer.parseInt(jTextField23.getText());
       String d=jTextField24.getText();
       float e=Float.parseFloat(jTextField26.getText());
       
       pl.sendToDB(b, c, d, e);
       LoadTable3();
        
       
       
        
        
        
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jTextField49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField49ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField49ActionPerformed

    private void jTextField48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField48ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField48ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jLabel48MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel48MouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jLabel48MouseClicked

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        // TODO add your handling code here:
        PayMaintain m=new PayMaintain();
        String a=jTextField44.getText();
        float b=Float.parseFloat(jTextField45.getText());
        float c=Float.parseFloat(jTextField47.getText());
        float d=Float.parseFloat(jTextField7.getText());
        m.sendToDB(a,b,c,d);
        
       LoadTable1();
        
        
        
        
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        // TODO add your handling code here:
        
        jLabel43.setVisible(true);
        jTextField43.setVisible(true);
        PayMaintain m=new PayMaintain();
        String a=jTextField44.getText();
        float b=Float.parseFloat(jTextField45.getText());
        float c=Float.parseFloat(jTextField47.getText());
        float d=Float.parseFloat(jTextField7.getText());
        
        m.updateDB(a,b,c,d);
        LoadTable1();
        
        
        
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
        jLabel31.setVisible(true);
        jTextField33.setVisible(true);
        paysup ps=new paysup();
        int a=Integer.parseInt(jTextField33.getText());
        //String b=jComboBox1.getSelectedItem().toString();
        float c=Float.parseFloat(jTextField4.getText());
        float d=Float.parseFloat(jTextField5.getText());
        int e=Integer.parseInt(jTextField50.getText());
        float f=Float.parseFloat(jTextField17.getText());
        
        
        ps.updateDB(a,c,d,e,f);
        LoadTable5();
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        
         jLabel20.setVisible(true);
        jTextField21.setVisible(true);
       paylab pl=new paylab();
      //String a=jTextField22.getText();
       String b=jTextField27.getText();
       int c=Integer.parseInt(jTextField23.getText());
      String d=jTextField24.getText();
       float e=Float.parseFloat(jTextField26.getText());
       
       pl.updateDB(b, c, d, e);
       LoadTable3();
      
        
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        // TODO add your handling code here:
        try
        {
        float price2=Float.parseFloat(jTextField40.getText());
        float quantity2=Float.parseFloat(jTextField41.getText());
        PayEquip eq=new PayEquip();
        float sum=eq.calEquip(price2, quantity2);
        jTextField42.setText(String.valueOf(sum));
        }
        catch(Exception e)
        {
         System.out.println(e);
        // JOptionPane.showMessageDialog(null,"UN Successfull");
        }
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jTable6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable6MouseClicked
        // TODO add your handling code here:
         jTextField43.setVisible(true);
        jLabel43.setVisible(true);
        int row=jTable6.getSelectedRow();
        String a1=jTable6.getValueAt(row, 0).toString();
        String a2=jTable6.getValueAt(row, 1).toString();
        String a3=jTable6.getValueAt(row, 2).toString();
        String a4=jTable6.getValueAt(row, 3).toString();
        String a5=jTable6.getValueAt(row, 4).toString();
        
        jTextField43.setText(a1);
        jTextField44.setText(a2);
        jTextField45.setText(a3);
        jTextField47.setText(a4);
        jTextField7.setText(a5);
        
        
        
    }//GEN-LAST:event_jTable6MouseClicked

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton35ActionPerformed
        // TODO add your handling code here:
//      String name=jTextField6.getText();
//      String sql="Select equipNo,proCost,servCost from maintenance where equipNo='"+name+"'";
//      
//        System.out.println(name);
//        Connection conn = dbconnect.connect();
//        try{
//          
//         pst =conn.prepareStatement(sql);
//         rs=pst.executeQuery();
//        
//         rs.next();
//           
//         
//         String a1=rs.getObject(1).toString();
//         String a2=rs.getObject(2).toString();
//         String a3=rs.getObject(3).toString();
//         //String a4=rs.getObject(8).toString();
//         
//           
//         
//         jTextField44.setText(a1);
//         jTextField45.setText(a2);
//         jTextField47.setText(a3);
//        // jTextField7.setText(a1);
//         
//
//        }
//        catch(Exception e){
//            
//            System.out.println(e);
//        }
//        
//      
     String name=jTextField6.getText();
      String sql="SELECT d.drugCode,d.drugName,d.type,d.unitPrice,s.buyPrice,d.totQty FROM sells s,drugs d WHERE s.drugCode='"+name+"' OR d.drugName='"+name+"' ";
      
        System.out.println(name);
        Connection conn =DBconnect.getConnection();
        try{
          
         pst =conn.prepareStatement(sql);
         rs=pst.executeQuery();
        
         rs.next();
           
         
         String a1=rs.getObject(1).toString();
         String a2=rs.getObject(2).toString();
         String a3=rs.getObject(3).toString();
         String a4=rs.getObject(4).toString();
         String a5=rs.getObject(5).toString();
         String a6=rs.getObject(6).toString();
         //String a7=rs.getObject(7).toString();
         
           
         
         jTextField1.setText(a1);
         jTextField2.setText(a2);
         jTextField3.setText(a3);
         jTextField4.setText(a4);
         jTextField5.setText(a5);
         jTextField50.setText(a6);
//         jComboBox1.setSelectedItem(a7);
         

        }
        catch(Exception e){
            
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"check again");
        }
      
        
            
                
                


    }//GEN-LAST:event_jButton35ActionPerformed

    private void drugTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_drugTMouseClicked
        // TODO add your handling code here:
        int row=drugT.getSelectedRow();
        
       
        String a1=drugT.getValueAt(row, 0).toString();
        String a2=drugT.getValueAt(row, 1).toString();
        int a3=Integer.parseInt(drugT.getValueAt(row, 2).toString());
        String a4=drugT.getValueAt(row, 3).toString();
        String aa=drugT.getValueAt(row, 4).toString();
       
        jTextField13.setText(a1);
        jTextField8.setText(a2);
        jTextField12.setText(a4);
        jLabel16.setVisible(true);
        jTextField15.setVisible(true);
        jTextField15.setText(aa);
        jSpinner2.setValue(a3);
       
 
        
        
        //String v1=drugT.getValueAt(row,0)
    }//GEN-LAST:event_drugTMouseClicked

    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed
        // TODO add your handling code here:
    
      String name=jTextField48.getText();
      String sql="Select serialNo,proCost,serviceCost from maintainance where serialNo='"+name+"'";
      
        System.out.println(name);
        Connection conn = DBconnect.getConnection();
        try{
          
         pst =conn.prepareStatement(sql);
         rs=pst.executeQuery();
        
         rs.next();
           
         
         String a1=rs.getObject(1).toString();
         String a2=rs.getObject(2).toString();
         String a3=rs.getObject(3).toString();
         //String a4=rs.getObject(8).toString();
         
           
         
         jTextField44.setText(a1);
         jTextField45.setText(a2);
         jTextField47.setText(a3);
        // jTextField7.setText(a1);
         

        }
        catch(Exception e){
            
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"check again");
            
        }
        
       

        
    }//GEN-LAST:event_jButton37ActionPerformed

    private void jButton38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton38ActionPerformed
        // TODO add your handling code here:
      String name=jTextField49.getText();
      String sql="Select serialNo,name,type,manufacturer,supplier,price,qty from assests where serialNo='"+name+"' or name='"+name+"'";
      
        System.out.println(name);
        Connection conn = DBconnect.getConnection();
        try{
          
         pst =conn.prepareStatement(sql);
         rs=pst.executeQuery();
        
         rs.next();
           
         
         String a1=rs.getObject(1).toString();
         String a2=rs.getObject(2).toString();
         String a3=rs.getObject(3).toString();
         String a4=rs.getObject(4).toString();
         String a5=rs.getObject(5).toString();
         String a6=rs.getObject(6).toString();
         String a7=rs.getObject(7).toString();
         
           
         
         jTextField35.setText(a1);
         jTextField36.setText(a2);
         jTextField37.setText(a3);
         jTextField38.setText(a4);
         jTextField39.setText(a5);
         jTextField40.setText(a6);
         jTextField41.setText(a7);
         
         

        }
        catch(Exception e){
            
            System.out.println(e);
        }
        
     
        
    }//GEN-LAST:event_jButton38ActionPerformed

    private void jButton41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton41ActionPerformed
        // TODO add your handling code here:
        
         String name=jTextField28.getText();
      //String sql="Select drugCode,drugName,totQty,unitPrice from drugs where drugCode='"+name+"'";
      String sql="SELECT i.presNo,i.drugCode,i.qty,d.drugName,d.unitPrice FROM issued i,drugs d WHERE i.drugCode=d.drugCode and i.presNo='"+name+"'";
      
        System.out.println(name);
        Connection conn = DBconnect.getConnection();
        try{
          
         pst =conn.prepareStatement(sql);
         rs=pst.executeQuery();
         drugT.setModel(DbUtils.resultSetToTableModel(rs));
        
//         rs.next();
//           
//         
//         String a1=rs.getObject(1).toString();
//         String a2=rs.getObject(2).toString();
//         String a3=rs.getObject(3).toString();
//         String a4=rs.getObject(4).toString();
//         String a5=rs.getObject(5).toString();
//        
//           
//         jTextField13.setText(a1);
//         jTextField8.setText(a2);
//         jTextField12.setText(a3);
//         jSpinner2.setValue(Integer.valueOf(a4));
//         jTextField15.setText(a5);
//         
        
         

        }
        catch(Exception e){
            
            System.out.println(e);
        }

        
        
    }//GEN-LAST:event_jButton41ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
         jLabel32.setVisible(true);
        jTextField32.setVisible(true);
        payDrugs pd=new payDrugs();
       // String aa=jTextField13.getText();
        String a=jTextField8.getText();
        String b=jTextField12.getText();
        int c=(Integer)jSpinner2.getValue();
        //float d=Float.parseFloat(jTextField15.getText());
        String d = jTextField15.getText();
        //float e=Float.parseFloat(jTextField16.getText());
        String e = jTextField16.getText();
        String p = jTextField13.getText();
        pd.updateDB(p,a, b, c, d, e);
        
        LoadTable6();
        
          
        jTextField13.setText("");
        jTextField32.setText("");
        jTextField8.setText("");
        jTextField12.setText("");
        
        jTextField15.setText("");
        jTextField16.setText("");
        jTextField28.setText("");
        jSpinner2.setValue(0);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
        // TODO add your handling code here:
        
         jTextField21.setVisible(true);
        jLabel20.setVisible(true);
         int row=jTable4.getSelectedRow();
        String a1=jTable4.getValueAt(row, 0).toString();
        String a2=jTable4.getValueAt(row, 1).toString();
        String a3=jTable4.getValueAt(row, 2).toString();
        String a4=jTable4.getValueAt(row, 3).toString();
        String a5=jTable4.getValueAt(row, 4).toString();
        
        
        jTextField21.setText(a1);
      
        jTextField27.setText(a2);
        jTextField23.setText(a3);
        jTextField24.setText(a4);
        jTextField26.setText(a5);
       
        
    }//GEN-LAST:event_jTable4MouseClicked

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        
//        int qty2=Integer.parseInt(jSpinner2.getValue().toString());
//        float up2=Float.parseFloat(jTextField15.getText());
//        payDrugs dr1=new payDrugs();
//        float sum=dr1.caldrugs1(qty2, up2);
//        jTextField16.setText(String.valueOf(sum));

        
        getSum();
        getsumqty();
        getdrugstot();
        getnametot();
        
            

    
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton42ActionPerformed
        // TODO add your handling code here:
        jTextField43.setVisible(false);
       jLabel43.setVisible(false);
        
        jTextField43.setText("");
        jTextField44.setText("");
        jTextField45.setText("");
        jTextField47.setText("");
        jTextField7.setText("");
        jTextField48.setText("");
        
    }//GEN-LAST:event_jButton42ActionPerformed

    private void jButton43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton43ActionPerformed
        // TODO add your handling code here:
        jTextField42.setVisible(false);
       jLabel34.setVisible(false);
        jTextField34.setText("");
        jTextField35.setText("");
        jTextField36.setText("");
        jTextField37.setText("");
        jTextField38.setText("");
        jTextField39.setText("");
        jTextField40.setText("");
        jTextField41.setText("");
        jTextField42.setText("");
        jTextField49.setText("");
                
        
        
    }//GEN-LAST:event_jButton43ActionPerformed

    private void jButton45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton45ActionPerformed
        // TODO add your handling code here:
        
        
        totErr.setVisible(false);
        
        jTextField15.setVisible(false);
        jLabel16.setVisible(false);
       
        jTextField32.setVisible(false);
       jLabel32.setVisible(false);
       
        jTextField13.setText("");
        jTextField32.setText("");
        jTextField8.setText("");
        jTextField12.setText("");
        
        jTextField15.setText("");
        jTextField16.setText("");
        jTextField28.setText("");
        jSpinner2.setValue(0);
              
        
    }//GEN-LAST:event_jButton45ActionPerformed

    private void jButton46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton46ActionPerformed
        // TODO add your handling code here:
         jTextField21.setVisible(false);
       jLabel20.setVisible(false);
        jTextField21.setText("");
        
        jTextField27.setText("");
        jTextField23.setText("");
        jTextField24.setText("");
        jTextField26.setText("");
        jTextField30.setText("");
    }//GEN-LAST:event_jButton46ActionPerformed

    private void equipTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_equipTableMouseClicked
        // TODO add your handling code here:
         jTextField34.setVisible(true);
        jLabel42.setVisible(true);
        
        int row=equipTable.getSelectedRow();
        String a1=equipTable.getValueAt(row, 0).toString();
        String a2=equipTable.getValueAt(row, 1).toString();
        String a3=equipTable.getValueAt(row, 2).toString();
        String a4=equipTable.getValueAt(row, 3).toString();
        String a5=equipTable.getValueAt(row, 4).toString();
        String a6=equipTable.getValueAt(row, 5).toString();
        String a7=equipTable.getValueAt(row, 6).toString();
        String a8=equipTable.getValueAt(row, 7).toString();
        String a9=equipTable.getValueAt(row, 8).toString();
        
        jTextField34.setText(a1);
        jTextField35.setText(a2);
        jTextField36.setText(a3);
        jTextField37.setText(a4);
        jTextField38.setText(a5);
        jTextField39.setText(a6);
        jTextField40.setText(a7);
        jTextField41.setText(a8);
        jTextField42.setText(a9);
        
    }//GEN-LAST:event_equipTableMouseClicked

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
         jTextField32.setVisible(true);
        jLabel32.setVisible(true);
        int row=jTable2.getSelectedRow();
        String a1=jTable2.getValueAt(row, 0).toString();
        String a2=jTable2.getValueAt(row, 1).toString();
        String a3=jTable2.getValueAt(row, 2).toString();
        String a4=jTable2.getValueAt(row, 3).toString();
        String a7 = jTable2.getValueAt(row, 5).toString();
        String a6 = jTable2.getValueAt(row, 5).toString();
        
        int a5=Integer.parseInt(jTable2.getValueAt(row, 4).toString());
        
        //Float a6=Float.parseFloat(jTable2.getValueAt(row, 5).toString());
        
        jTextField32.setText(a1);
        jTextField13.setText(a2);
        jTextField8.setText(a3);
        jTextField12.setText(a4);
        jTextField15.setText(a6);
        jTextField16.setText(a7);
        jSpinner2.setValue(a5);
        
        
    }//GEN-LAST:event_jTable2MouseClicked

    private void supTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_supTableMouseClicked
        // TODO add your handling code here:
        
        jTextField33.setVisible(true);
        jLabel31.setVisible(true);
        int row=supTable.getSelectedRow();
        String a1=supTable.getValueAt(row, 0).toString();
        String a2=supTable.getValueAt(row, 1).toString();
        String a3=supTable.getValueAt(row, 2).toString();
        String a4=supTable.getValueAt(row, 3).toString();
        String a5=supTable.getValueAt(row, 4).toString();
        String a6=supTable.getValueAt(row, 5).toString();
        String a7=supTable.getValueAt(row, 6).toString();
       //String a8=supTable.getValueAt(row, 7).toString();
        String a9=supTable.getValueAt(row, 7).toString();
        
        jTextField33.setText(a1);
        jTextField1.setText(a2);
        jTextField2.setText(a3);
        jTextField3.setText(a4);
        jTextField4.setText(a5);
        jTextField5.setText(a6);
        jTextField50.setText(a7);
//       jComboBox1.setSelectedItem(a8);
        jTextField17.setText(a9);
        
        
    }//GEN-LAST:event_supTableMouseClicked

    private void jButton47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton47ActionPerformed
        // TODO add your handling code here:
        
        jTextField33.setVisible(false);
        jLabel31.setVisible(false);
        jTextField33.setText("");
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
         jTextField5.setText("");
        jTextField50.setText("");
//       jComboBox1.setSelectedItem(null);
        jTextField17.setText("");
        jTextField6.setText("");
        
        
    }//GEN-LAST:event_jButton47ActionPerformed

    private void jButton39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton39ActionPerformed
        // TODO add your handling code here:
      String name=jTextField30.getText();
      String sql="Select patientName,testNo,type,charge from test where patientName='"+name+"'";
      
        System.out.println(name);
        Connection conn = DBconnect.getConnection();
        try{
          
         pst =conn.prepareStatement(sql);
         rs=pst.executeQuery();
        
         rs.next();
           
         
         String a1=rs.getObject(1).toString();
         String a2=rs.getObject(2).toString();
         String a3=rs.getObject(3).toString();
         String a4=rs.getObject(4).toString();
         
           
         
         jTextField27.setText(a1);
         jTextField23.setText(a2);
         jTextField24.setText(a3);
         jTextField26.setText(a4);
         

        }
        catch(Exception e){
            
            System.out.println(e);
        }
        

        
    }//GEN-LAST:event_jButton39ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        
        float upr=Float.parseFloat(jTextField4.getText());
        int qty2=Integer.parseInt(jTextField50.getText());
        paysup ps=new paysup();
        float sum=ps.calsup(upr, qty2);
        jTextField17.setText(String.valueOf(sum));
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField13ActionPerformed

    private void jTextField14KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField14KeyReleased
        // TODO add your handling code here:
        String name=jTextField14.getText();
        filter(name,supTable);
        
                 
        
    }//GEN-LAST:event_jTextField14KeyReleased

    private void jTextField14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField14ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
        try{
            String host = "smtp.gmail.com";
            String user = "deegayuu@gmail.com";
            String pass = "deegayuu123";
            String to = "sachi.epa96@gmail.com";
            String from = "deegayuu@gmail.com";
            String subject = "Confirmation";
            String msg = "88888888";
            boolean sessionDebug = false;
            System.out.println("jjjjj");
            
            Properties props = System.getProperties();
            
            props.put("mail.smtp.starttls.enable","true");
            props.put("mail.smtp.host",host);
            props.put("mail.smtp.port","587");
            props.put("mail.smtp.auth","true");
            props.put("mail.smtp.starttls.required","true");
            System.out.println("jjjjj");
            
            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            
            
            javax.mail.Session mailSession = javax.mail.Session.getDefaultInstance(props,null);
            mailSession.setDebug(sessionDebug);
            Message msgg = new MimeMessage(mailSession);
            msgg.setFrom(new InternetAddress(from));
            //msgg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msgg.setRecipients(Message.RecipientType.TO, address);
            msgg.setSubject(subject);
            msgg.setSentDate(new java.util.Date());
            msgg.setText(msg);
                         
            System.out.println("jjjjj");
            Transport transport = mailSession.getTransport("smtp");
            System.out.println("jjjjj");
            transport.connect(host, user, pass);
            System.out.println("jjjjj");
            transport.sendMessage(msgg, msgg.getAllRecipients());
            System.out.println("jjjjj");
            transport.close();
            JOptionPane.showMessageDialog(null,"Email sent");
        }
        
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        
        
        JFileChooser dialog= new JFileChooser();
        dialog.setSelectedFile(new File("Supplier Record"+jTextField33.getText()+".pdf"));
        int dialogResult = dialog.showSaveDialog(null);
        if(dialogResult == JFileChooser.APPROVE_OPTION){
            String filePath = dialog.getSelectedFile().getPath();
            try{
                Document myDocument = new Document();
                PdfWriter myWriter = PdfWriter.getInstance(myDocument, new FileOutputStream(filePath));
                myDocument.open();
                
                myDocument.add(new Paragraph("Deegayuu - Medical Center", FontFactory.getFont(FontFactory.TIMES_BOLD, 16, Font.BOLD)));
                myDocument.add(new Paragraph("Records", FontFactory.getFont(FontFactory.TIMES_BOLD, 15, Font.BOLD)));
                myDocument.add(new Paragraph(new Date().toString()));
                myDocument.add(new Paragraph("-------------------------------------------------------------------------", FontFactory.getFont(FontFactory.TIMES_BOLD, 20, Font.BOLD)));
                myDocument.add(new Paragraph("\nBill No                    : "+jTextField33.getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD)));
                myDocument.add(new Paragraph("\nDrug Code             : "+jTextField1.getText() ,FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
                myDocument.add(new Paragraph("\nDrug Name            : "+jTextField2.getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
                myDocument.add(new Paragraph("\nDrug Type              : "+jTextField3.getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
                myDocument.add(new Paragraph("\nUnit Price                : "+jTextField4.getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
                myDocument.add(new Paragraph("\nBuying Price           : "+jTextField5.getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
                myDocument.add(new Paragraph("\nQuantity                  : "+jTextField50.getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
//                myDocument.add(new Paragraph("\nSupplier                   : "+jComboBox1.getSelectedItem().toString(), FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
               // myDocument.add(new Paragraph("\nDeductions", FontFactory.getFont(FontFactory.TIMES_BOLD, 16, Font.BOLD)));
               // myDocument.add(new Paragraph("-------------------------------------------------------------------------", FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD)));
                myDocument.add(new Paragraph("\nTotal                         : "+jTextField17.getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD)));
               // myDocument.add(new Paragraph("\nLeave Deduction      : "+ldeduct.getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
               // myDocument.add(new Paragraph("\nTotal Payable Amount : "+totPay.getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 14,Font.BOLD)));
                //myDocument.add(new Paragraph("\nEPF (12%)            : "+cEPF.getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
               // myDocument.add(new Paragraph("\nETF (3%)             : "+cETF.getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
                
                myDocument.close();
                
                JOptionPane.showMessageDialog(null, "Record successfuly saved");
            }
            catch(Exception e){
                
            }
        }
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton40ActionPerformed
        // TODO add your handling code here:
        
        LoadTableDrug();
        
        
        
        
    }//GEN-LAST:event_jButton40ActionPerformed

    private void jSpinner2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jSpinner2KeyPressed
        // TODO add your handling code here:
        
      
    }//GEN-LAST:event_jSpinner2KeyPressed

    private void jTextField15KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField15KeyPressed
        // TODO add your handling code here:
       
        try
        {
            Float i=Float.parseFloat(jTextField15.getText());
             unpError.setText("");
             
        }
        
        catch(NumberFormatException e1)
        {
            unpError.setText("Invalid Syntax ");
        }
        
    }//GEN-LAST:event_jTextField15KeyPressed

    private void jTextField16KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField16KeyPressed
        // TODO add your handling code here:
        
        
        try
        {
            Float i=Float.parseFloat(jTextField16.getText());
             totErr.setText("");
        }
        
        catch(NumberFormatException e1)
        {
            totErr.setText("Invalid Syntax ");
        }
        
    }//GEN-LAST:event_jTextField16KeyPressed

    private void jTextField5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyPressed
        // TODO add your handling code here:
       
         try
        {
            Float i=Float.parseFloat(jTextField5.getText());
             bpErr.setText("");
        }
        
        catch(NumberFormatException e1)
        {
            bpErr.setText("Invalid Syntax ");
        }
        
    }//GEN-LAST:event_jTextField5KeyPressed

    private void jTextField50KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField50KeyPressed
        // TODO add your handling code here:
            try
        {
            Float i=Float.parseFloat(jTextField50.getText());
             qtyErr.setText("");
        }
        
        catch(NumberFormatException e1)
        {
            qtyErr.setText("Invalid Syntax ");
        }
       
    }//GEN-LAST:event_jTextField50KeyPressed

    private void jButton8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MouseClicked
        // TODO add your handling code here:
                
         int col=drugT.getSelectedColumn();
        String a1=drugT.getValueAt(0, 0).toString();
        jTextField13.setText(a1);
        
        
        
        
        
        
           
    }//GEN-LAST:event_jButton8MouseClicked

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        // TODO add your handling code here:
         JFileChooser dialog= new JFileChooser();
        dialog.setSelectedFile(new File("Equipment_BiLL"+jTextField34.getText()+".pdf"));
        int dialogResult = dialog.showSaveDialog(null);
        if(dialogResult == JFileChooser.APPROVE_OPTION){
            String filePath = dialog.getSelectedFile().getPath();
            try{
                Document myDocument = new Document();
                PdfWriter myWriter = PdfWriter.getInstance(myDocument, new FileOutputStream(filePath));
                myDocument.open();
                
                myDocument.add(new Paragraph("Deegayuu - Medical Center", FontFactory.getFont(FontFactory.TIMES_BOLD, 16, Font.BOLD)));
                myDocument.add(new Paragraph("Bill", FontFactory.getFont(FontFactory.TIMES_BOLD, 15, Font.BOLD)));
                myDocument.add(new Paragraph(new Date().toString()));
                myDocument.add(new Paragraph("-------------------------------------------------------------------------", FontFactory.getFont(FontFactory.TIMES_BOLD, 20, Font.BOLD)));
                myDocument.add(new Paragraph("\nBill No                     : "+jTextField34.getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD)));
                myDocument.add(new Paragraph("\nSerial No                 : "+jTextField35.getText() ,FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
                myDocument.add(new Paragraph("\nName                       : "+jTextField36.getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
                myDocument.add(new Paragraph("\nType                       : "+jTextField37.getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
                myDocument.add(new Paragraph("\nManufacture         : "+jTextField38.getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
                myDocument.add(new Paragraph("\nSupplier                 : "+jTextField39.getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
                myDocument.add(new Paragraph("\nPrice                      : "+jTextField40.getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
                myDocument.add(new Paragraph("\nQuantity                : "+jTextField41.getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
               // myDocument.add(new Paragraph("\nDeductions", FontFactory.getFont(FontFactory.TIMES_BOLD, 16, Font.BOLD)));
               // myDocument.add(new Paragraph("-------------------------------------------------------------------------", FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD)));
                myDocument.add(new Paragraph("\nTotal                        : "+jTextField42.getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD)));
               // myDocument.add(new Paragraph("\nLeave Deduction      : "+ldeduct.getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
               // myDocument.add(new Paragraph("\nTotal Payable Amount : "+totPay.getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 14,Font.BOLD)));
                //myDocument.add(new Paragraph("\nEPF (12%)            : "+cEPF.getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
               // myDocument.add(new Paragraph("\nETF (3%)             : "+cETF.getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
                
                myDocument.close();
                
                JOptionPane.showMessageDialog(null, "Bill successfuly saved");
            }
            catch(Exception e){
                
            }
        }
        
    }//GEN-LAST:event_jButton30ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
             JFileChooser dialog= new JFileChooser();
        dialog.setSelectedFile(new File("LabReport_BiLL"+jTextField34.getText()+".pdf"));
        int dialogResult = dialog.showSaveDialog(null);
        if(dialogResult == JFileChooser.APPROVE_OPTION){
            String filePath = dialog.getSelectedFile().getPath();
            try{
                Document myDocument = new Document();
                PdfWriter myWriter = PdfWriter.getInstance(myDocument, new FileOutputStream(filePath));
                myDocument.open();
                
                myDocument.add(new Paragraph("Deegayuu - Medical Center", FontFactory.getFont(FontFactory.TIMES_BOLD, 16, Font.BOLD)));
                myDocument.add(new Paragraph("Bill", FontFactory.getFont(FontFactory.TIMES_BOLD, 15, Font.BOLD)));
                myDocument.add(new Paragraph(new Date().toString()));
                myDocument.add(new Paragraph("-------------------------------------------------------------------------", FontFactory.getFont(FontFactory.TIMES_BOLD, 20, Font.BOLD)));
                myDocument.add(new Paragraph("\nBill No                  : "+jTextField21.getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD)));
                myDocument.add(new Paragraph("\nPatient Name       : "+jTextField27.getText() ,FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
                myDocument.add(new Paragraph("\nTest No                 : "+jTextField23.getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
                myDocument.add(new Paragraph("\nTest Name            : "+jTextField24.getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
             
               
                myDocument.add(new Paragraph("\nCharge                  : "+jTextField26.getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD)));
               
               
                myDocument.close();
                
                JOptionPane.showMessageDialog(null, "Bill successfuly saved");
            }
            catch(Exception e){
                
            }
        }
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:String name=jTextField28.getText();
      //String sql="Select drugCode,drugName,totQty,unitPrice from drugs where drugCode='"+name+"'";
      
      String name=jTextField28.getText();
      String sql="SELECT i.presNo,i.drugCode,i.qty,d.drugName,d.unitPrice FROM issued i,drugs d WHERE i.drugCode=d.drugCode and i.presNo='"+name+"'";
      
        System.out.println(name);
        Connection conn = DBconnect.getConnection();
        try{
          
         pst =conn.prepareStatement(sql);
         rs=pst.executeQuery();
        }
        catch(Exception e){
        }
        
        
        
        
        JFileChooser dialog= new JFileChooser();
        dialog.setSelectedFile(new File("Drugs_Bill"+jTextField33.getText()+".pdf"));
        int dialogResult = dialog.showSaveDialog(null);
        if(dialogResult == JFileChooser.APPROVE_OPTION){
            String filePath = dialog.getSelectedFile().getPath();
            try{
                Document myDocument = new Document();
                PdfWriter myWriter = PdfWriter.getInstance(myDocument, new FileOutputStream(filePath));
                myDocument.open();
                
                myDocument.add(new Paragraph("Deegayuu - Medical Center", FontFactory.getFont(FontFactory.TIMES_BOLD, 16, Font.BOLD)));
                myDocument.add(new Paragraph("Bill", FontFactory.getFont(FontFactory.TIMES_BOLD, 15, Font.BOLD)));
                myDocument.add(new Paragraph(new Date().toString()));
                myDocument.add(new Paragraph("-------------------------------------------------------------------------", FontFactory.getFont(FontFactory.TIMES_BOLD, 20, Font.BOLD)));
                myDocument.add(new Paragraph("\nBill No                     : "+jTextField32.getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD)));
                 myDocument.add(new Paragraph("\nPrescription No        : "+jTextField13.getText() ,FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
                while(rs.next()){
                    String drName = rs.getString("drugName");
                    String drQty = rs.getString("qty");
                    String drprice = rs.getString("unitPrice");
                     myDocument.add(new Paragraph("\n"+drName+"        "+drQty+"       "+drprice ,FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
                }
                //myDocument.add(new Paragraph("\nDrug Code         : "+jTextField1.getText() ,FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
                //myDocument.add(new Paragraph("\nDrug Name         : "+jTextField2.getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
                //myDocument.add(new Paragraph("\nDrug Type         : "+jTextField3.getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
               // myDocument.add(new Paragraph("\nUnit Price        : "+jTextField4.getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
                //myDocument.add(new Paragraph("\nBuying Price      : "+jTextField5.getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
                //myDocument.add(new Paragraph("\nQuantity          : "+jTextField50.getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
                //myDocument.add(new Paragraph("\nSupplier          : "+jComboBox1.getSelectedItem().toString(), FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
               // myDocument.add(new Paragraph("\nDeductions", FontFactory.getFont(FontFactory.TIMES_BOLD, 16, Font.BOLD)));
               // myDocument.add(new Paragraph("-------------------------------------------------------------------------", FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD)));
                myDocument.add(new Paragraph("\nTotal                         : Rs. "+jTextField16.getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD)));
               // myDocument.add(new Paragraph("\nLeave Deduction      : "+ldeduct.getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
               // myDocument.add(new Paragraph("\nTotal Payable Amount : "+totPay.getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 14,Font.BOLD)));
                //myDocument.add(new Paragraph("\nEPF (12%)            : "+cEPF.getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
               // myDocument.add(new Paragraph("\nETF (3%)             : "+cETF.getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
                
                myDocument.close();
                
                JOptionPane.showMessageDialog(null, "Bill successfuly saved");
            }
            catch(Exception e){
                
            }
        }
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jTextField46KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField46KeyReleased
        String query="select patientID,name,dob,gender,contactNo from patient where patientID like ?" ;

        String contactNo=jTextField46.getText()+"%";
        search(contactNo,jTable16  ,query);
    }//GEN-LAST:event_jTextField46KeyReleased

    private void jTable16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable16MouseClicked

        DefaultTableModel model =(DefaultTableModel) jTable16.getModel();
        int patID=Integer.parseInt(model.getValueAt(jTable16.getSelectedRow(),0).toString());
        String query="select a.apRefNo,s.sessionID,d.empNo,d.name,d.doctorFee,a.appNo,a.time,a.date,a.fee ,a.billStatus from appointment a,doctor d,session s where a.sessionID=s.sessionID and s.empNo=d.empNo AND a.patientID = ? and billStatus='UnBilled'";
        search2(patID,jTable17,query);

    }//GEN-LAST:event_jTable16MouseClicked

    private void jTable17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable17MouseClicked
        DefaultTableModel model =(DefaultTableModel) jTable17.getModel();
        DefaultTableModel model2 =(DefaultTableModel) jTable16.getModel();
        String apRefNo =model.getValueAt(jTable17.getSelectedRow(),0).toString();
        String patName =model2.getValueAt(jTable16.getSelectedRow(),1).toString();
        String docname =model.getValueAt(jTable17.getSelectedRow(),3).toString();
        String docfee=model.getValueAt(jTable17.getSelectedRow(),4).toString();
        String total=model.getValueAt(jTable17.getSelectedRow(),8).toString();
        //  String date =model.getValueAt(jTable17.getSelectedRow(),6).toString();
        String time =model.getValueAt(jTable17.getSelectedRow(),5).toString();
        String fee =model.getValueAt(jTable17.getSelectedRow(),7).toString();

        jTextField58.setText(apRefNo);
        jTextField57.setText(patName);
        jTextField56.setText(docname);
        jTextField53.setText(hospitalFee);
        jTextField54.setText(docfee);
        //
    }//GEN-LAST:event_jTable17MouseClicked

    private void jButton48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton48ActionPerformed
        DefaultTableModel model =(DefaultTableModel) jTable17.getModel();
        String fee =model.getValueAt(jTable17.getSelectedRow(),8).toString();
        jTextField55.setText(fee);
    }//GEN-LAST:event_jButton48ActionPerformed

    private void jButton49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton49ActionPerformed
        // TODO add your handling code here:
        jTextField58.setText("");
        jTextField57.setText("");
        jTextField56.setText("");
        jTextField53.setText("");
        jTextField54.setText("");
        jTextField55.setText("");
        
        
        
    }//GEN-LAST:event_jButton49ActionPerformed

    private void jButton50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton50ActionPerformed
        try {
            Connection con=DBconnect.getConnection();
            con=DBconnect.getConnection();

            DefaultTableModel model2 =(DefaultTableModel) jTable16.getModel();
            int pid =Integer.parseInt(model2.getValueAt(jTable16.getSelectedRow(),0).toString());
            String query1 = "insert into paychanel(apRefNo,PID,pName,DocName,HosFee,DocFee,Total) values(?,?,?,?,?,?,?)";
                 LoadTablechanel();
            PreparedStatement pst2 =con.prepareStatement(query1);

            //insert into  table
            pst2.setString(1,jTextField58.getText());
            pst2.setInt(2,pid);
            pst2.setString(3,jTextField57.getText());
            pst2.setString(4,jTextField56.getText());
            pst2.setDouble(5,Double.parseDouble(jTextField53.getText()));
            pst2.setDouble(6,Double.parseDouble(jTextField54.getText()));
            pst2.setDouble(7,Double.parseDouble(jTextField55.getText()));
            

            pst2.executeUpdate();

            String query2="update appointment set billStatus='Billed' where apRefNo=?";
            PreparedStatement pst3=con.prepareStatement(query2);
            pst3.setString(1,jTextField57.getText());
            pst3.executeUpdate();
            LoadTablechanel();
            
            DefaultTableModel model =(DefaultTableModel) jTable16.getModel();
        int patID=Integer.parseInt(model.getValueAt(jTable16.getSelectedRow(),0).toString());
        String query="select a.apRefNo,s.sessionID,d.empNo,d.name,d.doctorFee,a.appNo,a.time,a.date,a.fee ,a.billStatus from appointment a,doctor d,session s where a.sessionID=s.sessionID and s.empNo=d.empNo AND a.apRefNo = ?";
        search(jTextField58.getText(),jTable17,query);
            

        } catch (SQLException ex) {
            Logger.getLogger(paymenthandeling.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton50ActionPerformed

    private void jButton55ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton55ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton55ActionPerformed

    private void jTabledbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabledbMouseClicked
        // TODO add your handling code here:
        
        int row=jTabledb.getSelectedRow();
        String a1=jTabledb.getValueAt(row, 0).toString();
        String a2=jTabledb.getValueAt(row, 1).toString();
        String a3=jTabledb.getValueAt(row, 2).toString();
        String a4=jTabledb.getValueAt(row, 3).toString();
        String a5=jTabledb.getValueAt(row, 4).toString();
        String a6=jTabledb.getValueAt(row, 5).toString();
        String a7=jTabledb.getValueAt(row, 6).toString();
        
        
        jTextField9.setText(a1);
        jTextField58.setText(a2);
        jTextField57.setText(a3);
        jTextField56.setText(a4);
        jTextField53.setText(a5);
        jTextField54.setText(a6);
        jTextField55.setText(a7);
        
        
        
    }//GEN-LAST:event_jTabledbMouseClicked

    private void jButton54ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton54ActionPerformed
        // TODO add your handling code here:
         try{
            conn = DBconnect.getConnection();
            String report = "C:\\Users\\HP User\\Desktop\\itp\\ITPproject\\src\\chanReport.jrxml";
            JasperReport jr = JasperCompileManager.compileReport(report);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
            JasperViewer.viewReport(jp,false);
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_jButton54ActionPerformed

    private void jButton56ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton56ActionPerformed
        // TODO add your handling code here:
         try{
            conn = DBconnect.getConnection();
            String report = "C:\\Users\\HP User\\Desktop\\itp\\ITPproject\\src\\supReport.jrxml";
            JasperReport jr = JasperCompileManager.compileReport(report);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
            JasperViewer.viewReport(jp,false);
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_jButton56ActionPerformed

    private void jButton57ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton57ActionPerformed
        // TODO add your handling code here:
         try{
            conn = DBconnect.getConnection();
            String report = "C:\\Users\\HP User\\Desktop\\itp\\ITPproject\\src\\maintReport.jrxml";
            JasperReport jr = JasperCompileManager.compileReport(report);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
            JasperViewer.viewReport(jp,false);
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_jButton57ActionPerformed

    private void jButton58ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton58ActionPerformed
        // TODO add your handling code here:
         try{
            conn = DBconnect.getConnection();
            String report = "C:\\Users\\HP User\\Desktop\\itp\\ITPproject\\src\\EquipReport.jrxml";
            JasperReport jr = JasperCompileManager.compileReport(report);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
            JasperViewer.viewReport(jp,false);
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_jButton58ActionPerformed

    private void jButton60ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton60ActionPerformed
        // TODO add your handling code here:
        try{
            conn = DBconnect.getConnection();
            String report = "C:\\Users\\HP User\\Desktop\\itp\\ITPproject\\src\\LabReport.jrxml";
            JasperReport jr = JasperCompileManager.compileReport(report);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
            JasperViewer.viewReport(jp,false);
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_jButton60ActionPerformed

    private void jButton61ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton61ActionPerformed
        // TODO add your handling code here:
        
        try{
            conn = DBconnect.getConnection();
            String report = "C:\\Users\\HP User\\Desktop\\itp\\ITPproject\\src\\DrugsReport.jrxml";
            JasperReport jr = JasperCompileManager.compileReport(report);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
            JasperViewer.viewReport(jp,false);
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_jButton61ActionPerformed

    private void jTextField46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField46ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField46ActionPerformed
/*
    private void LoadTable(){
        
        Connection conn = dbconnect.connect();
        
        try
        {
            
            String sql;
            sql = "SELECT * from paychanel ";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery(sql);
            jTable5.setModel(DbUtils.resultSetToTableModel(rs));
            //JOptionPane.showMessageDialog(null,"Successfull");
        
        }
        
        catch(SQLException e)
                {
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null,"UN-Successfull");
                }
  
    
    }
 */   
    //Maintainance
//    
//    private String getEquiNo()
//    {
//        
//   return  jTextField44.getText();
//    }
//            
//    
//    private float getPCost()
//    {
//        
//   return  Float.parseFloat(jTextField45.getText());
//    }
//    
//       private float getSCost()
//    {
//        
//    return  Float.parseFloat(jTextField47.getText());
//   }
//      
//         private float getTCost()
//   {
//        
//   return  Float.parseFloat(jTextField46.getText());
//    }
//         
  //update Maintainance
         
//         private String getUpEquiNo()
//         {
//         return jTextField44.getText();
//         }
//         
//       private float getUpPCost()
//    {
//        
//    return Float.parseFloat(jTextField45.getText());
//   }
//         private float getUpSCost()
//    {
//        
//    return Float.parseFloat(jTextField47.getText());
//   }
//      private float getUpTCost()
//    {
//        
//    return Float.parseFloat(jTextField46.getText());
//   } 
//         
//      
      
      //Equipment
//
//
//      private String getsno1()
//    {
//        
//   return  jTextField44.getText();
//    }
//     
//          private String getname1()
//    {
//        
//   return  jTextField44.getText();
//    }
//               private String gettype1()
//    {
//        
//   return  jTextField44.getText();
//    }
//                    private String getmanuf1()
//    {
//        
//   return  jTextField44.getText();
//    }
//        private String getsupp1()
//    {
//        
//   return  jTextField44.getText();
//    }
//        
//    private float getprice1()
//    {
//        
//   return  Float.parseFloat(jTextField45.getText());
//    }
//    
//        private float getqty()
//    {
//        
//   return  Float.parseFloat(jTextField45.getText());
//    }
//        
//        private float gettcost1()
//    {
//        
//   return  Float.parseFloat(jTextField45.getText());
//    }
   
     //Update Equip
        
    /*    
         private String getUpsno1()
    {
        
   return  jTextField44.getText();
    }
     
          private String getUpname1()
    {
        
   return  jTextField44.getText();
    }
               private String getUptype1()
    {
        
   return  jTextField44.getText();
    }
               
               
         private String getUpmanuf1()
    {
        
   return  jTextField44.getText();
    }
         
         
        private String getUpsupp1()
    {
        
   return  jTextField44.getText();
    }
        
        
    private float getUpprice1()
    {
        
   return  Float.parseFloat(jTextField45.getText());
    }
    
        private float getUpqty1()
    {
        
   return  Float.parseFloat(jTextField45.getText());
    }
        
        private float gettUptcost1()
    {
        
   return  Float.parseFloat(jTextField45.getText());
    }
        
      */  
        
        
    
     private void LoadTable1(){
        
        Connection conn = DBconnect.getConnection();
        
        try
        {
            
            String sql;
            sql = "SELECT * from paymaintain ";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery(sql);
           jTable6.setModel(DbUtils.resultSetToTableModel(rs));
            //JOptionPane.showMessageDialog(null,"Successfull");
        
        }
        
        catch(SQLException e)
                {
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null,"UN Successfull");
                }
        
     }
        
        
//        private void LoadTable2(){
//        
//        Connection conn = dbconnect.connect();
//        
//        try
//        {
//            
//            String sql;
//            sql = "SELECT * from payequip ";
//            pst = conn.prepareStatement(sql);
//            rs = pst.executeQuery(sql);
//           jTable5.setModel(DbUtils.resultSetToTableModel(rs));
//            //JOptionPane.showMessageDialog(null,"Successfull");
//        
//        }
//        
//        catch(SQLException e)
//                {
//                    System.out.println(e);
//                    JOptionPane.showMessageDialog(null,"UN Successfull");
//                }
//     }
//        
//        
//        
//        
//         private void LoadTable3(){
//        
//        Connection conn = dbconnect.connect();
//        
//        try
//        {
//            
//            String sql;
//            sql = "SELECT * from paylab ";
//            pst = conn.prepareStatement(sql);
//            rs = pst.executeQuery(sql);
//           jTable5.setModel(DbUtils.resultSetToTableModel(rs));
//            //JOptionPane.showMessageDialog(null,"Successfull");
//        
//        }
//        
//        catch(SQLException e)
//                {
//                    System.out.println(e);
//                    JOptionPane.showMessageDialog(null,"UN Successfull");
//                }
//        
//                }
//        
//         
//         private void LoadTable4(){
//        
//        Connection conn = dbconnect.connect();
//        
//        try
//        {
//            
//            String sql;
//            sql = "SELECT * from paychanel ";
//            pst = conn.prepareStatement(sql);
//            rs = pst.executeQuery(sql);
//           jTable5.setModel(DbUtils.resultSetToTableModel(rs));
//            //JOptionPane.showMessageDialog(null,"Successfull");
//        
//        }
//        
//        catch(SQLException e)
//                {
//                    System.out.println(e);
//                    JOptionPane.showMessageDialog(null,"UN Successfull");
//                }
//         }
//         
//         
//         private void LoadTableDrug()
//         {
//             String q1="Select b.drugCode, d.drygName, b.qty, d.unitPrice  from drugs d, buy b where d.drugCode=b.drugCode" ;
//             
//             try
//             {
//                pst=conn.prepareStatement(q1);
//                rs=pst.executeQuery();
//                
//                drugT.setModel(DbUtils.resultSetToTableModel(rs));
//                
//             }
//             catch(Exception e)
//             {
//                 System.out.println("Error: "+e);
//             }
//             
//             
//         }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bpErr;
    private javax.swing.JTable drugT;
    private javax.swing.JTable equipTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton42;
    private javax.swing.JButton jButton43;
    private javax.swing.JButton jButton45;
    private javax.swing.JButton jButton46;
    private javax.swing.JButton jButton47;
    private javax.swing.JButton jButton48;
    private javax.swing.JButton jButton49;
    private javax.swing.JButton jButton50;
    private javax.swing.JButton jButton54;
    private javax.swing.JButton jButton55;
    private javax.swing.JButton jButton56;
    private javax.swing.JButton jButton57;
    private javax.swing.JButton jButton58;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton60;
    private javax.swing.JButton jButton61;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane28;
    private javax.swing.JScrollPane jScrollPane29;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable16;
    private javax.swing.JTable jTable17;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable6;
    private javax.swing.JTable jTabledb;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField28;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField30;
    private javax.swing.JTextField jTextField32;
    private javax.swing.JTextField jTextField33;
    private javax.swing.JTextField jTextField34;
    private javax.swing.JTextField jTextField35;
    private javax.swing.JTextField jTextField36;
    private javax.swing.JTextField jTextField37;
    private javax.swing.JTextField jTextField38;
    private javax.swing.JTextField jTextField39;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField40;
    private javax.swing.JTextField jTextField41;
    private javax.swing.JTextField jTextField42;
    private javax.swing.JTextField jTextField43;
    private javax.swing.JTextField jTextField44;
    private javax.swing.JTextField jTextField45;
    private javax.swing.JTextField jTextField46;
    private javax.swing.JTextField jTextField47;
    private javax.swing.JTextField jTextField48;
    private javax.swing.JTextField jTextField49;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField50;
    private javax.swing.JTextField jTextField53;
    private javax.swing.JTextField jTextField54;
    private javax.swing.JTextField jTextField55;
    private javax.swing.JTextField jTextField56;
    private javax.swing.JTextField jTextField57;
    private javax.swing.JTextField jTextField58;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JLabel qtyErr;
    private javax.swing.JTable supTable;
    private javax.swing.JLabel totErr;
    private javax.swing.JLabel unpError;
    // End of variables declaration//GEN-END:variables
}
