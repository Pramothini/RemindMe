package relic.remindme;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import adapter.ListLab;
import entities.List_entity;

/**
 * Populates the list details of all the lists that the user had created in the home page
 */
public class HomePage extends ListActivity {
    private ArrayList<List_entity> mListEntity = new ArrayList<List_entity>();
    ListLab listlab;
    private ListAdapter listAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        updateUI();
    }



//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
////        ((CrimeAdapter)getListAdapter()).notifyDataSetChanged();
//    }


//    private class ListsAdapter extends ArrayAdapter<List_entity> {
//        public ListsAdapter(ArrayList<List_entity> students) {
//            super(getActivity(), android.R.layout.simple_list_item_1, students);
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            // if we weren't given a view, inflate one
//            if (null == convertView) {
//                convertView = getActivity().getLayoutInflater()
//                    .inflate(R.layout.controller_activity_homepage, null);
//            }
//
//
//            TextView idTextView = (TextView)convertView.findViewById(R.id.listName);
//
//
//                final List_entity l = getItem(position);
//
//                idTextView.setText(l.getListName() + "  ");
//            /**
//             * To make the search visible only before the first item of the list
//             */
//            if(position != 0 ){
//                RelativeLayout rl = (RelativeLayout)convertView.findViewById(R.id.searchLayout);
//                if(rl.getVisibility() == View.VISIBLE)
//                     rl.setVisibility(View.GONE);
//            }
//
//            /**
//             * To make the add new list button visible only after the last list item
//             */
//            if(position == (mListEntity.size()-1)) {
//                Button donebtn = (Button) convertView.findViewById(R.id.addNewListBtn);
//                donebtn.setVisibility(View.VISIBLE);
//                donebtn.setOnClickListener(new View.OnClickListener() {
//                    public void onClick(View v) {
////                        int newlistid;
////                        ListLab listlab = new ListLab(getActivity());
////                        newlistid = listlab.createNewList();
////                        Toast toast = Toast.makeText(getActivity(), "New list is created and its id is"+newlistid,Toast.LENGTH_SHORT );
////                        Log.e("HomePage","inside on add new list button click, New list is created and its id is"+newlistid);
////                        toast.show();
//                        Intent i = new Intent(getActivity(), ListItemActivity.class);
//                        startActivity(i);
//                    }
//                });
//            }
//
//
//            RelativeLayout rlList = (RelativeLayout)convertView.findViewById(R.id.listRL);
//            rlList.setOnClickListener(new View.OnClickListener() {
//                public void onClick(View v) {
//
//                    Intent i = new Intent(getActivity(), ListItemActivity.class);
//                    startActivity(i);
//                }
//            });
//
//
//            return convertView;
//        }
//    }

    //new design
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
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

                        // insert a new list in the UI
//                        helper = new TaskDBHelper(MainActivity.this);
//                        SQLiteDatabase db = helper.getWritableDatabase();
//                        ContentValues values = new ContentValues();
//
//                        values.clear();
//                        values.put(TaskContract.Columns.TASK,task);
//
//                        db.insertWithOnConflict(TaskContract.TABLE,null,values,SQLiteDatabase.CONFLICT_IGNORE);
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
//        helper = new TaskDBHelper(MainActivity.this);
//        SQLiteDatabase sqlDB = helper.getReadableDatabase();
//        Cursor cursor = sqlDB.query(TaskContract.TABLE,
//                new String[]{TaskContract.Columns._ID, TaskContract.Columns.TASK},
//                null, null, null, null, null);


//       ListLab listlab = new ListLab(this);
//        Log.e("HomePage_GetList", "printing listlab object"+listlab);
//        if(listlab != null) {
//            mListEntity = listlab.getallLists();
//            ArrayList<String> listnames= new ArrayList<String>();
//            for(List_entity s:mListEntity)
//                listnames.add(s.getListName());
//
//            //comment this later
//            listnames.add("dummy");
//
//            if (mListEntity != null && mListEntity.size() > 0) {
//                Log.e("HomePage_GetList", "mlistentity is not null and " + mListEntity.get(0) + " is the first list item");
//                listAdapter = new ArrayAdapter(this,R.layout.task_view,listnames);
//                setListAdapter(listAdapter);
//            } else {
//                Log.e("HomePage_GetList", "mlistentity is null ");
//            }
//        }
//        else{
//            Log.e("HomePage_GetList", "listlab is null ");
//        }


        String[] columns = new String[] { "TASK", "_ID"};

        MatrixCursor matrixCursor= new MatrixCursor(columns);
        startManagingCursor(matrixCursor);

        matrixCursor.addRow(new Object[]{"Item A", "1"});
        matrixCursor.addRow(new Object[]{"Item B", "2"});

        listAdapter = new SimpleCursorAdapter(
                this,
                R.layout.task_view,
                matrixCursor,
                new String[] { "TASK" },
                new int[]{R.id.taskTextView},
                0
        );

        this.setListAdapter(listAdapter);
    }

    public void onDoneButtonClick(View view) {
        View v = (View) view.getParent();
        TextView taskTextView = (TextView) v.findViewById(R.id.taskTextView);
        String task = taskTextView.getText().toString();

//        String sql = String.format("DELETE FROM %s WHERE %s = '%s'",
//                TaskContract.TABLE,
//                TaskContract.Columns.TASK,
//                task);


//        helper = new TaskDBHelper(MainActivity.this);
//        SQLiteDatabase sqlDB = helper.getWritableDatabase();
//        sqlDB.execSQL(sql);
        updateUI();
    }

    public void enterList(View v) {

//        Intent i=new Intent();
//        i.setClass(this,List_Screen.class);
//        startActivity(i);

    }


}

