package waterbill;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Meter_Reading {
    private int mRId;
    private int mId;
    private String readingDate;
    private long previousReading;
    private long currentReading;
    private static Statement sObj;

    public Meter_Reading() {
        sObj=Config.setConnection();
     }
    public Meter_Reading(int mRId, int mId, String readingDate, long previousReading, long currentReading) {
        this.mRId = mRId;
        this.mId = mId;
        this.readingDate = readingDate;
        this.previousReading = previousReading;
        this.currentReading = currentReading;
        sObj=Config.setConnection();
    }
    
    public void setmRId(int mRId) {  this.mRId = mRId;   }
    public void setmId(int mId) {    this.mId = mId;   }
    public void setReadingDate(String readingDate) {     this.readingDate = readingDate; }
    public void setPreviousReading(long previousReading) {    this.previousReading = previousReading; }
    public void setCurrentReading(long currentReading) {    this.currentReading = currentReading; }
    public void setAllMR(int mRId, int mId, Date String, long previousReading, long currentReading) {
        this.mRId = mRId;
        this.mId = mId;
        this.previousReading = previousReading;
        this.currentReading = currentReading;
    }
        
    public int getmRId() {    return mRId;  }
    public int getmId() {    return mId; }
    public String getReadingDate() {  return readingDate;   }
    public long getPreviousReading() {    return previousReading;}
    public long getCurrentReading() {    return currentReading;}
    public static Statement getsObj() {
        sObj=Config.setConnection();
        return sObj;
    }
    
    public String getDate(){
        String dateString = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
       return dateString;
    }


    public void calculateReading(){
        ResultSet rsObj=null;
        try {
            String query="Select current_reading from meter_reading where mid = "+getmId();
            rsObj=getsObj().executeQuery(query);
            if(rsObj.last()){
                setPreviousReading(rsObj.getLong(1));
            }
            else{
                setPreviousReading(0);
            }
            long max = getPreviousReading()+30000;
		    long min = getPreviousReading()+1000;
            long range = max - min + 1;
            long cRead = (long)(Math.random() * range) + min;
            setCurrentReading(cRead);
            rsObj.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
    public void addReading(){
        try {
            String query="insert into meter_reading() values(null,"+getmId()+",'"+getDate()+"',"+getPreviousReading()+","+getCurrentReading()+")";
            //JOptionPane.showMessageDialog(null, query);
            getsObj().executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  static int[] checkMeters(){
        ResultSet rsObj=null;
        int arr[]=null;
        try { 
           
            rsObj=getsObj().executeQuery("Select mid from meter where Status='Active'");
            int i=0;
            rsObj.last();
            arr=new int[rsObj.getRow()];
            rsObj.beforeFirst();
            while(rsObj.next()){
             
                arr[i++]=rsObj.getInt(1);
                //calculateReading();
                // addReading();
            }
            rsObj.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }
    public void addingReadings(){
       int arr[]= checkMeters();
       if(arr!=null){
            for(int i=0;i<arr.length;i++){
                    setmId(arr[i]);
                    calculateReading();
                    addReading();
            }
       }
    }
}
