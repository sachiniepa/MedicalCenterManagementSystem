/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itpproject;

import com.teknikindustries.bulksms.SMS;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Shalindri
 */
public class Session {
    private String sId;
    private String empNo;
    private String sTime;
    private String eTime;
    private int noOfApp;
    private double fee;
    private String status;
    private String  specialty;
    private String name;
    private String message;
    private String contactNo;
   
    
   
    Session(String i,String e, String st,String et,int num,double f,String s,String sp,String n,String sms,String tel){
       
        this.sId=i;
        this.empNo=e;
        this.sTime =st;
        this.eTime = et;
        this.noOfApp = num;
        this.fee =f;
        this.status = s;
        this.specialty=sp;
        this.name=n;
        this.message=sms;
        this .contactNo=tel;
        
        
      }
    Session(){}
    
  /*  Session(String sp,String n){
    this.specialty=sp;
    this.name=n;
    
    }*/
    Session(String message,String contactNo){
     this.message=message;
    this.contactNo=contactNo;


}
   
  //fill speciality combo box 
   public static ResultSet fillCombo(){
        PreparedStatement pst= null;
        ResultSet rs = null;
        Connection con=null;
        try{
            con=DBconnect.getConnection();
            String query="select distinct Speciality  from doctor";
            pst=con.prepareStatement(query);
            rs=pst.executeQuery();

        } 
        catch (SQLException ex) {
            Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
   }
   
   //fill Name combo box 
   public static ResultSet fillNameCombo(){
        PreparedStatement pst= null;
        ResultSet rs = null;
        Connection con=null;
        try{
            con=DBconnect.getConnection();
            String query="select distinct Speciality  from doctor";
            pst=con.prepareStatement(query);
            rs=pst.executeQuery();

        } 
        catch (SQLException ex) {
            Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
   }
    
    public static ResultSet fillNameComboFiltered(String select,String combo){
      ResultSet rs=null;
        try{
            Connection con=DBconnect.getConnection();
            String query="select distinct name  from doctor where Speciality=?";
            PreparedStatement pst=con.prepareStatement(query); 
            pst.setString(1,select);
           
            rs=pst.executeQuery();
            while(rs.next()){
                String name=rs.getString("name");
                
            }
            
           
        }
        catch(SQLException ex){
               JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
        return rs;
   }
    
    
    //search function
    public ResultSet searchDoctor(String sp){
        PreparedStatement pst= null;
        ResultSet rs = null;
        Connection con=null;
        

         try 
        {
            
            con = DBconnect.getConnection();
            
           String sql = "Select * from doctor where Speciality =?"; //search query
           pst= con.prepareStatement(sql);
           //pst= (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sql);  
         // pst.setString(1,sp);
           rs = pst.executeQuery(); 
            
        } 
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
         return rs;
       
    }
    //
    
    ////////////////////////////////////////////////////////////////////

   public int generateAppNo(int sessionID){
        int appNo=0;
        try{  
                    Connection con=DBconnect.getConnection();
                    con=DBconnect.getConnection();
	
                    String query = "select currentAppNo from session where sessionID = ?";
                    PreparedStatement pst =con.prepareStatement(query);
                    pst.setInt(1,sessionID);
                    ResultSet rs = pst.executeQuery();
                    while(rs.next()){
                    int aApp=rs.getInt("currentAppNo");
                    appNo=aApp+1;
                    }
                    
        }
        catch(Exception e){
                  JOptionPane.showMessageDialog(null, e.getMessage());  
                }
        return appNo;
    }
   
       public String calAprxTime(int sessionID){
        String ApTime=null;
        try{  
                    Connection con=DBconnect.getConnection();
                    con=DBconnect.getConnection();
	
                    String query = "select startTime,endTime ,noOfApp ,avilableApp from session where sessionID = ?";
                    PreparedStatement pst =con.prepareStatement(query);
                    pst.setInt(1,sessionID);
                    ResultSet rs = pst.executeQuery();
                   while(rs.next()){
                    String sTime=rs.getString("startTime");
                    String eTime=rs.getString("endTime");
                    int totalApp=rs.getInt("noOfApp");
                    int availableApp=rs.getInt("avilableApp");
                  
                  
                  
                    
                    //java.util.Date sTime1 = new Date(rs.getDate("startTime").getTime());
                   // java.util.Date eTime1 = new Date(rs.getDate("endTime").getTime());
        
                  //  String time1 = sTime;
                  //  String time2 = eTime;
                    
                    SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                   
                        java.util.Date sTime1 = format.parse(sTime);
                        java.util.Date eTime1 = format.parse(eTime);
                        long diff = eTime1.getTime() - sTime1.getTime();
                        java.sql.Date sTime3=new java.sql.Date(sTime1.getTime());
                        
                   int duration=(int) TimeUnit.MINUTES.convert(diff, TimeUnit.MILLISECONDS);
              //     int sTime2=(int) TimeUnit.MINUTES.convert(sTime1.getTime(), TimeUnit.MILLISECONDS);
                  // int eTime2=(int) TimeUnit.SECONDS.convert(eTime1.getTime(), TimeUnit.MILLISECONDS);
                   
                    
                    int aprxTime = (duration*(availableApp)/totalApp);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(sTime3);
                    cal.add(Calendar.MINUTE, aprxTime);
                    ApTime = format.format(cal.getTime());
                    
                   
                
        }}
        catch(Exception e){
                  JOptionPane.showMessageDialog(null, e.getMessage());  
                }
		
        
        return ApTime;
       }
       
  
    
     public void EnterAppointment(String time,int appNo,int sessionID,int patientID,double fee,String source){ //enter Appointment details into Appointment table
	String ar="AP";
                try{  
                    Connection con=DBconnect.getConnection();
                    con=DBconnect.getConnection();
                    
                String q1 = "INSERT INTO appseq (name) VALUES ('aaaaa')";
                String q2 = "SELECT CONCAT('AP', LPAD(LAST_INSERT_ID(), 3, '0')) as eid FROM appseq";
		
                String query2 = "insert into appointment(ApRefNo,date,time,appNo,sessionID,patientID,fee,source) values(?,?,?,?,?,?,?,?)";
                String query3 = "select date from session where sessionID = ?";
               // String query4 = "update session set currentAppNo = ? where sessionID=?";
                PreparedStatement pst =con.prepareStatement(q1);
                pst.executeUpdate();
            
               Statement st = con.createStatement();
               ResultSet rs1 = st.executeQuery(q2);
               
                if(rs1.next()){
                    ar=rs1.getString("eid");
                }
                PreparedStatement pst2 =con.prepareStatement(query2);
                PreparedStatement pst3 =con.prepareStatement(query3);
              //  PreparedStatement pst4 =con.prepareStatement(query4);
                
         
         //get date from session Table  
         
                pst3.setInt(1,sessionID);
                ResultSet rs = pst3.executeQuery();
                
        //insert into Appointment table    
                if(rs.next()){
                pst2.setString(1,ar); 
                pst2.setDate(2,rs.getDate("date")); 
                pst2.setString(3,time);
                pst2.setInt(4,appNo);
                pst2.setInt(5,sessionID);
                pst2.setInt(6,patientID);       
                pst2.setDouble(7,fee);
                 pst2.setString(8,source);
                
               
              int status= pst2.executeUpdate();
              if(status>0){
                  JOptionPane.showMessageDialog(null,"Appointment has been placed successfully");  
                  
              }
               
                }
                /*
        //update Session table
                if(rs.next()){
                   // currentApp=rs.getInt("currentAppNo");
                    pst4.setInt(1,appNo );
                    pst4.setInt(2,sessionID);
                    pst4.executeUpdate();  
                }
*/
                }
                catch(Exception e){
                  JOptionPane.showMessageDialog(null, e.getMessage());  
                }
		
        }
      public int UpdateCurrentApp(int sessionID,int appNo){ //update currentAppointmet no in session  table when placing an appointment 
	int status=0;
	
	try{
		Connection con=DBconnect.getConnection();
		
		
                        String query2="update session set currentAppNo= ? where sessionID=?";
                        PreparedStatement pst2=con.prepareStatement(query2);
                        pst2.setInt(1,appNo);
                   
                        pst2.setInt(2,sessionID);
		
                        status=pst2.executeUpdate();
		
		con.close();}
                catch(Exception e){System.out.println(e);}
	return status;
}
     
     
     public int UpdateAvailableApp(int sessionID){ //update session  table when placing an appointment (available+1)
	int status=0;
	int available=0;
	try{
		Connection con=DBconnect.getConnection();
		String query ="select avilableApp from session where sessionID=?";
		PreparedStatement ps=con.prepareStatement(query);
		ps.setInt(1,sessionID);
		ResultSet rs=ps.executeQuery();
		if(rs.next()){ 
			//total=rs.getInt("noOfApp");
			available=rs.getInt("avilableApp");
		
                        String query2="update session set avilableApp= ? where sessionID=?";
                        PreparedStatement pst2=con.prepareStatement(query2);
                        pst2.setInt(1,available+1);
                   
                        pst2.setInt(2,sessionID);
		
		status=pst2.executeUpdate();
		}
		con.close();
	}catch(Exception e){System.out.println(e);}
	return status;
}
        public void UpdateFillAction(int sessionID){ //update action As Filled in session table

	try{
		Connection con=DBconnect.getConnection();
		
                        String query2="update session set action= 'Filled' where sessionID=?";
                        PreparedStatement pst2=con.prepareStatement(query2);
                        pst2.setInt(1,sessionID);
                        pst2.executeUpdate();
		
		con.close();}
                catch(Exception e){System.out.println(e);}
	
}
        
        public int UpdateStatusBlocked(int sessionID){ //update status As blocked in session table
            int status=0;   
	try{
		Connection con=DBconnect.getConnection();
		
                        String query2="update session set status= 'Blocked' where sessionID=?";
                        PreparedStatement pst2=con.prepareStatement(query2);
                        pst2.setInt(1,sessionID);
                        status=pst2.executeUpdate();
		
		con.close();}
                catch(Exception e){System.out.println(e);
                 System.out.println("error in blocked multisession method");}
            return status;	
}
        public int UpdateStatusAvailable(int sessionID){ //update status As Available in session table
            int status=0;   
	try{
		Connection con=DBconnect.getConnection();
		
                        String query2="update session set status= 'Available' where sessionID=?";
                        PreparedStatement pst2=con.prepareStatement(query2);
                        pst2.setInt(1,sessionID);
                        status=pst2.executeUpdate();
		
		con.close();}
                catch(Exception e){System.out.println(e);}
            return status;	
}
     
     //Cancel An Appointment---------------------------------------------------------------------------------------------------------------------------
     
     
     
     public void deleteAppointment(String apRefNo)//delete appointment details from Appointment table
{
                
    try{
                Connection con=DBconnect.getConnection();
                String query = "DELETE FROM appointment WHERE apRefNo =?";
                PreparedStatement pst =con.prepareStatement(query);
                pst.setString(1, apRefNo);
             
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "The Appointment has canceled successfully.");
        }
          catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
       } 
}
     
         public int deleteAvailableApp(int sessionID){ //update session  table when deleting an appointment (available-1)
	int status=0;
	int available=0;
	try{
		Connection con=DBconnect.getConnection();
		String query ="select avilableApp from session where sessionID=?";
		PreparedStatement ps=con.prepareStatement(query);
		ps.setInt(1,sessionID);
		ResultSet rs=ps.executeQuery();
		if(rs.next()){ 
			//total=rs.getInt("noOfApp");
			available=rs.getInt("avilableApp");
		
                        String query2="update session set avilableApp= ? where sessionID=?";
                        PreparedStatement pst2=con.prepareStatement(query2);
                        pst2.setInt(1,available-1);
                       // pst2.setInt(2,issued-1);
                        pst2.setInt(2,sessionID);
		
		status=pst2.executeUpdate();
		}
		con.close();
	}catch(Exception e){System.out.println(e);}
	return status;
}    
	
		
	public void refund(String apRfNo){
             try{  
                    Connection con=DBconnect.getConnection();
                    con=DBconnect.getConnection();
	
		
                String query1 = "insert into refund(apRefNo,patientID,total) values(?,?,?)";
                String query2 = "select a.patientID,a.fee from appointment a,session s where a.sessionId=s.sessionID and apRefNo = ?";
                
                
                PreparedStatement pst2 =con.prepareStatement(query1);
                PreparedStatement pst3 =con.prepareStatement(query2);
                
         
         //get patientID from Appointment Table  
         
                pst3.setString(1,apRfNo);
                ResultSet rs = pst3.executeQuery();
                
        //insert into Refund table  
                pst2.setString(1,apRfNo);
                if(rs.next()){
                pst2.setInt(2,rs.getInt("patientID"));
                pst2.setDouble(3,rs.getDouble("fee"));}
            
               pst2.executeUpdate();
              
                }
                catch(Exception e){
                  JOptionPane.showMessageDialog(null, e.getMessage());  
                }
		
            
        }
        
        public void UpdateUnFillAction(int sessionID){ //update action As Filled in session table

	try{
		Connection con=DBconnect.getConnection();
		
                        String query2="update session set action= 'UnFilled' where sessionID=?";
                        PreparedStatement pst2=con.prepareStatement(query2);
                        pst2.setInt(1,sessionID);
                        pst2.executeUpdate();
		
		con.close();}
                catch(Exception e){System.out.println(e);}
	
}
        public  void alertSMS(String sms,String tel){
            String URL="https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0";
      
            String username="Deegayuu3";
            String password="deegayuu123";
             SMS pSms= new SMS();
             pSms.SendSMS(username, password, sms, tel, URL);
        }
        public  void alertSMS2(String sms,String tel){
            String URL="https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0";
      
            String username="Deegayuu2";
            String password="deegayuu123";
             SMS pSms= new SMS();
             pSms.SendSMS(username, password, sms, tel, URL);
        }
        
        /*
        public void createSession(java.util.Date dd,java.util.Date sd,java.util.Date ed,int noOfApp,String startH,String startM,String endH,String endM,String empNo,String TelBooking,javax.swing.JTable jloadingTable){
        //DefaultTableModel model =(DefaultTableModel) jTable7.getModel();
        Connection con = DBconnect.getConnection();

        DateFormat df = new SimpleDateFormat("YYYY-MM-DD");
        java.util.Date date=new java.util.Date();
        //java.util.Date dd=sDate.getDate();

        java.sql.Date sessionDate=new java.sql.Date(dd.getTime());
        java.sql.Date endDate=new java.sql.Date(ed.getTime());
        java.sql.Date startDate=new java.sql.Date(sd.getTime());
       // String empNo =model.getValueAt(jTable7.getSelectedRow(),0).toString();
      //  int noOfApp=Integer.parseInt(appNo.getText());

       // String startH=hs.getText();
        //String startM=ms.getText();
        String startT=(startH+":"+startM+":00");

       // String endH=he.getText();
       // String endM=me.getText();
        String endT=(endH+":"+endM+":00");

        try{
            int a=Integer.parseInt(startH.trim());
            int b=Integer.parseInt(startM.trim());
            int c=Integer.parseInt(endH.trim());
            int d=Integer.parseInt(endM.trim());

            if(a>=0 && a<=24){
                if(b>=0 && b<60)
                if(c>=0 && c<=24){
                    if(d>=0 && d<60){
                        String query="insert into session(date,empNo,startDate,endDate,startTime,endTime,noOfApp,TelBooking) values(?,?,?,?,?,?,?,?)";
                        PreparedStatement pst2 =con.prepareStatement(query);
                        //insert into Session table

                        pst2.setDate(1,sessionDate);
                        pst2.setString(2,empNo);
                        pst2.setDate(3,startDate);
                        pst2.setDate(4,endDate);
                        pst2.setString(5,startT);
                        pst2.setString(6,endT);
                        pst2.setInt(7, noOfApp);
                        pst2.setString(8,TelBooking);
                        int status=pst2.executeUpdate();

                        if(status>0){
                            JOptionPane.showMessageDialog(null,"Session created Successfully");
                            //DefaultTableModel model =(DefaultTableModel) jTable7.getModel();

                            try{
                                //Connection con=DBconnect.getConnection();
                              //  String empno=model.getValueAt(jTable7.getSelectedRow(),0).toString();
                                String query1="select  d.name,d.Speciality,s.date,s.startTime,s.noOfApp,s.avilableApp,s.TelBooking,s.status from session s,doctor d where s.empNo=d.empNo AND s.date >= CURDATE() AND d.empNo=?";
                                PreparedStatement pst=con.prepareStatement(query1);
                                pst.setString(1,empNo);
                               ResultSet rs=pst.executeQuery();
                             
                                  jloadingTable.setModel(DbUtils.resultSetToTableModel(rs));

                            }
                            catch(SQLException ex){
                                JOptionPane.showMessageDialog(null, ex.getMessage());
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"Cannot create Session.INVALID INPUTS!");
                        }

                    }}}}

                    catch(NumberFormatException nfe){
                        
                        JOptionPane.showMessageDialog(null,"INVALID INPUTS!","ERROR",JOptionPane.ERROR_MESSAGE);
                    } catch (SQLException ex) {
                      
                        JOptionPane.showMessageDialog(null,"INVALID INPUTS!","ERROR",JOptionPane.ERROR_MESSAGE);
                    }
        
        
        
        }*/
        public void createMultiSession(java.util.Date startDate,java.util.Date endDate,int selection,int noOfApp,String startT,String endT,String empNo,String TelBooking,javax.swing.JTable jloadingTable){
            Calendar startCal =Calendar.getInstance();
            Calendar endCal =Calendar.getInstance();
             java.sql.Date startDate1=new java.sql.Date(startDate.getTime());
             java.sql.Date endDate1=new java.sql.Date(endDate.getTime());
             
            startCal.setTime(startDate);
            endCal.setTime(endDate);
             while(startCal.getTimeInMillis()<=endCal.getTimeInMillis()){
                 if(startCal.get(Calendar.DAY_OF_WEEK)== selection){
                     //java.util.Date selectedDate= startCal.getTime();
                     createSession2(startCal.getTime(),startDate1,endDate1,noOfApp,startT, endT,empNo,TelBooking,jloadingTable);
                 }
                 startCal.add(Calendar.DAY_OF_MONTH,1);
             }
           
             
            
        }
        public void blockMultiSessions(String empNo,java.util.Date startDate,java.util.Date endDate){
            try {
            Connection con = DBconnect.getConnection();
            Calendar startCal =Calendar.getInstance();
            Calendar endCal =Calendar.getInstance();
            Calendar sesscal =Calendar.getInstance();
            
            
            java.sql.Date startDate1=new java.sql.Date(startDate.getTime());
            java.sql.Date endDate1=new java.sql.Date(endDate.getTime());
            startCal.setTime(startDate);
            endCal.setTime(endDate);
            
            String query="select sessionID,date,noOfApp,avilableApp ,status,action from session "
                    + "where empNo=? AND status= 'Available' AND date BETWEEN ? AND ? order by date ";
            PreparedStatement pst=con.prepareStatement(query);
            pst.setString(1,empNo);
            pst.setDate(2,startDate1);
            pst.setDate(3,endDate1);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                
                java.sql.Date sessionDate=rs.getDate("date");
                java.util.Date new1= sessionDate;
                sesscal.setTime(new1);
                
                int sid2=rs.getInt("sessionID");
                int availableApp4=rs.getInt("avilableApp");
              
            if(availableApp4 == 0){ 
     
           
            while(startCal.getTimeInMillis()<=endCal.getTimeInMillis()){
                 System.out.println(sesscal.get(Calendar.DAY_OF_YEAR)+"="+startCal.get(Calendar.DAY_OF_YEAR));
                if((startCal.get(Calendar.DAY_OF_YEAR))== (sesscal.get(Calendar.DAY_OF_YEAR))){
                    
                    int status=UpdateStatusBlocked(sid2);
                   
                }
                            startCal.add(Calendar.DAY_OF_MONTH,1);
            
            }
           }
            else{
                   JOptionPane.showMessageDialog(null,"SESSION ID:"+sid2+" on "+sessionDate+" already have "+availableApp4+" appointments.Please Re-Schedule or Cancel appointments before blocking the Session!","SESSION CANNOT BE BLOCKED",JOptionPane.ERROR_MESSAGE);
               }
            }
        }
         catch (SQLException ex) {
            Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("error in blocked multisession method");}
        }
        

        
        public void reScheduleAppointment(String appRef,int newSid,String source,String docName,Boolean enterApp){
            try {  
                
          //  DefaultTableModel model =(DefaultTableModel) j7.getModel();
            //DefaultTableModel model2 =(DefaultTableModel) j6.getModel();
          //  DefaultTableModel model3 =(DefaultTableModel) j8.getModel();
            
            Connection con = DBconnect.getConnection();
         //   int appRef=Integer.parseInt(model.getValueAt(j7.getSelectedRow(),0).toString());
            
           // int pid=Integer.parseInt(model2.getValueAt(j6.getSelectedRow(),0).toString());
           //int newSid=Integer.parseInt(model3.getValueAt(j8.getSelectedRow(),0).toString());
          // float  fee=Float.parseFloat(model.getValueAt(j7.getSelectedRow(),7).toString());
          
          if(enterApp)   {  
            //APPOINTMENT REMOVING PROCESS 
            String query="select sessionID ,patientID,fee from appointment where apRefNo=?";
            PreparedStatement pst=con.prepareStatement(query);
            pst.setString(1,appRef);
            
            ResultSet rs=pst.executeQuery();
         
            while(rs.next()){
                
            
            int sid=rs.getInt("sessionID");
            int pid=rs.getInt("patientID");
            float fee=rs.getInt("fee");
            
             

                        deleteAppointment(appRef);
                        deleteAvailableApp(sid);
                         String query3="select avilableApp,empNo,action,date,noOfApp from session where sessionID=?";
                            PreparedStatement pst1=con.prepareStatement(query3);
                            pst1.setInt(1,sid);
                            ResultSet rs1=pst1.executeQuery();
                            while(rs1.next()){
                                int totalApp=rs1.getInt("noOfApp");
                                int availableApp=rs1.getInt("avilableApp");
                              //  String empNo=rs1.getString("empNo");
                                if(availableApp<totalApp){
                                    UpdateUnFillAction(sid);
                                
                            
           ////APPOINTMENT RE ENTERING PROCESS
           
              /*  String source = null;
             if(w1.isSelected())
                 source = "Walkin";
            else if(p1.isSelected())
               source = "Phone";
          
           */
                
             
              
              
                int appNo=generateAppNo(newSid);
                String action=rs1.getString("action");
                java.sql.Date dbDate=rs1.getDate("date");
                java.sql.Date today=  new Date(System.currentTimeMillis());
                int x=today.compareTo(dbDate);
                
              
                
                if(action.equals("UnFilled")){
                    
                            String aprxTime=calAprxTime(newSid);
            
                            EnterAppointment(aprxTime, appNo, newSid, pid,fee,source);
                            UpdateAvailableApp(newSid);
                            UpdateCurrentApp(newSid, appNo);
                         //sending a SMS to patient   
                            String query4="select contactNo ,name from patient where patientID=?";
                            PreparedStatement pst4=con.prepareStatement(query4);
                            pst4.setInt(1,pid);
                            
                                rs=pst4.executeQuery();
                                
                                 while(rs.next()){
                                 String contactNo1=rs.getString("contactNo");
                                 String contactNo="+94"+contactNo1.substring(1);
                                 String patName= rs.getString("name");
                             //  String docName=jLabel119.getText();
                            
                                String message="Dear "+patName+" Your Appointment with Dr."+docName+ " is re-Scheduled to AppNo."+appNo+" on "+dbDate+" at "+aprxTime+". Deegayuu-Kuliyapitiya";
                                System.out.println(contactNo+message);
                                alertSMS2(message,contactNo); 
                            
                            //CHECK THIS IF AVAILABLE APPOINTMENTS >= TOTAL APPOINTMENTS ACTION COLUMN IN SESSION TABLE SHOULD BE
                            //UPDATED AS FILLED
                          
                          
                            
                                totalApp=rs1.getInt("noOfApp");
                                availableApp=rs1.getInt("avilableApp");
                                if(availableApp==totalApp){
                                    UpdateFillAction(newSid);
                                    
                                }
                            
                         
                           
                      
                }}
                else{
                    JOptionPane.showMessageDialog(null,"Cannot Re-Schedule the appointment.Session is Full");
                }
            }
           
                            }
        
       //String query2="select a.apRefNo,s.sessionID,d.empNo,d.name,a.appNo,a.time,a.date,a.fee,a.billStatus from appointment a,doctor d,session s where a.sessionID=s.sessionID and s.empNo=d.empNo and a.date >= CURDATE() AND a.patientID = ?";
       // search2(patid,j7,query2);
            }
        } else{
                   JOptionPane.showMessageDialog(null,"Cannot Re-Schedule the appointment.Telephone Bookings are NOT ALLOWED! ","RE-SCHEDULE CANCELED!",JOptionPane.ERROR_MESSAGE);
                                }
            }
              catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, ex.getMessage());
        }
            }
        
        public void removeAppointment(String appRef,String docName){
            try {
        //*    DefaultTableModel model =(DefaultTableModel) jTable17.getModel();
            
            
            Connection con = DBconnect.getConnection();
     //*       String appRef=model.getValueAt(jTable17.getSelectedRow(),0).toString();
           // int sid=Integer.parseInt(model.getValueAt(jTable17.getSelectedRow(),1).toString());
            
            String query="select billStatus ,sessionID ,patientID,date,appNo from appointment where apRefNo=?";
            PreparedStatement pst=con.prepareStatement(query);
            pst.setString(1,appRef);
            
           ResultSet rs=pst.executeQuery();
          
            while(rs.next()){
                
            String bStatus=rs.getString("billStatus");
            int sid=rs.getInt("sessionID");
            int pid=rs.getInt("patientID");
            java.sql.Date dbDate=rs.getDate("date");
            int appNo=rs.getInt("appNo");
            
            String query4="select contactNo ,name from patient where patientID=?";
                            PreparedStatement pst4=con.prepareStatement(query4);
                            pst4.setInt(1,pid);
                            
                                rs=pst4.executeQuery();
                                
                                 while(rs.next()){
                                 String contactNo1=rs.getString("contactNo");
                                 String contactNo="+94"+contactNo1.substring(1);
                                 String patName= rs.getString("name");
            
             if(bStatus.equals("UnBilled")){
                 

                        deleteAppointment(appRef);
                        deleteAvailableApp(sid);
                        
                        //sending a SMS to patient   
                            String message="Dear "+patName+" Your Appointment with Dr."+docName+" AppNo."+appNo+" on "+dbDate+" is canceled- Deegayuu-Kuliyapitiya";
                            System.out.println(contactNo+message);
                            alertSMS(message,contactNo); 
                            
                            String query3="select avilableApp,noOfApp from session where sessionID=?";
                            PreparedStatement pst1=con.prepareStatement(query3);
                            pst1.setInt(1,sid);
                            ResultSet rs1=pst1.executeQuery();
                            while(rs1.next()){
                                int totalApp=rs1.getInt("noOfApp");
                                int availableApp=rs1.getInt("avilableApp");
                                if(availableApp<totalApp){
                                    UpdateUnFillAction(sid);
                                }
                            
                            }}
             else{
                        refund(appRef);
                        deleteAppointment(appRef);
                        deleteAvailableApp(sid);
                        //sending a SMS to patient   
                            String message="Dear "+patName+" Your Appointment with Dr."+docName+ " is Canceled.Please collect your fee. Deegayuu-Kuliyapitiya";
                            System.out.println(contactNo+message);
                            alertSMS(message,contactNo); 
                            
                            String query3="select avilableApp,noOfApp from session where sessionID=?";
                            PreparedStatement pst1=con.prepareStatement(query3);
                            pst1.setInt(1,sid);
                            ResultSet rs1=pst1.executeQuery();
                            while(rs1.next()){
                                int totalApp=rs1.getInt("noOfApp");
                                int availableApp=rs1.getInt("avilableApp");
                                if(availableApp<totalApp){
                                    UpdateUnFillAction(sid);
                                }
                            
                            }
             
                }
            
                 
        
             }}
            
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        }
        
        public int createSession2(java.util.Date dd,java.util.Date sd,java.util.Date ed,int noOfApp,String startT,String endT,String empNo,String TelBooking,javax.swing.JTable jloadingTable){
           int  status=0;
            try {
            //DefaultTableModel model =(DefaultTableModel) jTable7.getModel();
            Connection con = DBconnect.getConnection();
            
            DateFormat df = new SimpleDateFormat("YYYY-MM-DD");
            java.util.Date date=new java.util.Date();
       
            
            java.sql.Date sessionDate=new java.sql.Date(dd.getTime());
            java.sql.Date endDate=new java.sql.Date(ed.getTime());
            java.sql.Date startDate=new java.sql.Date(sd.getTime());
          
            String startTime=startT;
         
            String endTime=endT;
           // LocalTime.parse(endTime).isAfter(LocalTime.parse(startTime));
       
            
            
            String query="insert into session(date,empNo,startDate,endDate,startTime,endTime,noOfApp,TelBooking) values(?,?,?,?,?,?,?,?)";
            PreparedStatement pst2 =con.prepareStatement(query);
            //insert into Session table
            
            pst2.setDate(1,sessionDate);
            pst2.setString(2,empNo);
            pst2.setDate(3,startDate);
            pst2.setDate(4,endDate);
            pst2.setString(5,startTime);
            pst2.setString(6,endTime);
            pst2.setInt(7, noOfApp);
            pst2.setString(8,TelBooking);
            status=pst2.executeUpdate();

                
                
                
                try{
                   
                    String query1="select  s.sessionID,d.empNo,s.date,s.startTime,s.endTime,s.noOfApp,s.avilableApp,s.TelBooking from session s,doctor d where s.empNo=d.empNo AND s.date >= CURDATE() AND d.empNo=? order by s.date";
                    PreparedStatement pst=con.prepareStatement(query1);
                    pst.setString(1,empNo);
                    ResultSet rs=pst.executeQuery();
                    
                    jloadingTable.setModel(DbUtils.resultSetToTableModel(rs));
                    
                }
                catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            
           
           
        } catch (SQLException ex) {
            Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
        }

        return status;
        
        }
        
        public int updateSession(java.util.Date dd, String stime, String etime,int count,String telBooking,int sid,javax.swing.JTable jloadingTable,String empNo){
            int status=0;
        try {
            Connection con = DBconnect.getConnection();
             DateFormat df = new SimpleDateFormat("YYYY-MM-DD");
            java.util.Date date=new java.util.Date();

            java.sql.Date sDate=new java.sql.Date(dd.getTime());
            
            String query = "UPDATE session "
                    + "SET date = ?, startTime = ?, endTime = ?, noOfApp = ?, TelBooking = ?"
                    + "WHERE sessionID = ?";
            PreparedStatement pst2 =con.prepareStatement(query);
            //update Session table
            
            pst2.setDate(1,sDate);
            pst2.setString(2,stime);
            pst2.setString(3,etime);
            pst2.setInt(4,count);
            pst2.setString(5,telBooking);
            pst2.setInt(6,sid);
           
            status=pst2.executeUpdate();
             try{
                   
                    String query1="select  s.sessionID,d.empNo,s.date,s.startTime,s.endTime,s.noOfApp,s.avilableApp,s.TelBooking from session s,doctor d where s.empNo=d.empNo AND s.date >= CURDATE() AND d.empNo=? order by s.date";
                    PreparedStatement pst=con.prepareStatement(query1);
                    pst.setString(1,empNo);
                    ResultSet rs=pst.executeQuery();
                    
                    jloadingTable.setModel(DbUtils.resultSetToTableModel(rs));
                    
                }
                catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            
        } catch (SQLException ex) {
            Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
        }

        return status;
        }
}
    

