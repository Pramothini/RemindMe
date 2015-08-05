package adapter;

/**
 * Created by pramothinidk on 8/5/15.
 * API to delete items in the database
 */
public interface Deletable {

    public void deleteAllListItems(int listid);
    public void deleteList(String name);
}

