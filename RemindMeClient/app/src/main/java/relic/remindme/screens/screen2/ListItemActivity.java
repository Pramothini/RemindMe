package relic.remindme.screens.screen2;

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
import relic.remindme.R;
import relic.remindme.screens.QRscanner.QRScanner_screen;
import relic.remindme.screens.homepage.HomePage;
import relic.remindme.screens.notifications.Notifications_MainScreen;


/**
 * Created by pramothinidk on 7/18/15.
 *
 * Display the details of a single list and handle settings associated with a list
 */
public class ListItemActivity extends ListActivity {
    ArrayList list = new ArrayList();
    ArrayAdapter adapter;
    String listname;
//    ListLab listLab = ListLab.get(this);
    adapter.Readable read;

    Updatable update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.control_listitemactivity);

        update = new Manage(this);
        read = new Manage(this);

        EditText edit = (EditText) findViewById(R.id.txtItem);


        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            //
           if(extras.getString("QR_item")!=null)
            {
            String QR_listitem = extras.getString("QR_item");
            Toast.makeText(this, "added item:" + QR_listitem, Toast.LENGTH_LONG).show();
            edit.setText(edit.getText() + QR_listitem);
            }
            //
            listname = extras.getString("ListName");
        }
        Log.e("App", "LIST NAME: " + listname);
        list = read.getAllListItems(listname);
        /** Reference to the add button of the layout controller_homepage1roller_homepage1.xml */
        Button btn = (Button) findViewById(R.id.btnAdd);

        /** Reference to the delete button of the layout control_homepage1.xmler_homepage1.xml */
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

        int listid = update.savelistitems(listname,list);
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

        }


        return super.onOptionsItemSelected(item);
    }





}

