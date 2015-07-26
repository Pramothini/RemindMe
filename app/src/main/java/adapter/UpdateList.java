package adapter;

import java.util.ArrayList;
import java.util.Date;

import entities.ListItem;

/**
 * Created by Santosh on 25-07-2015.
 */
public interface UpdateList {


    public boolean updateListName(String listName, String newlistName);
    public boolean updateUserId(String userId, String newUserId);
    public boolean updateCreatedDate(Date createdDate, Date newcreatedDate);
    public boolean updateListItemName(String listItemName, String newlistItemName);

}
