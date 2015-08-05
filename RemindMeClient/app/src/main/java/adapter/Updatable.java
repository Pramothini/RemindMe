package adapter;

import java.util.ArrayList;

/**
 * Created by pramothinidk on 8/5/15.
 * API to update list items associated with a list in the database
 */
public interface Updatable {
    public int savelistitems(String listname,ArrayList listitems);
}
