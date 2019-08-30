package com.appinventiv.servicebyabhinav;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.TreeMap;

public class MyServiceViaService extends Service {

    String TAG = MyServiceViaService.class.getSimpleName();
    Thread myThread=null;
    boolean flag=true;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        Log.d(TAG,"OnCreate Called");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

       final String str = intent.getStringExtra("data");

        /*for (long i = 1; i <= 100000 ; i++){
            Log.d("MyServiceViaService",str + " " + i);
        }*/

        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                for (long i=0; (i<=10 && flag); i++){
                        try{
                            Thread.sleep(1000);
                            Log.d("MyServiceViaService",str +" "+ i);
                        }catch (Exception e){
                            e.getMessage();
                        }
                }
                return;

            }
        });
        thread.start();
        this.myThread=thread;
        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"Service destroy");
        if (myThread.isAlive()){
            flag=false;
            myThread.interrupt();
           // myThread.stop();
            Log.d(TAG,"Interrupt");
        }
       // Toast.makeText(this, "Service Done", Toast.LENGTH_SHORT).show();
    }
}
