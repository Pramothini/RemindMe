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
import android.content.BroadcastReceiver;
import android.os.SystemClock;
import java.util.ArrayList;
import java.util.*;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
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
import relic.remindme.QRscanner.QRScanner_screen;
import relic.remindme.push_Notifications.Notification_Time;
import relic.remindme.push_Notifications.NotifyMessage;


public class NotificationSettings extends Activity {

   /* //new
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

        showNotification();
        //compareDateTime(day, month, year, hour, minute);

        // context variable contains your `Context`
        Context c = getApplicationContext();
        AlarmManager mgrAlarm = (AlarmManager) c.getSystemService(ALARM_SERVICE);
        ArrayList<PendingIntent> intentArray = new ArrayList<PendingIntent>();

        for(i = 0; i < 10; ++i)
        {
            Intent intent = new Intent(c, NotificationSettings.class);
            // Loop counter `i` is used as a `requestCode`
            PendingIntent pendingIntent = PendingIntent.getBroadcast(c, i, intent, 0);
            // Single alarms in 1, 2, ..., 10 minutes (in `i` minutes)
            mgrAlarm.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    SystemClock.elapsedRealtime() + 60000 * i,
                    pendingIntent);

            intentArray.add(pendingIntent);
        }

        Intent k =new Intent();
        k.setClass(this, SetTime.class);
    //    k.putExtra("lisname",listname);
        startActivity(k);

    }

    public void showNotification() {
        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, NotificationSettings.class), 0);
        Resources r = getResources();
        Notification notification = new NotificationCompat.Builder(this)
                .setTicker("Hello")
                .setSmallIcon(android.R.drawable.ic_menu_report_image)
                .setContentTitle("Hi")
                .setContentText("Hey")
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);
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

        */

      /*  hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE); */

    // set current date into textview
     /*   tvDisplayDate.setText(new StringBuilder()
                // Month is 0 based, just add 1
                .append(month + 1).append("-").append(day).append("-")
                .append(year).append(" "));

        // tvDisplayTime.setText(new StringBuilder().append(hour).append(":").append(minute).append(" "));

        // set current date into datepicker
        dpResult.init(year, month, day, null);
        //  tpResult.init(hour,minute);

    }

    public void addListenerOnButton() {

    */

      /*  btnChangeTime = (Button) findViewById(R.id.btnChangeTime);

        btnChangeTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(TIME_DIALOG_ID);
            }
        });*/

     /*   btnChangeDate = (Button) findViewById(R.id.btnChangeDate);

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
*/

    TimePicker myTimePicker;
    Button buttonstartSetDialog;
    TextView textAlarmPrompt;

    TimePickerDialog timePickerDialog;

    final static int RQS_1 = 1;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.controller_settime);

        textAlarmPrompt = (TextView) findViewById(R.id.alarmprompt);

        buttonstartSetDialog = (Button) findViewById(R.id.startSetDialog);
        buttonstartSetDialog.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                textAlarmPrompt.setText("");
                openTimePickerDialog(false);

            }
        });

    }


    private void openTimePickerDialog(boolean is24r) {
        Calendar calendar = Calendar.getInstance();

        timePickerDialog = new TimePickerDialog(
                NotificationSettings.this,
                onTimeSetListener,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                is24r);
        timePickerDialog.setTitle("Set Alarm Time");

        timePickerDialog.show();

    }

    TimePickerDialog.OnTimeSetListener onTimeSetListener
            = new TimePickerDialog.OnTimeSetListener() {

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            Calendar calNow = Calendar.getInstance();
            Calendar calSet = (Calendar) calNow.clone();

            calSet.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calSet.set(Calendar.MINUTE, minute);
            calSet.set(Calendar.SECOND, 0);
            calSet.set(Calendar.MILLISECOND, 0);

            if (calSet.compareTo(calNow) <= 0) {
                //Today Set time passed, count to tomorrow
                calSet.add(Calendar.DATE, 1);
            }

            setAlarm(calSet);
        }
    };

    private void setAlarm(Calendar targetCal) {

        textAlarmPrompt.setText(
                "\n\n***\n"
                        + "Alarm is set@ " + targetCal.getTime() + "\n"
                        + "***\n");

        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), RQS_1, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);

    }
}








