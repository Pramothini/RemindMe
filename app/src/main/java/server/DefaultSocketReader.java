package server;

import java.io.ObjectOutputStream;
import java.util.ArrayList;

import adapter.ListLab;
import entities.ListItem;

/**
 * Created by Administrator on 25-07-2015.
 */
public class DefaultSocketReader implements ListServer {


    static ListLab listlab = null;


    public boolean setListItem(int index, ArrayList<ListItem> listItem){
        listlab.createListItem(index,listItem);
        return true;
    }
    public boolean setListItemName(int index, int index1, String listItemName){
        listlab.createListItemName(index,index1,listItemName);
        return true;
    }

    public ArrayList<ListItem> viewAllListItems(String listName){
        return listlab.viewAllListItems(listName);

    }

    public boolean editListItemName(String listItemName, String newlistItemName){
        listlab.updateListItemName(listItemName, newlistItemName);
        return true;
    }

    public boolean deleteListItem(String listItemName){
        listlab.removeListItem(listItemName);
        return true;
    }

    public void sendAllListItems (ObjectOutputStream os, ArrayList<ListItem> listItems){
        listlab.sendAllListItems(os,listItems);
}

   /* public void sendOneListItem(ObjectOutputStream os, String listItemName) {
        listlab.sendOneListItem(os,listItemName);

    }*/
}
