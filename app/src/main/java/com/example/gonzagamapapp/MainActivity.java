/**
 * The Gonzaga Campus Map App is designed to help navigate both
 *  first year students as well as new faces to GU around the campus in
 *  order to show individuals all GU has to offer.
 *
 * CPSC 312-01, Fall 2019
 * Gonzaga Campus Map App
 * No sources to cite.
 *
 * @authors Joseph Torii and Nicole Bien
 * @version v1.0 12/10/19
 */

package com.example.gonzagamapapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    /**
     Brings up the main page of the Gonzaga Campus Map App where the user can decide what
     action they want to be directed to next
     *
     * @param Bundle savedInstanceState
     * @return n/a
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Button directionsButton = (Button) findViewById(R.id.directionsButton);
        directionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DirectionActivity.class);
                startActivity(intent);
            }
        });

        Button findMyClassesButton = (Button) findViewById(R.id.findClassButton);
        findMyClassesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FindMyClasses.class);
                startActivity(intent);
            }
        });

        Button zagWebButton = (Button) findViewById(R.id.zagWebButton);
        zagWebButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri guUri = Uri.parse("https://zagweb.gonzaga.edu/prod/twbkwbis.P_GenMenu?name=homepage");
                intent.setData(guUri);
                startActivity(intent);
            }
        });

        Button extrasButton = (Button) findViewById(R.id.extrasButton);
        extrasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MessagingActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     Holds the title of the app and extra options: settings
     *
     * @param Menu menu
     * @return boolean
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     Holds the extra options like settings
     *
     * @param MenuItem item
     * @return boolean
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
