package com.gofit;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ViewPagerActivity extends AppCompatActivity {
    ArrayList selectedList;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            selectedList  = (ArrayList) extras.getSerializable("shoulder");
            position = (int) extras.get("position");
            Log.d("in",""+selectedList.get(0));
        }
        HashMap map = (HashMap) selectedList.get(position);
        Iterator it = map.entrySet().iterator();

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
      //  getSupportFragmentManager().beginTransaction().replace(R.id.layout1,ImageFragment.newInstance());

    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            switch(pos) {

                case 0: return ImageFragment.newInstance(selectedList);
                case 1: return VideoFragment.newInstance(selectedList);
                case 2: return DescriptionFragment.newInstance(selectedList);

                default: return ImageFragment.newInstance(selectedList);
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
