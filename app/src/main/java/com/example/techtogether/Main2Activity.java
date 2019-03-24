package com.example.techtogether;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    EditText editText;
    EditText editTextAge;
    RadioGroup radioGroup;
    Button diagnosis;
    TextView gender;

    String sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        editText = findViewById(R.id.editText);
        radioGroup = findViewById(R.id.radioGroup);
        editTextAge = findViewById(R.id.editText2);
        diagnosis = findViewById(R.id.button3);
        gender = findViewById(R.id.textView2);

        editText.setTextColor(Color.rgb(0,169,165));
        editTextAge.setTextColor(Color.rgb(0,169,165));
        gender.setTextColor(Color.rgb(11,83,81));
        diagnosis.setBackgroundColor(Color.rgb(144,194,231));
        diagnosis.setTextColor(Color.rgb(255,255,255));

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.getText().clear();
            }
        });

        editTextAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextAge.getText().clear();
            }
        });

        diagnosis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToDiagnose = new Intent(Main2Activity.this, Main3Activity.class);
                intentToDiagnose.putExtra("Symptom", editText.getText().toString());
                intentToDiagnose.putExtra("Age", Integer.parseInt(editTextAge.getText().toString()));
                intentToDiagnose.putExtra("Sex", sex);
                startActivity(intentToDiagnose);
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.radioButtonFemale){
                    sex = "female";
                }
                else{
                    sex = "male";
                }
            }
        });
    }
}
