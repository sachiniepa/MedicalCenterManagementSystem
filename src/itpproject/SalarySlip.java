/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itpproject;

import Classes.Salary;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Font;
import java.awt.Frame;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class SalarySlip extends javax.swing.JFrame {

    /**
     * Creates new form SalarySlip
     */
    public SalarySlip() {
        initComponents();
        
        
        
    }
    void loadDetails(String en, int mon, int year){
        Salary s = new Salary();
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        
        rs1 = s.getSalaryDetails(en);
        rs2 = s.getMonthlySalary(en, mon, year);
        DecimalFormat df = new DecimalFormat("###,###,###.##");
        String day = getMonth(mon)+ "   "+Integer.toString(year);
        month.setText(day);
        eID.setText(en);
        double basal=0, otpay, sbonus, gsal, epf, lded, totsal, cepf, cetf;
        try{
            if(rs1.next()){
              eName.setText(rs1.getString("name"));
              bSal.setText("Rs"+df.format(rs1.getDouble("basicSal")));
               basal = rs1.getDouble("basicSal");
               ePosition.setText(rs1.getString("position"));
            }
            if(rs2.next()){
                OtHrs.setText(rs2.getString("OThrs"));
                OtAmt.setText("Rs"+df.format(rs2.getDouble("OTpay")));
                otpay = rs2.getDouble("OTpay");
                sBonus.setText("Rs"+df.format(rs2.getDouble("seasonalBonus")));
                sbonus = rs2.getDouble("seasonalBonus");
                gsal = basal+ otpay + sbonus;
                gSal.setText("Rs"+df.format(gsal) );
                sEPF.setText("Rs"+df.format(rs2.getDouble("ETF")));
                epf = rs2.getDouble("ETF");
                ldeduct.setText("Rs"+df.format(rs2.getDouble("leaveDed")));
                lded = rs2.getDouble("leaveDed");
                totsal = gsal -(epf+lded);
                totPay.setText("Rs"+df.format(totsal));
                cepf = basal *(12.0/100.0);
                cetf = basal * (3.0/100.0);
                cEPF.setText("Rs"+df.format(cepf));
                cETF.setText("Rs"+df.format(cetf));
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    String getMonth(int mon){
        String month2 = "";
        switch (mon) {
            case 0:
                month2 = "January";
                break;
            case 1:
                month2 = "February";
                break;
            case 2:
                month2 = "March";
                break;
            case 3:
                month2 = "April";
                break;
            case 4:
                month2 = "May";
                break;
            case 5:
                month2 = "June";
                break;
            case 6:
                month2 = "July";
                break;
            case 7:
                month2 = "August";
                break;
            case 8:
                month2 = "September";
                break;
            case 9:
                month2 = "October";
                break;
            case 10:
                month2 = "November";
                break;
            case 11:
                month2 = "December";
                break;
            default:
                break;
        }
        
        return month2;
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        month = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        eID = new javax.swing.JTextField();
        ePosition = new javax.swing.JTextField();
        bSal = new javax.swing.JTextField();
        eName = new javax.swing.JTextField();
        gSal = new javax.swing.JTextField();
        sBonus = new javax.swing.JTextField();
        OtAmt = new javax.swing.JTextField();
        OtHrs = new javax.swing.JTextField();
        sEPF = new javax.swing.JTextField();
        ldeduct = new javax.swing.JTextField();
        totPay = new javax.swing.JTextField();
        cETF = new javax.swing.JTextField();
        cEPF = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 31, 82), 3));

        jPanel2.setBackground(new java.awt.Color(0, 31, 82));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Rage Italic", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Clinic_52px.png"))); // NOI18N
        jLabel1.setText("Deegayu");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 11, 236, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI Symbol", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("MEDICAL CENTER");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, 203, -1));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Close_Window_26px.png"))); // NOI18N
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 0, 30, 20));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Minimize_Window_26px.png"))); // NOI18N
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(664, 0, -1, 20));

        month.setFont(new java.awt.Font("Segoe UI Symbol", 1, 18)); // NOI18N
        month.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel4.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel4.setText("Employee ID :");

        jLabel5.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel5.setText("Name :");

        jLabel6.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel6.setText("Designation :");

        jLabel7.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel7.setText("Basic salary :");

        jLabel8.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel8.setText("OT hrs :");

        jLabel9.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel9.setText("Total OT amount :");

        jLabel10.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel10.setText("Seasonal Bonus :");

        jLabel11.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jLabel11.setText("Gross salary :");

        jLabel12.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel12.setText("Self EPF (8%):");

        jLabel13.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel13.setText("Leave deduction :");

        jLabel14.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jLabel14.setText("Total Payable amount :");

        jLabel15.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel15.setText("EPF (12%) :");

        jLabel16.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        jLabel16.setText("ETF (3%) :");

        jLabel17.setText("-----------------------------------------------------------------------");

        jLabel18.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Signature");

        eID.setEditable(false);
        eID.setBackground(new java.awt.Color(255, 255, 255));
        eID.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        eID.setBorder(null);

        ePosition.setEditable(false);
        ePosition.setBackground(new java.awt.Color(255, 255, 255));
        ePosition.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        ePosition.setBorder(null);

        bSal.setEditable(false);
        bSal.setBackground(new java.awt.Color(255, 255, 255));
        bSal.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        bSal.setBorder(null);

        eName.setEditable(false);
        eName.setBackground(new java.awt.Color(255, 255, 255));
        eName.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        eName.setBorder(null);

        gSal.setEditable(false);
        gSal.setBackground(new java.awt.Color(255, 255, 255));
        gSal.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        gSal.setBorder(null);

        sBonus.setEditable(false);
        sBonus.setBackground(new java.awt.Color(255, 255, 255));
        sBonus.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        sBonus.setBorder(null);

        OtAmt.setEditable(false);
        OtAmt.setBackground(new java.awt.Color(255, 255, 255));
        OtAmt.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        OtAmt.setBorder(null);

        OtHrs.setEditable(false);
        OtHrs.setBackground(new java.awt.Color(255, 255, 255));
        OtHrs.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        OtHrs.setBorder(null);

        sEPF.setEditable(false);
        sEPF.setBackground(new java.awt.Color(255, 255, 255));
        sEPF.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        sEPF.setBorder(null);

        ldeduct.setEditable(false);
        ldeduct.setBackground(new java.awt.Color(255, 255, 255));
        ldeduct.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        ldeduct.setBorder(null);

        totPay.setEditable(false);
        totPay.setBackground(new java.awt.Color(255, 255, 255));
        totPay.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        totPay.setBorder(null);

        cETF.setEditable(false);
        cETF.setBackground(new java.awt.Color(255, 255, 255));
        cETF.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        cETF.setBorder(null);

        cEPF.setEditable(false);
        cEPF.setBackground(new java.awt.Color(255, 255, 255));
        cEPF.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        cEPF.setBorder(null);

        jLabel3.setFont(new java.awt.Font("Segoe UI Symbol", 1, 15)); // NOI18N
        jLabel3.setText("Deductions");

        jButton1.setBackground(new java.awt.Color(0, 31, 82));
        jButton1.setFont(new java.awt.Font("Segoe UI Symbol", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ePosition, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(eID, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(eName, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bSal, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(87, 87, 87))
            .addComponent(jSeparator1)
            .addComponent(jSeparator2)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(26, 26, 26)
                                .addComponent(gSal, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(59, 59, 59)
                                .addComponent(jLabel10)
                                .addGap(28, 28, 28)
                                .addComponent(sBonus, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(73, 73, 73)
                                .addComponent(OtHrs, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(59, 59, 59)
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(OtAmt, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18)
                                .addComponent(totPay, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addGap(18, 18, 18)
                                        .addComponent(cEPF, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addGap(35, 35, 35)
                                        .addComponent(sEPF, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(55, 55, 55)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cETF, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addGap(29, 29, 29)
                                        .addComponent(ldeduct, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(235, 235, 235)
                        .addComponent(month, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(month, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jLabel5)
                        .addComponent(eName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(eID, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(bSal, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1))
                    .addComponent(ePosition, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(OtAmt, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(OtHrs, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10)
                    .addComponent(sBonus, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gSal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(sEPF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ldeduct, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(totPay, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(cETF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cEPF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18))
                    .addComponent(jButton1))
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jLabel19MouseClicked

    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked
        // TODO add your handling code here:
        this.setState(Frame.ICONIFIED);
    }//GEN-LAST:event_jLabel20MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        JFileChooser dialog= new JFileChooser();
        dialog.setSelectedFile(new File(eID.getText()+"_"+month.getText()+"_SalSlip"+".pdf"));
        int dialogResult = dialog.showSaveDialog(null);
        if(dialogResult == JFileChooser.APPROVE_OPTION){
            String filePath = dialog.getSelectedFile().getPath();
            try{
                Document myDocument = new Document();
                PdfWriter myWriter = PdfWriter.getInstance(myDocument, new FileOutputStream(filePath));
                myDocument.open();
                
                myDocument.add(new Paragraph("PAY SLIP", FontFactory.getFont(FontFactory.TIMES_BOLD, 20, Font.BOLD)));
                myDocument.add(new Paragraph(new Date().toString()));
                myDocument.add(new Paragraph("-------------------------------------------------------------------------", FontFactory.getFont(FontFactory.TIMES_BOLD, 20, Font.BOLD)));
                myDocument.add(new Paragraph("\nEmployee ID          : "+eID.getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD)));
                myDocument.add(new Paragraph("\nName                 : "+eName.getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD)));
                myDocument.add(new Paragraph("\nDesignation          : "+ePosition.getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
                myDocument.add(new Paragraph("\nBasic Salary         : "+bSal.getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
                myDocument.add(new Paragraph("\nOT Hrs               : "+OtHrs.getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
                myDocument.add(new Paragraph("\nOT pay               : "+OtAmt.getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
                myDocument.add(new Paragraph("\nSeasonal Bonus       : "+sBonus.getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
                myDocument.add(new Paragraph("\nGross Salary         : "+gSal.getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 14,Font.BOLD)));
                myDocument.add(new Paragraph("\nDeductions", FontFactory.getFont(FontFactory.TIMES_BOLD, 16, Font.BOLD)));
                myDocument.add(new Paragraph("-------------------------------------------------------------------------", FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD)));
                myDocument.add(new Paragraph("\nSelf EPF(8%)         : "+sEPF.getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
                myDocument.add(new Paragraph("\nLeave Deduction      : "+ldeduct.getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
                myDocument.add(new Paragraph("\nTotal Payable Amount : "+totPay.getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 14,Font.BOLD)));
                myDocument.add(new Paragraph("\nEPF (12%)            : "+cEPF.getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
                myDocument.add(new Paragraph("\nETF (3%)             : "+cETF.getText(), FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
                
                myDocument.close();
                
                JOptionPane.showMessageDialog(null, "Salary Slip successfuly saved");
            }
            catch(Exception e){
                
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SalarySlip.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SalarySlip.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SalarySlip.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SalarySlip.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SalarySlip().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField OtAmt;
    private javax.swing.JTextField OtHrs;
    private javax.swing.JTextField bSal;
    private javax.swing.JTextField cEPF;
    private javax.swing.JTextField cETF;
    private javax.swing.JTextField eID;
    private javax.swing.JTextField eName;
    private javax.swing.JTextField ePosition;
    private javax.swing.JTextField gSal;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField ldeduct;
    private javax.swing.JLabel month;
    private javax.swing.JTextField sBonus;
    private javax.swing.JTextField sEPF;
    private javax.swing.JTextField totPay;
    // End of variables declaration//GEN-END:variables
}
