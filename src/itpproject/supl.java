/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itpproject;

import cls.DBconnect;
import cls.Order;
import cls.drug;
import cls.emailValidation;
import cls.supplier;
import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import net.proteanit.sql.DbUtils;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Admin
 */
public class supl extends javax.swing.JInternalFrame {

   Connection con = null;
   PreparedStatement pst =null;
   ResultSet rs = null;
    public supl() {
        initComponents();
        supTl();
        drugTL();
        SellTl();
        tbLoad();
        OrderTl();
        fillcombo();
        fillcombo2();
    }
     public void supTl()
   {
       try 
       {
           
           con=DBconnect.getConnection();
           String query ="SELECT * FROM supplier";
           pst =con.prepareStatement(query);
           rs=pst.executeQuery(query);
           SupplierTable.setModel(DbUtils.resultSetToTableModel(rs));
       } 
       catch (SQLException ex)
       {
           Logger.getLogger(supplier.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
     
     private void fillcombo()
    {
        try{
            con = itpproject.DBconnect.getConnection();
            String q = "select * from supplier";
            PreparedStatement pt = con.prepareStatement(q);
            ResultSet rs = pt.executeQuery(q);
            while (rs.next())
            {
                String c = rs.getString("name");
                OPSupplier2.addItem(c);
            }
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
     
     private void fillcombo2()
    {
        try{
            con = itpproject.DBconnect.getConnection();
            String q = "select * from supplier";
            PreparedStatement pt = con.prepareStatement(q);
            ResultSet rs = pt.executeQuery(q);
            while (rs.next())
            {
                String c = rs.getString("name");
                s.addItem(c);
            }
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
     
     public void OrderTl()
   {
       try 
       {
           
           con=DBconnect.getConnection();
           String query ="SELECT * FROM orders";
           pst =con.prepareStatement(query);
           rs=pst.executeQuery(query);
          order.setModel(DbUtils.resultSetToTableModel(rs));
       } 
       catch (SQLException ex)
       {
           Logger.getLogger(supplier.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
      public void tbLoad()
    {
        String q = "Select * from drugs";
        try{
            DefaultTableModel model = (DefaultTableModel) drug.getModel();
            model.setRowCount(0);
            con=DBconnect.getConnection();
            pst = con.prepareStatement(q);
            rs = pst.executeQuery();
            drug.setModel(DbUtils.resultSetToTableModel(rs)); 
        }
        
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
        public void SellTl()
   {
       try 
       {
           
           con=DBconnect.getConnection();
           String query ="SELECT * FROM sells";
           pst =con.prepareStatement(query);
           rs=pst.executeQuery(query);
           sell.setModel(DbUtils.resultSetToTableModel(rs));
       } 
       catch (SQLException ex)
       {
           Logger.getLogger(supplier.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
     public void drugTL()
   {
       try 
       {
           
           con=DBconnect.getConnection();
           String query ="SELECT `drugCode`, `drugName` FROM `drugs`";
           pst =con.prepareStatement(query);
           rs=pst.executeQuery(query);
           Drug.setModel(DbUtils.resultSetToTableModel(rs));
       } 
       catch (SQLException ex)
       {
           Logger.getLogger(supplier.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
 void filter(String query,javax.swing.JTable j)
 {
     DefaultTableModel dm = (DefaultTableModel)j.getModel();
     TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(dm);
     j.setRowSorter(tr);
     tr.setRowFilter(RowFilter.regexFilter(query));
 }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        entityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("hms?zeroDateTimeBehavior=convertToNullPU").createEntityManager();
        supplierQuery = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT s.name FROM Supplier s");
        supplierList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : supplierQuery.getResultList();
        supplierQuery1 = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT s.name FROM Supplier s");
        supplierList1 = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : supplierQuery1.getResultList();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        SName = new javax.swing.JLabel();
        SupCode = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        SupAddress = new javax.swing.JTextField();
        SupEmailAdd = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        SupTelNo = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        SupFax = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Remarke = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        SupplierTable = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        SName1 = new javax.swing.JLabel();
        SupName1 = new javax.swing.JTextField();
        CSupRatng = new javax.swing.JComboBox<>();
        jDateChooser7 = new com.toedter.calendar.JDateChooser();
        jPanel9 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Drug = new javax.swing.JTable();
        jTextField3 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane4 = new javax.swing.JScrollPane();
        sell = new javax.swing.JTable();
        jSeparator4 = new javax.swing.JSeparator();
        jTextField9 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        OPSupplier2 = new javax.swing.JComboBox<>();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        drug = new javax.swing.JTable();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        oq = new javax.swing.JSpinner();
        jLabel17 = new javax.swing.JLabel();
        dc = new javax.swing.JTextField();
        dn = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jScrollPane6 = new javax.swing.JScrollPane();
        order = new javax.swing.JTable();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel22 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        dc1 = new javax.swing.JTextField();
        ds = new com.toedter.calendar.JDateChooser();
        jScrollPane7 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        s = new javax.swing.JComboBox<>();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        ReportS1 = new javax.swing.JComboBox<>();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jDateChooser4 = new com.toedter.calendar.JDateChooser();
        jPanel14 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jPanel15 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jDateChooser5 = new com.toedter.calendar.JDateChooser();
        jDateChooser6 = new com.toedter.calendar.JDateChooser();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(964, 592));

        jTabbedPane1.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(964, 592));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel1.setPreferredSize(new java.awt.Dimension(964, 592));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SName.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        SName.setText("Supplier Code");
        SName.setFocusTraversalPolicyProvider(true);
        jPanel1.add(SName, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 110, -1));

        SupCode.setEditable(false);
        SupCode.setBackground(new java.awt.Color(255, 255, 255));
        SupCode.setBorder(null);
        SupCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SupCodeActionPerformed(evt);
            }
        });
        jPanel1.add(SupCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, 160, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel3.setText("Supplier Address");
        jLabel3.setFocusTraversalPolicyProvider(true);
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 120, -1));
        jPanel1.add(SupAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, 160, -1));
        jPanel1.add(SupEmailAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 180, 160, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel4.setText("Supplier Email Address");
        jLabel4.setFocusTraversalPolicyProvider(true);
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 140, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel5.setText("Supplier Telephone No");
        jLabel5.setFocusTraversalPolicyProvider(true);
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 140, -1));

        SupTelNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SupTelNoActionPerformed(evt);
            }
        });
        jPanel1.add(SupTelNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 230, 160, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel9.setText("Supplier Fax-No");
        jLabel9.setFocusTraversalPolicyProvider(true);
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 140, -1));
        jPanel1.add(SupFax, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 280, 160, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel6.setText("Rating");
        jLabel6.setFocusTraversalPolicyProvider(true);
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 60, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel14.setText("Remark");
        jLabel14.setFocusTraversalPolicyProvider(true);
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 70, -1));

        Remarke.setColumns(20);
        Remarke.setRows(5);
        jScrollPane2.setViewportView(Remarke);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 410, -1, -1));

        SupplierTable.setModel(new javax.swing.table.DefaultTableModel(
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
        SupplierTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SupplierTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(SupplierTable);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 90, 470, 290));

        jLabel7.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel7.setText("Joined Date");
        jLabel7.setFocusTraversalPolicyProvider(true);
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 140, -1));
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 580, 990, 430));

        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setBorder(null);
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });
        jPanel1.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 40, 220, 20));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Search_26px_1.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 30, 40, 40));

        jButton1.setBackground(new java.awt.Color(0, 31, 82));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Add_User_Male_26px_1.png"))); // NOI18N
        jButton1.setText("Add");
        jButton1.setActionCommand("");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 420, 110, 50));

        jButton2.setBackground(new java.awt.Color(0, 31, 82));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Registration_26px_2.png"))); // NOI18N
        jButton2.setText("Edit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 420, 120, 50));

        jButton3.setBackground(new java.awt.Color(0, 31, 82));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Change_User_26px.png"))); // NOI18N
        jButton3.setText("Delete");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 420, 120, 50));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 60, 230, 10));

        SName1.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        SName1.setText("Supplier Name");
        SName1.setFocusTraversalPolicyProvider(true);
        jPanel1.add(SName1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 110, -1));

        SupName1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SupName1ActionPerformed(evt);
            }
        });
        jPanel1.add(SupName1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, 160, -1));

        CSupRatng.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Excellent", "Grate", "Good\t", "Poor" }));
        jPanel1.add(CSupRatng, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 370, 160, 30));
        jPanel1.add(jDateChooser7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 330, 160, -1));

        jTabbedPane1.addTab("SupplierDetails", jPanel1);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jLabel43.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel43.setText("Drug Code");

        jTextField8.setEditable(false);
        jTextField8.setBackground(new java.awt.Color(255, 255, 255));
        jTextField8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField8.setBorder(null);
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });

        jLabel46.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel46.setText("Buying price ");

        jTextField11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField11ActionPerformed(evt);
            }
        });

        jLabel49.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel49.setText("Supplier");

        Drug.setModel(new javax.swing.table.DefaultTableModel(
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
        Drug.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DrugMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(Drug);

        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField3.setBorder(null);
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField3KeyReleased(evt);
            }
        });

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Search_26px_1.png"))); // NOI18N

        jButton13.setBackground(new java.awt.Color(0, 31, 82));
        jButton13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton13.setForeground(new java.awt.Color(255, 255, 255));
        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Change_User_26px.png"))); // NOI18N
        jButton13.setText("Delete");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setBackground(new java.awt.Color(0, 31, 82));
        jButton14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton14.setForeground(new java.awt.Color(255, 255, 255));
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Registration_26px_2.png"))); // NOI18N
        jButton14.setText("Edit");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.setBackground(new java.awt.Color(0, 31, 82));
        jButton15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton15.setForeground(new java.awt.Color(255, 255, 255));
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Add_User_Male_26px_1.png"))); // NOI18N
        jButton15.setText("Add");
        jButton15.setActionCommand("");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        sell.setModel(new javax.swing.table.DefaultTableModel(
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
        sell.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sellMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(sell);

        jTextField9.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField9.setBorder(null);
        jTextField9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField9KeyReleased(evt);
            }
        });

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Search_26px_1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(OPSupplier2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(211, 211, 211)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 82, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(111, 111, 111))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(143, 143, 143))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel43)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel49)
                            .addComponent(OPSupplier2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel46)
                            .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(64, 64, 64)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(76, 76, 76))
        );

        jTabbedPane1.addTab("DrugDetails", jPanel9);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(102, 102, 0)));

        drug.setModel(new javax.swing.table.DefaultTableModel(
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
        drug.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                drugMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(drug);

        jLabel30.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel30.setText("Drug Code");

        jLabel31.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel31.setText("Drug Name");

        jLabel33.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel33.setText("Order Quantity");

        jLabel34.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel34.setText("Supplier");

        jLabel35.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel35.setText("Date to be supplied");

        oq.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        oq.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        dc.setEditable(false);
        dc.setBackground(new java.awt.Color(255, 255, 255));
        dc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        dc.setBorder(null);
        dc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dcActionPerformed(evt);
            }
        });

        dn.setEditable(false);
        dn.setBackground(new java.awt.Color(255, 255, 255));
        dn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        dn.setBorder(null);

        jTextField4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField4.setBorder(null);
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField4KeyReleased(evt);
            }
        });

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Search_26px_1.png"))); // NOI18N

        jButton16.setBackground(new java.awt.Color(0, 31, 82));
        jButton16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton16.setForeground(new java.awt.Color(255, 255, 255));
        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Change_User_26px.png"))); // NOI18N
        jButton16.setText("Delete");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.setBackground(new java.awt.Color(0, 31, 82));
        jButton17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton17.setForeground(new java.awt.Color(255, 255, 255));
        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Registration_26px_2.png"))); // NOI18N
        jButton17.setText("Edit");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton18.setBackground(new java.awt.Color(0, 31, 82));
        jButton18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton18.setForeground(new java.awt.Color(255, 255, 255));
        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Add_User_Male_26px_1.png"))); // NOI18N
        jButton18.setText("Add");
        jButton18.setActionCommand("");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        order.setModel(new javax.swing.table.DefaultTableModel(
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
        order.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                orderMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(order);

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Search_26px_1.png"))); // NOI18N

        jTextField5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField5.setBorder(null);
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });
        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField5KeyReleased(evt);
            }
        });

        jLabel44.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel44.setText("Order Code");

        dc1.setEditable(false);
        dc1.setBackground(new java.awt.Color(255, 255, 255));
        dc1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        dc1.setBorder(null);
        dc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dc1ActionPerformed(evt);
            }
        });

        jList1.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        jScrollPane7.setViewportView(jList1);

        jButton9.setBackground(new java.awt.Color(0, 31, 82));
        jButton9.setFont(new java.awt.Font("Segoe UI Symbol", 1, 12)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("Add");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setBackground(new java.awt.Color(0, 31, 82));
        jButton10.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("Remove");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(ds, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel10Layout.createSequentialGroup()
                                                .addGap(1, 1, 1)
                                                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(dc1, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                                            .addComponent(s, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(dn)
                                        .addComponent(dc, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(oq, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(78, 78, 78)))
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel10Layout.createSequentialGroup()
                            .addGap(110, 110, 110)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel10Layout.createSequentialGroup()
                                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel10Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(133, 133, 133))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                            .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(42, 42, 42)
                            .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(46, 46, 46)
                            .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(15, 15, 15))
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(43, 43, 43)
                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addGap(37, 37, 37)
                                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel10Layout.createSequentialGroup()
                                                .addGap(88, 88, 88)
                                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel30)
                                                    .addComponent(dc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(23, 23, 23)
                                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jLabel31)
                                                    .addComponent(dn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jLabel33)
                                                    .addComponent(oq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(dc1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel44))
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel34)
                                        .addGap(26, 26, 26)
                                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(ds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel35))))
                                .addGap(51, 51, 51)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addComponent(jButton9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton10))))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(4, 4, 4)
                                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(s, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel10Layout.createSequentialGroup()
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(4, 4, 4)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(78, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("OrderProcessing", jPanel10);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setLayout(null);

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Suppliers Reports", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPanel12.setLayout(null);

        jButton5.setBackground(new java.awt.Color(0, 31, 82));
        jButton5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Report_Card_32px_2.png"))); // NOI18N
        jButton5.setText("Report");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel12.add(jButton5);
        jButton5.setBounds(90, 150, 170, 60);
        jPanel12.add(jLabel21);
        jLabel21.setBounds(130, 50, 110, 70);

        jPanel11.add(jPanel12);
        jPanel12.setBounds(40, 20, 400, 230);

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Supplier Wise Reports", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel13.setLayout(null);

        ReportS1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${resultList}");
        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, supplierQuery1, eLProperty, ReportS1);
        bindingGroup.addBinding(jComboBoxBinding);

        ReportS1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReportS1ActionPerformed(evt);
            }
        });
        jPanel13.add(ReportS1);
        ReportS1.setBounds(150, 40, 70, 21);

        jLabel36.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel36.setText("Supplier Name");
        jPanel13.add(jLabel36);
        jLabel36.setBounds(40, 40, 100, 20);

        jLabel37.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel37.setText("Time Period");
        jPanel13.add(jLabel37);
        jLabel37.setBounds(150, 80, 130, 20);

        jLabel13.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel13.setText("From");
        jPanel13.add(jLabel13);
        jLabel13.setBounds(20, 120, 40, 14);

        jLabel15.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel15.setText("To");
        jPanel13.add(jLabel15);
        jLabel15.setBounds(220, 120, 45, 20);

        jButton6.setBackground(new java.awt.Color(0, 31, 82));
        jButton6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Report_Card_32px_2.png"))); // NOI18N
        jButton6.setText("Report");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel13.add(jButton6);
        jButton6.setBounds(120, 170, 170, 60);
        jPanel13.add(jDateChooser3);
        jDateChooser3.setBounds(60, 120, 130, 20);
        jPanel13.add(jDateChooser4);
        jDateChooser4.setBounds(250, 120, 120, 20);

        jPanel11.add(jPanel13);
        jPanel13.setBounds(510, 20, 410, 240);

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Order Reports", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel14.setLayout(null);

        jLabel38.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel38.setText("Time Period");
        jPanel14.add(jLabel38);
        jLabel38.setBounds(110, 40, 130, 20);

        jLabel16.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel16.setText("From");
        jPanel14.add(jLabel16);
        jLabel16.setBounds(20, 70, 40, 20);

        jLabel18.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel18.setText("To");
        jPanel14.add(jLabel18);
        jLabel18.setBounds(220, 70, 15, 20);

        jButton7.setBackground(new java.awt.Color(0, 31, 82));
        jButton7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Report_Card_32px_2.png"))); // NOI18N
        jButton7.setText("Report");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel14.add(jButton7);
        jButton7.setBounds(90, 130, 170, 60);
        jPanel14.add(jDateChooser1);
        jDateChooser1.setBounds(60, 70, 120, 30);
        jPanel14.add(jDateChooser2);
        jDateChooser2.setBounds(250, 70, 110, 30);

        jPanel11.add(jPanel14);
        jPanel14.setBounds(50, 270, 390, 220);

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Supplier Reports", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel15.setLayout(null);

        jLabel40.setBackground(new java.awt.Color(0, 31, 82));
        jLabel40.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel40.setText("Time Period");
        jPanel15.add(jLabel40);
        jLabel40.setBounds(150, 40, 130, 20);

        jLabel19.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel19.setText("From");
        jPanel15.add(jLabel19);
        jLabel19.setBounds(30, 70, 40, 20);

        jLabel20.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel20.setText("To");
        jPanel15.add(jLabel20);
        jLabel20.setBounds(210, 70, 15, 20);

        jButton8.setBackground(new java.awt.Color(0, 31, 82));
        jButton8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Report_Card_32px_2.png"))); // NOI18N
        jButton8.setText("Report");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel15.add(jButton8);
        jButton8.setBounds(130, 140, 170, 60);
        jPanel15.add(jDateChooser5);
        jDateChooser5.setBounds(70, 70, 130, 20);
        jPanel15.add(jDateChooser6);
        jDateChooser6.setBounds(230, 70, 130, 20);

        jPanel11.add(jPanel15);
        jPanel15.setBounds(510, 270, 420, 220);
        jPanel11.add(jLabel41);
        jLabel41.setBounds(0, 0, 0, 90);
        jPanel11.add(jLabel42);
        jLabel42.setBounds(10, 670, 0, 340);

        jTabbedPane1.addTab("Reports", jPanel11);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 982, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SupTelNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SupTelNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SupTelNoActionPerformed

    private void SupCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SupCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SupCodeActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jTextField11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField11ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int y=JOptionPane.showConfirmDialog(null,"Do You Want to Edit");
        
        if(y==0)
        {
        String sa = SupCode.getText();
        String n = SupName1.getText();
        String sb =SupAddress.getText();
        String sc =SupEmailAdd.getText();
        String sd =SupTelNo.getText();
        String se =SupFax.getText();
        //String sf =jDateChooser7.getDate().toString();
        java.util.Date ss = jDateChooser7.getDate();
        java.sql.Date sf = new java.sql.Date(ss.getTime());
        String sg =CSupRatng.getSelectedItem().toString();
        String sh =Remarke.getText();
        supplier sp=new supplier();
        sp.editSupplier(n, sb, sc, sd, se,sf, sg,sh,sa);
        JOptionPane.showMessageDialog(null,"Successfully Updated");
        supTl();
         OPSupplier2.removeAllItems();
          s.removeAllItems();
        fillcombo();
        fillcombo2();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void ReportS1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReportS1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ReportS1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String sa = SupCode.getText();
        String n = SupName1.getText();
        String sb =SupAddress.getText();
        String sc =SupEmailAdd.getText();
        String sd =SupTelNo.getText();
        String se =SupFax.getText();
        //String sf =jDateChooser7.getDate().toString();
        java.util.Date q = jDateChooser7.getDate();
        java.sql.Date sf = new java.sql.Date(q.getTime());
        String sg =CSupRatng.getSelectedItem().toString();
        String sh =Remarke.getText();
        
        emailValidation ev =new emailValidation();
        if(ev.EmailValidat(sc) & ev.phoneNoV(sd) & ev.phoneNoV(se))
       {
          supplier sp = new supplier (n,sb,sc,sd,se,sf,sg,sh);
          supTl();
          OPSupplier2.removeAllItems();
          s.removeAllItems();
        fillcombo();
        fillcombo2();
           JOptionPane.showMessageDialog(null,"Successfully Add");
       }    
       else
        {
         JOptionPane.showMessageDialog(null,"Email or Telephone or Fax Is Invalide");
        }
        SupCode.setText("");
        SupName1.setText("");
        SupAddress.setText("");
        SupEmailAdd.setText("");
        SupTelNo.setText("");
        SupFax.setText("");
        jDateChooser7.setCalendar(null);
        Remarke.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void SupName1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SupName1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SupName1ActionPerformed

    private void SupplierTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SupplierTableMouseClicked
        int row =SupplierTable.getSelectedRow();
        String a =SupplierTable.getValueAt(row,0).toString();
        String b=SupplierTable.getValueAt(row, 1).toString();
        String c=SupplierTable.getValueAt(row, 2).toString();
        String d=SupplierTable.getValueAt(row, 3).toString();
        String e=SupplierTable.getValueAt(row, 4).toString();
        String f=SupplierTable.getValueAt(row, 5).toString();
        String h=SupplierTable.getValueAt(row, 7).toString();
        String i=SupplierTable.getValueAt(row, 8).toString();
        try {
            SimpleDateFormat date=new SimpleDateFormat("yyyy-MM-dd");
            Date datevalue =date.parse(SupplierTable.getValueAt(row,6).toString());
            jDateChooser7.setDate(datevalue);
        } catch (Exception ex) {
        }
        SupCode.setText(a);
        SupName1.setText(b);
        SupAddress.setText(c);
        SupEmailAdd.setText(d);
        SupTelNo.setText(e);
        SupFax.setText(f);
        CSupRatng.setSelectedItem(h);
        Remarke.setText(i);
        
        
    }//GEN-LAST:event_SupplierTableMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String sa = SupCode.getText();
        supplier sp = new supplier();
        int y=JOptionPane.showConfirmDialog(null,"Do You Want to Delet");
        
        if(y==0)
        {
           sp.deleteSupplier(sa);
           supTl();
            OPSupplier2.removeAllItems();
          s.removeAllItems();
        fillcombo();
        fillcombo2();
            JOptionPane.showMessageDialog(null,"Successfully Deleted");
        }
        else
        {
        
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        String que = jTextField2.getText();
        filter(que,SupplierTable);
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        String aa =jTextField8.getText();
        String bb = OPSupplier2.getSelectedItem().toString();
        double cc =Double.parseDouble(jTextField11.getText());
        
        drug dr =new drug();
        dr.Adddrug(bb, aa, cc);
         JOptionPane.showMessageDialog(null,"Successfully Add");
        SellTl();
        jTextField8.setText("");
        jTextField11.setText("");
        OPSupplier2.setSelectedItem(null);
    }//GEN-LAST:event_jButton15ActionPerformed

    private void DrugMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DrugMouseClicked
        int row =Drug.getSelectedRow();
        String a =Drug.getValueAt(row,0).toString();
        //String b=Drug.getValueAt(row,1).toString();
        //String c=Drug.getValueAt(row,2).toString();
        jTextField8.setText(a);
       // OPSupplier2.setSelectedItem(b);
        //jTextField11.setText(c);
        
    }//GEN-LAST:event_DrugMouseClicked

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        int y=JOptionPane.showConfirmDialog(null,"Do You Want to Edit");
        
        if(y==0)
        {
        String s=jTextField8.getText().toString();
        String a=OPSupplier2.getSelectedItem().toString();
        double dd=Double.parseDouble(jTextField11.getText().toString());
        drug dg=new drug();
        dg.Updatedrug(s, a, dd);
        JOptionPane.showMessageDialog(null,"Successfully Updated");
        SellTl();
        }
    }//GEN-LAST:event_jButton14ActionPerformed

    private void sellMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sellMouseClicked
        int row =sell.getSelectedRow();
        String a =sell.getValueAt(row,0).toString();
        String aa =sell.getValueAt(row,1).toString();
        String b =sell.getValueAt(row,2).toString();
        jTextField8.setText(a);
        OPSupplier2.setSelectedItem(aa);
        jTextField11.setText(b);
    }//GEN-LAST:event_sellMouseClicked

    private void jTextField3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyReleased
        String que = jTextField3.getText();
        filter(que,Drug);
    }//GEN-LAST:event_jTextField3KeyReleased

    private void jTextField9KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField9KeyReleased
        String que =  jTextField9.getText();
        filter(que,sell);
       
    }//GEN-LAST:event_jTextField9KeyReleased

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
       int y=JOptionPane.showConfirmDialog(null,"Do You Want to Delet");
        
        if(y==0)
        {
        String aa=jTextField8.getText();
       String al=OPSupplier2.getSelectedItem().toString();
       drug dg=new drug();
       dg.deleteDrug(aa,al);
       SellTl();
       JOptionPane.showMessageDialog(null,"Successfully Deleted");
        }
    }//GEN-LAST:event_jButton13ActionPerformed
DefaultListModel dm = new DefaultListModel();
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        java.util.Date sd = jDateChooser1.getDate();
        java.sql.Date stdate = new java.sql.Date(sd.getTime());
        String s = stdate.toString();
        java.util.Date ed = jDateChooser2.getDate();
        java.sql.Date endate = new java.sql.Date(ed.getTime());
        String en = endate.toString();
         HashMap param = new HashMap();
       param.put("fromDate",s);
        param.put("toDate",en);
        try{
            Connection conn = itpproject.DBconnect.getConnection();
            String report = "C:\\Users\\HP User\\Desktop\\itp\\ITPproject\\src\\orderReport.jrxml";
            JasperReport jr = JasperCompileManager.compileReport(report);
            JasperPrint jp = JasperFillManager.fillReport(jr, param, conn);
            JasperViewer.viewReport(jp,false);
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        java.util.Date sd = jDateChooser5.getDate();
        java.sql.Date stdate = new java.sql.Date(sd.getTime());
        String s = stdate.toString();
        java.util.Date ed = jDateChooser6.getDate();
        java.sql.Date endate = new java.sql.Date(ed.getTime());
        String en = endate.toString();
         HashMap param = new HashMap();
       param.put("fromDate",s);
        param.put("toDate",en);
        try{
            Connection conn = itpproject.DBconnect.getConnection();
            String report = "C:\\Users\\HP User\\Desktop\\itp\\ITPproject\\src\\supR.jrxml";
            JasperReport jr = JasperCompileManager.compileReport(report);
            JasperPrint jp = JasperFillManager.fillReport(jr, param, conn);
            JasperViewer.viewReport(jp,false);
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        java.util.Date sd = jDateChooser3.getDate();
        java.sql.Date stdate = new java.sql.Date(sd.getTime());
        String s = stdate.toString();
        java.util.Date ed = jDateChooser4.getDate();
        java.sql.Date endate = new java.sql.Date(ed.getTime());
        String en = endate.toString();
        String snn=ReportS1.getSelectedItem().toString();
         HashMap param = new HashMap();
       param.put("fromDate",s);
        param.put("toDate",en);
        param.put("SupName",snn);
        try{
            Connection conn = itpproject.DBconnect.getConnection();
            String report = "C:\\Users\\HP User\\Desktop\\itp\\ITPproject\\src\\sOrder.jrxml";
            JasperReport jr = JasperCompileManager.compileReport(report);
            JasperPrint jp = JasperFillManager.fillReport(jr, param, conn);
            JasperViewer.viewReport(jp,false);
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
         try{
            Connection conn = itpproject.DBconnect.getConnection();
            String report = "C:\\Users\\HP User\\Desktop\\itp\\ITPproject\\src\\suRP.jrxml";
            JasperReport jr = JasperCompileManager.compileReport(report);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
            JasperViewer.viewReport(jp,false);
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:

        dm.removeElement(jList1.getSelectedValue());
        jList1.setModel(dm);

    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:

        ListModel model = jList1.getModel();
        boolean check = true;
        String d = dc.getText();
        int q = (int) oq.getValue();
        for (int i=0; i<model.getSize();i++)
        {
            String n = (String) model.getElementAt(i);
            String val[] = n.split(" ");
            String n1 = val[0];
            if (d.equals(n1))
            {
                check=false;
                break;
            }
        }
        try{
            if (check)
            {
                String list = dc.getText()+"     "+dn.getText()+"     "+oq.getValue().toString();
                dm.addElement(list);
                jList1.setModel(dm);
                dc.setText(" ");
                dn.setText(" ");
                System.out.println("kota");
                oq.setValue(0);
            }
            else
            {
                getToolkit().beep();
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }//GEN-LAST:event_jButton9ActionPerformed

    private void dc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dc1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dc1ActionPerformed

    private void jTextField5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyReleased
        String que =jTextField5.getText();
        filter(que,order);
    }//GEN-LAST:event_jTextField5KeyReleased

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void orderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_orderMouseClicked
        // TODO add your handling code here:
        int row =order.getSelectedRow();
        String a =order.getValueAt(row,0).toString();
        String b = order.getValueAt(row, 1).toString();
        int c =(int) order.getValueAt(row,2);
        String d = order.getValueAt(row, 6).toString();

        dc1.setText(a);
        try{
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            Date datvalue = date.parse(b);
            ds.setDate(datvalue);
        }
        catch(Exception e)
        {}
        // oq.setValue(c);
        s.setSelectedItem(d);
    }//GEN-LAST:event_orderMouseClicked

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        //String a=oq.getValue().toString();
        //int aa=Integer.parseInt(a);
        String b=s.getSelectedItem().toString();

        String c=dc.getText();
        // java.util.Date s = jDateChooser7.getDate();
        //java.sql.Date sf = new java.sql.Date(s.getTime());
        //SimpleDateFormat dcn = new SimpleDateFormat("yyyy-MM-dd");
        //String sf = dcn.format(jDateChooser7.getDate());
        java.util.Date ss = ds.getDate();
        java.sql.Date sf = new java.sql.Date(ss.getTime());
        String name =  dn.getText();

        try{
            con = DBconnect.getConnection();
            PreparedStatement pt = null;

            String list = (String) jList1.getSelectedValue();
            ListModel model = jList1.getModel();

            for (int i=0; i<model.getSize(); i++)
            {
                String dr = (String) model.getElementAt(i);
                //System.out.println("reding dr = (String) model.getElementAt(i);da");
                String val[] = dr.split("     ");
                String dc = val[0];
                String dn = val[1];
                String a = val[2];
                System.out.println("kotaaaaa");

                int oq = Integer.parseInt(a);

                //System.out.println("teek");
                System.out.println("shoooooooooooook");
                String query="insert into orders (dateOfPurchase,qtyOrdered,drugCode,suppName)values (?,?,?,?)";
                //System.out.println("redehfusejdda");
                pt= con.prepareStatement(query);

                pt.setDate(1, sf);
                pt.setInt(2, oq);
                pt.setString(3, dc);
                pt.setString(4, b);

                //pt.setInt(2, oq);

                pt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Inserted");
                try{

                    String host = "smtp.gmail.com";
                    String user = "deegayuu@gmail.com";
                    String pass = "deegayuu123";
                    String to=null;
                    con=DBconnect.getConnection();
                    String qu ="SELECT * FROM `supplier` WHERE name='"+b+"'";
                    pst =con.prepareStatement(qu);
                    rs=pst.executeQuery();

                    while(rs.next())
                    {
                        to = rs.getString("SupplierEmail");
                    }
                    String from = "deegayuu@gmail.com";
                    String subject = "Make new Order";
                    String msg1=null;
                    if(msg1==null)
                    {
                        msg1=dr+"\n";
                    }
                    else
                    {
                        msg1=msg1+"\n"+dr;
                    }
                    String msg = "Dear Supplier \n This is the new Order we need before''"+sf+"'. \n"+msg1;
                    boolean sessionDebug = false;

                    System.out.println("shdsdsdsdook");

                    Properties props = System.getProperties();

                    props.put("mail.smtp.starttls.enable", "true");
                    props.put("mail.smtp.host", host);
                    props.put("mail.smtp.port", "587");
                    props.put("mail.smtp.auth", "true");
                    props.put("mail.smtp.starttls.required", "true");

                    java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

                    javax.mail.Session mailSession = javax.mail.Session.getDefaultInstance(props,null);
                    mailSession.setDebug(sessionDebug);
                    Message msgg = new MimeMessage(mailSession);
                    msgg.setFrom(new InternetAddress(from));
                    InternetAddress[] address = {new InternetAddress(to)};
                    msgg.setRecipients(Message.RecipientType.TO, address);
                    msgg.setSubject(subject);
                    msgg.setSentDate(new java.util.Date());
                    msgg.setText(msg);

                    System.out.println("ayoooooooooooooooooooooooooo");

                    Transport transport = mailSession.getTransport("smtp");
                    transport.connect(host, user, pass);
                    transport.sendMessage(msgg, msgg.getAllRecipients());
                    transport.close();
                    // System.out.print("Msg sent sussessfuly!!!!!"); zx

                    System.out.println("77777777777777777777777");

                    JOptionPane.showMessageDialog(null,"email sent");
                }

                catch(Exception e){
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }

        }
        catch(Exception e)
        {

        }

        Order od = new Order();
        //od.AddOrder(c,aa,b,sf);
        OrderTl();
        dc.setText("");
        dc1.setText("");
        dn.setText("");
        //dt.setText("");
        oq.setValue(0);
        s.setSelectedItem(null);
        ds.setDate(null);
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        int y=JOptionPane.showConfirmDialog(null,"Do You Want to Edit");

        if(y==0)
        {
            String a=oq.getValue().toString();
            int aa=Integer.parseInt(a);
            String b=s.getSelectedItem().toString();
            int c=Integer.parseInt(dc1.getText());
            java.util.Date s = jDateChooser7.getDate();
            java.sql.Date sf = new java.sql.Date(s.getTime());
            Order od =new Order();
            od.editOrder(c,aa,b,sf);
            JOptionPane.showMessageDialog(null,"Successfully Updated");
            OrderTl();
        }
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        int y=JOptionPane.showConfirmDialog(null,"Do You Want to Delet");

        if(y==0)
        {
            int c=Integer.parseInt(dc1.getText());
            Order od =new Order();
            od.deleteSupplier(c);
            OrderTl();
            JOptionPane.showMessageDialog(null,"Successfully Deleted");
        }
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jTextField4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyReleased
        String que = jTextField4.getText();
        filter(que,drug);        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4KeyReleased

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void dcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dcActionPerformed

    private void drugMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_drugMouseClicked
        int row =drug.getSelectedRow();
        String a =drug.getValueAt(row,0).toString();
        String b =drug.getValueAt(row,1).toString();
        String c =drug.getValueAt(row,2).toString();
        String d =drug.getValueAt(row,4).toString();
        dc.setText(a);
        dn.setText(b);
        // dt.setText(c);
        s.setSelectedItem(d);
    }//GEN-LAST:event_drugMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CSupRatng;
    private javax.swing.JTable Drug;
    private javax.swing.JComboBox<String> OPSupplier2;
    private javax.swing.JTextArea Remarke;
    private javax.swing.JComboBox<String> ReportS1;
    private javax.swing.JLabel SName;
    private javax.swing.JLabel SName1;
    private javax.swing.JTextField SupAddress;
    private javax.swing.JTextField SupCode;
    private javax.swing.JTextField SupEmailAdd;
    private javax.swing.JTextField SupFax;
    private javax.swing.JTextField SupName1;
    private javax.swing.JTextField SupTelNo;
    private javax.swing.JTable SupplierTable;
    private javax.swing.JTextField dc;
    private javax.swing.JTextField dc1;
    private javax.swing.JTextField dn;
    private javax.swing.JTable drug;
    private com.toedter.calendar.JDateChooser ds;
    private javax.persistence.EntityManager entityManager;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private com.toedter.calendar.JDateChooser jDateChooser4;
    private com.toedter.calendar.JDateChooser jDateChooser5;
    private com.toedter.calendar.JDateChooser jDateChooser6;
    private com.toedter.calendar.JDateChooser jDateChooser7;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JSpinner oq;
    private javax.swing.JTable order;
    private javax.swing.JComboBox<String> s;
    private javax.swing.JTable sell;
    private java.util.List<itpproject.Supplier> supplierList;
    private java.util.List<itpproject.Supplier> supplierList1;
    private javax.persistence.Query supplierQuery;
    private javax.persistence.Query supplierQuery1;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
