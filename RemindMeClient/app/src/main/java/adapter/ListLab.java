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

    private List_entity latestlist;

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



    public List_entity getallLists(){
        List_entity listentities = new List_entity();
        try {
            DefaultSocketClient ds = new DefaultSocketClient(new Socket("10.0.2.2", 4444));
            if(ds.openConnection()) {
                listentities = ds.getAllLists();
                ds.closeSession();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listentities;
    }


    /**
     * creates  and returns the list_entity object based on the values entered by the user
     * @param listName - name of the list
     * @param listitems
     */
    public boolean createList(String listName,ArrayList<String> listitems){
        Log.e("pinky","inside createList() in list lab class.. start");
        ArrayList<ListItem> listItemArrayList = new ArrayList<>();
        for (String item:listitems)
            listItemArrayList.add(new ListItem(item));
        List_entity list_entity = new List_entity(listName,listItemArrayList);

        latestlist=list_entity;
        try {

            DefaultSocketClient ds = new DefaultSocketClient(new Socket("10.0.2.2", 4444));
            if(ds.openConnection()) {
                ds.upload(list_entity);
                ds.closeSession();
            }
            Log.e("pinky", "inside createList() in list lab class.. end");
    } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        //create the object of default socket client and pass the
        // list_entity object
        // if list is successfully created, then return true
        return true;
    }

    public List_entity getLatestList(){
        return this.latestlist;
    }

}

