/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *http://google.com/settings/security/lesssecureapps
 */
package itpproject;

import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;


/**
 *
 * @author hiranabayaratne
 */
public class assetsManagemant extends javax.swing.JInternalFrame {

    /**
     * Creates new form assetsManagemant
     */
    
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    ArrayList name = new ArrayList();
    
    public assetsManagemant() {
        initComponents();
        jLabel12.setVisible(false);
        jLabel14.setVisible(false);
        jLabel17.setVisible(false);
        jLabel24.setVisible(false);
        tableLoad();
        databaseName();
        mainLoad();
        supLoad();
        fillcombo();
        fillcombo2();
        fillcombo3();
        fillcombo4();
        eqLoad();
        
    }
/*void filter(String q, javax.swing.JTable j){
    DefaultTableModel dm = (DefaultTableModel) j.getModel();
    TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(dm);
    j.setRowSorter(tr);
    tr.setRowFilter(RowFilter.regexFilter(q));
}*/
     boolean isInteger (String str)
    {
        int len = str.length();
       // System.out.println(len);
        if (len==0)
        {
            //System.out.println("1231");
            return false;
        }
        for (int i=0; i <len; ++i)
        {
            if (!Character.isDigit(str.charAt(i)))
                return false;
        }
        System.out.println(len);
        return true;
    }
        
      
    
    public void tableLoad()
    
    {
        try {
                con = DBconnect.getConnection();
                String sql = "Select * from assests";
                PreparedStatement pt = (PreparedStatement) con.prepareStatement(sql);
                ResultSet rs = pt.executeQuery();               
                jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            }
    }
    
    public void eqLoad()
    
    {
        try {
                con = DBconnect.getConnection();
                String sql = "Select serialNo,name from assests";
                PreparedStatement pt = (PreparedStatement) con.prepareStatement(sql);
                ResultSet rs = pt.executeQuery();               
                jTable6.setModel(DbUtils.resultSetToTableModel(rs));
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            }
    }
    
   
    public void mainLoad()
    {
        try {
            con = DBconnect.getConnection();
            String sql = "Select * from maintainance";
            PreparedStatement pt = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = pt.executeQuery();

            jTable4.setVisible(true);
            jTable4.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void databaseName ()
    {
        try{
            con = DBconnect.getConnection();
            String sql = "Select * from assests";
            PreparedStatement pt = con.prepareStatement(sql);
            ResultSet rs = pt.executeQuery(sql);
            
            while (rs.next())
            {
                String Name = rs.getString("serialNo");
                name.add(Name);
            }
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void autoComplete(String txt)
    {
        String complete = " ";
        int start = txt.length();
        int last = txt.length();
        int a;
        
        for (a=0; a<name.size(); a++ )
        {
            if (name.get(a).toString().startsWith(txt))
            {
                complete = name.get(a).toString();
                last = complete.length();
                break;
            }
        }
        if (last>start)
        {
            jTextField42.setText(complete);
            jTextField42.setCaretPosition(last);
            jTextField42.moveCaretPosition(start);
        }
    }
    
    public void autoComplete2(String txt)
    {
        String complete = " ";
        int start = txt.length();
        int last = txt.length();
        int a;
        
        for (a=0; a<name.size(); a++ )
        {
            if (name.get(a).toString().startsWith(txt))
            {
                complete = name.get(a).toString();
                last = complete.length();
                break;
            }
        }
        if (last>start)
        {
            jTextField14.setText(complete);
            jTextField14.setCaretPosition(last);
            jTextField14.moveCaretPosition(start);
        }
    }
    
    public void supLoad()
    {
        try {
            con = DBconnect.getConnection();
            String sql = "Select * from eqsupplier";
            PreparedStatement pt = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = pt.executeQuery();

            jTable5.setVisible(true);
            jTable5.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void fillcombo()
    {
        try{
            con = DBconnect.getConnection();
            String q = "select distinct name from eqsupplier";
            PreparedStatement pt = con.prepareStatement(q);
            ResultSet rs = pt.executeQuery(q);
            while (rs.next())
            {
                String c = rs.getString("name");
                jComboBox2.addItem(c);
            }
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
     private void fillcombo2()
    {
        try{
            con = DBconnect.getConnection();
            String q = "select * from eqsupplier";
            PreparedStatement pt = con.prepareStatement(q);
            ResultSet rs = pt.executeQuery(q);
            while (rs.next())
            {
                String c = rs.getString("supid");
                jComboBox5.addItem(c);
            }
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
     private void fillcombo3()
    {
        try{
            con = DBconnect.getConnection();
            String q = "select distinct type from assests";
            PreparedStatement pt = con.prepareStatement(q);
            ResultSet rs = pt.executeQuery(q);
            while (rs.next())
            {
                String c = rs.getString("type");
                jComboBox3.addItem(c);
            }
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
     
     private void fillcombo4()
    {
        try{
            con = DBconnect.getConnection();
            String q = "select distinct remarks from maintainance";
            PreparedStatement pt = con.prepareStatement(q);
            ResultSet rs = pt.executeQuery(q);
            while (rs.next())
            {
                String c = rs.getString("remarks");
                jComboBox4.addItem(c);
            }
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jTabbedPane6 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        jTextField3 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        equip_add_btn = new javax.swing.JButton();
        equip_reset_btn = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        equip_update_btn1 = new javax.swing.JButton();
        equip_delete_btn1 = new javax.swing.JButton();
        equp_search3 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jTextField42 = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jTextField44 = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jTextField46 = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        jTextField45 = new javax.swing.JTextField();
        main_add_btn = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        don_update_btn1 = new javax.swing.JButton();
        jDateChooser7 = new com.toedter.calendar.JDateChooser();
        jDateChooser8 = new com.toedter.calendar.JDateChooser();
        equip_reset_btn2 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox<>();
        jTextField4 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jButton12 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();
        jButton15 = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jDateChooser4 = new com.toedter.calendar.JDateChooser();
        jButton5 = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox();
        jScrollPane11 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jSpinner2 = new javax.swing.JSpinner();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jSeparator2 = new javax.swing.JSeparator();
        jTextField15 = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        general_rep_btn = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        main_rep_btn = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        specific_rep_no = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jDateChooser5 = new com.toedter.calendar.JDateChooser();
        jDateChooser6 = new com.toedter.calendar.JDateChooser();
        jPanel11 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox();
        type_rep_btn = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox();
        type_rep_btn1 = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

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
        jScrollPane7.setViewportView(jTable2);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane8.setViewportView(jTable3);

        setPreferredSize(new java.awt.Dimension(964, 592));

        jTabbedPane6.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane6.setFocusable(false);
        jTabbedPane6.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jTabbedPane6.setPreferredSize(new java.awt.Dimension(964, 592));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 0, 204))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel1.setText("Serial #");

        jLabel2.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel2.setText("Name");

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jComboBox1.setFont(new java.awt.Font("Segoe UI Symbol", 1, 12)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(51, 51, 51));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Type", "Diagnostics", "Laboratory Equipments", "General Equipments" }));
        jComboBox1.setToolTipText("Medical Monitors - ex: ECG, blood pressure checkers Diagnostics - ex: ultrasound scanners, x-ray machines Life support - ex: nebulizers Treatment Equipments - ex: Infusion pumps ");
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel11.setText("Description");

        jLabel9.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel9.setText("Life Expectancy");

        jLabel3.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel3.setText("Type");

        jLabel5.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel5.setText("Manufacturer");

        jTextField5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel10.setText("Warranty Exp Date");

        jLabel6.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel6.setText("Purchased Date");

        jLabel4.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel4.setText("Supplier");

        jLabel7.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel7.setText("Price");

        jTextField9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel8.setText("Qty");

        jTextField10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        equip_add_btn.setBackground(new java.awt.Color(0, 31, 82));
        equip_add_btn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        equip_add_btn.setForeground(new java.awt.Color(255, 255, 255));
        equip_add_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Add_32px.png"))); // NOI18N
        equip_add_btn.setText("Add");
        equip_add_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                equip_add_btnActionPerformed(evt);
            }
        });

        equip_reset_btn.setBackground(new java.awt.Color(0, 31, 82));
        equip_reset_btn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        equip_reset_btn.setForeground(new java.awt.Color(255, 255, 255));
        equip_reset_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Reset_32px.png"))); // NOI18N
        equip_reset_btn.setText("Reset");
        equip_reset_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                equip_reset_btnActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane3.setViewportView(jTextArea1);

        jLabel12.setText("invalid input!");

        jLabel14.setText("Use only numerical values! ");

        jLabel17.setText("Use only numerical values! ");

        jLabel24.setText("Use only numerical values! ");

        equip_update_btn1.setBackground(new java.awt.Color(0, 31, 82));
        equip_update_btn1.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        equip_update_btn1.setForeground(new java.awt.Color(255, 255, 255));
        equip_update_btn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Update_26px.png"))); // NOI18N
        equip_update_btn1.setText("Update");
        equip_update_btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                equip_update_btn1ActionPerformed(evt);
            }
        });

        equip_delete_btn1.setBackground(new java.awt.Color(0, 31, 82));
        equip_delete_btn1.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        equip_delete_btn1.setForeground(new java.awt.Color(255, 255, 255));
        equip_delete_btn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Delete_32px.png"))); // NOI18N
        equip_delete_btn1.setText("Delete");
        equip_delete_btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                equip_delete_btn1ActionPerformed(evt);
            }
        });

        equp_search3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Search_26px_1.png"))); // NOI18N
        equp_search3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                equp_search3ActionPerformed(evt);
            }
        });

        jComboBox2.setFont(new java.awt.Font("Segoe UI Symbol", 1, 12)); // NOI18N

        jButton3.setBackground(new java.awt.Color(0, 31, 82));
        jButton3.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("demo");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField1)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addGap(26, 26, 26)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(equp_search3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButton3)
                                .addGap(35, 35, 35))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel11))))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jLabel14))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(equip_add_btn)
                        .addGap(18, 18, 18)
                        .addComponent(equip_reset_btn)
                        .addGap(18, 18, 18)
                        .addComponent(equip_update_btn1)
                        .addGap(16, 16, 16)
                        .addComponent(equip_delete_btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel10))
                                .addGap(28, 28, 28)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField5)
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addComponent(jLabel17)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel24))
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addGap(18, 18, 18)
                                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel8)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(74, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(4, 4, 4)
                                .addComponent(jLabel12))
                            .addComponent(equp_search3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6))
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8)
                                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel24))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(equip_add_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(equip_reset_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(equip_update_btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(equip_delete_btn1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setBackground(new java.awt.Color(0, 31, 82));
        jButton1.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Refresh ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 905, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jTabbedPane6.addTab("Equipment Inventory", jPanel2);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 0, 204))); // NOI18N

        jTextField42.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField42KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField42KeyReleased(evt);
            }
        });

        jLabel48.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel48.setText("Serial No:");

        jLabel49.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel49.setText("Issue:");

        jLabel56.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel56.setText("Assigned To: ");

        jLabel55.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel55.setText("Assigned Date: ");

        jLabel54.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel54.setText("Completed Date: ");

        jLabel52.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel52.setText("Product Cost: ");

        jLabel50.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel50.setText("Service Cost: ");

        main_add_btn.setBackground(new java.awt.Color(0, 31, 82));
        main_add_btn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        main_add_btn.setForeground(new java.awt.Color(255, 255, 255));
        main_add_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Add_32px.png"))); // NOI18N
        main_add_btn.setText("Add");
        main_add_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                main_add_btnActionPerformed(evt);
            }
        });

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane6.setViewportView(jTextArea2);

        don_update_btn1.setBackground(new java.awt.Color(0, 31, 82));
        don_update_btn1.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        don_update_btn1.setForeground(new java.awt.Color(255, 255, 255));
        don_update_btn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Update_26px.png"))); // NOI18N
        don_update_btn1.setText("Update");
        don_update_btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                don_update_btn1ActionPerformed(evt);
            }
        });

        equip_reset_btn2.setBackground(new java.awt.Color(0, 31, 82));
        equip_reset_btn2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        equip_reset_btn2.setForeground(new java.awt.Color(255, 255, 255));
        equip_reset_btn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Reset_32px.png"))); // NOI18N
        equip_reset_btn2.setText("Reset");
        equip_reset_btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                equip_reset_btn2ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel13.setText("Status:");

        jComboBox6.setBackground(new java.awt.Color(0, 31, 82));
        jComboBox6.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jComboBox6.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "select status", "processing", "completed", "removed" }));
        jComboBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox6ActionPerformed(evt);
            }
        });

        jTextField4.setBorder(null);

        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton12.setBackground(new java.awt.Color(0, 31, 82));
        jButton12.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton12.setForeground(new java.awt.Color(255, 255, 255));
        jButton12.setText("demo");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel48)
                    .addComponent(jLabel49))
                .addGap(19, 19, 19)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                    .addComponent(jTextField42))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel52)
                            .addComponent(jLabel56)
                            .addComponent(jLabel55)
                            .addComponent(jLabel54)
                            .addComponent(jLabel13))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextField44)
                            .addComponent(jDateChooser7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDateChooser8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jTextField46, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel50)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField45, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jComboBox6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(main_add_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(equip_reset_btn2)
                        .addGap(18, 18, 18)
                        .addComponent(don_update_btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)))
                .addGap(45, 45, 45))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator1)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addComponent(jButton12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel48)
                            .addComponent(jTextField42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel56)
                            .addComponent(jTextField44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel49)
                            .addComponent(jLabel55)
                            .addComponent(jDateChooser7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel54)
                            .addComponent(jDateChooser8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel52)
                            .addComponent(jLabel50)
                            .addComponent(jTextField45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(don_update_btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(equip_reset_btn2)
                            .addComponent(main_add_btn)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton12)
                        .addContainerGap())))
        );

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Task No", "Equipment No", "Description", "Assigned To", "Assigned Date", "Completed Date", "Product Cost", "Service Cost"
            }
        ));
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable4);

        jButton2.setBackground(new java.awt.Color(0, 31, 82));
        jButton2.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Refresh");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane4)))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(84, Short.MAX_VALUE))
        );

        jTabbedPane6.addTab("Maintenance", jPanel4);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Supplier Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Symbol", 1, 14))); // NOI18N

        jLabel15.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel15.setText("Supplier ID:");

        jLabel16.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel16.setText("Name:");

        jLabel18.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel18.setText("Address:");

        jLabel19.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel19.setText("Tel No:");

        jLabel20.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel20.setText("Email:");

        jLabel21.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel21.setText("Date of Join:");

        jLabel22.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel22.setText("Remarks:");

        jTextField12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField12KeyTyped(evt);
            }
        });

        jTextArea4.setColumns(20);
        jTextArea4.setRows(5);
        jScrollPane5.setViewportView(jTextArea4);

        jButton15.setBackground(new java.awt.Color(0, 31, 82));
        jButton15.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton15.setForeground(new java.awt.Color(255, 255, 255));
        jButton15.setText("demo");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel25.setText("Name:");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap(352, Short.MAX_VALUE)
                        .addComponent(jLabel16))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addContainerGap(45, Short.MAX_VALUE)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel25))
                                .addGap(34, 34, 34))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField8)
                            .addComponent(jTextField7)
                            .addComponent(jTextField11)
                            .addComponent(jTextField12)
                            .addComponent(jTextField13)
                            .addComponent(jDateChooser3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(40, 40, 40))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel21))
                    .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addGap(18, 18, 18)
                        .addComponent(jButton15)))
                .addGap(39, 39, 39))
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Make Order", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Symbol", 1, 14))); // NOI18N

        jLabel23.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel23.setText("Serial No:");

        jLabel28.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel28.setText("Quantity:");

        jLabel29.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel29.setText("Date to be supplied:");

        jTextField14.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField14KeyReleased(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(0, 31, 82));
        jButton5.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Send Email");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel27.setText("SupplierID:");

        jComboBox5.setFont(new java.awt.Font("Segoe UI Symbol", 1, 12)); // NOI18N

        jList1.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        jScrollPane11.setViewportView(jList1);

        jButton13.setBackground(new java.awt.Color(0, 31, 82));
        jButton13.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton13.setForeground(new java.awt.Color(255, 255, 255));
        jButton13.setText("Add");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setBackground(new java.awt.Color(0, 31, 82));
        jButton14.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton14.setForeground(new java.awt.Color(255, 255, 255));
        jButton14.setText("remove");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27)
                            .addComponent(jLabel29))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jDateChooser4, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                            .addComponent(jComboBox5, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSpinner2, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton14))))
                .addContainerGap(23, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(101, 101, 101))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel27)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29)
                    .addComponent(jDateChooser4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28)
                            .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jButton13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton14)))
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton6.setBackground(new java.awt.Color(0, 31, 82));
        jButton6.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Add");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
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

        jButton8.setBackground(new java.awt.Color(0, 31, 82));
        jButton8.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("Update");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(0, 31, 82));
        jButton9.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("Delete");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable5MouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(jTable5);

        jTextField15.setBorder(null);

        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setBackground(new java.awt.Color(0, 31, 82));
        jButton11.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setText("refresh");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane10.setViewportView(jTable6);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(606, 606, 606))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton6)
                            .addComponent(jButton7)
                            .addComponent(jButton8)
                            .addComponent(jButton9))
                        .addGap(3, 3, 3)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton11)
                            .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(84, Short.MAX_VALUE))
        );

        jTabbedPane6.addTab("Supplier Handling", jPanel3);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Equipments Report", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Symbol", 1, 14))); // NOI18N

        general_rep_btn.setBackground(new java.awt.Color(0, 31, 82));
        general_rep_btn.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        general_rep_btn.setForeground(new java.awt.Color(255, 255, 255));
        general_rep_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Report_Card_32px_2.png"))); // NOI18N
        general_rep_btn.setText("GENERATE REPORT");
        general_rep_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                general_rep_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(general_rep_btn)
                .addGap(37, 37, 37))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(general_rep_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Suppliers Report", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Symbol", 1, 14))); // NOI18N

        main_rep_btn.setBackground(new java.awt.Color(0, 31, 82));
        main_rep_btn.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        main_rep_btn.setForeground(new java.awt.Color(255, 255, 255));
        main_rep_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Report_Card_32px_2.png"))); // NOI18N
        main_rep_btn.setText("GENERATE REPORT");
        main_rep_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                main_rep_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(91, Short.MAX_VALUE)
                .addComponent(main_rep_btn)
                .addGap(81, 81, 81))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(main_rep_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Maintenance Report", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Symbol", 1, 14))); // NOI18N

        specific_rep_no.setBackground(new java.awt.Color(0, 31, 82));
        specific_rep_no.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        specific_rep_no.setForeground(new java.awt.Color(255, 255, 255));
        specific_rep_no.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Report_Card_32px_2.png"))); // NOI18N
        specific_rep_no.setText("GENERATE REPORT");
        specific_rep_no.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                specific_rep_noActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jLabel30.setText("From");

        jLabel31.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jLabel31.setText("To");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel31)
                    .addComponent(jLabel30))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDateChooser5, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                    .addComponent(jDateChooser6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(58, 58, 58))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(85, Short.MAX_VALUE)
                .addComponent(specific_rep_no, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel30)
                    .addComponent(jDateChooser5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel31)
                    .addComponent(jDateChooser6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(specific_rep_no, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Equipments Report", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Symbol", 1, 14))); // NOI18N

        jLabel26.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jLabel26.setText("Type:");

        jComboBox3.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));

        type_rep_btn.setBackground(new java.awt.Color(0, 31, 82));
        type_rep_btn.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        type_rep_btn.setForeground(new java.awt.Color(255, 255, 255));
        type_rep_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Report_Card_32px_2.png"))); // NOI18N
        type_rep_btn.setText("GENERATE REPORT");
        type_rep_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                type_rep_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(type_rep_btn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addComponent(jLabel26)
                .addGap(18, 18, 18)
                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(type_rep_btn)
                .addGap(199, 199, 199))
        );

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Maintenance Report", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Symbol", 1, 14))); // NOI18N

        jLabel32.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jLabel32.setText("Status:");

        jComboBox4.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });

        type_rep_btn1.setBackground(new java.awt.Color(0, 31, 82));
        type_rep_btn1.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        type_rep_btn1.setForeground(new java.awt.Color(255, 255, 255));
        type_rep_btn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Report_Card_32px_2.png"))); // NOI18N
        type_rep_btn1.setText("GENERATE REPORT");
        type_rep_btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                type_rep_btn1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel32)
                .addGap(18, 18, 18)
                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(type_rep_btn1)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(type_rep_btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(79, 79, 79)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(172, 172, 172)
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(138, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(89, Short.MAX_VALUE))
        );

        jTabbedPane6.addTab("Reports", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 593, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
        // TODO add your handling code here:

        try
        {
            int r = jTable4.getSelectedRow();
            
            //String click = (jTable4.getModel().getValueAt(r, 0).toString());
            //String sql = "select * from maintainance where serialNo = '"+click+"'";
            //pst = con.prepareStatement(sql);
            //rs=pst.executeQuery();

            String serialNo = jTable4.getValueAt(r, 1).toString();
            String desc = jTable4.getValueAt(r, 2).toString();
            String assignedTo = jTable4.getValueAt(r, 3).toString();
            String assignedDate = jTable4.getValueAt(r, 4).toString();
            String  compDate = jTable4.getValueAt(r, 5).toString();
            String proCost = jTable4.getValueAt(r, 6).toString();
            String serCost = jTable4.getValueAt(r, 7).toString();
            String remarks = jTable4.getValueAt(r, 8).toString();
            
            //SimpleDateFormat date = new SimpleDateFormat ("yyyy-MM-dd");
           //Date add4 = rs.getDate("assignedDate");
            //jDateChooser7.setDate(add4);
           // Date add5 = rs.getDate("compDate");
           // jDateChooser8.setDate(add5);

            jTextField42.setText(serialNo);
            jTextArea2.setText(desc);
            jTextField44.setText(assignedTo);
            try{
                SimpleDateFormat date = new SimpleDateFormat ("yyyy-MM-dd");
                Date datvalue = date.parse(assignedDate);
                jDateChooser7.setDate(datvalue);
                Date datvalue2 = date.parse(compDate);
                jDateChooser8.setDate(datvalue2);
                
            }
            catch(Exception e){
            }
            jTextField46.setText(proCost);
            jTextField45.setText(serCost);
            jComboBox6.setSelectedItem(remarks);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_jTable4MouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:

        try{
            int row = jTable1.getSelectedRow();
            String click = (jTable1.getModel().getValueAt(row, 0).toString());
            String sql = "select * from assests where serialNo = '"+click+"'";
            pst = con.prepareStatement(sql);
            rs=pst.executeQuery();

            if (rs.next())
            {
                String add1 = rs.getString("serialNo");
                jTextField1.setText(add1);
                String add2 = rs.getString("type");
                jComboBox1.setSelectedItem(add2);
                String add3 = rs.getString("name");
                jTextField2.setText(add3);
                String add4 = rs.getString("LifeExp");
                jTextField3.setText(add4);
                String add5 = rs.getString("description");
                jTextArea1.setText(add5);
                String add6 = rs.getString("manufacturer");
                jTextField5.setText(add6);
                String add7 = rs.getString("supplier");
                jComboBox2.setSelectedItem(add7);

                SimpleDateFormat date = new SimpleDateFormat ("yyyy-MM-dd");
                Date add8 = rs.getDate("purchDate");
                jDateChooser1.setDate(add8);
                Date add9 = rs.getDate("warExpDate");
                jDateChooser2.setDate(add9);

                String add10 = rs.getString("price");
                jTextField9.setText(add10);
                String add11 = rs.getString("qty");
                jTextField10.setText(add11);

            }

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void equp_search3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_equp_search3ActionPerformed

        String serialNo = jTextField1.getText();
        String sql = "select * from assests where serialNo LIKE '"+serialNo+"'";

        try{
            con = DBconnect.getConnection();
            pst = con.prepareStatement(sql);

            rs = pst.executeQuery();

            jTable1.setModel(DbUtils.resultSetToTableModel(rs));

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Result not found");
        }
    }//GEN-LAST:event_equp_search3ActionPerformed

    private void equip_delete_btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_equip_delete_btn1ActionPerformed
        // TODO add your handling code here:
        if(jTextField1.getText().isEmpty()){
            JOptionPane.showMessageDialog(this,"enter the serial number to delete","Error",JOptionPane.ERROR_MESSAGE);
        }
        else{
            String serialNo = jTextField1.getText();
            Assets a = new Assets(serialNo);

            a.deleteEquip();
            try{
                con = DBconnect.getConnection();
                String sql = "Select * from assests";
                PreparedStatement pt = (PreparedStatement) con.prepareStatement(sql);
                ResultSet rs = pt.executeQuery();

                jTable1.setVisible(true);
                jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(this, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_equip_delete_btn1ActionPerformed

    private void equip_update_btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_equip_update_btn1ActionPerformed
        // TODO add your handling code here:
      //  if(isInteger(jTextField9.getText())&&isInteger(jTextField10.getText())){
        String serialNo= jTextField1.getText();

        Assets as=new Assets(serialNo);
        Connection con=null;

        String type = (String)jComboBox1.getSelectedItem();
        String name = jTextField2.getText();
        int lifeExp = Integer.parseInt(jTextField3.getText());
        String desc = jTextArea1.getText();
        String manufact = jTextField5.getText();
        String supplier = jComboBox2.getSelectedItem().toString();
        java.util.Date purdate = jDateChooser1.getDate();
        java.util.Date warexpdate = jDateChooser2.getDate();
        java.sql.Date pd = new java.sql.Date(purdate.getTime()); // convert java.util.date to java.sql.date
        java.sql.Date wd = new java.sql.Date(warexpdate.getTime());
        Double price = Double.parseDouble(jTextField9.getText());
        int qty = Integer.parseInt(jTextField10.getText());

        con = DBconnect.getConnection();
        SimpleDateFormat dn = new SimpleDateFormat("yyyy-MM-dd");
        
        try{
            String prd = dn.format(jDateChooser1.getDate());
            String wed = dn.format(jDateChooser2.getDate());
            Date ad = dn.parse(prd);
            Date cd = dn.parse(wed);
            if (cd.before(ad))
            {
                JOptionPane.showMessageDialog(null, "Error in Completed date!");
            }
            else if (ad.equals(cd))
            {
                JOptionPane.showMessageDialog(null, "Error in dates!");
            }
            else
            {
                as.updateEuip(serialNo, type, name, lifeExp, desc, manufact, supplier, pd, wd, price, qty);
                try{
                    String sql = "Select * from assests where serialNo= '"+serialNo+"'";
                    PreparedStatement pt = (PreparedStatement) con.prepareStatement(sql);
                    ResultSet rs = pt.executeQuery();
                    JOptionPane.showMessageDialog(null, "updated ");
                    jTable1.setVisible(true);
                    jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                    
                    jTextField1.setText(" ");
        jComboBox1.setSelectedItem("Select type");
        jTextField2.setText(" ");
        jTextField3.setText(" ");
        jTextArea1.setText(" ");
        jTextField5.setText(" ");
        jComboBox2.setSelectedItem(null);
        jDateChooser1.setCalendar(null);
        jDateChooser2.setCalendar(null);
        jTextField9.setText(" ");
        jTextField10.setText(" ");
        jLabel12.setVisible(false);
        jLabel14.setVisible(false);
        jLabel17.setVisible(false);
        jLabel24.setVisible(false);

                 }

                 catch (Exception e)
                 {
                      JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                  }
                
            }
            
         }
            
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }      
        
        

        
        
      /*  }
        else
        {
            JOptionPane.showMessageDialog(null, "invalid input for price or quantity!");
        }*/
    }//GEN-LAST:event_equip_update_btn1ActionPerformed

    private void equip_reset_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_equip_reset_btnActionPerformed
        // TODO add your handling code here:

        jTextField1.setText(" ");
        jComboBox1.setSelectedItem("Select type");
        jTextField2.setText(" ");
        jTextField3.setText(" ");
        jTextArea1.setText(" ");
        jTextField5.setText(" ");
        jComboBox2.setSelectedItem(null);
        jDateChooser1.setCalendar(null);
        jDateChooser2.setCalendar(null);
        jTextField9.setText(" ");
        jTextField10.setText(" ");
        jLabel12.setVisible(false);
        jLabel14.setVisible(false);
        jLabel17.setVisible(false);
        jLabel24.setVisible(false);
    }//GEN-LAST:event_equip_reset_btnActionPerformed

    private void equip_add_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_equip_add_btnActionPerformed
        // TODO add your handling code here:
        if(isInteger(jTextField9.getText())&&isInteger(jTextField10.getText())){
            String serialNo = jTextField1.getText();
            String type = (String)jComboBox1.getSelectedItem();
            String name = jTextField2.getText();
            int lifeExp = Integer.parseInt(jTextField3.getText());
            String desc = jTextArea1.getText();
            String manufact = jTextField5.getText();
            String supplier = jComboBox2.getSelectedItem().toString();
            SimpleDateFormat dn = new SimpleDateFormat("yyyy-MM-dd");
            String purdate = dn.format(jDateChooser1.getDate());
            String warexpDate = dn.format(jDateChooser2.getDate());
            Double price = Double.parseDouble(jTextField9.getText());
            int qty = Integer.parseInt(jTextField10.getText());
            try{
            Date ad = dn.parse(purdate);
            Date cd = dn.parse(warexpDate);
            if (cd.before(ad))
            {
                JOptionPane.showMessageDialog(null, "Error in Completed date!");
            }
            else if (ad.equals(cd))
            {
                JOptionPane.showMessageDialog(null, "Error in dates!");
            }
            else
            {
                Assets a = new Assets (serialNo, type, name, lifeExp, desc,manufact, supplier , purdate , warexpDate ,  price , qty);
                tableLoad();
                
                jTextField1.setText(" ");
                jComboBox1.setSelectedItem("Select type");
                jTextField2.setText(" ");
                jTextField3.setText(" ");
                jTextArea1.setText(" ");
                jTextField5.setText(" ");
                jComboBox2.setSelectedItem(null);
                jDateChooser1.setCalendar(null);
                jDateChooser2.setCalendar(null);
                jTextField9.setText(" ");
                jTextField10.setText(" ");
                jLabel12.setVisible(false);
                jLabel14.setVisible(false);
                jLabel17.setVisible(false);
                jLabel24.setVisible(false);
            }
            
            }
            
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            }           
                     
        }
        else{
            JOptionPane.showMessageDialog(null, "invalid input for price or quantity!");
        }
    }//GEN-LAST:event_equip_add_btnActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        tableLoad();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        mainLoad();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        
        String serialNo = jTextField4.getText();
        String sql = "select * from maintainance where serialNo LIKE '"+serialNo+"'";

        try{
            con = DBconnect.getConnection();
            pst = con.prepareStatement(sql);

            rs = pst.executeQuery();

            jTable4.setModel(DbUtils.resultSetToTableModel(rs));

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Result not found");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        
        if(isInteger(jTextField12.getText())){
            String supid = jTextField7.getText();
            String name = jTextField8.getText();
            String address = jTextField11.getText();
            int telno = Integer.parseInt(jTextField12.getText());
            String email = jTextField13.getText();
            SimpleDateFormat dn = new SimpleDateFormat("yyyy-MM-dd");
            String doj = dn.format(jDateChooser3.getDate());
            String remarks = jTextArea4.getText();

            int a=0, b=0;
            for (int k=0; k<email.length(); k++)
            {
                if (email.charAt(k) == '@')
                {
                    a=k;
                }
                else if (email.charAt(k) == '.')
                {
                    b = k;
                }
            }
             if (b>a)
             {
                eqsup eq = new eqsup (supid, name, address, telno, email, doj, remarks);
                supLoad();
                fillcombo();
                fillcombo2();
                
                jTextField7.setText(" ");
                jTextField8.setText(" ");
                jTextField11.setText(" ");
                jTextField12.setText(" ");
                jDateChooser3.setCalendar(null);
                jTextField13.setText(" ");
                jTextArea4.setText(" ");
             }
             
             else {
                 JOptionPane.showMessageDialog(null, "invalid email address!");
             }
           
            }
            else{
                JOptionPane.showMessageDialog(null, "invalid input for telephone number!");
            }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        if(isInteger(jTextField12.getText())){
        String supid = jTextField7.getText();

        eqsup eq = new eqsup(supid);

            
            String name = jTextField8.getText();
            String address = jTextField11.getText();
            int telno = Integer.parseInt(jTextField12.getText());
            String email = jTextField13.getText();
            java.util.Date d = jDateChooser3.getDate();
            java.sql.Date sd = new java.sql.Date(d.getTime());
            String remarks = jTextArea4.getText();

        con = DBconnect.getConnection();
        eq.updateSup(supid, name, address, telno, email,sd, remarks);
        try{
            String sql = "select * from eqsupplier where supid = '"+supid+"' ";

            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            jTable5.setModel(DbUtils.resultSetToTableModel(rs));
            
            JOptionPane.showMessageDialog(null, "updated!");
            jTextField7.setText(" ");
            jTextField8.setText(" ");
            jTextField11.setText(" ");
            jTextField12.setText(" ");
            jDateChooser3.setCalendar(null);
            jTextField13.setText(" ");
            jTextArea4.setText(" ");

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "invalid email address!");
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        jTextField7.setText(" ");
        jTextField8.setText(" ");
        jTextField11.setText(" ");
        jTextField12.setText(" ");
        jDateChooser3.setCalendar(null);
        jTextField13.setText(" ");
        jTextArea4.setText(" ");
        
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        
         if(jTextField7.getText().isEmpty()){
            JOptionPane.showMessageDialog(this,"enter the supplier id to delete","Error",JOptionPane.ERROR_MESSAGE);
        }
        else{
            String supid = jTextField7.getText();
            eqsup eq = new eqsup (supid);

           eq.deleteSup();

            try{
                con = DBconnect.getConnection();
                String sql = "Select * from eqsupplier";
                PreparedStatement pt = (PreparedStatement) con.prepareStatement(sql);
                ResultSet rs = pt.executeQuery();

                jTable5.setVisible(true);
                jTable5.setModel(DbUtils.resultSetToTableModel(rs));
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(this, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        
        String name = jTextField15.getText();
       String sql = "select * from eqsupplier where supid='"+name+"' ";

        try{
            con = DBconnect.getConnection();
            pst = con.prepareStatement(sql);

            rs = pst.executeQuery();

            jTable5.setModel(DbUtils.resultSetToTableModel(rs));

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Result not found");
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jTable5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable5MouseClicked
        // TODO add your handling code here:
        try{
            int row = jTable5.getSelectedRow();
            String click = (jTable5.getModel().getValueAt(row, 0).toString());
            String sql = "select * from eqsupplier where supid = '"+click+"'";
            pst = con.prepareStatement(sql);
            rs=pst.executeQuery();

            if (rs.next())
            {
                String add1 = rs.getString("supid");
                jTextField7.setText(add1);
                String add2 = rs.getString("name");
                jTextField8.setText(add2);                       
                String add3 = rs.getString("address");
                jTextField11.setText(add3);
                String add4 = rs.getString("telno");
                jTextField12.setText(add4);
                String add5 = rs.getString("email");
                jTextField13.setText(add5);
                SimpleDateFormat date = new SimpleDateFormat ("yyyy-MM-dd");
                Date add8 = rs.getDate("doj");
                jDateChooser3.setDate(add8);
                String add7 = rs.getString("remarks");
                jTextArea4.setText(add7);
             
              
            }

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);

        }
        
    }//GEN-LAST:event_jTable5MouseClicked

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        supLoad();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jTextField14KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField14KeyReleased
        // TODO add your handling code here:
        switch(evt.getKeyCode())
        {
            case KeyEvent.VK_BACK_SPACE:
                break;
            case KeyEvent.VK_ENTER:
                jTextField42.setText(jTextField42.getText());
                break;
            default:
                EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                String txt = jTextField14.getText();
                autoComplete2(txt);
            }
        });  
        }
               
    }//GEN-LAST:event_jTextField14KeyReleased

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        System.out.println("dsdjs");
      
            System.out.println("dsdgfghfghjs");
            String supid = jComboBox5.getSelectedItem().toString();
            String serialNo = jTextField14.getText();
            System.out.println("fefsdef");
            //int qty = Integer.parseInt(jTextField16.getText());     
            System.out.println("fsdsdsdsdsdsdsdsdsdef");
            SimpleDateFormat dn = new SimpleDateFormat("yyyy-MM-dd");
            String datesup = dn.format(jDateChooser4.getDate());
            String email = jTextField13.getText();
            System.out.println("dsdjs");
            try{
                con = DBconnect.getConnection();
                PreparedStatement pt = null;
                
                String list = (String) jList1.getSelectedValue();
                ListModel model = jList1.getModel();
                
                for (int i=0; i<model.getSize(); i++)
                {
                    String dr = (String) model.getElementAt(i);
                    System.out.println("redda");
                    String val[] = dr.split("     ");
                    String sn = val[0];
                    int qty = Integer.parseInt(val[1]);
                    System.out.println("teek");
                    String query="insert into eqorder values (?,?,?,?)";
                    System.out.println("redehfusejdda");
                    pt= con.prepareStatement(query);
      
                    pt.setString(1, supid);
                    pt.setString(2, sn);
                    pt.setInt(3, qty);
                    pt.setString(4, datesup);
                    
        
                    pt.executeUpdate();     //execution of the query
                    
                    
                }
       
                String host = "smtp.gmail.com";
                String user = "deegayuu@gmail.com";
                String pass = "deegayuu123";
                String to = null;
                
                String q2 = "Select * from eqsupplier where supid = '"+supid+"'";
                pt = con.prepareStatement(q2);
                rs = pt.executeQuery();
                
                while(rs.next()){
                    to = rs.getString("email");
                }
                    
                        
                        //String to = email;
                String from = "deegayuu@gmail.com";
                String subject = "New Order";
                String msg1 = null;
                        System.out.println("23323");
                        for (int i=0; i<model.getSize(); i++)
                        {
                            System.out.println("23323");
                            String dr = (String) model.getElementAt(i);
                            if(msg1==null){
                               msg1 = dr + "\n";
                            }
                            else{
                                msg1 = msg1 + "\n" + dr;
                            }
                        }
                        
                        String msg = "Dear Supplier \n This is the new order we need before '"+datesup+"'. \n \n  " + msg1;
                        boolean sessionDebug = false;
                
                        Properties props = System.getProperties();
                        
                        
                        System.out.println("23323");
                        props.put("mail.smtp.starttls.enable", "true");
                        props.put("mail.smtp.host", host);
                        props.put("mail.smtp.port", "587");
                        props.put("mail.smtp.auth", "true");
                        props.put("mail.smtp.starttls.required", "true");
                        
                         System.out.println("23323");
                        java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider() );
                        javax.mail.Session mailSession = javax.mail.Session.getDefaultInstance(props, null);
                        mailSession.setDebug(sessionDebug);
                        Message msgg = new MimeMessage(mailSession);
                        msgg.setFrom(new InternetAddress(from));
                        InternetAddress[] address = {new InternetAddress(to)};
                        msgg.setRecipients(Message.RecipientType.TO,address);
                        msgg.setSubject(subject);
                        msgg.setSentDate(new java.util.Date());
                        msgg.setText(msg);
                
                        System.out.println("23323");
                        Transport transport = mailSession.getTransport("smtp");
                        transport.connect(host, user, pass);
                        transport.sendMessage(msgg, msgg.getAllRecipients());
                        transport.close();
                        
                        JOptionPane.showMessageDialog(null, "Order sent!");
                                  
                           
              }
              catch(Exception ex)
               {
                    JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                }
            
            
      
            
            
        
        
        
        
    }//GEN-LAST:event_jButton5ActionPerformed
                DefaultListModel dm = new DefaultListModel();
    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        ListModel model = jList1.getModel();
        boolean check = true;
        String d = jTextField14.getText();
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
        
        if (check)
        {
            String list = jTextField14.getText()+"     "+jSpinner2.getValue();
            dm.addElement(list);
            jList1.setModel(dm);
            jTextField14.setText(" ");
            jSpinner2.setValue(0);
        }
        else
        {
            getToolkit().beep();
        }
        
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        
        dm.removeElement(jList1.getSelectedValue());
        jList1.setModel(dm);
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jTextField12KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField12KeyTyped
        // TODO add your handling code here:
        
        /*char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c== KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || jTextField12.getText().length()>9 ))
        {
            getToolkit().beep();
            evt.consume();
        }*/
        
        if (jTextField12.getText().length()> 9)
        {
            evt.consume();
        }
        
    }//GEN-LAST:event_jTextField12KeyTyped

    private void jComboBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox6ActionPerformed

    private void equip_reset_btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_equip_reset_btn2ActionPerformed
        // TODO add your handling code here:

        jTextField42.setText(" ");
        jTextArea2.setText(" ");
        jTextField44.setText(" ");
        jDateChooser7.setCalendar(null);
        jDateChooser8.setCalendar(null);
        jTextField46.setText(" ");
        jTextField45.setText(" ");
        jComboBox6.setSelectedItem("select status");

        /* if (!jTextField42.getText().isEmpty())
        {
            String serialNo = jTextField42.getText();

            String sql = "select * from maintainance where serialNo LIKE '"+serialNo+"' ";

            try{
                con = DBconnect.getConnection();
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                jTable4.setVisible(true);
                jTable4.setModel(DbUtils.resultSetToTableModel(rs));
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(this, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            }
        }*/
    }//GEN-LAST:event_equip_reset_btn2ActionPerformed

    private void don_update_btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_don_update_btn1ActionPerformed

        // TODO add your handling code here:
        //if(isInteger(jTextField46.getText())&&isInteger(jTextField45.getText())){
            String serialNo = jTextField42.getText();

            maintenance m = new maintenance(serialNo);

            // Connection con = null;

            String desc = jTextArea2.getText();
            String assignedTo = jTextField44.getText();
            java.util.Date d = jDateChooser7.getDate();
            java.sql.Date sd = new java.sql.Date(d.getTime());
            java.util.Date da = jDateChooser8.getDate();
            java.sql.Date sdd = new java.sql.Date(da.getTime());
            double proCost = Double.parseDouble(jTextField46.getText());
            double serCost = Double.parseDouble(jTextField45.getText());
            String status = jComboBox6.getSelectedItem().toString();

            con = DBconnect.getConnection();
            SimpleDateFormat dn = new SimpleDateFormat("yyyy-MM-dd");
            try{
                String prd = dn.format(jDateChooser7.getDate());
                String wed = dn.format(jDateChooser8.getDate());
                Date ad = dn.parse(prd);
                Date cd = dn.parse(wed);
                if (cd.before(ad))
                {
                    JOptionPane.showMessageDialog(null, "Error in Completed date!");
                }
                else if (ad.equals(cd))
                {
                    JOptionPane.showMessageDialog(null, "Error in dates!");
                }
                else
                {
                    m.updateMain(serialNo,desc, assignedTo,sd, sdd, proCost, serCost,status);

                    try{
                        System.out.println("dddddddd");
                        String sql = "select * from maintainance where serialNo = '"+serialNo+"' ";

                        pst = con.prepareStatement(sql);
                        rs = pst.executeQuery();

                        jTable4.setModel(DbUtils.resultSetToTableModel(rs));
                        System.out.println("dddddddd");

                        jTextField42.setText(" ");
                        jTextArea2.setText(" ");
                        jTextField44.setText(" ");
                        jDateChooser7.setCalendar(null);
                        jDateChooser8.setCalendar(null);
                        jTextField46.setText(" ");
                        jTextField45.setText(" ");
                        jComboBox6.setSelectedItem("select status");

                    }
                    catch(Exception e){
                        JOptionPane.showMessageDialog(this, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(this, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            }
            //  }
        /*        else
        {
            JOptionPane.showMessageDialog(null, "invalid input for product cost or service cost!");
        }*/
    }//GEN-LAST:event_don_update_btn1ActionPerformed

    private void main_add_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_main_add_btnActionPerformed
        // TODO add your handling code here:
        if(isInteger(jTextField46.getText())&&isInteger(jTextField45.getText())){
            String serialNo = jTextField42.getText();
            String desc = jTextArea2.getText();
            String assignedTo = jTextField44.getText();
            SimpleDateFormat dn = new SimpleDateFormat("yyyy-MM-dd");
            String assignedDate = dn.format(jDateChooser7.getDate());
            String compDate = dn.format(jDateChooser8.getDate());
            double proCost = Double.parseDouble(jTextField46.getText());
            double servCost = Double.parseDouble(jTextField45.getText());
            String status = jComboBox6.getSelectedItem().toString();
            
            if (status=="removed" )
            {
                JOptionPane.showMessageDialog(null, "Don't select removed status!");
            }
            
            

            /*try{
                Date ad = dn.parse(assignedDate);
                Date cd = dn.parse(compDate);
                if (cd.before(ad))
                {
                    JOptionPane.showMessageDialog(null, "Error in Completed date!");
                }
                else if (ad.equals(cd))
                {
                    JOptionPane.showMessageDialog(null, "Error in dates!");
                }
                else
                {*/
                    maintenance m = new maintenance(serialNo,desc,assignedTo,assignedDate,compDate,proCost,servCost,status);
                    mainLoad();

                  
                    jTextField42.setText(" ");
                    jTextArea2.setText(" ");
                    jTextField44.setText(" ");
                    jDateChooser7.setCalendar(null);
                    jDateChooser8.setCalendar(null);
                    jTextField46.setText(" ");
                    jTextField45.setText(" ");
                    jComboBox6.setSelectedItem("select status");
                    /*}

            }

            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            }    */

        }
        else{
            JOptionPane.showMessageDialog(null, "invalid input for product cost or service cost!");
        }
    }//GEN-LAST:event_main_add_btnActionPerformed

    private void jTextField42KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField42KeyReleased
        // TODO add your handling code here:

        switch(evt.getKeyCode())
        {
            case KeyEvent.VK_BACK_SPACE:
            break;
            case KeyEvent.VK_ENTER:
            jTextField42.setText(jTextField42.getText());
            break;
            default:
            EventQueue.invokeLater(new Runnable() {

                @Override
                public void run() {
                    String txt = jTextField42.getText();
                    autoComplete(txt);
                }
            });

        }

    }//GEN-LAST:event_jTextField42KeyReleased

    private void jTextField42KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField42KeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_jTextField42KeyPressed

    private void general_rep_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_general_rep_btnActionPerformed
        // TODO add your handling code here:
         try{
            Connection conn = DBconnect.getConnection();
            String report = "C:\\Users\\HP User\\Desktop\\itp\\ITPproject\\src\\eq1.jrxml";
            JasperReport jr = JasperCompileManager.compileReport(report);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
            JasperViewer.viewReport(jp,false);
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        } 
    }//GEN-LAST:event_general_rep_btnActionPerformed

    private void main_rep_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_main_rep_btnActionPerformed
        // TODO add your handling code here:
        try{
            Connection conn = DBconnect.getConnection();
            String report = "C:\\Users\\HP User\\Desktop\\itp\\ITPproject\\src\\sup.jrxml";
            JasperReport jr = JasperCompileManager.compileReport(report);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
            JasperViewer.viewReport(jp,false);
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        } 
        
    }//GEN-LAST:event_main_rep_btnActionPerformed

    private void type_rep_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_type_rep_btnActionPerformed
        // TODO add your handling code here:
        
        String speciality = jComboBox3.getSelectedItem().toString();
         HashMap param = new HashMap();
       param.put("eqType",speciality);
        
        try{
            Connection conn = DBconnect.getConnection();
            String report = "C:\\Users\\HP User\\Desktop\\itp\\ITPproject\\src\\eqType.jrxml";
            JasperReport jr = JasperCompileManager.compileReport(report);
            JasperPrint jp = JasperFillManager.fillReport(jr, param, conn);
            JasperViewer.viewReport(jp,false);
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_type_rep_btnActionPerformed

    private void specific_rep_noActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_specific_rep_noActionPerformed
        // TODO add your handling code here:
        java.util.Date sd = jDateChooser5.getDate();
        java.sql.Date stdate = new java.sql.Date(sd.getTime());
        String s = stdate.toString();
        java.util.Date ed = jDateChooser6.getDate();
        java.sql.Date endate = new java.sql.Date(ed.getTime());
        String en = endate.toString();
         HashMap param = new HashMap();
       param.put("sDate",s);
        param.put("eDate",en);
        try{
            Connection conn = DBconnect.getConnection();
            String report = "C:\\Users\\HP User\\Desktop\\itp\\ITPproject\\src\\maintain.jrxml";
            JasperReport jr = JasperCompileManager.compileReport(report);
            JasperPrint jp = JasperFillManager.fillReport(jr, param, conn);
            JasperViewer.viewReport(jp,false);
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_specific_rep_noActionPerformed

    private void type_rep_btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_type_rep_btn1ActionPerformed
        // TODO add your handling code here:
         String speciality = jComboBox4.getSelectedItem().toString();
         HashMap param = new HashMap();
       param.put("state",speciality);
        
        try{
            Connection conn = DBconnect.getConnection();
            String report = "C:\\Users\\HP User\\Desktop\\itp\\ITPproject\\src\\stateMain.jrxml";
            JasperReport jr = JasperCompileManager.compileReport(report);
            JasperPrint jp = JasperFillManager.fillReport(jr, param, conn);
            JasperViewer.viewReport(jp,false);
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_type_rep_btn1ActionPerformed

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
          jTextField1.setText("GUE24J64D9");
        jComboBox1.setSelectedItem("Laboratory Equipments");
        jTextField2.setText("Pipet");
        jTextField3.setText("12");
        jTextArea1.setText("in good condition");
        jTextField5.setText("ABC");
        jComboBox2.setSelectedItem("ABC");
       // jDateChooser1.setCalendar(null);
        //jDateChooser2.setCalendar(null);
        jTextField9.setText("2500");
        jTextField10.setText("10");
        //jLabel12.setVisible(false);
       // jLabel14.setVisible(false);
       // jLabel17.setVisible(false);
        //jLabel24.setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        
        jTextField42.setText("GUE24J64D9");
        jTextArea2.setText("blasted");
        jTextField44.setText("PQR");
        //jDateChooser7.setCalendar(null);
        //jDateChooser8.setCalendar(null);
        jTextField46.setText("2000");
        jTextField45.setText("0");
        jComboBox6.setSelectedItem("removed");

    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        
        jTextField7.setText("S010");
        jTextField8.setText("Himeshika");
        jTextField11.setText("Ja ela");
        jTextField12.setText("0712801032");
        jTextField13.setText("hishi1996@gmail.com");
        //jDateChooser3.setCalendar(null);
        
        jTextArea4.setText("excellent");
    }//GEN-LAST:event_jButton15ActionPerformed

 public boolean validserial(String a){
       boolean check;
            int count = 0;
            System.out.println("aaaaaaaaaa");

            for(int i =0 ; i<a.length(); i++){   //Validating equipNo
                if(!(Character.isDigit(a.charAt(i)) || Character.isLetter(a.charAt(i))))
                {
                    check = false;
                    count++;
                    jLabel12.setVisible(true);
                    return true;
                }

            }
            JOptionPane.showMessageDialog(this, "invalid serial No","Error",JOptionPane.ERROR_MESSAGE);
     return false;
 }
    

    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton don_update_btn1;
    private javax.swing.JButton equip_add_btn;
    private javax.swing.JButton equip_delete_btn1;
    private javax.swing.JButton equip_reset_btn;
    private javax.swing.JButton equip_reset_btn2;
    private javax.swing.JButton equip_update_btn1;
    private javax.swing.JButton equp_search3;
    private javax.swing.JButton general_rep_btn;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JComboBox jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private com.toedter.calendar.JDateChooser jDateChooser4;
    private com.toedter.calendar.JDateChooser jDateChooser5;
    private com.toedter.calendar.JDateChooser jDateChooser6;
    private com.toedter.calendar.JDateChooser jDateChooser7;
    private com.toedter.calendar.JDateChooser jDateChooser8;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JTabbedPane jTabbedPane6;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField42;
    private javax.swing.JTextField jTextField44;
    private javax.swing.JTextField jTextField45;
    private javax.swing.JTextField jTextField46;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JButton main_add_btn;
    private javax.swing.JButton main_rep_btn;
    private javax.swing.JButton specific_rep_no;
    private javax.swing.JButton type_rep_btn;
    private javax.swing.JButton type_rep_btn1;
    // End of variables declaration//GEN-END:variables
}
