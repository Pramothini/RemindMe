package relic.remindme;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import relic.remindme.QRscanner.QRScanner_screen;


/**
 * Created by pramothinidk on 7/18/15.
 *
 * Display the details of a single list and handle settings associated with a list
 */
public class ListItemActivity extends ListActivity {
//    GridLayout grid;
//    ListLab listLab = new ListLab(this);
//
//
////    private Socket client;
//    private PrintWriter printwriter;
//    private String message;
//    DefaultListReader dr = new DefaultListReader();



//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.controller_activity_listitem);
//        EditText listItem = (EditText) findViewById(R.id.listItem);
//        grid = (GridLayout) findViewById(R.id.gridLayout);
//        listItem.setText("New list item");
//        setListeners(listItem);
//    }

//    public void onNotificationClick(View v){
//
//
//        Intent i = new Intent(ListItemActivity.this, NotificationSettings.class);
//        startActivity(i);
//
//    }
//
//    public void onHomeClick(View v){
//        String listname = "";
//        ArrayList<String> listitems = new ArrayList<>();
//        String msg = "";
//        int listid;
//        Log.e("pink","inside onHomeClick ");
//        AlertDialog.Builder builder =
//                new AlertDialog.Builder(ListItemActivity.this);
//
//        // set dialog title & message, and provide Button to dismiss
//        builder.setTitle("Number of list items");
//
//        for (int i = 1; i < grid.getChildCount(); i++) {
//            EditText  et = (EditText) grid.getChildAt(i);
//            if(i==1)
//                listname = et.getText()+"";
//            else
//                listitems.add(et.getText() + "");
//        }
//        msg = "no of list items" + (grid.getChildCount() - 2) + " list items are: "+listitems.toString()
//                +"list name = "+listname;
//
//        listid = (int) listLab.createList(listname,listitems);
//
//        if(listid != -1)
//            msg += "saved success";
//        else
//            msg += "not success";
//
//        builder.setMessage(msg);
//
//        builder.setPositiveButton("OK", null);
//        builder.show();
//
//
//         // display the Dialog
//        Intent i = new Intent(ListItemActivity.this, HomePage.class);
//        startActivity(i);
//    }
//
//    public void onQRClick(View v){
//        Toast.makeText(ListItemActivity.this, "QR sensor will be implemented in construction phase", Toast.LENGTH_SHORT).show();
//    }
//
//    public void onTrashClick(View v){
//        Toast.makeText(ListItemActivity.this, "This list will be deleted. This will be implemented in construction phase", Toast.LENGTH_SHORT).show();
//    }
//
//    public void onShare(View v){
//        Toast.makeText(ListItemActivity.this, "Share will utilize the contacts DB and will be implemented in construction phase", Toast.LENGTH_SHORT).show();
//    }
//
//    void setListeners(EditText listitem){
//
//        listitem.setOnKeyListener(new View.OnKeyListener() {
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
//                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
//                    EditText newListItem = new EditText(ListItemActivity.this);
//                    newListItem.setText("New list item");
//                    grid.addView(newListItem);
//                    setListeners(newListItem);
//                    return true;
//                }
//                return false;
//            }
//        });
//    }





    //new design



    ArrayList list = new ArrayList();
    ArrayAdapter adapter;
    String listname;
    ListLab listLab = ListLab.get(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__screen);


        EditText edit = (EditText) findViewById(R.id.txtItem);
        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            String QR_listitem = extras.getString("QR_item","");
            Toast.makeText(this,"added item:" + QR_listitem, Toast.LENGTH_LONG).show();
            edit.setText(edit.getText()+QR_listitem);
            listname = extras.getString("ListName");

        }
        list = listLab.getAllListItems(listname);
        /** Reference to the add button of the layout main.xml */
        Button btn = (Button) findViewById(R.id.btnAdd);

        /** Reference to the delete button of the layout main.xml */
        Button btnDel = (Button) findViewById(R.id.btnDel);


        Toast toast = Toast.makeText(this, "you have clicked on "+listname, Toast.LENGTH_LONG);
        toast.show();


        /** Defining the ArrayAdapter to set items to ListView */
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, list);

        /** Defining a click event listener for the button "Add" */
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edit = (EditText) findViewById(R.id.txtItem);
                list.add(edit.getText().toString());
                edit.setText("");
                adapter.notifyDataSetChanged();
            }
        };


        /** Defining a click event listener for the button "Delete" */
        View.OnClickListener listenerDel = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /** Getting the checked items from the listview */
                SparseBooleanArray checkedItemPositions = getListView().getCheckedItemPositions();
                int itemCount = getListView().getCount();

                for(int i=itemCount-1; i >= 0; i--){
                    if(checkedItemPositions.get(i)){
                        adapter.remove(list.get(i));
                    }
                }
                checkedItemPositions.clear();
                adapter.notifyDataSetChanged();
            }
        };

        /** Setting the event listener for the add button */
        btn.setOnClickListener(listener);

        /** Setting the event listener for the delete button */
        btnDel.setOnClickListener(listenerDel);

        /** Setting the adapter to the ListView */
        setListAdapter(adapter);
    }

    @Override
    public void onBackPressed() {

        int itemCount = getListView().getCount();
//        Toast toast = Toast.makeText(this, "The total items is " + itemCount + "and the items are"+list.toString(), Toast.LENGTH_LONG);
//        toast.show();

        int listid = listLab.savelistitems(listname,list);
//String msg = "";
//        if(listLab.savelistitems(listname,list)) {
//           msg = "list items are saved successfully";
//        }
//        else{
//            msg = "Problem in list item save";
//        }
        Toast toast2 = Toast.makeText(this, "value of list item id is"+listid, Toast.LENGTH_LONG);
        toast2.show();

        Intent k =new Intent();
        k.setClass(this, HomePage.class);
        startActivity(k);



//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setCancelable(false);
//        builder.setMessage("Do you want to Exit?");
//        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                //if user pressed "yes", then he is allowed to exit from application
//                finish();
//            }
//        });
//        builder.setNegativeButton("No",new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                //if user select "No", just cancel this dialog and continue with app
//                dialog.cancel();
//            }
//        });
//        AlertDialog alert=builder.create();
//        alert.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list__screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch(id)
        {
            case R.id.action_Notification:

                Intent j =new Intent();
                j.putExtra("lisname",listname);
                j.setClass(this,NotificationSettings.class);
                startActivity(j);
                return true;


            case R.id.action_QRScanner:

                Intent k =new Intent();
                k.setClass(this, QRScanner_screen.class);
                startActivity(k);
                return true;

        }


        return super.onOptionsItemSelected(item);
    }





}

