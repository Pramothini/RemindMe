package adapter;

import java.util.ArrayList;

import entities.List;
import entities.ListItem;

/**
 * Created by Administrator on 25-07-2015.
 */
public interface ViewList {

    public ArrayList<List> viewAllLists();
    public List viewList(String listName);
    public ArrayList<ListItem> viewAllListItems(String listName);

}
