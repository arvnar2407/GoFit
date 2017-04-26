package com.gofit;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BackActivity extends MainActivity {

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back);
        super.createDrawer();
//        DatabaseReference childRef = FirebaseDatabase.getInstance().getReference().child("exercisedata").getRef();
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
//        DatabaseReference newref =ref.child("users/"+user.getUid());
//
//
//        final ExerciseData data;
//        childRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                HashMap map = (HashMap) dataSnapshot.getValue();
//                Iterator it = map.entrySet().iterator();
//                while (it.hasNext()) {
//                    Map.Entry pair = (Map.Entry)it.next();
//
//                    if(pair.getKey().equals("shoulder"))
//                    {
//                        shoulder.add(pair.getValue());
//                    }
//                    else if(pair.getKey().equals("biceps"))
//                    {
//                        biceps.add(pair.getValue());
//                    }
//                    else if(pair.getKey().equals("abs"))
//                    {
//                        abs.add(pair.getValue());
//                    }
//
//                    it.remove(); // avoids a ConcurrentModificationException
//                }
//
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Log.d("err","err");
//            }
//        });
        final Animation animScale = AnimationUtils.loadAnimation(this, R.anim.anim_scale);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                mediaPlayer = MediaPlayer.create(BackActivity.this, R.raw.float2);
                view.startAnimation(animScale);
                mediaPlayer.start();
                startActivity(intent);
                finish();
            }
        });

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
        //do nothing
        }
    }
}
