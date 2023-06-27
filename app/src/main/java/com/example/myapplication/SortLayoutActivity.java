package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.Arrays;
import java.util.Comparator;

public class SortLayoutActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort_layout);

        SharedStorage.init(getApplicationContext());

        Integer taggedRadioIndex = Integer.parseInt(SharedStorage.getProperty("tagged"));
        final RadioGroup radio = findViewById(R.id.radioGroup);
        radio.check(radio.getChildAt(taggedRadioIndex).getId());

        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                View radioButton = radio.findViewById(checkedId);
                int index = radio.indexOfChild(radioButton);


                switch (index) {
                    case 0:
                        SharedStorage.addProperty("sort", "from");
                        SharedStorage.addProperty("tagged", "0");
                        break;
                    case 1:
                        SharedStorage.addProperty("sort", "dest");
                        SharedStorage.addProperty("tagged", "1");
                        break;
                    case 2:
                        SharedStorage.addProperty("sort", "size");
                        SharedStorage.addProperty("tagged", "2");
                        break;
                    case 3:
                        SharedStorage.addProperty("sort", "cost");
                        SharedStorage.addProperty("tagged", "3");
                        break;

                    default:
                        break;
                }
            }
        });
    }

    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}