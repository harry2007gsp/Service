package com.harry;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Harry on 8/20/15.
 */
public class MyService extends Service {
    MediaPlayer mp;
    MyBinder myBinder = new MyBinder();
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("test", "Service Started");
        // this code was used with startService method without any binding
//        String song = intent.getStringExtra("song");
//        if (song.equals("song1")) {
//            mp = MediaPlayer.create(this, R.raw.djsnake);
//        } else {
//            mp = MediaPlayer.create(this, R.raw.lonely);
//        }
//        mp.start();
////        stopSelf();
//        stopService(intent);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("test", "on unbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d("test", "on Rebind");
        super.onRebind(intent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("test", "Inside Service but null binder");
        return myBinder;
    }

    @Override
    public void onCreate() {
        Log.d("test", "on create");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        mp.stop();
        Log.d("test", "on destroy");
    }

    public int customMethod(int a, int b) {
        Log.d("test", "custom method");
        return a+b;
    }
    // MyBinder helps us to pass service instance with the help of which we can
    // use custom methods and even get back the results
    // Without binding ibinder returns null, with binding method it returns ibinder myBinder
    // instance but that is not utilized to get back results in the calling components
    // But when we have to get back results then we use this returned myBinder
    // So this is equivalent to startActivityForResult method in case of calling other activity
    public class MyBinder extends Binder {
        public MyService getServiceInstance() {
            return MyService.this;
        }
    }
}
