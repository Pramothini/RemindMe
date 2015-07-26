package server;

import java.io.ObjectOutputStream;
import java.util.ArrayList;

import entities.List;
import entities.ListItem;

/**
 * Created by Administrator on 25-07-2015. interface will be implemented in buildListModel
 */
public interface ListServer {

/* only list items methods*/

    public boolean setListItem(int index, ArrayList<ListItem> listItem);
    public boolean setListItemName(int index, int index1, String listItemName);

     public ArrayList<ListItem> viewAllListItems(String listName);

    public boolean editListItemName(String listItemName, String newlistItemName);

    public boolean deleteListItem(String listItemName);

    public void sendAllListItems (ObjectOutputStream os, ArrayList<ListItem> listItems);

    //public void sendOneListItem(ObjectOutputStream os, String listItemName);



}
