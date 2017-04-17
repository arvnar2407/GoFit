package com.gofit;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LaunchscreenActivity extends AppCompatActivity implements LaunchFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launchscreen);
        getSupportFragmentManager().beginTransaction().replace(R.id.launch,LaunchFragment.newInstance()).commit();

    }

    @Override
    public void onFragmentInteraction(View v) {
        if (v.getId() == R.id.btn_letsgo)
        {
            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(intent);
        }
        else if (v.getId()==R.id.btn_about)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.launch,AboutFragment.newInstance()).addToBackStack(null).commit();
        }

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
