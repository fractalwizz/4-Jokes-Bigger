package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.udacity.gradle.builditbigger.joker.Joker;
import com.fract.nano.williamyoung.jokedisplay.JokeDisplay;

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

//        if (id == R.id.action_settings) { return true; }
//
//        return super.onOptionsItemSelected(item);
        return (id == R.id.action_settings) || super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        Joker joker = new Joker();

        Intent intent = new Intent(this, JokeDisplay.class);
        intent.putExtra("joke", joker.getJoke());
        startActivity(intent);
    }
}