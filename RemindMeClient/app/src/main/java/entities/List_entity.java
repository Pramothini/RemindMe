package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
/**
 * Created by pramothinidk on 7/18/15.
 */
public class List_entity implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String listName;
	private String userId;
	private String createdDate;
	private String listoperation;
	private ArrayList<ListItem> listitems;
	private Notifications notification;

	public List_entity() {
//		listName = "Groceries";
//		createdDate = new Date().toString();
//		listitems = new ArrayList<ListItem>();
//		for (int i = 0; i < 5; i++) {
//			ListItem li = new ListItem();
//			listitems.add(li);
//		}
	}
	public List_entity(String name,ArrayList<ListItem> listitems) {
		listName = name;
		createdDate = new Date().toString();
		this.listitems = listitems;
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

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getListoperation() {
		return listoperation;
	}

	public void setListoperation(String listoperation) {
		this.listoperation = listoperation;
	}

	public Notifications getNotification() {
		return notification;
	}

	public void setNotification(Notifications notification) {
		this.notification = notification;
	}

	/*to add listitems*/

	public void setListItems(ArrayList<ListItem> listitems){
		this.listitems=listitems;
	}

	public ArrayList<ListItem> getListItems(){
		return this.listitems;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
