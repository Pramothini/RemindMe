// DatabaseConnector.java
// Provides easy connection and creation of UserContacts database.
package DBLayout;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

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

    public static final String List_Item = "LISTITEM";
    public static final String COL_5 = "itemid";
    public static final String COL_6 = "listid";

    public static final String Notification = "NOTIFICATION";
    public static final String COL_7 = "alarm_id";
    public static final String COL_8 = "listid2";
    public static final String COL_9 = "recurring_days";

    public static final String AlarmTypeLocation = "ALARMLOCATION";
    public static final String COL_10 = "alarm_id_location";
    public static final String COL_11 = "alarm_id2";
    public static final String COL_12 = "location";

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

    public boolean insertListItem(Integer listid) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_6, listid);
        open(); // open the database
        long result = database.insert(List, null, contentValues);
        close(); // close the database
        if (result == -1)
            return false;
        else
            return true;

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
//    public boolean insertAlarmTypeLocation(Integer alarm2, String location){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COL_11, alarm2);
//        contentValues.put(COL_12, location);
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
//    /*to get all List Items*/
//    public Cursor getAllList() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor res = db.rawQuery("select * from "+List_Item, null);
//        return res;
//
//    }
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
//    public Cursor deleteList(Integer id) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor res = db.rawQuery("delete from List where (List.id='"+id+"')",null);
//        return res;
//    }
//
//    /*delete records in ListItem*/
//
//    public Cursor deleteListItem(Integer id) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor res = db.rawQuery("delete from ListItem where (ListItem.itemid='"+id+"')",null);
//        return res;
//    }
//
//    /*find list by list name*/
//
//    public Cursor findListbyListName(String listname) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor res = db.rawQuery("select * from List where (List.listname='"+listname+"')", null);
//        return  res;
//    }
//
//     /*find list by list created date*/
//
//    public Cursor findListbyListName(Date date) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor res = db.rawQuery("select * from List where (List.created_date='"+date+"')", null);
//        return  res;
//    }


   private class DatabaseOpenHelper extends SQLiteOpenHelper {
        public static final String List = "LIST";
        public static final String COL_1 = "id";
        public static final String COL_2 = "userid";
        public static final String COL_3 = "date";
        public static final String COL_4 = "listname";

        public static final String List_Item = "LISTITEM";
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
            db.execSQL("create table " + List_Item + " (itemid INTEGER PRIMARY KEY AUTOINCREMENT, listid INTEGER, FOREIGN KEY (listid) REFERENCES List(id)");
//            db.execSQL("create table " + Notification + " (alarmid INTEGER PRIMARY KEY AUTOINCREMENT, listid2 INTEGER, recdays INTEGER, FOREIGN KEY (listid2) REFERENCES List(id)");
//            db.execSQL("create table " + AlarmTypeLocation + " (alarmid_location INTEGER PRIMARY KEY AUTOINCREMENT, alarmid2 INTEGER, location String, FOREIGN KEY (alarmid2) REFERENCES Notification(alarmid)");
//            db.execSQL("create table " + AlarmTypeDate + " (alarmid_date INTEGER PRIMARY KEY AUTOINCREMENT, alarmid3 INTEGER, date Date, FOREIGN KEY (alarmid2) REFERENCES Notification(alarmid)");
//            db.execSQL("create table " + AlarmTypeSound + " (alarmid_sound INTEGER PRIMARY KEY AUTOINCREMENT, alarmid4 INTEGER, sound String, FOREIGN KEY (alarmid2) REFERENCES Notification(alarmid)");

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


