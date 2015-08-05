package relic.remindme;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
//import com.google.android.gms.common.ConnectionResult;
//import com.google.android.gms.common.GooglePlayServicesUtil;
//import com.google.android.gms.maps.CameraUpdate;
//import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.SupportMapFragment;
//import com.google.android.gms.maps.model.LatLng;
//import android.location.LocationListener;
//import android.location.LocationManager;
//import android.content.Context;
//import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Calendar;
import java.util.LinkedList;


import entities.Notifications;
import relic.remindme.push_Notifications.Notification_Time;
import relic.remindme.push_Notifications.NotifyMessage;


public class NotificationSettings extends Activity{

    //new
    private TextView tvDisplayDate;
    private DatePicker dpResult;
    private Button btnChangeDate;
    private int i = 0;

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    static final int DATE_DIALOG_ID = 999;

    private static final int NOTIFY_ME_ID = 1337;

    private TextView textViewTime;
    private TimePicker timePicker;
    private Button button;
    private String listname;

    static final int TIME_DIALOG_ID = 990;

    private Button Change_Location;
    public static LinkedList<Notification_Time> notification_times = new LinkedList<Notification_Time>();

    @Override
    protected void onNewIntent(Intent intent) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.controller_activity_notification_settings);
        setCurrentDateOnView();
        addListenerOnButton();

        setCurrentTimeOnView();
        addButtonListener();

        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            listname = extras.getString("ListName");
            Toast.makeText(this,"list name:" +listname , Toast.LENGTH_LONG).show();

        }

        Change_Location = (Button) findViewById(R.id.btnChangeLocation);
        Change_Location.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(), SetLocation.class);
                startActivity(i);
            }
        });

        compareDateTime(day, month, year, hour, minute);
    }

    @Override
    public void onBackPressed() {
        Notifications new_notification = new Notifications(year, month, day, hour, minute, 66, 67, 0);

    }

    // display current date
    public void setCurrentDateOnView() {

        tvDisplayDate = (TextView) findViewById(R.id.tvDate);
        dpResult = (DatePicker) findViewById(R.id.dpResult);

        // tvDisplayTime = (TextView) findViewById(R.id.tvTime);
        //tpResult = (TimePicker) findViewById(R.id.tpResult);

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

      /*  hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE); */

        // set current date into textview
        tvDisplayDate.setText(new StringBuilder()
                // Month is 0 based, just add 1
                .append(month + 1).append("-").append(day).append("-")
                .append(year).append(" "));

        // tvDisplayTime.setText(new StringBuilder().append(hour).append(":").append(minute).append(" "));

        // set current date into datepicker
        dpResult.init(year, month, day, null);
        //  tpResult.init(hour,minute);

    }

    public void addListenerOnButton() {

      /*  btnChangeTime = (Button) findViewById(R.id.btnChangeTime);

        btnChangeTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(TIME_DIALOG_ID);
            }
        });*/

        btnChangeDate = (Button) findViewById(R.id.btnChangeDate);

        btnChangeDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                showDialog(DATE_DIALOG_ID);

            }

        });

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                // set date picker as current date
                return new DatePickerDialog(this, datePickerListener,
                        year, month, day);
            case TIME_DIALOG_ID:
                // set time picker as current time
                return new TimePickerDialog(this, timePickerListener, hour, minute, false);

        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            // set selected date into textview
            tvDisplayDate.setText(new StringBuilder().append(month + 1)
                    .append("-").append(day).append("-").append(year)
                    .append(" "));

            // set selected date into datepicker also
            dpResult.init(year, month, day, null);

        }
    };

    //Time:

    // display current time

    public void setCurrentTimeOnView() {
        textViewTime = (TextView) findViewById(R.id.txtTime);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        final Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);
        // set current time into textview
        textViewTime.setText(new StringBuilder().append(padding_str(hour)).append(":").append(padding_str(minute)));
        // set current time into timepicker
        timePicker.setCurrentHour(hour);
        timePicker.setCurrentMinute(minute);

        Notification_Time nd = new Notification_Time(hour, minute);
        this.notification_times.add(nd);
    }

    public void addButtonListener() {
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(TIME_DIALOG_ID);
            }
        });
    }

    private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
            hour = selectedHour;
            minute = selectedMinute;
            // set current time into textview
            textViewTime.setText(new StringBuilder().append(padding_str(hour)).append(":").append(padding_str(minute)));
            // set current time into timepicker
            timePicker.setCurrentHour(hour);
            timePicker.setCurrentMinute(minute);

        }
    };

    private static String padding_str(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

    public void gotoSetLocation() {
        Intent intent = new Intent(NotificationSettings.this, SetLocation.class);
        startActivity(intent);
    }

    public void compareDateTime(int day, int month, int year, int hour, int minute) {

        //find current time an ddate
        final Calendar current_calendar = Calendar.getInstance();
        int current_hour = current_calendar.get(Calendar.HOUR_OF_DAY);
        int current_minute = current_calendar.get(Calendar.MINUTE);
        int current_year = current_calendar.get(Calendar.YEAR);
        int current_month = current_calendar.get(Calendar.MONTH);
        int current_day = current_calendar.get(Calendar.DAY_OF_MONTH);


        //compare both
        if (((current_day == day) && (current_month == month) && (current_year == year) && (current_hour == hour) && (current_minute == minute)) || ((current_hour == hour) && (current_minute == minute))) {
            Toast.makeText(this, "Match found", Toast.LENGTH_LONG).show();
            //if match--> send notification
            final NotificationManager mgr =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            Notification note = new Notification(R.drawable.stat_notify_chat,
                    "Android Example Status message!",
                    System.currentTimeMillis());

            // This pending intent will open after notification click
            PendingIntent i = PendingIntent.getActivity(getBaseContext(), 0,
                    new Intent(getBaseContext(), NotifyMessage.class),
                    0);

            note.setLatestEventInfo(getBaseContext(), "Android Example Notification Title",
                    "This is the android example notification message", i);

            //After uncomment this line you will see number of notification arrived
            //note.number=2;
            mgr.notify(NOTIFY_ME_ID, note);
        }

    }
}








