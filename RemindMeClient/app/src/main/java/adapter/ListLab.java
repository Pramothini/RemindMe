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
    DatabaseConnector db;

    //remember to maintain singleton instance
    public ListLab(Context appContext) {
        mAppContext = appContext;
        db = new DatabaseConnector(mAppContext);
//        mlist = new ArrayList<List_entity>();
//        for (int i = 0; i < 10; i++) {
//            List_entity l = new List_entity();
//            mlist.add(l);
//        }

    }

    //another private constructor
    public static ListLab get(Context c) {
        if (lListLab == null) {
            lListLab = new ListLab(c);
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

    public DatabaseConnector getDb() {
        return db;
    }

    public void setDb(DatabaseConnector db) {
        this.db = db;
    }

    public  ArrayList<List_entity> getallLists(){
        Log.e("ListLab","inside getallLists() in list lab class.. start");
        ArrayList<List_entity> listentities = new ArrayList<List_entity>();
        DatabaseConnector db = getDb();
        Cursor c = db.getAllList();
        listentities = createListEntities(c);
        if(listentities != null)
        Log.e("ListLab","inside getallLists() in list lab class.. listentitites and the first item in arrlistentity is ");
        else
            Log.e("ListLab","inside getallLists() in list lab class listentitites is null.. end");
        return listentities;
    }

    public int getListID(String listname){
        Log.e("ListLab", "inside getListID() in list lab class .. start. value of listname" + listname);
        boolean success = true;
        int listitemid = 1;
        getDb().open();
        int listid = db.getListID(listname);
        getDb().close();
        return listid;
    }

   public int savelistitems(String listname,ArrayList listitems){
       Log.e("ListLab","inside savelistitems() in list lab class .. start. value of listname"+listname+"value of listitems"+listitems.toString());
       boolean success = true;
       int listitemid = 1;

       getDb().open();
       int listid = db.getListID(listname);
       if(listid != -1) {
           deleteAllListItems(listid);
           for (Object itemname : listitems)
               listitemid = (int) db.insertListItem(listid, (String) itemname);
       }
       else {
           success = false;
       }
       db.close();
       Log.e("ListLab", "inside savelistitems() in list lab class and value of list item id from db is  " + listitemid + ".. end");
       return listitemid;
    }

    public int createNewList(String listname,String android_id){
        int latestListId;
        List_entity list_entity = new List_entity();
        DatabaseConnector db = getDb();
        db.open();
        latestListId = (int)db.insertNewList(listname,android_id);
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
        DatabaseConnector db = getDb();
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
        Log.e("ListLab", "inside createListEntities .. end and the first item in arrlistentity is ");
        return arrlistentity;
    }



    public void deleteList(String name){
        getDb().open();
        db.deleteList(name);
        db.close();
    }

    public ArrayList<String> getAllListItems(String listname) {
        int listid = getListID(listname);
        ArrayList<String> itemnames = new ArrayList<>();
        if (listid != -1) {
            getDb().open();
            Cursor c = getDb().getAllListItems(listid);
            if (c.moveToFirst())
                do {
                    itemnames.add(c.getString(c.getColumnIndex("itemname")));
                } while (c.moveToNext());

        }
        return  itemnames;
    }

    public void deleteAllListItems(int listid){
        getDb().open();
        getDb().deleteListItems(listid);
        getDb().close();
    }
}

