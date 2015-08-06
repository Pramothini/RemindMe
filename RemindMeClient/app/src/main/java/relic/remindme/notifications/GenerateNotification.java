package relic.remindme.notifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


import java.util.Calendar;

import entities.Notifications;
import relic.remindme.ListItemActivity;
import relic.remindme.R;


/**
 * Created by srish on 8/5/15.
 */
public class GenerateNotification {

    public static NotificationManager mManager;

    @SuppressWarnings("static-access")
    public static void generateNotification(Context context){
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
        intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP| Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingNotificationIntent = PendingIntent.getActivity(context,0, intent1,PendingIntent.FLAG_UPDATE_CURRENT);
        notification.flags |= Notification.FLAG_AUTO_CANCEL;


        notification.setLatestEventInfo(context, "RemindMe", "Click to Open", pendingNotificationIntent);
        mManager.notify(0, notification);
    }

}
