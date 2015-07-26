package adapter;

import java.util.ArrayList;
import java.util.Date;

import entities.ListItem;

/**
 * Created by Administrator on 25-07-2015.
 */
public interface CreateList {

    public boolean createListName(int index, String listName);
    public boolean createUserId(int index, String userId);
    public boolean createCreatedDate(int index, Date createdDate);
    public boolean createListItem(int index, ArrayList<ListItem> listItem);
    public boolean createListItemName(int index, int index1, String listItemName);

}
