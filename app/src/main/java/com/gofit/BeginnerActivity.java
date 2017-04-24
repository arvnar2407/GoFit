package com.gofit;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BeginnerActivity extends AppCompatActivity implements BeginnerRoutineFragment.OnFragmentInteractionListener,Start_Workout_Fragment1.OnFragmentInteractionListener {
    final ArrayList beginner = new ArrayList();
    DatabaseReference history = FirebaseDatabase.getInstance().getReference().child("history").getRef();;
    DatabaseReference childRef;
    DatabaseReference userRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beginner);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        childRef = ref.child("beginner").getRef();
        userRef =ref.child("users/"+ FirebaseAuth.getInstance().getCurrentUser().getUid());



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
    public void onFragmentInteraction(View v, int position) {

        Intent intent = new Intent(getApplicationContext(),ViewPagerActivity.class);


        intent.putExtra("selectedlist",beginner);
        intent.putExtra("position",position);
        startActivity(intent);
    }

    @Override
    public void beginWorkout(ArrayList list) {
        getSupportFragmentManager().beginTransaction().replace(R.id.beginnercontainer, Start_Workout_Fragment1.newInstance(list,0)).addToBackStack(null).commit();
    }

    @Override
    public void onFragmentInteraction(int position,HashMap data) {

        getSupportFragmentManager().beginTransaction().replace(R.id.beginnercontainer, Start_Workout_Fragment1.newInstance(beginner,position)).addToBackStack(null).commit();
        writeData(position,data);

    }
    public void writeData(int position,HashMap data)
    {
       // history.setValue(data);
        userRef.child("Beginner/"+data.get("id").toString()).setValue(data);
    }

}
