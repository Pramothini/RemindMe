package dblayout;
import entities.List;
import entities.ListItem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Santosh on 25-07-2015
 */
public class DatabaseConnector {

    Connection connection;
    PreparedStatement statement,statement2;
    ResultSet resultSet, resultSet2;

    public void startDb() {

        try {
            // Load the MySQL JDBC driver
            System.out.println(".. welcome to database utility inside server..");
            Class.forName("com.mysql.jdbc.Driver") ;
            System.out.println("MySQL JDBC driver loaded ok.");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:8889/List?"
                    + "user=santosh&password=santosh123");
            System.out.println("Connected with host:port/database");
        } catch (Exception e) {
            System.err.println("Exception: "+e.getMessage());
        }

    }

    public void viewRecords(String operation){

        try{
            System.out.println("After "+operation +" records in Remindme db : ..\n");
            statement = connection.prepareStatement("select * from List");
            statement2 = connection.prepareStatement("select * from ListItem");
            resultSet = statement.executeQuery();
            resultSet2 = statement2.executeQuery();

				/*printing  new values of List*/
            while(resultSet.next())

                System.out.println("List table: "+resultSet.getString(0)+" "+resultSet.getString(1)
                        + " "+resultSet.getString(2)+" "+resultSet.getString(3)+"\n");


				  /*printing  new values of ListItem*/

            while(resultSet2.next())

                System.out.println("ListItem  table: "+resultSet2.getString(0)+" "+resultSet2.getString(1)
                        + " "+resultSet2.getString(2)+"\n");


        }
        catch (Exception e) {
            System.err.println("Exception: "+e.getMessage());
        }
    }

    public void insertQuery(String query){

        try {
            statement=connection.prepareStatement(query);
            statement.executeUpdate();
        }
        catch (SQLException ex) {
            System.err.println(ex);
        }

    }

    public void updateQuery(String query){

        try {
            statement=connection.prepareStatement(query);
            statement.executeUpdate();


        }
        catch (SQLException ex) {
            System.err.println(ex);

        }

    }

    public void deleteQuery(String query){
        try{
            statement=connection.prepareStatement(query);
            statement.executeUpdate();
        }
        catch(SQLException e){
            System.err.println(e);
        }
    }


    public void closedb() throws SQLException{
        System.out.println("now closing db");
        resultSet.close();
        statement.close();
        connection.close();
    }




}
