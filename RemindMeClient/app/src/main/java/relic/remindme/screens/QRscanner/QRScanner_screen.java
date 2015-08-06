package relic.remindme.screens.QRscanner;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import relic.remindme.screens.listitems.ListItemActivity;
import relic.remindme.R;

/* This class handles all the QR and Bar code functioning
 */
public class QRScanner_screen extends Activity {


    static final String ACTION_SCAN = "com.google.zxing.client.android.SCAN";

    /* Called when the activity is first created. */

    /**
     *
     * @param savedInstanceState
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.control_qrscanner__screen);
        setTitle("Remind Me");

        String listname = "hello";
        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            listname = extras.getString("lisname");
            Log.e("App", "QR CODE Listname1 : " + listname);
        }

    }

    /* When scan bar is clicked, a new activity is started
    *  PRODUCT MODE value is passed to the new activity */

    /**
     *
     * @param v
     */
    public void scanBar(View v) {
        try {
            Intent intent = new Intent(ACTION_SCAN);
            intent.putExtra("SCAN_MODE", "PRODUCT_MODE");
            startActivityForResult(intent, 0);
        } catch (ActivityNotFoundException anfe) {
            showDialog(QRScanner_screen.this, "No Scanner Found", "Download a scanner code activity?", "Yes", "No").show();
        }
    }

    /* When scan bar is clicked, a new activity is started
    *  PRODUCT MODE value is passed to the new activity */

    /**
     *
     * @param v
     */

    public void scanQR(View v) {
        try {
            Intent intent = new Intent(ACTION_SCAN);
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
            startActivityForResult(intent, 0);
        } catch (ActivityNotFoundException anfe) {
            showDialog(QRScanner_screen.this, "No Scanner Found", "Download a scanner code activity?", "Yes", "No").show();
        }
    }

    /* Takes the uri to scan the QR/Bar code */

    /**
     *
     * @param act
     * @param title
     * @param message
     * @param buttonYes
     * @param buttonNo
     * @return
     */
    private static AlertDialog showDialog(final Activity act, CharSequence title, CharSequence message, CharSequence buttonYes, CharSequence buttonNo) {
        AlertDialog.Builder downloadDialog = new AlertDialog.Builder(act);
        downloadDialog.setTitle(title);
        downloadDialog.setMessage(message);
        downloadDialog.setPositiveButton(buttonYes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Uri uri = Uri.parse("market://search?q=pname:" + "com.google.zxing.client.android");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                try {
                    act.startActivity(intent);
                } catch (ActivityNotFoundException anfe) {

                }
            }
        });
        downloadDialog.setNegativeButton(buttonNo, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        return downloadDialog.show();
    }

    /* The Result is stored in contents
     * Result is displayed as the Edit field text where new list items are added
     */

    /**
     * 
     * @param requestCode
     * @param resultCode
     * @param intent
     */
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                String contents = intent.getStringExtra("SCAN_RESULT");
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");


                Intent n = new Intent();
                String listname = "List1";
                Bundle extras = getIntent().getExtras();
                if (extras != null)
                {
                    listname = extras.getString("lisname");
                    Log.e("App", "QR CODE Listname2 : " + listname);
                }
                n.setClass(this.getApplicationContext(), ListItemActivity.class);
                n.putExtra("QR_item", contents);
                n.putExtra("ListName", listname);
                startActivity(n);
            }
        }
        }
    }









