package entities;

import java.util.Date;

/**
 * Created by pramothinidk on 7/18/15.
 */
public class List {
    private String listName;
    private String userId;
    private Date createdDate;

    public List() {
        listName = "Groceries";
        createdDate = new Date();
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
