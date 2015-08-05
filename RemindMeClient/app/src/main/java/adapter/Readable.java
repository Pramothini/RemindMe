package adapter;

import java.util.ArrayList;

import entities.List_entity;

/**
 * Created by pramothinidk on 8/5/15.
 */
public interface Readable {
    public ArrayList<String> getAllListItems(String listname);
    public  ArrayList<List_entity> getallLists();
}
