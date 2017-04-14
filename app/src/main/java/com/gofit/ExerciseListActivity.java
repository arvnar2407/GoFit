package com.gofit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class ExerciseListActivity extends AppCompatActivity implements NavigationListener{
    ArrayList selectedList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_list);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (extras.getSerializable("shoulder")!= null)
            {
                selectedList  = (ArrayList) extras.getSerializable("shoulder");
            }

            //The key argument here must match that used in the other activity
            if (selectedList!=null)
            {
               getSupportFragmentManager().beginTransaction().replace(R.id.exercisecontainer, ExerciseRecycler.newInstance(selectedList)).commit();
            }
        }
    }

    @Override
    public void navigate(View v, int position) {

        ImageView imageView = (ImageView)v;
        int id = imageView.getId();
        String idStr = getResources().getResourceName(id);
        Intent intent = new Intent(getApplicationContext(),ViewPagerActivity.class);
        intent.putExtra("id",idStr);
        intent.putExtra("shoulder",selectedList);
        startActivity(intent);
    }
}
