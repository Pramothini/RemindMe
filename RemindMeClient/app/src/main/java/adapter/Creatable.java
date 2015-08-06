package adapter;

import java.util.ArrayList;

/**
 * Created by pramothinidk on 8/4/15.
 * API to create a list in the database
 */
public interface Creatable {

    /**
     * Used to create a new list
     * @param listname
     * @param android_id
     * @return
     */
    public int createNewList(String listname,String android_id);
    

}
