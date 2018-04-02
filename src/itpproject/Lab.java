
package itpproject;
import java.sql.*;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.concurrent.TimeUnit;
/**
 *
 * @author S.Tiffany
 */





import javax.swing.JOptionPane;




public class Lab{
    private String TestNo;
    private String type;
    private String specimen;
    private float charge;
    private String PName;
    private double ContNo;
    private int age;
    private boolean sex;
    private String Pcat;
    private Date date;
    private String time;
    
    Connection con = null;
    PreparedStatement pst = null;
    public Lab(){
        
    }
    
    public Lab(String tn,String t,String ss, Boolean s,float c,String pn,double cn,int a,boolean sx,String pc,Date d,String ti)
           {
             this.TestNo=tn;
             this.type=t;
             this.specimen =ss;
             this.charge = c;
             this.PName = pn;
             this.ContNo =cn;
             this.age = a;
             this.sex = s;
             this.Pcat = pc;
             this.date = d;
             this.time = ti;
           }
    
    
    
    // ADD LAB TEST
    public void AddLabTest(String Pname,String contactNo,int age,String sex, String pCat,Date sdobday,String type,String speciemen, Double charge)
    
    { 
        con = DBconnect.getConnection();
	try{ 
            
         
            String sql1 = "INSERT INTO test (patientName,conatctNo,age,gender,pcategory,date,type,speciman,charge) value( ?, ?, ?, ?, ?,? ,? ,?, ?)";
             pst = con.prepareStatement(sql1);
             pst.setString(1, Pname);
             pst.setString(2, contactNo);
             pst.setInt(3, age);
             pst.setString(4, sex);            

             pst.setString(5, pCat);
             pst.setDate(6, sdobday);
           // pst.setString(7,time);
             pst.setString(7, type);
             pst.setString(8, speciemen);
             pst.setDouble(9, charge);
             
             pst.executeUpdate();
             JOptionPane.showMessageDialog(null, "test record added", "ok", JOptionPane.INFORMATION_MESSAGE); 
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
    }

    //UPADTE LAB TEST
    public  void UpdateLabTest(String tid,String Pname,String contactNo,int age, String sex,String pCat, Date sdobday,String t,String type,String specimen,double charge )
    {
       con = DBconnect.getConnection();
            String sql = "update test set patientName='"+Pname+"', conatctNo='"+contactNo+"', age='"+age+"', gender='"+sex+"', pcategory='"+pCat+"', date='"+sdobday+"', time='"+t+"', type='"+type+"', speciman='"+specimen+"', charge='"+charge+"' WHERE testNo='"+tid+"'     ";  
       try{
                               
          pst = con.prepareStatement(sql);
          pst.execute();
          JOptionPane.showMessageDialog(null,"test record updated","ok",JOptionPane.INFORMATION_MESSAGE);
       
       }
       
       catch(Exception ex) {
       JOptionPane.showMessageDialog(null,ex.getMessage());
       }
    
    
    
    }
    
    
    
    
        //DELETE A LAB TEST

    /**
     *
     * @param tn
     */
    public void DeleteLabTest(String tid) 
     {
       
        try{
            Connection con = DBconnect.getConnection();
            
            String dquery ="DELETE FROM test WHERE testNo = '"+tid+"' ";       //delete query 
            PreparedStatement pst2=con.prepareStatement(dquery);             
            pst2.executeUpdate();       //execution
            JOptionPane.showMessageDialog(null, "Test deleted!!");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }   
        
     }
    
      public  void alertSMS(String sms,String tel){
          /*  String URL="https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0";
      
            String username="Deegayuu";
            String password="deegayuu123";
             SMS pSms= new SMS();
             pSms.SendSMS(username, password, sms, tel, URL);*/
        }
        
        
     // LOAD TEST TABLE
       /*public void()
       {
           con= DBconnect.getConnection();
           Laboratory.tableload(); 
           
       }}*/
        
        
}
  

//UPDATE A LAB TEST RECORD
    