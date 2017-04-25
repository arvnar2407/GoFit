package com.gofit;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LaunchscreenActivity extends AppCompatActivity implements LaunchFragment.OnFragmentInteractionListener{
    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    //    mediaPlayer = MediaPlayer.create(LaunchscreenActivity.this, R.raw.launch);
     //   mediaPlayer.start();
        setContentView(R.layout.activity_launchscreen);
        getSupportFragmentManager().beginTransaction().replace(R.id.launch,LaunchFragment.newInstance()).commit();

    }

    @Override
    public void onFragmentInteraction(View v) {
        if (v.getId() == R.id.btn_letsgo1)
        {
            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(intent);
        }
        else if (v.getId()==R.id.btn_about1)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.launch,AboutFragment.newInstance()).addToBackStack(null).commit();
        }

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
