package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Date;
import java.text.SimpleDateFormat;

import adapter.ListLab;
import controller.DefaultListReader;
import db.JDBCConnector;
import entities.List_entity;
/**
 * This is a simple server application. This server receive a string message
 * from the Android mobile phone and show it on the console.
 * Author by Lak J Comspace
 */
public class Server extends Exception {

	private static ServerSocket serverSocket;
	//private static Socket clientSocket;
	ObjectInputStream inputFromClient = null;
	private static JDBCConnector j = new JDBCConnector();
	private static String userid,date,name,insertquery;
	
	//private static ListLab l1 = new ListLab();
	//private static InputStreamReader inputStreamReader;
	//private static BufferedReader bufferedReader;
	//private static String message;

	public static void main(String[] args) {
		try {
			serverSocket = new ServerSocket(4444); // Server socket

		} catch (IOException e) {
			System.out.println("Could not listen on port: 4444");
		}

		System.out.println("Server started. Listening to the port 4444");

		while (true) {
			try {
				
				Socket clientSocket = null;
				
				clientSocket = serverSocket.accept(); // accept the client connection
				//inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
				//bufferedReader = new BufferedReader(inputStreamReader); // get the client message
				
			    //message = bufferedReader.readLine();
				
				DefaultListReader dr = new DefaultListReader();
				List_entity list = new List_entity();
	            
				
				//ListLab l1 = new ListLab();
				
				ObjectInputStream receiveFromClient = new ObjectInputStream(clientSocket.getInputStream());
				
				dr = (DefaultListReader) receiveFromClient.readObject();
				
				//userid = "1234"; //for the time being
				
				list=dr.getLatestListObject();
				
				date = new SimpleDateFormat("yyyy-MM-dd").format(list.getCreatedDate());
						
				if(userid==null)userid="1234";
				
				name = list.getListName().toString();
				
		    	insertquery= "INSERT INTO list_table() VALUES (NULL,'"+userid+"','"+date+"','"+name+"')";
				
				///just to test printing below block
				
				System.out.println("lists "+ dr.getListName());
				System.out.println("list items "+ dr.getListItems());
				System.out.println("latest created list object "+ dr.getLatestListObject());
				System.out.println("latest created list object name "+ dr.getLatestListObject().getListName());
				System.out.println("latest created list object userid "+ dr.getLatestListObject().getUserId());
				System.out.println("latest created list object createDated "+ dr.getLatestListObject().getCreatedDate());
				System.out.println("latest created list object listitems "+ dr.getLatestListObject().getListItems());
				
				try{
				j.startDb();
				
		

				
		    	insertquery= "INSERT INTO list_table() VALUES (NULL,'"+userid+"','"+date+"','"+name+"')";
				j.insertRecords(insertquery);
				//db.createList(list);
				
				//db.viewList();
				j.closedb();
				
				}
				catch(Exception e){
					System.err.println("unable to work with db"+e);
				}
				
			} catch (IOException ex) {
				System.err.println(ex);
			} catch (ClassNotFoundException s){
				System.err.println(s);
			}
		}

	}

}
