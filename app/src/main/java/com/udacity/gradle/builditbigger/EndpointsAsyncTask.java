package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Pair;

import com.fract.nano.williamyoung.jokedisplay.JokeDisplay;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

/*
    Got AsyncTask Testing Assistance from...
    http://marksunghunpark.blogspot.com/2015/05/how-to-test-asynctask-in-android.html
 */
public class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
    private MyApi apiService = null;
    private Context mContext;
    private Exception mError = null;

    protected EndpointsAsyncTask(Context context) { this.mContext = context; }

    // TODO - Verify Code Functionality
    @SafeVarargs
    @Override
    protected final String doInBackground(Pair<Context, String>... params) {
        String output = null;

        if (apiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                .setRootUrl("https://jokesbigger.appspot.com/_ah/api/");

            apiService = builder.build();
        }

        mContext = params[0].first;
        String joke = params[0].second;

        try {
            output = apiService.sayJoke(joke).execute().getData();
        } catch (IOException e) {
            mError = e;
        }

        return output;
    }

    @Override
    protected void onPostExecute(String result) {
        Intent intent = new Intent(mContext, JokeDisplay.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("joke", result);

        if (mContext != null) {
            LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
        }
    }
}