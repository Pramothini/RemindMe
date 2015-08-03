package entities;

import java.io.Serializable;

/**
 * Created by pramothinidk on 7/18/15.
 */
public class ListItem implements Serializable {

    private static final long serialVersionUID = 1L;

    private String listName;
    private boolean isSwiped;

    public ListItem() {
    }

    public ListItem(String listname) {
        this.listName = listname;
        isSwiped = false;
    }

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
