package com.gofit;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import app.num.numandroidpagecurleffect.PageCurlView;

public class DietActivity extends AppCompatActivity implements DietFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);
        PageCurlView pageCurlView = (PageCurlView) findViewById(R.id.pagecurl_view);
        List<Integer> pages_id = new ArrayList<>();
        pages_id.add(R.drawable.background1);
        pages_id.add(R.drawable.home1);
        pageCurlView.setCurlView(pages_id);
        pageCurlView.setCurlSpeed(65);
     //   getSupportFragmentManager().beginTransaction().replace(R.id.dietcontainer,DietFragment.newInstance()).addToBackStack(null).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
