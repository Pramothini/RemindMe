package adapter;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import DBLayout.DatabaseConnector;
import controller.DefaultSocketClient;
import entities.ListItem;
import entities.List_entity;
import relic.remindme.R;


/**
 * Contains an arraylist of students
 * maintains a singleton instance
 *
 */
public class ListLab  {

    private ArrayList<List_entity> mlist;


    private static ListLab lListLab;
    private Context mAppContext;

    //remember to maintain singleton instance
    public ListLab(Context appContext) {
        mAppContext = appContext;
        mlist = new ArrayList<List_entity>();
        for (int i = 0; i < 10; i++) {
            List_entity l = new List_entity();
            mlist.add(l);
        }

    }


    //another private constructor
    public static ListLab get(Context c) {
        if (lListLab == null) {
            lListLab = new ListLab(c.getApplicationContext());
        }
        return lListLab;
    }

    public ArrayList<List_entity> getMlist() {
        return mlist;
    }

    public void setMlist(ArrayList<List_entity> mlist) {
        this.mlist = mlist;
    }

    public static ListLab getlListLab() {
        return lListLab;
    }

    public static void setlListLab(ListLab lListLab) {
        ListLab.lListLab = lListLab;
    }

    public Context getmAppContext() {
        return mAppContext;
    }

    public void setmAppContext(Context mAppContext) {
        this.mAppContext = mAppContext;
    }



    public  ArrayList<List_entity> getallLists(){
        Log.e("ListLab","inside getallLists() in list lab class.. start");
        ArrayList<List_entity> listentities = new ArrayList<List_entity>();
        DatabaseConnector db = new DatabaseConnector(mAppContext);
        Cursor c = db.getAllList();
        listentities = createListEntities(c);
        if(listentities != null)
        Log.e("ListLab","inside getallLists() in list lab class.. listentitites and the first item in arrlistentity is "+listentities.get(0).getListName());
        else
            Log.e("ListLab","inside getallLists() in list lab class listentitites is null.. end");
        return listentities;
    }

    public int createNewList(){
        int latestListId;
        List_entity list_entity = new List_entity();
        DatabaseConnector db = new DatabaseConnector(mAppContext);
        db.open();
        latestListId = (int)db.insertNewList(list_entity);
        db.close();
        return latestListId;
    }

    /**
     * creates  and returns the list_entity object based on the values entered by the user
     * @param listName - name of the list
     * @param listitems
     */
    public long createList(String listName,ArrayList<String> listitems){
        long latestListId;
        Log.e("pinky","inside createList() in list lab class.. start");
        ArrayList<ListItem> listItemArrayList = new ArrayList<>();
        for (String item:listitems)
            listItemArrayList.add(new ListItem(item));
        List_entity list_entity = new List_entity(listName,listItemArrayList);
        DatabaseConnector db = new DatabaseConnector(mAppContext);
        db.open();
        latestListId = db.insertList(list_entity);
        db.close();
        return latestListId;
    }

    public ArrayList<List_entity> createListEntities(Cursor c){
        Log.e("ListLab","inside createListEntities .. start");
        ArrayList<List_entity> arrlistentity = new ArrayList<List_entity>();
        if(c.moveToFirst()){
            do{
                List_entity le = new List_entity();
                le.setId(c.getInt(c.getColumnIndex("id")));
                le.setListName(c.getString(c.getColumnIndex("listname")));
                le.setCreatedDate(c.getString(c.getColumnIndex("created_date")));
                arrlistentity.add(le);

            }while(c.moveToNext());
        }
//        else{
//            Log.e("ListLab","inside createListEntities and Cursor does not contain any objects..");
//            List_entity le = new List_entity();
//            le.setId(123);
//            le.setListName("dummy");
//            le.setCreatedDate("08/02/1015");
//            arrlistentity.add(le);
//
//        }
        Log.e("ListLab","inside createListEntities .. end and the first item in arrlistentity is "+arrlistentity.get(0).getListName());
        return arrlistentity;
    }


}

