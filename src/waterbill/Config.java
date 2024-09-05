package waterbill;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import com.mysql.jdbc.Driver;

public class Config {
    private static Connection cObj;
    private static Statement sObj;
    
    public  static Statement setConnection(){
        try {
            DriverManager.registerDriver(new Driver());
            cObj=DriverManager.getConnection("jdbc:mysql://localhost/water_management?characterEncoding=UTF-8", "root", "");
            sObj=cObj.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            return sObj;
        }
        
    }
}
