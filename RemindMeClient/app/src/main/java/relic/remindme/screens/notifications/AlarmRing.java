package relic.remindme.screens.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/* This is a receiver
 * On receving the context, it calls generateNotification()
 */

public class AlarmRing extends BroadcastReceiver {


    /* On receving the context, it calls generateNotification()
     * context is passed as an argument
     */

    /**
     * @param arg0
     * @param arg1
     */

    @Override
    public void onReceive(Context arg0, Intent arg1) {

        try {
            Log.e("App", "SRISHTI: Reached Alarm Ring class!");

            GenerateNotification.generateNotification(arg0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
