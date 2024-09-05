package waterbill;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

public class Meter implements ActionListener{
    private int mId;
    private int cId;
    private int meterType;
    private String installationDate;
    private String cINumber;
    private boolean active;
    private static Statement sObj;
    
    public Meter() {
        sObj=Config.setConnection();
    }
    
    public Meter(int mId, int cId, int meterType, String installationDate, String cINumber, boolean active) {
        this.mId = mId;
        this.cId = cId;
        this.meterType = meterType;
        this.installationDate = installationDate;
        this.cINumber = cINumber;
        this.active = active;
        sObj=Config.setConnection();
    }

    public void setMId(int mId) {   this.mId = mId;  }
    public void setActive(boolean active) {    this.active = active; }
    public void setcId(int cId) {   this.cId = cId; } 
    public void setcINumber(String cINumber) {   this.cINumber = cINumber; }
    public void setInstallationDate(String installationDate) { this.installationDate = installationDate; }
    public void setMeterType(int meterType) {   this.meterType = meterType; }
    public void setAllMeter( int cId, int meterType, String installationDate, String cINumber) {
       // this.mId = mId;
        this.cId = cId;
        this.meterType = meterType;
        this.installationDate = installationDate;
        this.cINumber = cINumber;
       // this.active = active;
    }

    public int getcId() {   return cId;}
    public int getMeterType() { return meterType; }
    public String getInstallationDate() {  return installationDate; }
    public int getMId() {  return mId;}
    public String getcINumber() {  return cINumber; }
    public boolean isActive() {   return active; }
    public static Statement getsObj() {  sObj=Config.setConnection();  return sObj; }
    public static String getDate(){
        String dateString = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
       return dateString;
    }
   
    //genrate cin
    public  void genCINumber(){
        long max = 9;
	    long min = 0;
        long range = max - min + 1;
        int i=1;
        String number="";
       while(isValid(number)==false){
            while(i<=12)    {
                long num = (long)(Math.random() * range) + min;
                number+=String.valueOf(num);
                i++;
            }
       }
       setcINumber(number);    
    }

    //  search cid
    public int searchMeterCID(String adh){
        try {
            String query="Select cid from consumer where caadharno="+adh;
            ResultSet rsObj=sObj.executeQuery(query);
            if(rsObj!=null && rsObj.next()){
                //setAllConsumer(rsObj.getInt(1),rsObj.getString(2),rsObj.getString(3), rsObj.getString(4), rsObj.getString(5),rsObj.getString(6));
                setcId(rsObj.getInt(1));
            }
            else{
                return 0;
            }
            
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return getcId();
    }

    //insert meter
    public void addMeter(){
        String query="insert into meter(cid,meter_type,installation_date,ci_number) values("+getcId()+","+getMeterType()+",'"+getDate()+"','"+getcINumber()+"')";
        try {
            //JOptionPane.showMessageDialog(null,  query);
            getsObj().executeUpdate(query);
           // MainWindow.dialogBox("Meter Inserted");
            JOptionPane.showMessageDialog(null, "Meter Inserted");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //meter type name by id
    public void setMeterTypeQ(String name){
        try {
            String query="Select mtid from meter_type where mtname = '"+name+"'";
            //JOptionPane.showMessageDialog(null,  query);
            ResultSet rsObj= getsObj().executeQuery(query);
            if(rsObj.next()){
                setMeterType(rsObj.getInt(1));
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
    //display meter types
    public static String[] showMeterType(){
        String data[]=null;
        try {
          ResultSet rsObj= getsObj().executeQuery("Select mtname from meter_type");
          int i=0;
          rsObj.last();
          data=new String[rsObj.getRow()];
          rsObj.beforeFirst();
          while(rsObj.next()){
            data[i++]=rsObj.getString(1);
          }
      } catch (Exception e) {
        e.printStackTrace();
      }
      return data;
    }
    
    public static void closeMeter(int id){
        try {
            getsObj().executeUpdate("Update meter set Status='Not Active' where mid = "+id);    
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 

    //sub search query for cname and type
    public String[] getCName(String cin){
        String nt[]=new String[3];
        try {
            String query="SELECT  meter_type.mtbase_price,consumer.cname ,meter_type.mtname from meter join consumer on consumer.cid=meter.cid  JOIN meter_type on meter_type.mtid=meter.meter_type where meter.ci_number = '"+cin+"'";
            ResultSet rsObj=sObj.executeQuery(query);
            if(rsObj.next()){
                nt[0]=rsObj.getString(2);
                nt[1]=rsObj.getString(3);
                nt[2]=rsObj.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nt;

    }
    //Search main
    public boolean search(String cin){
        boolean ans=false;
        try{
            String query="Select * from meter where ci_number="+cin;
            ResultSet rsObj=sObj.executeQuery(query);
            if(rsObj!=null && rsObj.next()){
               
                setAllMeter(rsObj.getInt(2),rsObj.getInt(3),rsObj.getString(4), rsObj.getString(5));
                if(rsObj.getString(6).equals("Active")){
                    setActive(true);
                }
                setMId(rsObj.getInt(1));
                
                ans= true;
            }
            else{
                ans= false;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return ans;
    }
    //update status
    public void updateStatus(){
        String query="update meter set Status = 'Not Active' where ci_number = "+this.getcINumber();
        try {
          // JOptionPane.showMessageDialog(null,  query);
            getsObj().executeUpdate(query);
            
            //MainWindow.dialogBox("Meter Connection Closed");
            JOptionPane.showMessageDialog(null, "Meter Connection Closed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Dialog box
    JDialog searchBox;
    JLabel msg,errorMsg;
    JTextField txtCIN;
    JButton btnSearchC,btnCancel,btnUpdateC;
    public  void searchDialog(String op){
        searchBox=new JDialog();
        msg=new JLabel("Enter Customer Identification num : ");
                
        msg.setFont(new Font("Dubai",Font.BOLD,24));
        msg.setBounds(10,10,400,100);

        txtCIN=new JTextField();
        txtCIN.setFont(new Font("Dubai",Font.BOLD,22));
        txtCIN.setBounds(400,35,150,50);

        errorMsg=new JLabel("Hello");
        errorMsg.setBounds(450,300,100,50);

        btnSearchC=new JButton("Search");
        btnSearchC.setBounds(150,150,100,50);
        btnSearchC.setBackground(Color.black);
        btnSearchC.setForeground(Color.white);
        btnSearchC.addActionListener(this);

        btnUpdateC=new JButton("Show");
        btnUpdateC.setBounds(150,150,100,50);
        btnUpdateC.setBackground(Color.black);
        btnUpdateC.setForeground(Color.white);
        btnUpdateC.addActionListener(this);

        btnCancel=new JButton("Cancel");
        btnCancel.setBounds(350,150,100,50);
        btnCancel.setBackground(Color.black);
        btnCancel.setForeground(Color.white);
        btnCancel.addActionListener(this);

        searchBox.add(msg);
        searchBox.add(btnCancel);
        searchBox.add(txtCIN);
        searchBox.add(errorMsg);

        if (op.charAt(0)=='s') {
            searchBox.add(btnSearchC);
        }
        
        else{
            searchBox.add(btnUpdateC);
        }

        searchBox.setBackground(Color.BLACK);
        searchBox.setBounds(600,350,600,300);
        searchBox.setLayout(null);
        searchBox.setVisible(true);
    }

    
    
    public void actionPerformed(java.awt.event.ActionEvent e) {
            if(e.getSource()==btnCancel){
                searchBox.dispose();
            }
            else if(e.getSource()==btnSearchC){
                if(txtCIN.getText().trim().isEmpty()){; 
                    JOptionPane.showMessageDialog(null,"You must enter a value"); 
                   //MainWindow.dialogBox("You must enter a value"); 
                }
                else if(this.isNumeric(txtCIN.getText().trim())){
                    searchBox.dispose();
                    if(this.search(txtCIN.getText().trim())){
                        new Search_Meter(this,"s");
                    }
                    else{   
                        JOptionPane.showMessageDialog(null,"No Match Found"); 
                       // MainWindow.dialogBox("No Match Found");
                    }
                    
                }
                else {
                    errorMsg.setText("You must enter 12 digit number");
                    errorMsg.setForeground(Color.RED); 
                    //MainWindow.dialogBox("You must enter 12 digit number");
                    JOptionPane.showMessageDialog(null,"You must enter 12 digit number");  
                }
            }
            else if(e.getSource()==btnUpdateC){
                if(txtCIN.getText().trim().isEmpty()){
                    JOptionPane.showMessageDialog(null,"You must enter a value"); 
                   //MainWindow.dialogBox("You must enter a value"); 
                }
                else if(this.isNumeric(txtCIN.getText().trim())){
                    searchBox.dispose();
                    if(this.search(txtCIN.getText().trim())){
                        new Search_Meter(this,"u");
                    }
                    else{   
                        JOptionPane.showMessageDialog(null,"No Match Found"); 
                        //MainWindow.dialogBox("No Match Found"); 
                    }
                    
                    }
                    else {
                        errorMsg.setText("You must enter 12 digit number");
                        errorMsg.setForeground(Color.RED); 
                       // MainWindow.dialogBox("You must enter 12 digit number"); 
                        JOptionPane.showMessageDialog(null,"You must enter 12 digit number");  
                    }
                }
        }
        



    //validation
    //number only
    public boolean isNumeric(String cin){
        return cin.matches("^[0-9]+$");
    }

    // duplicacy check
    public boolean isValid(String num){
        try {
         ResultSet rsObj=  getsObj().executeQuery("Select ci_number from meter");
         if(num.isEmpty()){  return false;}
         while (rsObj.next()) {
             if(rsObj.getString(1).equals(num)){ return false;}
         }
 
        } catch (Exception e) {
          e.printStackTrace();
        } 
        return true;
     }

    
}
