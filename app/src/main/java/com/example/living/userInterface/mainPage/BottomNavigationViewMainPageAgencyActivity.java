package com.example.living.userInterface.mainPage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import com.example.living.userInterface.mainPage.authenticationAccountPage.AccountPageAgencyFragment;
import com.example.living.userInterface.mainPage.featurePage.matchPage.ReadMatchPageFragment;
import com.example.living.userInterface.mainPage.featurePage.requestCustomerPage.CreateRequestCustomerPageFragment;
import com.example.living.userInterface.mainPage.homePage.HomePageFragment;
import com.example.living.userInterface.mainPage.mapPage.MapPageFragment;
import com.example.living.R;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;

public class BottomNavigationViewMainPageAgencyActivity extends AppCompatActivity implements com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener {
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_navigation_view_main_page_agency_activity);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().setStatusBarColor(Color.GRAY);
        }

        getSupportActionBar().hide();

        loadFragment(new HomePageFragment());
        com.google.android.material.bottomnavigation.BottomNavigationView bnv = findViewById(R.id.bnv);
        bnv.setOnNavigationItemSelectedListener(this);

        bnv.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        bnv.setSelectedItemId(R.id.bnv_home);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem mi) {
        Fragment fragment = null;
        switch (mi.getItemId()) {
            case R.id.bnv_home:
                fragment = new HomePageFragment();
                break;
            case R.id.bnv_map:
                fragment = new MapPageFragment();
                break;
            case R.id.bnv_match:
                fragment = new ReadMatchPageFragment();
                break;
            case R.id.bnv_request_customer:
                fragment = new CreateRequestCustomerPageFragment();
                break;
            case R.id.bnv_account:
                fragment = new AccountPageAgencyFragment();
                break;
        }
        return loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fl, fragment).commit();
            return true;
        }
        return false;
    }
}