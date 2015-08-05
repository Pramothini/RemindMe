package relic.remindme;

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
import adapter.ListLab;
import adapter.Manage;
import adapter.Readable;
import entities.List_entity;

/**
 * Populates the list details of all the lists that the user had created in the home page
 */
public class HomePage extends ListActivity {

    private ArrayList<List_entity> mListEntity = new ArrayList<List_entity>();
//    ListLab listlab = ListLab.get(this);
    private ListAdapter listAdapter;
    private String android_id;
    Deletable del;
    Creatable create;
    Readable read;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
       android_id = Secure.getString(this.getContentResolver(),
                Secure.ANDROID_ID);
        Toast toast = Toast.makeText(this, "my android id is" + android_id, Toast.LENGTH_LONG);
        toast.show();
        del = new Manage(this);
        create = new Manage(this);
        read = new Manage(this);

        updateUI();
    }


    //new design
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

                        int id = create.createNewList(task,android_id);
                        Toast toast = Toast.makeText(inputField.getContext(), "created a new list and the list id is" + id, Toast.LENGTH_LONG);
                        toast.show();

                        updateUI();
                    }
                });

                builder.setNegativeButton("Cancel",null);

                builder.create().show();
                return true;

            default:
                return false;
        }
    }

    private void updateUI() {
//        ListLab listlab = ListLab.getlListLab();
//        Log.e("HomePage_GetList", "printing listlab object"+listlab);
////        if(listlab != null) {
//            Log.e("HomePage_GetList", "printing listlab object is not null "+listlab);
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
                    R.layout.task_view,
                    matrixCursor,
                    new String[] { "TASK" },
                    new int[]{R.id.taskTextView},
                    0
            );

            this.setListAdapter(listAdapter);

            Log.e("HomePage_GetList", "list names from db "+listnames.toString());
//        }
//        else{
//            Log.e("HomePage_GetList", "listlab is null ");
//        }




//        String[] columns = new String[] { "TASK", "_ID"};
//
//        MatrixCursor matrixCursor= new MatrixCursor(columns);
//        startManagingCursor(matrixCursor);
//
//        matrixCursor.addRow(new Object[]{"Item A", "1"});
//        matrixCursor.addRow(new Object[]{"Item B", "2"});
//
//        listAdapter = new SimpleCursorAdapter(
//                this,
//                R.layout.task_view,
//                matrixCursor,
//                new String[] { "TASK" },
//                new int[]{R.id.taskTextView},
//                0
//        );
//
//        this.setListAdapter(listAdapter);
    }

    public void onDoneButtonClick(View view) {
        View v = (View) view.getParent();
        TextView taskTextView = (TextView) v.findViewById(R.id.taskTextView);
        String task = taskTextView.getText().toString();
        del.deleteList(task);
        updateUI();
    }

    public void enterList(View v) {
        TextView tv = (TextView) v;
        Intent i=new Intent();
        i.putExtra("ListName",tv.getText());
        i.setClass(this,ListItemActivity.class);
        startActivity(i);

    }


}