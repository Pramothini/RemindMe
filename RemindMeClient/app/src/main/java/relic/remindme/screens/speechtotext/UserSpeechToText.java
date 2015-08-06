package relic.remindme.screens.speechtotext;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import relic.remindme.R;
import relic.remindme.screens.listitems.ListItemActivity;

public class UserSpeechToText extends Activity {

    protected static final int RESULT_SPEECH = 1;

    private ImageButton btnSpeak;
    private TextView txtText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.control_user_speech_to_text);
        setTitle("Remind Me");

        String listname = "hello";
        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            listname = extras.getString("lisname");
            Log.e("App", "Speech Listname1 : " + listname);

        }

        txtText = (TextView) findViewById(R.id.txtText);


        btnSpeak = (ImageButton) findViewById(R.id.btnSpeak);

        btnSpeak.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(
                        RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");

                try {
                    startActivityForResult(intent, RESULT_SPEECH);
                    txtText.setText("");
                } catch (ActivityNotFoundException a) {
                    Toast t = Toast.makeText(getApplicationContext(),
                            "Opps! Your device doesn't support Speech to Text",
                            Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RESULT_SPEECH: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> text = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    txtText.setText(text.get(0));

                    String spoken = text.get(0);



                    Intent next = new Intent();
                    String listname = "List1";
                    Bundle extras = getIntent().getExtras();
                    if (extras != null)
                    {
                        listname = extras.getString("lisname");
                        Log.e("App", "QR CODE Listname2 : " + listname);
                    }
                    next.setClass(this.getApplicationContext(), ListItemActivity.class);
                    next.putExtra("SpokenWord", spoken);
                    next.putExtra("ListName", listname);
                    startActivity(next);

                }
                break;
            }

        }
    }
}
