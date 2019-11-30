package com.example.gonzagamapapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class FindMyClasses extends AppCompatActivity {

    static final String TAG = "findMyClass";

    private String SAVE_COURSE1;
    private int SAVE_BUILDING1;
    private String SAVE_ROOM1;
    private String SAVE_COURSE2;
    private int SAVE_BUILDING2;
    private String SAVE_ROOM2;
    private String SAVE_COURSE3;
    private int SAVE_BUILDING3;
    private String SAVE_ROOM3;
    private String SAVE_COURSE4;
    private int SAVE_BUILDING4;
    private String SAVE_ROOM4;
    private String SAVE_COURSE5;
    private int SAVE_BUILDING5;
    private String SAVE_ROOM5;
    private String SAVE_COURSE6;
    private int SAVE_BUILDING6;
    private String SAVE_ROOM6;
    private String SAVE_COURSE7;
    private int SAVE_BUILDING7;
    private String SAVE_ROOM7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_my_classes);

        if (savedInstanceState != null){
            EditText course1 = (EditText) findViewById(R.id.course1);
            course1.setText(savedInstanceState.getString("save_course1"));
            SAVE_COURSE1 = savedInstanceState.getString("save_course1");
            Spinner building1 = (Spinner) findViewById(R.id.buildingSpinner1);
            building1.setSelection(savedInstanceState.getInt("save_building1"));
            SAVE_BUILDING1 = savedInstanceState.getInt("save_building1");
            EditText room1 = (EditText) findViewById(R.id.course1);
            room1.setText(savedInstanceState.getString("save_room1"));
            SAVE_ROOM1 = savedInstanceState.getString("save_room1");

            EditText course2 = (EditText) findViewById(R.id.course2);
            course2.setText(savedInstanceState.getString("save_course2"));
            SAVE_COURSE2 = savedInstanceState.getString("save_course2");
            Spinner building2 = (Spinner) findViewById(R.id.buildingSpinner2);
            building2.setSelection(savedInstanceState.getInt("save_building2"));
            SAVE_BUILDING2 = savedInstanceState.getInt("save_building2");
            EditText room2 = (EditText) findViewById(R.id.course2);
            room2.setText(savedInstanceState.getString("save_room2"));
            SAVE_ROOM2 = savedInstanceState.getString("save_room2");

            EditText course3 = (EditText) findViewById(R.id.course3);
            course3.setText(savedInstanceState.getString("save_course3"));
            SAVE_COURSE3 = savedInstanceState.getString("save_course3");
            Spinner building3 = (Spinner) findViewById(R.id.buildingSpinner3);
            building3.setSelection(savedInstanceState.getInt("save_building3"));
            SAVE_BUILDING3 = savedInstanceState.getInt("save_building3");
            EditText room3 = (EditText) findViewById(R.id.course3);
            room3.setText(savedInstanceState.getString("save_room3"));
            SAVE_ROOM3 = savedInstanceState.getString("save_room3");

            EditText course4 = (EditText) findViewById(R.id.course4);
            course4.setText(savedInstanceState.getString("save_course4"));
            SAVE_COURSE4 = savedInstanceState.getString("save_course4");
            Spinner building4 = (Spinner) findViewById(R.id.buildingSpinner4);
            building4.setSelection(savedInstanceState.getInt("save_building4"));
            SAVE_BUILDING4 = savedInstanceState.getInt("save_building4");
            EditText room4 = (EditText) findViewById(R.id.course1);
            room4.setText(savedInstanceState.getString("save_room4"));
            SAVE_ROOM4 = savedInstanceState.getString("save_room4");

            EditText course5 = (EditText) findViewById(R.id.course5);
            course5.setText(savedInstanceState.getString("save_course5"));
            SAVE_COURSE5 = savedInstanceState.getString("save_course5");
            Spinner building5 = (Spinner) findViewById(R.id.buildingSpinner5);
            building5.setSelection(savedInstanceState.getInt("save_building5"));
            SAVE_BUILDING5 = savedInstanceState.getInt("save_building5");
            EditText room5 = (EditText) findViewById(R.id.course5);
            room5.setText(savedInstanceState.getString("save_room5"));
            SAVE_ROOM5 = savedInstanceState.getString("save_room5");

            EditText course6 = (EditText) findViewById(R.id.course6);
            course6.setText(savedInstanceState.getString("save_course6"));
            SAVE_COURSE6 = savedInstanceState.getString("save_course6");
            Spinner building6 = (Spinner) findViewById(R.id.buildingSpinner6);
            building6.setSelection(savedInstanceState.getInt("save_building6"));
            SAVE_BUILDING6 = savedInstanceState.getInt("save_building6");
            EditText room6 = (EditText) findViewById(R.id.course6);
            room6.setText(savedInstanceState.getString("save_room6"));
            SAVE_ROOM6 = savedInstanceState.getString("save_room6");

            EditText course7 = (EditText) findViewById(R.id.course7);
            course7.setText(savedInstanceState.getString("save_course7"));
            SAVE_COURSE7 = savedInstanceState.getString("save_course7");
            Spinner building7 = (Spinner) findViewById(R.id.buildingSpinner7);
            building7.setSelection(savedInstanceState.getInt("save_building7"));
            SAVE_BUILDING7 = savedInstanceState.getInt("save_building7");
            EditText room7 = (EditText) findViewById(R.id.course7);
            room7.setText(savedInstanceState.getString("save_room7"));
            SAVE_ROOM7 = savedInstanceState.getString("save_room7");
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
                String course1 = "n/a";
                String building1 = "";
                String room1 = "n/a";

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

                Intent intent = new Intent(FindMyClasses.this, FindMyClassesMapsActivity.class);
                intent.putExtra("course1", course1);
                intent.putExtra("building1", building1);
                intent.putExtra("room1", room1);
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

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        EditText course1 = (EditText) findViewById(R.id.course1);
        outState.putString("save_course1", String.valueOf(course1.getText()));
        Spinner buildingSpinner1 = (Spinner) findViewById(R.id.buildingSpinner1);
        outState.putInt("save_building1", determineBuilding((String) buildingSpinner1.getSelectedItem()));
        EditText room1 = (EditText) findViewById(R.id.room1);
        outState.putString("save_room1", String.valueOf(room1.getText()));

        EditText course2 = (EditText) findViewById(R.id.course2);
        outState.putString("save_course2", String.valueOf(course2.getText()));
        Spinner buildingSpinner2 = (Spinner) findViewById(R.id.buildingSpinner2);
        outState.putInt("save_building2", determineBuilding((String) buildingSpinner2.getSelectedItem()));
        EditText room2 = (EditText) findViewById(R.id.room2);
        outState.putString("save_room2", String.valueOf(room2.getText()));

        EditText course3 = (EditText) findViewById(R.id.course3);
        outState.putString("save_course3", String.valueOf(course3.getText()));
        Spinner buildingSpinner3 = (Spinner) findViewById(R.id.buildingSpinner3);
        outState.putInt("save_building3", determineBuilding((String) buildingSpinner3.getSelectedItem()));
        EditText room3 = (EditText) findViewById(R.id.room3);
        outState.putString("save_room3", String.valueOf(room3.getText()));

        EditText course4 = (EditText) findViewById(R.id.course4);
        outState.putString("save_course4", String.valueOf(course4.getText()));
        Spinner buildingSpinner4 = (Spinner) findViewById(R.id.buildingSpinner4);
        outState.putInt("save_building4", determineBuilding((String) buildingSpinner4.getSelectedItem()));
        EditText room4 = (EditText) findViewById(R.id.room4);
        outState.putString("save_room4", String.valueOf(room4.getText()));

        EditText course5 = (EditText) findViewById(R.id.course5);
        outState.putString("save_course5", String.valueOf(course5.getText()));
        Spinner buildingSpinner5 = (Spinner) findViewById(R.id.buildingSpinner5);
        outState.putInt("save_building5", determineBuilding((String) buildingSpinner5.getSelectedItem()));
        EditText room5 = (EditText) findViewById(R.id.room5);
        outState.putString("save_room5", String.valueOf(room5.getText()));

        EditText course6 = (EditText) findViewById(R.id.course6);
        outState.putString("save_course6", String.valueOf(course1.getText()));
        Spinner buildingSpinner6 = (Spinner) findViewById(R.id.buildingSpinner6);
        outState.putInt("save_building6", determineBuilding((String) buildingSpinner6.getSelectedItem()));
        EditText room6 = (EditText) findViewById(R.id.room6);
        outState.putString("save_room6", String.valueOf(room6.getText()));

        EditText course7 = (EditText) findViewById(R.id.course7);
        outState.putString("save_course7", String.valueOf(course7.getText()));
        Spinner buildingSpinner7 = (Spinner) findViewById(R.id.buildingSpinner7);
        outState.putInt("save_building7", determineBuilding((String) buildingSpinner7.getSelectedItem()));
        EditText room7 = (EditText) findViewById(R.id.room7);
        outState.putString("save_room7", String.valueOf(room7.getText()));
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        EditText course1 = (EditText) findViewById(R.id.course1);
        course1.setText(savedInstanceState.getString("save_course1"));
        Spinner building1 = (Spinner) findViewById(R.id.buildingSpinner1);
        building1.setSelection(savedInstanceState.getInt("save_building1"));
        EditText room1 = (EditText) findViewById(R.id.course1);
        room1.setText(savedInstanceState.getString("save_room1"));

        EditText course2 = (EditText) findViewById(R.id.course2);
        course2.setText(savedInstanceState.getString("save_course2"));
        Spinner building2 = (Spinner) findViewById(R.id.buildingSpinner2);
        building2.setSelection(savedInstanceState.getInt("save_building2"));
        EditText room2 = (EditText) findViewById(R.id.course2);
        room2.setText(savedInstanceState.getString("save_room2"));

        EditText course3 = (EditText) findViewById(R.id.course3);
        course3.setText(savedInstanceState.getString("save_course3"));
        Spinner building3 = (Spinner) findViewById(R.id.buildingSpinner3);
        building3.setSelection(savedInstanceState.getInt("save_building3"));
        EditText room3 = (EditText) findViewById(R.id.course3);
        room3.setText(savedInstanceState.getString("save_room3"));

        EditText course4 = (EditText) findViewById(R.id.course4);
        course4.setText(savedInstanceState.getString("save_course4"));
        Spinner building4 = (Spinner) findViewById(R.id.buildingSpinner4);
        building4.setSelection(savedInstanceState.getInt("save_building4"));
        EditText room4 = (EditText) findViewById(R.id.course1);
        room4.setText(savedInstanceState.getString("save_room4"));

        EditText course5 = (EditText) findViewById(R.id.course5);
        course5.setText(savedInstanceState.getString("save_course5"));
        Spinner building5 = (Spinner) findViewById(R.id.buildingSpinner5);
        building5.setSelection(savedInstanceState.getInt("save_building5"));
        EditText room5 = (EditText) findViewById(R.id.course5);
        room5.setText(savedInstanceState.getString("save_room5"));

        EditText course6 = (EditText) findViewById(R.id.course6);
        course6.setText(savedInstanceState.getString("save_course6"));
        Spinner building6 = (Spinner) findViewById(R.id.buildingSpinner6);
        building6.setSelection(savedInstanceState.getInt("save_building6"));
        EditText room6 = (EditText) findViewById(R.id.course6);
        room6.setText(savedInstanceState.getString("save_room6"));

        EditText course7 = (EditText) findViewById(R.id.course7);
        course7.setText(savedInstanceState.getString("save_course7"));
        Spinner building7 = (Spinner) findViewById(R.id.buildingSpinner7);
        building7.setSelection(savedInstanceState.getInt("save_building7"));
        EditText room7 = (EditText) findViewById(R.id.course7);
        room7.setText(savedInstanceState.getString("save_room7"));
    }

    public int determineBuilding(String building) {
        if (building.equals("College Hall"))
            return 0;
        else if (building.equals("Coughlin"))
            return 1;
        else if (building.equals("Crosby"))
            return 2;
        else if (building.equals("Hemmingson"))
            return 3;
        else if (building.equals("Herek"))
            return 4;
        else if (building.equals("Hughes"))
            return 5;
        else if (building.equals("Jepson"))
            return 6;
        else if (building.equals("Jundt"))
            return 7;
        else if (building.equals("Paccar"))
            return 8;
        else if (building.equals("Rosar"))
            return 9;
        else // tilford
            return 0;
    }
}
