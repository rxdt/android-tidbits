

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


/*
 * This activity exists simply to create and start IdleTrackerService. 
 * Once the necessary broadcast receivers are registered, this activity ends.
 */
public class IdleTrackerActivity extends Activity {

    private static BroadcastReceiver activityOffReceiver = null;
    
    
    
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Toast toast = Toast.makeText(getApplicationContext(), 
                " ***** IN IDLE_TRACKER_ACTIVITY. IN THE oncreate() METHOD ****", 
                Toast.LENGTH_LONG);
        toast.show();
        Intent intent = new Intent(IdleTrackerActivity.this, IdleTrackerService.class);
        this.createReceiver();
        startService(intent);
    }
    
    
    private void createReceiver() {
         activityOffReceiver = new BroadcastReceiver() { 
            @Override
            public void onReceive(Context arg0, Intent intent) {
                if (intent.getAction().equals("ACTIVITY_OFF")) {
                finish();
                }
            }
        };
        registerReceiver(activityOffReceiver, new IntentFilter("ACTIVITY_OFF"));
    }

    
    
    @Override
    public void onDestroy() {
        Toast toast = Toast.makeText(getApplicationContext(), 
                " ***** IN IDLE_TRACKER_ACTIVITY. IN THE ONDESTROY() METHOD RIGHT BEFORE FINISHING ****", 
                Toast.LENGTH_LONG);
        toast.show();
        super.onDestroy();
        unregisterReceiver(activityOffReceiver);
    }
    
    
}
