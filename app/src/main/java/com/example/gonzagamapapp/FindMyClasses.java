package com.example.gonzagamapapp;

/**
 * Activity that allows users to input courses into the app to later be used to map it out
 */

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class FindMyClasses extends AppCompatActivity {

    static final String TAG = "findMyClass";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_my_classes);

        int currentOrientation = getResources().getConfiguration().orientation;
        if (currentOrientation == Configuration.ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }

        ImageView backButton = (ImageView) findViewById(R.id.backButton1);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FindMyClasses.this.finish();
            }
        });

        ImageView mapMyClassesButton = (ImageView) findViewById(R.id.mapMyClassesButton1);
        mapMyClassesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String course1 = "n/a", building1 = "", room1 = "n/a";
                String course2 = "n/a", building2 = "", room2 = "n/a";
                String course3 = "n/a", building3 = "", room3 = "n/a";
                String course4 = "n/a", building4 = "", room4 = "n/a";
                String course5 = "n/a", building5 = "", room5 = "n/a";
                String course6 = "n/a", building6 = "", room6 = "n/a";
                String course7 = "n/a", building7 = "", room7 = "n/a";


                if (findViewById(R.id.course1) != null) {
                    EditText C1 = (EditText) findViewById(R.id.course1);
                    course1 = String.valueOf(C1.getText());
                    Spinner B1 = (Spinner) findViewById(R.id.buildingSpinner1);
                    building1 = String.valueOf(B1.getSelectedItem());
                    if (findViewById(R.id.room1) != null) {
                        EditText R1 = (EditText) findViewById(R.id.room1);
                        room1 = String.valueOf(R1.getText());
                    }
                }
                if (findViewById(R.id.course2) != null) {
                    EditText C1 = (EditText) findViewById(R.id.course2);
                    course2 = String.valueOf(C1.getText());
                    Spinner B1 = (Spinner) findViewById(R.id.buildingSpinner2);
                    building2 = String.valueOf(B1.getSelectedItem());
                    if (findViewById(R.id.room2) != null) {
                        EditText R1 = (EditText) findViewById(R.id.room2);
                        room2 = String.valueOf(R1.getText());
                    }
                }
                if (findViewById(R.id.course3) != null) {
                    EditText C1 = (EditText) findViewById(R.id.course3);
                    course3 = String.valueOf(C1.getText());
                    Spinner B1 = (Spinner) findViewById(R.id.buildingSpinner3);
                    building3 = String.valueOf(B1.getSelectedItem());
                    if (findViewById(R.id.room3) != null) {
                        EditText R1 = (EditText) findViewById(R.id.room3);
                        room3 = String.valueOf(R1.getText());
                    }
                }
                if (findViewById(R.id.course4) != null) {
                    EditText C1 = (EditText) findViewById(R.id.course4);
                    course4 = String.valueOf(C1.getText());
                    Spinner B1 = (Spinner) findViewById(R.id.buildingSpinner4);
                    building4 = String.valueOf(B1.getSelectedItem());
                    if (findViewById(R.id.room4) != null) {
                        EditText R1 = (EditText) findViewById(R.id.room4);
                        room4 = String.valueOf(R1.getText());
                    }
                }
                if (findViewById(R.id.course5) != null) {
                    EditText C1 = (EditText) findViewById(R.id.course5);
                    course5 = String.valueOf(C1.getText());
                    Spinner B1 = (Spinner) findViewById(R.id.buildingSpinner5);
                    building5 = String.valueOf(B1.getSelectedItem());
                    if (findViewById(R.id.room5) != null) {
                        EditText R1 = (EditText) findViewById(R.id.room5);
                        room5 = String.valueOf(R1.getText());
                    }
                }
                if (findViewById(R.id.course6) != null) {
                    EditText C1 = (EditText) findViewById(R.id.course6);
                    course6 = String.valueOf(C1.getText());
                    Spinner B1 = (Spinner) findViewById(R.id.buildingSpinner6);
                    building6 = String.valueOf(B1.getSelectedItem());
                    if (findViewById(R.id.room6) != null) {
                        EditText R1 = (EditText) findViewById(R.id.room6);
                        room6 = String.valueOf(R1.getText());
                    }
                }
                if (findViewById(R.id.course7) != null) {
                    EditText C1 = (EditText) findViewById(R.id.course7);
                    course7 = String.valueOf(C1.getText());
                    Spinner B1 = (Spinner) findViewById(R.id.buildingSpinner7);
                    building7 = String.valueOf(B1.getSelectedItem());
                    if (findViewById(R.id.room7) != null) {
                        EditText R1 = (EditText) findViewById(R.id.room7);
                        room7 = String.valueOf(R1.getText());
                    }
                }

                Intent intent = new Intent(FindMyClasses.this, FindMyClassesMapsActivity.class);
                intent.putExtra("course1", course1);
                intent.putExtra("building1", building1);
                intent.putExtra("room1", room1);
                intent.putExtra("course2", course2);
                intent.putExtra("building2", building2);
                intent.putExtra("room2", room2);
                intent.putExtra("course3", course3);
                intent.putExtra("building3", building3);
                intent.putExtra("room3", room3);
                intent.putExtra("course4", course4);
                intent.putExtra("building4", building4);
                intent.putExtra("room4", room4);
                intent.putExtra("course5", course5);
                intent.putExtra("building5", building5);
                intent.putExtra("room5", room5);
                intent.putExtra("course6", course6);
                intent.putExtra("building6", building6);
                intent.putExtra("room6", room6);
                intent.putExtra("course7", course7);
                intent.putExtra("building7", building7);
                intent.putExtra("room7", room7);

                ClassesOpenHelper openHelper = new ClassesOpenHelper(FindMyClasses.this);
                Classes classes = new Classes("woah", "woah", "woah");
                openHelper.insertContact(classes);


                startActivity(intent);
            }
        });

        ImageView addButton = (ImageView) findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (findViewById(R.id.course2).getVisibility() == View.INVISIBLE) {
                    findViewById(R.id.course2).setVisibility(View.VISIBLE);
                    findViewById(R.id.buildingSpinner2).setVisibility(View.VISIBLE);
                    findViewById(R.id.room2).setVisibility(View.VISIBLE);
                } else if (findViewById(R.id.course3).getVisibility() == View.INVISIBLE) {
                    findViewById(R.id.course3).setVisibility(View.VISIBLE);
                    findViewById(R.id.buildingSpinner3).setVisibility(View.VISIBLE);
                    findViewById(R.id.room3).setVisibility(View.VISIBLE);
                } else if (findViewById(R.id.course4).getVisibility() == View.INVISIBLE) {
                    findViewById(R.id.course4).setVisibility(View.VISIBLE);
                    findViewById(R.id.buildingSpinner4).setVisibility(View.VISIBLE);
                    findViewById(R.id.room4).setVisibility(View.VISIBLE);
                } else if (findViewById(R.id.course5).getVisibility() == View.INVISIBLE) {
                    findViewById(R.id.course5).setVisibility(View.VISIBLE);
                    findViewById(R.id.buildingSpinner5).setVisibility(View.VISIBLE);
                    findViewById(R.id.room5).setVisibility(View.VISIBLE);
                } else if (findViewById(R.id.course6).getVisibility() == View.INVISIBLE) {
                    findViewById(R.id.course6).setVisibility(View.VISIBLE);
                    findViewById(R.id.buildingSpinner6).setVisibility(View.VISIBLE);
                    findViewById(R.id.room6).setVisibility(View.VISIBLE);
                } else if (findViewById(R.id.course7).getVisibility() == View.INVISIBLE) {
                    findViewById(R.id.course7).setVisibility(View.VISIBLE);
                    findViewById(R.id.buildingSpinner7).setVisibility(View.VISIBLE);
                    findViewById(R.id.room7).setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
