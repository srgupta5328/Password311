/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package password311;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maxwell
 */
public class Credentials {
    private PreparedStatement pst;
    private PreparedStatement status;
    private PreparedStatement insertCredential;
    private int updateValue;
    private ResultSet rs;
    private ArrayList<String> results;
    static Credentials aCredential = null;
    private final String DATABASE_URL = "jdbc:derby://localhost:1527/Password311";
    private final String USERNAME = "Rohan";
    private final String PASSWORD = "Hello";
    private static Connection connection;
    
    //private Credentials(){};
    
    public static Credentials getInstance()
    {
        if (aCredential == null)
        {
            aCredential = new Credentials();
        }
        
        return aCredential;
    }
    
//    public ArrayList<String> getCredentials()
//    {
//        try {
//            //needs to be updated to select something from credential table
//            pst = getConnection().prepareStatement("Select NAME from HOLIDAY");
//            rs = pst.executeQuery();
//            
//            results = new ArrayList<>();
//            while (rs.next())
//            {
//                results.add(rs.getString("NAME"));
//            }
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(Credentials.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        return results;
//    }
    
//    public ArrayList getStatus(String credentialName)
//    {
//        @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
//        ArrayList<ArrayList<Object>> twoDArrayList = new ArrayList<>();
//        String sql = "Select CUSTOMER_NAME, MAGICIAN_NAME from BOOKINGS where HOLIDAY_NAME = ?";
//            
//        try {
//            status = getConnection().prepareStatement(sql);
//            status.setString(1, holidayName);
//            rs = status.executeQuery();
//            
//            while(rs.next())
//            {
//                ArrayList columnList = new ArrayList();
//                twoDArrayList.add(columnList);
//                columnList.add(rs.getString("CUSTOMER_NAME"));
//                columnList.add(rs.getString("MAGICIAN_NAME"));   
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(Credentials.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        return twoDArrayList;
//    }    
    
    
    public void addCredential(int user_id, String username, String password, String label)
    {
        try {
            DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD); 
            insertCredential = connection.prepareStatement("INSERT INTO CREDENTIAL (USER_ID, USERNAME, PASSWORD, LABEL) values (?, ?, ?, ?)");
            insertCredential.setInt(1, user_id);
            insertCredential.setString(2, username); 
            insertCredential.setString(3, password); 
            insertCredential.setString(4, label); 
            updateValue = insertCredential.executeUpdate();
            
//            insertCredential = getConnection().prepareStatement("INSERT INTO CREDENTIAL (PASSWORD) values (?)");
//            insertCredential.setString(1, password);  
//            updateValue = insertCredential.executeUpdate();
//            
//            insertCredential = getConnection().prepareStatement("INSERT INTO CREDENTIAL (LABEL) values (?)");
//            insertCredential.setString(1, label);  
//            updateValue = insertCredential.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Credentials.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    
    private Connection getConnection()
    {
        return CredentialList.getConnection();
    }    
}
