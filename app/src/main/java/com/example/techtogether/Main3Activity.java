package com.example.techtogether;

import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

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

public class Main3Activity extends AppCompatActivity {

    JSONArray symptomsData;
    TextView textView;
    TextView illness;

    String symptom;
    String sex;
    int age;
    int symptomID;

    JSONArray data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        textView = findViewById(R.id.id_diagnosis);
        illness = findViewById(R.id.textView5);

        illness.setTextColor(Color.rgb(11,83,81));
        textView.setTextColor(Color.rgb(0,169,165));

        symptom = getIntent().getStringExtra("Symptom");
        age = getIntent().getIntExtra("Age", 21);


        getJSON();

    }
    public class AsyncWeatherTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Log.d("TAG",""+symptomID);
                URL url = new URL("https://healthservice.priaid.ch/diagnosis?symptoms=["+symptomID+"]&gender=female&year_of_birth="+age+"&token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Im5tdHJvY2tzQHlhaG9vLmNvbSIsInJvbGUiOiJVc2VyIiwiaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvd3MvMjAwNS8wNS9pZGVudGl0eS9jbGFpbXMvc2lkIjoiMjE1MCIsImh0dHA6Ly9zY2hlbWFzLm1pY3Jvc29mdC5jb20vd3MvMjAwOC8wNi9pZGVudGl0eS9jbGFpbXMvdmVyc2lvbiI6IjEwOCIsImh0dHA6Ly9leGFtcGxlLm9yZy9jbGFpbXMvbGltaXQiOiIxMDAiLCJodHRwOi8vZXhhbXBsZS5vcmcvY2xhaW1zL21lbWJlcnNoaXAiOiJCYXNpYyIsImh0dHA6Ly9leGFtcGxlLm9yZy9jbGFpbXMvbGFuZ3VhZ2UiOiJlbi1nYiIsImh0dHA6Ly9zY2hlbWFzLm1pY3Jvc29mdC5jb20vd3MvMjAwOC8wNi9pZGVudGl0eS9jbGFpbXMvZXhwaXJhdGlvbiI6IjIwOTktMTItMzEiLCJodHRwOi8vZXhhbXBsZS5vcmcvY2xhaW1zL21lbWJlcnNoaXBzdGFydCI6IjIwMTktMDMtMjMiLCJpc3MiOiJodHRwczovL2F1dGhzZXJ2aWNlLnByaWFpZC5jaCIsImF1ZCI6Imh0dHBzOi8vaGVhbHRoc2VydmljZS5wcmlhaWQuY2giLCJleHAiOjE1NTM0MTQ3NjUsIm5iZiI6MTU1MzQwNzU2NX0.-XRMtKwztlQx8NClDTkz0Ie7Z6Ki-C2ZEx7mQmGECT4&format=json&language=en-gb");
                URLConnection urlConnection = url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                data = new JSONArray(bufferedReader.readLine());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            try {
                textView.setText(data.getJSONObject(0).getJSONObject("Issue").getString("ProfName"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }


    public void getJSON(){
        String json;
        try {
            InputStream inputStream = getAssets().open("SymptomData.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            json = new String(buffer, "UTF-8");
            symptomsData = new JSONArray(json);

            for(int i = 0; i<symptomsData.length();i++){
                JSONObject obj = symptomsData.getJSONObject(i);
                Log.d("TAG","yo ya girl made it here");
                if(obj.getString("Name").toLowerCase().equals(symptom.toLowerCase())){
                    symptomID = obj.getInt("ID");
                    new AsyncWeatherTask().execute();

                }
            }



        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
