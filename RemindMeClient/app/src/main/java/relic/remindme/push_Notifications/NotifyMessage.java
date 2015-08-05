package relic.remindme.push_Notifications;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import relic.remindme.R;

public class NotifyMessage extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView txt=new TextView(this);

        txt.setText("Activity after click on notification");
        setContentView(txt);
    }
}
