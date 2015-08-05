package relic.remindme;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;


import java.util.Calendar;

/**
 * Created by srish on 8/5/15.
 */
public class AlarmUtil {

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
            Intent intent1 = new Intent(context, SetTime.class);
            Notification notification = new Notification(R.drawable.ic_launcher,"This is a test message!", System.currentTimeMillis());
            intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP| Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingNotificationIntent = PendingIntent.getActivity(context,0, intent1,PendingIntent.FLAG_UPDATE_CURRENT);
            notification.flags |= Notification.FLAG_AUTO_CANCEL;
            notification.setLatestEventInfo(context, "AlarmManagerDemo", "This is a test message!", pendingNotificationIntent);
            mManager.notify(0, notification);
        }

}
