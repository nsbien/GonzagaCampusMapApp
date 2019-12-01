package com.example.gonzagamapapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.w3c.dom.Document;

public class extraActivity extends AppCompatActivity {

    TextView texx;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra);

        Intent intent = getIntent();

    }

    public class doIt extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Document doc = (Document) Jsoup.connect("https://www.gonzaga.edu/").get();
                

            }catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }
    }


}
