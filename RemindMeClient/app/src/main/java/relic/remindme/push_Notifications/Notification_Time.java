package relic.remindme.push_Notifications;

/**
 * Created by srish on 8/4/15.
 */
public class Notification_Time {

    private int day;
    private int month;
    private int year;
    private int hour;
    private int minute;

    public int getHour() {
        return hour;
    }

    public Notification_Time(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public void setHour(int hour) {
        this.hour = hour;

    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
