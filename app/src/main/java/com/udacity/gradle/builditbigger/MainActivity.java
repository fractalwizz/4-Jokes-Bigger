package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.fract.nano.williamyoung.jokedisplay.JokeDisplay;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

public class MainActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return (id == R.id.action_settings) || super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        new EndpointsAsyncTask().execute(new Pair<Context, String>(this, "joke"));
    }

    class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
        private MyApi apiService = null;
        private Context context;

        @Override
        protected String doInBackground(Pair<Context, String>... params) {
            if (apiService == null) {
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://jokesbigger.appspot.com/_ah/api/");

                apiService = builder.build();
            }

            context = params[0].first;
            String joke = params[0].second;

            try {
                return apiService.sayJoke(joke).execute().getData();
            } catch (IOException e) {
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            Intent intent = new Intent(getApplicationContext(), JokeDisplay.class);
            intent.putExtra("joke", result);
            startActivity(intent);
        }
    }
}