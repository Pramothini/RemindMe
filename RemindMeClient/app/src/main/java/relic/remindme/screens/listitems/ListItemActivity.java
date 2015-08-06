package relic.remindme.screens.listitems;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import adapter.*;
import exceptions.ExceptionManager;
import exceptions.FixExceptions;
import relic.remindme.R;
import relic.remindme.screens.QRscanner.QRScanner_screen;
import relic.remindme.screens.homepage.HomePage;
import relic.remindme.screens.notifications.Notifications_MainScreen;
import relic.remindme.screens.speechtotext.UserSpeechToText;


/**
 * Created by pramothinidk on 7/18/15.
 *
 * Display the details of a single list and handle settings associated with a list
 */
public class ListItemActivity extends ListActivity {
    ArrayList list = new ArrayList();
    ArrayAdapter adapter;
    String listname;
    adapter.Readable read;

    Updatable update;
    FixExceptions fixExceptions;

    /**
     * called before the ListItemActivity becomes visible
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.control_list_item_activity);
        setTitle("Remind Me");

        // initializing the API
        update = new Manage(this);
        read = new Manage(this);
        fixExceptions = new Manage(this);

        EditText edit = (EditText) findViewById(R.id.txtItem);

        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
           if(extras.getString("QR_item")!=null)
            {
            String QR_listitem = extras.getString("QR_item");
            Toast.makeText(this, "added item:" + QR_listitem, Toast.LENGTH_LONG).show();
            edit.setText(edit.getText() + QR_listitem);
            }
            if(extras.getString("SpokenWord")!=null)
            {
                String speaking = extras.getString("SpokenWord");
                edit.setText(edit.getText() + speaking);

            }

            listname = extras.getString("ListName");
        }
        Log.e("App", "LIST NAME: " + listname);
        list = read.getAllListItems(listname);
        Button btn = (Button) findViewById(R.id.btnAdd);
        Button btnDel = (Button) findViewById(R.id.btnDel);
//
        /** Defining the ArrayAdapter to set items to ListView */
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, list);

        /**
         * Defining a click event listener for the button "Add"
         * If a list item is empty , it raises a custom exception and does not create that
         * list item
         * */
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edit = (EditText) findViewById(R.id.txtItem);
                try {
                    if (fixExceptions.validator(edit.getText().toString())) {
                        throw new ExceptionManager(2, "List item name is empty");
                    } else {
                        list.add(edit.getText().toString());
                        edit.setText("");
                        adapter.notifyDataSetChanged();
                    }
                }
                catch(ExceptionManager e){
                    Toast toast = Toast.makeText(ListItemActivity.this, fixExceptions.fix(e.getErrorno(), e.getErrormsg()).toString(), Toast.LENGTH_LONG);
                    toast.show();
                }
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

    /**
     * When the user clicks on back, this method will be called
     * saves the list items to database
     */
    @Override
    public void onBackPressed() {
        int listid = update.savelistitems(listname,list);
//        Toast toast2 = Toast.makeText(this, "value of list item id is"+listid, Toast.LENGTH_LONG);
//        toast2.show();
        Intent k =new Intent();
        k.setClass(this, HomePage.class);
        startActivity(k);

    }

    /**
     * called when menu options are created
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list__screen, menu);
        return true;
    }

    /**
     * called when a menu item is clicked
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as a parent activity is specified in AndroidManifest.xml.
        int id = item.getItemId();
        switch(id)
        {
            case R.id.action_Notification:

                Intent j =new Intent();
                j.putExtra("lisname",listname);
                j.setClass(this,Notifications_MainScreen.class);
                startActivity(j);
                return true;


            case R.id.action_QRScanner:

                Intent k =new Intent();
                k.setClass(this, QRScanner_screen.class);
                k.putExtra("lisname",listname);
                startActivity(k);
                return true;

            case R.id.action_Share:
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = list.toString();
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, listname);
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
                return true;

            case R.id.action_Speak:

                Intent z =new Intent();
                z.setClass(this, UserSpeechToText.class);
                z.putExtra("lisname", listname);

                startActivity(z);
                return true;



        }

        return super.onOptionsItemSelected(item);
    }





}

