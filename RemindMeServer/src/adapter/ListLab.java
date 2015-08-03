package adapter;



import java.util.ArrayList;
import java.util.List;

import DBLayout.DatabaseConnector;
import entities.ListItem;
import entities.List_entity;



/**
 * Contains an arraylist of students
 * maintains a singleton instance
 *
 */
public class ListLab {
	
    private ArrayList<List_entity> mlist;
    private List_entity latestlist;
    private static ListLab lListLab;
   
   


   

    public ArrayList<List_entity> getMlist() {
        return mlist;
    }

    public void setMlist(ArrayList<List_entity> mlist) {
        this.mlist = mlist;
    }

    public static ListLab getlListLab() {
        return lListLab;
    }

    public static void setlListLab(ListLab lListLab) {
        ListLab.lListLab = lListLab;
    }



    /**
     * creates  and returns the list_entity object based on the values entered by the user
     * @param listName - name of the list
     * @param listitems
     */
    public boolean createList(String listName,ArrayList<String> listitems){
        ArrayList<ListItem> listItemArrayList = new ArrayList<>();
        for (String item:listitems)
            listItemArrayList.add(new ListItem(item));
        List_entity list_entity = new List_entity(listName,listItemArrayList);
        //create the object of default socket client and pass the
        // list_entity object
        //DefaultSocketClient defaultSocketClient = new DefaultSocketClient()
        // if list is successfully created, then return true

        //defaultSocket.run();

        return true;

    }
    
    public List_entity getLatestList(){
        return this.latestlist;
    }

}

