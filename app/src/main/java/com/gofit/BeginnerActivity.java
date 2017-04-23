package com.gofit;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BeginnerActivity extends AppCompatActivity implements BeginnerRoutineFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beginner);
        DatabaseReference childRef = FirebaseDatabase.getInstance().getReference().child("beginner").getRef();



        final ArrayList beginner = new ArrayList();
        childRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("ins","ins");
                HashMap map = (HashMap) dataSnapshot.getValue();
                Iterator it = map.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry)it.next();
                    beginner.add(pair.getValue());
                    it.remove(); // avoids a ConcurrentModificationException
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.beginnercontainer,BeginnerRoutineFragment.newInstance(beginner)).commit();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("err","err");
            }
        });


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}