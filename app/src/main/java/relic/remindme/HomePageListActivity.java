package relic.remindme;

import android.support.v4.app.Fragment;
import android.util.Log;

/**
 * Used to create a list fragment for each list
 */
public class HomePageListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        Log.d("create", "inside createFragment() in CrimeListActivity");
        return new HomePageListFragment();
    }
}
