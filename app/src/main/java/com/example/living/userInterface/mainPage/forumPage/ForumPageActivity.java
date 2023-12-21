package com.example.living.userInterface.mainPage.forumPage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.living.R;
import com.example.living.userInterface.mainPage.AuthenticationWelcomePage.AuthenticationWelcomePageActivity;
import com.example.living.userInterface.mainPage.BottomNavigationViewMainPageAgencyActivity;
import com.example.living.userInterface.mainPage.authenticationAccountPage.loginPage.LoginPageAgencyActivity;
import com.google.android.material.tabs.TabLayout;

public class ForumPageActivity extends AppCompatActivity {
    private TabLayout tlForum;
    private ViewPager vpForum;
    private ViewPagerForumPageAdapter fpaForum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forum_page_activity);

        setupUIForumPageActivity();
        setupNavigationForumPageActivity();
    }

    private void setupUIForumPageActivity() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().setStatusBarColor(Color.GRAY);
        }

        getSupportActionBar().hide();

        final Toolbar tForumPage = (Toolbar)findViewById(R.id.tForumPage);
        tForumPage.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_dark_green_36dp);
        tForumPage.setTitle("Forum Page");
        tForumPage.setTitleTextAppearance(getApplicationContext(), R.style.setTitleTextAppearance);
        tForumPage.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForumPageActivity.this, BottomNavigationViewMainPageAgencyActivity.class);
                startActivity(intent);
                Toast.makeText(ForumPageActivity.this, "Main Page", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupNavigationForumPageActivity() {
        tlForum = findViewById(R.id.tl_Forum);
        vpForum = findViewById(R.id.vp_Forum);

        fpaForum = new ViewPagerForumPageAdapter(getSupportFragmentManager(), tlForum.getTabCount());
        vpForum.setAdapter(fpaForum);

        tlForum.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vpForum.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        vpForum.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tlForum));
    }
}