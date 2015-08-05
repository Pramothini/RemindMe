package entities;

/**
 * Created by pramothinidk on 8/4/15.
 */
public class Notifications {
    private int year;
    private int month;
    private int date;

    private int hour;
    private int minute;

    private double latitude;
    private double longitude;



    private int days_recur=0;

    public Notifications(int year, int month, int date, int hour, int
            minute, double latitude, double longitude, int
                                 days_recur) {
        this.year = year;
        this.month = month;
        this.date = date;
        this.hour = hour;
        this.minute = minute;
        this.latitude = latitude;
        this.longitude = longitude;

        this.days_recur = days_recur;
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

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getHour() {
        return hour;
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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getDays_recur() {
        return days_recur;
    }

    public void setDays_recur(int days_recur) {
        this.days_recur = days_recur;
    }
}
