import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ThisJDBC {
    public static void main(String args[]){
        try{
// Deprecated Class.forName("com.mysql.jdbc.Driver"); 
// 43306 is the port I have forwarded to the same port on my VM 
// (The leading "6" would be replaced by your Pair ID.) 
// ThisAndThat is the database name 
// root is the mysql username 
// "haskell1819!" is the password for the root mysql user 
// HORRIBLE SECURITY 
// Normally we do not embed passwords in code 
            Connection dbCxn = DriverManager.getConnection(
                "jdbc:mysql://localhost:43306/ThisAndThat", "root", "haskell1819!"
            );
            Statement selectThis = dbCxn.createStatement();
            ResultSet rsThis = selectThis.executeQuery("select * from This");
            while(rsThis.next()){
                System.out.println(rsThis.getInt(1)
                    + " " + rsThis.getString(2)
                    + " " + rsThis.getString(3)
                    + " " + rsThis.getString(4)
                    + " " + rsThis.getString(5)
                );
            }
            dbCxn.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}