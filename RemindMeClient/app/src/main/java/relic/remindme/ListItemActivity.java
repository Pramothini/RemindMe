package relic.remindme;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import adapter.ListLab;
import controller.DefaultListReader;
import controller.DefaultSocketClient;
import entities.List_entity;


/**
 * Created by pramothinidk on 7/18/15.
 *
 * Display the details of a single list and handle settings associated with a list
 */
public class ListItemActivity extends Activity {
    GridLayout grid;
    ListLab listLab = new ListLab(this);
    String listname = "";
    ArrayList<String> listitems = new ArrayList<>();

//    private Socket client;
    private PrintWriter printwriter;
    private String message;
    DefaultListReader dr = new DefaultListReader();



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.controller_activity_listitem);
        EditText listItem = (EditText) findViewById(R.id.listItem);
        grid = (GridLayout) findViewById(R.id.gridLayout);
        listItem.setText("New list item");
        setListeners(listItem);
    }

    public void onNotificationClick(View v){


        Intent i = new Intent(ListItemActivity.this, NotificationSettings.class);
        startActivity(i);

    }

    public void onHomeClick(View v){
        Log.e("pink","inside onHomeClick ");
        AlertDialog.Builder builder =
                new AlertDialog.Builder(ListItemActivity.this);

        // set dialog title & message, and provide Button to dismiss
        builder.setTitle("Number of list items");

        for (int i = 1; i < grid.getChildCount(); i++) {
            EditText  et = (EditText) grid.getChildAt(i);
            if(i==1)
                listname = et.getText()+"";
            else
                listitems.add(et.getText() + "");
        }

            builder.setMessage("no of list items" + (grid.getChildCount() - 2) + " list items are: "+listitems.toString()
                    +"list name = "+listname+" successfully created the list!..now connecting");

            SendMessage sendMessageTask = new SendMessage();
            sendMessageTask.execute();


        builder.setPositiveButton("OK", null);
        builder.show();

        //defaultSocket.run();

         // display the Dialog
//        Intent i = new Intent(ListItemActivity.this, HomePageListActivity.class);
//        startActivity(i);
    }

    public void onQRClick(View v){
        Toast.makeText(ListItemActivity.this, "QR sensor will be implemented in construction phase", Toast.LENGTH_SHORT).show();
    }

    public void onTrashClick(View v){
        Toast.makeText(ListItemActivity.this, "This list will be deleted. This will be implemented in construction phase", Toast.LENGTH_SHORT).show();
    }

    public void onShare(View v){
        Toast.makeText(ListItemActivity.this, "Share will utilize the contacts DB and will be implemented in construction phase", Toast.LENGTH_SHORT).show();
    }

    void setListeners(EditText listitem){

        listitem.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    EditText newListItem = new EditText(ListItemActivity.this);
                    newListItem.setText("New list item");
                    grid.addView(newListItem);
                    setListeners(newListItem);
                    return true;
                }
                return false;
            }
        });
    }


    private class SendMessage extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {

                Log.e("pink","going to call createList from activity");
                listLab.createList(listname, listitems);
//                List_entity newList = new List_entity();
//
//                newList
//                DefaultSocketClient ds = new DefaultSocketClient(new Socket("10.0.2.2", 4444));
//
//
//                dr.setListLabObjects(listLab);
//                dr.printListLabObjects(listLab);
//                dr.getListName();
//
//                ds.upload(dr);

//                client = new Socket("10.0.2.2", 4444); // connect to the server



//                PrintWriter out = new PrintWriter(client.getOutputStream(), true);
//
//                out.println("insert");
//                out.close();
//                client = new Socket("localhost", 4444); // connect to the server

                //printwriter = new PrintWriter(client.getOutputStream(), true);
                //printwriter.write(message); // write the message to output stream
//                ObjectOutputStream sendToServer = new ObjectOutputStream(new BufferedOutputStream(client.getOutputStream()));




//                ObjectOutputStream sendToServer = new ObjectOutputStream(client.getOutputStream());
//                sendToServer.writeObject(dr);






//                sendToServer.flush();

               // printwriter.flush();
                // printwriter.close();
               // client.close(); // closing the connection

//                try {
////                    out = null;
//                    client.close();
//                }
//                catch (IOException e){
//                    e.printStackTrace();
//                }

//            } catch (UnknownHostException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
            }
                catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

    }
}

