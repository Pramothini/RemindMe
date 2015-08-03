package db;
import java.sql.*;
import java.math.*;

public class JDBCConnector {
	
	Connection connection;
	PreparedStatement statement;
	ResultSet result;
	
	
	public void startDb() {
		try {
			// Load the MySQL JDBC driver
			      System.out.println(".. welcome to database utility inside server..");
			      Class.forName("com.mysql.jdbc.Driver").newInstance();
			      System.out.println("MySQL JDBC driver loaded ok.");
			      
			      connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/remindme",
			    	        "santosh", "santosh123");
			      System.out.println("Connected with host:port/database");
			    } catch (Exception e) {
			      System.err.println("Exception: "+e.getMessage());
			    }
			     
			  }
	
	public void insertRecords(String query){
		 try {
			  	statement=connection.prepareStatement(query);
			  	statement.executeUpdate(); 
			 	System.out.println(" this is SUCCESS");

			}
			catch (SQLException ex) {
				System.err.println(ex);
			}
		 
		 finally {
			    if (result != null) {
			        try {
			            result.close();
			        } catch (SQLException e) { /* ignored */}
			    }
			    if (statement != null) {
			        try {
			            statement.close();
			        } catch (SQLException e) { /* ignored */}
			    }
			    if (connection != null) {
			        try {
			            connection.close();
			        } catch (SQLException e) { /* ignored */}
			    }
		 }
	}
	
	public void viewRecrods(){
		
		try{
			statement = connection.prepareStatement("select * from list_table");
			result = statement.executeQuery();
			
			 while(result.next())
				 
					System.out.println("List table: "+result.getString(1)+" "+result.getString(2)
					+ " "+result.getString(3)+" "+result.getString(4)+"\n");       
			}
			catch (SQLException ex) {
				System.err.println(ex);
			} 
		
	}
	
	public void closedb() throws SQLException{
		  System.out.println("now closing db");
		  if (result != null) {
		        try {
		            result.close();
		        } catch (SQLException e) { /* ignored */}
		    }
		    if (statement != null) {
		        try {
		            statement.close();
		        } catch (SQLException e) { /* ignored */}
		    }
		    if (connection != null) {
		        try {
		            connection.close();
		        } catch (SQLException e) { /* ignored */}
		    }
	  }

}
