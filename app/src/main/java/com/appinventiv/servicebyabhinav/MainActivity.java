package com.appinventiv.servicebyabhinav;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.appinventiv.servicebyabhinav.BoundService.ActivityMessenger;
import com.appinventiv.servicebyabhinav.BoundService.BindingActivity;

public class MainActivity extends AppCompatActivity {
    Button btnStartService;
    Button btnStopService;
    Button btnStartIntentService;
    Button btn1, btn2, btn3, btn4, btn5;
    Button btnGoBinderActivity, btnGoMessangerActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialisedID();

    }

    private void initialisedID() {
        btnStartService = findViewById(R.id.btn_start);
        btnStopService = findViewById(R.id.btn_stop);
        btnStartIntentService = findViewById(R.id.btn_start_via_intent);
        btn1 = findViewById(R.id.btn_download1);
        btn2 = findViewById(R.id.btn_download2);
        btn3 = findViewById(R.id.btn_download3);
        btn4 = findViewById(R.id.btn_download4);
        btn5 = findViewById(R.id.btn_download5);
        btnGoBinderActivity = findViewById(R.id.btn_go_binder_activity);
        btnGoMessangerActivity = findViewById(R.id.btn_go_messanger_activity);

    }

    @Override
    protected void onStart() {
        super.onStart();

        serviceOperations();
    }

    private void serviceOperations() {

        btnStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG","start calling");
                Intent intent = new Intent(MainActivity.this, MyServiceViaService.class);
                intent.putExtra("data","Button StartService ");
                startService(intent);
            }
        });

        btnStopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyServiceViaService.class);
                stopService(intent);
                Log.d("MyService", "Stop Method Called ");
            }
        });

        btnStartIntentService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyServiceViaIntentService.class);
                intent.putExtra("data","Button IntentService ");
                startService(intent);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyServiceViaService.class);
                intent.putExtra("data","Button Download One ");
                startService(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyServiceViaService.class);
                intent.putExtra("data","Button Download Two ");
                startService(intent);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyServiceViaService.class);
                intent.putExtra("data","Button Download Three ");
                startService(intent);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyServiceViaService.class);
                intent.putExtra("data","Button Download Four ");
                startService(intent);
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyServiceViaService.class);
                intent.putExtra("data","Button Download Five ");
                startService(intent);
            }
        });

        btnGoBinderActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BindingActivity.class);
                startActivity(intent);
            }
        });

        btnGoMessangerActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivityMessenger.class);
                startActivity(intent);
            }
        });


        // For Intent Service

       /* btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyServiceViaIntentService.class);
                intent.putExtra("data","Button Download One ");
                startService(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyServiceViaIntentService.class);
                intent.putExtra("data","Button Download Two ");
                startService(intent);
            }
        });*/
    }
}
