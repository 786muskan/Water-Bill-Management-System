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

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

public class Bill implements ActionListener{
    private int bId;
    private int mId;
    private String billingDate;
    private String dueDate;
    private boolean status;
    private String paymentDate;
    private static Statement sObj;
   
    public Bill() {
        sObj=Config.setConnection();
    }
    public Bill(int bId, int mId, String billingDate, String dueDate, boolean status, String paymentDate) {
        this.bId = bId;
        this.mId = mId;
        this.billingDate = billingDate;
        this.dueDate = dueDate;
        this.status = status;
        this.paymentDate = paymentDate;
        sObj=Config.setConnection();
    }

    public void setbId(int bId) {  this.bId = bId; }
    public void setPaymentDate(String paymentDate) {    this.paymentDate = paymentDate; }
    public void setmId(int mId) {    this.mId = mId; }
    public void setStatus(boolean status) {    this.status = status; }
    public void setDueDate(String dueDate) {    this.dueDate = dueDate; }
    public void setBillingDate(String billingDate) {    this.billingDate = billingDate; }
    public void setAllBill( int mId, String billingDate, String dueDate, boolean status, String paymentDate) {
        //this.bId = bId;
        this.mId = mId;
        this.billingDate = billingDate;
        this.dueDate = dueDate;
        this.status = status;
        this.paymentDate = paymentDate;
    }

    public String getDueDate() {    return dueDate;  }
    public int getbId() {    return bId;}
    public int getmId() {     return mId; }
    public String getPaymentDate() {    return paymentDate; }
    public String getBillingDate() {    return billingDate; }
    public boolean isActive() {    return status; }
    public static Statement getsObj() {
        sObj=Config.setConnection();
        return sObj;
    }
    
    public String getDate(){
        String dateString = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
       return dateString;
    }
    public String calDueDate(){
        Date d=new Date();
        d.setDate(d.getDate()+10);
        String dateString = new SimpleDateFormat("yyyy-MM-dd").format(d);
       return dateString;
    }
    
    public void addBill(){
        try {
            String query="insert into bill(`mid`, `billing_date`, `due_date`) values("+getmId()+",'"+getDate()+"','"+calDueDate()+"')";
          // JOptionPane.showMessageDialog(null, query);
            getsObj().executeUpdate(query);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static int[] getDueBill(){
        int []mids=null;
        try {
            
            String query="SELECT bill.mid from bill where  bill.status='Unpaid' AND TIMESTAMPDIFF(month, bill.due_date, CURRENT_DATE)>=3";
            ResultSet rsObj=getsObj().executeQuery(query);
            rsObj.last();
            mids=new int[rsObj.getRow()];
            rsObj.beforeFirst();
            int i=0;
            while (rsObj.next()) {
                mids[i]=rsObj.getInt(1);
            }
        }
         catch (Exception e) {
             e.printStackTrace();
         }
         return mids;
    }
    public void genBill(){
      int arr[]= Meter_Reading.checkMeters();
      if(arr!=null){
            for(int i=0;i<arr.length;i++){
                setmId(arr[i]);
                addBill();
            }
      }
    }
    public int[] getMonths(String cin){
       int months[]=null;
      
        try {
           ResultSet rsObj=getsObj().executeQuery("Select reading_date from meter_reading where mid = "+getmId());
           rsObj.last();
           months=new int[rsObj.getRow()];
           rsObj.beforeFirst(); 
           int i=0;
           int temp=0;
           while (rsObj.next()) {
            temp=new SimpleDateFormat("yyyy-MM-dd").parse(rsObj.getString(1)).getMonth();
                if(temp==0){
                    months[i++]= 12;
                }
                else{
                    months[i++]=temp;
                }
            //months[i++]= new SimpleDateFormat("yyyy-MM-dd").parse(rsObj.getString(1)).getMonth()+1;
                 
        }
       }
        catch (Exception e) {
            e.printStackTrace();
        }
        return  months;
    } 
    //select bill.billing_date from bill where bill.status='Paid' and bill.mid=(SELECT meter.mid from meter where meter.ci_number = '123456789009');

    public int[] getMonthNumber(String cin,String status){
        int months[]=null;
       
         try {
            ResultSet rsObj=getsObj().executeQuery("select bill.billing_date from bill where bill.status='"+status+"' and bill.mid=(SELECT meter.mid from meter where meter.ci_number = '"+cin+"')");
            rsObj.last();
            months=new int[rsObj.getRow()];
            rsObj.beforeFirst(); 
            int i=0;
            int temp=0;
            while (rsObj.next()) {
                temp=new SimpleDateFormat("yyyy-MM-dd").parse(rsObj.getString(1)).getMonth();
                if(temp==0){
                    months[i++]= 12;
                }
                else{
                    months[i++]=temp;
                }
                  
         }
        }
         catch (Exception e) {
             e.printStackTrace();
         }
         return  months;
     } 


    public boolean search(String cin){
        boolean ans=false;
        try {
            ResultSet rsObj=getsObj().executeQuery("Select mid from meter where ci_number = "+cin);
            if(rsObj.next()){
                setmId(rsObj.getInt(1));
                ans=true;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ans;
    }
    public void searchAll(String date,String cin){
        //boolean ans=false;
        try {
            ResultSet rsObj=getsObj().executeQuery("SELECT * FROM `bill` WHERE bill.`billing_date`= '"+date+"' AND bill.mid=(select meter.mid from meter where meter.ci_number = '"+cin+"')");
            if(rsObj.next()){
                setAllBill(rsObj.getInt(2), rsObj.getString(3),rsObj.getString(4),rsObj.getString(5).equals("Paid")?true:false, rsObj.getString(6));
               //// setDueDate(rsObj.getString(4));
               // setPaymentDate(rsObj.getString(6));
                //ans=true;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        //return ans;
    }

    public void payment(){
        //boolean ans=false;
        String query="UPDATE `bill` SET `status`='Paid',`payment_date`='"+this.getDate()+"' WHERE `mid`= "+this.getmId()+" AND `billing_date` ='"+this.getBillingDate()+"'";
       //JOptionPane.showMessageDialog(null, query);
        try {
            getsObj().executeUpdate(query);
            JOptionPane.showMessageDialog(null,"Bill Paid");
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        //return ans;
    }


    
    public  Meter_Reading setReadings(String cin,int month){
        Meter_Reading mrObj=new Meter_Reading();
        try {
            String query="SELECT meter_reading.reading_date,meter_reading.previous_reading,meter_reading.current_reading,meter_reading.mid from meter_reading where MONTH( meter_reading.reading_date) ="+(month+1)+" and meter_reading.mid=(SELECT meter.mid from meter where meter.ci_number='"+cin+"')";
            ResultSet rsObj=getsObj().executeQuery(query);
            if(rsObj.next()){
                mrObj.setmId(rsObj.getInt(4));
                mrObj.setPreviousReading(rsObj.getLong(2));
                mrObj.setCurrentReading(rsObj.getLong(3));
                mrObj.setReadingDate(rsObj.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mrObj;
    }

    //Dialog box
    JDialog searchBox,searchBoxR,searchBoxPay;
    JLabel msg,lbMonth;
    JTextField txtCIN;
    Choice chMonth;
    JButton btnSearchC,btnCancel,btnSearch,btnReceipt,btnSearchR,btnSearchP,btnPay;
    public  void searchDialog(String op){
        searchBox=new JDialog();
        searchBoxR=new JDialog();
        
        msg=new JLabel("Enter Customer Identification num : ");       
        msg.setFont(new Font("Dubai",Font.BOLD,24));
        msg.setBounds(10,10,400,100);
        
        txtCIN=new JTextField();
        txtCIN.setFont(new Font("Dubai",Font.BOLD,22));
        txtCIN.setBounds(400,35,150,50);
        
        
        lbMonth=new JLabel("Select Month : ");       
        lbMonth.setFont(new Font("Dubai",Font.BOLD,24));
        lbMonth.setBounds(100,100,400,100);

        chMonth=new Choice();
        //function
        chMonth.setFont(new Font("Dubai",Font.BOLD,22));
        chMonth.setBounds(400,125,150,50);
        
        btnSearchC=new JButton("Search");
        btnSearchC.setBounds(150,150,100,50);
        btnSearchC.setBackground(Color.black);
        btnSearchC.setForeground(Color.white);
        btnSearchC.addActionListener(this);
        
        
        btnSearch=new JButton("Search");
        btnSearch.setBounds(150,200,100,50);
        btnSearch.setBackground(Color.black);
        btnSearch.setForeground(Color.white);
        btnSearch.addActionListener(this);
        
        
        btnReceipt=new JButton("Print");
        btnReceipt.setBounds(150,200,100,50);
        btnReceipt.setBackground(Color.black);
        btnReceipt.setForeground(Color.white);
        btnReceipt.addActionListener(this);
        
        
        btnCancel=new JButton("Cancel");
        btnCancel.setBounds(350,150,100,50);
        btnCancel.setBackground(Color.black);
        btnCancel.setForeground(Color.white);
        btnCancel.addActionListener(this);
        
        
        btnSearchR=new JButton("Search");
        btnSearchR.setBounds(150,150,100,50);
        btnSearchR.setBackground(Color.black);
        btnSearchR.setForeground(Color.white);
        btnSearchR.addActionListener(this);
        
        btnSearchP=new JButton("Search");
        btnSearchP.setBounds(150,150,100,50);
        btnSearchP.setBackground(Color.black);
        btnSearchP.setForeground(Color.white);
        btnSearchP.addActionListener(this);
        

        
        btnPay=new JButton("Bill");
        btnPay.setBounds(150,200,100,50);
        btnPay.setBackground(Color.black);
        btnPay.setForeground(Color.white);
        btnPay.addActionListener(this);
        
        
        // searchBox2.add(msg);
        // searchBox2.add(btnCancel);
        // searchBox2.add(txtCIN);
        // searchBox2.add(btnSearchR);
        
        
        // searchBox.add(msg);
        // searchBox.add(btnCancel);
        // searchBox.add(txtCIN);
        // //searchBox.add(lbMonth);
        // searchBox.add(btnSearchC);
        
        
        
        searchBox.setBackground(Color.BLACK);
        searchBox.setBounds(600,250,600,300);
        searchBox.setLayout(null);
        
        searchBoxR.setBackground(Color.BLACK);
        searchBoxR.setBounds(600,250,600,300);
        searchBoxR.setLayout(null);

        searchBoxPay=new JDialog();
        searchBoxPay.setBackground(Color.BLACK);
        searchBoxPay.setBounds(600,250,600,300);
        searchBoxPay.setLayout(null);
        if(op.charAt(0)=='s'){
            
            searchBox.add(msg);
            searchBox.add(btnCancel);
            searchBox.add(txtCIN);
        //searchBox.add(lbMonth);
            searchBox.add(btnSearchC);
            searchBox.setVisible(true);
        }
        else if(op.charAt(0)=='r'){
            searchBoxR.add(msg);
            searchBoxR.add(btnCancel);
            searchBoxR.add(txtCIN);
            searchBoxR.add(btnSearchR);
            searchBoxR.setVisible(true);
        }
        else{
            searchBoxPay.add(msg);
            searchBoxPay.add(btnCancel);
            searchBoxPay.add(txtCIN);
            searchBoxPay.add(btnSearchP);
            searchBoxPay.setVisible(true);
        }
    }

    
    
    public void actionPerformed(java.awt.event.ActionEvent e) {
            if(e.getSource()==btnCancel){
                searchBox.dispose();
                searchBoxR.dispose();
                searchBoxPay.dispose();
            }
            //
            else if(e.getSource()==btnSearchC){
                if(txtCIN.getText().trim().isEmpty()){; 
                    JOptionPane.showMessageDialog(null,"You must enter a value"); 
                    
                }
                else if(this.isNumeric(txtCIN.getText().trim())){
                    //searchBox.dispose();
                    searchBox.setVisible(false);
                    if(this.search(txtCIN.getText().trim())){
                       // new Search_Meter(this,"s");
                      int mon[]= getMonths(txtCIN.getText().trim());
                      if(mon.length!=0){
                          for(int i=0;i<mon.length;i++){
                            chMonth.add(String.valueOf(mon[i]));
                        }
                        searchBox.add(chMonth);
                        searchBox.add(lbMonth);
                        txtCIN.setEditable(false);
                        btnCancel.setLocation(350,200);
                        btnSearchC.setLocation(150,200);;
                        searchBox.setSize(600,400);
                        searchBox.remove(btnSearchC);
                        searchBox.add(btnSearch);
                        searchBox.setVisible(true);
                      }
                      else{
                        JOptionPane.showMessageDialog(null, "There is no bill .");
                      }
                    }
                    else{   JOptionPane.showMessageDialog(null,"No Match Found"); }
                    
                }
                else {
                   
                    JOptionPane.showMessageDialog(null,"You must enter 12 digit number");  
                }
            }
            else if(e.getSource()==btnSearch){
              
              searchBox.dispose();
                Meter_Reading mrObj=  setReadings(txtCIN.getText().trim(),Integer.parseInt(chMonth.getSelectedItem()));
              searchAll(mrObj.getReadingDate(),txtCIN.getText().trim()); 
              Meter mObj=new Meter();
               boolean m1= mObj.search(txtCIN.getText().trim());
               String data[]=mObj.getCName(txtCIN.getText().trim());
               Consumer cObj=new Consumer();
                cObj. searchByID(mObj.getcId());
                new Search_Bill(cObj, mObj, mrObj, data,Integer.parseInt(chMonth.getSelectedItem()),this,"s");
            }
            else if(e.getSource()==btnSearchR){
                if(txtCIN.getText().trim().isEmpty()){; 
                    JOptionPane.showMessageDialog(null,"You must enter a value"); 
                    
                }
                else if(this.isNumeric(txtCIN.getText().trim())){
                    //searchBox.dispose();
                    searchBoxR.setVisible(false);
                    if(this.search(txtCIN.getText().trim())){
                       
                      int mon[]= getMonthNumber(txtCIN.getText().trim(),"Paid");
                      if(mon.length!=0){
                          for(int i=0;i<mon.length;i++){
                            chMonth.add(String.valueOf(mon[i]));
                            }
                    
                        searchBoxR.add(chMonth);
                        searchBoxR.add(lbMonth);
                        txtCIN.setEditable(false);
                        btnCancel.setLocation(350,200);
                        btnSearchR.setLocation(150,200);;
                        searchBoxR.setSize(600,400);
                        searchBoxR.remove(btnSearchR);
                        searchBoxR.add(btnReceipt);
                        searchBoxR.setVisible(true);
                      }
                      else{
                        JOptionPane.showMessageDialog(null, "There is no paid bill receipt.");
                      }
                      
                    }
                    else{   JOptionPane.showMessageDialog(null,"No Match Found"); }
                    
                }
                else {
                   
                    JOptionPane.showMessageDialog(null,"You must enter 12 digit number");  
                }
            }
            else if(e.getSource()==btnReceipt){
                searchBoxR.dispose();
                Meter_Reading mrObj=  setReadings(txtCIN.getText().trim(),Integer.parseInt(chMonth.getSelectedItem())); 
                searchAll(mrObj.getReadingDate(),txtCIN.getText().trim()); 
                // if(getPaymentDate()==null){
                //     JOptionPane.showMessageDialog(null,"Bill is not Paid");
                // }
                Meter mObj=new Meter();
                boolean m1= mObj.search(txtCIN.getText().trim());
                String data[]=mObj.getCName(txtCIN.getText().trim());

                new Receipt(mrObj,mObj,data,this);
            }
            else if(e.getSource()==btnSearchP){
                if(txtCIN.getText().trim().isEmpty()){; 
                    JOptionPane.showMessageDialog(null,"You must enter a value"); 
                    
                }
                else if(this.isNumeric(txtCIN.getText().trim())){
                    
                    searchBoxPay.setVisible(false);
                    if(this.search(txtCIN.getText().trim())){
                      
                      int mon[]= getMonthNumber(txtCIN.getText().trim(),"Unpaid");
                      if(mon.length!=0){
                          for(int i=0;i<mon.length;i++){
                            chMonth.add(String.valueOf(mon[i]));
                        }
                        searchBoxPay.add(chMonth);
                        searchBoxPay.add(lbMonth);
                        txtCIN.setEditable(false);
                        btnCancel.setLocation(350,200);
                        btnSearchP.setLocation(150,200);;
                        searchBoxPay.setSize(600,400);
                        searchBoxPay.remove(btnSearchP);
                        searchBoxPay.add(btnPay);
                        searchBoxPay.setVisible(true);
                      }
                      else{
                        JOptionPane.showMessageDialog(null, "There is no Unpaid bill .");
                      }
                    }
                    else{   JOptionPane.showMessageDialog(null,"No Match Found"); }
                    
                }
                else {
                   
                    JOptionPane.showMessageDialog(null,"You must enter 12 digit number");  
                }
            }
            else if(e.getSource()==btnPay){
              searchBoxPay.dispose();
                Meter_Reading mrObj=  setReadings(txtCIN.getText().trim(),Integer.parseInt(chMonth.getSelectedItem()));
                Meter mObj=new Meter();
                boolean m1= mObj.search(txtCIN.getText().trim());
                searchAll(mrObj.getReadingDate(),txtCIN.getText().trim()); 
               String data[]=mObj.getCName(txtCIN.getText().trim());
               Consumer cObj=new Consumer();
                cObj. searchByID(mObj.getcId());
                
                new Search_Bill(cObj, mObj, mrObj, data,Integer.parseInt(chMonth.getSelectedItem()),this,"p");
            }

           
        }
        



    //validation
    //number only
    public boolean isNumeric(String cin){
        return cin.matches("^[0-9]+$");
    }

}
