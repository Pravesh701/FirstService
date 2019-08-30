package com.appinventiv.servicebyabhinav;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyServiceViaIntentService extends IntentService {

    String str;

    public MyServiceViaIntentService() {
        super("MyServiceViaIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        str = intent.getStringExtra("data");

        for (long i = 1; i <= 100000 ; i++){
            Log.d("MyServiceViaService",str + " " + i);

        }

    }

}
