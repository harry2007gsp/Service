package com.harry;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Intent intent;
    MyService.MyBinder myBinder;
    boolean flag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void start(View view) {
        intent = new Intent(this, MyService.class);
        // starting service with startService()
////        intent.putExtra("song", "song1");
//        intent.putExtra("song", "song2");
//        startService(intent);

        // starting service with bindService()
        bindService(intent, serviceConnection, 1);// 1 when service is already created and then bound
        flag=true;                                // 0 when service is not created and already
    }                                             // created service is used

    public void stop(View view) {
        intent = new Intent(this, MyService.class);
//        stopService(intent);
        if (flag) {
            unbindService(serviceConnection);
            flag = false;
        }

    }
    // to use customMethod of service
    public void binder(View view) {
        if (flag) {
            int result = myBinder.getServiceInstance().customMethod(2,3);
            Log.d("test", String.valueOf(result));
        }
    }
    ServiceConnection serviceConnection = new ServiceConnection() {
        //this method is used when we wanna use customMethod of service and need results back
        //in this activity. So serviceBinder is gotten and used to access service instance with the help
        //of which we will use customMethod
        @Override
        public void onServiceConnected(ComponentName name, IBinder serviceBinder) {
            Log.d("test", "onServiceConnected");
            myBinder = (MyService.MyBinder)serviceBinder;

        }
        // this method is not called when service is unbound intentionaly but only called when
        // service is disconnected and unbound accidentally due to crash or other reason
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("test", "onServiceDisconnected");

        }
    };
}
