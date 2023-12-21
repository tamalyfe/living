package com.example.living.userInterface.mainPage.AuthenticationWelcomePage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import com.example.living.R;
import java.util.ArrayList;

public class AuthenticationWelcomePageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authentication_welcome_page_activity);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().setStatusBarColor(Color.GRAY);
        }

        getSupportActionBar().hide();

        ViewPager vpAuthenticationWelcomePageActivity = (ViewPager)findViewById(R.id.vpAuthenticationWelcomePageActivity);

        AuthenticationPagerAdapter authenticationPagerAdapter = new AuthenticationWelcomePageActivity.AuthenticationPagerAdapter(getSupportFragmentManager());
        authenticationPagerAdapter.addFragmet(new WelcomePageAgencyFragment());
        authenticationPagerAdapter.addFragmet(new WelcomePageTeamFragment());
        vpAuthenticationWelcomePageActivity.setAdapter(authenticationPagerAdapter);
    }

    class AuthenticationPagerAdapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> arrayListFragment = new ArrayList<>();
        public AuthenticationPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return arrayListFragment.get(i);
        }

        @Override
        public int getCount() {
            return arrayListFragment.size();
        }

        void addFragmet(Fragment f) {
            arrayListFragment.add(f);
        }
    }
}