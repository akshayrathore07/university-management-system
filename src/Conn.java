import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Conn {
    Connection c;
    Statement s;

    Conn(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Driver manager is a class
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/universitymanagementsystem" , "root" , "12345");
            s = c.createStatement(); // with the help of statement you can execute mysql query

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
