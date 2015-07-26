package entities;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by pramothinidk on 7/18/15.
 */
public class List {
    private String listName;
    private String userId;
    private Date createdDate;

    private ArrayList<ListItem> listitems;

    private int listid;

    private List list;


    public List() {
        listName = "Groceries";
        createdDate = new Date();
        listitems = new ArrayList<>();
        for(int i = 0; i < 5;i++){
            ListItem li = new ListItem();
            listitems.add(li);
        }

    }

    //this will be required to get listId and map listItem against it
    public int getListId(String listName){
        if(list.getListName().equals(listName))
            return list.listid;
        else return -1;
    }
    public void setListId(int id){
        this.listid=id;
    }




    public String getListName() {
        return listName;
    }



    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }


    /*ListItems related functions*/

    public  void setListItems(ArrayList<ListItem> listitem){
        listitems = listitem;
    }

        /* to get all list items mapped against a list*/

    public ArrayList<ListItem> getListitems() {
        return  listitems;
    }

        /* to get one known list item*/

    public ListItem getListItem(int index){
        return listitems.get(index);
    }

        /* to get one particular unkown list item, can be used for search functions*/

    public ListItem getListItem(String name) {
        for (int i=0; i<listitems.size(); i++){
            if (listitems.get(i).getListItemName().equals(name))
                return listitems.get(i);
        }
        return null;
    }

    /* to avoid null pointer exception let's have one constructor to be on safer side*/

    public void List(String listName1, String userId1, Date createdDate1, int listitemsize){

        listName=listName1;
        userId=userId1;
        createdDate=createdDate1;
        for(int i=0; i<listitemsize;i++){
            ListItem listItem1 = new ListItem();
            listitems.add(i,listItem1);
        }

    }

}
