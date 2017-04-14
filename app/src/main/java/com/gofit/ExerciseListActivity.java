package com.gofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class ExerciseListActivity extends AppCompatActivity {
    ArrayList selectedList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_list);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            selectedList  = (ArrayList) extras.getSerializable("shoulder");
            //The key argument here must match that used in the other activity
            if (selectedList!=null)
            {
               getSupportFragmentManager().beginTransaction().replace(R.id.exercisecontainer, ExerciseRecycler.newInstance(selectedList)).commit();
            }
        }
    }
}
