package relic.remindme.screens.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AlarmRing extends BroadcastReceiver {



    @Override
    public void onReceive(Context arg0, Intent arg1) {


        Toast.makeText(arg0, "Alarm received!", Toast.LENGTH_LONG).show();

        try{
            Log.e("App", "SRISHTI: Reached Alarm Ring class!");

            GenerateNotification.generateNotification(arg0);
        }catch(Exception e){
            e.printStackTrace();
        }
    }



}
