package com.appinventiv.servicebyabhinav.BoundService;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.Random;

public class LocalService extends Service {

    private static final String TAG = "LocalService";

    // Binder given to clients
    private final IBinder binder = new LocalBinder();
    // Random number generator
    private final Random mGenerator = new Random();


    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */

  /**  If your service is private to your own application and runs in the same process as the client
   (which is common), you should create your interface by extending the Binder class and
   returning an instance of it from onBind(). The client receives the Binder and can use it to directly
   access public methods available in either the Binder implementation or the Service.
   */

    public class LocalBinder extends Binder {
        LocalService getService() {
            // Return this instance of LocalService so clients can call public methods
            return LocalService.this;
        }
    }

    /**
     * In the client, receive the Binder from the onServiceConnected() callback method and make calls
     * to the bound service using the methods provided. */

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "Bound Service Started");
        return binder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"Bound Service Stopped");
    }

    /** The LocalBinder provides the getService() method for clients to retrieve the current instance
     *  of LocalService. This allows clients to call public methods in the service. For example,
     *  clients can call getRandomNumber() from the service.*/

    /** method for clients */
    public int getRandomNumber() {
        return mGenerator.nextInt(100);
    }
}
