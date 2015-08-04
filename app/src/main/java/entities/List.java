package entities;

import android.app.Notification;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by pramothinidk on 7/18/15.
 */
public class List {
    private String listName;
    private String userId;
    private Date createdDate;
    private ArrayList<ListItem> listitems;
    private ArrayList<Notifications> notifications;

    public List() {
        listName = "Groceries";
        createdDate = new Date();
        listitems = new ArrayList<>();
        for(int i = 0; i < 5;i++){
            ListItem li = new ListItem();
            listitems.add(li);
        }

    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
