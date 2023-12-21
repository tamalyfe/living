package com.example.living.userInterface.mainPage.authenticationAccountPage;

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
import com.example.living.userInterface.mainPage.authenticationAccountPage.loginPage.LoginPageTeamFragment;
import com.example.living.userInterface.mainPage.authenticationAccountPage.registerPage.RegisterPageTeamFragment;

import java.util.ArrayList;

public class AuthenticationAccountPageTeamActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authentication_account_page_team_activity);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().setStatusBarColor(Color.GRAY);
        }

        getSupportActionBar().hide();

        ViewPager vpAccount = (ViewPager)findViewById(R.id.vp_ViewPagerAccountPage);

        AuthenticationPagerAdapter pa = new AuthenticationPagerAdapter(getSupportFragmentManager());
        pa.addFragmet(new LoginPageTeamFragment());
        pa.addFragmet(new RegisterPageTeamFragment());
        vpAccount.setAdapter(pa);
    }

    class AuthenticationPagerAdapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> alf = new ArrayList<>();
        public AuthenticationPagerAdapter(FragmentManager fm) { super(fm); }
        @Override
        public Fragment getItem(int i) { return alf.get(i); }
        @Override
        public int getCount() { return alf.size(); }
        void addFragmet(Fragment f) { alf.add(f); }
    }
}