package com.gofit;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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

public class TrackActivity extends MainActivity implements ExerciseRecycler.OnFragmentInteractionListener{
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    DatabaseReference childRef;
    DatabaseReference userRef;
    ArrayList history;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);
        super.createDrawer();
        childRef = ref.child("beginner").getRef();
        history = new ArrayList();
        userRef =ref.child("users/"+ FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Beginner");
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                HashMap map = (HashMap) dataSnapshot.getValue();
                Iterator it = map.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry)it.next();
                    history.add(pair.getValue());
                    it.remove(); // avoids a ConcurrentModificationException
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.trackcontainer,ExerciseRecycler.newInstance(history)).addToBackStack(null).commit();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("err","err");
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public void onFragmentInteraction(View v, int position) {
        ImageView imageView = (ImageView)v;
        int id = imageView.getId();
        String idStr = getResources().getResourceName(id);
        Intent intent = new Intent(getApplicationContext(),ViewPagerActivity.class);
        intent.putExtra("id",idStr);

        intent.putExtra("selectedlist",history);
        intent.putExtra("position",position);
        startActivity(intent);
    }
}
