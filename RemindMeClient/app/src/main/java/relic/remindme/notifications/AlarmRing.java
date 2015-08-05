package relic.remindme.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import relic.remindme.R;

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
