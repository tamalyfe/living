package com.example.living.userInterface.mainPage.featurePage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.living.R;
import com.example.living.userInterface.mainPage.BottomNavigationViewMainPageAgencyActivity;
import com.example.living.userInterface.mainPage.featurePage.ActivityRecordPage.ReadActivityRecordPage;
import com.example.living.userInterface.mainPage.featurePage.matchPage.ReadMatchPageActivity;
import com.example.living.userInterface.mainPage.featurePage.projectPage.ReadProjectPageActivity;
import com.example.living.userInterface.mainPage.featurePage.recruitmentTeamPage.ReadRecruitmentTeamPageActivity;
import com.example.living.userInterface.mainPage.featurePage.requestCustomerPage.ReadRequestCustomerPageActivity;
import com.example.living.userInterface.mainPage.featurePage.surveySchedulePage.ReadSurveySchedulePage;
import com.example.living.userInterface.mainPage.forumPage.ForumPageActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FeaturePageActivity extends AppCompatActivity {
    CardView cvProject;
    CardView cvTeam;
    CardView cvSurveySchedule;
    CardView cvRequestCustomer;
    CardView cvMatch;
    CardView cvActivityReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feature_page_activity);

        setupUIFeaturePageActivity();
        setupNavigationFeaturePageActivity();
    }

    private void setupUIFeaturePageActivity() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().setStatusBarColor(Color.GRAY);
        }

        getSupportActionBar().hide();

        final Toolbar tFeaturePage = (Toolbar)findViewById(R.id.tFeaturePage);
        tFeaturePage.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_dark_green_36dp);
        tFeaturePage.setTitle("Feature Page");
        tFeaturePage.setTitleTextAppearance(getApplicationContext(), R.style.setTitleTextAppearance);
        tFeaturePage.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FeaturePageActivity.this, BottomNavigationViewMainPageAgencyActivity.class);
                startActivity(intent);
                Toast.makeText(FeaturePageActivity.this, "Main Page", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupNavigationFeaturePageActivity() {
        FloatingActionButton fabForumPage = findViewById(R.id.fabForumPage);
        fabForumPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FeaturePageActivity.this, ForumPageActivity.class);
                startActivity(intent);
            }
        });

        cvProject = findViewById(R.id.cvProject);
        cvProject.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(FeaturePageActivity.this, ReadProjectPageActivity.class);
                startActivity(intent);
                Toast.makeText(FeaturePageActivity.this, "Project", Toast.LENGTH_SHORT).show();
            }
        });

        cvTeam = findViewById(R.id.cvTeam);
        cvTeam.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(FeaturePageActivity.this, ReadRecruitmentTeamPageActivity.class);
                startActivity(intent);
                Toast.makeText(FeaturePageActivity.this, "Team", Toast.LENGTH_SHORT).show();
            }
        });

        cvSurveySchedule = findViewById(R.id.cvSurveySchedule);
        cvSurveySchedule.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(FeaturePageActivity.this, ReadSurveySchedulePage.class);
                startActivity(intent);
                Toast.makeText(FeaturePageActivity.this, "Survey Schedule", Toast.LENGTH_SHORT).show();
            }
        });

        cvRequestCustomer = findViewById(R.id.cvRequestCustomer);
        cvRequestCustomer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(FeaturePageActivity.this, ReadRequestCustomerPageActivity.class);
                startActivity(intent);
                Toast.makeText(FeaturePageActivity.this, "Request Customer", Toast.LENGTH_SHORT).show();
            }
        });

        cvMatch = findViewById(R.id.cvMatch);
        cvMatch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(FeaturePageActivity.this, ReadMatchPageActivity.class);
                startActivity(intent);
                Toast.makeText(FeaturePageActivity.this, "Match", Toast.LENGTH_SHORT).show();
            }
        });

        cvActivityReport = findViewById(R.id.cvActivityReport);
        cvActivityReport.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(FeaturePageActivity.this, ReadActivityRecordPage.class);
                startActivity(intent);
                Toast.makeText(FeaturePageActivity.this, "Activity Report", Toast.LENGTH_SHORT).show();
            }
        });
    }
}