 package waterbill;

import java.sql.Statement;
import java.util.Vector;
import java.util.regex.Pattern;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.Cursor;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;



public class Consumer implements ActionListener,MouseListener {
    private int cId;
    private String cName;
    private String cAddress;
    private String cEmail;
    private String cPhone;
    private String cAadharNo;
    
    private  static Statement sObj;
    
    public Consumer() {
        sObj=Config.setConnection();
    }
    public Consumer(int cId, String cName, String cAddress, String cPhone,String cEmail,String cAadharNo) {
        this.cId = cId;
        this.cName = cName;
        this.cAddress = cAddress;
        this.cEmail = cEmail;
        this.cPhone=cPhone;
        this.cAadharNo=cAadharNo;
        sObj=Config.setConnection();
    }
    
    public void setCEmail(String cEmail) {     this.cEmail = cEmail;     }
    public void setCAddress(String cAddress) {   this.cAddress = cAddress; }
    public void setCId(int cId) { this.cId = cId; }
    public void setCName(String cName) {  this.cName = cName; }
    public void setCPhone(String cPhone) { this.cPhone = cPhone;}
    //public void setsObj(Statement sObj) {    this.sObj = sObj;}
    public void setcAadharNo(String cAadharNo) {   this.cAadharNo = cAadharNo;  }
    public void setAllConsumer( int cId,String cName, String cAddress, String cPhone,String cEmail,String cAadharNo) {
        this.cId = cId;
        this.cName = cName;
        this.cAddress = cAddress;
        this.cEmail = cEmail;
        this.cPhone=cPhone;
        this.cAadharNo=cAadharNo;
    }
    
    public int getCId() {return cId; }
    public String getCAddress() {      return cAddress;  }
    public String getCName() {return cName; }
    public String getCEmail() {     return cEmail;   }
    public String getCPhone() {return cPhone;  }
    public static Statement getsObj() {   sObj=Config.setConnection(); return sObj; }
    public String getCAadharNo() {  return cAadharNo; }
    
    //add
    public void addData(){
        String query="insert into consumer() values(null,'"+getCName()+"','"+getCAddress()+"','"+getCPhone()+"','"+getCEmail()+"','"+getCAadharNo()+"')";
        try {
            getsObj().executeUpdate(query);
           // MainWindow.dialogBox("Consumer Inserted")
;            JOptionPane.showMessageDialog(null, "Consumer Inserted");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //search
    public boolean searchOne(String adh){
        boolean ans=false;
        try{
            String query="Select * from consumer where caadharno="+adh;
            ResultSet rsObj=sObj.executeQuery(query);
            if(rsObj!=null && rsObj.next()){
                setAllConsumer(rsObj.getInt(1),rsObj.getString(2),rsObj.getString(3), rsObj.getString(4), rsObj.getString(5),rsObj.getString(6));
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

    //search
    public void searchByID(int id){
        //boolean ans=false;
        try{
            String query="Select * from consumer where cid="+id;
            ResultSet rsObj=sObj.executeQuery(query);
            if(rsObj!=null && rsObj.next()){
                setAllConsumer(rsObj.getInt(1),rsObj.getString(2),rsObj.getString(3), rsObj.getString(4), rsObj.getString(5),rsObj.getString(6));
               // ans= true;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        //return ans;
    }
    
    //update
    public void updateConsumer(){
        String query="update consumer set cname = '"+getCName()+"',caddress = '"+getCAddress()+"',cphone='"+getCPhone()+"',cemail='"+getCEmail()+"' where caadharno='"+getCAadharNo()+"'";
        try {
            getsObj().executeUpdate(query);
            //MainWindow.dialogBox("Consumer Updated");
            JOptionPane.showMessageDialog(null, "Consumer Updated");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    //show
    public static void showAll(){
        try{
            //String data="";
            Vector<String> columns=new Vector<>();
            Vector<Vector<String>> consumerData = new Vector<>();

            ResultSet rsObj=getsObj().executeQuery("Select * from consumer");
            if(rsObj!=null){
                ResultSetMetaData rsmdObj=rsObj.getMetaData();
                for(int i=1;i<=rsmdObj.getColumnCount();i++){
                    columns.addElement(rsmdObj.getColumnName(i));
                }
                int k=0;
                while(rsObj.next()){
                    consumerData.addElement(new Vector<>());

                    for(int i=1;i<=rsmdObj.getColumnCount();i++){
                        consumerData.get(k).addElement(rsObj.getString(i));
                    }
                    k++;
                }
                // JOptionPane.showMessageDialog(null,columns);
                // JOptionPane.showMessageDialog(null,consumerData);
                new Show_Consumer(columns,consumerData);
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    JDialog searchBox;
    JLabel msg,errorMsg;
    JTextField txtAddh;
    JButton btnSearchC,btnMeter,btnCancel,btnUpdateC;
    Cursor hand;
    public  void searchDialog(String op){
        hand=new Cursor(Cursor.HAND_CURSOR);
        searchBox=new JDialog();
        msg=new JLabel("Enter Your Aadhr-card number : ");
                
        msg.setFont(new Font("Dubai",Font.BOLD,24));
        msg.setBounds(10,10,350,100);

        txtAddh=new JTextField();
        txtAddh.setFont(new Font("Dubai",Font.BOLD,22));
        txtAddh.setBounds(400,35,150,50);

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

        btnMeter=new JButton("Select");
        btnMeter.setBounds(150,150,100,50);
        btnMeter.setBackground(Color.black);
        btnMeter.setForeground(Color.white);
        btnMeter.addActionListener(this);

        btnCancel=new JButton("Cancel");
        btnCancel.setBounds(350,150,100,50);
        btnCancel.setBackground(Color.black);
        btnCancel.setForeground(Color.white);
        btnCancel.addActionListener(this);

        searchBox.add(msg);
        searchBox.add(btnCancel);
        searchBox.add(txtAddh);
        searchBox.add(errorMsg);

        if (op.charAt(0)=='s') {
            
            searchBox.add(btnSearchC);
        }
        else if(op.charAt(0)=='m'){
            searchBox.add(btnMeter);
        }
        else{
            searchBox.add(btnUpdateC);
        }

        searchBox.setBackground(Color.BLACK);
        searchBox.setBounds(600,350,600,300);
        searchBox.setLayout(null);
        searchBox.setVisible(true);
    }

    
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnCancel){
            searchBox.dispose();
        }
        else if(e.getSource()==btnSearchC){
            if(txtAddh.getText().trim().isEmpty()){
                errorMsg.setText("You must enter a value");
                errorMsg.setForeground(Color.RED); 
                //MainWindow.dialogBox("You must enter a value");
                JOptionPane.showMessageDialog(null,"You must enter a value"); 
                
            }
            else if(this.isValidAadhar(txtAddh.getText().trim())){
                searchBox.dispose();
                Boolean ans=this.searchOne(txtAddh.getText().trim());
                if(ans){
                    //dispose();
                    new Search_Consumer(this);
                }
                else{
                  //  MainWindow.dialogBox("No Record Found");
                    JOptionPane.showMessageDialog(null,"No Record Found");
                }
            }
            else {
                errorMsg.setText("You must enter 12 digit number");
                errorMsg.setForeground(Color.RED); 
              //  MainWindow.dialogBox("You must enter 12 digit number");
                JOptionPane.showMessageDialog(null,"You must enter 12 digit number");  
            }
        }
        else if(e.getSource()==btnUpdateC){
            if(txtAddh.getText().trim().isEmpty()){
                errorMsg.setText("You must enter a value");
                errorMsg.setForeground(Color.RED); 
              //  MainWindow.dialogBox("You must enter a value");
                JOptionPane.showMessageDialog(null,"You must enter a value"); 
                
            }
            else if(this.isValidAadhar(txtAddh.getText().trim())){
                searchBox.dispose();
                Boolean ans=this.searchOne(txtAddh.getText().trim());
                if(ans){
                    //dispose();
                    new Update_Consumer(this);
                }
                else{
                    //MainWindow.dialogBox("No Record Found");
                    JOptionPane.showMessageDialog(null,"No Record Found");
                }
            }
            else {
                errorMsg.setText("You must enter 12 digit number");
                errorMsg.setForeground(Color.RED); 
                //MainWindow.dialogBox("You must enter 12 digit number");
                JOptionPane.showMessageDialog(null,"You must enter 12 digit number");  
            }
        }
        else if(e.getSource()==btnMeter){
            if(txtAddh.getText().trim().isEmpty()){ 
              //MainWindow.dialogBox("You must enter a value");
                  JOptionPane.showMessageDialog(null,"You must enter a value"); 
                
            }
            else if(this.isValidAadhar(txtAddh.getText().trim())){
                searchBox.dispose();
                
                int id=new Meter().searchMeterCID(txtAddh.getText().trim());
                if(id!=0){
                    //dispose();
                    new Add_Meter(id);
                }
                else{
                    
                    JOptionPane.showMessageDialog(null,"You are not our Customer.\nWe are redirecting you to our \"Add Consumer Form\" so you can get your meter...");
                    new Add_Consumer();
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

     public void mouseEntered(MouseEvent e) {
        if(e.getSource()==btnCancel){
            btnCancel.setCursor(hand);
        }
        else if(e.getSource()==btnSearchC){
            btnSearchC.setCursor(hand);
        }
        else if(e.getSource()==btnMeter){
            btnMeter.setCursor(hand);
        }
        else if(e.getSource()==btnUpdateC){
            btnUpdateC.setCursor(hand);
        }
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }
    public void mouseReleased(MouseEvent e) {
    }
    public void mouseExited(MouseEvent e) {
        if(e.getSource()==btnCancel){
            btnCancel.setCursor(new Cursor(1));
        }
        else if(e.getSource()==btnSearchC){
            btnSearchC.setCursor(new Cursor(1));
        }
        else if(e.getSource()==btnMeter){
            btnMeter.setCursor(new Cursor(1));
        }
        else if(e.getSource()==btnUpdateC){
            btnUpdateC.setCursor(new Cursor(1));
        }
    }


  //validations
  
    public boolean isValidAadhar(String aadhar){
        String regexAadhaar = "^[2-9]{1}[0-9]{11}$";
        Pattern pt=Pattern.compile(regexAadhaar);
        if(aadhar==null)     return false;
        return pt.matcher(aadhar).matches();
    }
    
    public boolean isDuplicate(String aadhar){
        boolean ans=false;
        try {
            ResultSet rsObj=getsObj().executeQuery("Select caadharno from consumer");
            while(rsObj.next()){
                if(rsObj.getString(1).equals(aadhar)){
                  // MainWindow.dialogBox("You are already our Customer.\nWe are redirecting you to Our \"add meter form\" for your new Meter connection");
                     JOptionPane.showMessageDialog(null, "You are already our Customer.\nWe are redirecting you to Our \"add meter form\" for your new Meter connection");   
                    ans=true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ans;
    }
    
    public boolean isValidEmail(String email){
        String regexEmail="^[a-zA-Z]{1}[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pt=Pattern.compile(regexEmail);
        if(email==null)     return false;
        return pt.matcher(email).matches();
    }
     
    public boolean isValidPhone(String phone){
        String regexPhone="^[6789]\\d{9}$";
        Pattern pt=Pattern.compile(regexPhone);
        if(phone==null)     return false;
        return pt.matcher(phone).matches();
    }
    public boolean isValidName(String name){
        String regexName = "^[a-zA-Z]+([\\s][a-zA-Z]+)*$";
        Pattern pt=Pattern.compile(regexName);
        if(name==null)     return false;
        return pt.matcher(name).matches();
    }
    

}
