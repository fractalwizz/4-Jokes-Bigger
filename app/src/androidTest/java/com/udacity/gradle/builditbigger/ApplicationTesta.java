package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.IntentFilter;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;

import static junit.framework.Assert.assertFalse;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ApplicationTesta {
    @Test
    public void testJokeGetTask() {
        TaskReceiver taskReceiver = new TaskReceiver();
        Context context = InstrumentationRegistry.getTargetContext();
        LocalBroadcastManager.getInstance(context).registerReceiver(taskReceiver, new IntentFilter(TaskReceiver.TASK_TAG));

        EndpointsAsyncTask task = new EndpointsAsyncTask(context);
        try {
            String result = task.execute(new Pair<>(context, "joke")).get();
            assertFalse(TextUtils.isEmpty(result));
            Log.w("Testa", result);
        } catch(InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}