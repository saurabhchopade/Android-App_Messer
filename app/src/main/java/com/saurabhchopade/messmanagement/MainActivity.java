package com.saurabhchopade.messmanagement;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

//this code for splash activity delay code to give delay 1000 mili second

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //intent for next activity
                startActivity(new Intent(MainActivity.this,Main2Activity.class));
                finish();

            }
        },1500);
    }
}
