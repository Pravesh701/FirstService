package com.appinventiv.servicebyabhinav.BoundService;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;

import com.appinventiv.servicebyabhinav.R;

public class ActivityMessenger extends AppCompatActivity {

    Button btnStartMessangerService;
    Button btnStopMessangerService;
    Button btnSayHelloMessage;

    /** To Access getRandom Method in MessangerService Class */
    MessengerService messengerService;

    /** Messenger for communicating with the service. */
    Messenger mService = null;

    /** Flag indicating whether we have called bind on the service. */
    boolean bound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);

        btnStartMessangerService = findViewById(R.id.btn_start_via_messanger_Service);
        btnStopMessangerService = findViewById(R.id.btn_stop_messanger_Service);
        btnSayHelloMessage = findViewById(R.id.btn_show_message);
    }


    /**
     * Class for interacting with the main interface of the service.
     */
    private ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            // This is called when the connection with the service has been
            // established, giving us the object we can use to
            // interact with the service.  We are communicating with the
            // service using a Messenger, so here we get a client-side
            // representation of that from the raw IBinder object.
            mService = new Messenger(service);
            bound = true;

        }

        public void onServiceDisconnected(ComponentName className) {
            // This is called when the connection with the service has been
            // unexpectedly disconnected -- that is, its process crashed.
            mService = null;
            bound = false;
        }
    };


    @Override
    protected void onStart() {
        super.onStart();

        btnStartMessangerService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Bind to the service
                bindService(new Intent(ActivityMessenger.this, MessengerService.class), mConnection,
                        Context.BIND_AUTO_CREATE);
            }
        });

        btnSayHelloMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!bound) return;
                // Create and send a message to the service, using a supported 'what' value
                Message msg = Message.obtain(null, MessengerService.MSG_SAY_HELLO, 0, 0);
                try {
                    mService.send(msg);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            }
        });

        btnStopMessangerService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Unbind from the service
                if (bound) {
                    unbindService(mConnection);
                    bound = false;
                }

            }
        });

    }
}
