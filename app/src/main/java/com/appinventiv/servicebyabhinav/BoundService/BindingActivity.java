package com.appinventiv.servicebyabhinav.BoundService;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.appinventiv.servicebyabhinav.R;

public class BindingActivity extends AppCompatActivity {

    private static final String TAG = "LocalService";

    LocalService mService;
    boolean mBound = false;

    Button btnStartBinderClassService;
    Button btnStopBinderClassService;
    Button btnGenerateRandom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binding);

        btnStartBinderClassService = findViewById(R.id.btn_start_binderService);
        btnStopBinderClassService = findViewById(R.id.btn_stop_binderService);
        btnGenerateRandom = findViewById(R.id.btn_generate_random);
    }

    /** Three Parameter passed in bindService()
     * 1. The first parameter of bindService() is an Intent that explicitly names the service to bind.
     * 2. The second parameter is the ServiceConnection object.
     * 3. The third parameter is a flag indicating options for the binding. It should usually be
     *      BIND_AUTO_CREATE in order to create the service if it's not already alive. Other possible
     *      values are BIND_DEBUG_UNBIND and BIND_NOT_FOREGROUND, or 0 for none.*/

    @Override
    protected void onStart() {
        super.onStart();
        // Bind to LocalService

        btnStartBinderClassService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(BindingActivity.this, LocalService.class);
                bindService(intent, connection, Context.BIND_AUTO_CREATE);

            }
        });

        btnGenerateRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBound) {
                    for (int i = 1; i <= 10000; i++){
                        int num = mService.getRandomNumber();
                        Log.d(TAG, "Bound RandomNumber Count " + i+ " is  "+num);
                    }
                }
            }
        });

        btnStopBinderClassService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unbindService(connection);
                mBound = false;
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    /** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

            //ComponentName: The concrete component name of the service that has been connected.

            Log.d(TAG,componentName.getClassName()+" : "+componentName.getPackageName());

            // We've bound to LocalService, cast the IBinder and get LocalService instance

            LocalService.LocalBinder binder = (LocalService.LocalBinder) iBinder;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            //ComponentName: The concrete component name of the service whose connection has been lost.
            mBound = false;
        }
    };
}
