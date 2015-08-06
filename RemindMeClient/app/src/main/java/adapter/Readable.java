package adapter;

import java.util.ArrayList;

import entities.List_entity;

/**
 * Created by pramothinidk on 8/5/15
 * API to read list and list item values from the database
 */
public interface Readable {
    /**
     * Used to read the list items from db
     * @param listname
     * @return
     */
    public ArrayList<String> getAllListItems(String listname);

    /**
     * Used to get all the list items from the db
     * @return
     */
    public  ArrayList<List_entity> getallLists();
}
