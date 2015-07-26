package adapter;

import java.io.ObjectOutputStream;
import java.util.ArrayList;

import entities.ListItem;

/**
 * Created by Administrator on 25-07-2015.
 */
public interface SendList {


    public void sendAllListItems(ObjectOutputStream os, ArrayList<ListItem> listItems);

    //public void sendOneListItem(ObjectOutputStream os, String listItemName);
}
