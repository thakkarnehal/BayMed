package com.example.techtogether;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.os.AsyncTask;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button diagnosis;
    Button history;
    ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        diagnosis = findViewById(R.id.button);
        history = findViewById(R.id.button2);
        layout=findViewById(R.id.layout);

        textView.setTextColor(Color.rgb(144,194,231));
        diagnosis.setBackgroundColor(Color.rgb(144,194,231));
        history.setBackgroundColor(Color.rgb(144,194,231));
        diagnosis.setTextColor(Color.rgb(255,255,255));
        history.setTextColor(Color.rgb(255,255,255));
        layout.setBackgroundColor(Color.rgb(242,239,241));

        diagnosis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToLoad = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intentToLoad);
            }
        });

    }
}
