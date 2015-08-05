package adapter;

import android.content.Context;
import android.widget.EditText;

/**
 * Created by pramothinidk on 8/4/15.
 * Used to Manage CRUD operations for a list and its associated items
 */
public class Manage extends ListLab implements Creatable,Readable,Updatable,Deletable{
    public Manage(Context c){
        super(c);
    }

}
