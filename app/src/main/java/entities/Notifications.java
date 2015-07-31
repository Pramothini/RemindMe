package entities;

/**
 * Created by srish on 7/22/15.
 */
public class Notifications {

    private String Location;
    private String Date;
    private String Time;
    private boolean isRecurring;
    private String audio;
    public enum repeat_Day {
        SUNDAY, MONDAY, TUESDAY, WEDNESDAY,
        THURSDAY, FRIDAY, SATURDAY
    };

    public Notifications(String Location, String Date, String time, boolean isRecurring)
    {

    }
}
