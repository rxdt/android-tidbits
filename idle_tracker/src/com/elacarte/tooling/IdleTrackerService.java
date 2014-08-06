package com.elacarte.tooling;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

public class IdleTrackerService extends Service {

    static long totalIdleTime = 0;

    static long totalUsedTime = 0;

    static long lastTimeTouched = 0;

    static long lastTimeIdle = 0;
    
    // In the Presto “Setting->Display->Idle Timeout” is to set the interval after which the device is "idle".
    static long lag = 15000; // 15,000 ms == 15 seconds

    boolean idleTimeStart = true;
    
    private static BroadcastReceiver idleReceiver;

    IntentFilter filter;

    String message1 = "The total idleTime is " + totalIdleTime;
    String message2 = "The total timeUsed is " + totalUsedTime;

    private Handler handler = new Handler();;
    
    // defined in Intent.java. sent out 5 minutes after LAST touchs
    public final String ACTION_DEVICE_IS_IDLE_TIMEOUT = "android.intent.action.DEVICE_IS_IDLE_TIMEOUT";
    
    // defined in Intent.java. sent out immediately upon new touch after an idle period
    public final String ACTION_TOUCH_SCREEN_AFTER_DEVICE_IDLE_TIMEOUT = "android.intent.action.TOUCH_SCREEN_AFTER_DEVICE_IDLE_TIMEOUT";
    
    
    
    @Override
    public void onCreate() {
        super.onCreate();
        createBroadcastReceivers();
    }
    
    @Override
    public int onStartCommand(Intent intent, int flags, int startID) {
        return super.onStartCommand(intent, flags, startID);
    }

    
    
    private void createBroadcastReceivers() {
        idleReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(final Context context, final Intent intent) {
                // check if the broadcast is the desired one
                if (intent.getAction().equals("android.intent.action.DEVICE_IS_IDLE_TIMEOUT")) {
                    lastTimeIdle = System.currentTimeMillis() - lag;
                    trackTotalUsedTime();
                    showToast();
                }
                else if (intent.getAction().equals(ACTION_TOUCH_SCREEN_AFTER_DEVICE_IDLE_TIMEOUT)) {
                    lastTimeTouched = System.currentTimeMillis();
                    trackTotalIdleTime();
                    showOtherToast();
                }
            }
        };
        register(idleReceiver);
    }

    
    
    
    /*
     * Registers the receiver that will listen for 2 intents and shuts down the 
     * IdleTrackerActivity that started this.Service
     * @param idleReceiver to register
     */
    private void register(BroadcastReceiver idleReceiver) {
        filter = new IntentFilter();
        {
            filter.addAction("android.intent.action.DEVICE_IS_IDLE_TIMEOUT");
            filter.addAction(ACTION_TOUCH_SCREEN_AFTER_DEVICE_IDLE_TIMEOUT);
            registerReceiver(idleReceiver, filter);
        }
        sendBroadcast(new Intent("ACTIVITY_OFF")); // after registering we can shut down Activity
    }

    
    
    
    
    private void showToast() {
        handler.post(new Runnable() {
            public void run() {
                Toast toast1 = Toast.makeText(IdleTrackerService.this, 
                              "onReceive worked - the touch activated intent was received by the service  " + totalIdleTime, 
                              Toast.LENGTH_LONG);
                toast1.show();
            } 
        });
    }
    
    
    private void showOtherToast() {
        handler.post(new Runnable() {
            public void run() {
                Toast toast2 = Toast.makeText(IdleTrackerService.this, 
                              "the Device was touched and onReceive worked  " + lastTimeTouched, 
                              Toast.LENGTH_LONG);
                toast2.show();
            } 
        });
    }
    
    
    /*
     *  counting the total idle time through Presto's run
     *  @return totalIdleTime in total minutes
     */
    private long trackTotalIdleTime() {
        
        if(idleTimeStart == true) {
            idleTimeStart = false;
            return 0;
        }
        
        totalIdleTime = totalIdleTime;
        return ((totalIdleTime/1000)/60);
    }
    
    /*
     *  counting the total used time through Presto's run
     *  @return totalUsedTime in total minutes
     */
    private long trackTotalUsedTime() {
        
        return((totalUsedTime/1000)/60);
    }
    

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    
    
    
    @Override
    public void onDestroy() {
        //this.unregisterReceiver(idleReceiver);
    }

}
