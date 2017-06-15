package com.udacity.gradle.builditbigger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.fract.nano.williamyoung.jokedisplay.JokeDisplay;

public class TaskReceiver extends BroadcastReceiver {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final String TASK_TAG = "task_receiver_tag";

    public static final String JOKE_RND = "joke_random";

    @Override
    public void onReceive(Context context, Intent intent) {
        // Who launched the broadcast?
        String tag = intent.getStringExtra(TASK_TAG);

        switch (tag) {
            case JOKE_RND:
                String joke = intent.getStringExtra(JOKE_RND);

                Intent intentAct = new Intent(context, JokeDisplay.class);
                intentAct.putExtra(JokeDisplay.JOKE_TAG, joke);
                context.startActivity(intentAct);

                break;

            default:
                Log.e(LOG_TAG, "Tag not found: " + tag);
        }
    }
}
