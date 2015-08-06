package relic.remindme.screens.homepage;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.provider.Settings.Secure;

import java.util.ArrayList;

import adapter.Creatable;
import adapter.Deletable;
import adapter.Manage;
import adapter.Readable;
import entities.List_entity;

import exceptions.ExceptionManager;
import relic.remindme.screens.listitems.ListItemActivity;
import relic.remindme.R;

import exceptions.FixExceptions;


/**
 * Populates the list details of all the lists that the user had created in the home page
 */
public class HomePage extends ListActivity {

    private ArrayList<List_entity> mListEntity = new ArrayList<List_entity>();
    private ListAdapter listAdapter;
    private String android_id;
    Deletable del;
    Creatable create;
    Readable read;
    FixExceptions fixError;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.control_homepage1);
       android_id = Secure.getString(this.getContentResolver(),
                Secure.ANDROID_ID);
        //assign values for the CRUD APIs
        del = new Manage(this);
        create = new Manage(this);
        read = new Manage(this);
        fixError = new Manage(this);
        updateUI();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void onBackPressed() {

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_task:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Create New List");
                builder.setMessage("Enter List Name");
                final EditText inputField = new EditText(this);
                builder.setView(inputField);
                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String task = inputField.getText().toString();
                        try {
                            if (fixError.validator(task)) {
                                throw new ExceptionManager(1,"List name is empty");
                            } else if (fixError.checkIfListNameAlreadyExists(task)) {
                                throw new ExceptionManager(3, "List name is already present");
                            } else {
                                int id = create.createNewList(task, android_id);
//                        Toast toast = Toast.makeText(inputField.getContext(), "created a new list and the list id is" + id, Toast.LENGTH_LONG);
//                        toast.show();
                                Intent intent = new Intent();
                                intent.putExtra("ListName", task);
                                intent.setClass(HomePage.this, ListItemActivity.class);
                                startActivity(intent);
                                updateUI();
                            }
                        }
                        catch (ExceptionManager e){
                            Toast toast = Toast.makeText(inputField.getContext(), fixError.fix(e.getErrorno(), e.getErrormsg()).toString(), Toast.LENGTH_LONG);
                            toast.show();
                        }

                    }
                });

                builder.setNegativeButton("Cancel",null);

                builder.create().show();
                return true;

            default:
                return false;
        }
    }

    /**
     * Updates the UI based on the number of lists present in the db
     */
    private void updateUI() {
            mListEntity = read.getallLists();
            String[] columns = new String[] { "TASK", "_ID"};

            MatrixCursor matrixCursor= new MatrixCursor(columns);
            startManagingCursor(matrixCursor);

            ArrayList<String> listnames= new ArrayList<String>();
            for(List_entity s:mListEntity) {
                matrixCursor.addRow(new Object[]{s.getListName(), s.getId()});
                listnames.add(s.getListName());
            }

            listAdapter = new SimpleCursorAdapter(
                    this,
                    R.layout.control_homepage2,
                    matrixCursor,
                    new String[] { "TASK" },
                    new int[]{R.id.taskTextView},
                    0
            );

            this.setListAdapter(listAdapter);
            Log.e("HomePage_GetList", "list names from db "+listnames.toString());
    }

    /**
     * Action listener for the delete list button
     * Deletes the corresponding list in the UI
     * @param view
     */
    public void onDoneButtonClick(View view) {
        View v = (View) view.getParent();
        TextView taskTextView = (TextView) v.findViewById(R.id.taskTextView);
        String task = taskTextView.getText().toString();
        del.deleteList(task);
        updateUI();
    }

    /**
     * Used to navigate to the next activity
     * @param v
     */
    public void enterList(View v) {
        TextView tv = (TextView) v;
        Intent i=new Intent();
        i.putExtra("ListName",tv.getText());
        i.setClass(this,ListItemActivity.class);
        startActivity(i);

    }


}