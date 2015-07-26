package adapter;

import android.content.Context;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;

import entities.List;
import entities.ListItem;


/**
 * Contains an arraylist of students
 * maintains a singleton instance
 *
 */
public class ListLab {

    private ArrayList<List> mlist;

    private List list;


    /*list item*/
    ArrayList<ListItem> listItems = new ArrayList<ListItem>();



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

    //Santosh-default constructor for adapter class
    public ListLab(){

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




    /*Santosh: all create methods which we declared in CrearteList Interface*/


    public boolean createListName(int index, String listName) {

        //list.setListName(listName);
        mlist.get(index).setListName(listName);
        return true;
    }
    public boolean createUserId(int index, String userId) {

        //list.setUserId(userId);
        mlist.get(index).setUserId(userId);
        return true;
    }
    public boolean createCreatedDate(int index, Date createdDate){
        //list.setCreatedDate(createdDate);
        mlist.get(index).setCreatedDate(createdDate);
        return true;
    }

    public boolean createListItem(int index, ArrayList<ListItem> listItem) {
        mlist.get(index).setListItems(listItem);
        //this.listItems = listItem;
        return true;
    }

    public boolean createListItemName(int index, int index1, String listItemName) {
        mlist.get(index).getListItem(index1).setListItemName(listItemName);
        //listItems.get(index1).setListItemName(listItemName);
        return true;
    }

    /*Santosh: update list methods*/

    public boolean updateListName(String listName, String newlistName){

        for(int i=0; i<mlist.size(); i++){
            if(mlist.get(i).getListName().equals(listName))
                mlist.get(i).setListName(newlistName);
        }
        return true;
    }
    public boolean updateUserId(String userId, String newUserId){

        for(int i=0; i<mlist.size(); i++){
            if(mlist.get(i).getUserId().equals(userId))
                mlist.get(i).setUserId(newUserId);
        }
        return true;

    }
    public boolean updateCreatedDate(Date createdDate, Date newcreatedDate){

        for(int i=0; i<mlist.size(); i++){
            if(mlist.get(i).getCreatedDate().equals(createdDate))
                mlist.get(i).setCreatedDate(newcreatedDate);
        }
        return true;
    }
    public boolean updateListItemName(String listItemName, String newlistItemName){

        for(int i=0; i<mlist.size(); i++){
            for(int j=0; j<listItems.size();j++){
                if(mlist.get(i).getListitems().get(j).getListItemName().equals(listItemName))
                    mlist.get(i).getListitems().get(j).setListItemName(newlistItemName);
            }
        }
        return true;

    }

    /*Santosh remove list and list item methods*/

    public boolean removeList(String listName){
        for (int i=0; i<mlist.size(); i++){
            if(mlist.get(i).getListName().equals(listName)) {
                mlist.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean removeListItem(String listItemName){
        for(int i=0; i<mlist.size(); i++){
            for(int j=0; j<listItems.size();j++){
                if(mlist.get(i).getListitems().get(j).getListItemName().equals(listItemName)) {
                    mlist.get(i).getListitems().remove(j);
                    return true;
                }

            }
        }
        return false;
    }

    /*view options for both list and list items*/

    public ArrayList<List> viewAllLists(){
       return mlist;
    }
    public List viewList(String listName){
        for(int i=0; i<mlist.size(); i++){
        if(mlist.get(i).getListName().equals(listName))
            return mlist.get(i);
        }
        return null;
    }
    public ArrayList<ListItem> viewAllListItems(String listName){
        for(int i=0; i<mlist.size(); i++){
            if(mlist.get(i).getListName().equals(listName))
                return mlist.get(i).getListitems();
        }
        return null;
    }

    /* to send list and list items*/

    public void sendAllListItems(ObjectOutputStream os, ArrayList<ListItem> listItems){
        this.listItems = listItems;

        try {
            os.writeObject(this.listItems);
            os.flush();
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    //sending selected list to client it is dependent on how List is designed.
   /* public void sendOneListItem(ObjectOutputStream os, String listItemName){

        for(int i=0; i<mlist.size(); i++){
            for (int j=0; j<listItems.size(); j++){
                if(mlist.get(i).getListItem(j).getListItemName().equals(listItemName))
                    os.writeObject(this);
            }
        }

    }*/

}

