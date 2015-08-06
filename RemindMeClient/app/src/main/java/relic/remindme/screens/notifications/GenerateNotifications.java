package relic.remindme.screens.notifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Calendar;

import relic.remindme.R;
import relic.remindme.screens.listitems.ListItemActivity;

/* This class generates the push notification
 */
public class GenerateNotifications extends ActionBarActivity {

    public static NotificationManager mManager;
    String listname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            listname = extras.getString("Listname");
            Log.e("App", "SRISH Listname1 : " + listname);
        }


    }

     /* Context is passed
    * Notification Builder sets the notification constructor
    * Pending intend is got and notification flags set
    * Notification is updated with the latest event info
    * notify() is called with the notification details
    */

    /**
     *
     * @param context
     */
    @SuppressWarnings("static-access")
    public static void generateNotification(Context context) {

        Calendar calendar = Calendar.getInstance();
        Log.i("App", "Hour : " + calendar.get(Calendar.HOUR_OF_DAY));
        Log.i("App", "Min : " + calendar.get(Calendar.MINUTE));
        Log.i("App", "Day : " + calendar.get(Calendar.DAY_OF_MONTH));
        Log.i("App", "Month : " + calendar.get(Calendar.MONTH));
        Log.i("App", "Year : " + calendar.get(Calendar.YEAR));
        Log.i("App", "====================");

        mManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        Intent intent1 = new Intent(context, ListItemActivity.class);

        Notification.Builder builder = new Notification.Builder(context);

        builder.setContentText("This is a test message!")
                .setSmallIcon(R.drawable.ic_launcher)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setWhen(System.currentTimeMillis());

        Notification notification = builder.build();
        intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingNotificationIntent = PendingIntent.getActivity(context, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        notification.flags |= Notification.FLAG_AUTO_CANCEL;


        notification.setLatestEventInfo(context, "Remind Me", "Click to Open", pendingNotificationIntent);
        mManager.notify(0, notification);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_generate, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
