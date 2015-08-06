package adapter;

/**
 * Created by pramothinidk on 8/5/15.
 * API to delete items in the database
 */
public interface Deletable {

    /**
     * Used to delete list items
     * @param listid
     */
    public void deleteAllListItems(int listid);

    /**
     * Used to delete a list
     * @param name
     */
    public void deleteList(String name);
}

