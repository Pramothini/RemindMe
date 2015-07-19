package relic.remindme;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Toast;

/**
 * Created by pramothinidk on 7/18/15.
 */
public class ListItemActivity extends Activity {
    GridLayout grid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.controller_activity_listitem);
        EditText listItem = (EditText) findViewById(R.id.listItem);
        grid = (GridLayout) findViewById(R.id.gridLayout);
        listItem.setText("New list item");
        setListeners(listItem);
    }


    public void onNotificationClick(View v){
        Intent i = new Intent(ListItemActivity.this, NotificationSettings.class);
        startActivity(i);

    }

    public void onHomeClick(View v){
                Intent i = new Intent(ListItemActivity.this, HomePageListActivity.class);
                startActivity(i);
    }

    public void onQRClick(View v){
        Toast.makeText(ListItemActivity.this, "QR sensor will be implemented in construction phase", Toast.LENGTH_SHORT).show();
    }

    public void onTrashClick(View v){
        Toast.makeText(ListItemActivity.this, "This list will be deleted. This will be implemented in construction phase", Toast.LENGTH_SHORT).show();
    }

    public void onShare(View v){
        Toast.makeText(ListItemActivity.this, "Share will utilize the contacts DB and will be implemented in construction phase", Toast.LENGTH_SHORT).show();
    }



    void setListeners(EditText listitem){

        listitem.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    EditText newListItem = new EditText(ListItemActivity.this);
                    newListItem.setText("New list item");
                    grid.addView(newListItem);
                    setListeners(newListItem);
                    return true;
                }
                return false;
            }
        });

    }



    /**
     * Detects left and right swipes across a view.
     */
    public class OnSwipeTouchListener implements View.OnTouchListener {

        private final GestureDetector gestureDetector;

        public OnSwipeTouchListener(Context context) {
            gestureDetector = new GestureDetector(context, new GestureListener());
        }

        public void onSwipeLeft() {
        }

        public void onSwipeRight() {
        }

        public boolean onTouch(View v, MotionEvent event) {
            return gestureDetector.onTouchEvent(event);
        }

        private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

            private static final int SWIPE_DISTANCE_THRESHOLD = 100;
            private static final int SWIPE_VELOCITY_THRESHOLD = 100;

            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                float distanceX = e2.getX() - e1.getX();
                float distanceY = e2.getY() - e1.getY();
                if (Math.abs(distanceX) > Math.abs(distanceY) && Math.abs(distanceX) > SWIPE_DISTANCE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (distanceX > 0)
                        onSwipeRight();
                    else
                        onSwipeLeft();
                    return true;
                }
                return false;
            }
        }
    }


}
