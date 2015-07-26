package entities;

/**
 * Created by pramothinidk on 7/18/15.
 */
public class ListItem {
    private String listItemName;
    private boolean isSwiped;

    public ListItem() {
        listItemName = "Milk";
        isSwiped = false;
    }

    public String getListItemName() {
        return listItemName;
    }

    public void setListItemName(String listItemName) {
        this.listItemName = listItemName;
    }

    public boolean isSwiped() {
        return isSwiped;
    }

    public void setIsSwiped(boolean isSwiped) {
        this.isSwiped = isSwiped;
    }
}
