
package itpproject;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.time;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author User S.Tiffany
 */


public class Urine extends Lab {
    
    

    static ResultSet AddUrineResult() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    int Uid;
    String color;
    String appearance;
    String gravity;
    String reaction;
    String protein;
    String sugar;
    String urobilin;
    String bilirubin;
    String ketone;
    String pus;
    String red;
    String epithelial;
    String organisms;
    String crystal;
    String casts;
    String Other;
    String testNo;
    
    
 // CONSTRUTOR from super class   
 public Urine(String tn,String t,String ss, Boolean s,float c,String pn,double cn,int a,boolean sx,String pc,java.sql.Date d,String ti)
    {
       super(tn,t,ss,s,c,pn,cn,a,sx,pc,d,ti);
    }
 /*//CONSTRUCTOR
public Urine(int ud,String c,String ap,String gr,String r,String p,String su,String uro,String bil,String ket,String pu,String re,String ep,String org,String cr,String ca,String o)
{
    this.Uid=ud;
    this.color=c;
    this.appearance=ap;
    this.gravity=gr;
    this.reaction=r;
    this.protein=p;
    this.sugar=su;
    this.urobilin=uro;
    
    this.ketone=ket;
    this.pus=pu;
    this.red=re;
    this.epithelial=ep;
    this.organisms=org;
    this.crystal=cr;
    this.casts=ca;
    this.Other=o;



} 
*/

//constructor to add urine result
    public Urine(String tid ,String color,String app,String SG ,String react,String protein,String sugar,String uro,String bili,String Ket ,String pusC,String redC,String epC,String org,String cry,String cas,String other) {
      
        this.testNo = tid;
        this.color = color;
        this.appearance = app;
        this.gravity = SG;
        this.reaction = react;
        this.protein=protein;
        this.sugar = sugar;
        this.urobilin=uro;
        this.bilirubin = bili;
        this.ketone= Ket;
        this.pus=pusC;
        this.red=redC;
        this.epithelial = epC;
        this.organisms=org;
        this.crystal =cry;
        this.casts=cas;
        this.Other=other;
        
        
        Connection con = null;
        PreparedStatement pst = null;
        con = DBconnect.getConnection();
        
        try{

    con = DBconnect.getConnection();
    String sql1 = "INSERT INTO urineresults (colour,apperance,gravity,reaction,protein,sugar,urobilin,bilirubin,ketone,puscells,redcells,epcells,organisms,crystals,casts,other,testNo) value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    pst = con.prepareStatement(sql1);
             pst.setString(1, color);
             pst.setString(2, app);
             pst.setString(3, SG);
             pst.setString(4, react);
             pst.setString(5, protein);
             pst.setString(6, sugar);
             pst.setString(7, uro);
             pst.setString(8, bili);
             pst.setString(9,Ket);
             pst.setString(10, pusC);
             pst.setString(11, redC);
             pst.setString(12, epC);
             pst.setString(13, org);
             pst.setString(14, cry);
             pst.setString(15, cas);
             pst.setString(16, other);
             pst.setString(17,tid);
             
     pst.executeUpdate();
     JOptionPane.showMessageDialog(null, "Urine Rsult Recorded", "ok", JOptionPane.INFORMATION_MESSAGE);         

}
catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }

    
    
    
    }

    Urine() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
// ADDING A Urine test RESULT
/*
public void AddUrineResult(String tid,String color,String app,String SG ,String react,String protein,String sugar,String uro,String bili,String Ket ,String pusC,String redC,String epC,String org,String cry,String cas,String other)
{
  
 
    Connection con = null;
    PreparedStatement pst = null;
    con = DBconnect.getConnection();
    
try{

    con = DBconnect.getConnection();
    String sql1 = "INSERT INTO urineresults (colour,apperance,gravity,reaction,protein,sugar,urobilin,bilirubin,ketone,puscells,redcells,epcells,organisms,crystals,casts,other,testNo) value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    pst = con.prepareStatement(sql1);
             pst.setString(1, color);
             pst.setString(2, app);
             pst.setString(3, SG);
             pst.setString(4, react);
             pst.setString(5, protein);
             pst.setString(6, sugar);
             pst.setString(7, uro);
             pst.setString(8, bili);
             pst.setString(9,Ket);
             pst.setString(10, pusC);
             pst.setString(11, redC);
             pst.setString(12, epC);
             pst.setString(13, org);
             pst.setString(14, cry);
             pst.setString(15, cas);
             pst.setString(16, other);
             pst.setString(17,tid);
             
     pst.executeUpdate();
     JOptionPane.showMessageDialog(null, "Urine Rsult Recorded", "ok", JOptionPane.INFORMATION_MESSAGE);         

}
catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }


}
*/

//UPDATE METHOD 

//(tid,color,app,SG,react,protein,sugar,uro,bili,Ket,pusC,redC,epC,org,cry,cas,other);

 public  void UpdateUrineR(String tid, String color,String app, String SG,String react,String protein,String sugar,String uro,String bili,String Ket,String pusC,String redC,String epC,String org,String cry, String cas, String other)
    {
       con = DBconnect.getConnection();
       try{
          String sql2 = "UPDATE urineresults SET colour ='"+color+"',apperance= '"+app+"', gravity = '"+SG+"',reaction='"+react+"',protein = '"+protein+"', sugar='"+sugar+"',urobilin ='"+uro+"',bilirubin='"+bili+"',keton ='"+Ket+"',puscells='"+pusC+"',redcells = '"+redC+"',epcells ='"+epC+"',organisms = '"+org+"',crystals ='"+cry+"',casts = '"+cas+"',other = '"+other+"' WHERE testNo ='"+tid+"' ";                             
          pst = con.prepareStatement(sql2);
          pst.execute();
          JOptionPane.showMessageDialog(null,"Urine result record updated","ok",JOptionPane.INFORMATION_MESSAGE);
       
       }
       
       catch(Exception ex) {
       JOptionPane.showMessageDialog(null,ex.getMessage());
       }
    
    
    
    }
 
 
 
 
 
 
 
 
    
}

