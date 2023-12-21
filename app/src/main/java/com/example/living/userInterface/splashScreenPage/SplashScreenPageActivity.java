package com.example.living.userInterface.splashScreenPage;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.example.living.R;
import com.example.living.databinding.SplashScreenPageActivityBinding;
import com.example.living.userInterface.mainPage.AuthenticationWelcomePage.AuthenticationWelcomePageActivity;
import com.example.living.userInterface.mainPage.BottomNavigationViewMainPageAgencyActivity;

public class SplashScreenPageActivity extends AppCompatActivity {
    private SplashScreenPageActivityBinding binding;
    private Animation middleAnimation;
    private final int SPLASH_SCREEN_DURATION = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = SplashScreenPageActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().setStatusBarColor(Color.GRAY);
        }

        getSupportActionBar().hide();

        middleAnimation = AnimationUtils.loadAnimation(this, R.anim.middle_animation);
        binding.ivSplashScreen.startAnimation(middleAnimation);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(this, BottomNavigationViewMainPageAgencyActivity.class);
            startActivity(intent);
            finish();
        }, SPLASH_SCREEN_DURATION);
    }
}
