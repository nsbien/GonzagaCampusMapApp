package com.example.gonzagamapapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

        texx = (TextView)findViewById(R.id.tex1);

        Button but = (Button) findViewById(R.id.but1);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new doIt().execute();

            }
        });

    }

    public class doIt extends AsyncTask<Void, Void, Void>{

        String words;

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Document doc = (Document) Jsoup.connect("https://www.gonzaga.edu/").get();
                words = doc.getTextContent();

            }catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            texx.setText(words);

        }
    }


}
