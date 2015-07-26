package dblayout;
import android.content.ContentValues;
import android.content.Context;

import java.sql.Date;
import java.util.ArrayList;

import dblayout.DatabaseConnector;
import entities.List;
import entities.ListItem;


/**
 * Created by Santosh on 25-07-2015.
 */
public class DbAdapter {

    DatabaseConnector dc = new DatabaseConnector();


    List list1 = new List();
    ListItem listItem1 = new ListItem();

    /*creating new entries for ListItem table*/

    public void createListItemRecords(String listName, ListItem listitem){

        System.out.println(" creating entry into ListItemTable ");
        listItem1 = listitem;

        int listid = list1.getListId(listName); // this is the unique key that should come from list table)

        String name = listItem1.getListItemName();


        String insertlistitemquery= "INSERT INTO listitemtable() VALUES (NULL,'"+listid+"', '"+name+"')";

        try{
            dc.startDb();
            dc.insertQuery(insertlistitemquery);
            dc.viewRecords("INSERTION");
        } catch(Exception e){
            System.err.println(e);
        }
    }

    public void updateListItemRecords(String listName, ListItem listItem){

        System.out.println(" updating entry into ListItemTable ");
        listItem1 = listItem;

        int listid = list1.getListId(listName); // this is the unique key that should come from list table)

        String name = listItem1.getListItemName();

        //String updatequery= "UPDATE listitemtable() SET (NULL,'"+listid+"', '"+name+"')";

    /*
        try{
            dc.startDb();
            dc.insertQuery();
            dc.viewRecords("INSERTION");
        } catch(Exception e){
            System.err.println(e);
        }*/
    }

    public void deleteRecords(String listName, ListItem listItem){

        System.out.println(" updating entry into ListItemTable ");
        listItem1 = listItem;

        int listid = list1.getListId(listName); // this is the unique key that should come from list table)

        String name = listItem1.getListItemName();


        //String deletequery= "DELETE FROM listitem where (listitem.name='...')";


    }




}
