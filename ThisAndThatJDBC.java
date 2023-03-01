import java.sql.*; 
class ThisAndThatJDBC { 
    public static void main (String args[]){ 
        try{ 
// Deprecated Class.forName("com.mysql.jdbc.Driver"); 
            Connection dbCxn=DriverManager.getConnection( 
                "jdbc:mysql://localhost:43306/ThisAndThat","root","haskell1819!" 
            ); 
// 43306 is the port I have forwarded to the same port on my VM 
// (The leading "6" would be replaced by your Pair ID.) 
// ThisAndThat is the database name // root is the mysql username 
// "haskell1819!" is the password for the root mysql user 
// HORRIBLE SECURITY BREACH, we normally don't embed passwords in our code 
            Statement thatStmt=dbCxn.createStatement(); 
            ResultSet rsThat=thatStmt.executeQuery("select * from That"); 
            while(rsThat.next()) { 
                int foreignKeyToThis = rsThat.getInt(4); 
                System.out.println(rsThat.getInt(1) + " " 
                                + rsThat.getString(2) + " " 
                                + rsThat.getString(3) + " " 
                                + rsThat.getInt(4)); 
                String thisQuery = 
                    "select * from This where This_pk = '" + foreignKeyToThis + "'"; 
                System.out.println(thisQuery); 
                Statement thisStmt = dbCxn.createStatement(); 
                ResultSet rsThis=thisStmt.executeQuery(thisQuery); 
                while (rsThis.next()) { 
                    System.out.println("\t" + rsThis.getInt(1) 
                        + " " + rsThis.getString(2) 
                        + " " + rsThis.getInt(3) 
                        + " " + rsThis.getDate(4) 
                        + " " + rsThis.getString(5));
                } 
            } 
            dbCxn.close(); 
        } catch(Exception e){ System.out.println(e); }
    }
}