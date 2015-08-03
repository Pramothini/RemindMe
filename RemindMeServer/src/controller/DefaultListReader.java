package controller;


import java.util.ArrayList;

import adapter.ListLab;
import entities.ListItem;
import entities.List_entity;

import java.io.Serializable;


/**
 * Created by Administrator on 30-07-2015.
 */
public class DefaultListReader implements Serializable{

    private static final long serialVersionUID = 1L;

    private static ArrayList<List_entity> list1 = new ArrayList<>();


    List_entity list_entity;
    ArrayList<ListItem> listItemArrayList = new ArrayList<>();
    ArrayList<String> listItemsinList = new ArrayList<>();


    /*setting up all list lab*/
    public void setListLabObjects(ListLab lab) {
       list1 = lab.getMlist();
    }

    /*setting up latest list*/
    public void setLatestListObject(ListLab lab) {
        list_entity = lab.getLatestList();
    }

    public List_entity getLatestListObject(){
        return list_entity;
    }

    public void printListLabObjects(ListLab lab){
        int size= lab.getMlist().size();
        System.out.println("size of list lab: "+size);
    }

    /* getter*/

    public ArrayList<List_entity> getList(){
        return list1;
    }



    /*setter*/
    public boolean setListItems(String listName,ArrayList<String> listitems){
        for (String item:listitems)
            listItemArrayList.add(new ListItem(item));
        list_entity = new List_entity(listName,listItemArrayList);
        return true;
    }

    /*getters*/

    public String getListName(){
        return list_entity.getListName();
    }

    public ArrayList<String> getListItems() {
        for(int i=0; i< listItemArrayList.size();i++)

            listItemsinList.add(i, listItemArrayList.get(i).getListName());

        return listItemsinList;

    }


}
