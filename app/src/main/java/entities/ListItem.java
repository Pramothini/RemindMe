package entities;

/**
 * Created by pramothinidk on 7/18/15.
 */
public class ListItem {
    private String ListName;
    private boolean isSwiped;

    public ListItem() {
        ListName = "Milk";
        isSwiped = false;
    }

    public String getListName() {
        return ListName;
    }

    public void setListName(String listName) {
        ListName = listName;
    }

    public boolean isSwiped() {
        return isSwiped;
    }

    public void setIsSwiped(boolean isSwiped) {
        this.isSwiped = isSwiped;
    }
}
