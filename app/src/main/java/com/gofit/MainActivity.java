package com.gofit;

import android.app.LauncherActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.TaskStackBuilder;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
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
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    FirebaseUser user;
MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = FirebaseAuth.getInstance().getCurrentUser();
        createDrawer();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),BackActivity.class);
                mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.float2);
                mediaPlayer.start();
                startActivity(intent);

            }
        });
        DatabaseReference childRef = FirebaseDatabase.getInstance().getReference().child("exercisedata").getRef();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        DatabaseReference newref =ref.child("users/"+user.getUid());


        final ExerciseData data;
        final ArrayList shoulder = new ArrayList();
        final ArrayList biceps = new ArrayList();
        final ArrayList abs = new ArrayList();
        final ArrayList selected = new ArrayList();
        childRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("ins","ins");
                HashMap map = (HashMap) dataSnapshot.getValue();
                Iterator it = map.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry)it.next();

                    if(pair.getKey().equals("shoulder"))
                    {
                        shoulder.add(pair.getValue());
                    }
                    else if(pair.getKey().equals("biceps"))
                    {
                        biceps.add(pair.getValue());
                    }
                    else if(pair.getKey().equals("abs"))
                    {
                        abs.add(pair.getValue());
                    }

                    it.remove(); // avoids a ConcurrentModificationException
                }
                HashMap selectedMap = (HashMap) shoulder.get(0);

                Iterator it1 = selectedMap.entrySet().iterator();
                while (it1.hasNext()) {
                    Map.Entry pair = (Map.Entry)it1.next();

                    selected.add(pair.getValue());
                    it1.remove(); // avoids a ConcurrentModificationException
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("err","err");
            }
        });
        TextView shoulderView = (TextView) findViewById(R.id.shoulder);
        TextView absView = (TextView) findViewById(R.id.abs);
        TextView bicepsView = (TextView) findViewById(R.id.biceps);
        shoulderView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ExerciseListActivity.class);
                intent.putExtra("shoulder",selected);
                startActivity(intent);

            }
        });
        absView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        bicepsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void createDrawer() {
        String name = null;
        String email = null;
        Uri photoUrl=null;
        if (user != null) {
            // Name, email address, and profile photo Url
            name = user.getDisplayName();
            email = user.getEmail();
            photoUrl = user.getPhotoUrl();

        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hView =  navigationView.getHeaderView(0);
        TextView nav_user = (TextView)hView.findViewById(R.id.headername);
        TextView nav_email = (TextView)hView.findViewById(R.id.headeremail);
        nav_user.setText(name);
        nav_email.setText(email);
        if(photoUrl !=null)
        {
            Picasso.with(getApplicationContext()).load(photoUrl).into((ImageView) hView.findViewById(R.id.navimage1));
        }
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
           finish();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle
            mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.navbut);
            mediaPlayer.start();
            Intent intent = new Intent(this ,MainActivity.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.nav_setr) {
            mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.navbut);
            mediaPlayer.start();
            Intent intent = new Intent(this,CategoryActivity.class);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this);
                intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                startActivity(intent, options.toBundle());

            } else {
                intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                startActivity(intent);

            }
        } else if (id == R.id.nav_trackr) {
            mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.navbut);
            mediaPlayer.start();
            Intent intent = new Intent(this ,TrackActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_locate) {
            mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.navbut);
            mediaPlayer.start();
            Intent intent = new Intent(this,MapsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_diet) {
            mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.navbut);
            mediaPlayer.start();

        }
        else if (id == R.id.logout) {
            FirebaseAuth auth = FirebaseAuth.getInstance();
            auth.signOut();
            mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.navbut);
            mediaPlayer.start();
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
