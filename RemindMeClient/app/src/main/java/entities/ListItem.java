package entities;

import java.io.Serializable;

/**
 * Created by pramothinidk on 7/18/15.
 * Entity of the Items associated to a list
 * This object would be used to pass list item data between the CRUD method implementation and the
 * DB implementation
 */
public class ListItem implements Serializable {

    private static final long serialVersionUID = 1L;
    private String listName;
    private boolean isSwiped;

    //constructor
    public ListItem() {
    }

    //parameterised constuctor
    public ListItem(String listname) {
        this.listName = listname;
        isSwiped = false;
    }

    //getters and setters
    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public boolean isSwiped() {
        return isSwiped;
    }

    public void setIsSwiped(boolean isSwiped) {
        this.isSwiped = isSwiped;
    }
}
