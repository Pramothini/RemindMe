package adapter;

import java.util.ArrayList;
import java.util.Date;

import entities.ListItem;

/**
 * Created by Santosh on 25-07-2015.
 */
public interface RemoveList {

    public boolean removeList(String listName);
    public boolean removeListItem(String listItemName);

}
