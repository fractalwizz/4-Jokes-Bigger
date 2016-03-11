package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;

import java.lang.Exception;
import java.lang.InterruptedException;
import java.lang.Override;
import java.lang.String;
import java.util.concurrent.CountDownLatch;

public class ApplicationTest extends ApplicationTestCase<Application> {
    String mResult = null;
    Exception mError = null;
    CountDownLatch signal = null;

    public ApplicationTest() { super(Application.class); }

    @Override
    protected void setUp() throws Exception { signal = new CountDownLatch(1); }

    @Override
    protected void tearDown() throws Exception { signal.countDown(); }

    public void testJokeGetTask() throws InterruptedException {
        EndpointsAsyncTask task = new EndpointsAsyncTask(getContext());
        task.setListener(new EndpointsAsyncTask.EndpointsAsyncTaskListener() {
            @Override
            public void onComplete(String result, Exception e) {
                mResult = result;
                mError = e;
                signal.countDown();
            }
        }).execute(new Pair<>(getContext(), "joke"));
        signal.await();

        assertNull(mError);
        assertFalse(TextUtils.isEmpty(mResult));
    }
}