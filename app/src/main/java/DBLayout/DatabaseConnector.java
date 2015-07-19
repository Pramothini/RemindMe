package DBLayout;
import entities.List;
import entities.List;
import entities.ListItem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;
import java.text.DateFormat;

/**
 * Created by Santosh on 18-07-2015.
 */
public class DatabaseConnector extends SQLiteOpenHelper{

    List list1 = new List();
    ListItem listItem1 = new ListItem();

    public static final String DATABASE_NAME="remindme.db";

    public static final String List = "LIST";
    public static final String COL_1 = "id";
    public static final String COL_2 = "userid";
    public static final String COL_3 = "date";
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

    public DatabaseConnector(Context context){

        super(context, DATABASE_NAME, null, 1);


    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + List + " (id INTEGER PRIMARY KEY AUTOINCREMENT, userid INTEGER, created_date DATETIME, listname TEXT)");
        db.execSQL("create table " + List_Item + " (itemid INTEGER PRIMARY KEY AUTOINCREMENT, listid INTEGER, FOREIGN KEY (listid) REFERENCES List(id)");
        db.execSQL("create table " + Notification + " (alarmid INTEGER PRIMARY KEY AUTOINCREMENT, listid2 INTEGER, recdays INTEGER, FOREIGN KEY (listid2) REFERENCES List(id)");
        db.execSQL("create table " + AlarmTypeLocation + " (alarmid_location INTEGER PRIMARY KEY AUTOINCREMENT, alarmid2 INTEGER, location String, FOREIGN KEY (alarmid2) REFERENCES Notification(alarmid)");
        db.execSQL("create table " + AlarmTypeDate + " (alarmid_date INTEGER PRIMARY KEY AUTOINCREMENT, alarmid3 INTEGER, date Date, FOREIGN KEY (alarmid2) REFERENCES Notification(alarmid)");
        db.execSQL("create table " + AlarmTypeSound + " (alarmid_sound INTEGER PRIMARY KEY AUTOINCREMENT, alarmid4 INTEGER, sound String, FOREIGN KEY (alarmid2) REFERENCES Notification(alarmid)");

    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + List);
        db.execSQL("DROP TABLE IF EXISTS " + List_Item);
        db.execSQL("DROP TABLE IF EXISTS "+ Notification);
        onCreate(db);

    }

    /*to insert data in List table*/
    public boolean insertList(Integer userid, String date, String listanme){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,userid);
        contentValues.put(COL_3, date);
        contentValues.put(COL_4, listanme);

        long result = db.insert(List,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    /*to insert data in List Items*/

    public boolean insertListItem(Integer listid){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_6, listid);
        long result = db.insert(List_Item,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    /* to insert data in Notification */

    public boolean insertNotification(Integer listid2, Integer frequency){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_8, listid2);
        contentValues.put(COL_9, frequency);
        long result = db.insert(Notification,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public boolean insertAlarmTypeLocation(Integer alarm2, String location){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_11, alarm2);
        contentValues.put(COL_12, location);
        long result = db.insert(AlarmTypeLocation,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public boolean insertAlarmTypeSound(Integer alarm3, String sound){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_14, alarm3);
        contentValues.put(COL_15, sound);
        long result = db.insert(AlarmTypeSound,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public boolean insertAlarmTypeDate(Integer alarm4, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_17, alarm4);
        contentValues.put(COL_18, date);
        long result = db.insert(AlarmTypeDate,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;

    }



     /* to get all List records */

    public Cursor getAllListData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+List, null);
        return res;

    }

    /*to get all List Items*/
    public Cursor getAllList() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+List_Item, null);
        return res;

    }

    /*to update a records in List table*/

    public Cursor updateListTable(Integer id, Date date, String listname) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("update List VALUES('NULL, '" + id + "' ,'" + date + "','" + listname + "')", null);
        return res;
    }

    /*to update records in ListItem table*/
    public Cursor updateListItemTable(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("update ListItem VALUES('NULL,'" + id + "')", null);
        return res;
    }

    /*delete records in List */

    public Cursor deleteList(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("delete from List where (List.id='"+id+"')",null);
        return res;
    }

    /*delete records in ListItem*/

    public Cursor deleteListItem(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("delete from ListItem where (ListItem.itemid='"+id+"')",null);
        return res;
    }

    /*find list by list name*/

    public Cursor findListbyListName(String listname) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from List where (List.listname='"+listname+"')", null);
        return  res;
    }

     /*find list by list created date*/

    public Cursor findListbyListName(Date date) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from List where (List.created_date='"+date+"')", null);
        return  res;
    }



}
