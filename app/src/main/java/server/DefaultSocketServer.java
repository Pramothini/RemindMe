package server;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Santosh on 25-07-2015.
 */
public class DefaultSocketServer extends Exception {

    private static ServerSocket serverSocket = null;
    ListServer listServerObject = new DefaultSocketReader();

    ObjectInputStream ois = null;

    public boolean openConnection(){
        try {
            serverSocket = new ServerSocket(4444);
            System.out.println(" listening on port 4444 ");
        } catch (IOException e) {
            System.err.println(" can not listen to port 4444 ");
            return false;
        }
        return true;
    }

    public void handleSession() {

        int filesize = 2058386;
        int bytesRead;
        int currentTot = 0;

        try{
            Socket client = null;
            client = serverSocket.accept(); //accepting client's request via client's socket
            //read from socket to input stream
            ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
            //convert objectstream to string
            String message = (String) ois.readObject();
            System.out.println("message received from client: "+message);
            //create ObjectOutputStream object
            ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
            //write Object to socket
            oos.writeObject("Hello client"+message);


            String option = (String)ois.readObject();

            System.out.println("Client has choose option to perform : " + option);
            if (option.equals("create")) {

                //writing in the backend after reading the client socket

            }
            else if(option.equals("view")){ // this will view all list items

            //code for writing to client socket.

            } else {
                System.out.println("invalid input from client....");
            }

            ois.close();
            oos.close();
            client.close();



        } catch (Exception e){
            System.err.println(e);
        }
    }

    /*closing server sockets*/

    public void closeSession(){
        try {
            System.out.println("shutting down server socket");
            serverSocket.close();
        }
        catch (IOException e){
            System.err.println(" error in closing the server socket  ");
        }
    }

    public void run(){
        openConnection();
        while(true) {
            handleSession();
            closeSession();
        }

    }

    public static void main(String[] args) {
        DefaultSocketServer s1 = new DefaultSocketServer();
        s1.run();
    }


}