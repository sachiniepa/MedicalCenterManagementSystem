
package itpproject;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

/**
 *
 * @author User S.Tiffany
 */

public class Blood extends Lab{
    
    String testNo;
    double glucosefast;
    double tsHormone;
    double cholesterol;
    double triglyceride;
    double hdl;
    double ldl;
    double vdl;
    double ratio;
    double bur;
    String pppglucose;
    double neutro;
    double lympho;
    double eosin;
    double mono;
    double basophil;
    double hb;
    double pcv;
    double pateletC;
    double wbwCount;
    
public Blood(String tn,String t,String ss, Boolean s,float c,String pn,double cn,int a,boolean sx,String pc,Date d,String ti)
    {
        super(tn,t,ss,s,c,pn,cn,a,sx,pc,d,ti);
    }
    
public Blood()
{
    
}

Connection con = DBconnect.getConnection();

//Constructor
public Blood(String id,double gf,double tsh,double ch,double tri,double hl,double ll,double vl,double ra,double bu,String pppg,double ne, double ly,double eo,double mon,double baso,double hb,double pv,double patc,double wbw)
    {
     this.testNo=id;
     this.glucosefast = gf;
     this.tsHormone = tsh;
     this.cholesterol= ch;
     this.triglyceride = tri;
     this.hdl= hl;
     this.ldl=ll;
     this.vdl=vl;
     this.ratio=ra;
     this.bur=bu;
     this.pppglucose=pppg;
     this.neutro=ne;
     this.lympho=ly;
     this.eosin=eo;
     this.mono=mon;
     this.basophil=baso;
     this.hb = hb;
     this.pcv=pv;
     this.pateletC=patc;
     this.wbwCount=wbw; 
     
     Connection con = null;
    PreparedStatement pst = null;
    con = DBconnect.getConnection();
    
    try{
          con = DBconnect.getConnection();
          String sql1 = "INSERT INTO bloodresults (testNo,glucoseF,tsHormone,cholesterol,triglyceride,hdl,ldl,vdl,ratio,bUrea,ppPlasmaGlu,neutro,lympho,eosin,mono,basophil,hb,pcv,pateletC,wbwCount) value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
          pst = con.prepareStatement(sql1);
             pst.setString(1, id);
             pst.setDouble(2, gf);
             
             pst.setDouble(3, tsh);
             pst.setDouble(4, ch);
             pst.setDouble(5, tri);
             pst.setDouble(6, hl);
             pst.setDouble(7, ll);
             pst.setDouble(8, vl);
             pst.setDouble(9, ra);
             pst.setDouble(10,bu);
             pst.setString(11, pppg);
             pst.setDouble(12,ne);
             pst.setDouble(13,ly);
             pst.setDouble(14,eo);
             pst.setDouble(15,mon);
             pst.setDouble(16,baso);
             pst.setDouble(17,hb);
             pst.setDouble(18,patc);
             pst.setDouble(19,wbw);
        pst.executeUpdate();
        JOptionPane.showMessageDialog(null, "Blood Result Recorded", "ok", JOptionPane.INFORMATION_MESSAGE);  
           
    
    }
    
    catch (Exception e)
    {
     
        JOptionPane.showMessageDialog(null,e.getMessage());
       
    }
    }
    

// CONSTRUCTOR lipid profile
public Blood(String id,double ch,double tri,double hl,double ll,double vl,double ra)
    {
     this.testNo=id;
     this.cholesterol= ch;
     this.triglyceride = tri;
     this.hdl= hl;
     this.ldl=ll;
     this.vdl=vl;
     this.ratio=ra;
     
     
     Connection con = null;
    PreparedStatement pst = null;
    con = DBconnect.getConnection();
    
    try{
          con = DBconnect.getConnection();
          String sql1 = "INSERT INTO bloodresults (testNo,cholesterol,triglyceride,hdl,ldl,vdl,ratio) value(?,?,?,?,?,?,?)";
          pst = con.prepareStatement(sql1);
             pst.setString(1, id);
             
             
             pst.setDouble(2, ch);
             pst.setDouble(3, tri);
             pst.setDouble(4, hl);
             pst.setDouble(5, ll);
             pst.setDouble(6, vl);
             pst.setDouble(7, ra);
            
        pst.executeUpdate();
        JOptionPane.showMessageDialog(null, "Blood Result Recorded", "ok", JOptionPane.INFORMATION_MESSAGE);  
           
    
    }
    
    catch (Exception e)
    {
     
        JOptionPane.showMessageDialog(null,e.getMessage());
       
    }
    }
    
// CONSTRUCTOR FBS
public Blood(String id,double gf)
    {
     this.testNo=id;
     this.glucosefast=gf;
     
     
     Connection con = null;
    PreparedStatement pst = null;
    con = DBconnect.getConnection();
    
    try{
          con = DBconnect.getConnection();
          String sql1 = "INSERT INTO bloodresults (testNo,glucoseF) value(?,?)";
          pst = con.prepareStatement(sql1);
             pst.setString(1, id);      
             pst.setDouble(2, gf);
          pst.executeUpdate();
        JOptionPane.showMessageDialog(null, "Blood Result Recorded", "ok", JOptionPane.INFORMATION_MESSAGE);  
    }
    
    catch (Exception e)
    {
     
        JOptionPane.showMessageDialog(null,e.getMessage());
       
    }
    }
    
// CONSTRUCTOR Blood Urea
public Blood(double br,String id)
    {
     this.testNo=id;
     this.bur=br;
     
     
     Connection con = null;
    PreparedStatement pst = null;
    con = DBconnect.getConnection();
    
    try{
          con = DBconnect.getConnection();
          String sql1 = "INSERT INTO bloodresults (testNo,bUrea) value(?,?)";
          pst = con.prepareStatement(sql1);
             pst.setString(1, id);      
             pst.setDouble(2, bur);
          pst.executeUpdate();
        JOptionPane.showMessageDialog(null, "Blood Result Recorded", "ok", JOptionPane.INFORMATION_MESSAGE);  
    }
    
    catch (Exception e)
    {
     
        JOptionPane.showMessageDialog(null,e.getMessage());
       
    }
}



// CONSTRUCTOR Full Blood Count
public Blood(String id, double ne,double ly, double eo,double mo, double ba, double hb,double pc, double pa,double wbw)
{
 this.testNo=id;
 this.neutro=ne;
     this.lympho=ly;
     this.eosin=eo;
     this.mono=mo;
     this.basophil=ba;
     this.hb=hb;
     this.pcv=pc;
     this.pateletC=pa;
     this.wbwCount=wbw;
     
     
     Connection con = null;
    PreparedStatement pst = null;
    con = DBconnect.getConnection();
    
    try{
          con = DBconnect.getConnection();
          String sql1 = "INSERT INTO bloodresults (testNo,neutro,lympho,eosin,mono,basophil,hb,pcv,pateletC,wbwCount) value(?,?,?,?,?,?,?,?,?,?)";
          pst = con.prepareStatement(sql1);
             pst.setString(1, id);      
             pst.setDouble(2, ne);
             pst.setDouble(3,ly);
             pst.setDouble(4,eo);
             pst.setDouble(5,mo);
             pst.setDouble(6,ba);
             pst.setDouble(7,hb);
             pst.setDouble(8,pc);
             pst.setDouble(9,pa);
             pst.setDouble(10,wbw);
          pst.executeUpdate();
        JOptionPane.showMessageDialog(null, "Blood Result Recorded", "ok", JOptionPane.INFORMATION_MESSAGE);  
    }
    
    catch (Exception e)
    {
     
        JOptionPane.showMessageDialog(null,e.getMessage());
       
    }
    }

//PPBS ADD methof
public void ppbsAdd(String tid, double ppbs)
{
   Connection con = null;
    PreparedStatement pst = null;
    con = DBconnect.getConnection();
    
    try{
          con = DBconnect.getConnection();
          String sql1 = "INSERT INTO bloodresults (testNo,ppPlasmaGlu) value(?,?)";
          pst = con.prepareStatement(sql1);
             pst.setString(1, tid);      
             pst.setDouble(2, ppbs);
          pst.executeUpdate();
        JOptionPane.showMessageDialog(null, "Blood Result Recorded", "ok", JOptionPane.INFORMATION_MESSAGE);  
    }
    
    catch (Exception e)
    {
     
        JOptionPane.showMessageDialog(null,e.getMessage());
       
    }

}

// CONSTRUCTOR PPBS
public Blood(String id,String ppbs)
    {
     this.testNo=id;
     this.pppglucose=ppbs;
     
     
     Connection con = null;
    PreparedStatement pst = null;
    con = DBconnect.getConnection();
    
    try{
          con = DBconnect.getConnection();
          String sql1 = "INSERT INTO bloodresults (testNo,ppPlasmaGlu) value(?,?)";
          pst = con.prepareStatement(sql1);
             pst.setString(1, id);      
             pst.setString(2, ppbs);
          pst.executeUpdate();
        JOptionPane.showMessageDialog(null, "Blood Result Recorded", "ok", JOptionPane.INFORMATION_MESSAGE);  
    }
    
    catch (Exception e)
    {
     
        JOptionPane.showMessageDialog(null,e.getMessage());
       
    }
    }







/*
//ADDING A BLOOD RESULT TO blood table 
public  void AddBloodResult(int tID,double glufast,double tsHormone,double cho,double tri,double hdl,double ldl,double vdl,double ratio,double bur,double pppglu)
{
    Connection con = null;
    PreparedStatement pst = null;
    con = DBconnect.getConnection();
    
    try{
          con = DBconnect.getConnection();
          String sql1 = "INSERT INTO bloodresults (testNo,glucoseF,tsHormone,cholesterol,triglyceride,hdl,ldl,vdl,ratio,bUrea,ppPlasmaGlu) value(?,?,?,?,?,?,?,?,?,?,?)";
          pst = con.prepareStatement(sql1);
             pst.setDouble(2, glufast);
             pst.setInt(1,tID);
             pst.setDouble(3, tsHormone);
             pst.setDouble(4, cho);
             pst.setDouble(5, tri);
             pst.setDouble(6, hdl);
             pst.setDouble(7, ldl);
             pst.setDouble(8, vdl);
             pst.setDouble(9, ratio);
             pst.setDouble(10,bur);
             pst.setDouble(11, pppglu);
        pst.executeUpdate();
        JOptionPane.showMessageDialog(null, "Blood Result Recorded", "ok", JOptionPane.INFORMATION_MESSAGE);  
        
        
}
    
    catch(Exception ex) {
        JOptionPane.showMessageDialog(null,ex.getMessage());
    }}
*/


//UPDATE A BLOOD RESULT

public  void UpdateBloodR(int tID,double glufast,double tsHormone,double cho,double tri,double hdl,double ldl,double vdl,double ratio,double bur,double pppglu )
    {
       con = DBconnect.getConnection();
       try{
          String sql2 = "UPDATE test SET glucoseF = '"+ glufast +"', tsHormone = '"+tsHormone+"',cholesterol ='"+cho+"',triglyceride= '"+tri+"', hdl = '"+hdl+"',ldl='"+ldl+"',	vdl = '"+vdl+"', ratio='"+ratio+"', bUrea='"+bur+"',='"+pppglu+"' WHERE testNo ='"+tID+"' ";                             
          pst = con.prepareStatement(sql2);
          pst.execute();
          JOptionPane.showMessageDialog(null,"blood result record updated","ok",JOptionPane.INFORMATION_MESSAGE);
       
       }
       
       catch(Exception ex) {
       JOptionPane.showMessageDialog(null,ex.getMessage());
       }
    
    
    
    }

//UPDATE METHOD OVERLOAD FOR LIPID PROFILE 

public  void UpdateBloodR(int tID,double cho,double tri,double hdl,double ldl,double vdl,double ratio)
    {
       con = DBconnect.getConnection();
       try{
          String sql2 = "UPDATE bloodresults SET cholesterol ='"+cho+"',triglyceride= '"+tri+"', hdl = '"+hdl+"',ldl='"+ldl+"',	vdl = '"+vdl+"', ratio='"+ratio+"' WHERE testNo ='"+tID+"' ";                             
          pst = con.prepareStatement(sql2);
          pst.execute();
          JOptionPane.showMessageDialog(null,"blood result record updated","ok",JOptionPane.INFORMATION_MESSAGE);
       
       }
       
       catch(Exception ex) {
       JOptionPane.showMessageDialog(null,ex.getMessage());
       }
    
    
    
    }

//UPDATE METHOD OVERLOAD FOR Full Blood Count

public  void UpdateBloodR(int tID,double neu, double lym,double eosin,double mon,double baso,double hb,double pcv, double patC, double wbw)
    {
       con = DBconnect.getConnection();
       try{
          String sql2 = "UPDATE bloodresults SET neutro='"+neu+"',lympho= '"+lym+"', eosin = '"+eosin+"',mono='"+mon+"',basophil = '"+baso+"', hb='"+hb+"',pcv='"+pcv+"',pateletC='"+patC+"',wbwCount='"+wbw+"' WHERE testNo ='"+tID+"' ";                             
          pst = con.prepareStatement(sql2);
          pst.execute();
          JOptionPane.showMessageDialog(null,"blood result record updated","ok",JOptionPane.INFORMATION_MESSAGE);
       
       }
       
       catch(Exception ex) {
       JOptionPane.showMessageDialog(null,ex.getMessage());
       }
    
    
    
    }

//UPDATE method overload for FBS

public  void UpdateBloodR(int tID,double glufast)
    {
       con = DBconnect.getConnection();
       try{
          String sql2 = "UPDATE bloodresults SET glucoseF='"+glufast+"' WHERE testNo ='"+tID+"' ";                             
          pst = con.prepareStatement(sql2);
          pst.execute();
          JOptionPane.showMessageDialog(null,"blood result record updated","ok",JOptionPane.INFORMATION_MESSAGE);
       
       }
       
       catch(Exception ex) {
       JOptionPane.showMessageDialog(null,ex.getMessage());
       }
    
    
    
    }

//UPDATE method overload for FBS

public  void UpdateBloodR(double bur, int tid)
    {
       con = DBconnect.getConnection();
       try{
          String sql2 = "UPDATE bloodresults SET bUrea='"+bur+"' WHERE testNo ='"+tid+"' ";                             
          pst = con.prepareStatement(sql2);
          pst.execute();
          JOptionPane.showMessageDialog(null,"blood result record updated","ok",JOptionPane.INFORMATION_MESSAGE);
       
       }
       
       catch(Exception ex) {
       JOptionPane.showMessageDialog(null,ex.getMessage());
       }
    
    
    
    }

//UPDATE method for ppbs

public void Updateppbs(int tid, double ppbs)
{
    con = DBconnect.getConnection();
    try{
         String sq1 = "UPDATE bloodresults SET ppPlasmaGlu = '"+ppbs+"' WHERE testNo = '"+tid+"' ";
         pst = con.prepareStatement(sq1);
         pst.execute();
          JOptionPane.showMessageDialog(null,"blood result record updated","ok",JOptionPane.INFORMATION_MESSAGE);
    
    }

catch(Exception ex) {
       JOptionPane.showMessageDialog(null,ex.getMessage());
       }
}



}
 