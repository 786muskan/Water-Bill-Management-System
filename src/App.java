import waterbill.MainWindow;
import waterbill.Meter;
import waterbill.Meter_Reading;
import waterbill.Bill;
import waterbill.Config;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class App {
    public static void main(String[] args) throws Exception {
        
        autoBill();
        closeMeter();
       new MainWindow();
    }
    
    public static void closeMeter(){
        if(new Date().getDate()==10){
            Statement sObj=Config.setConnection();
          
            try {
               int []mid= Bill.getDueBill();
               for(int i=0;i<mid.length;i++){
                    Meter.closeMeter(mid[i]);
               }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void autoBill(){
        if((new Date()).getDate()==1){
            Statement sObj=Config.setConnection();
            try {
                String dateString = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                ResultSet rsObj=sObj.executeQuery("Select reading_date from meter_reading");
                rsObj.last();
                int i=rsObj.getRow();
                if(i==0){
                    new Meter_Reading().addingReadings();
                    new Bill().genBill();   
                }
                else{
                    rsObj.beforeFirst();
                    while(rsObj.next()){
                        if(!(rsObj.getString(1).equals(dateString))){
                             new Meter_Reading().addingReadings();
                             new Bill().genBill();
                             break;     
                        }
                        else{    break;}
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();}
        }
    }
}