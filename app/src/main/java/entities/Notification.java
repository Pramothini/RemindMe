package entities;

/**
 * Created by Administrator on 25-07-2015.
 */
public class Notification {

    private  int notificationId;


    public Notification(){
        notificationId = 1;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }
}
