package com.example.user.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.user.myapplication.fragments_schedule.Schedule.FragmentNotes;

public class Schedule extends AppCompatActivity {
    FragmentPagerAdapter adapterViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);

    }
    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 3;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment
                    return FragmentMain.newInstance(0, "Розклад");
                case 1: // Fragment # 0 - This will show FirstFragment different title
                    return FragmentNotes.newInstance(1, "Нотатки");
                case 2: // Fragment # 1 - This will show SecondFragment
                    return FragmentNotes.newInstance(2, "Чат");
                default:
                    return null;
            }
        }
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment
                    return  "Розклад";
                case 1: // Fragment # 0 - This will show FirstFragment different title
                    return "Нотатки";
                case 2: // Fragment # 1 - This will show SecondFragment
                    return "Чат";
                default:
                    return null;
            }
        }

    }

}

