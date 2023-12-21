package com.example.living.userInterface.mainPage.authenticationAccountPage.loginPage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.living.R;
import com.example.living.userInterface.mainPage.AuthenticationWelcomePage.AuthenticationWelcomePageActivity;
import com.example.living.userInterface.mainPage.BottomNavigationViewMainPageAgencyActivity;

public class LoginPageAgencyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page_agency_activity);

        setupUILoginPageAgencyActivity();
        setupNavigationLoginPageAgencyActivity();
    }

    private void setupUILoginPageAgencyActivity() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().setStatusBarColor(Color.GRAY);
        }

        getSupportActionBar().hide();

        final Toolbar tLoginPageAgencyActivity = (Toolbar)findViewById(R.id.tLoginPageAgencyActivity);
        tLoginPageAgencyActivity.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_dark_green_36dp);
        tLoginPageAgencyActivity.setTitle("Login");
        tLoginPageAgencyActivity.setTitleTextAppearance(getApplicationContext(), R.style.setTitleTextAppearance);
        tLoginPageAgencyActivity.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginPageAgencyActivity.this, AuthenticationWelcomePageActivity.class);
                startActivity(intent);
                Toast.makeText(LoginPageAgencyActivity.this, "Welcome Page", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupNavigationLoginPageAgencyActivity() {
        Button bLoginPageAgencyActivity = findViewById(R.id.bLoginPageAgencyActivity);
        bLoginPageAgencyActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginPageAgencyActivity.this, BottomNavigationViewMainPageAgencyActivity.class);
                startActivity(intent);
                Toast.makeText(LoginPageAgencyActivity.this, "Main Page", Toast.LENGTH_SHORT).show();
            }
        });
    }
}