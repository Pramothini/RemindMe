// DatabaseConnector.java
// Provides easy connection and creation of UserContacts database.
package dBLayout;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import entities.ListItem;
import entities.List_entity;

public class DatabaseConnector {
    private SQLiteDatabase database; // database object
    private DatabaseOpenHelper databaseOpenHelper; // database helper

    List_entity list1 = new List_entity();
    ListItem listItem1 = new ListItem();

    public static final String DATABASE_NAME = "remindmeDb";

    public static final String List = "LIST";
    public static final String COL_1 = "id";
    public static final String COL_2 = "userid";
    public static final String COL_3 = "created_date";
    public static final String COL_4 = "listname";

    public static final String List_Item = "LSTITEM";
    public static final String COL_5 = "itemid";
    public static final String COL_6 = "listid";
    public static final String COL_19 = "itemname";

    public static final String Notification = "NOTIFICATION";
    public static final String COL_7 = "alarm_id";
    public static final String COL_8 = "listid2";
    public static final String COL_9 = "recurring_days";

    public static final String AlarmTypeLocation = "ALARMLOCATION";
    public static final String COL_10 = "alarm_id_location";
    public static final String COL_11 = "alarm_id2";
    public static final String COL_12 = "Location";

    public static final String AlarmTypeSound = "ALARMSOUND";
    public static final String COL_13 = "alarm_id_sound";
    public static final String COL_14 = "alarm_id3";
    public static final String COL_15 = "sound";

    public static final String AlarmTypeDate = "ALARMDATE";
    public static final String COL_16 = "alarm_id_date";
    public static final String COL_17 = "alarm_id4";
    public static final String COL_18 = "datentime";

    // public constructor for DatabaseConnector
    public DatabaseConnector(Context context) {
        // create a new DatabaseOpenHelper
        databaseOpenHelper =
                new DatabaseOpenHelper(context, DATABASE_NAME, null, 1);
    } // end DatabaseConnector constructor

    // open the database connection
    public void open() throws SQLException {
        // create or open a database for reading/writing
        database = databaseOpenHelper.getWritableDatabase();
    } // end method open

    // close the database connection
    public void close() {
        if (database != null)
            database.close(); // close the database connection
    } // end method close

//    public long insertNewNotification(Notifications n) {
////        long listid;
////        ContentValues contentValues = new ContentValues();
////        contentValues.put(COL_2, android_id);
////        contentValues.put(COL_3, "");
////        contentValues.put(COL_4, listname);
////        open(); // open the database
////        listid = database.insert(List, null, contentValues);
////        close(); // close the database
////        return listid;
//    }

    /*to insert data in List table*/
    public long insertNewList(String listname, String android_id) {
        long listid;
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, android_id);
        contentValues.put(COL_3, "");
        contentValues.put(COL_4, listname);
        open(); // open the database
        listid = database.insert(List, null, contentValues);
        close(); // close the database
        return listid;
    }

    public int getListID(String listname){
        int id = -1;
        open(); // open the database
        Cursor c = database.query(List,new String[] {"id"},"listname = ?",new String[] {listname},null
        ,null,null);
        if(c.moveToFirst()){
            id = c.getInt(c.getColumnIndex("id"));
        }

        close(); // close the database
        return id;
    }


    /*to insert data in List table*/
    public long insertList(List_entity listEntity) {
        long listid;
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, "1234");
        contentValues.put(COL_3, listEntity.getCreatedDate()+"");
        contentValues.put(COL_4, listEntity.getListName());
        open(); // open the database
        listid = database.insert(List, null, contentValues);
        close(); // close the database
        return listid;
    }

    /*to insert data in List Items*/

    public long insertListItem(int listid, String listname) {
        Log.e("DatabseConnector", " list item " + listname + "list id is" + listid);
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_6, listid);
        contentValues.put(COL_19, listname);
        open(); // open the database
        long result = database.insert(List_Item, null, contentValues);
        close();
        Log.e("DatabseConnector", " resullt " + result );
        return result;

//        if(result!= -1){
//            Log.e("DatabseConnector"," saved list item "+listname);
//            return true;
//        }
//        else{
//            Log.e("DatabseConnector"," not saved list item "+listname);
//            return false;
//        }
    }


    public Cursor getAllListItems(int listid){
        open();
        Cursor res = database.rawQuery("select * from "+List_Item+" where listid = "+listid, null);
        return res;
    }


    /* to insert data in Notification */

//    public boolean insertNotification(Integer listid2, Integer frequency){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COL_8, listid2);
//        contentValues.put(COL_9, frequency);
//        long result = db.insert(Notification,null,contentValues);
//        if(result==-1)
//            return false;
//        else
//            return true;
//    }
//
//    public boolean insertAlarmTypeLocation(Integer alarm2, String Location){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COL_11, alarm2);
//        contentValues.put(COL_12, Location);
//        long result = db.insert(AlarmTypeLocation,null,contentValues);
//        if(result==-1)
//            return false;
//        else
//            return true;
//    }
//
//    public boolean insertAlarmTypeSound(Integer alarm3, String sound){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COL_14, alarm3);
//        contentValues.put(COL_15, sound);
//        long result = db.insert(AlarmTypeSound,null,contentValues);
//        if(result==-1)
//            return false;
//        else
//            return true;
//    }
//
//    public boolean insertAlarmTypeDate(Integer alarm4, String date){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COL_17, alarm4);
//        contentValues.put(COL_18, date);
//        long result = db.insert(AlarmTypeDate,null,contentValues);
//        if(result==-1)
//            return false;
//        else
//            return true;
//
//    }
//
//
//
//     /* to get all List records */
//
//    public Cursor getAllListData() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor res = db.rawQuery("select * from "+List, null);
//        return res;
//
//    }
//
    /*to get all List Items*/
    public Cursor getAllList() {
        open();
        Cursor res = database.rawQuery("select * from "+List, null);
        return res;

    }


//
//    /*to update a records in List table*/
//
//    public Cursor updateListTable(Integer id, Date date, String listname) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor res = db.rawQuery("update List VALUES('NULL, '" + id + "' ,'" + date + "','" + listname + "')", null);
//        return res;
//    }
//
//    /*to update records in ListItem table*/
//    public Cursor updateListItemTable(Integer id) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor res = db.rawQuery("update ListItem VALUES('NULL,'" + id + "')", null);
//        return res;
//    }
//
//    /*delete records in List */
//
    public void deleteList(String listname) {
        open();
        database.delete("LIST", "listname=?", new String[]{listname});
        close();
    }

    public void deleteListItems(int listid) {
        open();
        database.delete(List_Item, "listid=?", new String[]{listid+""});
        close();
    }



   private class DatabaseOpenHelper extends SQLiteOpenHelper {
        public static final String List = "LIST";
        public static final String COL_1 = "id";
        public static final String COL_2 = "userid";
        public static final String COL_3 = "date";
        public static final String COL_4 = "listname";

        public static final String List_Item = "LSTITEM";
        public static final String COL_5 = "itemid";
        public static final String COL_6 = "listid";

        // public constructor
        public DatabaseOpenHelper(Context context, String name,
                                  CursorFactory factory, int version) {
            super(context, name, factory, version);
        } // end DatabaseOpenHelper constructor

        // creates the mortgage table when the database is created
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table " + List + " (id INTEGER PRIMARY KEY AUTOINCREMENT, userid INTEGER, created_date DATETIME, listname TEXT)");
            db.execSQL("create table " + List_Item + " (itemid INTEGER PRIMARY KEY AUTOINCREMENT,itemname Text, listid INTEGER, FOREIGN KEY (listid) REFERENCES LIST(id))");
          db.execSQL("create table " + Notification + " (notification_id INTEGER PRIMARY KEY AUTOINCREMENT, list_id INTEGER, latitude double, longitute double, day INTEGER, month INTEGER, year INTEGER, hour INTEGER, minute INTEGER, recdays INTEGER, FOREIGN KEY (list_id) REFERENCES LIST(id))");

        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + List);
            db.execSQL("DROP TABLE IF EXISTS " + List_Item);
//            db.execSQL("DROP TABLE IF EXISTS "+ Notification);
            onCreate(db);

        }
    } // end class DatabaseOpenHelper
}


