package adapter;

import android.content.Context;

import java.util.ArrayList;

import entities.List;


/**
 * Contains an arraylist of students
 * maintains a singleton instance
 *
 */
public class ListLab {
    private ArrayList<List> mlist;


    private static ListLab lListLab;
    private Context mAppContext;

    //private constructor to maintain singleton instance
    private ListLab(Context appContext) {
        mAppContext = appContext;
        mlist = new ArrayList<List>();
        for (int i = 0; i < 10; i++) {
            List l = new List();
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

    public ArrayList<List> getMlist() {
        return mlist;
    }

    public void setMlist(ArrayList<List> mlist) {
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
}

