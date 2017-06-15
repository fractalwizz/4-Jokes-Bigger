package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.test.ApplicationTestCase;

import java.lang.Exception;
import java.lang.InterruptedException;
import java.lang.Override;
import java.util.concurrent.CountDownLatch;

public class ApplicationTest extends ApplicationTestCase<Application> {
    CountDownLatch signal = null;

    public ApplicationTest() { super(Application.class); }

    @Override
    protected void setUp() throws Exception { signal = new CountDownLatch(1); }

    @Override
    protected void tearDown() throws Exception { signal.countDown(); }

    public void testJokeGetTask() throws InterruptedException {
    }
}